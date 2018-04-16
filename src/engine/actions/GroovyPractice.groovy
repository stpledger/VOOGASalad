package engine.actions

import engine.components.KeyInput
import groovy.lang.GroovyClassLoader
import groovy.util.Eval;

import engine.components.AI
import javafx.scene.input.KeyCode
import org.codehaus.groovy.tools.GroovyClass

import javax.xml.crypto.dsig.keyinfo.KeyName
import java.lang.reflect.Method
import java.util.function.Consumer

class GroovyPractice {

    static void main (String [] args) {

        def map = new HashMap()
        def map2 = new HashMap()
        map2.put("KeyInput", new KeyInput(1, null, null))
        map.put(1, map2)

        /**def action = 'println map.get(1).getParentID()'

        def shell = new GroovyShell()
        def script = shell.parse(action)**/
        def name;

        def arguments = """KeyInput addCode KeyCode.SPACE 
            new Consumer() {
                @Override
                void accept(Object o) {
                  println map.get(1).get(name).getParentID()
                }
            }"""

        for (int i = 0; i < arguments.length(); i++) {
            if (arguments.substring(i, i+1).equals(" ")) {
                name = arguments.substring(0, i)
                break
            }
        }

        def ki = (map.get(1).get(name)).class

        def methodParams;

        Method[] declaredMethods = ki.getDeclaredMethods();

        for (Method declaredMethod: declaredMethods) {
            if (declaredMethod.getName().equals("addCode")) {
                methodParams = declaredMethod.getParameterTypes()
            }
        }

        Method myMethod = ki.getDeclaredMethod("addCode", methodParams)

        def invokeArgs = new Object[methodParams.size()]

        invokeArgs[0] = KeyCode.SPACE
        invokeArgs[1] = new Consumer() {
            @Override
            void accept(Object o) {
                println map.get(1).get(name).getParentID()
            }
        }

        myMethod.invoke(map.get(1).get(name), invokeArgs)

        map.get(1).get(name).action(KeyCode.SPACE);

    }

    /**
     * Ideal way to have a user input information to a component:
     * componentObject componentMethod args[]
     */

}
