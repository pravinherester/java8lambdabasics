package org.java8action;

@FunctionalInterface
public interface TestInterface {
    void testmethodrefernce();

}


class TestClass implements TestInterface {

    @Override
    public void testmethodrefernce() {
        System.out.println("Hello Test Method reference");

    }

    public void testmethodrefernce(String testString) {
        System.out.println("This is the String you passed " +testString);

    }
    public void testMethodReferenceImplementation(TestInterface testMethod) {

        testMethod.testmethodrefernce();
    }

}