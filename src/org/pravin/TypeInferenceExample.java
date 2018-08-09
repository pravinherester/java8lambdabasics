package org.pravin;

public class TypeInferenceExample {
    public static void main(String[] args) {
        StringLengthLambda length = (String s) -> s.length();
        StringLengthLambda length1 = (s) -> s.length();
        StringLengthLambda length2 = s -> s.length();
        System.out.println(length.getLength("Pravin"));
        printLengthOfString(length2);
        printLengthOfString(s->s.length());

    }
   public static void printLengthOfString(StringLengthLambda lambda)
    {
        System.out.println(lambda.getLength("Hello"));
    }

}


interface StringLengthLambda {

    int getLength(String s);
}