package com.asgn.algorithmasgn1;

import com.thoughtworks.xstream.XStream;
import Resources.LinkedList;
import Resources.Node;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.Components;
import models.DisplayCase;
import models.DisplayTray;
import models.Items;

import java.io.*;


public class CaseController {
    public static LinkedList<DisplayCase> list = new LinkedList<>(); //LL to store Display Cases

    private String trayID = ""; //String used to store all ID's of Trays in the system
    private String caseID = ""; //String used to store all ID's of Cases in the system
    private int totalPrice; //Int used to calculate the total price of all stock in the system


    //https://m.media-amazon.com/images/I/71TsTgt2ecL._AC_UL1000_.jpg

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
    @FXML
    private TextField imageUrl;



    //Components tab
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
    @FXML
    private ComboBox<Items> chooseItem;
    @FXML
    private ImageView itemImage;


    //Inspect Stock
    @FXML
    private TreeView viewAll;
    @FXML
    private ListView<Components> compList;
    @FXML
    private ListView<String> searchView;
    @FXML
    private TextField keywords;





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
        Items item = new Items(typeDesc.getText(),itemType.getValue(),gender.getValue(),tPrice, imageUrl.getText());
        DisplayTray displayTrayChosen = pickTray.getSelectionModel().getSelectedItem(); //setting the chosen display Tray to be able to add Item to it
        displayTrayChosen.addItem(item); //adding item to chosen tray
        totalPrice += tPrice; //adding the retail price of item to totalPrice of all stock
        pickItem.getItems().add(item);//populating the choice box with each added item
        chooseItem.getItems().add(item);

        displayTrayChosen.priceUp(tPrice);
    }
    @FXML
    void listSelected(MouseEvent event){
        viewItems.getItems().clear();
        DisplayTray displayTrayChosen = pickTray.getSelectionModel().getSelectedItem();
        String it = displayTrayChosen.items.listAll(); //adding all items in chosen tray to str
        viewItems.getItems().add(it);//adding str to ListView of viewItems

        pickItem.getItems().clear();
        chooseItem.getItems().clear();
        for(int i = 0; i < list.numNodes(); i++){
            DisplayCase dc = (DisplayCase) list.get(i);
            for(int j = 0; j < dc.displayTrays.numNodes(); j++){
                DisplayTray dt = (DisplayTray) dc.displayTrays.get(j);
                for(int k = 0; k < dt.items.numNodes(); k++){
                    Items item = (Items) dt.items.get(k);
                    pickItem.getItems().add(item);
                    chooseItem.getItems().add(item);
                }
            }
        }
    }
    @FXML
    void delete(MouseEvent event){
        itemToDel.getSelectionModel().getSelectedItem();
        int index = itemToDel.getSelectionModel().getSelectedIndex();
        DisplayTray tray = chooseTray.getSelectionModel().getSelectedItem();
        int price = itemToDel.getSelectionModel().getSelectedItem().getrPrice();
        totalPrice -= price;
        tray.items.deleteNode(index);
//        pickItem.getItems().clear();
//        pickItem.getItems().remove(index);
//        chooseItem.getItems().remove(index);
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
    @FXML
    void smartAdd(MouseEvent event) {
        boolean it = false;
        int tPrice = Integer.parseInt(rPrice.getValue()); //changing rPrice from string to int for later calculations and to input into constructor
        Items itemToAdd = new Items(typeDesc.getText(), itemType.getValue(), gender.getValue(), tPrice, imageUrl.getText());

        if (!it) {
            for (int i = 0; i < list.numNodes(); i++) {
                DisplayCase dc = (DisplayCase) list.get(i);
                if (!it) {
                    for (int j = 0; j < dc.displayTrays.numNodes(); j++) {
                        DisplayTray dt = (DisplayTray) dc.displayTrays.get(j);
                        if (dc.displayTrays.numNodes() == 1) {
                            dt.items.add(itemToAdd);
                            totalPrice += tPrice;
                            it = true;
                        }
                        if (it == false) {
                            for (int k = 0; k < dt.items.numNodes(); k++) {
                                Items item = (Items) dt.items.get(k);
                                if (it == false) {
                                    String str = itemType.getValue() + "";
                                    if (item.getType().contains(str)) {
                                        dt.items.add(itemToAdd);
                                        totalPrice += tPrice;
                                        it = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if(it == false) {
            for (int i = 0; i < list.numNodes(); i++) {
                DisplayCase dc = (DisplayCase) list.get(i);
                for (int j = 0; j < dc.displayTrays.numNodes(); j++) {
                    if(it == false) {
                        DisplayTray dt = (DisplayTray) dc.displayTrays.get(j);
                        dt.items.add(itemToAdd);
                        totalPrice += tPrice;
                        it = true;
                    }
                }
            }
        }
    }



    //Materials
    @FXML
    void addMaterial(MouseEvent event){
        int w = Integer.parseInt(weight.getValue());
        int q = Integer.parseInt(quality.getValue());
        Components cp = new Components(type.getValue(), materialDesc.getText(), w, q);
        Items itemChosen = pickItem.getSelectionModel().getSelectedItem();
        itemChosen.addComponent(cp);
    }
    @FXML
    void showMaterials(MouseEvent event) throws FileNotFoundException {
        viewMaterial.getItems().clear(); //clearing the viewMaterials list view on button click
        Items item =  chooseItem.getSelectionModel().getSelectedItem();
        String cp = "Components: " + '\n'+ "  " + item.components.listAll();//Creating the string to be used in the list View
        viewMaterial.getItems().add(cp);//adding to Materials listView
        String url = item.getImageURl();//creating the string url by fetching the item Url for chosen item
        Image image = new Image(url);
        itemImage.setImage(image);
    }



    //Inspect Stock
    @FXML
    void viewAllStock(MouseEvent event) {
        //Inspect Store

        if(list.numNodes() != 0) {
            TreeItem rootItem = new TreeItem<>("Display Cases: ");

            viewAll.setRoot(rootItem);

            String str = "   Trays:";
            String str1 = "    Items:";

            for (int i = 0; i < list.numNodes(); i++) {
                DisplayCase dc = (DisplayCase) list.get(i);
                TreeItem treeCase = new TreeItem(dc + str);
                rootItem.getChildren().add(treeCase);
                for (int j = 0; j < dc.displayTrays.numNodes(); j++) {
                    DisplayTray dt = (DisplayTray) dc.displayTrays.get(j);
                    TreeItem treeTray = new TreeItem(dt + str1);
                    treeCase.getChildren().add(treeTray);
                    for (int k = 0; k < dt.items.numNodes(); k++) {
                        Items items = (Items) dt.items.get(k);
                        TreeItem treeItem = new TreeItem(items);
                        treeTray.getChildren().add(treeItem);
                        for (int l = 0; l < items.components.numNodes(); l++) {
                            Components comp = (Components) items.components.get(l);
                            TreeItem compItem = new TreeItem(comp);
                            treeItem.getChildren().add(compItem);
                        }
                    }
                }
            }
        }else {
            TreeItem root = new TreeItem<>("No data inserted ");
            viewAll.setRoot(root);
        }
    }

    @FXML
    private void expandTree(MouseEvent event){
        TreeItem treeRoot = viewAll.getRoot();
        expandTreeView(treeRoot);
    }



    private void expandTreeView(TreeItem<?> item){
        if(item != null && !item.isLeaf()){
            item.setExpanded(true);
            for(TreeItem<?> child :item.getChildren()){
                expandTreeView(child);
            }
        }
    }
    @FXML
    void search(MouseEvent event) {
        //keywords searchView
        searchView.getItems().clear();
        for (int i = 0; i < list.numNodes(); i++) {//scrolling through dc
            DisplayCase dc = (DisplayCase) list.get(i);
            for (int j = 0; j < dc.displayTrays.numNodes(); j++) {//scrolling through dt
                DisplayTray dt = (DisplayTray) dc.displayTrays.get(j);
                for (int k = 0; k < dt.items.numNodes(); k++) {//scrolling through items
                    Items items = (Items) dt.items.get(k);
                    String str = items.toString();
                    String str1 = keywords.getText();
                    if (str.contains(str1)) {
                        String allItems = dc + "  " + dt;
                        searchView.getItems().add(allItems);
                        searchView.getItems().add("   " + items);
                        String n = '\n' + "";
                        searchView.getItems().add(n);
                    }
                }
            }
        }
    }
    @FXML
    public void getItemComponents(MouseEvent event) {
        compList.getItems().clear();
        if (searchView.getSelectionModel().getSelectedItem() != null) {//if something is chosen
            for (int i = 0; i < list.numNodes(); i++) {//scrolling through dc
                DisplayCase dc = (DisplayCase) list.get(i);
                for (int j = 0; j < dc.displayTrays.numNodes(); j++) {//scrolling through dt
                    DisplayTray dt = (DisplayTray) dc.displayTrays.get(j);
                    for (int k = 0; k < dt.items.numNodes(); k++) {//scrolling through items
                        Items items = (Items) dt.items.get(k);
                        String str = searchView.getSelectionModel().getSelectedItem();
                        String str1 = items + "";

                        if (str.contains(str1)) {//if chosen item in searchView is the item currently that the loop is at
                            for (int l = 0; l < items.components.numNodes(); l++) {//scrolling through materials
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


    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("Jewellery.xml"));
        LinkedList<DisplayCase> list1 = list;
        out.writeObject(list1);
        out.close();
    }


    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[]{DisplayCase.class, DisplayTray.class, Items.class, Components.class, LinkedList.class, Node.class };

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader("Jewellery.xml"));
        list = (LinkedList<DisplayCase>) in.readObject();
        in.close();
    }
}