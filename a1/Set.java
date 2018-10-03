package a1;

import java.util.Arrays;

public  class Set <E> implements SetInterface<E>{

    private E[]set;
    private static final int DEFAULT_INTITIAL_CAPACITY = 5;
    private int numberOfEntries;

    /** Creates an empty bag whose initial capacity is 5. */
    public Set(){
        this(DEFAULT_INTITIAL_CAPACITY);
    }

    /** Creates an empty bag having a given initial capacity.
     @param capacity the integer capacity desired */

    public Set(int capacity) {
        numberOfEntries = 0;
        @SuppressWarnings("unchecked")
        E[]tempSet = (E[]) new Object[capacity]; // unchecked cast
        set = tempSet;
    }// end constructor



    public int getSize() {

        return numberOfEntries;
    }

    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    public boolean add(E newEntry) throws SetFullException, NullPointerException {
        ensureCapacity();
        set[numberOfEntries] = newEntry;
        numberOfEntries++;
        return true;
    }

    // Doubles the size of the array bag if it is full.
    public void ensureCapacity(){
        if(numberOfEntries == set.length);
        set = Arrays.copyOf(set,2* set.length);
    }

    public int getIndexOf (E entry){
        int where = -1;
        boolean found = false;
        for(int index = 0; !found && (index < numberOfEntries); index++){
            if(entry.equals(set[index])){
                found = true;
                where = index;
            }
        }

        return where;
    }

    public E remove(E entry) throws NullPointerException {

        int index = getIndexOf(entry);
        E result = removeEntry(index);

        if(entry.equals(result)){
            return result;
        }else {
            return null;
        }

    }

    public E remove() {
        E result = removeEntry(numberOfEntries - 1 );

        return result;
    }

    private E removeEntry(int givenIndex){
        E result = null;
        if(!isEmpty() && (givenIndex) >= 0){
            //Entry to remove
            result = set[givenIndex]; // entry to remove
            numberOfEntries --;
            set[givenIndex] = set[numberOfEntries]; // replace entry with last entry
            set[numberOfEntries] = null; // remove last entry

        }
        return result;
    }

    public void clear() {
        while (!isEmpty()){
            remove();
        }//end clear
    }

    public boolean contains(E entry) throws NullPointerException {

        return getIndexOf(entry) >- 1;
    }


    /** Retrieves all entries that are in this bag.
     @return a newly allocated array of all the entries in the bag */
    public Object[] toArray() {
        @SuppressWarnings("unchecked")
        E[]result = (E[])new Object[numberOfEntries];
        for(int index = 0; index < numberOfEntries; index++){
            result[index] = set[index];
        }
        return result;
    }
}
