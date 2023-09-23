import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NPCTest {

    private Inventory inventory;

    @Test
    void testBlacksmithTalk() {
        inventory = new Inventory();
        NPC.Blacksmith blacksmith = new NPC.Blacksmith("Blacksmith", 0, 0);
        blacksmith.talk(inventory);
        assertNotNull(inventory.getItem("Sword"), "Inventory should contain a 'Sword'");
    }

    @Test
    void testThiefTalkWeapon() {
        inventory = new Inventory();
        inventory.addItem(new Item("Sword", 0, 0));
        NPC.Thief thief = new NPC.Thief("Thief", 0, 0);
        thief.talk(inventory);
        assertEquals(1, inventory.getItems().size(), "Inventory should be empty after the thief's visit");
    }
    @Test
    void testThiefTalkSingle() {
        inventory = new Inventory();
        inventory.addItem(new Item("Coin", 0, 0));
        NPC.Thief thief = new NPC.Thief("Thief", 0, 0);
        thief.talk(inventory);
        assertEquals(0, inventory.getItems().size(), "Inventory should be empty after the thief's visit");
    }

    @Test
    void testThiefTalkMultiple() {
        inventory = new Inventory();
        inventory.addItem(new Item("Coin", 0, 0));
        inventory.addItem(new Item("Belt", 0, 0));
        inventory.addItem(new Item("Sword", 0, 0));
        NPC.Thief thief = new NPC.Thief("Thief", 0, 0);
        thief.talk(inventory);
        assertEquals(1, inventory.getItems().size(), "Inventory should only contain sword after the thief's visit");
    }

    @Test
    void testDwarfTalk() {
        inventory = new Inventory();
        NPC.Dwarf dwarf = new NPC.Dwarf("Dwarf", 0, 0);
        dwarf.talk(inventory);
        assertNotNull(inventory.getItem("Sack of Gold"), "Inventory should contain a 'Sack of Gold'");
    }
}
