public class LinkedListDeque<T> {
    private class StuffNode {
        private T item;
        private StuffNode prev;
        private StuffNode next;

        StuffNode(StuffNode n1, T i, StuffNode n2) {
            item = i;
            prev = n1;
            next = n2;
        }

        private T getRecursiveNode(int index) {
            if (index == 0) {
                return item;
            } else {
                return next.getRecursiveNode(index - 1);
            }
        }
    }


    private StuffNode sentinel;
    private int size;

    // Creates an empty linked list deque.
    public LinkedListDeque() {
        this.size = 0;
        sentinel = new StuffNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    // Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        StuffNode temp = new StuffNode(null, item, null);
        temp.next = sentinel.next;
        sentinel.next.prev = temp;
        temp.prev = sentinel;
        sentinel.next = temp;
        size = size + 1;
    }

    // Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        StuffNode temp = new StuffNode(null, item, null);
        temp.prev = sentinel.prev;
        sentinel.prev.next = temp;
        temp.next = sentinel;
        sentinel.prev = temp;
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
        return size;
    }

    // Prints the items in the deque from first to last, separated by a space.
    public void printDeque() {
        StuffNode ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
    }

    // Removes and returns the item at the front of the deque. If no such item
    // exists, returns null.
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        } else {
            T temp = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size = size - 1;
            return temp;
        }
    }

    //Removes and returns the item at the back of the deque. If no such item
    // exists, returns null.
    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        } else {
            T temp = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size = size - 1;
            return temp;
        }
    }

    //Gets the item at the given index, where 0 is the front, 1 is the next item,
    // and so forth. If no such item exists, returns null. Must not alter the deque!
    // get must use iteration, not recursion.
    public T get(int index) {
        if (size < index - 1) {
            return null;
        } else {
            StuffNode ptr = sentinel.next;
            int i = 0;
            while (i < index) {
                ptr = ptr.next;
                i = i + 1;
            }
            return ptr.item;
        }
    }
    // Same as get, but uses recursion.
    public T getRecursive(int index) {
        if (size < index - 1) {
            return null;
        } else {
            return sentinel.next.getRecursiveNode(index);
        }
    }

//    public static void main(String[] args) {
//        //main 里面全是测试数据
//        System.out.println("OK");
//        LinkedListDeque<Double> L = new LinkedListDeque<>();
//        System.out.println(L.isEmpty());
//        L.addFirst(1.1);
//        L.addFirst(0.4);
//        L.addLast(2.2);
//        L.addLast(3.3);
//        L.addLast(4.4);
//        System.out.println(L.size);
//        L.printDeque();
////        System.out.println(L.isEmpty());
////        System.out.println(L.removeFirst());
////        System.out.println(L.removeLast());
////        System.out.println(L.removeLast());
////        System.out.println(L.removeLast());
////        System.out.println(L.removeLast());
////        System.out.println(L.removeLast());
////        L.printDeque();
//        System.out.println("-----------");
//        System.out.println(L.get(0));
//        System.out.println(L.get(3));
//        System.out.println(L.get(10));
////        L.getRecursive(0);
////        L.getRecursive(3);
//        System.out.println(L.getRecursive(0));
//        System.out.println(L.getRecursive(3));
//        System.out.println(L.getRecursive(10));
//    }
}
