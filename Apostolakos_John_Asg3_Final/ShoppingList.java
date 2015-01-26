
/**
 * ShoppingList class creates the Node objects and builds a link list
 * in a sorted order.
 * The items are added as needed from the objects created in the Processor
 * class, or quantities are updated if object already exists.
 * The items are subrtracted from the processor class if there is enough quantity
 * in inventory, or the quantity is updated to reflect the difference.
 * 
 * @author John Apostolakos
 * @version March 9, 2014
 */
public class ShoppingList implements GroceryList
{
    private Node current;
    private Node beforeLast;
    private Node last;
    private Node start;
    private Node newNode;
   
    // Adds or updates an item to the existing shopping list in the proper lexicographic order
    @Override
    public void addItem (String itemName, int itemQuantity) // Name and quantity read from meals file
    {
        int quantity;
        int compare;
        String item;
        item = itemName;
        quantity = itemQuantity;
        
        if(contains(item))// Invokes the contains method to validate whether the object exists. Will return true if exists.
        {
            quantity += current.getQuantity();
            current.setQuantity(quantity);
        } 
        else // If object does not exist will create the object and add to link list
        {                    
            current = start;
            newNode = new Node(item, quantity);
            if(start == null) // Adds node to start 
            {
                start = newNode;
            }        
            else 
            {                
                last = current;
                beforeLast = last;
                boolean insert = false;
                do
                {                    
                    beforeLast = last;
                    last = current;
                    compare = current.getItem().compareToIgnoreCase(item); // Returns int neg, 0, or pos if compared item is smaller, equal, or larger
                    if(compare > 0 && current == start) // Verifies if there is only one object in the list
                    {
                        start = newNode;
                        newNode.setNextNode(current);
                        insert = true;
                    }
                    else if(compare < 0 && current.getNextNode() == null) // Verifies if the object is at the end of the list
                    {
                        current.setNextNode(newNode);
                        insert = true;
                    }                   
                    else if(compare > 0) // Adds object to the appropriate position in the middle of the list
                    {
                        beforeLast.setNextNode(newNode);
                        newNode.setNextNode(current);
                        insert = true;
                    }
                    else if(compare < 0 && current.getNextNode() != null)
                        current = last.getNextNode(); 

                }
                while(!insert);               
            }

        }
    }

    // Removes or updates an item in the list
    @Override
    public void removeItem (String itemName, int qtyInput) // Name and quantity read from inventory file
    {
        int onHand;
        int compare;
        int quantity;
        String item;
        item = itemName;
        onHand = qtyInput;

        if(contains(item)) // Returns true if item is in list
        {
            quantity = current.getQuantity(); 
            
            if(onHand < quantity) // Updates quantity if inventory is less than required 
            {
                quantity = quantity - onHand;
                current.setQuantity(quantity);
            }
            else if(onHand >= quantity) // Removes object if inventory is equal or greater than required
            {       
                if(current == start) // Removes object from start of list
                {                   
                    start = current.getNextNode();
                    current.setNextNode(null);

                    current = null;
                    last = null;
                    beforeLast = null;
                }
                else if(current.getNextNode() == null) // Removes object from end of list
                {
                    beforeLast.setNextNode(null);
                    current = null;
                    last = null;                      
                }
                else if(current.getNextNode() != null) // Removes object from middle of list
                {
                    beforeLast.setNextNode(current.getNextNode());
                    current.setNextNode(null);
                    current = null;
                    last = null;
                }
            }
        }
        else{}
    }

    @Override
    public boolean contains(String itemName) // Verifies whether an instance of the object exists
    {
        boolean found = false;
        String item = itemName;
        current = start;

        int compare;

        if(current != null)
        {        
            do
            {
                beforeLast = last;
                last = current;                
                compare = current.getItem().compareToIgnoreCase(item); 
                if(compare == 0)
                    found = true;
                else 
                {
                    current = last.getNextNode();
                }
            }
            while(!found && current != null);

        }
        else{}

        return found;
    }

    @Override
    public int getQuantity(String itemName) // Returns a quantity if contains boolean is true
    {     
        int units = 0;
        if(contains(itemName)) // Verifies whether instance of object exists
        {
            units = current.getQuantity();
        }
        else{}

        return units;
    }

    @Override
    public int size() // Counts the number of objects in the list - Objects are already verified as unique in the addItem method
    {
        current = start;
        int count = 0;
        while(current != null)
        {           
            last = current;
            current = last.getNextNode(); 
            count++;
        }
        return count;
    }

    @Override
    public String toString () // Concatenates a string used to write to the file
    {
        String shoppingList = "";
        current = start;       
        while(current != null)
        {
            last = current;
            shoppingList += current.toString();
            current = last.getNextNode();
        }
        return shoppingList;
    }

}
