package org.java8action;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferReaderLambdaExample {
    public String process() throws FileNotFoundException, IOException {
        try (BufferedReader ob = new BufferedReader(new FileReader("c:/Users/pjagadeesan/learning/Java8lambdabasics/src/Data"))) {
            return ob.readLine();
        }

    }

    public String process(BufferReaderProcessor bp) throws FileNotFoundException, IOException {
        try (BufferedReader ob = new BufferedReader(new FileReader("c:/Users/pjagadeesan/learning/Java8lambdabasics/src/Data"))) {
            return bp.process(ob);
        }

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        BufferReaderLambdaExample br = new BufferReaderLambdaExample();
        String s = br.process();
        System.out.println("First line : " + s);
        String oneline = br.process((BufferedReader br1) -> br1.readLine());
        String twolines = br.process((BufferedReader br1) -> br1.readLine() + br1.readLine());
        String threelines = br.process((BufferedReader br1) -> br1.readLine() + br1.readLine() + br1.readLine());
        System.out.println(oneline);
        System.out.println(twolines);
        System.out.println(threelines);

    }

}

interface BufferReaderProcessor {
    String process(BufferedReader br) throws IOException;
}
