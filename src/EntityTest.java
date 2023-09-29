import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a JUnit test class responsible for covering all tests for the Entity class.
 *
 * @author Jerry Zhao
 */
public class EntityTest {

    /**
     * Tests the basic constructor and 'get' method in entity class.
     * Expected outcome: Constructed entity should have correct name and coordinates.
     *
     * @author Jerry Zhao
     */
    @Test
    void testConstructorAndGetters() {
        Entity entity = new Entity("NPC", 0, 0);

        assertEquals("NPC", entity.getName(), "Name should be 'TestEntity'");
        assertEquals(0, entity.getX(), "X-coordinate should be 0");
        assertEquals(0, entity.getY(), "Y-coordinate should be 0");
    }

    /**
     * Tests the 'set' method in entity class.
     * Expected outcome: The coordinate should be updated correctly using 'set' method.
     *
     * @author Jerry Zhao
     */
    @Test
    void testSetters() {
        Entity entity = new Entity("Enemy", 0, 0);

        entity.setX(1);
        entity.setY(1);

        assertEquals(1, entity.getX(), "X-coordinate should be 1 after setting");
        assertEquals(1, entity.getY(), "Y-coordinate should be 1 after setting");
    }

    /**
     * Tests the 'move' method in entity class.
     * Expected outcome: The coordinate should be updated correctly after a 'move' action.
     *
     * @author Jerry Zhao
     */
    @Test
    void testMove() {
        Entity entity = new Entity("Item", 0, 1);

        entity.move(0, 1);

        assertEquals(0, entity.getX(), "X-coordinate should be 0 after moving");
        assertEquals(1, entity.getY(), "Y-coordinate should be 1 after moving");
    }

    /**
     * Tests the 'toString' method in entity class.
     * Expected outcome: The returned string should match the name of the entity.
     *
     * @author Jerry Zhao
     */
    @Test
    void testToString() {
        Entity entity = new Entity("Item", 0, 1);
        assertEquals("Item", entity.toString());
    }
}
