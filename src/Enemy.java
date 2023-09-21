public class Enemy extends Entity{
    /**
     * Public constructor to initialize the Enemy instance.
     * It creates a new name, x-axis coordinate, and y-axis coordinate for the Enemy.
     */
    public Enemy(String name, int x, int y) {
        super(name, x, y);
    }

    //    private int hp;
//    private int attack;
//
//    public void takeDamage(int damage) {
//        hp -= damage;
//    }
    public void talk() {
        System.out.println("There is an enemy!");
    }
}
