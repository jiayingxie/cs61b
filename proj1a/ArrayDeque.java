public class ArrayDeque<T> {
    private int myArrayLen; // the length of array
    private int size; // the length of deque
    private T[] items;
    private int nextFirst; // to help deal with add first and remove first
    private int nextLast; // to help deal with add last and remove last

    // Creates an empty array deque.
    public ArrayDeque() {
        myArrayLen = 8;
        items = (T[]) new Object[myArrayLen];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        // 1：copy the last part
        int startOfCopy = (capacity - 1) % (myArrayLen - 1);
        if (items[0] != null) {
            for (int i = 0; i < nextLast; ++i) {
                a[i] = items[i];
            }
        }else {
            for (int i = startOfCopy; i < nextLast; ++i) {
                a[i % capacity] = items[i];
            }
        }

        // 2: copy the first part
        if (nextLast <= nextFirst) {
            int i, j = capacity - 1;
            for (i = myArrayLen - 1; i > nextFirst; i = i - 1) {
                a[j] = items[i];
                j = j - 1;
            }
            nextFirst = j;
        } else {
            for (int i = nextFirst; i < nextLast; ++i) {
                a[i] = items[i];
            }
        }

        myArrayLen = capacity;
        items = a;
    }

    // Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        if (nextFirst == nextLast && (myArrayLen - size) <= 1) {
            resize(myArrayLen * 2);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst + myArrayLen - 1) % myArrayLen;
        size = size + 1;

    }

    // Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        if (nextFirst == nextLast && (myArrayLen - size) <= 1) {
            resize(myArrayLen * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % myArrayLen;
        size = size + 1;
    }

    // Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    // Returns the number of items in the deque.
    public int size() {
        return this.size;
    }

    // Prints the items in the deque from first to last, separated by a space.
    public void printDeque() {
        int i;
        if (nextLast <= nextFirst) {
            // 1: print the first part
            for (i = nextFirst + 1; i < myArrayLen; i = i + 1) {
                System.out.print(items[i] + " ");
            }
            // 2: print the last part
            for (i = 0; i < nextLast; i = i + 1) {
                System.out.print(items[i] + " ");
            }
        } else {
            for (i = nextFirst + 1; i < nextLast; i = i + 1) {
                System.out.print(items[i] + " ");
            }
        }

    }

    // Removes and returns the item at the front of the deque. If no such item
    // exists, returns null.
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            size = size - 1;
            if (myArrayLen >= 16) {
                if (size < 0.25 * myArrayLen) {
                    resize(myArrayLen / 2);
                }
            }
            T temp = items[(nextFirst + 1) % myArrayLen];
            items[(nextFirst + 1) % myArrayLen] = null;
            nextFirst = (nextFirst + 1) % myArrayLen;
            // ???delete
            System.out.println(temp);
            return temp;
        }
    }

    //Removes and returns the item at the back of the deque. If no such item
    // exists, returns null.
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            size = size - 1;
            if (myArrayLen >= 16) {
                if (size < 0.25 * myArrayLen) {
                    resize(myArrayLen / 2);
                }
            }
            T temp = items[(myArrayLen + nextLast - 1) % myArrayLen];
            items[(myArrayLen + nextLast - 1) % myArrayLen] = null;
            nextLast = (myArrayLen + nextLast - 1) % myArrayLen;
            // ???delete
            System.out.println(temp);
            return temp;
        }
    }

    //Gets the item at the given index, where 0 is the front, 1 is the next item,
    // and so forth. If no such item exists, returns null. Must not alter the deque!
    // get must use iteration, not recursion.
    public T get(int index) {
        if (this.size <= index) {
            return null;
        } else {
            return items[(nextFirst + 1 + index) % myArrayLen];
        }
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        System.out.println(L.isEmpty());
        L.addLast(0);
        L.addLast(1);
        L.addLast(2);
        L.addLast(3);
        L.addFirst(4);
        L.addLast(5);
        L.addLast(8);
        L.removeLast();
        L.addLast(11);
        L.addLast(12);
        L.removeLast();
        L.removeFirst();
        L.removeLast();
        L.removeFirst();
        L.removeLast();

    }
}