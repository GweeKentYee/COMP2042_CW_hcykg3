# COMP2042_CW_hcykg3

## Brick Destroyer Refactoring
This project is about refactoring an existing game called Brick Destroy created by FilippoRanza.

## Table of Contents
* [Refactoring and Addition](#refactoring-and-addition)
* [How to Install and Run](#how-to-install-and-run)
* [Functions](#functions)
* [How to Play](#how-to-play)
* [Acknowledgment](#acknowledgement)

## Refactoring and Addition
* MVC Format
* Factory Design Pattern
* Renaming Variables and Methods
* Splitting Components
* JavaFX Implementation
* JSON Data Handling
* New Game Modes
* Leaderboard
* Game Info

## How to Install and Run
* Create a Java project in your IDE and then paste the source files into your project.
* Before actually running the project, we will have to add some libraries (JavaFX and org.json) in order to run this project. 
* After adding the libraries, we will have to add a VM option into the configuration.     
Eg. (--module-path /Users/Sample/Sample/javafx-sdk-17.0.1/lib --add-modules=javafx.controls,javafx.fxml,javafx.swing)
* Now you are good to go!
	
## Functions

* The player will be able to choose 2 modes. (Training and Ranked)
* Training mode allows the player to play the game freely as they wouldn't be a countdown timer. The player can press ALT+SHIFT+F1 as well to open a panel to change the speed of the ball or skip levels.
* As for ranked mode, players can score points by destroying the bricks and there will be a countdown timer as well. If a player doesn't finish the level in time, the player will automatically lose.
* The top 7 player records will be displayed in the leaderboard. (The system will decide the rankings by looking at the level the players have reached first, followed by their scores and lastly their time remaining)
* Players can access the leaderboard through the home menu or after playing ranked mode.
* There will be a info page in the home menu as well to explain how the game works.
	
## How to Play
* A -> Moving left
* D -> Moving righjt
* Space -> Start/Pause
* Esc -> Pause Menu
* Shift+Alt+F1 -> Settings Panel

## Acknowledgement
I would like to express my gratitude to FilippoRanza (https://github.com/FilippoRanza/Brick_Destroy) for creating this game as it gives me the 
opportunity to make this wonderful project possible.


