package com.arkavquarium.models;

public class LinkedList<E> {
  private LinkedListIterator<E> first;
  private LinkedListIterator<E> last;

  /**
   * Construct linked list with first = null and last = null.
   */
  public LinkedList() {
    this.first = null;
    this.last = null;
  }

  /**
   * Find particular element in LinkedList.
   * @param element element to find
   * @return index of element
   */
  public int find(E element) {
    int count = 0;
    boolean found = false;
    LinkedListIterator<E> it;

    it = this.first;
    while (it != null && !found) {
      if (it.getContent() == element) {
        found = true;
      } else {
        it = it.getNext();
        count++;
      }
    }
    return count;
  }

  /**
   * Return true if there is no object inside linked list.
   * @return true if LinkedList is empty
   */
  public boolean isEmpty() {
    return this.first == null && this.last == null;
  }

  /**
   * Adds a new LinkedListIterator to front of list.
   * @param element element to add
   */
  public void add(E element) {
    LinkedListIterator<E> newIterator;

    newIterator = new LinkedListIterator<E>(element); 
    newIterator.setPrev(this.last);
    if (this.last != null) {
      this.last.setNext(newIterator);
    }
    this.last = newIterator;
    if (this.first == null) {
      this.first = newIterator;
    }
  }
    
  /**
   * Removes firs occurence LinkedListIterator with content element.
   * @param element element to delete
   */
  public void remove(E element) {
    LinkedListIterator<E> it;

    it = this.first;

    while (it != null && it.getContent() != element) {
      it = it.getNext();
    }

    if (it != null) {
      LinkedListIterator<E> prev = it.getPrev();
      LinkedListIterator<E> next = it.getNext();

      if (prev == null) {
        this.first = next;
      } else {
        prev.setNext(next);
      }

      if (next == null) {
        this.last = prev;
      } else {
        next.setPrev(prev);
      }
    }
  }

  /**
   * Get content of linked list at particular index.
   * @param index Element index, must less than sizeof LinkedList
   * @return i-th element in the LinkedList
   */
  public E getContentAt(int index) {
    return this.getIteratorAt(index).getContent();
  }

  /**
   * Get iterator of linked list at particular index.
   * @param index Element index, must less than sizeof LinkedList
   * @return i-th it in the LinkedList
   */
  public LinkedListIterator<E> getIteratorAt(int index) {
    LinkedListIterator<E> it;
    int i = 0;

    it = this.first;
    while (i < index && it != this.last) {
      it = it.getNext();
      i++;
    }
    return it;
  }

  /**
   * Get the first content of linked list.
   * The List is not empty.
   * @return content of first LinkedListIterator
   */
  public E getFirstContent() {
    return this.first.getContent();
  }

  /**
   * Get the first iterator of linked list.
   * The List is not empty.
   * @return first LinkedListIterator
   */
  public LinkedListIterator<E> getFirstIterator() {
    return this.first;
  }

  /**
   * Get the last content of linked list.
   * The List is not empty.
   * @return content of last LinkedListIterator
   */
  public E getLastContent() {
    return this.last.getContent();
  }

  /**
   * Get the last iterator of linked list.
   * The List is not empty.
   * @return last LinkedListIterator
   */
  public LinkedListIterator<E> getLastIterator() {
    return this.last;
  }
}