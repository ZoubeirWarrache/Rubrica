
package rubrica.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import rubrica.model.Contatto;
import rubrica.model.util.DateUtil;

/**
 * Controllo per edita contatti .
 * 
 */
public class DialogoEditaContattiController {
    
    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoCognome;
    @FXML
    private TextField campoDataNascita;
    @FXML
    private TextField campoSesso;
    @FXML
    private TextField campoNumTelefono;
    @FXML
    private TextField campoIndirizzoResidenza;
    @FXML
    private TextField campoCittaResidenza;
    @FXML
    private TextField campoCAP;
    
    private Stage dialogStage;
    private Contatto contatto;
    private boolean salvaClick = false;
    
    /**
     * Inizializza controllo . Invocato automaticamente dopo 
     * aver caricato file fxml 
     */
    @FXML
    private void inizialize(){}
    
    /**
     * Definisce lo starge del dialogo .
     * 
     * @param dialogoStage stage di dialogo per edita dati contatto
     */
    public final void setDialogStage(Stage dialogoStage)
    {
        this.dialogStage = dialogoStage;
    }
    
    /**
     * Definisce conttato da editare nel dialogo
     * 
     * @param contact oggetto di cui editare i campi di istanza
     */
    public final void setContatto(Contatto contact)
    {
        this.contatto =contact;
        campoCognome.setText(contatto.getCognome());
        campoNome.setText(contatto.getNome());
        campoSesso.setText(contatto.getSesso());
        campoNumTelefono.setText(Long.toString(contatto.getNumTelefono()));
        campoCittaResidenza.setText(contatto.getCittaResidenza());
        campoIndirizzoResidenza.setText(contatto.getIndirizzoResidenza());
        campoCAP.setText(Integer.toString(contatto.getCAP()));
        campoDataNascita.setText(DateUtil.format(contatto.getDataNascita()));
        campoDataNascita.setPromptText("dd.mm.yyyy");
        
    }
    
    /**
     * Ritorna true se utente preme pulsante Salva , false in caso contrario .
     * 
     * @return booleano per segnalare azione pulsante Salva
     */
    public boolean salvaClick()
    {
        return salvaClick;
    }
    
    /**
     * Invocato quando utente preme tasto Salva .
     */
    @FXML
    private void handleSalva()
    {
        if(verificaDatiInput())
        {
            
            contatto.setNome(campoNome.getText());
            contatto.setCognome(campoCognome.getText());
            contatto.setDataNascita(DateUtil.parse(campoDataNascita.getText()));
            contatto.setSesso(campoSesso.getText());
            contatto.setNumTelefono(Long.parseLong(campoNumTelefono.getText()));
            contatto.setIndirizzoResidenza(campoIndirizzoResidenza.getText());
            contatto.setCittaResidenza(campoCittaResidenza.getText());
            contatto.setCAP(Integer.parseInt(campoCAP.getText()));
            
            salvaClick = true;
            dialogStage.close();
            
        }
    }
    
    /**
     * Invocato quando utente preme tasto Annnulla .
     */
    @FXML
    private void handleAnnulla()
    {
        dialogStage.close();
    }
    
    /**
     * Verifica validita testo immesso da utente nei campi di testo 
     * 
     * @return true/false se input corretto/non corretto
     */
    private boolean verificaDatiInput()
    {
        String errorMessage = "";
        
        if(campoNome.getText()==null || campoNome.getText().length()==0)
            errorMessage += "Nome non inserito ! \n";
        
        if(campoCognome.getText()==null || campoCognome.getText().length()==0)
            errorMessage += "Cognoome non inserito ! \n";
        
        if(campoDataNascita.getText()==null || campoDataNascita.getText().length()==0)
        {  
            errorMessage += "Data di nascità non inserita ! \n";
        }
        else
        {
             if(!DateUtil.verificaData(campoDataNascita.getText()))
            { 
                errorMessage +="Data di nascità non valida ! "+
                             "inserirla in forma dd.MM.yyyy";
            }
        }
        if(campoSesso.getText()==null || campoSesso.getText().length()==0)
            errorMessage += "Sesso non inserito ! \n";
        
        if(campoNumTelefono.getText()==null || campoNumTelefono.getText().length()==0)
            errorMessage += "Numero di telefono non inserito ! \n";
        else
        {
            try{
                Long.parseLong(campoNumTelefono.getText());
                
            }catch(NumberFormatException e)
            {
                errorMessage += "CAP non valido , deve essere intero ! \n";
            }
        }
            
        if(campoIndirizzoResidenza.getText()==null || campoIndirizzoResidenza.getText().length()==0)
            errorMessage += "Indirzzo di residenza non inserito ! \n";
        
        if(campoCittaResidenza.getText()==null || campoCittaResidenza.getText().length()==0)
            errorMessage += "Città di residenza non inserita ! \n";
        
        if(campoCAP.getText()==null || campoCAP.getText().length()==0)
            errorMessage += "Codice di avviamento postale non inserito ! \n";
        else
        {
            try{
                Integer.parseInt(campoCAP.getText());
            }catch(NumberFormatException e)
            {
                errorMessage += "CAP non valido , deve essere intero ! \n";
            }
        }
        
        if(errorMessage.length()==0)
            return true;
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Dati non inseriti");
            alert.setHeaderText("Modificare dati non inseriti");
            alert.setContentText(errorMessage);
            
            //mostra dialogo errore e chiudi su richiesta utente
            alert.showAndWait();
            return false;
        }
            
    }

}
