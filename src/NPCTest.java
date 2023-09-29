import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a JUnit test class responsible for covering all tests for the NPC class.
 *
 * @author Jerry Zhao
 */
public class NPCTest {

    private Inventory inventory;
    private Map map;

    /**
     * Test the interaction with BlackSmith NPC.
     * Expected outcome: The inventory should contain a 'Sword' after talking with BlackSmith,
     *                      and BlackSmith should be removed on the map.
     *
     * @author Jerry Zhao
     */
    @Test
    void testBlacksmithTalk() {
        inventory = new Inventory();
        map = new Map();
        NPC.Blacksmith blacksmith = new NPC.Blacksmith("Blacksmith", 0, 0);
        blacksmith.talk(map, 0, 0, inventory);
        assertNotNull(inventory.getItem("Sword"), "Inventory should contain a 'Sword'");
        assertNull(map.getEntityAt(0, 0), "Blacksmith should be removed from the map");
    }

    /**
     * Test the interaction with Thief NPC when player has only 'Sword'.
     * Expected outcome: The inventory's size should be 1 after talking with Thief,
     *                      and Thief should be removed on the map.
     *
     * @author Jerry Zhao
     */
    @Test
    void testThiefTalkWeapon() {
        inventory = new Inventory();
        map = new Map();
        inventory.addItem(new Item("Sword", 0, 0,ItemType.Sword));
        NPC.Thief thief = new NPC.Thief("Thief", 0, 0);
        thief.talk(map, 0, 0, inventory);
        assertEquals(1, inventory.getItems().size(), "Inventory should be empty after the thief's visit");
        assertNull(map.getEntityAt(0, 0), "Thief should be removed from the map");
    }

    /**
     * Test the interaction with Thief NPC when player has a single item (except sword).
     * Expected outcome: The inventory's size should be 0 after talking with Thief,
     *                      and Thief should be removed on the map.
     *
     * @author Jerry Zhao
     */
    @Test
    void testThiefTalkSingle() {
        inventory = new Inventory();
        map = new Map();
        inventory.addItem(new Item("Coin", 0, 0,ItemType.Gold));
        NPC.Thief thief = new NPC.Thief("Thief", 0, 0);
        thief.talk(map, 0, 0, inventory);
        assertEquals(0, inventory.getItems().size(), "Inventory should be empty after the thief's visit");
        assertNull(map.getEntityAt(0, 0), "Thief should be removed from the map");
    }

    /**
     * Test the interaction with Thief NPC when player has multiple items.
     * Expected outcome: The inventory's size should only contain sword after talking with Thief,
     *                      and Thief should be removed on the map.
     *
     * @author Jerry Zhao
     */
    @Test
    void testThiefTalkMultiple() {
        inventory = new Inventory();
        map = new Map();
        inventory.addItem(new Item("Coin", 0, 0,ItemType.Gold));
        inventory.addItem(new Item("Belt", 0, 1,ItemType.Armor));
        inventory.addItem(new Item("Sword", 1, 0,ItemType.Sword));
        NPC.Thief thief = new NPC.Thief("Thief", 1, 0);
        thief.talk(map, 1, 0, inventory);
        assertEquals(1, inventory.getItems().size(), "Inventory should only contain sword after the thief's visit");
        assertNull(map.getEntityAt(1, 0), "Thief should be removed from the map");
    }

    /**
     * Test the interaction with Dwarf NPC.
     * Expected outcome: The inventory should contain a 'gold' after talking with Dwarf,
     *                      and Dwarf should be removed on the map.
     *
     * @author Jerry Zhao
     */
    @Test
    void testDwarfTalk() {
        inventory = new Inventory();
        map = new Map();
        NPC.Dwarf dwarf = new NPC.Dwarf("Dwarf", 0, 0);
        dwarf.talk(map, 0, 0, inventory);
        assertNotNull(inventory.getItem("gold"), "Inventory should contain a 'gold'");
        assertNull(map.getEntityAt(0, 0), "Dwarf should be removed from the map");
    }
}
