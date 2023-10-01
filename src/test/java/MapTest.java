import org.bermudagame.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a JUnit test class responsible for covering all tests for the Map class.
 *
 * @author Jerry Zhao
 */
public class MapTest {

    private Map map;

    /**
     * Test whether an entity is correctly placed on the map.
     * Expected outcome: The specified location should contain the placed entity.
     *
     * @author Jerry Zhao
     */
    @Test
    void testPlaceEntity() {
        map = new Map();
        Entity entity = new NPC.Blacksmith("Blacksmith", 0, 0);
        map.placeEntity(entity, 0, 0);
        assertEquals(entity, map.getEntityAt(0, 0));
    }

    /**
     * Test whether an entity is correctly removed on the map.
     * Expected outcome: The specified location should be empty after removal.
     *
     * @author Jerry Zhao
     */
    @Test
    void testRemoveEntity() {
        map = new Map();
        Entity entity = new Item("Item", 1, 0, ItemType.Gold);
        map.placeEntity(entity, 1, 0);
        map.removeEntity(1, 0);
        assertNull(map.getEntityAt(1, 0));
    }

    /**
     * Test default entity type at empty space.
     * Expected outcome: An empty space should return "Empty Space" as its entity type.
     *
     * @author Jerry Zhao
     */
    @Test
    void testGetEntityTypeEmptySpace() {
        map = new Map();
        assertEquals("Empty Space", map.getEntityTypeAt(0, 0));
    }

    /**
     * Test getting an entity at out-of-bound position.
     * Expected outcome: Method should return null when trying to get out-of-bound position.
     *
     * @author Jerry Zhao
     */
    @Test
    void testGetEntityOutOfBounds() {
        map = new Map();
        assertNull(map.getEntityAt(-4, -4));
    }

    /**
     * Test type of NPC on the map.
     * Expected outcome: The NPC entity should return "NPC" as its type.
     *
     * @author Jerry Zhao
     */
    @Test
    void testGetEntityTypeNPC() {
        map = new Map();
        Entity entity = new NPC.Blacksmith("Blacksmith", 0, 0);
        map.placeEntity(entity, 0, 0);
        assertEquals("NPC", map.getEntityTypeAt(0, 0));
    }

    /**
     * Test type of enemy on the map.
     * Expected outcome: The enemy entity should return "Enemy" as its type.
     *
     * @author Jerry Zhao
     */
    @Test
    void testGetEntityTypeEnemy() {
        map = new Map();
        Entity entity = new Enemy("Enemy", 1, 1);
        map.placeEntity(entity, 1, 1);
        assertEquals("Enemy", map.getEntityTypeAt(1, 1));
    }

    /**
     * Test type of item on the map.
     * Expected outcome: The item entity should return "Item" as its type.
     *
     * @author Jerry Zhao
     */
    @Test
    void testGetEntityTypeItem() {
        map = new Map();
        Entity entity = new Item("Sword", 1, 1, ItemType.Sword);
        map.placeEntity(entity, 1, 1);
        assertEquals("Item", map.getEntityTypeAt(1, 1));
    }

    /**
     * Test the type of unknown entity on the map.
     * Expected outcome: The unknown entity should return "Unknown Entity" as its type.
     *
     * @author Jerry Zhao
     */
    @Test
    void testGetEntityTypeOther() {
        map = new Map();
        Entity entity = new Entity("null", 1, 1);
        map.placeEntity(entity, 1, 1);
        assertEquals("Unknown Entity", map.getEntityTypeAt(1, 1));
    }
}
