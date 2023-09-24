import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final List<Item> items = new ArrayList<>();


    /**
     * @param name name of item to get in inventory
     * @return the first item with a matching name, null if the item doesn't exist.
     */
    public Item getItem(String name) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {return item;}
        }
        return null;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }


}
