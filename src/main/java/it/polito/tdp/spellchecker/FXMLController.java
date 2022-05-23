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
    	
    	long start = System.nanoTime();
    	
    	String lingua = cmbLanguage.getValue();
    	model.loadDictionary(lingua);
    	
    	String input = txtInput.getText();
    	input = input.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
    	//LinkedList<RichWord> resultSet = (LinkedList<RichWord>) model.spellCheck(input);
    	LinkedList<RichWord> resultSet = (LinkedList<RichWord>) model.spellCheclDicotomic(input);
    	
    	String result = "";
    	for(RichWord r : resultSet) {
    		if(r.isCorrect() == true) 
    			result += r.getWord()+": correct\n"; 
    		else result += r.getWord()+": incorrect\n";
    	}
  
    	txtResult.setText(result);
    	int err = 0;
    	for(RichWord r : resultSet) {
    		if(r.isCorrect() == false)
    			err ++;
    	}
    	txtCount.setText("NUMERO ERRORI: "+err);
    	
    	long end = System.nanoTime();
    	long time = end-start;
    	
    	txtTime.setText("TIME: "+time);	
    	
    }

    @FXML
    void handleClear(ActionEvent event) {
    	txtResult.clear();
    	txtInput.clear();
    	txtTime.setText("TIME");
    	txtCount.setText("NO ERRORS YET");
    	cmbLanguage.getItems().clear();
    	cmbLanguage.getItems().add("Italian");
        cmbLanguage.getItems().add("English");
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
