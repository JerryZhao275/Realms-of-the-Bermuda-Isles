import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    private GameEngine gameEngine = new GameEngine();
    private final Enemy goblin = new Enemy("goblin", 0, 0);
    private final Enemy spider = new Enemy("spider", 1, 0);
    private final Enemy ogre = new Enemy("ogre", 0, 1);
    private Enemy boss = new Enemy("boss", 1, 1);
    private Enemy other = new Enemy("other", -1, -1);

    @Test
    void testFight() {
        // Add sword and test for fighting goblin
        gameEngine.inventory.addItem(new Item(GameEngine.sword, -1, -1, ItemType.Sword));
        gameEngine.map.placeEntity(goblin, 0, 0);
        assertEquals("Enemy", gameEngine.map.getEntityTypeAt(0,0));
        goblin.fight(gameEngine, gameEngine.map, 0, 0, 5);
        assertEquals("Empty Space", gameEngine.map.getEntityTypeAt(0,0));

        // Test for fighting spider
        gameEngine.map.placeEntity(spider, 0, 1);
        assertEquals("Enemy", gameEngine.map.getEntityTypeAt(0,1));
        spider.fight(gameEngine, gameEngine.map, 0, 1, 5);
        assertEquals("Empty Space", gameEngine.map.getEntityTypeAt(0,1));

        // Add sword and test for fighting ogre
        gameEngine.inventory.addItem(new Item(GameEngine.sword, -1, -1, ItemType.Sword));
        gameEngine.map.placeEntity(ogre, 1, 0);
        assertEquals("Enemy", gameEngine.map.getEntityTypeAt(1,0));
        ogre.fight(gameEngine, gameEngine.map, 1, 0, 5);
        assertEquals("Empty Space", gameEngine.map.getEntityTypeAt(0,1));

        // Test for fighting the boss
        gameEngine.map.placeEntity(boss, 1, 1);
        assertEquals("Enemy", gameEngine.map.getEntityTypeAt(1,1));
        boss.fight(gameEngine, gameEngine.map, 1, 1, 5);
        assertEquals("Enemy", gameEngine.map.getEntityTypeAt(1,1));

        // Test for fighting goblin with no weapon
        gameEngine.map.placeEntity(goblin, 0, 0);
        assertEquals("Enemy", gameEngine.map.getEntityTypeAt(0,0));
        goblin.fight(gameEngine, gameEngine.map, 0, 0, 5);
        gameEngine.map.placeEntity(goblin, 0, 0);
        assertEquals("Enemy", gameEngine.map.getEntityTypeAt(0,0));
        goblin.fight(gameEngine, gameEngine.map, 0, 0, 5);
        gameEngine.map.placeEntity(goblin, 0, 0);
        assertEquals("Enemy", gameEngine.map.getEntityTypeAt(0,0));
        goblin.fight(gameEngine, gameEngine.map, 0, 0, 5);
        assertEquals("Enemy", gameEngine.map.getEntityTypeAt(0,0));
    }

    @Test
    void testTalk() {
        assertEquals("A puny traveler dares enter my territory? Face my wrath!", goblin.talk());
        assertEquals("Hssss... Another prey entangled in my web!", spider.talk());
        assertEquals("You smell... tasty! Prepare to be my meal!", ogre.talk());
        assertEquals("Impressive that you've come this far, but do you really think you can defeat me?", boss.talk());
        assertEquals("There is an enemy!", other.talk());
    }
}
