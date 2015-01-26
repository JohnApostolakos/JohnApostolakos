/**
 * GroceryList class maintains the grocery list items in a link list format
 * 
 * 
 * @Author: John Apostolakos
 * @Created date: March 9, 2013
 * 
 */

// Constructor for GroceryList 

public interface GroceryList {

   //@paramameter item added to the list
   // @parameter quantity of item is updated
    public void addItem (String item, int quantity);

    /**
     * Removes the specified quantity of the given item from the list. If the
     * item is already in the list, its quantity is decreased by the given
     * amount. If the quantity is reduced to zero or less ,the item is removed
     * entirely from the list.
     * 
     * @param item the item to remove from the list.
     * @param quantity the quantity of the item to remove from the list.
     */
    public void removeItem (String item, int quantity);

    /**
     * Determines if the item is in the list. Returns {@code true} if the item
     * is in the list; {@code false} otherwise.
     * 
     * @param item the item to check for in the list.
     * @return {@code true} if the item is in the list; {@code false} otherwise.
     */
    public boolean contains (String item);

    /**
     * Retrieves the quantity associated with the given item (or 0 if the item
     * is not in the list).
     * 
     * @param item the item for which the quantity is to be retrieved.
     * @return the quantity associated with the given item (or 0 if the item is
     *         not in the list).
     */
    public int getQuantity (String item);

    /**
     * Retrieves the number of unique items in the list.
     * 
     * @return the number of unique items in the list.
     */
    public int size ();

    /**
     * Retrieves the current grocery list as a string. The list should be sorted
     * alphabetically with one item per line. Each line should have the quantity
     * followed by a space followed by the item name.
     * 
     * @return the current grocery list as a string.
     */
    public String toString ();
}
