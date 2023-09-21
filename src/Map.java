/**
 * This class represents a square map, that is the space for the player to explore.
 * It handles the existence of entities on each coordinate.
 *
 * @author Kwong Yu Zhou
 */
public class Map {
    private Entity[][] grid;

    /**
     * Public constructor to initialize the Map instance.
     * It creates a new grid, which is a nxn entity array.
     */
    public Map() {
//        int mapSize = 2;
//        grid = new Entity[mapSize][mapSize];
        grid = new Entity[2][2];
        Entity enemy = new Enemy("Enemy", -1, -1);
        Entity npc = new NPC("NPC", -1, -1);
        Entity item = new Item("Item", -1, -1);

        placeEntity(item,0,1);
        placeEntity(npc,1,0);
        placeEntity(enemy,1,1);
    }

    /**
     * Set the entity's position and add it to the grid.
     */
    private void placeEntity(Entity entity, int row, int col) {
        entity.move(row, col);
        grid[row][col] = entity;
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
     * @return the position is valid on the grid
     */
    private boolean isValidPosition(int row, int col) {
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
            } else {
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

    public static void main(String[] args) {
        Map gameMap = new Map();

        // Test: Print and display the skeleton map
        int row = 0;
        int col = 0;
        gameMap.printMap();
        String entityType0 = gameMap.getEntityTypeAt(row, col);
        System.out.println("Entity at grid[" + row + "][" + col + "]: " + entityType0);
        col = 1;
        String entityType1 = gameMap.getEntityTypeAt(row, col);
        System.out.println("Entity at grid[" + row + "][" + col + "]: " + entityType1);
        row = 1;
        col = 0;
        String entityType2 = gameMap.getEntityTypeAt(row, col);
        System.out.println("Entity at grid[" + row + "][" + col + "]: " + entityType2);
        col = 1;
        String entityType3 = gameMap.getEntityTypeAt(row, col);
        System.out.println("Entity at grid[" + row + "][" + col + "]: " + entityType3);
    }
}

