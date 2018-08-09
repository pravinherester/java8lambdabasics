package org.pravin;

public class MethodReference {  
    public  int saySomething(){  
        System.out.println("Hello, this is static method.");
        return 0;  
    }  
    public static void main(String[] args) { 
        MethodReference ob=new MethodReference();
        // Referring static method  
        Sayable sayable = ob::saySomething;  
        // Calling interface method  
        sayable.say();  
    }  

interface Sayable{  
    int say();  
}  

}