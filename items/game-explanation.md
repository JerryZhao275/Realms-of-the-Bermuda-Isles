# **Summary of “Realms of the Bermuda Isles” v1.0**
* For a guide with live screenshots, see our gitlab Wiki: [Project Summary for v1.0](https://gitlab.cecs.anu.edu.au/u7480724/comp-2120-assignment-3-workshop-07-group-a/-/wikis/Project-Summary-for-v1.0)

## **Game Introduction**

* **_Realms of the Bermuda Isles_** is a Text-based RPG Game, that solely runs in a terminal. Its creation is inspired by the text-based game website _Zork_.

## **Background Story**

* In the enigmatic expanse of the Bermuda Triangle, an area where time and space mysteriously intertwine, lies a realm unknown to most.
* This place, known as the heart of the Bermuda Triangle, is the Bermuda Isles.
* Its inhabitants, mostly sailors and aviators who vanished over the years, have formed a unique community in this lost world.
* You, i.e. the player, will play the role of a brave adventurer to solve the secret of Bermuda.

## **Basic Commands**

* Enter “**_quit_**” to quit the game.
* Enter “**_help_**” for a list of commands.

## Game Modes

* **Easy**:<span dir="">      </span>_HP limit_ = 5, Initial Items (_sword_ x1, _potion_ x1, _gold_ x4)
* **Normal**: _HP limit_ = 4, Initial Items (_sword_ x1)
* **Hard**: <span dir="">    </span>_HP limit_ = 3, Initial Items (_empty_)
* Commands for choosing a mode:

    **-** Enter “**_play easy_**” to play on an Easy mode.

    **-** Enter “**_play normal_**” to play on a Normal mode.

    **-** Enter “**_play hard_**” to play on a Hard mode.
- <img src="img/game_mode.png"><br>

## HP System

* The **_HP_** system is designed for the player. It will decrease when the enemy gains the upper hand. It is recoverable by using **supplementary Items**.
* When your HP comes lower than 1, the game **END**s.
- <img src="img/0HP.png"><br>
* If you want to check your _HP_, you can enter the command “**_hp_**”. Your remaining _HP_ will then be presented.
- <img src="img/HP.png"><br>

## Moving on the Map

* You will be starting your adventure on a _4x4_ grid map.<span dir="">  </span>The areas on the map contain one of the entities (**_Enemy_**, **_NPC_**, or **_Item_**).
* Sample Map:

**- # # # B**   (e.g. “B” stands for the location of the **_boss_**(3,3))

**- # # # #**   (e.g. “#” stands for the **undiscovered** areas)

**- # # # #**

**- S # # #**   (e.g. <span dir="">“</span>S<span dir="">”</span> stands for the **starting point**(0,0))

* If you want to move to a new area, you can enter a command “**_move_** _+ (The direction)_”, where the directions can be **_left_**, **_right_**, **_forward_** or **_backward_**.
* Once you move on to a new area, the game will tell you what entity exists in that area (**_Enemy_**, **_NPC_**, or **_Item_**).
- <img src="img/move.png"><br>

## Inventory

* The **Inventory** saves the **Items** you get during the adventure.
* You may hold more than one of the same items in your inventory.
* If you want to check your inventory, you can enter the command “**_inventory_**”. The names of the items in your inventory will be listed.
- <img src="img/inventory.png"><br>

## Items

* You will have a chance to come across certain items in the game, including weapons (**_Sword_**, **_Bow_**_)_, supplements (**_Potions_**, **_Armor_**) and currency (**_Gold_**).
* Weapons are useful when you attack the enemies. Challenging enemies without a weapon may cause a loss on your HP.
* Supplements provide support on your HP. “**_Potion_**” recoveries **_1 HP_** if your _HP_ is lower than your _HP limit_. “**_Armor_**” gives you **1** **extra** **HP**, which is independent of the _HP limit_. It performs better than potions, but it is also more difficult to obtain.
- <img src="img/use_potion.png"><br>
- <img src="img/use_armor.png"><br>
* Pieces of “**_Gold_**” are used as the currency along the Bermuda Isles. You can use them to trade with some particular NPCs.
* If you find an item, you can add the item to your inventory by entering the command “**_take_**”. Each item can only be taken **ONCE**.
* If you want to use a supplement, you can use the item from your inventory by entering the command “**_use_** _+ (The name of the Item)_”.

* There is a **Durability** for the weapons and supplements. You can use:

    **-** _Sword_ & _Bow_: Three times

    **-** _Potion_: Twice

    **-** _Armor_: Once

    **-** _ps. The durability of weapons means the time for using them to attack_

* When the durability comes to **0**, the item will be removed from your inventory.
- <img src="img/durability.png"><br>

## NPC Interaction

**-** There are several types of NPCs scattered throughout Bermuda:

    **- _Blacksmith_**: The skilled blacksmith will gift you his hand-made sword.

    **- _Dwarf_**: The friendly dwarf will gift you a piece of gold.

    **- _Thief_**: The sneaky thief will steal all the items (except swords) from you and escape.

       **-** _ps. The identity of the thief is not published before you talk to him._

- <img src="img/thief.png"><br>

    **- _Merchant_**: You can purchase items from astute Merchant using “_Gold_”, with a pricing of

       **-** _Sword_: _2_

      **-** _Potion_: _1_

       **-** _Armor_: _2_

       **-** Note that each item can only be purchased **ONCE.**

**-** You can interact with the NPCs by entering a command “**_talk_** _+ (The name of the NPC)_”.

**-** After "**_talking_**" with the **_merchant_**, if you want to purchase an item, you can enter the command “**_trade_** _+ (The item you want to buy)_”.

- <img src="img/merchant.png"><br>

## Enemy Interaction

* On the Bermuda Isles, there is a risk of encountering enemies such as **_Goblins_**, **_Spiders_**, and **_Ogres_**.
* You can attack the enemies by entering the command “**_attack_** _+ (The lowercase name of the Enemy)_”.

    **-** Weapons are **essential for defeating your enemies**.

    **-** After defeating all the enemies in an area, you will find a treasure **_Chest_** as your trophy.

- <img src="img/normal_enemy_fight_win.png"><br>

    **-** The **_Chest_** will open automatically, giving you a random weapon or supplement, with _0-2_ pieces of **_Gold_**.

    **-** The items from the chest will be automatically saved in your **_Inventory_**.

    **-** If you attack an enemy **without having a weapon**, the enemy will prevail against you, resulting in a loss of _1 HP_.
- <img src="img/normal_enemy_fight_lose.png"><br>

* The “**_Boss_**”, who is the king of all the Bermuda monsters, is guarding the exit. Defeating him is the **ONLY** way for you to **WIN** the game.

    **-** To challenge the **_Boss_**, you need to be equipped with a **weapon**.

    **-** The **_Boss_** is much more powerful than normal enemies. To defeat him, you need to attack it 3 times in total.

    **-** For the intense hit-back from the **_Boss_**, you will lose **_2 HP_** from each of your attacks.

- <img src="img/hit_back_from_boss.png"><br>

    **-** During your challenge, logical planning on the usage of your **supplement** may be useful.

    **-** After defeating the **_Boss_**, you **WIN** the game!  _(p.s If the boss and you both die in the final duel, it will still count as your victory.)_

* Alternatively, you can also choose not to fight but simply move to the next area. The enemies will **NOT** leave unless they are defeated.

## Saving & Loading

* If you need to leave during a play, you can choose to save your current progress (maximum storage: 1 file)
* The command for **Saving** is “**_save_**”, all the parameters (**_Inventory_**, **_HP_**, player location, The state of **_NPCs_**, **_Enemies_**, and **_Items_**) will all be saved.
- <img src="img/save.png"><br>

* When you come back, you can load the game and continue playing.
* The command for **Loading** is **<span dir="">“</span>_load_<span dir="">”</span>.** The data saved will be loaded as parameters for continuing the game.
- <img src="img/load.png"><br>

## Features Planned without Implement

1. **The RNG for the size and entities for the map**: We give up this feature simply because of the difficulty for the developers on testing, the difficulty for the players and other <span dir="">uncontrollable factor</span>s. The <span dir="">alternative solution</span> we finally implemented is a fixed 4x4 map, which has <span dir="">manual-</span>set entities for each area (something like a game level). This ensures that the difficulty for players is manageable, but on the other hand, it also loses some fun brought by randomness. (We still have RNG for opening chests!)

   As a result, it is hard for the current version to get players to play repeatedly. If the project continues, the RNG Map feature will be the primary goal for further development.

## Game Tester

Realms of the Bermuda Isles are thoroughly tested through a combination of JUnit tests and simulated playthroughs with simulated inputs.

**JUnit Testing**

JUnit testing was performed to write basic unit tests for methods independent of other classes. This included writing tests to ensure dialogue was printed correctly, the player's inventory and map position were correct, and further attributes such as HP and equipment durability. The JUnit tests consist of:

- EnemyTest
- EntityTest
- InventoryTest
- ItemTest
- MapTest
- NPCTest

All of the above test classes covered all, if not, the majority of lines within their respective classes. There are certain exceptions, i.e. the Merchant NPC in the NPC class can only be tested within simulated runs as inputs must be directly provided for that specific instance.

**Automatic Game Tester**

An Automatic tester was implemented similar to JUnit testing syntax, but rather than performing unit tests, this AutomaticGameTest.java class was responsible for running through as many lines of code in both GameEngine.java and MainMenu.java, ensuring the game is error-free from a user perspective.

This tester was implemented by utilising the ByteArrayOutputStream class, allowing us to hardcode inputs in a list of Strings that are sequentially fed into the game engine. However, to allow this input feeding process, we were required to re-architecture our GameEngine.java class, parameterising it with a list of strings, allowing testing to be performed. This re-architecture was performed for specific class methods that require the use of a scanner, further allowing a list of string inputs to be processed by several unique scanners functioning in different classes. For the case of a normal playthrough, we would simply process a key within a singleton, and write additional logic for the program to not perform testing.

Through using both JUnit testing and Automated game testing, our team was able to provide coverage of 82.7% of all lines within the project. Note that some methods are duplicated for the sake of testing and running a playthrough, but retain the same functionality, hence the true coverage is much higher.
- <img src="img/coverage.png"><br>
More information on the coverage report can be found in [coverage-report/index.html](https://gitlab.cecs.anu.edu.au/u7480724/comp-2120-assignment-3-workshop-07-group-a/-/blob/main/items/coverage-report/index.html).
