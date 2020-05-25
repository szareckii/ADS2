package lesson8.DZ;

import lesson8.DoubleHashTableImpl;
import lesson8.HashTable;
import lesson8.Product;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainDZ {

    public static void main(String[] args) {
//        HashTable<Product, Integer> hashTable = new HashTableImpl<>(5);
//        HashTable<Product, Integer> hashTable = new DoubleHashTableImpl<>(5);


        LinkedHashTable<Product, Integer> hashTable = new LinkedHashTableImpl<>(10);

//        LinkedHashTable<Product, LinkedList<String>> hashTable = new LinkedHashTableImpl<>(10);



//        hashTable.put(new Product(1, "Orange"), 150);
        hashTable.put(new Product(77, "Banana"), 100);
        hashTable.put(new Product(77, "Orange"), 101);
//        hashTable.put(new Product(77, "Banana"), 102);
        hashTable.put(new Product(77, "Carrot"), 228);
//        hashTable.put(new Product(60, "Lemon"), 250);
//        hashTable.put(new Product(52, "Milk"), 120);
//        hashTable.put(new Product(21, "Potato"), 67);

        System.out.println("Size is " + hashTable.size());
        hashTable.display();

//        System.out.println("Cost potato is " + hashTable.get(new Product(21, "Potato")));
        System.out.println("Cost banana is " + hashTable.get(new Product(77, "Orange")));
//
//        hashTable.remove(new Product(21,"Potato"));
//        hashTable.remove(new Product(77,"Banana"));
//
//        System.out.println("Cost potato is " + hashTable.get(new Product(21, "Potato")));
//        System.out.println("Cost banana is " + hashTable.get(new Product(77, "Banana")));
//
//        hashTable.display();
    }
}
