package lesson5;

import java.util.ArrayList;
import java.util.Arrays;

public class Backpack {

    private static int BACKPACKMAXWEIGHT = 10;
    private static int COLLITEMS = 5;
    private static int maxP = 0;

    public static void main(String[] args) {

        int sumW = 0;
        int sumP = 0;

        Item item1 = new Item(3,5);
        Item item2 = new Item(1,6);
        Item item3 = new Item(4,5);
        Item item4 = new Item(2,3);
        Item item5 = new Item(5,10);

        ArrayList<Item> arrayItems = new ArrayList<>(COLLITEMS);
        ArrayList<Item> arrayCopy = new ArrayList<>(COLLITEMS);

        arrayItems.add(item1);
        arrayItems.add(item2);
        arrayItems.add(item3);
        arrayItems.add(item4);
        arrayItems.add(item5);

        sumW = sumWeight(arrayItems);
        sumP = sumPrice(arrayItems);

        System.out.println("Max weight backpack: " + BACKPACKMAXWEIGHT);

        System.out.println("Weight -- Price");
        for (int i = 0; i < arrayItems.size(); i++) {
            System.out.println(arrayItems.get(i).weight + " -- " + arrayItems.get(i).price);
        }

        System.out.println("Weight all items: " + sumW);
        System.out.println("Price all items: " + sumP);

        pereb(arrayItems, arrayCopy, 0, 0, sumP);

        System.out.println("Max price: " + maxP);

        System.out.println("Weight -- Price");

        for (int i = 0; i < arrayCopy.size(); i++) {
            System.out.println(arrayCopy.get(i).weight + " -- " + arrayCopy.get(i).price);
        }
    }

    private static void pereb(ArrayList<Item> arrayItems, ArrayList<Item> arrayCopy, int i, int arrayWeight, int arrayPrice) {

        if (sumWeight(arrayItems) <= BACKPACKMAXWEIGHT) {
            maxP = sumPrice(arrayItems);
            arrayCopy.addAll(arrayItems);
            return;
        }

        if (arrayWeight + arrayItems.get(i).weight <= BACKPACKMAXWEIGHT) {
            if (i < COLLITEMS - 1) {
                pereb(arrayItems, arrayCopy,i + 1 , arrayWeight + arrayItems.get(i).weight, arrayPrice);
            }

        else {
                if (arrayPrice > maxP) {
                    maxP = arrayPrice;

                    arrayCopy.clear();
                    for (int j = 0; j < arrayItems.size() - 1; j++) {
                        arrayCopy.add(arrayItems.get(j + 1));
                    }
                }
            }
        }

        if (arrayPrice > maxP + arrayItems.get(i).price) {
            if (i < COLLITEMS - 1) {
                pereb(arrayItems, arrayCopy,i + 1, arrayWeight, arrayPrice - arrayItems.get(i).price);
            } else {
                maxP = arrayPrice - arrayItems.get(i).price;

//                arrayCopy.clear();
//                for (int j = 0; j < arrayItems.size() - 1; j++) {
//                    arrayCopy.add(arrayItems.get(j + 1));
//                }
            }
        }
    }

    private static int sumWeight(ArrayList<Item> arrayItems) {
        int sumWeight = 0;

        for (int i = 0; i < arrayItems.size(); i++) {
            sumWeight += arrayItems.get(i).weight;
        }
        return sumWeight;
    }

    private static int sumPrice(ArrayList<Item> arrayItems) {
        int sumPrice = 0;

        for (int i = 0; i < arrayItems.size(); i++) {
            sumPrice += arrayItems.get(i).price;
        }
        return sumPrice;
    }
}
