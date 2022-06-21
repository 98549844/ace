package com;


import java.util.ArrayList;
import java.util.Date;

public class Hello {


    //task1_1
    //The length  of the string must be 8 characters.
    //Only allow 0 to 9 and A<Z (upper case)
    //The first character must be alphabet
    public static final String ONLY_UPPERCASE_OR_NUMBER = "^[0-9A-Z]+$";

    public static boolean task1(String text) {
        if (text.length() == 8 && text.matches(ONLY_UPPERCASE_OR_NUMBER) && !Character.isDigit(text.charAt(0))) {
            return true;
        }
        return false;
    }


    //task1_2
    public static Integer add(String text) {
        if (text == null || text.length() != 8) {
            return null;
        }
        char[] arr = text.toCharArray();
        Integer num = 0;
        for (int i = 0; i < 8; i++) {
            char t = arr[i];
            if (Character.isDigit(t)) {
                num = num + Integer.parseInt(String.valueOf(t));
            } else if (t == 'A' || t == 'K' || t == 'U') {
            } else if (t == 'B' || t == 'L' || t == 'V') {
                num = num + 1;
            } else if (t == 'C' || t == 'M' || t == 'W') {
                num = num + 2;
            } else if (t == 'D' || t == 'N' || t == 'X') {
                num = num + 3;
            } else if (t == 'E' || t == 'O' || t == 'Y') {
                num = num + 4;
            } else if (t == 'F' || t == 'P' || t == 'Z') {
                num = num + 5;
            } else if (t == 'G' || t == 'Q') {
                num = num + 6;
            } else if (t == 'H' || t == 'R') {
                num = num + 7;
            } else if (t == 'I' || t == 'S') {
                num = num + 8;
            } else if (t == 'J' || t == 'T') {
                num = num + 9;
            }
        }

        int remainder = num % 11;
        if (remainder == 0 || remainder == 10) {
            return 0;
        } else {
            return remainder;
        }
    }

    //task2_1
/*     Primitive-type instance variables are initialized by default.
       Variables of types byte, char, short, int, long, float and double are initialized to 0.
       Variables of type boolean are initialized to false.
       */

/*    Reference-type variables store the location of an object in the computer’s memory.
      Such variables refer to objects
      */

    //task2_2
    public static Date task2_2(Object obj) {
        Date dtTmp = (Date) obj;
        return dtTmp;

    }

    public static void main(String[] args) {
        System.out.println(task2_2(new Date()));
    }


    //task2_3
    //console output
/*    15 0
      20
*/


    //task3_1
/*
    select
    a.*,
    b.*
    from tableA a
    left join tableB b on a.column1 = b.column1;
*/
    //task3_2
/*
    update tableA a
    set a.Column1 = (select Column1 from tableB b where b.Column2 = a.Column2);
*/

//task4_1
/*    Write a non-instance method, Name: CheckAge, output: boolean, input:
    ArrayList<Person>
    Function description: Check if there exists 1 record with a Person.Age less
    than or equal to 20.*/

//    public static boolean CheckAge(ArrayList<Person> person) {
//        for (int i = 0; i < person.size(); i++) {
//            if (person.get(i).getAge() <= 20) {
//                return true;
//            }
//        }
//        return false;
//    }


    //task4_2
    //springboot+thymeleaf

    /*
1. Quickly buildup a java web project
2. common and well known java based project, most developer willing to use and development,
   rich in literature and online resources, beneficial for development and debugging
3. Perfect and rich support for various commonly used plug-ins,
   such as database connection, redis, mq which have good solutions
*/
}

