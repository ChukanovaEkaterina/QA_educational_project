package ru.katerinaluzhnykh25;


public class Less7 {
    public static void main(String[] args) {

        //0) применить несколько арифметических операций ( + , -, * , /) над двумя примитивами типа int
        int a = 165;
        int b = 150;
        int s  = a + b;

        System.out.println(" a + b = " + s);
        System.out.println(a - b);
        System.out.println(a * b);
        System.out.println(a / b);

        //1) применить несколько арифметических операций над int и double в одном выражении
        int c = 5;
        double d = 2.5;

        System.out.println(c + d);
        System.out.println(c - d);
        System.out.println(c * d);
        System.out.println(c / d);



        //2) применить несколько логических операций ( < , >, >=, <= )

        if (a > b) {
            System.out.println(" a больше b ");
        } else System.out.println(" b больше a ");

        int weight = 55;
        double height = 1.65;
        double bodyMassIndex = weight / (height*height);

        if (bodyMassIndex < 18.5) {
            System.out.println("Недостаток веса");
        } else if (bodyMassIndex < 25) {
            System.out.println("Нормальный вес");
        } else if (bodyMassIndex < 30) {
            System.out.println("Избыточный вес");
        } else {
            System.out.println("Ожирение");
        };

        //4) получить переполнение при арифметической операции

        int e = 1000000;
        int f = 20000;
        System.out.println(e * f);
    }
}