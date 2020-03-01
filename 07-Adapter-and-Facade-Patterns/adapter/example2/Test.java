package adapterandfacade.adapter.example2;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        vector.add(4);
        vector.add(5);
        Enumeration enumeration = vector.elements();
        Iterator enumerationAdapter = new EnumerationAdapter(enumeration);

        while (enumerationAdapter.hasNext()) {
            System.out.println(enumerationAdapter.next());
        }

        try {
            enumerationAdapter.remove();
        } catch (UnsupportedOperationException e) {
            System.out.println("UnsupportedOperationException occurred!");
        }

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        Iterator iterator = list.iterator();
        Enumeration iteratorAdapter = new IteratorAdapter(iterator);
        while (iteratorAdapter.hasMoreElements()) {
            System.out.println(iteratorAdapter.nextElement());
        }
    }
}
