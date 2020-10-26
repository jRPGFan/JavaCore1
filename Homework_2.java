package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Homework_2 {

    public static void main(String[] args) {
        // Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
        // Написать метод, заменяющий в принятом массиве 0 на 1, 1 на 0;
        int[] array1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        System.out.println("Original array:\n" + Arrays.toString(array1));
        System.out.println("Reversed array:\n" + Arrays.toString(reverseArrayValues(array1)) + "\n");

        // Задать пустой целочисленный массив размером 8.
        // Написать метод, который c помощью цикла заполнит его значениями 1 4 7 10 13 16 19 22;
        int[] array2 = new int[8];
        System.out.println("Filled array:\n" + Arrays.toString(fillIntArray(array2)) + "\n");

        // Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ],
        // написать метод, принимающий на вход массив и умножающий числа меньше 6 на 2;
        int[] array3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("Original array:\n" + Arrays.toString(array3));
        System.out.println("Multiplied array:\n" + Arrays.toString(multiplyArrayValues(array3)) + "\n");

        // Задать одномерный массив.
        // Написать методы поиска в нём минимального и максимального элемента
        int[] array4 = new int[10];
        fillIntArrayWRandomValues(array4, 100);
        System.out.println("Filled array:\n" + Arrays.toString(array4));
        System.out.println("Max array value: " + findMaxValueInArray(array4));
        System.out.println("Min array value: " + findMinValueInArray(array4) + "\n");
        
        // Создать квадратный целочисленный массив (количество строк и столбцов одинаковое), 
        // заполнить его диагональные элементы единицами, используя цикл(ы);
        int[][] array5 = new int [10][10];
        showTwoDimensionalArrayValues(fillArrayDiagonals(array5));
        System.out.println();

        // Написать метод, в который передается не пустой одномерный целочисленный массив,
        // метод должен вернуть true если в массиве есть место, в котором сумма левой
        // и правой части массива равны. Примеры:
        // checkBalance([1, 1, 1, || 2, 1]) → true,
        // checkBalance ([2, 1, 1, 2, 1]) → false,
        // checkBalance ([10, || 1, 2, 3, 4]) → true.
        // Абстрактная граница показана символами ||, эти символы в массив не входят.
        int[] array6 = new int[9];
        fillIntArrayWRandomValues(array6, 5);
        checkBalance(array6);

        // Написать метод, которому на вход подаётся одномерный массив и число n
        // (может быть положительным, или отрицательным), при этом метод должен
        // циклически сместить все элементы массива на n позиций.
        // [1,2,3,4,5], -2 => [3,4,5,1,2]
        // [1,2,3,4,5], 2 => [4,5,1,2,3]
        // Не пользоваться вспомогательным массивом
        int[] array7 = new int[5];
        fillIntArrayWRandomValues(array7, 10);
        System.out.println("Original array:\n" + Arrays.toString(array7));
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter shift range: ");
        int step = scan.nextInt();
        moveArrayItems(array7, step);

        // Нужен метод, который получает в параметры 2 массива (разной длины) int-чисел.
        // Он (метод) должен вернуть массив значений, которые есть в 1 массиве, но их нет во втором
        int[] array8 = new int[5];
        fillIntArrayWRandomValues(array8, 10);
        //int[] array8 = {12, 12, 13, 13, 5};
        System.out.println("First array:\n" + Arrays.toString(array8));
        int[] array9 = new int[10];
        fillIntArrayWRandomValues(array9, 10);
        System.out.println("Second array:\n" + Arrays.toString(array9));
        compareArrayValues(array8, array9);
    }

    public static int[] reverseArrayValues(int[] array){
        for (int i = 0; i< array.length; i++){
            if(array[i] == 0) array[i] = 1;
            else array[i] = 0;
        }
        return array;
    }

    public static int[] fillIntArray(int[] array){
        int value = 1;
        for (int i = 0; i < array.length; i++) {
            array[i] = value;
            value += 3;
        }
        return array;
    }

    public static int[] multiplyArrayValues(int[] array){
        for (int i = 0; i < array.length; i++) {
            if(array[i] < 6) array[i] *= 2;
        }
        return array;
    }

    public static void fillIntArrayWRandomValues(int[] array, int range){
        for (int i = 0; i < array.length; i++) array[i] = (int) (Math.random() * range);
    }

    public static int findMaxValueInArray(int[] array){
        int maxValue = array[0];
        for(int i : array)
            if(i > maxValue) maxValue = i;
        return maxValue;
    }

    public static int findMinValueInArray(int[] array){
        int minValue = array[0];
        for(int i : array)
            if(i < minValue) minValue = i;
        return minValue;
    }

    public static void showTwoDimensionalArrayValues(int[][] array){
        for (int[] intI : array) {
            for (int intJ : intI) {
                System.out.print(intJ + " ");
            }
            System.out.println();
        }
    }
    
    public static int[][] fillArrayDiagonals(int[][] array){
        System.out.println("Filled array:");
        for (int i = 0; i < array.length; i++) {
            array[i][i] = 1;
            array[i][array[i].length - 1 - i] = 1;
        }
        return array;
    }

    public static void checkBalance(int[] array){
        int middle = (array.length - 1) / 2, westSide = 0, eastSide = 0;
        for (int i = 0; i <= middle; i++) {
            westSide += array[i];
            if (array[i] != array[array.length - 1 - i]) eastSide += array[array.length - 1 - i];
        }
        System.out.println(Arrays.toString(array));
        System.out.println("West Side = " + westSide + "\tEast Side = " + eastSide);
        if(westSide > eastSide) System.out.println("West Coast best coast.");
        else if(westSide < eastSide) System.out.println("East Coast beast coast.");
        else System.out.println("Huh, it's a draw. What are the chances?");
        System.out.println(westSide == eastSide);
        System.out.println();
    }

    public static void moveArrayItems(int[] array, int step){
        if (step % array.length == 0 || array.length % step == 0){
            System.out.println("Bibbidi Bobbidi Boo, the array hasn't shifted so here's a return for you.\n");
            return;
        }
        if (step > array.length) step = step % array.length;
        else if (step < -array.length) step = step % -array.length;
        if (step > 0) System.out.println("Shifting array by " + step + " to the right...");
        else System.out.println("Shifting array by " + Math.abs(step) + " to the left...");

        int temp;
        if (step > 0){
            for (int i = 0; i < step; i++) {
                temp = array[array.length - 1];
                for (int j = array.length - 1; j > 0; j--) {
                    array[j] = array[j - 1];
                }
                array[0] = temp;
            }
        }

        else{
            for (int i = 0; i > step; i--) {
                temp = array[0];
                for (int j = 0; j < array.length - 1; j++) {
                    array[j] = array[j + 1];
                }
                array[array.length - 1] = temp;
            }
        }

        System.out.println("Shifted array:\n" + Arrays.toString(array));
        System.out.println();
    }

    public static void compareArrayValues(int[] array1, int[] array2) {
        ArrayList<Integer> exclusiveValues = new ArrayList<Integer>(0);
        int currentValue;
        boolean match = false;
        for (int i = 0; i < array1.length; i++) {
            currentValue = array1[i];

            for (int k : array2) {
                if (currentValue == k) {
                    match = true;
                    break;
                }
            }

            if (!match) {
                if (exclusiveValues.size() == 0 || !exclusiveValues.contains(currentValue)) {
                    exclusiveValues.ensureCapacity(exclusiveValues.size() + 1);
                    exclusiveValues.add(currentValue);
                }
            }
            match = false;
        }

        if (exclusiveValues.size() > 0) System.out.println("Exclusive values:\n" + exclusiveValues);
        else System.out.println("There were no exclusive values");
    }
}
