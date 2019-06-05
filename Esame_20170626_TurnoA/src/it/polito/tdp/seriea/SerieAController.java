/**
 * Sample Skeleton for 'SerieA.fxml' Controller Class
 */

package it.polito.tdp.seriea;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class SerieAController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="boxSquadra"
	private ChoiceBox<?> boxSquadra; // Value injected by FXMLLoader

	@FXML // fx:id="boxStagione"
	private ChoiceBox<?> boxStagione; // Value injected by FXMLLoader

	@FXML // fx:id="btnCalcolaConnessioniSquadra"
	private Button btnCalcolaConnessioniSquadra; // Value injected by FXMLLoader

	@FXML // fx:id="btnSimulaTifosi"
	private Button btnSimulaTifosi; // Value injected by FXMLLoader

	@FXML // fx:id="btnAnalizzaSquadre"
	private Button btnAnalizzaSquadre; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
	void doAnalizzaSquadre(ActionEvent event) {

	}

	@FXML
	void doCalcolaConnessioniSquadra(ActionEvent event) {

	}

	@FXML
	void doSimulaTifosi(ActionEvent event) {

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert boxSquadra != null : "fx:id=\"boxSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
		assert boxStagione != null : "fx:id=\"boxStagione\" was not injected: check your FXML file 'SerieA.fxml'.";
		assert btnCalcolaConnessioniSquadra != null : "fx:id=\"btnCalcolaConnessioniSquadra\" was not injected: check your FXML file 'SerieA.fxml'.";
		assert btnSimulaTifosi != null : "fx:id=\"btnSimulaTifosi\" was not injected: check your FXML file 'SerieA.fxml'.";
		assert btnAnalizzaSquadre != null : "fx:id=\"btnAnalizzaSquadre\" was not injected: check your FXML file 'SerieA.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SerieA.fxml'.";

	}
}
