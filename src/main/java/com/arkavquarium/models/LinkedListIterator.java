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
    E getContent() {
        return this.content;
    }

    /**
     * @return previous iterator
     */
    LinkedListIterator getPrev() {
        return this.prev;
    }

    /**
     * @return next iterator
     */
    LinkedListIterator getNext() {
        return this.next;
    }

    /**
     * @param E new iterator content
     */
    void setContent(E content) {
        this.content = content;
    }

    /**
     * Set address to previous LinkedListIterator
     * @param prev previous iterator
     */
    void setPrev(LinkedListIterator prev) {
        this.prev = prev;
    }

    /**
     * Set address to next LinkedListItem
     * @param next next iterator
     */
    void setNext(LinkedListIterator next) {
        this.next = next;
    }
}