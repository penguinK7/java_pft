package ru.stqa.pft.sandbox;
// сравнение ссылок и объектов
public class Equality {
    public static void main(String[] args){
        String s1 = "firefox";
        String s2 = s1;

        String s3 = "chrome";
        String s4 = new String(s3);


        System.out.println(s1 == s2);  //сравниваются ссылки на один объект
        System.out.println(s1.equals(s2)); //сравнивается содержимое объектов

        System.out.println(s3 == s4); //сравниваются ссылки на разные объекты
        System.out.println(s1.equals(s2)); //сравнивается содержимое объектов
    }
}
