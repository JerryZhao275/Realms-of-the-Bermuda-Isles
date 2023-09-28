import java.util.ArrayList;
import java.util.List;

/**
 * Represents an inventory, a collection where players can store items in the game.
 * Items can be added, removed, or got from the inventory.
 * This class provides methods to add,delete,change or search the contents of the inventory.
 * </p>
 *
 * @author
 */

public class Inventory {

    /**
     *  A list containing items in the inventory
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Get all the items from the inventory
     *
     * @author
     *
     * @return  item The list of items in the inventory
     *
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Get an item from the inventory
     *
     * @author
     *
     * @param name The name of item to get in inventory
     *
     * @return the first item with a matching name, null if the item doesn't exist.
     */
    public Item getItem(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {return item;}
        }
        return null;
    }

    /**
     * Add an item from the inventory
     *
     * @author
     *
     * @param item The name of item to add in inventory
     *
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Remove an item from the inventory
     *
     * @author
     *
     * @param item The name of item to remove in inventory
     *
     */
    public void removeItem(Item item) {
        items.remove(item);
    }

    /**
     * Count the number of items presented in the inventory
     *
     * @author
     *
     * @param itemName The name of item to count
     *
     * @return the count of item with a matching name, return 0 if the item doesn't exist.
     */
    public int getItemCount(String itemName) {
        int count = 0;
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                count++;
            }
        }
        return count;
    }



}
