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

    @Test
    void testBlacksmithTalk() {
        inventory = new Inventory();
        map = new Map();
        NPC.Blacksmith blacksmith = new NPC.Blacksmith("Blacksmith", 0, 0);
        blacksmith.talk(map, 0, 0, inventory);
        assertNotNull(inventory.getItem("Sword"), "Inventory should contain a 'Sword'");
        assertNull(map.getEntityAt(0, 0), "Blacksmith should be removed from the map");
    }

    @Test
    void testThiefTalkWeapon() {
        inventory = new Inventory();
        map = new Map();
        inventory.addItem(new Item("Sword", 0, 0));
        NPC.Thief thief = new NPC.Thief("Thief", 0, 0);
        thief.talk(map, 0, 0, inventory);
        assertEquals(1, inventory.getItems().size(), "Inventory should be empty after the thief's visit");
        assertNull(map.getEntityAt(0, 0), "Thief should be removed from the map");
    }

    @Test
    void testThiefTalkSingle() {
        inventory = new Inventory();
        map = new Map();
        inventory.addItem(new Item("Coin", 0, 0));
        NPC.Thief thief = new NPC.Thief("Thief", 0, 0);
        thief.talk(map, 0, 0, inventory);
        assertEquals(0, inventory.getItems().size(), "Inventory should be empty after the thief's visit");
        assertNull(map.getEntityAt(0, 0), "Thief should be removed from the map");
    }

    @Test
    void testThiefTalkMultiple() {
        inventory = new Inventory();
        map = new Map();
        inventory.addItem(new Item("Coin", 0, 0));
        inventory.addItem(new Item("Belt", 0, 1));
        inventory.addItem(new Item("Sword", 1, 0));
        NPC.Thief thief = new NPC.Thief("Thief", 1, 0);
        thief.talk(map, 1, 0, inventory);
        assertEquals(1, inventory.getItems().size(), "Inventory should only contain sword after the thief's visit");
        assertNull(map.getEntityAt(1, 0), "Thief should be removed from the map");
    }

    @Test
    void testDwarfTalk() {
        inventory = new Inventory();
        map = new Map();
        NPC.Dwarf dwarf = new NPC.Dwarf("Dwarf", 0, 0);
        dwarf.talk(map, 0, 0, inventory);
        assertNotNull(inventory.getItem("Sack of Gold"), "Inventory should contain a 'Sack of Gold'");
        assertNull(map.getEntityAt(0, 0), "Dwarf should be removed from the map");
    }
}
