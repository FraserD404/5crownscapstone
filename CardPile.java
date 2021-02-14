package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class CardPile extends ArrayList<Card>{

    //private ArrayList<Card> pile = new ArrayList<Card>();

    public CardPile(){
        super();
    }

    public void addCardToTop (Card in_Card){
        this.add(in_Card);
    }

    public Card removeTopCard (){
        return this.remove(0);
    }

    public Card peekTopCard(){
        return this.get(this.size() - 1);
    }

    public Card peekAtIndex(int in_c){
        return this.get(in_c);
    }

    public int getSize(){
        return this.size();
    }

    public void shuffle(){

        //qPrint("Size of this: " + this.size());
        //return 0;
        for (int x = 0; x < 100; x++) {
            for (int i = 0; i < this.size(); i++) {
                //qPrint("Iteration: " + i + " Round: " + x);
                //qPrint("this size is " + this.size());
                Card cardA = this.remove(i);

                int rand = ThreadLocalRandom.current().nextInt(0, this.size() - 1);

                while (rand == i){
                    rand = ThreadLocalRandom.current().nextInt(0, this.size() - 1);
                }

                Card cardB = this.remove(rand);

                this.add(rand, cardA);
                this.add(i, cardB);
            }
        }
        //qPrint("Deck shuffle completed! " + this.size());
    }



    private void qPrint(String a){
        System.out.println(a);
    }

}
