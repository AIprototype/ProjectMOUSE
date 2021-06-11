# ProjectMOUSE
A small 2D platformer, developed in Intellij using Java and Processing library

# Design
ProjectMOUSE is a 2D platformer in which the main goal of the player is to collect as many points as possible and exit the game. 
During the course of the game, you will be presented with various pathways and numerous collectables which you can choose to collect/destroy or just focus on exiting the lab. 
The game architecture follows an OOP architecture with significant levels of data abstraction and inheritance.

Have created a camera handler class, whereby only items/enemy zombies within our camera range will only be used for collision detection. 
The rest all won't be available for collision detection. This meant I could now increase my game world size as much as I wanted to, without worrying about fps drop.

Each platform in my game has a cost value, standard platforms have the least cost in the game, movable platforms have the highest cost. 
Wall platforms have a comparatively higher cost when compared to StandardPlatforms. 
These cost values are used so that whenever I request a platform for placing an enemy or an item, the game engine will give me the freest and un-cluttered platform available within my game world. 
Whenever an item or enemy is placed on a platform, its cost gets modified based on the object placed. Now you may ask why the priority queues? Why not simple for loop and comparing? I
have used priority queues specifically to avoid the loop and cost comparison as these two are heavier operations and can hava a negative impact on loading times. 
In priority queues, I have designed them to be always sorted in the ascending order of cost, so when I pop a platform, it will be the least cost platform within the game.

# Rules and Goals
There are 3 game modes I have designed; a normal mode, bouncy mode and a hard mode. The normal mode is where you can experience the game in a mild difficulty, which gradually increases as you progress through the map. The bouncy mode is a fun addition, what it does is basically add a bounce feature to the player. So when a player jumps from platform to platform they will have a level of bounce based on how high or low the platform jumping to is positioned. Hard mode is for players who have experienced the game maybe a few times, here there is a far greater number of enemies. The player has only two energy bolts and the players start with reduced health of eighty-five.
Like real-world mechanics, the player of this game cannot change direction in mid-air, the player will have to run and build momentum in the direction to jump and then jump. Simply pressing jump and pressing the right or left arrow key won’t do anything.
In normal mode when the player starts the game, the player has full health, three energy bolts and zero points. The energy bolts a player can use are limited to three, if you hit a target with the bolt, the bolt count won't reduce, but if it misses the target and moves away from the screen, then the player loses that bolt. This enforces the player to use their power wisely rather than just randomly firing.
The wall jump mechanics are made easier, to perform a wall jump, jump towards a wall platform and while you are still pressing the right/left arrow key, jump one more time. This will make you jump in the opposite direction. You can also chain a series of wall jumps to perform cool kinds of stuff.
The ultimate goal of the game is the destroy as many items possible and collect as many collectables as possible and win. The points directly relate to the destruction of enemies/cloning containers or collection of collectables. The higher the score, the better the player. The scoring category is divided into Bronze, Silver and Gold. You can see this on your top left corner or on the victory page.
