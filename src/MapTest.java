import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a JUnit test class responsible for covering all tests for the Map class.
 *
 * @author Jerry Zhao
 */
public class MapTest {

    private Map map;

    @Test
    void testPlaceEntity() {
        map = new Map();
        Entity entity = new NPC.Blacksmith("Blacksmith", 0, 0);
        map.placeEntity(entity, 0, 0);
        assertEquals(entity, map.getEntityAt(0, 0));
    }

    @Test
    void testRemoveEntity() {
        map = new Map();
        Entity entity = new Item("Item", 1, 0,ItemType.Gold);
        map.placeEntity(entity, 1, 0);
        map.removeEntity(1, 0);
        assertNull(map.getEntityAt(1, 0));
    }

    @Test
    void testGetEntityTypeEmptySpace() {
        map = new Map();
        assertEquals("Empty Space", map.getEntityTypeAt(0, 0));
    }

    @Test
    void testGetEntityOutOfBounds() {
        map = new Map();
        assertNull(map.getEntityAt(-4, -4));
    }

    @Test
    void testGetEntityTypeNPC() {
        map = new Map();
        Entity entity = new NPC.Blacksmith("Blacksmith", 0, 0);
        map.placeEntity(entity, 0, 0);
        assertEquals("NPC", map.getEntityTypeAt(0, 0));
    }

    @Test
    void testGetEntityTypeEnemy() {
        map = new Map();
        Entity entity = new Enemy("Enemy", 1, 1);
        map.placeEntity(entity, 1, 1);
        assertEquals("Enemy", map.getEntityTypeAt(1, 1));
    }

    @Test
    void testGetEntityTypeItem() {
        map = new Map();
        Entity entity = new Item("Sword", 1, 1, ItemType.Sword);
        map.placeEntity(entity, 1, 1);
        assertEquals("Item", map.getEntityTypeAt(1, 1));
    }

    @Test
    void testGetEntityTypeOther() {
        map = new Map();
        Entity entity = new Entity("null", 1, 1);
        map.placeEntity(entity, 1, 1);
        assertEquals("Unknown Entity", map.getEntityTypeAt(1, 1));
    }


}
