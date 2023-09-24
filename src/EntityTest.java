import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {

    @Test
    void testConstructorAndGetters() {
        Entity entity = new Entity("NPC", 0, 0);

        assertEquals("NPC", entity.getName(), "Name should be 'TestEntity'");
        assertEquals(0, entity.getX(), "X-coordinate should be 0");
        assertEquals(0, entity.getY(), "Y-coordinate should be 0");
    }

    @Test
    void testSetters() {
        Entity entity = new Entity("Enemy", 0, 0);

        entity.setX(1);
        entity.setY(1);

        assertEquals(1, entity.getX(), "X-coordinate should be 1 after setting");
        assertEquals(1, entity.getY(), "Y-coordinate should be 1 after setting");
    }

    @Test
    void testMove() {
        Entity entity = new Entity("Item", 0, 1);

        entity.move(0, 1);

        assertEquals(0, entity.getX(), "X-coordinate should be 0 after moving");
        assertEquals(1, entity.getY(), "Y-coordinate should be 1 after moving");
    }
}
