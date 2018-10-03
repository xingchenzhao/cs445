package a1;

/**
 * cs445.a1.a1.GroceryItem represents a single item on a shopping list. Its quantity is
 * mutable but its description is not editable once created.
 */
public class GroceryItem {

    private String desc;
    private int quantity;

    /**
     * Initialize a GroceryItem with the given description and quantity.
     *
     * @param desc the description to be assigned
     * @param quantity the quantity of the item
     */
    public GroceryItem(String desc, int quantity)
            throws NullPointerException, IllegalArgumentException {
        if (desc == null) throw new NullPointerException("Null description");
        if (desc.length() == 0)
            throw new IllegalArgumentException("Empty description");
        if (quantity < 1)
            throw new IllegalArgumentException("Quantity less than 1");
        this.desc = desc;
        this.quantity = quantity;
    }

    /**
     * Initialize a GroceryItem with the given description and quantity 1.
     *
     * @param desc the description to be assigned
     */
    public GroceryItem(String desc) {
        this(desc, 1);
    }

    /**
     * Gets this item's description.
     *
     * @return  the description
     */
    public String getDescription() {
        return desc;
    }

    /**
     * Gets this item's quantity.
     *
     * @return  the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets this items quantity. If the given quantity is invalid (zero or negative), this method
     * will throw IllegalArgumentException.
     *
     * @param q the new quantity
     * @throws IllegalArgumentException if q is 0 or negative
     */
    public void setQuantity(int q) throws IllegalArgumentException {
        if (q < 1) throw new IllegalArgumentException("Quantity less than 1");
        quantity = q;
    }

    /**
     * Converts this item into a readable string.
     *
     * @return  the string representation of this item
     */
    @Override
    public String toString() {
        return quantity + " " + desc;
    }

    /**
     * Determines whether some other object is a GroceryItem that is equal to
     * this GroceryItem. Note: This method considers GroceryItem objects to be
     * equal if their description is the same, even if their quantity is
     * different. This means you cannot add two GroceryItems to the same Set
     * that have the same description and different quantities.
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof GroceryItem)) return false;
        GroceryItem gOther = (GroceryItem)other;

        // Note: because desc cannot be set to null, this is safe
        return desc.equals(gOther.desc);
    }

}