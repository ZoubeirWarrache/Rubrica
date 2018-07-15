
package rubrica.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import rubrica.model.Contatto;

/**
 * Controllo per ricerca CAP (codice di avviamento postale) .
 */
public class RicercaCAPController  {

    @FXML
    private TableView<Contatto> tabellaCAP;
    
    @FXML
    private TableColumn<Contatto , String> colonnaCognome;
    
    @FXML
    private TableColumn<Contatto , String> colonnaNome;
     
    @FXML
    private TableColumn<Contatto , Integer> colonnaCAP;
   
    /**
     * Inizializza controllo .
     */
    @FXML
    public void initialize() {
        
        // Initializzza tabella a tre colonne per contatti 
        this.colonnaCognome.setCellValueFactory(cellData -> cellData.getValue().cognomeProperty());
        this.colonnaNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        this.colonnaCAP.setCellValueFactory(cellData -> cellData.getValue().CAPProperty().asObject());
    }    
    
    /**
     * Seleziona contatti dalla lista  
     * 
     * @param lista lista contatti
     */
    public void setContatti(ObservableList<Contatto> lista)
    {
        // aggiunge alla tabella lista dati
        tabellaCAP.setItems(lista);   
    }
}
