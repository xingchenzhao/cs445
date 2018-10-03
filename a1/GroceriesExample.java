package a1;

public class GroceriesExample {

    public static void main(String[] args) {

        // Create my grocery list
        GroceriesInterface billsGroceries = new Groceries();

        // Add dinner
        billsGroceries.addItem(new GroceryItem("Pork roast", 1));
        billsGroceries.addItem(new GroceryItem("Fennel", 2));
        billsGroceries.addItem(new GroceryItem("White wine", 1));
        billsGroceries.addItem(new GroceryItem("Rosemary", 1));
        billsGroceries.addItem(new GroceryItem("Fuji apple", 3));
        billsGroceries.addItem(new GroceryItem("Red onion", 1));

        // Print groceries so far
        billsGroceries.printAll();
        System.out.println();

        // Just found out friends are coming over! Better double the meat...
        billsGroceries.modifyQuantity(new GroceryItem("Pork roast", 2));

        // ... and add another bottle of wine...
        billsGroceries.addItem(new GroceryItem("White wine", 1));

        // ... and they're bringing the apples.
        billsGroceries.removeItem(new GroceryItem("Fuji apple", 5));

        // Make sure pork and wine are listed once each!
        billsGroceries.printAll();

    }

}

