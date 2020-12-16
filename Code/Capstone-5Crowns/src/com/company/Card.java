package com.company;

public class Card {

    private final int value; //numerical value of card
    private final String suit; //suit of the card

    public Card(int v, String s){
        value = v; suit = s;

    }

    public void getInfo(){
        qPrint(value + " of " + suit);
    }

    public String getInfo2(){
        return value + " of " + suit;
    }

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    private void qPrint(String x){
        System.out.println(x);
    }
}
