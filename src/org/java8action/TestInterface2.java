package org.java8action;

@FunctionalInterface
public interface TestInterface2 {
    void testmethodrefernce(String testString);

}


class TestClass2 implements TestInterface2 {

    @Override
    public void testmethodrefernce(String testString) {
        System.out.println("This is the String you passed " +testString);

    }

    public void testMethodReferenceImplementation(TestInterface2 testMethod) {

        testMethod.testmethodrefernce("Hi test");
    }

}