import java.util.*;

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
            Item sword = new Item("sword", -1, -1, ItemType.Sword);
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
            if (inventory.getItems().isEmpty()) {
                System.out.println("Hmm, you have nothing worth stealing!");
                map.removeEntity(row, column);
                return "# The thief found nothing and quickly disappeared.";
            }

            System.out.println("Psst, newcomer, watch your pockets!");
            List<Item> stolenItems = new ArrayList<>();
            for (Item item : new ArrayList<>(inventory.getItems())) {
                if (!item.getName().equalsIgnoreCase("sword")) {
                    stolenItems.add(item);
                    inventory.removeItem(item);
                }
            }

            map.removeEntity(row, column);
            System.out.println("Thanks for the loot! Better luck next time!");

            if (stolenItems.isEmpty()) {
                return "# The thief took nothing and escaped.";
            } else {
                return "# The thief took your " + listItems(stolenItems) + " and escaped.";
            }
        }

        private String listItems(List<Item> items) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < items.size(); i++) {
                sb.append(items.get(i).getName());
                if (i < items.size() - 2) {
                    sb.append(", ");
                } else if (i == items.size() - 2) {
                    sb.append(" and ");
                }
            }
            return sb.toString();
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
            Item gold = new Item("Sack of Gold", -1, -1,ItemType.Gold);
            inventory.addItem(gold);
            map.removeEntity(row,column);
            System.out.println("Gold might not have much value here, but it's a start. Seek the exit, and may fortune favor you");
            return "# The Dwarf left you a piece of gold. A piece of 'gold' has been added to your inventory.";
        }
    }



    public static class Merchant extends NPC {
        private List<Item> itemsForSale;

        public Merchant(String name, int x, int y) {
            super(name, x, y);
            this.itemsForSale = itemsForSale;
            Item armor = new Item("armor", -1, -1,ItemType.Armor);
            armor.setPrice(2);

            Item potion = new Item("potion", -1, -1,ItemType.Potion);
            potion.setPrice(1);

            Item sword = new Item("sword", -1, -1,ItemType.Sword);
            sword.setPrice(2);

            this.itemsForSale = new ArrayList<>(Arrays.asList(armor, potion, sword));

        }

        public String talk(Map map, int row, int column, Inventory inventory) {
            while (true) {
                System.out.println("Greetings, traveler! Here are my wares:");
                for (Item item : itemsForSale) {
                    System.out.println(item.getName() + ": " + item.getPrice() + " gold");
                }

                System.out.print("What would you like to buy? (Enter 'exit' to leave) > ");
                Scanner scanner = new Scanner(System.in);
                String itemName = scanner.nextLine();

                if ("exit".equalsIgnoreCase(itemName)) {
                    return "# Farewell, traveler!";
                }

                boolean itemFound = false;
                for (Item item : new ArrayList<>(itemsForSale)) {  // Create a copy of itemsForSale for safe iteration and removal
                    if (item.getName().equalsIgnoreCase(itemName)) {
                        itemFound = true;
                        int goldCount = inventory.getItemCount("gold");
                        if (item.getPrice() <= goldCount) {
                            // Remove gold pieces from inventory
                            for (int i = 0; i < item.getPrice(); i++) {
                                inventory.removeItem(inventory.getItem("gold"));
                            }
                            // Add the purchased item to player's inventory
                            inventory.addItem(item);
                            // Remove the purchased item from merchant's inventory
                            itemsForSale.remove(item);
                            System.out.println("# You bought a " + itemName + ".");
                        } else {
                            System.out.println("You don't have enough gold!");
                        }
                        break;  // Exit the loop once the item is found
                    }
                }

                if (!itemFound) {
                    System.out.println("I don't have that item for sale. Please choose a valid item.");
                }

                if (itemsForSale.isEmpty()) {
                    map.removeEntity(row, column);
                    return "# Thank you for your purchases! I have nothing more to sell.";
                }
            }
        }
    }
}
