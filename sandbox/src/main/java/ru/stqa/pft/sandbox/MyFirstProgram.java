package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {

        Point p1 = new Point(2.0, 11);
        Point p2 = new Point(3, -3);
        System.out.println("Расстояние между точками p1 (" + p1.x + ";" + p1.y + ") и p2 (" + p2.x + ";" + p2.y + ") = " + p1.distance(p1, p2));
        System.out.println("Расстояние между точками p1 (" + p1.x + ";" + p1.y + ") и p2 (" + p2.x + ";" + p2.y + ") = " + p2.distance(p1, p2));
    }

}






