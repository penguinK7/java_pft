package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("Ksenia");

        Square s = new Square(8);

        System.out.println("Площадь квадрата со строной " + s.l + "=" + s.area());
Rectangle r = new Rectangle(4, 12);

        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody +"!");
    }



}