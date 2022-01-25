package cz.cvut.k36.omo.hw.events;

/**
 * Interface for Iterator.
 */
public interface Iterator<T> {

    public T next();
    public boolean hasNext();
}
