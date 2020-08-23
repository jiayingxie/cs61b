public class LinkedListDeque<Item> implements Deque<Item>{
    private class StuffNode {
        private Item item;
        private StuffNode prev;
        private StuffNode next;

        StuffNode(StuffNode n1, Item i, StuffNode n2) {
            item = i;
            prev = n1;
            next = n2;
        }

        private Item getRecursiveNode(int index) {
            if (index == 0) {
                return item;
            } else {
                return next.getRecursiveNode(index - 1);
            }
        }
    }

    private StuffNode sentinel;
    private int size;

    public LinkedListDeque() {
        this.size = 0;
        sentinel = new StuffNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public void addFirst(Item item) {
        StuffNode temp = new StuffNode(null, item, null);
        temp.next = sentinel.next;
        sentinel.next.prev = temp;
        temp.prev = sentinel;
        sentinel.next = temp;
        size = size + 1;
    }

    @Override
    public void addLast(Item item) {
        StuffNode temp = new StuffNode(null, item, null);
        temp.prev = sentinel.prev;
        sentinel.prev.next = temp;
        temp.next = sentinel;
        sentinel.prev = temp;
        size = size + 1;
    }

    @Override
    public boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        StuffNode ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
    }

    @Override
    public Item removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        } else {
            Item temp = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size = size - 1;
            return temp;
        }
    }

    @Override
    public Item removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        } else {
            Item temp = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size = size - 1;
            return temp;
        }
    }

    @Override
    public Item get(int index) {
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

    public Item getRecursive(int index) {
        if (size < index - 1) {
            return null;
        } else {
            return sentinel.next.getRecursiveNode(index);
        }
    }
}
