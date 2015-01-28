/**
 * A test class for a {@link GroceryList}. The method {@link #makeGroceryList()}
 * should be changed to return your specific type of {@code GroceryList}.
 * 
 * @author Jason Heard
 * @version 1.0
 */
public class GroceryListTests extends junit.framework.TestCase {
	private static final int APPLE_QUANTITY = 2;
	private static final int BREAD_QUANTITY = 7;
	private static final int JELLY_QUANTITY = 3;
	private static final int PEANUT_BUTTER_QUANTITY = 5;
	
	private static final String NON_EXISTANT_FIRST = "aardvark";
	private static final String NON_EXISTANT_MIDDLE = "carrot";
	private static final String NON_EXISTANT_LAST = "zebra";
	private static final String APPLE = "apple";
	private static final String BREAD = "bread";
	private static final String JELLY = "jelly";
	private static final String PEANUT_BUTTER = "peanut butter";
	
	public static GroceryList makeGroceryList () {
		return new ShoppingList();
	}

	private GroceryList addOneSetup () {
		GroceryList groceryList = makeGroceryList ();
		groceryList.addItem (APPLE, APPLE_QUANTITY);
		return groceryList;
	}

	private GroceryList addFourSetup () {
		GroceryList groceryList = makeGroceryList ();
		// add only
		groceryList.addItem (BREAD, BREAD_QUANTITY);
		// add at end
		groceryList.addItem (PEANUT_BUTTER, PEANUT_BUTTER_QUANTITY);
		// add in middle
		groceryList.addItem (JELLY, JELLY_QUANTITY);
		// add at front
		groceryList.addItem (APPLE, APPLE_QUANTITY);
		return groceryList;
	}	

	private GroceryList addDuplicatesSetup () {
		GroceryList groceryList = addFourSetup ();
		// add duplicate first item
		groceryList.addItem (APPLE, APPLE_QUANTITY);
		// add duplicate middle item
		groceryList.addItem (JELLY, JELLY_QUANTITY);
		// add duplicate end item
		groceryList.addItem (PEANUT_BUTTER, PEANUT_BUTTER_QUANTITY);
		// bread is left the same
		return groceryList;
	}	

	private GroceryList reduceQuantitySetup () {
		GroceryList groceryList = addFourSetup ();
		// reduce quantity on first item
		groceryList.removeItem (APPLE, 1);
		// reduce quantity on middle item
		groceryList.removeItem (JELLY, 1);
		// reduce quantity on end item
		groceryList.removeItem (PEANUT_BUTTER, 1);
		// bread is left the same
		return groceryList;
	}

	private GroceryList removeItemsSetup () {
		GroceryList groceryList = addFourSetup ();
		// remove middle item
		groceryList.removeItem (BREAD, BREAD_QUANTITY);
		// remove first item
		groceryList.removeItem (APPLE, APPLE_QUANTITY);
		// remove last item
		groceryList.removeItem (PEANUT_BUTTER, PEANUT_BUTTER_QUANTITY);
		// jelly is left in the list
		return groceryList;
	}	

	public void test_empty_list_contains () {
		GroceryList groceryList = makeGroceryList ();

		assertFalse ("A new list should not contain anything", groceryList.contains (NON_EXISTANT_MIDDLE));
	}

	public void test_empty_list_quantity () {
		GroceryList groceryList = makeGroceryList ();

		assertEquals ("A new list should return 0 for any item's quantity", 0, groceryList.getQuantity (APPLE));
	}

	public void test_empty_list_size () {
		GroceryList groceryList = makeGroceryList ();

		assertEquals ("A new list should have a size of 0", 0, groceryList.size ());
	}

	public void test_empty_list_toString () {
		GroceryList groceryList = makeGroceryList ();

		assertEquals ("A new list should return an empty toString", "", groceryList.toString ());
	}
	
	public void test_add_one_contains_exists () {
		GroceryList groceryList = addOneSetup ();
		
		assertTrue ("An item that was added should be remembered", groceryList.contains (APPLE));
	}

	public void test_add_one_contains_nonexistant_first () {
		GroceryList groceryList = addOneSetup ();
		
		assertFalse ("An item that was not added should remain out of the list (before first)", groceryList.contains (NON_EXISTANT_FIRST));
	}
	
	public void test_add_one_contains_nonexistant_last () {
		GroceryList groceryList = addOneSetup ();
		
		assertFalse ("An item that was not added should remain out of the list (after last)", groceryList.contains (NON_EXISTANT_LAST));
	}
	
	public void test_add_one_quantity_exists () {
		GroceryList groceryList = addOneSetup ();
		
		assertEquals ("The quantity of an item that was added should be remembered", APPLE_QUANTITY, groceryList.getQuantity (APPLE));
	}
	
	public void test_add_one_quantity_nonexistant_first () {
		GroceryList groceryList = addOneSetup ();
		
		assertEquals ("The quantity of an item that was not added should remain at zero (before first)", 0, groceryList.getQuantity (NON_EXISTANT_FIRST));
	}
	
	public void test_add_one_quantity_nonexistant_last () {
		GroceryList groceryList = addOneSetup ();
		
		assertEquals ("The quantity of an item that was not added should remain at zero (after last)", 0, groceryList.getQuantity (NON_EXISTANT_LAST));
	}
	
	public void test_add_one_size () {
		GroceryList groceryList = addOneSetup ();
		
		assertEquals ("A list with one item should rturn 1 for size", 1, groceryList.size ());
	}
	
	public void test_add_one_toString () {
		GroceryList groceryList = addOneSetup ();
		
		assertEquals ("A list with one item should return a one line toString", "2 apple\n", groceryList.toString ());
	}
	
	public void test_add_four_contains_apple () {
		GroceryList groceryList = addFourSetup ();
		
		assertTrue ("An item that was added should be remembered", groceryList.contains (APPLE));
	}

	public void test_add_four_contains_jelly () {
		GroceryList groceryList = addFourSetup ();
		
		assertTrue ("An item that was added should be remembered", groceryList.contains (JELLY));
	}

	public void test_add_four_contains_bread () {
		GroceryList groceryList = addFourSetup ();
		
		assertTrue ("An item that was added should be remembered", groceryList.contains (BREAD));
	}

	public void test_add_four_contains_peanut_butter () {
		GroceryList groceryList = addFourSetup ();
		
		assertTrue ("An item that was added should be remembered", groceryList.contains (PEANUT_BUTTER));
	}

	public void test_add_four_contains_nonexistant_first () {
		GroceryList groceryList = addFourSetup ();
		
		assertFalse ("An item that was not added should remain out of the list (before first)", groceryList.contains (NON_EXISTANT_FIRST));
	}
	
	public void test_add_four_contains_nonexistant_middle () {
		GroceryList groceryList = addFourSetup ();
		
		assertFalse ("An item that was not added should remain out of the list (in the middle)", groceryList.contains (NON_EXISTANT_MIDDLE));
	}
	
	public void test_add_four_contains_nonexistant_last () {
		GroceryList groceryList = addFourSetup ();
		
		assertFalse ("An item that was not added should remain out of the list (after last)", groceryList.contains (NON_EXISTANT_LAST));
	}
	
	public void test_add_four_quantity_apple () {
		GroceryList groceryList = addFourSetup ();
		
		assertEquals ("The quantity of an item that was added should be remembered", APPLE_QUANTITY, groceryList.getQuantity (APPLE));
	}
	
	public void test_add_four_quantity_bread () {
		GroceryList groceryList = addFourSetup ();
		
		assertEquals ("The quantity of an item that was added should be remembered", BREAD_QUANTITY, groceryList.getQuantity (BREAD));
	}
	
	public void test_add_four_quantity_jelly () {
		GroceryList groceryList = addFourSetup ();
		
		assertEquals ("The quantity of an item that was added should be remembered", JELLY_QUANTITY, groceryList.getQuantity (JELLY));
	}
	
	public void test_add_four_quantity_peanut_butter () {
		GroceryList groceryList = addFourSetup ();
		
		assertEquals ("The quantity of an item that was added should be remembered", PEANUT_BUTTER_QUANTITY, groceryList.getQuantity (PEANUT_BUTTER));
	}
	
	public void test_add_four_quantity_nonexistant_first () {
		GroceryList groceryList = addFourSetup ();
		
		assertEquals ("The quantity of an item that was not added should remain at zero (before first)", 0, groceryList.getQuantity (NON_EXISTANT_FIRST));
	}
	
	public void test_add_four_quantity_nonexistant_middle () {
		GroceryList groceryList = addFourSetup ();
		
		assertEquals ("The quantity of an item that was not added should remain at zero (in middle)", 0, groceryList.getQuantity (NON_EXISTANT_MIDDLE));
	}
	
	public void test_add_four_quantity_nonexistant_last () {
		GroceryList groceryList = addFourSetup ();
		
		assertEquals ("The quantity of an item that was not added should remain at zero (after last)", 0, groceryList.getQuantity (NON_EXISTANT_LAST));
	}
	
	public void test_add_four_size () {
		GroceryList groceryList = addFourSetup ();
		
		assertEquals ("A list with four items should return 4 for size", 4, groceryList.size ());
	}
	
	public void test_add_four_toString () {
		GroceryList groceryList = addFourSetup ();
		
		assertEquals ("A list with four items should return a sorted four line toString", "2 apple\n7 bread\n3 jelly\n5 peanut butter\n", groceryList.toString ());
	}	

	public void test_add_duplicates_contains_apple () {
		GroceryList groceryList = addDuplicatesSetup ();
		
		assertTrue ("An item that was added should be remembered", groceryList.contains (APPLE));
	}

	public void test_add_duplicates_contains_jelly () {
		GroceryList groceryList = addDuplicatesSetup ();
		
		assertTrue ("An item that was added should be remembered", groceryList.contains (JELLY));
	}

	public void test_add_duplicates_contains_bread () {
		GroceryList groceryList = addDuplicatesSetup ();
		
		assertTrue ("An item that was added should be remembered", groceryList.contains (BREAD));
	}

	public void test_add_duplicates_contains_peanut_butter () {
		GroceryList groceryList = addDuplicatesSetup ();
		
		assertTrue ("An item that was added should be remembered", groceryList.contains (PEANUT_BUTTER));
	}

	public void test_add_duplicates_contains_nonexistant_first () {
		GroceryList groceryList = addDuplicatesSetup ();
		
		assertFalse ("An item that was not added should remain out of the list (before first)", groceryList.contains (NON_EXISTANT_FIRST));
	}
	
	public void test_add_duplicates_contains_nonexistant_middle () {
		GroceryList groceryList = addDuplicatesSetup ();
		
		assertFalse ("An item that was not added should remain out of the list (in middle)", groceryList.contains (NON_EXISTANT_MIDDLE));
	}
	
	public void test_add_duplicates_contains_nonexistant_last () {
		GroceryList groceryList = addDuplicatesSetup ();
		
		assertFalse ("An item that was not added should remain out of the list (after last)", groceryList.contains (NON_EXISTANT_LAST));
	}
	
	public void test_add_duplicates_quantity_apple () {
		GroceryList groceryList = addDuplicatesSetup ();
		
		assertEquals ("The quantity of an item that was added twice should be doubled", APPLE_QUANTITY * 2, groceryList.getQuantity (APPLE));
	}
	
	public void test_add_duplicates_quantity_bread () {
		GroceryList groceryList = addDuplicatesSetup ();
		
		assertEquals ("The quantity of an item that was added once should remain constant", BREAD_QUANTITY, groceryList.getQuantity (BREAD));
	}
	
	public void test_add_duplicates_quantity_jelly () {
		GroceryList groceryList = addDuplicatesSetup ();
		
		assertEquals ("The quantity of an item that was added twice should be doubled", JELLY_QUANTITY * 2, groceryList.getQuantity (JELLY));
	}
	
	public void test_add_duplicates_quantity_peanut_butter () {
		GroceryList groceryList = addDuplicatesSetup ();
		
		assertEquals ("The quantity of an item that was added twice should be doubled", PEANUT_BUTTER_QUANTITY * 2, groceryList.getQuantity (PEANUT_BUTTER));
	}
	
	public void test_add_duplicates_quantity_nonexistant_first () {
		GroceryList groceryList = addDuplicatesSetup ();
		
		assertEquals ("The quantity of an item that was not added should remain at zero (before first)", 0, groceryList.getQuantity (NON_EXISTANT_FIRST));
	}
	
	public void test_add_duplicates_quantity_nonexistant_middle () {
		GroceryList groceryList = addDuplicatesSetup ();
		
		assertEquals ("The quantity of an item that was not added should remain at zero (in middle)", 0, groceryList.getQuantity (NON_EXISTANT_MIDDLE));
	}
	
	public void test_add_duplicates_quantity_nonexistant_last () {
		GroceryList groceryList = addDuplicatesSetup ();
		
		assertEquals ("The quantity of an item that was not added should remain at zero (after last)", 0, groceryList.getQuantity (NON_EXISTANT_LAST));
	}
	
	public void test_add_duplicates_size () {
		GroceryList groceryList = addDuplicatesSetup ();
		
		assertEquals ("A list with only four unique items added should return 4 for size", 4, groceryList.size ());
	}
	
	public void test_add_duplicates_toString () {
		GroceryList groceryList = addDuplicatesSetup ();
		
		assertEquals ("A list with four items (and duplicates added) should return a sorted four line toString", "4 apple\n7 bread\n6 jelly\n10 peanut butter\n", groceryList.toString ());
	}	

	public void test_reduce_quantity_contains_apple () {
		GroceryList groceryList = reduceQuantitySetup ();
		
		assertTrue ("An item that was added and reduced should be remembered", groceryList.contains (APPLE));
	}

	public void test_reduce_quantity_contains_jelly () {
		GroceryList groceryList = reduceQuantitySetup ();
		
		assertTrue ("An item that was added and reduced should be remembered", groceryList.contains (JELLY));
	}

	public void test_reduce_quantity_contains_bread () {
		GroceryList groceryList = reduceQuantitySetup ();
		
		assertTrue ("An item that was added and reduced should be remembered", groceryList.contains (BREAD));
	}

	public void test_reduce_quantity_contains_peanut_butter () {
		GroceryList groceryList = reduceQuantitySetup ();
		
		assertTrue ("An item that was added and reduced should be remembered", groceryList.contains (PEANUT_BUTTER));
	}

	public void test_reduce_quantity_contains_nonexistant_first () {
		GroceryList groceryList = reduceQuantitySetup ();
		
		assertFalse ("An item that was not added should remain out of the list (before first)", groceryList.contains (NON_EXISTANT_FIRST));
	}
	
	public void test_reduce_quantity_contains_nonexistant_middle () {
		GroceryList groceryList = reduceQuantitySetup ();
		
		assertFalse ("An item that was not added should remain out of the list (in middle)", groceryList.contains (NON_EXISTANT_MIDDLE));
	}
	
	public void test_reduce_quantity_contains_nonexistant_last () {
		GroceryList groceryList = reduceQuantitySetup ();
		
		assertFalse ("An item that was not added should remain out of the list (after last)", groceryList.contains (NON_EXISTANT_LAST));
	}
	
	public void test_reduce_quantity_quantity_apple () {
		GroceryList groceryList = reduceQuantitySetup ();
		
		assertEquals ("The quantity of an item that was added and reduced by 1 should be reduced by 1", APPLE_QUANTITY - 1, groceryList.getQuantity (APPLE));
	}
	
	public void test_reduce_quantity_quantity_bread () {
		GroceryList groceryList = reduceQuantitySetup ();
		
		assertEquals ("The quantity of an item that was added and not reduced should remain constant", BREAD_QUANTITY, groceryList.getQuantity (BREAD));
	}
	
	public void test_reduce_quantity_quantity_jelly () {
		GroceryList groceryList = reduceQuantitySetup ();
		
		assertEquals ("The quantity of an item that was added and reduced by 1 should be reduced by 1", JELLY_QUANTITY - 1, groceryList.getQuantity (JELLY));
	}
	
	public void test_reduce_quantity_quantity_peanut_butter () {
		GroceryList groceryList = reduceQuantitySetup ();
		
		assertEquals ("The quantity of an item that was added and reduced by 1 should be reduced by 1", PEANUT_BUTTER_QUANTITY - 1, groceryList.getQuantity (PEANUT_BUTTER));
	}
	
	public void test_reduce_quantity_quantity_nonexistant_first () {
		GroceryList groceryList = reduceQuantitySetup ();
		
		assertEquals ("The quantity of an item that was not added should remain at zero (before first)", 0, groceryList.getQuantity (NON_EXISTANT_FIRST));
	}
	
	public void test_reduce_quantity_quantity_nonexistant_middle () {
		GroceryList groceryList = reduceQuantitySetup ();
		
		assertEquals ("The quantity of an item that was not added should remain at zero (in middle)", 0, groceryList.getQuantity (NON_EXISTANT_MIDDLE));
	}
	
	public void test_reduce_quantity_quantity_nonexistant_last () {
		GroceryList groceryList = reduceQuantitySetup ();
		
		assertEquals ("The quantity of an item that was not added should remain at zero (after last)", 0, groceryList.getQuantity (NON_EXISTANT_LAST));
	}
	
	public void test_reduce_quantity_size () {
		GroceryList groceryList = reduceQuantitySetup ();
		
		assertEquals ("A list with only four unique items added should return 4 for size", 4, groceryList.size ());
	}
	
	public void test_reduce_quantity_toString () {
		GroceryList groceryList = reduceQuantitySetup ();
		
		assertEquals ("A list with four items (with reduced quantities) should return a sorted four line toString", "1 apple\n7 bread\n2 jelly\n4 peanut butter\n", groceryList.toString ());
	}	

	public void test_remove_items_contains_apple () {
		GroceryList groceryList = removeItemsSetup ();
		
		assertFalse ("An item that was added and removed should be remembered", groceryList.contains (APPLE));
	}

	public void test_remove_items_contains_jelly () {
		GroceryList groceryList = removeItemsSetup ();
		
		assertTrue ("An item that was added and not removed should be remembered", groceryList.contains (JELLY));
	}

	public void test_remove_items_contains_bread () {
		GroceryList groceryList = removeItemsSetup ();
		
		assertFalse ("An item that was added and removed should be remembered", groceryList.contains (BREAD));
	}

	public void test_remove_items_contains_peanut_butter () {
		GroceryList groceryList = removeItemsSetup ();
		
		assertFalse ("An item that was added and removed should be remembered", groceryList.contains (PEANUT_BUTTER));
	}

	public void test_remove_items_contains_nonexistant_first () {
		GroceryList groceryList = removeItemsSetup ();
		
		assertFalse ("An item that was not added should remain out of the list (before first)", groceryList.contains (NON_EXISTANT_FIRST));
	}
	
	public void test_remove_items_contains_nonexistant_middle () {
		GroceryList groceryList = removeItemsSetup ();
		
		assertFalse ("An item that was not added should remain out of the list (in middle)", groceryList.contains (NON_EXISTANT_MIDDLE));
	}
	
	public void test_remove_items_contains_nonexistant_last () {
		GroceryList groceryList = removeItemsSetup ();
		
		assertFalse ("An item that was not added should remain out of the list (after last)", groceryList.contains (NON_EXISTANT_LAST));
	}
	
	public void test_remove_items_quantity_apple () {
		GroceryList groceryList = removeItemsSetup ();
		
		assertEquals ("The quantity of an item that was added and removed should be 0", 0, groceryList.getQuantity (APPLE));
	}
	
	public void test_remove_items_quantity_bread () {
		GroceryList groceryList = removeItemsSetup ();
		
		assertEquals ("The quantity of an item that was added and not removed should remain constant", JELLY_QUANTITY, groceryList.getQuantity (JELLY));
	}
	
	public void test_remove_items_quantity_jelly () {
		GroceryList groceryList = removeItemsSetup ();
		
		assertEquals ("The quantity of an item that was added and removed should be 0", 0, groceryList.getQuantity (BREAD));
	}
	
	public void test_remove_items_quantity_peanut_butter () {
		GroceryList groceryList = removeItemsSetup ();
		
		assertEquals ("The quantity of an item that was added and removed should be 0", 0, groceryList.getQuantity (PEANUT_BUTTER));
	}
	
	public void test_remove_items_quantity_nonexistant_first () {
		GroceryList groceryList = removeItemsSetup ();
		
		assertEquals ("The quantity of an item that was not added should remain at zero (before first)", 0, groceryList.getQuantity (NON_EXISTANT_FIRST));
	}
	
	public void test_remove_items_quantity_nonexistant_middle () {
		GroceryList groceryList = removeItemsSetup ();
		
		assertEquals ("The quantity of an item that was not added should remain at zero (in middle)", 0, groceryList.getQuantity (NON_EXISTANT_MIDDLE));
	}
	
	public void test_remove_items_quantity_nonexistant_last () {
		GroceryList groceryList = removeItemsSetup ();
		
		assertEquals ("The quantity of an item that was not added should remain at zero (after last)", 0, groceryList.getQuantity (NON_EXISTANT_LAST));
	}
	
	public void test_remove_items_size () {
		GroceryList groceryList = removeItemsSetup ();
		
		assertEquals ("A list with one item (four added and one removed) should return 1 for size", 1, groceryList.size ());
	}
	
	public void test_remove_items_toString () {
		GroceryList groceryList = removeItemsSetup ();
		
		assertEquals ("A list with one item (four added and three removed) should return a sorted four line toString", "3 jelly\n", groceryList.toString ());
	}
}