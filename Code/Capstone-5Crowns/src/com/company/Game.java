package com.company;
import javax.management.ConstructorParameters;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

//TODO: FOR SLIDING WINDOW HAND THING MAYBE SORT BOTH BY VALUE AND STRAIGHTS & COMPARE THE TWO

public class Game{

    private final int playerNum;
    public static final CardPile masterDeck = new CardPile();
    public static CardPile discardDeck = new CardPile();
    private final String[] suits = new String[5];
    private final Player[] players;
    private final String[] botsNames = {"Beeper", "Booper", "Viking"};
    private HashMap<String, Integer> scoreTable;


    public static boolean finalFrenzy;

    public boolean debug;

    public Game(int numPlayers) {
        //Sets number of players for later
        playerNum = numPlayers;
        players = new Player[numPlayers];
        matchSetup();

        //Sets suits in suits[]
        suits[0] = "star";
        suits[1] = "heart";
        suits[2] = "spade";
        suits[3] = "diamond";
        suits[4] = "club";

        runner();
    }


    //Restructure code and make the players hand a function
    //scoreHand() function to score the player's hand
    //Work on different bot methods later

    //TODO: Rewrite runner() using another helper method for taking the player's turns
    //TODO: Improve the control flow within the Player class

    private void runner(){
        deckCreate();

        masterDeck.shuffle();

        qPrint("=========================================");

        //Handles each round
        for (int round = 3; round <= 13; round++){
            roundLogic(round);
        }

        scoreHands(1);
    }

    /*
    PRIVATE METHODS
     */

    private void roundLogic(int round){
        qPrint("Round: " + round + " Start!");

        int turn = 0;

        //Resets the indicator if it's the final round
        finalFrenzy = false;

        //Deals the player hand with 'round' cards
        //Round goes from 3-13 inclusive, as defined in the for loop above
        dealHands(round);

        while (!finalFrenzy) {
            for (Player c : players) {
                //TEMPORARY CODE TO SKIP BOT'S TURNS

                if (c.getBot()){
                    qPrint("A bot is taking their turn.");
                    c.takeYourTurn();
                    qPrint("The bot's turn is over.");
                }

                if (!c.getBot()) {
                    qPrint("Turn " + turn);
                    qPrint("It is now " + c.getName() + "'s turn.");
                    c.takeYourTurn();
                    turn +=1;
                }

                if(finalFrenzy){
                    scoreHands(0);
                    break;
                }
            }
        }

        qPrint("FINAL FRENZY!!!!!");

        turn = 1;
    }

    // TODO: THIS SCORING METHOD ISN'T THE PROPER WAY TO DO STUFF
    // TODO: FIX THIS WHEN GOING OUT IS POSSIBLE
    // Thought: zero out every card when go out is possible to make things easier
    // Fix this some time in the future

    private void scoreHands(int end){

        int tempScore = 0;
        // Display final scores
        if (end == 1){

        }

        //Update player hand scores
        else{
            //Player
            for (Card c : players[0].playerHand){
                tempScore += c.getValue();
            }
            scoreTable.put(players[0].getName(), tempScore);
            tempScore = 0;

            //Bot 1
            for (Card c : players[1].playerHand){
                tempScore += c.getValue();
            }
            scoreTable.put(botsNames[0], tempScore);
            tempScore = 0;

            //Bot 2
            for (Card c : players[2].playerHand){
                tempScore += c.getValue();
            }
            scoreTable.put(botsNames[1], tempScore);
            tempScore = 0;

            //Bot 3
            for (Card c : players[3].playerHand){
                tempScore += c.getValue();
            }
            scoreTable.put(botsNames[2], tempScore);
            tempScore = 0;
        }
    }

    private void checkDeck(){

        qPrint("Checking Deck...");

        if (masterDeck.size() != 116){
            qPrint("ERROR IN DECK CREATION - CARD NUMBER NOT 116");
        }

        qPrint(masterDeck.size());

        for (Card c : masterDeck){
            c.getInfo();
        }

        qPrint("Deck check complete.");
    }

    private void deckCreate(){
        //This loop creates 2 decks
        for(int k = 0; k < 2; k++){
            //This loop creates 5 suits of 13 card each
            for(int i = 0; i < 5; i++){
                //This loop assigns a numerical value to each card
                for (int y = 3; y < 14; y++){
                    Card ye = new Card(y, suits[i]);
                    masterDeck.add(ye);
                }
            }
        }

        //Adds jokers to the deck (n = 6)
        for (int m = 0; m < 6; m++){
            Card jok = new Card(0, "joker");
            masterDeck.add(jok);
        }

    }

    private void matchSetup(){
        finalFrenzy = false;

        // Player Set up

        Scanner inner = new Scanner(System.in);
        qPrint("Welcome to Five Crowns!");
        qPrint("Please Enter Your Name: ");
        String name1 = inner.next();

        players[0] = new Player(name1, false);

        players[1] = new Player(botsNames[0], true);

        players[2] = new Player(botsNames[1], true);

        players[3] = new Player(botsNames[2], true);

        // Scoring set up

        scoreTable.put(name1, 0);
        scoreTable.put(botsNames[0], 0);
        scoreTable.put(botsNames[1], 0);
        scoreTable.put(botsNames[2], 0);

        //qPrint("Players Created");
    }

    private void dealHands(int turn){
        //Goes through and deals (turn) cards to each player

        /*
            The following 8 lines of code clear each player's hand
            and rebuilds & clears the deck from scratch
        */

        for (Player p : players) {
            p.playerHand.clear();
        }
        masterDeck.clear();
        deckCreate();
        //End of rebuilding

        masterDeck.shuffle();

        for (int player = 0; player < playerNum; player++){
            //if turn = 3, then 3 cards will be dealt per hand
            // *technically* not how dealing in physical 5C works :)
            int c = getRandomNum(masterDeck.size() - 1);
            for (int i = 0; i < turn; i++){
                //qPrint("about to add from masterDeck with value " + c + " at size " + masterDeck.getSize());
                players[player].playerHand.add(masterDeck.get(c));
                masterDeck.remove(c);
            }
        }
    }

    private int getRandomNum(int bound){
        return ThreadLocalRandom.current().nextInt(0, bound);
    }

    private void qPrint(String x){
        System.out.println(x);
    }

    private void qPrint(int a){
        System.out.println(a);
    }

    private void LogIt(String inLogentry){
        qPrint(inLogentry);
    }

    /*
    PUBLIC METHODS
     */

    public boolean isFinalFrenzy() {
        return finalFrenzy;
    }

    public void setFinalFrenzy(boolean finalFrenz) {
        finalFrenzy = finalFrenz;
    }
}
