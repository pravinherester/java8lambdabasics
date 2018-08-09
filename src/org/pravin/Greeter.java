package org.pravin;

public class Greeter {

    public static void main(String[] args) {
        Greeter greeter = new Greeter();
        HelloWorldGreeting helloWorldGreeting = new HelloWorldGreeting();
        Greeting mylambda = () -> System.out.println("Hello World");
        greeter.greet(helloWorldGreeting);
        greeter.greet(mylambda);

    }

    public void greet(Greeting greeting) {
        greeting.perform();
        greeting.test();

      
        ;

    }
}
