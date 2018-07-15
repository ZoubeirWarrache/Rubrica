
package rubrica.controller;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import rubrica.model.Contatto;
import rubrica.model.util.Filtro;

/**
 * Controllo per ricerca maschi .
 * 
 */
public class RicercaMaschiController  {
    
    // tabella
    @FXML
    private TableView<Contatto> tabellaMaschi;
    
    @FXML
    private TableColumn<Contatto , String> colonnaCognome;
    
    @FXML
    private TableColumn<Contatto , String> colonnaNome;
     
    @FXML
    private TableColumn<Contatto , String> colonnaSesso;
    
    /**
     * Inizializza controllo .
     */
    @FXML
    private void initialize()
    {
        // Initializzza tabella a tre colonne per contatti 
        this.colonnaCognome.setCellValueFactory(cellData -> cellData.getValue().cognomeProperty());
        this.colonnaNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        this.colonnaSesso.setCellValueFactory(cellData -> cellData.getValue().sessoProperty());

    }
    
    /**
     * Dalla lista contatti seleziona nominativi maschi 
     * 
     * @param lista lista contatti
     */
    public void setContattiMaschi(ObservableList<Contatto> lista)
    {
       ObservableList<Contatto> estratti =  Filtro.filtraContatti(lista, (Contatto C)-> C.getSesso().equals("M"));
        
       // aggiunge alla tabella lista dati
        tabellaMaschi.setItems(estratti);
    }
    
}
