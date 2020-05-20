package lesson2;

import java.util.Arrays;
import java.util.Random;


public class ArrayTest {
    public static void main(String[] args) {
//        testArray();
//        testDinamicArray();
        testSort();
    }

    protected static void testSort() {
        final int SIZE = 20000;
        Array<Integer> data1 = new ArrayImpl<>(SIZE);
        Array<Integer> data2 = new ArrayImpl<>(SIZE);
        Array<Integer> data3 = new ArrayImpl<>(SIZE);
        Array<Integer> data4 = new ArrayImpl<>(SIZE);

        Array<Integer> dataSort = new SortedArrayImpl<>();

        for (int i = 0; i < SIZE; i++) {
            int rnd = new Random().nextInt();
            data1.add(rnd);
        }

        data1.copyArray(data2);
        data1.copyArray(data3);
        data1.copyArray(data4);
        data1.copyArray(dataSort);

//        data1.display();
//        data2.display();
//        data3.display();
//        dataSort.display();

        System.out.println("Test unsorted array:");
        timeSort(data1,"Bubble");
        timeSort(data2,"BubbleMode");
        timeSort(data3,"Select");
        timeSort(data4,"Insert");

        System.out.println();
        System.out.println("Test sorted array:");
        timeSort(dataSort,"Bubble");
        timeSort(dataSort,"BubbleMode");
        timeSort(dataSort,"Select");
        timeSort(dataSort,"Insert");
    }

//Метод подсчета и вывода времени выполнения сортировки.
    private static void timeSort(Array<Integer> data, String typeSort) {
        long start = System.nanoTime();

        switch (typeSort) {
            case "Bubble" : data.sortBubble(false); break;
            case "BubbleMode" : data.sortBubble(true); break;
            case "Select" : data.sortSelect(); break;
            case "Insert" : data.sortInsert(); break;
        }

        long end = System.nanoTime();
        long elapsedTime = end - start;
        double elapsedTimeInSecond = (double) elapsedTime / 1000000000;
        System.out.println(typeSort + " sorting time "+ data.size() + " items: " + elapsedTimeInSecond + " seconds");
    }

    private static void testDinamicArray() {
        //Array<Integer> data = new ArrayImpl<>();
        Array<Integer> data = new SortedArrayImpl<>();

        data.add(5);
        data.add(4);
        data.add(3);
        data.add(2);
        data.add(1);

        data.display();
        data.remove(Integer.valueOf(3));
        //data.remove(2);
        data.display();

        System.out.println("data[1] = " + data.get(1));
    }

    private static void testArray() {
        Integer[] data = {1, 2 ,3, 4, 5};

        data = Arrays.copyOf(data, data.length * 2);

        for (Integer value : data) {
            System.out.println(value);
        }

    }
}
