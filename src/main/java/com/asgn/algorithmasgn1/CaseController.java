package com.asgn.algorithmasgn1;

import Resources.LinkedList;
import Resources.Node;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import models.Components;
import models.DisplayCase;
import models.DisplayTray;
import models.Items;

import java.io.FileReader;
import java.io.ObjectInputStream;
import java.lang.reflect.Type;
import java.util.List;


public class CaseController {
    public static LinkedList<DisplayCase> list = new LinkedList<>(); //LL to store Display Cases

    private String trayID = ""; //String used to store all ID's of Trays in the system
    private String caseID = ""; //String used to store all ID's of Cases in the system
    private int totalPrice; //Int used to calculate the total price of all stock in the system



    //Home tab
    @FXML
    public ListView<String> stockValue;


    //Display case tab
    @FXML
    public ComboBox<String> CaseLetter;
    @FXML
    public ComboBox<String> CaseNumber;
    @FXML
    public ComboBox<String> pickLit;
    @FXML
    public ComboBox<String> pickMounted;
    @FXML
    public ListView<String> dc;
    @FXML
    public ComboBox<DisplayCase> caseToValue;
    @FXML
    public ListView<String> caseValue;



    //Trays tab
    @FXML
    public ComboBox<DisplayCase> pickCase;
    @FXML
    public ComboBox<String> pickLetter;
    @FXML
    public ComboBox<String> pickNum;
    @FXML
    public ComboBox<String> pickColour;
    @FXML
    public ComboBox<String> pickWidth;
    @FXML
    public ComboBox<String> pickDepth;
    @FXML
    public ComboBox<DisplayCase> showCase;
    @FXML
    public ListView<String> dt;
    @FXML
    public ComboBox<DisplayTray> trayToValue;
    @FXML
    public ListView<String> trayValue;



    //Items tab
    @FXML
    private ComboBox<DisplayCase> pickDCase;
    @FXML
    private ComboBox<DisplayTray> pickTray;
    @FXML
    private TextArea typeDesc;
    @FXML
    private ComboBox<String> itemType;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private ComboBox<String> rPrice;
    @FXML
    private ListView<String> viewItems;
    @FXML
    private ComboBox<Items> itemToDel;
    @FXML
    private ComboBox<DisplayTray> chooseTray;



    //components tab
    @FXML
    private ComboBox<Items> pickItem;
    @FXML
    private ComboBox<String> type;
    @FXML
    private TextArea materialDesc;
    @FXML
    private ComboBox<String> weight;
    @FXML
    private ComboBox<String> quality;
    @FXML
    private ListView<String> viewMaterial;


    //Inspect Stock
    @FXML
    private TreeView viewAll;
    @FXML
    private ListView<Components> compList;



    //Home
    @FXML
    void valueStock(MouseEvent event){//getting total stock value
        stockValue.getItems().clear();//Clearing stockValue ListView
        String str = "$" + totalPrice;
        stockValue.getItems().add(str); //Adding string to display in List view
    }


    //Display Case
    @FXML
    void addCase(MouseEvent event) {
        String id = CaseLetter.getValue() + "" + CaseNumber.getValue(); //creating a String by combining CaseLetter and CaseNumber
        //if statement not allowing a case to be created with the same ID
        if (caseID.contains(id)) { caseID += "";
        } else {
            DisplayCase dc1 = new DisplayCase(CaseLetter.getValue(), CaseNumber.getValue(), pickLit.getValue(), pickMounted.getValue()); //creating a type DisplayCase object for easier adding to Strings & LL
            list.add(dc1); //adding to LL
            pickCase.getItems().add(dc1); //adding cd1 to pickCase ComboBox
            pickDCase.getItems().add(dc1); //adding dc1 to pickDCase ComboBox
            showCase.getItems().add(dc1); //adding id to showCase ComboBox
            //str used to creat a list of all display cases to add to dc ListView
            String str = "Display Case: " + CaseLetter.getSelectionModel().getSelectedItem() + "" + CaseNumber.getSelectionModel().getSelectedItem() + " " + pickLit.getSelectionModel().getSelectedItem() + " " + pickMounted.getSelectionModel().getSelectedItem() + " ";
            dc.getItems().addAll(str);
            caseID += CaseLetter.getValue() + "" + CaseNumber.getValue() + " "; //adding ID's of DC to make sure that two aren't created with the same ID
            caseToValue.getItems().add(dc1);
        }
    }
    @FXML
    void reset(MouseEvent event){
        list.delAll();//making the head null meaning all nodes are deleted

        //clearing all choice boxes and lists
        //Display Case tab
        dc.getItems().clear();

        //Display Tray tab
        pickCase.getItems().clear();
        showCase.getItems().clear();
        dt.getItems().clear();

        //Items tab
        pickDCase.getItems().clear();
        pickTray.getItems().clear();
        viewItems.getItems().clear();

        //Components tab
        pickItem.getItems().clear();
        viewMaterial.getItems().clear();

        totalPrice = 0;
    }
    @FXML
    void valueCase(MouseEvent event){
        caseValue.getItems().clear();
        DisplayCase dispCase = caseToValue.getSelectionModel().getSelectedItem();
        dispCase.caseValue();
        if(caseToValue.getSelectionModel().getSelectedItem() != null) {
            String caseV = "$" + caseToValue.getSelectionModel().getSelectedItem().casePrice;
            caseValue.getItems().add(caseV);
        }
    }



    //Display Tray
    @FXML
    void addTray(MouseEvent event) {
        String identifier = pickLetter.getValue() + "" + pickNum.getValue();
        DisplayTray tray = new DisplayTray(pickLetter.getValue(), pickNum.getValue(), pickColour.getValue(), pickWidth.getValue(), pickDepth.getValue());
        //if statements to not allow a tray with the same ID to be added in any DC
        if (trayID != "") {
            if (trayID.contains(identifier)) {
                trayID += "";
            } else {
                DisplayCase displayCaseChosen = pickCase.getSelectionModel().getSelectedItem(); //creating DisplayCase type to be able to add a Tray to a chosen one
                displayCaseChosen.addTray(tray);
                trayID += pickLetter.getValue() + "" + pickNum.getValue() + " "; //recording Tray ID's as to not have two with the same ID in the whole system no matter in what DC
                chooseTray.getItems().add(tray);
                trayToValue.getItems().add(tray);
            }
        } else { //if trayID id is empty add displayTray to chosen DC
            DisplayCase displayCaseChosen = pickCase.getSelectionModel().getSelectedItem();
            displayCaseChosen.addTray(tray);
            trayID += pickLetter.getValue() + "" + pickNum.getValue() + " ";
            chooseTray.getItems().add(tray);
            trayToValue.getItems().add(tray);
        }
    }
    @FXML
    void listTrays(MouseEvent event){ //Setting up listView for Trays for a chosen DisplayCase
        String str = "";
        dt.getItems().clear(); //clears display tray list so we can add trays to list for a chosen case
        DisplayCase displayCaseChosen = showCase.getSelectionModel().getSelectedItem();
        str += displayCaseChosen.displayTrays.listAll();
        dt.getItems().addAll(str);
    }
    @FXML
    void populateTray() {
        pickTray.getItems().clear(); //clears pickTray list
        DisplayCase chosenCase = pickDCase.getSelectionModel().getSelectedItem(); //sets a chosenCase
        if (chosenCase != null) {//returning Trays from Chosen DC to Tray ComboBox
            Node temp = chosenCase.displayTrays.getHead();
            while (temp != null) {
                pickTray.getItems().add((DisplayTray) temp.getData());
                temp = temp.getNext();
            }
        }
    }
    @FXML
    void valueTray(MouseEvent event){
        trayValue.getItems().clear();
        if(trayToValue.getSelectionModel().getSelectedItem() != null) {
            String trayV = "$" + trayToValue.getSelectionModel().getSelectedItem().priceTray();
            trayValue.getItems().add(trayV);
        }
    }



    //Item
    @FXML
    void addItem(MouseEvent event){
        int tPrice = Integer.parseInt(rPrice.getValue()); //changing rPrice from string to int for later calculations and to input into constructor
        Items item = new Items(typeDesc.getText(),itemType.getValue(),gender.getValue(),tPrice);
        DisplayTray displayTrayChosen = pickTray.getSelectionModel().getSelectedItem(); //setting the chosen display Tray to be able to add Item to it
        displayTrayChosen.addItem(item); //adding item to chosen tray
        totalPrice += tPrice; //adding the retail price of item to totalPrice of all stock
        pickItem.getItems().add(item);//populating the choice box with each added item

        displayTrayChosen.priceUp(tPrice);
        //itemToDel.getItems().add(item);
    }
    @FXML
    void listSelected(MouseEvent event){
        viewItems.getItems().clear();
        DisplayTray displayTrayChosen = pickTray.getSelectionModel().getSelectedItem();
        String it = displayTrayChosen.items.listAll(); //adding all items in chosen tray to str
        viewItems.getItems().add(it);//adding str to ListView of viewItems
    }
    @FXML
    void delete(MouseEvent event){
        itemToDel.getSelectionModel().getSelectedItem();
        int index = itemToDel.getSelectionModel().getSelectedIndex();
        DisplayTray tray = chooseTray.getSelectionModel().getSelectedItem();
        int price = itemToDel.getSelectionModel().getSelectedItem().getrPrice();
        totalPrice -= price;
        tray.items.deleteNode(index);
        pickItem.getItems().remove(index);
        tray.priceDown(price);
    }
    @FXML
    void populateItem(MouseEvent event){
        itemToDel.getItems().clear(); //clears itemToDel list
        DisplayTray chosenTray = chooseTray.getSelectionModel().getSelectedItem(); //sets a chosenCase
        if (chosenTray != null) {//returning Items from Chosen Tray to Item ComboBox
            Node temp = chosenTray.items.getHead();
            while (temp != null) {
                itemToDel.getItems().add((Items) temp.getData());
                temp = temp.getNext();
            }
        }
    }



    //Materials
    @FXML
    void addMaterial(MouseEvent event){
        viewMaterial.getItems().clear();
        int w = Integer.parseInt(weight.getValue());
        int q = Integer.parseInt(quality.getValue());
        Components cp = new Components(type.getValue(), materialDesc.getText(), w, q);
        Items itemChosen = pickItem.getSelectionModel().getSelectedItem();
        itemChosen.addComponent(cp);
        String mat = "Components: " + '\n' + itemChosen.components.listAll();
        viewMaterial.getItems().add(mat);
    }



    //Inspect Stock
    @FXML
    void viewAllStock(MouseEvent event) {
        //Inspect Store
        TreeItem rootItem = new TreeItem<>("Display Cases: ");

        viewAll.setRoot(rootItem);

        String str = "   Trays:";
        String str1 = "    Items:";

        for(int i = 0; i < list.numNodes(); i++) {
            DisplayCase dc = (DisplayCase) list.get(i);
            TreeItem treeCase = new TreeItem(dc + str);
            rootItem.getChildren().add(treeCase);
            for (int j = 0; j < dc.displayTrays.numNodes(); j++){
                DisplayTray dt = (DisplayTray) dc.displayTrays.get(j);
                TreeItem treeTray = new TreeItem(dt + str1);
                treeCase.getChildren().add(treeTray);
                for(int k = 0; k < dt.items.numNodes(); k++){
                    Items items = (Items) dt.items.get(k);
                    TreeItem treeItem = new TreeItem(items);
                    treeTray.getChildren().add(treeItem);
                }
            }
        }
    }
    @FXML
    void getItemComponents(MouseEvent event) {

//        if(pickItem.getItems().contains(viewAll.getSelectionModel().getSelectedItem())){
//            Items item = (Items) viewAll.getSelectionModel().getSelectedItem();
//            for(int i = 0; i < item.components.numNodes(); i++){
//                Components ct = (Components) item.components.get(i);
//                compList.getItems().add(ct);
//            }
//        }
        //compList.getItems().add(new Components("A", "b", 1, 1));
//        System.out.println(viewAll.getSelectionModel().getSelectedItem());
        compList.getItems().clear();
        if (viewAll.getSelectionModel().getSelectedItem() != null) {
            for (int i = 0; i < list.numNodes(); i++) {
                DisplayCase dc = (DisplayCase) list.get(i);
                for (int j = 0; j < dc.displayTrays.numNodes(); j++) {
                    DisplayTray dt = (DisplayTray) dc.displayTrays.get(j);
                    for (int k = 0; k < dt.items.numNodes(); k++) {
                        Items items = (Items) dt.items.get(k);
                        String str = viewAll.getSelectionModel().getSelectedItem().toString();
                        String str1 = items.toString();
                        if (str.contains(str1)) {
                            for (int l = 0; l < items.components.numNodes(); l++) {
                                Components cp = (Components) items.components.get(l);
                                compList.getItems().add(cp);
                            }
                        }
                    }

                }
            }
        }
    }


    public void initialize() {
        //Display Case
        CaseLetter.getItems().addAll("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
        CaseNumber.getItems().addAll( "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16");
        pickLit.getItems().addAll(" Lit"," Un-Lit");
        pickMounted.getItems().addAll(" Wall Mounted", " Free-Standing");

        //Display Tray
        pickLetter.getItems().addAll("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
        pickNum.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16");
        pickColour.getItems().addAll("Ocean Blue", "Army Green", "Night Black", "Carat Gold", "Mars Red", "Coral Purple");
        pickDepth.getItems().addAll("8 cm" , "9 cm"  , "10 cm" , "11 cm", "12 cm", "13 cm", "14 cm", "15 cm");
        pickWidth.getItems().addAll("8 cm" , "9 cm" , "10 cm", "11 cm", "12 cm", "13 cm", "14 cm", "15 cm" );

        //Item
        itemType.getItems().addAll("Watch","Ring","Necklace", "Earring","Glass Eye", "Chain");
        gender.getItems().addAll("Men","Women", "All");
        rPrice.getItems().addAll("100","200","300","400","500","600","700","800","900","1000");
        rPrice.setEditable(true);

        //Component
        type.getItems().addAll("Gold", "Silver","Stainless Steel", "Diamond", "Ruby", "Sapphire", "Emerald");
        weight.getItems().addAll("10", "11", "12", "13", "14", "15", "16");
        weight.setEditable(true);
        quality.getItems().addAll("1", "2", "3", "4", "5", "6","7","8","9","10", "11", "12", "13", "14", "15", "16");
        quality.setEditable(true);

    }


    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{DisplayCase.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader(fileName()));
        developers = (List<Developer>) in.readObject();
        in.close();
    }
}