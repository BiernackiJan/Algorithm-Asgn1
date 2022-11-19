package models;

import Resources.LinkedList;
import Resources.Node;

public class DisplayCase extends LinkedList{


    public LinkedList<DisplayTray> displayTrays = new LinkedList<>();

    //create a head reference for displayTray LL
    private Node head;
    private String caseNumber;
    private String mounted;
    private String lit;

    private String letter;

    public int numTrays = 0;

    public DisplayCase(String letter, String caseNumber, String mounted, String lit){
        this.caseNumber = caseNumber;
        this.mounted = mounted;
        this.lit = lit;
        this.letter = letter;
    }

    public String identifier(){
        String str = letter + "" + caseNumber;
        return str;
    }

    public String getCaseNumber () {
        return caseNumber;
    }

    public void setCaseNumber ( String caseNumber){
        this.caseNumber = caseNumber;
    }

    public String isMounted () {
        return mounted;
    }

    public void setMounted ( String mounted){
        this.mounted = mounted;
    }

    public String  isLit () {
        return lit;
    }

    public void setLit ( String lit){
        this.lit = lit;
    }

    public void addTray(DisplayTray dt){
        displayTrays.add(dt);
        numTrays++;
    }

    public void getTray(int index) {displayTrays.get(index);}



    @Override
    public String toString() {
        return  letter + "" + caseNumber +
                "  " + mounted +
                "  " + lit + '\n' ;
    }
}


