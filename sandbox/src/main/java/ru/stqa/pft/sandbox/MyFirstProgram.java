package ru.stqa.pft.sandbox;

public class MyFirstProgram {
    public static void main (String[] args){

        Point p1 = new Point(0, 0);
        Point p2 = new Point(2, 1);
        System.out.println("Расстояние между точками p1 (" + p1.x + ";" + p1.y + ") и p2 (" + p2.x + ";" + p2.y + ")= " + p2.distance(p1));



        Square s = new Square(8);


        System.out.println("Площадь квадрата со строной " + s.l + "=" + s.area());
        Rectangle r = new Rectangle(4, 12);


        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    }




}






