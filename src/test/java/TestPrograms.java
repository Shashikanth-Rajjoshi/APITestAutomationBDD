import groovyjarjarantlr4.v4.codegen.model.SrcOp;

import  java.util.*;

public class TestPrograms {

    public static void main(String[] args){
//        String s = convertStringToUpperOrLowerCases("Hello World");
//        System.out.println("Converted String is: "+s);
        List<Integer> inp= Arrays.asList(0,-20,4,200);
        Scanner s = new Scanner(System.in);
        System.out.println(doubleTheValuesInArrayUsingStreams(inp));
    }

    public static String convertStringToUpperOrLowerCases(String value){
        char[] charArray = value.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : charArray){
            if(Character.isUpperCase(c)){
                sb.append(Character.toLowerCase(c));
            }else {
                sb.append(Character.toUpperCase(c));
            }
        }
        return  sb.toString();
    }

    public static List<Integer> doubleTheValuesInArrayUsingStreams(List<Integer>list){
        return list.stream().map(n->n*2).toList();
    }
}
