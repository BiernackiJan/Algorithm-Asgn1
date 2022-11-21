package models;

import Resources.LinkedList;
import Resources.Node;

public class DisplayTray {
    public LinkedList<Items> items = new LinkedList<>();

    private Node head;
    private String trayIdentifier;

    private String color;
    private String width;

    private String depth;

    private int trayPrice = 0;

    public DisplayTray(String trayLetter, String trayNum, String color, String width, String depth){
        this.trayIdentifier = trayLetter+trayNum;
        this.color = color;
        this.width = width;
        this.depth = depth;
    }

    public int priceTray(){return trayPrice;}

    public void priceUp(int price){trayPrice += price;}

    public void  priceDown(int price){trayPrice -= price;}

    public void addItem(Items it){ items.add(it); }

    public LinkedList<Items> getItems() {
        return items;
    }

    public void setItems(LinkedList<Items> items) {
        this.items = items;
    }

    public void getItem(int index){items.get(index);}





    @Override
    public String toString() {
        return  trayIdentifier +
                "  " + color +
                "  " + width + " x " + depth + '\n';
    }
}
