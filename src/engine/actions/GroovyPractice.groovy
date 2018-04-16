package engine.actions

import groovy.lang.GroovyClassLoader
import groovy.util.Eval;

import engine.components.AI
import javafx.scene.input.KeyCode
import org.codehaus.groovy.tools.GroovyClass

import java.util.function.Consumer

class GroovyPractice {

    static void main (String [] args) {

        def file = new File("/Users/charliedracos/eclipse-workspace/voogasalad_oneclassonemethod/src/engine/components/KeyInput.java")

        def shell = new GroovyShell()
        def script = shell.parse(action)

        def gcl = new GroovyClassLoader()
        def clazz1 = gcl.parseClass(file)
        def o = clazz1.newInstance(1, KeyCode.SPACE, new Consumer() {
            @Override
            void accept(Object o) {
                script.run()
            }
        })
        o.action(KeyCode.SPACE)

    }

    def static action = "println 'hello'"

}
