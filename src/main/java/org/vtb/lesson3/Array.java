package org.vtb.lesson3;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Array<E> implements Iterable<E> {
    private E[] a;
    private int size;
    private int nElements = 0;

    public Array() {
        this.size = 10;
        this.a = (E[]) new Object[this.size];
    }

    public Array(int size) {
        this.size = size;
        this.a = (E[]) new Object[this.size];
    }

    public void add(E value) throws ArrayIndexOutOfBoundsException {
        a[nElements] = value;
        nElements++;
    }

    public void setElement(int index, E value) throws IndexOutOfBoundsException {
        a[index] = value;
    }

    public E getElement(int index) throws IndexOutOfBoundsException {
        return a[index];
    }

    public void remove(int index) throws IndexOutOfBoundsException {
        for (int i = index; i < a.length - 1; i++)
            a[i] = a[i + 1];
        nElements--;
    }

    public void removeAll() {
        Arrays.fill(a, null);
        nElements = 0;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size && a[currentIndex] != null;
            }

            @Override
            public E next() {
                return a[currentIndex++];
            }
        };
        return it;
    }

    @Override
    public void forEach(Consumer<? super E> action) {

    }

    @Override
    public Spliterator<E> spliterator() {
        return null;
    }
}
