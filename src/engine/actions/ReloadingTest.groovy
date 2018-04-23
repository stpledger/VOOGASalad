package engine.actions


    class Greeter {
        String sayHello() {
            def greet = new Dependency().message
            greet
        }
    }

    new Greeter()
