package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        /*
        The game
         */
        Game goTime = new Game(3);

        /*
        Initialises the GUI, then runs the game
         */
        //GameLayer theGame = new GameLayer();
    }
}



/*
GRAVEYARD

/*
    private void shuffleMethodOne(){
        //Shuffles the deck twice
        for (int x = 0; x < 5; x++) {
            for (int i = 0; i < masterDeck.size(); i++) {
                Card temp = masterDeck.get(i);
                int rand = ThreadLocalRandom.current().nextInt(0, masterDeck.size() - 1);
                Card temp2 = masterDeck.get(rand);
                masterDeck.add(rand, temp);
                masterDeck.add(i, temp2);
            }
        }
    }
    */


    /*
    TODO: THIS METHOD CREATES ANOTHER ARRAYLIST FOR TEMP STORAGE OF SHUFFLED CARDS
     */
    /*
    private void shuffleMethodTwo(){
        ArrayList<Card> tempList = new ArrayList<>();
        //Shuffles deck twice over
        while (masterDeck.size() > 0) {
            int rand = ThreadLocalRandom.current().nextInt(0, masterDeck.size());
            tempList.add(masterDeck.get(rand));
            masterDeck.remove(rand);
        }
        qPrint("yo");
        masterDeck = tempList;

    }
    */

//The following is just test code for shuffleMethodTwo();
        /*
        for (int x = 0; x < 10; x++){
            Card a = new Card(x, "star", false);
            masterDeck.add(a);
        }

        shuffleMethodTwo();

        for (int y = 0; y < 10; y++){
            masterDeck.get(y).getInfo();
        }
        */
//STARTS THE GAME

