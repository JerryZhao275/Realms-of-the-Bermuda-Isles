package org.bermudagame;

import java.util.*;


/**
 * The NPC (Non-Playable Character) class serves as a foundation for the various types of NPCs present in the game.
 * Each type of NPC has unique interactions and behaviors that influence the player and the game .
 * <p>
 * This class primarily acts as an abstract base, with subclasses providing specific implementations for each NPC type.
 * </p>
 *
 * @author Thomas Green
 */


public abstract class NPC extends Entity {

    /**
     * Constructs an NPC with the specified name and coordinates.
     *
     * @author Thomas Green
     *
     * @param name The name of the NPC.
     * @param x    The x-coordinate of the NPC.
     * @param y    The y-coordinate of the NPC.
     *
     */

    public NPC(String name, int x, int y) {
        super(name, x, y);
    }
    //    Empty Constructor to allow JSON mapping
    public NPC() {}

    /**
     * Engages the player in a conversation or interaction. The nature and outcome of this interaction
     * varies based on the NPC's type and can potentially modify the player's inventory or the game world.
     *
     * @author Thomas Green
     *
     * @param map       The game map.
     * @param row       The row-coordinate of the NPC on the map.
     * @param column    The column-coordinate of the NPC on the map.
     * @param inventory The player's current inventory.
     *
     * @return A message to the player describing the result of the interaction.
     */

    public abstract String talk(Map map, int row, int column, Inventory inventory);

    /**
     * Represents a Blacksmith NPC who offers weaponry to players.
     * <p>
     * When interacted with, the Blacksmith provides the player with a sword.
     * </p>
     *
     * @author Thomas Green
     */

    public static class Blacksmith extends NPC {

        /**
         * Constructs a Blacksmith NPC with the specified name and coordinates.
         *
         * @author Thomas Green
         *
         * @param name The name of the Blacksmith.
         * @param x    The x-coordinate of the Blacksmith's location.
         * @param y    The y-coordinate of the Blacksmith's location.
         */

        public Blacksmith(String name, int x, int y) {
            super(name, x, y);
        }
        //    Empty Constructor to allow JSON mapping
        public Blacksmith() {}


        /**
         * Allows the player to interact with the Blacksmith, resulting in the Blacksmith offering a sword.
         * After the interaction, the Blacksmith is removed from the map.
         *
         * @author Thomas Green
         *
         * @param map       The game map where entities, including the Blacksmith, are located.
         * @param row       The row coordinate of the Blacksmith on the map.
         * @param column    The column coordinate of the Blacksmith on the map.
         * @param inventory The player's inventory where items are stored.
         * @return A message to the player describing the outcome of the interaction.
         */

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
     * Represents a Thief NPC who will steal all the items from players except the sword.
     * <p>
     * When interacted with, the Thief scans the player's inventory and steals any item, sparing only the sword.
     * The Thief then disappears from the game map.
     * </p>
     *
     * @author Thomas Green
     */

    public static class Thief extends NPC {
        /**
         * Constructs a Thief NPC with the specified name and coordinates.
         *
         * @author Thomas Green
         *
         * @param x    The x-coordinate of the Thief's location.
         * @param y    The y-coordinate of the Thief's location.
         */

        public Thief(String name, int x, int y) {
            super("stranger", x, y);
        }
        //    Empty Constructor to allow JSON mapping
        public Thief() {}

        /**
         * Interacts with the Thief NPC, resulting in potential theft from the player's inventory.
         * If the inventory is empty or only contains a sword, the Thief leaves without taking anything.
         * After the interaction, the Thief disappears from the game map.
         *
         * @author Thomas Green
         *
         * @param map       The game map where entities, including the Thief, are located.
         * @param row       The row coordinate of the Thief on the map.
         * @param column    The column coordinate of the Thief on the map.
         * @param inventory The player's inventory which is subject to theft by the Thief.
         * @return A message to the player describing the outcome of the interaction.
         */

        @Override
        public String talk(Map map, int row, int column, Inventory inventory) {
            System.out.println("Hey there! It's not often I see new faces around here. Stay safe and watch your belongings!");

            if (inventory.getItems().isEmpty()) {
                map.removeEntity(row, column);
                return "# After the brief conversation, the stranger quickly disappeared.";
            }

            List<Item> stolenItems = new ArrayList<>();
            for (Item item : new ArrayList<>(inventory.getItems())) {
                if (!item.getName().equalsIgnoreCase("sword")) {
                    stolenItems.add(item);
                    inventory.removeItem(item);
                }
            }

            map.removeEntity(row, column);

            if (stolenItems.isEmpty()) {
                return "# After the brief conversation, the stranger quickly disappeared.";
            } else {
                return "# You figured that your " + listItems(stolenItems) + " is missing. You turned around to look for the stranger "
                        +  ". He/She’s already gone, leaving no trace. That guy was a THIEF!";
            }
        }


        /**
         * Utility method to format the list of stolen items into a readable string format.
         *
         * @author Thomas Green
         *
         * @param items A list of items that the Thief stole from the player.
         * @return A formatted string listing all the stolen items.
         */

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
     * Represents a Dwarf NPC who will give item 'Gold' into player's inventory
     * </p>
     *
     * @author Thomas Green
     */

    public static class Dwarf extends NPC {

        /**
         * Constructs a Dwarf NPC with the specified name and coordinates.
         *
         * @author Thomas Green
         *
         * @param name The name of the Dwarf
         * @param x    The x-coordinate of the Dwarf's location.
         * @param y    The y-coordinate of the Dwarf's location.
         */

        public Dwarf(String name, int x, int y) {
            super(name, x, y);
        }
        //    Empty Constructor to allow JSON mapping
        public Dwarf() {}
        /**
         * Represents the interaction between the player and the Dwarf NPC.
         * When the player interacts with the Dwarf, the Dwarf offers them some gold.
         * This method will add the 'Gold' item to the player's inventory, remove the Dwarf
         * from the map after the interaction, and provide feedback to the player about the interaction's outcome.
         * </p>
         *
         * @author Thomas Green
         *
         * @param map       The game map where entities, including the Dwarf, are located.
         * @param row       The row coordinate of the Dwarf on the map.
         * @param column    The column coordinate of the Dwarf on the map.
         * @param inventory The player's inventory.
         * @return A message to the player describing the outcome of the interaction.
         */
        @Override
        public String talk(Map map, int row, int column, Inventory inventory) {
            System.out.println("Greetings, traveller. I've been on this island for a time unknown. " +
                    "Take some gold; it may aid you on your journey.");
            Item gold = new Item("gold", -1, -1,ItemType.Gold);
            inventory.addItem(gold);
            map.removeEntity(row,column);
            System.out.println("Gold might not have much value here, but it's a start. Seek the exit, and may fortune favor you");
            return "# The Dwarf left you a piece of gold. A piece of 'gold' has been added to your inventory.";
        }
    }




    /**
     * Represents a Merchant NPC, a character in the game from whom the player can buy items.
     * The Merchant offers armor,potion and sword for sale and players can use in-game gold to make purchases.
     * Interactions with the Merchant allow players to enhance their inventory, provided they have enough gold.
     * </p>
     *
     * @author Thomas Green
     */

    public static class Merchant extends NPC {
        private List<Item> itemsForSale;

        /**
         * Constructs a Merchant NPC with the specified name and coordinates.
         *
         * @author Thomas Green
         *
         * @param name The name of the Merchant
         * @param x    The x-coordinate of the Merchant's location.
         * @param y    The y-coordinate of the Merchant's location.
         */

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
        //    Empty Constructor to allow JSON mapping
        public Merchant() {}

        /**
         * Represents the interaction between the player and the Merchant NPC.
         * When the player interacts with the Merchant, the Merchant offers them a list of items available for sale.
         * This method will add the relevant item to the player's inventory, and remove the players' corresponding gold
         * for buying this item.
         * </p>
         *
         * @author Thomas Green
         *
         * @param map       The game map where entities, including the Merchant, are located.
         * @param row       The row coordinate of the Merchant on the map.
         * @param column    The column coordinate of the Merchant on the map.
         * @param inventory The player's inventory.
         * @return A message to the player describing the outcome of the interaction.
         */

        @Override
        public String talk(Map map, int row, int column, Inventory inventory) {
            while (true) {
                System.out.println("Greetings, traveler! Here are my wares:");
                for (Item item : itemsForSale) {
                    System.out.println(item.getName() + ": " + item.getPrice() + " gold");
                }

                System.out.print("What would you like to buy? Enter 'trade [item]' to trade or 'exit' to leave.");
                System.out.println("> ");
                Scanner scanner = new Scanner(System.in);
                String itemName = scanner.nextLine();

                if ("exit".equalsIgnoreCase(itemName)) {
                    return "# Farewell, traveler!";
                }

                boolean itemFound = false;
                for (Item item : new ArrayList<>(itemsForSale)) {
                    if (item.getName().equalsIgnoreCase(itemName)) {
                        itemFound = true;
                        int goldCount = inventory.getItemCount("gold");
                        if (item.getPrice() <= goldCount) {
                            for (int i = 0; i < item.getPrice(); i++) {
                                inventory.removeItem(inventory.getItem("gold"));
                            }

                            inventory.addItem(item);

                            itemsForSale.remove(item);
                            System.out.println("# You bought a " + itemName + ".");
                        } else {
                            System.out.println("You don't have enough gold!");
                        }
                        break;
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

        /**
         * Represents the interaction between the player and the Merchant NPC.
         * When the player interacts with the Merchant, the Merchant offers them a list of items available for sale.
         * This method will add a list of items player want to buy into the player's inventory,
         * and remove the players' corresponding gold for buying this item.
         *
         * @author Jerry Zhao
         *
         * @param map         The game map where the entities are located.
         * @param row         The row position of the merchant on the map.
         * @param column      The column position of the merchant on the map.
         * @param inventory   The player's current inventory.
         * @param listInputs  An array of strings representing the list of items the player intends to buy.
         *
         * @return A new array of strings containing any unprocessed commands after the interaction is completed.
         *
         */
        public String[] talk(Map map, int row, int column, Inventory inventory, String[] listInputs) {
            while (true) {
                System.out.println("Greetings, traveler! Here are my wares:");
                for (Item item : itemsForSale) {
                    System.out.println(item.getName() + ": " + item.getPrice() + " gold");
                }

                int lastInput = 0;
                for (int i = 0; i < listInputs.length; i++) {
                    lastInput = i+1;
                    String itemName = listInputs[i];

                    if ("exit".equalsIgnoreCase(itemName)) {
                        System.out.println("# Farewell, traveler!");
                    }

                    boolean itemFound = false;
                    for (Item item : new ArrayList<>(itemsForSale)) {  // Create a copy of itemsForSale for safe iteration and removal
                        if (item.getName().equalsIgnoreCase(itemName)) {
                            itemFound = true;
                            int goldCount = inventory.getItemCount("gold");
                            if (item.getPrice() <= goldCount) {
                                // Remove gold pieces from inventory
                                for (int j = 0; j < item.getPrice(); j++) {
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
                        System.out.println("# Thank you for your purchases! I have nothing more to sell.");
                    }
                }

                int newListSize = listInputs.length - lastInput;
                String[] newList = new String[newListSize];
                System.arraycopy(listInputs, lastInput, newList, 0, listInputs.length - lastInput);
                return newList;
            }
        }
    }
}
