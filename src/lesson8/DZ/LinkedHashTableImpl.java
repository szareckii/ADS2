package lesson8.DZ;

public class LinkedHashTableImpl<K, V> implements LinkedHashTable<K, V> {

        static class Item<K, V>  implements Entry<K, V>{
            private final K key;
            private V value;
            private Item<K, V> next;

            public Item(K key, V value) {
                this.key = key;
                this.value = value;
            }

            public Item(K key, V value, Item<K, V> current) {
                this.key = key;
                this.value = value;
                this.next = current;
            }

            @Override
            public String toString() {
                return "Item{" +
                        "key=" + key +
                        ", value=" + value +
                        '}';
            }

            @Override
            public K getKey() {
                return key;
            }

            @Override
            public V getValue() {
                return value;
            }


//            public Item getNext() {
//                return Item;
//            }

            @Override
            public void setValue(V value) {
                this.value = value;
            }


            public void setNext(Item<K, V> current) {
                this.next = current;
            }
        }

        public Item<K, V>[] data;
        public int size;

        @SuppressWarnings("unchecked")
        public LinkedHashTableImpl(int maxSize) {
            this.data = new Item[maxSize];
        }

        protected int hashFunc(K key) {
            return key.hashCode() % data.length;
        }

        @Override
        public boolean put(K key, V value) {
            int index = hashFunc(key);

            for (Item<K, V> e = data[index]; e != null; e = e.next) {
                if (data[index].key.equals(key)) {
                    data[index].value = value;
                    return true;
                }
            }

            Item<K, V> current = null;
            Item<K, V> currentItem = data[index];
            
//            for (Item<K, V> e = data[index]; e != null; e = e.next) {
//                currentItem = data[index];
//            }

                while (data[index] != null) {
                    current = data[index];
                    data[index] = data[index].next;
                }

                data[index] = currentItem;

                if (current == null) {
                    data[index] = new Item<>(key, value);
                }
                else {
                    Item<K, V> item = new Item<>(key, value);
                    current.setNext(item);
                    data[index] = currentItem;
                }

                size++;
                return true;
        }

        @Override
        public V get(K key) {
            int index = indexOf(key);
            return index != -1 ? data[index].value : null;
        }

        @Override
        public V remove(K key) {
            int index = indexOf(key);
            if (index == -1) {
                return null;
            }

            Item<K, V> item = data[index];
            data[index] = null;
            size--;
            return item.value;
        }

        private int indexOf(K key) {
            int index = hashFunc(key);
            int count = 0;
            while (data[index] != null && count < data.length) {
                Item<K, V> item = data[index];
                Item<K, V> item1 = data[index].next;


                for (Item<K, V> e = data[index]; e != null; e = e.next) {
                    if (item.key.equals(key)) {
                        return index;
                    }
                }
//
//                if (item.key.equals(key)) {
//                    return index;
//                }

                count++;
                index += getStep(key);
                index %= data.length;
            }

            return -1;
        }

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return size != 0;
        }

        protected int getStep(K key) {
            return 1;
        }

        @Override
        public void display() {
            System.out.println("--------------------");
            for (int i = 0; i < data.length; i++) {
                if (data[i] != null) {
                    while (data[i].next != null) {
                        System.out.printf("%d = [%s] ", i, data[i]);
                        data[i] = data[i].next;
                    }
                 //   System.out.printf("%d = [%s] ", i, data[i]);
                }
                System.out.printf("%d = [%s] ", i, data[i]);
                System.out.println();
            }
            System.out.println("--------------------");
        }
}
