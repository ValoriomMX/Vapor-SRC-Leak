package dev.astro.net.utils;

import java.lang.reflect.*;
import java.io.*;
import java.util.*;

public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, Serializable
{
    private transient Entry<E> header;
    private transient int size;
    private static long serialVersionUID;
    
    static {
        LinkedList.serialVersionUID = 876323262645176354L;
    }
    
    public LinkedList() {
        this.header = new Entry<E>(null, null, null);
        this.size = 0;
        final Entry<E> header = this.header;
        final Entry<E> header2 = this.header;
        final Entry<E> header3 = this.header;
        header2.previous = header3;
        header.next = header3;
    }
    
    public LinkedList(final Collection<? extends E> c) {
        this();
        this.addAll(c);
    }
    
    @Override
    public E getFirst() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        return this.header.next.element;
    }
    
    @Override
    public E getLast() {
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        return this.header.previous.element;
    }
    
    @Override
    public E removeFirst() {
        return this.remove(this.header.next);
    }
    
    @Override
    public E removeLast() {
        return this.remove(this.header.previous);
    }
    
    @Override
    public void addFirst(final E e) {
        this.addBefore(e, this.header.next);
    }
    
    @Override
    public void addLast(final E e) {
        this.addBefore(e, this.header);
    }
    
    @Override
    public boolean contains(final Object o) {
        return this.indexOf(o) != -1;
    }
    
    @Override
    public int size() {
        return this.size;
    }
    
    @Override
    public boolean add(final E e) {
        this.addBefore(e, this.header);
        return true;
    }
    
    @Override
    public boolean remove(final Object o) {
        if (o == null) {
            for (Entry<E> e = this.header.next; e != this.header; e = e.next) {
                if (e.element == null) {
                    this.remove(e);
                    return true;
                }
            }
        }
        else {
            for (Entry<E> e = this.header.next; e != this.header; e = e.next) {
                if (o.equals(e.element)) {
                    this.remove(e);
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public boolean addAll(final Collection<? extends E> c) {
        return this.addAll(this.size, c);
    }
    
    @Override
    public boolean addAll(final int index, final Collection<? extends E> c) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
        final Object[] a = c.toArray();
        final int numNew = a.length;
        if (numNew == 0) {
            return false;
        }
        ++this.modCount;
        final Entry<E> successor = (index == this.size) ? this.header : this.entry(index);
        Entry<E> predecessor = successor.previous;
        for (int i = 0; i < numNew; ++i) {
            final Entry<E> e = new Entry<E>((E)a[i], successor, predecessor);
            predecessor.next = e;
            predecessor = e;
        }
        successor.previous = predecessor;
        this.size += numNew;
        return true;
    }
    
    @Override
    public void clear() {
        ++this.modCount;
        this.header = new Entry<E>(null, null, null);
        final Entry<E> header = this.header;
        final Entry<E> header2 = this.header;
        final Entry<E> header3 = this.header;
        header2.previous = header3;
        header.next = header3;
        this.size = 0;
    }
    
    @Override
    public E get(final int index) {
        return this.entry(index).element;
    }
    
    @Override
    public E set(final int index, final E element) {
        final Entry<E> e = this.entry(index);
        final E oldVal = e.element;
        e.element = element;
        return oldVal;
    }
    
    @Override
    public void add(final int index, final E element) {
        this.addBefore(element, (index == this.size) ? this.header : this.entry(index));
    }
    
    @Override
    public E remove(final int index) {
        return this.remove(this.entry(index));
    }
    
    private Entry<E> entry(final int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
        Entry<E> e = this.header;
        if (index < this.size >> 1) {
            for (int i = 0; i <= index; ++i) {
                e = e.next;
            }
        }
        else {
            for (int i = this.size; i > index; --i) {
                e = e.previous;
            }
        }
        return e;
    }
    
    @Override
    public int indexOf(final Object o) {
        int index = 0;
        if (o == null) {
            for (Entry e = this.header.next; e != this.header; e = e.next) {
                if (e.element == null) {
                    return index;
                }
                ++index;
            }
        }
        else {
            for (Entry e = this.header.next; e != this.header; e = e.next) {
                if (o.equals(e.element)) {
                    return index;
                }
                ++index;
            }
        }
        return -1;
    }
    
    @Override
    public int lastIndexOf(final Object o) {
        int index = this.size;
        if (o == null) {
            for (Entry e = this.header.previous; e != this.header; e = e.previous) {
                --index;
                if (e.element == null) {
                    return index;
                }
            }
        }
        else {
            for (Entry e = this.header.previous; e != this.header; e = e.previous) {
                --index;
                if (o.equals(e.element)) {
                    return index;
                }
            }
        }
        return -1;
    }
    
    @Override
    public E peek() {
        if (this.size == 0) {
            return null;
        }
        return this.getFirst();
    }
    
    @Override
    public E element() {
        return this.getFirst();
    }
    
    @Override
    public E poll() {
        if (this.size == 0) {
            return null;
        }
        return this.removeFirst();
    }
    
    @Override
    public E remove() {
        return this.removeFirst();
    }
    
    @Override
    public boolean offer(final E e) {
        return this.add(e);
    }
    
    @Override
    public boolean offerFirst(final E e) {
        this.addFirst(e);
        return true;
    }
    
    @Override
    public boolean offerLast(final E e) {
        this.addLast(e);
        return true;
    }
    
    @Override
    public E peekFirst() {
        if (this.size == 0) {
            return null;
        }
        return this.getFirst();
    }
    
    @Override
    public E peekLast() {
        if (this.size == 0) {
            return null;
        }
        return this.getLast();
    }
    
    @Override
    public E pollFirst() {
        if (this.size == 0) {
            return null;
        }
        return this.removeFirst();
    }
    
    @Override
    public E pollLast() {
        if (this.size == 0) {
            return null;
        }
        return this.removeLast();
    }
    
    @Override
    public void push(final E e) {
        this.addFirst(e);
    }
    
    @Override
    public E pop() {
        return this.removeFirst();
    }
    
    @Override
    public boolean removeFirstOccurrence(final Object o) {
        return this.remove(o);
    }
    
    @Override
    public boolean removeLastOccurrence(final Object o) {
        if (o == null) {
            for (Entry<E> e = this.header.previous; e != this.header; e = e.previous) {
                if (e.element == null) {
                    this.remove(e);
                    return true;
                }
            }
        }
        else {
            for (Entry<E> e = this.header.previous; e != this.header; e = e.previous) {
                if (o.equals(e.element)) {
                    this.remove(e);
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public ListIterator<E> listIterator(final int index) {
        return new ListItr(index);
    }
    
    private Entry<E> addBefore(final E e, final Entry<E> entry) {
        final Entry<E> newEntry = new Entry<E>(e, entry, entry.previous);
        newEntry.previous.next = newEntry;
        newEntry.next.previous = newEntry;
        ++this.size;
        ++this.modCount;
        return newEntry;
    }
    
    private E remove(final Entry<E> e) {
        if (e == this.header) {
            throw new NoSuchElementException();
        }
        final E result = e.element;
        e.previous.next = e.next;
        e.next.previous = e.previous;
        final Entry<E> entry = null;
        e.previous = entry;
        e.next = entry;
        e.element = null;
        --this.size;
        ++this.modCount;
        return result;
    }
    
    @Override
    public Iterator<E> descendingIterator() {
        return (Iterator<E>)new DescendingIterator((DescendingIterator)null);
    }
    
    public Object clone() {
        LinkedList<E> clone = null;
        try {
            clone = (LinkedList)super.clone();
        }
        catch (CloneNotSupportedException e2) {
            throw new InternalError();
        }
        clone.header = new Entry<E>(null, null, null);
        final Entry<E> header = clone.header;
        final Entry<E> header2 = clone.header;
        final Entry<E> header3 = clone.header;
        header2.previous = header3;
        header.next = header3;
        clone.size = 0;
        clone.modCount = 0;
        for (Entry<E> e = this.header.next; e != this.header; e = e.next) {
            clone.add(e.element);
        }
        return clone;
    }
    
    @Override
    public Object[] toArray() {
        final Object[] result = new Object[this.size];
        int i = 0;
        for (Entry<E> e = this.header.next; e != this.header; e = e.next) {
            result[i++] = e.element;
        }
        return result;
    }
    
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < this.size) {
            a = (Object[])Array.newInstance(a.getClass().getComponentType(), this.size);
        }
        int i = 0;
        final Object[] result = a;
        for (Entry<E> e = this.header.next; e != this.header; e = e.next) {
            result[i++] = e.element;
        }
        if (a.length > this.size) {
            a[this.size] = null;
        }
        return (T[])a;
    }
    
    private void writeObject(final ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeInt(this.size);
        for (Entry e = this.header.next; e != this.header; e = e.next) {
            s.writeObject(e.element);
        }
    }
    
    private void readObject(final ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        final int size = s.readInt();
        this.header = new Entry<E>(null, null, null);
        final Entry<E> header = this.header;
        final Entry<E> header2 = this.header;
        final Entry<E> header3 = this.header;
        header2.previous = header3;
        header.next = header3;
        for (int i = 0; i < size; ++i) {
            this.addBefore(s.readObject(), (Entry<Object>)this.header);
        }
    }
    
    private class ListItr implements ListIterator<E>
    {
        private Entry<E> lastReturned;
        private Entry<E> next;
        private int nextIndex;
        private int expectedModCount;
        
        ListItr(final int index) {
            this.lastReturned = LinkedList.this.header;
            this.expectedModCount = LinkedList.this.modCount;
            if (index < 0 || index > LinkedList.this.size) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + LinkedList.this.size);
            }
            if (index < LinkedList.this.size >> 1) {
                this.next = LinkedList.this.header.next;
                this.nextIndex = 0;
                while (this.nextIndex < index) {
                    this.next = this.next.next;
                    ++this.nextIndex;
                }
            }
            else {
                this.next = LinkedList.this.header;
                this.nextIndex = LinkedList.this.size;
                while (this.nextIndex > index) {
                    this.next = this.next.previous;
                    --this.nextIndex;
                }
            }
        }
        
        @Override
        public boolean hasNext() {
            return this.nextIndex != LinkedList.this.size;
        }
        
        @Override
        public E next() {
            this.checkForComodification();
            if (this.nextIndex == LinkedList.this.size) {
                throw new NoSuchElementException();
            }
            this.lastReturned = this.next;
            this.next = this.next.next;
            ++this.nextIndex;
            return this.lastReturned.element;
        }
        
        @Override
        public boolean hasPrevious() {
            return this.nextIndex != 0;
        }
        
        @Override
        public E previous() {
            if (this.nextIndex == 0) {
                throw new NoSuchElementException();
            }
            final Entry<E> previous = this.next.previous;
            this.next = previous;
            this.lastReturned = previous;
            --this.nextIndex;
            this.checkForComodification();
            return this.lastReturned.element;
        }
        
        @Override
        public int nextIndex() {
            return this.nextIndex;
        }
        
        @Override
        public int previousIndex() {
            return this.nextIndex - 1;
        }
        
        @Override
        public void remove() {
            this.checkForComodification();
            final Entry<E> lastNext = this.lastReturned.next;
            try {
                LinkedList.this.remove(this.lastReturned);
            }
            catch (NoSuchElementException e) {
                throw new IllegalStateException();
            }
            if (this.next == this.lastReturned) {
                this.next = lastNext;
            }
            else {
                --this.nextIndex;
            }
            this.lastReturned = LinkedList.this.header;
            ++this.expectedModCount;
        }
        
        @Override
        public void set(final E e) {
            if (this.lastReturned == LinkedList.this.header) {
                throw new IllegalStateException();
            }
            this.checkForComodification();
            this.lastReturned.element = e;
        }
        
        @Override
        public void add(final E e) {
            this.checkForComodification();
            this.lastReturned = LinkedList.this.header;
            LinkedList.this.addBefore(e, this.next);
            ++this.nextIndex;
            ++this.expectedModCount;
        }
        
        void checkForComodification() {
            if (LinkedList.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
    
    private static class Entry<E>
    {
        E element;
        Entry<E> next;
        Entry<E> previous;
        
        Entry(final E element, final Entry<E> next, final Entry<E> previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }
    }
    
    private class DescendingIterator implements Iterator
    {
        ListItr itr;
        
        private DescendingIterator() {
            this.itr = new ListItr(LinkedList.this.size());
        }
        
        @Override
        public boolean hasNext() {
            return this.itr.hasPrevious();
        }
        
        @Override
        public E next() {
            return this.itr.previous();
        }
        
        @Override
        public void remove() {
            this.itr.remove();
        }
    }
}
