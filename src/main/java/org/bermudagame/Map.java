package org.bermudagame;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This class represents a square map, that is the space for the player to explore.
 * It handles the existence of entities on each coordinate.
 * The map provides function on placing, removing or retrieving entities from specific coordinates.
 *
 * @author Kwong Yu Zhou
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public class Map {
    private Entity[][] grid;

//   Getter method to allow JSON saving and loading
    public Entity[][] getGrid() {
        return grid;
    }

    /**
     * Public constructor to initialize the Map instance with predefined entities like enemies, items, and NPCs.
     * The map is initialized with a default size of 4 * 4.
     * It creates a new grid, which is a nxn entity array.
     *
     * @author Kwong Yu Zhou
     *
     */
    public Map() {
        int mapSize = 4;
        grid = new Entity[mapSize][mapSize];
        Entity goblin = new Enemy("goblin", -1, -1);
        Entity spider = new Enemy("spider", -1, -1);
        Entity ogre = new Enemy("ogre", -1, -1);
        Entity boss = new Enemy("boss", -1, -1);

        Entity potion = new Item("potion", -1, -1,ItemType.Potion);
        Entity armor = new Item("armor", -1, -1,ItemType.Armor);
        Entity sword = new Item("sword", -1, -1,ItemType.Sword);
        Entity bow = new Item("bow", -1, -1,ItemType.Bow);
        Entity gold = new Item("gold", -1, -1,ItemType.Gold);

        Entity blacksmith = new NPC.Blacksmith("blacksmith", -1, -1);
        Entity thief = new NPC.Thief("thief", -1, -1);
        Entity dwarf = new NPC.Dwarf("dwarf", -1, -1);
        Entity merchant = new NPC.Merchant("merchant", -1, -1);

        placeEntity(spider,1,0);
        placeEntity(potion,2,0);
        placeEntity(armor,3,0);

        Entity[][] entities = { // row 1-3
                {thief, dwarf, ogre, gold},
                {goblin, gold, merchant, goblin},
                {blacksmith, goblin, spider, boss}
        };

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 4; col++) {
                placeEntity(entities[row][col], col, row+1);
            }
        }
    }

    /**
     * Set the entity's position and add it to the grid.
     *
     * @author Kwong Yu Zhou
     *
     * @param entity The entity to be placed.
     * @param row    The row position on the grid.
     * @param col    The column position on the grid.
     */
    public void placeEntity(Entity entity, int row, int col) {
        entity.move(row, col);
        grid[row][col] = entity;
    }

    /**
     * Remove the entity from the given position.
     *
     * @author Kwong Yu Zhou
     *
     * @param row    The row position from which to remove on the grid.
     * @param col    The column position from which to remove on the grid.
     */
    public void removeEntity(int row, int col) {
        if (isValidPosition(row, col)) {
            grid[row][col] = null;
        }
    }

    /**
     * Check the entity from the given position.
     *
     * @author Kwong Yu Zhou
     *
     * @param row the given row position on the grid
     * @param col the given column position on the grid
     * @return True if the position is valid, otherwise false.
     */
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid.length;
    }

    /**
     * Get the type of entity at a specific position in the grid
     *
     * @author Kwong Yu Zhou
     *
     * @param row the row position on the grid
     * @param col the column position on the grid
     * @return A string indicating the type of entity.
     */
    public String getEntityTypeAt(int row, int col) {
        if (row >= 0 && row < grid.length && col >= 0 && col < grid[row].length && grid[row][col] != null) {
            if (grid[row][col] instanceof Enemy) {
                return "Enemy";
            } else if (grid[row][col] instanceof NPC) {
                return "NPC";
            } else if (grid[row][col] instanceof Item) {
                return "Item";
            }
            else {
                return "Unknown Entity";
            }
        } else {
            return "Empty Space";
        }
    }

    /**
     * Get the actual entity object at a specific position in the grid
     *
     * @author Thomas Green
     *
     * @param row the row position on the grid
     * @param col the column position on the grid
     * @return A string indicating actual entity if it exists, otherwise return null.
     */
    public Entity getEntityAt(int row, int col) {
        if (isValidPosition(row, col)) {
            Entity entity = grid[row][col];
            return entity;
        }
        return null;
    }
}
