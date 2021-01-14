# jCrowns 
###### 5 Crowns Simulator written in Java

### Project Structure:
Capstone-5Crowns/src/com/company | **Directory where all classes are stored** <br/>
Card.java  | **Card class that stores the value and suit of each Card** <br/>
CardPile.java | **Extension of ArrayList, specially modified for Card specific tasks. Player Hands, Deck, and Discard Pile are all CardPile objects**<br/>
Player.java | **Player class that stores the player's hand (CardPile), and if it is a bot or not. Bots are also Players**<br/>
Game.java | **General class for game logic, score keeping, and decks storage.**<br/>
Main.java | **Initialises starts the game by creating and calling the Game class.**<br/>
GameLogger.java | **General logging class for debugging to file.**<br/>

### How to Run:
Download the "Capstone-5Crowns" folder, and open in IntelliJ Community Edition.
Run 'main.java'.
The files shared are all IntellIJ specific, however the classes could be used and ran in another IDE.

### Current Feature List:
Ability to play 13 hands of 5 Crowns
Two random bots are also playing the game with you

### Planned Feature List:
Score verification and handling
Jokers and wild card proper implementation
Improved bot logic using machine learning
Different types of bots (Random, SimpleAlgorithm, Advanced)
GUI to replace current Terminal based UI
Dynamic amount of players (up to 5, including human)
Lots and lots of bug fixes


