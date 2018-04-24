package com.arkavquarium.models;

public class LinkedListIterator<E> {
    private E content;
    private LinkedListIterator<E> next;
    private LinkedListIterator<E> prev;

    /**
     * Construct LinkedListIterator with content = *content, next = null, and prev = null
     * @param content content of the iterator
     */
    public LinkedListIterator(E content) {
        this.next = null;
        this.prev = null;
        this.content = content;
    }

    /**
     * @return content of current item
     */
    public E getContent() {
        return this.content;
    }

    /**
     * @return previous iterator
     */
    public LinkedListIterator<E> getPrev() {
        return this.prev;
    }

    /**
     * @return next iterator
     */
    public LinkedListIterator<E> getNext() {
        return this.next;
    }

    /**
     * @param E new iterator content
     */
    public void setContent(E content) {
        this.content = content;
    }

    /**
     * Set address to previous LinkedListIterator
     * @param prev previous iterator
     */
    public void setPrev(LinkedListIterator<E> prev) {
        this.prev = prev;
    }

    /**
     * Set address to next LinkedListItem
     * @param next next iterator
     */
    public void setNext(LinkedListIterator<E> next) {
        this.next = next;
    }
}