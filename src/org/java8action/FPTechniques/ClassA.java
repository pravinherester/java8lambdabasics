package org.java8action.FPTechniques;

public class ClassA {
    DTO obj=new DTO();
    void testmethodA(){
      
        obj.setTest("Pravin");
    }
    
    void testmethodB(){
        
        obj.setTest("Rravin");
    }
    
    void testMethodC()
    {
        System.out.println(obj.getTest());
    }
    
    public static void main(String[] args) {
        ClassA obj1=new ClassA();
        obj1.testmethodA();
        obj1.testmethodB();
        obj1.testMethodC();
        
        ClassB obj2=new ClassB();
        obj2.testmethodA();
        obj2.testMethodC();
        obj2.testmethodB();
     
        String a="ABCD";
        String b="a12";
        System.out.println(b.compareTo(a));
               
    }

}
