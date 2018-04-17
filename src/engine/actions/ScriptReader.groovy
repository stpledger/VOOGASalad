package engine.actions

import java.lang.reflect.Method
import java.util.function.Consumer

class ScriptReader {

    HashMap entities
    int index
    String className, methodName
    def myBinding, shell, number

    public ScriptReader(HashMap entities) {
        this.entities = entities
        myBinding = new Binding()
        myBinding.setVariable("entities", entities)
        shell = new GroovyShell(myBinding)
    }

    public void readCommand (String command) {
        String arguments = command

        Class clazz = readArguments(arguments)
        def methodParams = getMethodParams(clazz)
        Method method = clazz.getDeclaredMethod(methodName, methodParams)

        def invokeArgs = getInvokeArgs(methodParams, arguments)
        doMethod(method, invokeArgs, methodParams)
    }

    private Class readArguments (String arguments) {
        for (int i = 0; i < arguments.length(); i++) {
            if (arguments.substring(i, i+1).equals(" ") && number == null) {
                number = arguments.substring(0, i).toInteger()
                index = i
            }
            else if (arguments.substring(i, i+1).equals(" ") && number!=null && className == null) {
                className = arguments.substring(index+1, i)
                index = i
            }
            else if (arguments.substring(i, i+1).equals(" ") && className!=null && methodName == null) {
                methodName = arguments.substring(index+1, i)
                index = i
                break
            }
        }
        return entities.get(number).get(className).class
    }

    private def getMethodParams (Class clazz) {
        def methodParams;

        Method[] declaredMethods = clazz.getDeclaredMethods();

        for (Method declaredMethod: declaredMethods) {
            if (declaredMethod.getName().equals(methodName)) {
                methodParams = declaredMethod.getParameterTypes()
            }
        }
        return methodParams
    }

    private def getInvokeArgs(def methodParams, String arguments) {
        def invokeArgs = new Object[methodParams.size()]
        def newIndex = index + 1

        for (int i = 0; i < invokeArgs.length; i++) {
            while (newIndex < arguments.length()) {
                if (arguments.substring(newIndex, newIndex+1).equals(" ")) {
                    invokeArgs[i] = arguments.substring(index+1, newIndex)
                    index = newIndex
                    newIndex++
                    break
                }
                else if (i==invokeArgs.length-1) {
                    invokeArgs[i] = arguments.substring(newIndex)
                    break
                }
                newIndex++
            }
        }
        return invokeArgs
    }

    private void doMethod(Method myMethod, def invokeArgs, def methodParams) {
        for (int i = 0; i < methodParams.length; i++) {
            Class clazz = methodParams[i]
            if (clazz == javafx.scene.input.KeyCode) {
                invokeArgs[i] = clazz.getKeyCode(invokeArgs[i])
            }
            else if (clazz == java.util.function.Consumer) {
                def action = invokeArgs[i]
                invokeArgs[i] = new Consumer() {
                    @Override
                    void accept(Object o) {
                        shell.evaluate(action)
                    }
                }
            }
            else invokeArgs[i] = Double.parseDouble(invokeArgs[i]).doubleValue()
        }

        myMethod.invoke(entities.get(number).get(className), invokeArgs)
    }
}
