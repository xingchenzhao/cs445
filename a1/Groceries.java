package a1;

import java.util.Arrays;

public class Groceries implements GroceriesInterface {

    private SetInterface<GroceryItem> myGroceries;
    private GroceryItem[] myList;
    private boolean existOfAddItem = false;
    private boolean existOfRemoveItem = false;
    private boolean existOfModifedItem = false;


    public Groceries() {
        myGroceries = new Set<>();
    }


    public void addItem(GroceryItem item) {
        existOfAddItem = myGroceries.contains(item);


        if (!existOfAddItem) {
            try {
                myGroceries.add(item);
            } catch (SetFullException e) {
                e.printStackTrace();
            }
            myList = Arrays.copyOf(myGroceries.toArray(), myGroceries.toArray().length, GroceryItem[].class);
        } else {
            for (int i = 0; i < myList.length; i++) {
                if (item.equals(myList[i])) {
                    myList[i].setQuantity(myList[i].getQuantity() + item.getQuantity());
                }
            }

        }
    }

    public void removeItem(GroceryItem item) {
        existOfRemoveItem = myGroceries.contains(item);
        if (existOfRemoveItem) {
            for (int i = 0; i < myList.length; i++) {
                if (item.equals(myList[i])) {
                    int quantity = myList[i].getQuantity() - item.getQuantity();
                    if (quantity <= 0) {
                        myGroceries.remove(myList[i]);
                        myList = Arrays.copyOf(myGroceries.toArray(), myGroceries.toArray().length, GroceryItem[].class);
                    } else {
                        myList[i].setQuantity(quantity);
                    }
                }
            }
        }
    }

    public int modifyQuantity(GroceryItem item) {
        int num = -1;
        existOfModifedItem = myGroceries.contains(item);
        if (existOfModifedItem) {
            myList = Arrays.copyOf(myGroceries.toArray(), myGroceries.toArray().length, GroceryItem[].class);
            for (int i = 0; i < myList.length; i++) {
                if (item.equals(myList[i])) {
                    num = myList[i].getQuantity();
                    int quantity = item.getQuantity();
                    myList[i].setQuantity(quantity);
                }
            }
            return num;
        } else {
            return num;
        }
    }
//testing clear method
    public void clearAllItems() {

        myGroceries.clear();
        myList = Arrays.copyOf(myGroceries.toArray(), myGroceries.toArray().length, GroceryItem[].class);
        System.out.println();
        System.out.println("All items have been cleared. ");
    }

    public void printAll() {
        System.out.println("Groceries: ");
        for (int i = 0; i < myList.length; i++) {
            System.out.println("The quantity of " + myList[i].getDescription() + " is " + myList[i].getQuantity());
        }
    }
}

