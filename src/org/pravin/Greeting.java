package org.pravin;

@FunctionalInterface
public interface Greeting {
  void perform();
  
  default void test()
  {
      System.out.println("Pravin");
  }

}
