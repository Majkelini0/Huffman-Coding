package Final;

import java.util.LinkedList;

public class MyPriorityQueue<T extends Comparable<T>> {
    private LinkedList<T> items;

    public MyPriorityQueue() {
        items = new LinkedList<>();
    }

    public void add(T item) {
        if (items.isEmpty()) {
            items.add(item);
        } else {
            int i = 0;
            while (i < items.size() && item.compareTo(items.get(i)) < 0) { // item to be inserted is less than the item at position i in the queue
                i++;
            }
            items.add(i, item);
        }
    }

    public T poll() {
        return items.pollLast(); // zwraca ostatni element
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getSize() {
        return items.size();
    }

    public LinkedList<T> getItems() {
        return items;
    }
}
