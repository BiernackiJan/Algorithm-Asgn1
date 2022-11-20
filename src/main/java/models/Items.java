package models;

import Resources.LinkedList;

public class Items {

    public LinkedList<Components> components = new LinkedList<>();
    private String description;
    private String type;
    private String gender;
    private String imageURl = "no url";
    private int rPrice;

    public Items(String description, String type, String gender, int rPrice) {
        this.description = description;
        this.type = type;
        this.gender = gender;
        //imageURL?????!!!
        this.rPrice = rPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String  gender) {
        this.gender = gender;
    }

    public String getImageURl() {
        return imageURl;
    }

    public void setImageURl(String imageURl) {
        this.imageURl = imageURl;
    }

    public int getrPrice() {
        return rPrice;
    }

    public void setrPrice(int rPrice) {
        this.rPrice = rPrice;
    }

    public void addComponent(Components component){components.add(component);}

    @Override
    public String toString() {
        return  type + "  " +
                "  " + description +
                ",  For " + gender +
                // ", imageURl='" + imageURl + '\'' +
                ",  Retail Price: $" + rPrice + '\n';
    }
}
