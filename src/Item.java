public class Item extends Entity{
    // This is the initialisation of a java class in a source folder
    private int price;
    private int durability;
    private ItemType itemType;

    /**
     * Public constructor to initialize the Item instance.
     * It creates a new name, x-axis coordinate, and y-axis coordinate for the Item.
     */
    public Item(String name, int x, int y,ItemType itemType) {
        super(name, x, y);
        this.itemType = itemType;
        switch (itemType) {
            case Sword -> {this.price = 2; this.durability = 3;}
            case Bow -> {this.price = 3; this.durability = 3;}  // Example price
            case Potion -> {this.price = 1; this.durability = 2;}
            case Armor -> {this.price = 2;}
            case Gold -> {this.price = 1;}  // Each gold piece is worth 1
        }
    }

    //    Empty Constructor to allow JSON mapping
    public Item() {}

    // other attributes
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ItemType getItemType() {
        return itemType;
    }
    public int getDurability() {
        return durability;
    }


    /**
     * Decrease the durability of a valid item, removing it from the players inventory if the item is broken.
     *
     * @param inventory inventory the item is contained in.
     * @author Sam Powell
     */
    public void use(Inventory inventory) {
        if (itemType == ItemType.Armor || itemType == ItemType.Gold) {
            System.out.println("You cannot use this item");
            return;
        }
        switch (durability) {
            case 0 -> System.out.println("This item appears to be broken");
            case 1 -> {
                System.out.println("You feel the " + getName() + " break in your hands, you will not be able to use this anymore.");
                durability -= 1;
                inventory.removeItem(this);
            }
            default -> {durability -= 1;}
        }
    }

    public boolean canUse() {
        return durability > 0;
    }
    public static Item ARMOR = new Item("Armor", 0, 0,ItemType.Armor);  // x and y set to 0 just for initialization.
    public static Item POTION = new Item("Potion", 0, 0,ItemType.Potion);
    public static Item SWORD = new Item("Sword", 0, 0,ItemType.Sword);

    static {
        ARMOR.setPrice(2);
        POTION.setPrice(1);
        SWORD.setPrice(2);
    }
}
