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

    public DisplayTray(String trayLetter, String trayNum, String color, String width, String depth){
        this.trayIdentifier = trayLetter+trayNum;
        this.color = color;
        this.width = width;
        this.depth = depth;
    }

    public String getTrayIdentifier() {
        return trayIdentifier;
    }

    public void setTrayidentifier(String trayidentifier) {
        this.trayIdentifier = trayidentifier;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

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

//    @Override
//    public String listAll() {
//        return trayIdentifier +
//                "  " + color +
//                "  " + width + " x " + depth + '\n'
//                +
//    }
}
