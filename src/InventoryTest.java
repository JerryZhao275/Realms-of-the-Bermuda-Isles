import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    private Inventory inventory;
    private Item item1 = new Item("Health Potion", 0, 0,ItemType.Potion);
    private Item item2 = new Item("Mana Elixir", 0, 0,ItemType.Potion);

    @Test
    void testAddItem() {
        inventory = new Inventory();
        inventory.addItem(item1);
        assertTrue(inventory.getItems().contains(item1), "Item should be added to inventory");
    }

    @Test
    void testRemoveItem() {
        inventory = new Inventory();
        inventory.addItem(item1);
        inventory.addItem(item2);

        inventory.removeItem(item1);
        assertFalse(inventory.getItems().contains(item1), "Item should be removed from inventory");
        assertTrue(inventory.getItems().contains(item2), "Other item should still be in inventory");
    }

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

    @Test
    void testGetItems() {
        inventory = new Inventory();
        assertEquals(0, inventory.getItems().size(), "Inventory should start empty");
        inventory.addItem(item1);
        inventory.addItem(item2);
        assertEquals(2, inventory.getItems().size(), "Inventory should contain two items");
    }
}
