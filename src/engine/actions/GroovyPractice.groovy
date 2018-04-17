package engine.actions

import engine.components.KeyInput
import javafx.scene.input.KeyCode

import java.lang.reflect.Method
import java.util.function.Consumer

class GroovyPractice {

    static void main (String [] args) {

        def map = new HashMap()
        def map2 = new HashMap()
        map2.put("KeyInput", new KeyInput(1, null, null))
        map.put(1, map2)

        def myBinding = new Binding()
        myBinding.setVariable("map", map)
        def shell = new GroovyShell()

        def className
        def methodName
        def index = 0

        def arguments = """KeyInput addCode P 'System.out.println(map.get(1).get("KeyInput").getParentID())'"""

        //def action = 'System.out.println(map.get(1).get("KeyInput").getParentID())'

        for (int i = 0; i < arguments.length(); i++) {
            if (arguments.substring(i, i+1).equals(" ") && className == null) {
                className = arguments.substring(0, i)
                index = i
            }
            else if (arguments.substring(i, i+1).equals(" ") && className!=null && methodName == null) {
                methodName = arguments.substring(index+1, i)
                index = i
                break
            }
        }

        def ki = (map.get(1).get(className)).class
        def methodParams;

        Method[] declaredMethods = ki.getDeclaredMethods();

        for (Method declaredMethod: declaredMethods) {
            if (declaredMethod.getName().equals(methodName)) {
                methodParams = declaredMethod.getParameterTypes()
            }
        }

        Method myMethod = ki.getDeclaredMethod(methodName, methodParams)

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

        for (int i = 0; i < methodParams.length; i++) {
            Class clazz = methodParams[i]
            if (clazz == javafx.scene.input.KeyCode) {
                invokeArgs[i] = clazz.getKeyCode(invokeArgs[i])
            }
            else if (clazz == java.util.function.Consumer) {
                def action = invokeArgs[i]
                def script = shell.parse(action)
                script.binding = myBinding
                invokeArgs[i] = new Consumer() {
                    @Override
                    void accept(Object o) {
                        script.run()
                    }
                }
            }
            else invokeArgs[i] = clazz.newInstance(invokeArgs[i])
        }

        myMethod.invoke(map.get(1).get(className), invokeArgs)

        map.get(1).get(className).action(KeyCode.P);

    }

    /**
     * Ideal way to have a user input information to a component:
     * componentObject componentMethod args[]
     */

}
