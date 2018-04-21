public class LinkedList<E> {
    private LinkedListIterator<E> first;
    private LinkedListIterator<E> last;

    /**
     * Construct linked list with first = null and last = null
     */
    LinkedList() {
        this.first = null;
        this.last = null;
    }

    /**
     * Find particular element in LinkedList
     * @param element element to find
     * @return index of element
     */
    int find(E element) {
        int count = 0;
        boolean found = false;
        LinkedListIterator<E> it;

        it = this.first;
        while (it != null && !found) {
            if (it.getContent() == element) { found = true; }
            else { it = it.getNext(); count++; }
        }
        return count;
    }

    /**
     * @return true if LinkedList is empty
     */
    boolean isEmpty() {
        return this.first == null && this.last == null;
    }

    /**
     * Adds a new LinkedListIterator to front of list
     * @param element element to add
     */
    void add(E element) {
        LinkedListIterator<E> newIterator;

        newIterator = new LinkedListIterator<E>(element); 
        newIterator.setPrev(this.last);
        if (this.last != null) { this.last.setNext(newIterator); }
        this.last = newIterator;
        if (this.first == null) {
            this.first = newIterator;
        }
    }
        
    /**
     * Removes firs occurence LinkedListIterator with content element
     * @param element element to delete
     */
    void remove(E element) {
        LinkedListIterator<E> it;

        it = this.first;

        while (it != null && it.getContent() != element) {
            it = it.getNext();
        }

        if (it != null) {
            LinkedListIterator<E> prev = it.getPrev();
            LinkedListIterator<E> next = it.getNext();

            if (prev == null) { this.first = next; }
            else { prev.setNext(next); }

            if (next == null) { this.last = prev; }
            else { next.setPrev(prev); }
        }
    }

    /**
     * @param index Element index, must less than sizeof LinkedList
     * @return i-th element in the LinkedList
     */
    E getContentAt(int index) {
        return this.getIteratorAt(index).getContent();
    }

    /**
     * @param index Element index, must less than sizeof LinkedList
     * @return i-th it in the LinkedList
     */
    LinkedListIterator<E> getIteratorAt(int index) {
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
     * The List is not empty
     * @return content of first LinkedListIterator
     */
    E getFirstContent() {
        return this.first.getContent();
    }

    /**
     * The List is not empty
     * @return first LinkedListIterator
     */
    LinkedListIterator<E> getFirstIterator() {
        return this.first;
    }

    /**
     * The List is not empty
     * @return content of last LinkedListIterator
     */
    E getLastContent() {
        return this.last.getContent();
    }

    /**
     * The List is not empty
     * @return last LinkedListIterator
     */
    LinkedListIterator<E> getLastIterator() {
        return this.last;
    }
}