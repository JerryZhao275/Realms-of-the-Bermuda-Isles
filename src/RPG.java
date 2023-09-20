public class RPG {
    // This is the initialisation of a java class in a source folder

    private Map map;
    private Inventory inventory;

    public RPG() {

        map = new Map();
        inventory = new Inventory();

    }

    public void move(Direction direction) {

    }

    public void fightEnemy(Enemy enemy) {
        enemy.talk();
    }

    public void talkToNPC(NPC npc) {
        npc.talk();
    }



}
