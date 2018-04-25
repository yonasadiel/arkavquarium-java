package com.arkavquarium.models;

public class LinkedListIterator<E> {
  private E content;
  private LinkedListIterator<E> next;
  private LinkedListIterator<E> prev;

  /**
   * Construct LinkedListIterator with content = *content, next = null, and prev = null.
   * @param content content of the iterator
   */
  public LinkedListIterator(E content) {
    this.next = null;
    this.prev = null;
    this.content = content;
  }

  /**
   * Return the content of the iterator.
   * @return content of current item
   */
  public E getContent() {
    return this.content;
  }

  /**
   * Get previous iterator.
   * @return previous iterator
   */
  public LinkedListIterator<E> getPrev() {
    return this.prev;
  }

  /**
   * Get next iterator.
   * @return next iterator
   */
  public LinkedListIterator<E> getNext() {
    return this.next;
  }

  /**
   * Set content of the iterator.
   * @param content new iterator content
   */
  public void setContent(E content) {
    this.content = content;
  }

  /**
   * Set address to previous LinkedListIterator.
   * @param prev previous iterator
   */
  public void setPrev(LinkedListIterator<E> prev) {
    this.prev = prev;
  }

  /**
   * Set address to next LinkedListItem.
   * @param next next iterator
   */
  public void setNext(LinkedListIterator<E> next) {
    this.next = next;
  }
}