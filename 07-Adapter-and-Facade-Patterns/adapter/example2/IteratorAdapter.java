package adapterandfacade.adapter.example2;

import java.util.Enumeration;
import java.util.Iterator;

public class IteratorAdapter implements Enumeration {
    Iterator iterator;

    public IteratorAdapter(Iterator iterator) {
        this.iterator = iterator;
    }

    public boolean hasMoreElements() {
        return iterator.hasNext();
    }

    public Object nextElement() {
        return iterator.next();
    }
}
