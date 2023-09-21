/**
 * This class represents the entity of the non-playable roles of the game (Enemy/NPC/Item).
 * It handles the character's name and its coordinate related to the map.
 *
 * @author Kwong Yu Zhou
 */
public class Entity {
    private String name;
    private int x;
    private int y;

    /**
     * Public constructor to initialize the Entity instance.
     * It creates a new name, x-axis coordinate, and y-axis coordinate.
     */
    public Entity(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    /**
     * Get the name of the Entity.
     *
     * @return The Entity name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the x-axis coordinate of the Entity.
     *
     * @return The x-axis coordinate of the Entity.
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y-axis coordinate of the Entity.
     *
     * @return The y-axis coordinate of the Entity.
     */
    public int getY() {
        return y;
    }

    /**
     * Set a new x-axis coordinate for the Entity.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set a new y-axis coordinate for the Entity.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Set a new (x,y) coordinate for the Entity.
     */
    public void move(int newX, int newY) {
        x = newX;
        y = newY;
    }

    @Override
    public String toString() {
        return name;
    }
}

