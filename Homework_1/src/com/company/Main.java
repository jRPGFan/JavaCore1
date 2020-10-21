package com.company;

import java.util.Scanner;

public class Main {
    //Создать пустой проект в IntelliJ IDEA и прописать метод main().
    public static void main(String[] args) {
        //Создать переменные всех пройденных типов данных и инициализировать их значения.
        int i = 1;
        long l = 12L;

        float f = 2.35f;
        double dbl = 12.34d;

        char ch = 'c';
        String s = "string";

        boolean t = true;

        Scanner userInput = new Scanner(System.in);
        System.out.println("Какое из заданий желаете проверить?\n" +
                "0. Все\n" +
                "1. Операция a * (b + (c / d))\n" +
                "2. Сумма двух чисел в пределах 10..20\n" +
                "3. Число в или вне пределов 100...999\n" +
                "4. Високосный год\n");
        boolean checkAll = false;

        switch (userInput.nextInt())
        {
            case 0:
                checkAll = true;

            case 1:
                // Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,
                // где a, b, c, d – входные параметры этого метода.
                System.out.println("Операция a * (b + (c / d)). \nВведите a:");
                int a = userInput.nextInt();

                System.out.println("Введите b:");
                int b = userInput.nextInt();

                System.out.println("Введите c:");
                float c = userInput.nextFloat();

                System.out.println("Введите d:");
                float d = userInput.nextFloat();

                System.out.println("Результат операции a * (b + (c / d)): " + calculateStuff(a, b, c, d));

                if (!checkAll)
                      break;

            case 2:
                // Написать метод, принимающий на вход два числа и проверяющий, что их сумма лежит в пределах
                // от 10 до 20 (включительно), если да – вернуть true, в противном случае – false.
                System.out.println("\nСумма двух чисел в пределах 10..20.\nВведите первое число:");
                float e = userInput.nextFloat();

                System.out.println("Введите второе число:");
                float g = userInput.nextFloat();

                System.out.println("Сумма = " + (e + g) + " => " + checkSum(e, g));

                if (!checkAll)
                    break;

            case 3:
                // Создать метод, который принимает число. Если данное число больше 100 и меньше 999 включительно -
                // вывести в консоль цифры данного числа в обратном порядке.
                // Например, введено число 725 -> в консоле будет: 527.
                System.out.println("\nВведите число +-1000:");
                checkNumber(userInput.nextInt());

                if (!checkAll)
                    break;

            case 4:
                // Написать метод, который определяет является ли год високосным, и выводит сообщение в консоль.
                // Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
                System.out.println("\nВведите год Нашей Эры:");
                checkLeapYear(userInput.nextInt());

                if (!checkAll)
                    break;

            default:
                System.out.println("\nЧтоб переменным и java не было обидно:\n" + i + ", " + l + ", " +
                        f + ", " + dbl + ", " + ch + ", " + s + ", " + t);
        }
    }

    // Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,
    // где a, b, c, d – входные параметры этого метода.
    public static float calculateStuff(int a, int b, float c, float d)
    {
        return a * (b + (c / d));
    }

    // Написать метод, принимающий на вход два числа и проверяющий, что их сумма лежит в пределах
    // от 10 до 20 (включительно), если да – вернуть true, в противном случае – false.
    public static boolean checkSum(float a, float b)
    {
        return a + b >= 10 && a + b <= 20;
    }

    // Создать метод, который принимает число. Если данное число больше 100 и меньше 999 включительно -
    // вывести в консоль цифры данного числа в обратном порядке.
    // Например, введено число 725 -> в консоле будет: 527.
    public static void checkNumber(int a)
    {
        if (a >= 100 && a <= 999)
        {
            StringBuffer strBuff = new StringBuffer(Integer.toString(a));
            System.out.println(strBuff.reverse());
        }

        else
            System.out.println("¯\\_(ツ)_/¯");
    }

    // Написать метод, который определяет является ли год високосным, и выводит сообщение в консоль.
    // Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
    public static void checkLeapYear(int y)
    {
        if ((y % 4 == 0 && y % 100 != 0) ||  y % 400 == 0)
            System.out.println(y + "й год - високосный");

        else
            System.out.println(y + "й год - не високосный");
    }
}
