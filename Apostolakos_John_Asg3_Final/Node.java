
/**
 * Node class constructs the Node object used within the 
 * program to store data.
 * 
 * @author John Apostolakos
 * @version March 9, 2014
 */
public class Node
{
    private Node next;
    private String item;
    private int quantity;

    public Node()
    {
        next = null;
        item = null;
        quantity = 0;
    }

    public Node (String item, int quantity)
    {
        next = null;
        this.item = item;
        this.quantity = quantity;
    }

    public Node getNextNode()
    {
        return next;
    }

    public String getItem()
    {
        return item;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setNextNode(Node next)
    {
        this.next = next;
    }

    public void setItem(String item)
    {
        this.item = item;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public String toString()
    {
        return (quantity + " " + item + "\r\n");
    }

}
