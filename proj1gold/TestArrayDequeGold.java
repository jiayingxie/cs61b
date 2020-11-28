import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void myTest() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads1 = new ArrayDequeSolution<>();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (true) {
            boolean addItem = StdRandom.bernoulli();
            if (addItem) {
                boolean addFirst = StdRandom.bernoulli();
                if (addFirst) {
                    sad1.addFirst(index);
                    ads1.addFirst(index);
                    sb.append("addFirst(" + index + ")\n");
                } else {
                    sad1.addLast(index);
                    ads1.addLast(index);
                    sb.append("addLast(" + index + ")\n");
                }
                index = index + 1;
            } else {
                if (!sad1.isEmpty()) {
                    boolean removeFirst = StdRandom.bernoulli();
                    if (removeFirst) {
                        Integer sadInt = sad1.removeFirst();
                        Integer adsInt = ads1.removeFirst();
                        sb.append("removeFirst()\n");
                        assertEquals(sb.toString(), adsInt, sadInt);
                    } else {
                        Integer sadInt = sad1.removeLast();
                        Integer adsInt = ads1.removeLast();
                        sb.append("removeLast()\n");
                        assertEquals(sb.toString(), adsInt, sadInt);
                    }
                }
            }
        }
    }
}
