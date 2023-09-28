package org.bermudagame;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This class represents the entity of the non-playable roles of the game (Enemy/NPC/Item).
 * It handles the character's name and its coordinate related to the map.
 *
 * @author Kwong Yu Zhou
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Entity {
    private String name;
    private int x;
    private int y;

    /**
     * Public constructor to initialize the Entity instance.
     * It creates a new name, x-axis coordinate, and y-axis coordinate.
     *
     * @author Kwong Yu Zhou
     *
     * @param name The name of the entity
     * @param x    The x-coordinate of the entity on the map.
     * @param y    The y-coordinate of the entity on the map.
     */
    public Entity(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    //    Empty Constructor to allow JSON mapping
    public Entity() {}

    /**
     * Get the name of the Entity.
     *
     * @author Kwong Yu Zhou
     *
     * @return The Entity name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the x-axis coordinate of the Entity.
     *
     * @author Kwong Yu Zhou
     *
     * @return The x-axis coordinate of the Entity.
     */
    public int getX() {
        return x;
    }

    /**
     * Set the y-axis coordinate of the Entity.
     *
     * @author Kwong Yu Zhou
     *
     * @return The y-axis coordinate of the Entity.
     */
    public int getY() {
        return y;
    }

    /**
     * Set the x-axis coordinate of the Entity.
     *
     * @author Kwong Yu Zhou
     *
     * @param x  The x-axis coordinate of the Entity.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set the y-axis coordinate of the Entity.
     *
     * @author Kwong Yu Zhou
     *
     * @param y  The y-axis coordinate of the Entity.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Set a new (x,y) coordinate for the Entity.
     *
     * @author Kwong Yu Zhou
     *
     * @param newX  The new x-axis coordinate of the Entity.
     * @param newY  The new y-axis coordinate of the Entity.
     *
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

