package models;

public class Components {
    private String type;
    private String description;
    private int weight;
    private int quality;

    public Components(String type, String description, int weight, int quality) {
        this.type = type;
        this.description = description;
        this.weight = weight;
        this.quality = quality;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return weight;
    }

    public void setQuantity(int weight) {
        this.weight = weight;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    @Override
    public String toString() {
        return  type + "  " +
                ",  " + description + " " +
                ",  " + weight + "g " +
                " ,  " + quality + " carat" +  '\n' ;
    }
}
