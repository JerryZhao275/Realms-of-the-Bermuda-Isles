import java.util.ArrayList;

/**
 * The NPC class represents non-playable characters (NPCs) in the game.
 * NPCs have names and specific interactions with the player character.
 * This class defines generic NPC behavior and contains inner classes
 * for specific types of NPCs like Blacksmith, Thief, and Dwarf.
 *
 *
 */

public abstract class NPC extends Entity {

    /**
     * Public constructor to initialize the NPC instance.
     * It creates a new name, x-axis coordinate, and y-axis coordinate for the NPC.
     * @param name Name of the NPC.
     * @param x X-axis coordinate.
     * @param y Y-axis coordinate.
     */
    public NPC(String name, int x, int y) {
        super(name, x, y);
    }

    /**
     * Abstract method to facilitate conversation with the NPC and potentially modify the player's inventory.
     * Each NPC subclass will have its own implementation.
     *
     * @param inventory Player's inventory.
     * @return Message to the player after interaction.
     */
    public abstract String talk(Map map, int row, int column, Inventory inventory);

    /**
     * A Blacksmith NPC that offers a sword to the player.
     */
    public static class Blacksmith extends NPC {
        public Blacksmith(String name, int x, int y) {
            super(name, x, y);
        }

        @Override
        public String talk(Map map, int row, int column, Inventory inventory) {
            System.out.println("Ah, a wanderer! You could use a sword to protect these treacherous lands");
            Item sword = new Item("sword", -1, -1);
            inventory.addItem(sword);
            map.removeEntity(row,column);
            System.out.println("Use it wisely. The Bermuda Isles are no place for the unprepared");
            return "# The blacksmith left you a sharp sword. A 'Sword' has been added to your inventory.";
        }
    }

    /**
     * A Thief NPC that warns the player and potentially steals from them.
     */
    public static class Thief extends NPC {
        public Thief(String name, int x, int y) {
            super(name, x, y);
        }

        @Override
        public String talk(Map map, int row, int column, Inventory inventory) {
            System.out.println("Psst, newcomer, watch your pockets!");
            for (Item item : new ArrayList<>(inventory.getItems())) {
                if (!item.getName().equalsIgnoreCase("sword")) {
                    inventory.removeItem(item);
                }
            }
            map.removeEntity(row,column);
            System.out.println("Thanks for the loot! Better luck next time!");
            return "# The thief took your (item being stolen) and escaped.";
        }
    }

    /**
     * A Dwarf NPC that offers a sack of gold to the player.
     */
    public static class Dwarf extends NPC {
        public Dwarf(String name, int x, int y) {
            super(name, x, y);
        }

        @Override
        public String talk(Map map, int row, int column, Inventory inventory) {
            System.out.println("Greetings, traveller. I've been on this island for a time unknown. " +
                    "Take this sack of Gold; it may aid you on your journey.");
            Item gold = new Item("Sack of Gold", -1, -1);
            inventory.addItem(gold);
            return "Gold might not have much value here, but it's a start. Seek the exit, and may fortune favor you";
        }
    }
}
