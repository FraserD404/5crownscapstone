package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

import static com.company.Game.masterDeck;
import static com.company.Game.discardDeck;
import static com.company.Game.finalFrenzy;


public class Player {

    private final String name;
    private final boolean isBot;
    public final CardPile playerHand = new CardPile(); //PUBLIC VARIABLES ARE SCARY FIX THIS LATER!!!!
    private final Scanner getCommand = new Scanner(System.in);
    private final Random rng;
    //private GameLogger logger = new GameLogger(false);

    public Player(String name, boolean bot) {
        this.name = name;
        this.isBot = bot;
        //qPrint("Welcome to Five Crowns, " + this.name + "!");

         rng = new Random();
    }

    //Take Your Time
    public void takeYourTurn() {
        if (isBot) {
            doBotRoutines();
        }

        else {
            int option = menu();

            //qPrint("done with the menu " + option);

            if (option == 1) {
                qPrint("Drawing card from deck...");
                drawFromDeck();
            } else if (option == 2) {
                qPrint("Drawing card from pile...");
                drawFromPile();
            } else if (option == 3) {
                qPrint("Going out...");
                goOut(); //come back to this later
            } else {
                qPrint("You probably shouldn't be here...");
            }
        }

        qPrint("Finished the turn!");
    }

    //Does Bot Routines
    private void doBotRoutines() {
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
            botFromDeck();
        }

        // Draws from discard deck
        else if (botChoice == 1){
            qPrint("The bot draws from discard deck");
            botFromPile();
        }

        else{
            qPrint("You shouldn't be here.");
        }

    }

    private void qPrint(String a) {
        System.out.println(a);
    }

    private int menu() {

        //this code is really fking janky come back and fix this
        qPrint("Your Hand: ");
        getHand();
        qPrint("Please make a selection:");
        qPrint("_____________________");
        qPrint("1. Draw card from deck");

        if (discardDeck.size() != 0) {
            qPrint("2. Draw card from pile ; Card Value: " + discardDeck.peekTopCard().getInfo2());
        } else {
            qPrint("2. Unable to draw card from pile (Pile is empty!)");
        }

        qPrint("3. Go out");
        qPrint("_____________________");

        int cmd = getUserChoice(3, false);

        return cmd;
    }

    private void botFromDeck(){
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
            playerHand.add(wtd2, drawn);
            qPrint("The bot changes it up.");
        }

        //????
        else{
            qPrint("Something went horribly wrong.");
        }

    }

    //TODO: Once drawFromPile is coded, just remake it in here :)
    private void botFromPile(){

    }

    private void drawFromDeck() {
        Card drawn = masterDeck.removeTopCard();
        qPrint("You drew a: ");
        drawn.getInfo();
        sleep(250);
        qPrint("Here is your hand: ");
        sleep(250);
        getHand();
        sleep(250);

        qPrint("Would you like to remove a card (1) or keep your hand (2) ?");
        int choice1 = getUserChoice(2, false);

        if (choice1 == 1) {
            qPrint("Here is your hand: ");
            getHand();
            qPrint("Choose a number (shown on left side as X| ) corresponding with card to remove.");

            int choice2 = getUserChoice(playerHand.size(), true);

            //Removes the card from player hand and puts it in discard pile
            discardDeck.add(playerHand.remove(choice2));

            playerHand.add(choice2, drawn);

            qPrint("Done! Here is your hand again: ");
        } else if (choice1 == 2) {
            masterDeck.add(drawn);
            qPrint("Hand is kept as is and the card has been returned to the deck.");
        } else {
            qPrint("That wasn't an option. Try again.");
        }

    }

    private int getUserChoice(int numItems, boolean zeroAllowed) {
        int choice = -1;

        while (choice == -1) {
            try {
                choice = getCommand.nextInt();

                if (!zeroAllowed) {
                    //Zero is not a menu option
                    if (choice > numItems || choice < 1) {
                        choice = -1;
                        qPrint("Not a valid number. Try again.");
                    }
                } else {
                    //Zero is a menu option
                    if (choice < 0 || choice > numItems - 1) {
                        choice = -1;
                        qPrint("Not a valid number. Try again.");
                    }
                }

            } catch (InputMismatchException e) {
                qPrint("Something went wrong. Probably a bad input. Try again." + e.toString());
                getCommand.next();
            }
        }

        return choice;
    }

    private void drawFromPile() {
        Card drawn = discardDeck.removeTopCard();
        qPrint("You drew a: ");
        drawn.getInfo();
        sleep(250);
        qPrint("Here is your hand: ");
        sleep(250);
        getHand();
        sleep(250);

        qPrint("Would you like to remove a card (1) or keep your hand (2) ?");
        int choice1 = getUserChoice(2, false);

        if (choice1 == 1) {
            qPrint("Here is your hand: ");
            getHand();
            qPrint("Choose a number (shown on left side as X| ) corresponding with card to remove.");

            int choice2 = getUserChoice(playerHand.size(), true);

            //Removes the card from player hand and puts it in discard pile
            discardDeck.add(playerHand.remove(choice2));

            playerHand.add(choice2, drawn);

            qPrint("Done! Here is your hand again: ");
        } else if (choice1 == 2) {
            masterDeck.add(drawn);
            qPrint("Hand is kept as is and the card has been returned to the deck.");
        } else {
            qPrint("That wasn't an option. Try again.");
        }
    }

    private void goOut() {
        finalFrenzy = true;
    }

    private void getHand() {
        qPrint("===================");
        int i = 0;
        for (Card e : playerHand) {
            System.out.print(i + "| ");
            e.getInfo();
            i++;
        }
        qPrint("===================");
    }

    public String getName() {
        return name;
    }

    private void sleep(long s) {
        //Try Catch needed otherwise compiler throws an error
        try {
            Thread.sleep(s);
        } catch (InterruptedException ez) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean getBot(){
        return isBot;
    }




} //End of the Player class here


