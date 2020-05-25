package lesson8.DZ;

import lesson8.Product;

public class MainDZ {

    public static void main(String[] args) {

        LinkedHashTable<Product, Integer> hashTable = new LinkedHashTableImpl<>(10);

        hashTable.put(new Product(1, "Orange"), 150);
        hashTable.put(new Product(77, "Banana"), 100);
        hashTable.put(new Product(77, "Banana"), 102);      //изменилась стоимость
        hashTable.put(new Product(77, "Carrot"), 228);      //тот же ключ - создаем список связный
        hashTable.put(new Product(60, "Lemon"), 250);
        hashTable.put(new Product(52, "Milk"), 120);
        hashTable.put(new Product(21, "Potato"), 67);

        System.out.println("Size is " + hashTable.size());
        hashTable.display();

        System.out.println("Cost potato is " + hashTable.get(new Product(21, "Potato")));
        System.out.println("Cost banana is " + hashTable.get(new Product(77, "Banana")));
        System.out.println("Cost carrot is " + hashTable.get(new Product(77, "Carrot")));

        hashTable.remove(new Product(52, "Milk"));
//        hashTable.remove(new Product(77,"Carrot"));
        hashTable.remove(new Product(77,"Banana"));

        hashTable.display();
        System.out.println("Size is " + hashTable.size());
    }
}
