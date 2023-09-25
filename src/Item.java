public class Item extends Entity{
    // This is the initialisation of a java class in a source folder
    private int price;
    /**
     * Public constructor to initialize the Item instance.
     * It creates a new name, x-axis coordinate, and y-axis coordinate for the Item.
     */
    public Item(String name, int x, int y) {
        super(name, x, y);
        switch (name) {
            case "sword":
                this.price = 2;
                break;
            case "bow":
                this.price = 3;  // Example price
                break;
            case "potion":
                this.price = 1;
                break;
            case "armor":
                this.price = 2;
                break;
            case "gold":
                this.price = 1;  // Each gold piece is worth 1
                break;
        }
    }

    // other attributes
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static Item ARMOR = new Item("Armor", 0, 0);  // x and y set to 0 just for initialization.
    public static Item POTION = new Item("Potion", 0, 0);
    public static Item SWORD = new Item("Sword", 0, 0);

    static {
        ARMOR.setPrice(2);
        POTION.setPrice(1);
        SWORD.setPrice(2);
    }

}
