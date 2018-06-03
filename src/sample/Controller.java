package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import jfxtras.scene.control.LocalDateTimeTextField;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable{
    @FXML
    private GridPane pane;

    @FXML
    private ComboBox<String> store;

    @FXML
    private TextField firstName, lastName, address, city, state, zipcode, phone, email;

    @FXML
    private TextField url;

    @FXML
    private Button submit, start;

    @FXML
    private Label error;

    @FXML
    private LocalDateTimeTextField date;








    private Properties user = new Properties();

    public Controller() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample/applet.fxml"));
        loader.setRoot(this);

    }

    public void initialize(URL url, ResourceBundle rb) {
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("user.properties");
            user.load(input);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        List<String> tempStoreList = new ArrayList<String>(Arrays.asList("Adidas", "Supreme", "Nike"));
        ObservableList<String> storeList = FXCollections.observableList(tempStoreList);
        store.setItems(storeList);
        firstName.setText(user.get("FirstName")+"");
        lastName.setText(user.get("LastName")+"");
        address.setText(user.get("Address")+"");
        city.setText(user.get("City")+"");
        state.setText(user.get("State")+"");
        zipcode.setText(user.get("ZipCode")+"");
        phone.setText(user.get("PhoneNumber")+"");
        email.setText(user.get("Email")+"");





    }
    public void submitClicked()
    {


        user.setProperty("FirstName", firstName.getText());
        user.setProperty("LastName", lastName.getText());
        user.setProperty("Address", address.getText());
        user.setProperty("City", city.getText());
        user.setProperty("State", state.getText());
        user.setProperty("ZipCode", zipcode.getText());
        user.setProperty("PhoneNumber", phone.getText());
        user.setProperty("Email", email.getText());
        user.setProperty("Url", url.getText());
        try {
            user.store(new FileOutputStream("src/user.properties"),null);

        }catch (IOException e)
        {
            System.out.println(e);
        }
        start.setDisable(false);


    }
    public void startClicked()
    {

        if(store.getValue().equals("Adidas"))
        {
            if(user.getProperty("Url").isEmpty()) error.setText("Invalid Url");
            else
            Purchaser.timeTest(url.getText(),date.getText());
        }
        else error.setText("Store not setup");
    }
    public void storeHandler()
    {

        if(store.getValue().equals("Adidas")) {
            url.setVisible(true);
        }
        else error.setText("Store not setup");
    }

}
