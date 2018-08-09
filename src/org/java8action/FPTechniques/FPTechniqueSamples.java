package org.java8action.FPTechniques;

import java.util.function.DoubleUnaryOperator;

public class FPTechniqueSamples {
    
    static DoubleUnaryOperator curriedConvertor(double f, double b){
        return (double x) ->x*f+b;
    }

    public static void main(String[] args) {
       DoubleUnaryOperator sampleConv= FPTechniqueSamples.curriedConvertor(9, 5);
       DoubleUnaryOperator AnothersampleConv= FPTechniqueSamples.curriedConvertor(12, 4);
       Double d=sampleConv.applyAsDouble(10);
       Double d1=AnothersampleConv.applyAsDouble(10);
       System.out.println(d);
       System.out.println(d1);
       }
}
