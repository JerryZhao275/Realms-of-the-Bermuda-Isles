import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Arrays;
import java.util.List;

/**
 * This class represents a square map, that is the space for the player to explore.
 * It handles the existence of entities on each coordinate.
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
     * Public constructor to initialize the Map instance.
     * It creates a new grid, which is a nxn entity array.
     */
    public Map() {
        int mapSize = 4;
        grid = new Entity[mapSize][mapSize];
        Entity goblin = new Enemy("goblin", -1, -1);
        Entity spider = new Enemy("spider", -1, -1);
        Entity orge = new Enemy("orge", -1, -1);
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

        // row 0
        placeEntity(spider,1,0);
        placeEntity(potion,2,0);
        placeEntity(armor,3,0);

        Entity[][] entities = { // row 1-3
                {bow, dwarf, orge, gold},
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
     */
    public void placeEntity(Entity entity, int row, int col) {
        entity.move(row, col);
        grid[row][col] = entity;
        System.out.println("Placed " + entity.getName() + " at [" + row + "][" + col + "]");
    }


    /**
     * Remove the entity from the given position.
     */
    public void removeEntity(int row, int col) {
        if (isValidPosition(row, col)) {
            grid[row][col] = null;
        }
    }

    /**
     * Check the entity from the given position.
     *
     * @param row the given row position on the grid
     * @param col the given column position on the grid
     * @return the position is valid on the grid
     */
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid.length;
    }

    /**
     * Get the type of entity at a specific position in the grid
     *
     * @author Kwong Yu Zhou
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
     * Visualize the contents of the map, showing entity names or '-' for empty cells
     *
     * @author Kwong Yu Zhou
     */
    public void printMap() {
        for (int col = grid.length-1; col >= 0; col--) {
            for (Entity[] entities : grid) {
                if (entities[col] != null) {
                    // Display the first character of the entity's name
                    System.out.print(entities[col].getName().charAt(0) + " ");
                } else {
                    System.out.print("- "); // Display a '-' if empty
                }
            }
            System.out.println();
        }
    }


    public Entity getEntityAt(int row, int col) {
        if (isValidPosition(row, col)) {
            Entity entity = grid[row][col];
            System.out.println("Entity at [" + row + "][" + col + "]: " + entity);
            return entity;
        }
        System.out.println("Invalid position: [" + row + "][" + col + "]");
        return null;
    }


    public static void main(String[] args) {
        Map gameMap = new Map();

        // Test: Print and display the skeleton map
//        int row = 0;
//        int col = 0;
        gameMap.printMap();
//        String entityType0 = gameMap.getEntityTypeAt(row, col);
//        System.out.println("Entity at grid[" + row + "][" + col + "]: " + entityType0);
//        col = 1;
//        String entityType1 = gameMap.getEntityTypeAt(row, col);
//        System.out.println("Entity at grid[" + row + "][" + col + "]: " + entityType1);
//        row = 1;
//        col = 0;
//        String entityType2 = gameMap.getEntityTypeAt(row, col);
//        System.out.println("Entity at grid[" + row + "][" + col + "]: " + entityType2);
//        col = 1;
//        String entityType3 = gameMap.getEntityTypeAt(row, col);
//        System.out.println("Entity at grid[" + row + "][" + col + "]: " + entityType3);
    }
}

