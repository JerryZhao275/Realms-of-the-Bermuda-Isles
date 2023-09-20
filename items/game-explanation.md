# Assignment 3 Guide

**Text-based RPG Game Explanation**

- The RPG game will be solely run in terminal, similar to the text-based game Zork. Once the game starts, a prompt within the terminal containing some sort of welcome message will be printed to the user, alongside a help command to prompt the user with instructions.

******************************************Moving around the Map******************************************

- The map of our game will be represented as a 2x2 grid for simplicity. Each map must contain one enemy, one NPC, and one item (We will implement more of each with RNG as an additional feature later). This map will be represented by the Map class, and interacts with the Enemy, NPC, and Item classes by hard coding a grid to contain each entity.
- The user will know how to move “Forward”, “Backward”, “Left” and “Right” around the map. If a user reaches the end of a map and attempts to keep moving in that direction, they will be prompted with a “You cannot move there!” instruction.
- Once the user moves into a new grid, we will check what entity exists in that grid, i.e. enemy, NPC, or item. Depending on which entity, we will write back to the terminal with the appropriate prompt:
    - Anything in grid: “You have moved to a new area and spotted a [Entity name]!

******************************NPC Interaction******************************

- The user will be able to interact with NPCs via a “Talk” keyword followed by the name of the NPC. We will hardcode 3 NPCs such as a blacksmith, thief, and dwarf, each with unique functions:
    - Blacksmith: Gives the user a sword
    - Thief: Steals all the user’s items in their inventory except for their weapon
    - Dwarf: Gives the user a sack of gold (does nothing for now)

**********************************Enemy Interaction**********************************

- Users will be able to fight enemies using a “Fight” keyword
    - If the user has no weapon and fights an enemy, they will die and game over. For now, we can have a set of enemies such as Goblins, Spiders, and Ogres.
    - Else, the user will kill the enemy (We will allow the user to kill anyone as long as they have a weapon for the sake of getting the game skeleton done)
- Users can also choose to not fight but simply moving to another grid

********************************Item Interaction********************************

- Users will have the chance to come across certain items in the game, i.e. “Sword, Bow, Potions, Gold, etc.”
- Users will have the option to interact with these items by the keyword “Take” and will be added to their inventory when used.
- We can later add more interactions such as user being able to perform “Open” actions on items such as chests or bags

**************************The RPG Class**************************

- Within this class, we will have a main function that consists of a constant loop seeking inputs from the terminal. This is our game engine, where it handles user inputs and outputs an appropriate response/action
- On initialisation, the user will be greeted by the game in terminal. Logically, the game itself will be in a never-ending while loop asking for inputs from the user until the user ‘exits’ the game
- As for now, the user can win if he can find the exit in the map so that there is a win condition