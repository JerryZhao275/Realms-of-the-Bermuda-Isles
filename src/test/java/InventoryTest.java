import org.bermudagame.Inventory;
import org.bermudagame.Item;
import org.bermudagame.ItemType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a JUnit test class responsible for covering all tests for the Inventory class.
 *
 * @author Jerry Zhao
 */
public class InventoryTest {

    private Inventory inventory;
    private Item item1 = new Item("Health Potion", 0, 0, ItemType.Potion);
    private Item item2 = new Item("Mana Elixir", 0, 0, ItemType.Potion);
    private Item item3 = new Item("Bow", 0, 0, ItemType.Bow);

    /**
     * Tests the ability to add items from inventory system.
     * Expected outcome: The item should be added successfully into the inventory system.
     *
     * @author Jerry Zhao
     */
    @Test
    void testAddItem() {
        inventory = new Inventory();
        inventory.addItem(item1);
        assertTrue(inventory.getItems().contains(item1), "Item should be added to inventory");
    }

    /**
     * Tests the ability to remove items from inventory system.
     * Expected outcome: The item should be removed successfully from the inventory system.
     *
     * @author Jerry Zhao
     */
    @Test
    void testRemoveItem() {
        inventory = new Inventory();
        inventory.addItem(item1);
        inventory.addItem(item2);

        inventory.removeItem(item1);
        assertFalse(inventory.getItems().contains(item1), "Item should be removed from inventory");
        assertTrue(inventory.getItems().contains(item2), "Other item should still be in inventory");
    }

    /**
     * Tests the ability to retrieve items from inventory class.
     * Expected outcome: The item should be retrieved successfully from the inventory system,
     *                      and null should be returned if it doesn't.
     *
     * @author Jerry Zhao
     */
    @Test
    void testGetItemByName() {
        inventory = new Inventory();
        inventory.addItem(item1);
        inventory.addItem(item2);

        Item retrievedItem = inventory.getItem("Health Potion");
        assertNotNull(retrievedItem, "Retrieved item should not be null");
        assertEquals("Health Potion", retrievedItem.getName(), "Retrieved item should have the correct name");

        Item nonExistentItem = inventory.getItem("Non-existent Item");
        assertNull(nonExistentItem, "Non-existent item should return null");
    }

    /**
     * Tests the ability to get items from inventory system.
     * Expected outcome: The item should be got successfully from the inventory system based on what was added.
     *
     * @author Jerry Zhao
     */
    @Test
    void testGetItems() {
        inventory = new Inventory();
        assertEquals(0, inventory.getItems().size(), "Inventory should start empty");
        inventory.addItem(item1);
        inventory.addItem(item2);
        assertEquals(2, inventory.getItems().size(), "Inventory should contain two items");
    }

    /**
     * Tests the count of items from inventory system.
     * Expected outcome: The correct count of specified item should be returned.
     *
     * @author Jerry Zhao
     */
    @Test
    void testGetItemCount() {
        inventory = new Inventory();
        inventory.addItem(item1);
        inventory.addItem(item3);
        assertEquals(2, inventory.getItemCount(item1.getName()) + inventory.getItemCount(item3.getName()), "Inventory should contain two items");
    }
}
