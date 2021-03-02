package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("world");
        hello("Ksenia");

        Square s = new Square(8);

        System.out.println("Площадь квадрата со строной " + s.l + "=" + area(s));
Rectangle r = new Rectangle(4, 12);

        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + area(r));
    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody +"!");
    }

    public static double area(Square s){
        return s.l * s.l;

    }
    public static double area(Rectangle r){
        return r.a * r.b;
    }
}