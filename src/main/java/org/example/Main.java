package org.example;

public class Main {
    public static void main(String[] args) {
        if (args.length <1) {
            System.out.println("please provide an author name");
            return;
        }

        try{
            retrieveCourses(args[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void retrieveCourses(String arg) {
        System.out.println("Retrieving courses for:" + arg);
    }
}
