package rubrica.controller;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

import rubrica.RubricaMain;
import rubrica.model.Contatto;
import rubrica.model.util.DateUtil;

/**
 * Controllo per root .
 * 
 */
public class RootLayoutController  {
    
    // tabella 
    @FXML
    private TableView<Contatto> tabellaContatti;
    
    @FXML
    private TableColumn<Contatto , String> colonnaCognome;
    
    @FXML
    private TableColumn<Contatto , String> colonnaNome;
    
    // label
    @FXML
    private Label labelCognome; 
    @FXML
    private Label labelNome;
    @FXML
    private Label labelSesso;
    @FXML
    private Label labelDataNascita;
    @FXML
    private Label labelNumeroCellulare;
    @FXML
    private Label labelCittaResidenza;
    @FXML
    private Label labelIndirizzo;
    @FXML
    private Label labelCAP;
    
    // per il collegamento al main 
    private RubricaMain appMain;
    
    /**
     * Initializza il controllo .
     */
    @FXML
    private void initialize()
    {
        // Initializzza tabella a due colonne per contatti 
        this.colonnaCognome.setCellValueFactory(cellData -> cellData.getValue().cognomeProperty());
        this.colonnaNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        
        // Pulisce dettagli
        showDettagliContatti(null);
        
        // Ascolta per selezione contatto e mostra dati aggiornati 
        tabellaContatti.getSelectionModel().selectedItemProperty().addListener((dati, vecchioValore, nuovoValore) -> showDettagliContatti(nuovoValore));
    }
    
    
    /**
     * Permette collegamento main con controllo 
     * 
     * @param pAppMain istanza di RubricaMain 
     */
    public final void setAppMain(RubricaMain pAppMain)
    {
        this.appMain = pAppMain;
        
        // aggiunge alla tabella lista dati
        tabellaContatti.setItems(appMain.getListaContatti());
    }
    
   
    /**
     * Mostra i dati di un contatto nei campi di testo , se contatto null
     * si cancelano tutti i campi .
     * 
     * @param contatto oggetto di tipo Contatto o null
     */ 
    private void showDettagliContatti(Contatto contatto) {
        
        if(contatto != null)
        {
            // scrive in label dati contatto
            labelCognome.setText(contatto.getCognome());
            labelNome.setText(contatto.getNome());
            labelSesso.setText(contatto.getSesso());
            labelDataNascita.setText(DateUtil.format(contatto.getDataNascita()));
            labelNumeroCellulare.setText(Long.toString(contatto.getNumTelefono()));
            labelCittaResidenza.setText(contatto.getCittaResidenza());
            labelIndirizzo.setText(contatto.getIndirizzoResidenza());
            labelCAP.setText(Integer.toString(contatto.getCAP()));
            
        }
        else
        {
            // rimuove dati se contatto null
            labelCognome.setText("");
            labelNome.setText("");
            labelSesso.setText("");
            labelDataNascita.setText("");
            labelNumeroCellulare.setText("");
            labelCittaResidenza.setText("");
            labelIndirizzo.setText("");
            labelCAP.setText("");
            
        }
        
    }
    
    /**
     * Crea rubrica vuota da menu File -> Nuovo.
     */
    @FXML
    private void handleNuovo()
    {
        appMain.getListaContatti().clear();
        appMain.setFilePathContatti(null);
    }
    
    /**
     * Apre una finestra per selezionare file rubrica da menu File -> Apri
     * 
     * @see javafx.stage.FileChooser
     */
    @FXML
    private void handleApri(){
        FileChooser fileChooser = new FileChooser();
        
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Documents/my rubrica/"+appMain.folderUtente));
        
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Files(*.xml)","*.xml");
        
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = fileChooser.showOpenDialog(appMain.getRubricaStage());
        
        if(file != null)
        {
            appMain.caricaDaFileDatiContatti(file);
        }
    }
    
    /**
     * Salva su disco file dati contatti aperto da menu File -> Salva .
     * Se file non aperto invoca SalvaCome .
     */
    @FXML
    private void handleSalva(){
        File contFile = appMain.getFilePathContatti();
        if(contFile != null)
        {
            appMain.salvaSuFileDatiContatti(contFile);
        }
        else
            handleSalvaCome();
    }
    
    /**
     * Apre finestra per la selezione file su cui salvare dati da File -> Salva come .
     */
    @FXML
    private void handleSalvaCome() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Documents/my rubrica/"+appMain.folderUtente));
        
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Files(*.xml)","*.xml");
        
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = fileChooser.showSaveDialog(appMain.getRubricaStage());
        
        if(file != null)
        {
            if(!file.getPath().endsWith(".xml"))
                file = new File(file.getPath() + ".xml");
            
            appMain.salvaSuFileDatiContatti(file);
        }
    }
    
    /**
     * Apre una tabella dei nominativi di sesso maschile da Ricerca -> Nominativi di sesso maschile .
     */
    @FXML
    private void handleNominativiSessoMaschile() 
    {
        appMain.showRicercaMaschi();
    }
    
    /**
     * Apre una tabella dei nominativi di sesso femminile da Ricerca -> Nominativi di sesso femminile .
     */
    @FXML
    private void handleNominativiSessoFemminile() 
    {
        appMain.showRicercaFemine();
    }
    
    /**
     * Apre una tabella dei nominativi residenti in specifiche aree postali
     * da Ricerca -> Nominativi residenti in specifiche aree postali .
     */
    @FXML
    private void handleNominativiResidentiAreePostali() 
    {
        appMain.showRicercaCAP();
    }
    
    /**
     * Apre dialogo per informazioni sugli sviluppatori del programma 
     * da Help -> Sviluppatori del programma .
     */
    @FXML
    private void handleSviluppatoriProgramma(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.initOwner(appMain.getRubricaStage());
        alert.setTitle("informazioni");
        alert.setHeaderText("Sviluppatore di programma");
        alert.setContentText("\nNome e Cognome : Zoubeir Warrache "+
                             "\nData di nascita       : 28.02.1996 "+
                             "\nUniversità               : Universita di bologna"+
                             "\nCorso                      : informatica per il management ");
        alert.showAndWait();
    }
    
    /**
     * Apre dialogo per informazioni sul funzionamento del programma 
     * da Help -> Funzionamento del programma .
     */
    @FXML
    private void handleFunzionamentoProgramma(){
    
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.initOwner(appMain.getRubricaStage());
        alert.setTitle("informazioni");
        alert.setHeaderText("Funzionamento del programma");
        alert.setContentText("Nella finestra principale c'è un menu che permette :\n" +
                             "• nella voce File di scegliere:\n" +
                             "– se il file è nuovo, Nuovo;\n" +
                             "– se il file è da aprire, Apri;\n" +
                             "– se il file è da salvare, Salva;\n" +
                             "– se il file è da salvare con nome, Salva come;\n" +
                             "• nella voce Ricerca di scegliere:\n" +
                             "– se visualizzare elenco di nominativi di sesso maschile;\n" +
                             "– se visualizzare elenco di nominativi di sesso femminile;\n" +
                             "– se visualizzare elenco di nominativi residenti in specifiche aree postali ;\n" +
                             "• nella voce Help visualizzare:\n" +
                             "– l’informazione sugli sviluppatori del programma ;\n" +
                             "– l’informazione sul funzionamento del programma ;\n" +
                             "Al disotto del menu , c'è a sinistra , l’elenco di contatti per nome e cognome; a destra ,\n" +
                             "i dati del contatto selezionato nella lista di contatti .\n" +
                             "Nel'area in basso ci sono tre pulsanti, Aggiungi per aggiungere nuovi nominativi ,\n" +
                             "Edita per modificare dati di un contatto selezionato nel' elenco di contatti , Rimuovi per\n" +
                             "rimuovere nominativi selezionati nello stesso elenco .");
        alert.showAndWait();
    }

    /**
     * Invocato quando utente preme tasto Aggiungi , si apre finestra 
     * per inserire dettagli di un contatto .
     */
    @FXML
    private void handleAggiungi() 
    {
        Contatto contatto = new Contatto();
        boolean salvaClick = appMain.showDialogoContatti(contatto);
        
        if(salvaClick)
            appMain.getListaContatti().add(contatto);
    }
    
    /**
     * Invocato quando utente preme tasto Edita , si apre finestra 
     * per modificare dettagli del conttato selezionato .
     */
    @FXML
    private void handleEdita() 
    {
        Contatto selected = tabellaContatti.getSelectionModel().getSelectedItem();
        if(selected != null)
        {
            boolean salvaclick = appMain.showDialogoContatti(selected);
            if(salvaclick)
                showDettagliContatti(selected);
        }
        else
        {
            // nessuna selezione
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(appMain.getRubricaStage());
            alert.setTitle("nessuna selezione");
            alert.setHeaderText("nessuno contatto selezionato");
            alert.setContentText("selezionare uno studente dalla tabella");
            alert.showAndWait();
        }
    }
    
    /**
     * Invocato quando utente preme tasto Rimuovi .
     */
    @FXML 
    private void handleRimuovi(){
        int indice = tabellaContatti.getSelectionModel().getSelectedIndex();
        if(indice >= 0)
        {
            tabellaContatti.getItems().remove(indice);
        }
        else
        {
            // nessuna selezione
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(appMain.getRubricaStage());
            alert.setTitle("nessuna selezione");
            alert.setHeaderText("nessuno contatto selezionato");
            alert.setContentText("selezionare uno studente dalla tabella");
            alert.showAndWait();
        }
    }
    
}

