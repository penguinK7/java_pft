package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//использовать варианты инициализации и заполнения можно разные

public class Collections {
    public static void main(String[] args ){
         /*  String[] langs = new String[4];    //Создали и заполнили массив
        langs[0] = "Java";
        langs[1] = "C#";
        langs[2] = "Python";
        langs[3] = "PHP";  */


        String[] langs = {"Java","C#","Python","PHP"};  //Другой вариант заполнения массива

        List<String> languages2 = new ArrayList<>(); //создали массив строк
        languages2.add("Java"); //заполнили строки
        languages2.add("C#");
        languages2.add("Python");

        List<String> languages = Arrays.asList("Java","C#","Python","PHP"); //аналог заполнения массива строк
        for(int i = 0; i< languages.size(); i++){      //вывести массив строк
            System.out.println("Я хочу выучить " + languages.get(i));
        }

        for(String l : languages){      //вывести массив на экран для строки
            System.out.println("Я хочу выучить " + l);
        }
        for(String l : langs){      //вывести массив на экран для строки
            System.out.println("Я хочу выучить " + l);
        }
     /*   for(int i = 0; i< langs.length; i++){      //вывести массив на экран
            System.out.println("Я хочу выучить " + langs[i]);
        }    */





    }
}
