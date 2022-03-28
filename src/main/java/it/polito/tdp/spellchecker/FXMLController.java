package it.polito.tdp.spellchecker;

import java.net.URL;

import dizionario.Dizionario;
import dizionario.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.util.*;

public class FXMLController {
Dizionario model;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbLanguage;

    @FXML
    private Label txtCount;

    @FXML
    private TextArea txtInput;

    @FXML
    private TextArea txtResult;

    @FXML
    private Label txtTime;

    @FXML
    void handleCheck(ActionEvent event) {
    	 
    	String lingua = cmbLanguage.getValue();
    	model.loadDictionary(lingua);
    	
    	String input = txtInput.getText();
    	input = input.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    	LinkedList<RichWord> resultSet = (LinkedList<RichWord>) model.spellCheck(input);
    	
    	String result = "";
    	for(RichWord r : resultSet) {
    		if(r.correct == true) 
    			result += r.word+": correct\n"; 
    		else result += r.word+": incorrect\n";
    	}
  
    	txtResult.setText(result);
    	txtCount.setText("NUMERO ERRORI: "+resultSet.size());
    	
    	
    }

    @FXML
    void handleClear(ActionEvent event) {
    	txtResult.clear();
    	txtInput.clear();
    	txtTime.setText("TIME");
    	txtCount.setText("NO ERRORS YET");
    	cmbLanguage.getItems().clear();
    }


    @FXML
    void initialize() {
        assert cmbLanguage != null : "fx:id=\"cmbLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCount != null : "fx:id=\"txtCount\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'Scene.fxml'.";

        cmbLanguage.getItems().clear();
        cmbLanguage.getItems().add("Italian");
        cmbLanguage.getItems().add("English");
        
        
    }
    public void setModel(Dizionario model) {
    	this.model = model;
    }

}
