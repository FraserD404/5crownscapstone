package com.company;

import org.python.core.Py;
import org.python.util.PythonInterpreter;

import java.util.Random;

import static com.company.Game.discardDeck;
import static com.company.Game.masterDeck;

public class Bot extends Player{

    /*
    Type 1: SimpleRandom
    Type 2: SafeBot
    Type 3: AdvancedML
     */

    int type;
    private final Random rng = new Random();

    public Bot(String name, int d){
        super(name, true);
        this.type = d;
    }

    private void takeTurn(){
        switch(type){
            case 1: randomMode();
            case 2: safeMode();
            case 3: machineMode();
            default: System.out.println("There's an error here."); System.out.println("Check the bot type | Type: " + type);
        }
    }

    /*
    This bot type is a purely random bot where it's actions are entirely randomly generated.
     */
    private void randomMode(){
        int botChoice;
        int cardChoice;

        //qPrint("I am a bot.");

        // This is the first model of the bot
        // Completely random bot

        // Chooses either a 0 or a 1
        botChoice = rng.nextInt(2);

        // Draws from deck
        if (botChoice == 0){
            qPrint("The bot draws from deck");
            randBotFromDeck();
        }

        // Draws from discard deck
        else if (botChoice == 1){
            qPrint("The bot draws from discard deck");
            randBotFromPile();
        }

    }

    /*
    This bot type evaluates it's hand and tries to stay 'safe' and minimises the card total
    by throwing out the highest value card in it's hand

     */
    private void safeMode(){

        /*
        First evaluate if the card in the discard pile is lower than any of the cards in its hand
        If not, then it draws from the deck and determines what it should do
         */

        boolean madeMove = false;
        Card topDiscardCard = discardDeck.peekTopCard();
        int delta = 0;
        Card highestDeltaCard = null;

        /*
        Loops through the entire deck to get the highest delta card out
         */
        for (Card c : this.playerHand){
            if (c.getValue() > topDiscardCard.getValue() && (c.getValue() - topDiscardCard.getValue()) > delta){
                delta = c.getValue() - topDiscardCard.getValue();
                highestDeltaCard = c;
            }
        }

        /*
        No cards in the deck are larger than this card
        */
        if (delta == 0){
            discardDeck.add(topDiscardCard);
        }
        else{
            this.playerHand.remove(highestDeltaCard);
            this.playerHand.add(topDiscardCard);
            discardDeck.addCardToTop(highestDeltaCard);
            madeMove = true;
        }


        /*
        Checks if it already discarded and added a card
        If it didn't, then it draws from the deck
        Also resets the value of delta and the highest delta card value
         */
        delta = 0;
        highestDeltaCard = null;

        /*
        Only runs if player has already taken from discard deck
        No double dipping!! >:o
         */
        if(!madeMove){
            Card drawnCard = masterDeck.removeTopCard();

            for(Card a : this.playerHand){
                if (a.getValue() > drawnCard.getValue() && (a.getValue() - drawnCard.getValue()) > delta){
                    delta = a.getValue() - drawnCard.getValue();
                    highestDeltaCard = a;
                }
            }

            /*
            No cards in the deck are larger than this card & hand stays the same
             */
            if (delta == 0){
                discardDeck.add(drawnCard);
            }
            else{
                this.playerHand.remove(highestDeltaCard);
                this.playerHand.add(drawnCard);
                discardDeck.addCardToTop(highestDeltaCard);
            }
        }

    }

    /*
    This bot interacts with the python ML model and uses it to make its decisions
     */
    private void machineMode(){

    }


    /*
    Idea for how to make Java and Python talk to each other:
    Write the Player's hand to a file
    execfile the ML script
    Script reads the file
    Provides output to another file
    Java reads that file & does what it needs to do
    Rinse and repeat, clearing both files after
     */

    /*
    Python Testing function
     */
    public void pyTest(){
        PythonInterpreter interpreter = new PythonInterpreter();

        try{
            //TODO: change the file path to something consistent (ie the github directory?)
            interpreter.execfile("/Users/fdougall/Desktop/test.py");
            interpreter.execfile("/Users/fdougall/Desktop/test.py");
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    /*
    This entire function may break & be super jank
     */
    private void randBotFromDeck(){
        Card drawn = masterDeck.removeTopCard();

        int wtd = rng.nextInt(2);
        int wtd2 = rng.nextInt(playerHand.size());

        //Keep hand
        if (wtd == 0){
            masterDeck.add(drawn);
            qPrint("The bot keeps their hand");
            return;
        }

        //Change it up
        else if (wtd == 1){
            discardDeck.add(playerHand.remove((wtd2)));
            playerHand.add(drawn);
            qPrint("The bot changes it up.");
        }

    }

    //TODO: Once drawFromPile is coded, just remake it in here :)
    private void randBotFromPile(){

    }

    private void qPrint(String a) {
        System.out.println(a);
    }
}
