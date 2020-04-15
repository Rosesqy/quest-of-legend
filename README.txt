Group number: 28
Student 1: Ziyu Shen
Student 2: Qingyang Shi

This README file explains how to use the Quest of Legend game for CS591.
This program is designed to play in terminal mode.

						---- COMPILE AND RUN ON THE COMMAND LINE ----

javac Quest.java
java Quest

						---------------- INSTRUCTIONS ----------------
in the game, you can choose  three heroes to your team to explor the map, buy things in market, fight against monsters.
some rules:
W: move up
A: move left
S: move down
D: move right
Z: status
T: Teleport
M: Shopping (only when in the nexus)
B: Back to nexus
Q: quit game
I: show information. If we are not in a fight this should show information about the heroes (more specifically their level, their hp, their mana, their current exp, their money and their skill levels).

all the heroes, weapons, armors... are parsed in to code from txt files in the the_Quest folder.

						---------------- CLASS DESCRIPTIONS ----------------
Quest.java: The class represents the quest game.
Fight.java: Represents the fight.
Explain.java: read and parse information from the_Quest folder.
----
WorldMap.java: Represents the world map of game.
Cell.java: Represent a cell on the world map.
Koulo.java, Block.java, Cave.java, Nexus.java: different type of cell with different functions.
----
Creatures.java: superclass of hero and monster.
Attack: An interface. contains an attack method.
----
Pub.java: A class stores all the information of heros, random pick heros when a game begin.
Hero.java: an abstract class Represents heros.
Paladin.java, Sorcerer.java, Warrior.java: represent three kinds of heros.
team.java: a class of hero team.
----
Abyss.java: A class stores all the information of monsters, random pick monsters when a fighting comes.
Monster.java: A class represents the monsters.
Dragon.java, Exoskeleton.java, Spirit.java:represent three kinds of monsters.
----
Market.java: Represent the market, includes the buy and sell action.
Item.java: Represent the items sold in the market.
Armor.java, weapon.java: Represent the equipments.
Potion.java: Represent the one-use items.
Spell.java: Represent the spells.
FireSpell.java, IceSpell.java, LightningSpell.java: represent three kinds of spells.
Sellable.java, Buyable.java: interfaces that represent the property of products in market.