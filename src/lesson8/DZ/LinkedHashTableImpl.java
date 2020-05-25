package lesson8.DZ;

public class LinkedHashTableImpl<K, V> implements LinkedHashTable<K, V> {

        static class Item<K, V>  implements Entry<K, V>{
            private final K key;
            private V value;
            private Item<K, V> next;

            public Item(K key, V value) {
                this.key = key;
                this.value = value;
                this.next = null;
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

            @Override
            public void setValue(V value) {
                this.value = value;
            }

            public void setNext(Item<K, V> next) {
                this.next = next;
            }
        }

        private final  Item<K, V>[] data;

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

            for (Item<K, V> e = data[index]; e != null; e = e.next) {   //проверяем в связном списке совпадение ключа
                if (e.key.equals(key)) {
                    e.value = value;
                    return true;
                }
            }

            if (data[index] == null) {                          //если новый элемент для данного ключа
                data[index] = new Item<>(key, value);
                size++;
                return true;
            }

            for (Item<K, V> e = data[index]; e != null; e = e.next) {       //если уже есть элементы для данного ключа
                if (e.next == null) {
                    Item<K, V> item = new Item<>(key, value);
                    e.setNext(item);
                    size++;
                    return true;
                }
            }

            return false;
        }

        @Override
        public V get(K key) {
            int index = indexOf(key);
            if (index != -1) {
                for (Item<K, V> e = data[index]; e != null; e = e.next) {
                    if (e.key.equals(key)) {
                        return e.value;
                    }
                }
            }
            return null;
        }

        @Override
        public V remove(K key) {
            int index = indexOf(key);
            if (index == -1) {
                return null;
            }


            Item<K, V> item = data[index];
            if (item.next == null) {
                data[index] = null;                          //если удаляется первый и нет списка
            }
            else if (data[index].key.equals(key)) {          //если удаляется первый и есть список
                data[index] = data[index].next;
            }
            else {
                Item<K, V> previous = data[index];
                for (Item<K, V> e = data[index].next; e != null; e = e.next) {
                    if (e.key.equals(key)) {
                        previous.setNext(e.next);
                        item = e;
                        break;
                    }
                    else {
                        previous = e;
                    }
                }
            }

            size--;
            return item.value;
        }

        private int indexOf(K key) {
            int index = hashFunc(key);
            index %= data.length;
            return index;
            }

        @Override
        public int size() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return size != 0;
        }

        @Override
        public void display() {
            System.out.println("--------------------");
            for (int i = 0; i < data.length; i++) {
                if (data[i] != null) {

                    for (Item<K, V> e = data[i]; e != null; e = e.next) {
                        System.out.printf("%d = [%s] ", i, e);
                    }
                }
                else {
                    System.out.printf("%d = [%s] ", i, data[i]);
                }
                System.out.println();
            }
            System.out.println("--------------------");
        }
}
