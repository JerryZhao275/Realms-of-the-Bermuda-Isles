import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a JUnit test class responsible for covering all tests for the Item class.
 *
 * @author Jerry Zhao
 */
public class ItemTest {
    Inventory inventory = new Inventory();
    private final Item item1 = new Item("Greatsword", 0, 0,ItemType.Sword);
    private final Item item2 = new Item("Mana Elixir", 0, 0,ItemType.Potion);
    private final Item item3 = new Item("Long Bow", 0, 0,ItemType.Bow);
    private final Item item4 = new Item("Steel Plated Chestplate", 0, 0,ItemType.Armor);
    private final Item item5 = new Item("Gold", 0, 0,ItemType.Gold);

    /**
     * Tests the 'getPrice' method for various items.
     * Expected outcome: Each item should return its unique price.
     *
     * @author Jerry Zhao
     */
    @Test
    void testGetPrice() {
        assertEquals(2, item1.getPrice());
        assertEquals(1, item2.getPrice());
        assertEquals(3, item3.getPrice());
        assertEquals(2, item4.getPrice());
        assertEquals(1, item5.getPrice());
    }

    /**
     * Tests the 'getItemType' method for various items.
     * Expected outcome: Each item should return its unique type.
     *
     * @author Jerry Zhao
     */
    @Test
    void testItemType() {
        assertEquals(ItemType.Sword, item1.getItemType());
        assertEquals(ItemType.Potion, item2.getItemType());
        assertEquals(ItemType.Bow, item3.getItemType());
        assertEquals(ItemType.Armor, item4.getItemType());
        assertEquals(ItemType.Gold, item5.getItemType());
    }

    /**
     * Tests the 'getDurability' method for various items.
     * Expected outcome: Each item should return its correct durability.
     *
     * @author Jerry Zhao
     */
    @Test
    void testGetDurability() {
        assertEquals(3, item1.getDurability());
        assertEquals(2, item2.getDurability());
        assertEquals(3, item3.getDurability());
    }

    /**
     * Tests the durability of items after using the items.
     * Expected outcome: Item's durability will decrease after using the item.
     *                      Unusable items will be removed from inventory.
     *
     * @author Jerry Zhao
     */
    @Test
    void testUseItem() {
        // Using item until break
        assertTrue(item1.canUse());
        inventory.addItem(item1);
        assertEquals(3, item1.getDurability());
        item1.use(inventory);
        assertEquals(2, item1.getDurability());
        item1.use(inventory);
        assertEquals(1, item1.getDurability());
        item1.use(inventory);
        assertEquals(0, item1.getDurability());
        assertFalse(item1.canUse());

        // Using item when broken
        item1.use(inventory);
        assertNull(inventory.getItem(item1.getName()));

        // Using unusable item
        assertNotNull(item4);
        item4.use(inventory);
        assertNotNull(item4);
    }
}
