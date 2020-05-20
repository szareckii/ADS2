package lesson5;

import lesson2.SortedArrayImpl;

public class RecSortedArrayImpl<E extends Object & Comparable<? super E>> extends SortedArrayImpl<E> {

    @Override
    public int indexOf(E value) {
        return recBinariFind(value, 0, size - 1);
    }

    private int recBinariFind(E value, int low, int hight) {
        if(low > hight) {
            return -1;
        }

        int mid = (low + hight) / 2;
        if (value.equals(data[mid])) {
            return mid;
        }

        if (value.compareTo(data[mid]) > 0) {
            return recBinariFind(value, mid + 1, hight);
        }
        else {
            return recBinariFind(value, low, mid - 1);
        }
    }
}
