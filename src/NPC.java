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
     */



        public NPC(String name, int x, int y) {
            super(name, x, y);
        }

        // 声明为抽象方法，让子类必须实现这个方法
        public abstract void talk();

    public abstract String interact(Player player);

    public static class Blacksmith extends NPC {
            public Blacksmith(String name, int x, int y) {
                super(name, x, y);
            }

            @Override
            public void talk() {
                System.out.println("Ah, a wanderer! You could use a sword to protect these treacherous lands");
            }

            @Override
            public String interact(Player player) {
                Item sword = new Item("Sword", -1, -1);
                player.getInventory().addItem(sword);
                return "Use it wisely. The Bermuda Isles are no place for the unprepared";
            }
        }

        public static class Thief extends NPC {
            public Thief(String name, int x, int y) {
                super(name, x, y);
            }

            @Override
            public void talk() {
                System.out.println("Psst, newcomer, watch your pockets!");
            }

            @Override
            public String interact(Player player) {
                Inventory playerInventory = player.getInventory();
                for (Item item : new ArrayList<>(playerInventory.getItems())) {
                    if (!item.getName().equalsIgnoreCase("sword")) {
                        playerInventory.removeItem(item);
                    }
                }
                return "Thanks for the loot! Better luck next time!";
            }
        }

        public static class Dwarf extends NPC {
            public Dwarf(String name, int x, int y) {
                super(name, x, y);
            }

            @Override
            public void talk() {
                System.out.println("Greetings, traveller. I've been on this island for a time unknown. " +
                        "Take this sack of Gold; it may aid you on your journey.");
            }

            @Override
            public String interact(Player player) {
                Item gold = new Item("Sack of Gold", -1, -1);
                player.getInventory().addItem(gold);
                return "Gold might not have much value here, but it's a start. Seek the exit, and may fortune favor you";
            }
        }
    }
