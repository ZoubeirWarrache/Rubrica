
package rubrica.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import rubrica.RubricaMain;
import rubrica.model.Utente;

/**
 * Controllo per registrarsi(Sign up) .
 */
public class RegistrarsiController  {
    
    
    @FXML 
    private TextField campoNomeUtente;
    
    @FXML 
    private PasswordField campoPassword;
    
    @FXML 
    private PasswordField campoConfirmaPassword;
    
    @FXML 
    private Label NomeUtenteNonValido;
    
    @FXML 
    private Label PasswordNonInserito;
    
    @FXML 
    private Label ConfirmaPassword;
    
    // per collegamento main
    private RubricaMain appMain;
    
    
    /**
     * Permette collegamento main con controllo
     * @param pAppmain istanza di RubricaMain
     */
    public final void setAppMain(RubricaMain pAppmain)
    {
        this.appMain = pAppmain;
    }
    
    
    /**
     * Invocato quando utente preme tasto OK .
     */
    @FXML 
    private void handleOK() 
    {
        if(verificaDati())
        {
            // aggiunge nuovo utente alla lista degli utenti
            appMain.getDatiUtente().add(new Utente(campoNomeUtente.getText(), campoPassword.getText()));
            
            // salva su disco lista utenti
            appMain.saveUtente();
           
            // crea un folder per ogni nuovo utente dove salva contatti
            appMain.createUserFolder(campoNomeUtente.getText());
            
            //apre l'applicazione
            appMain.initRootLayout();
            
        }
    }
    
    /**
     * Azione quando utente pigia enter .
     * @param event
     */
    @FXML
    public void handleEnterPressed(KeyEvent event){
    if (event.getCode() == KeyCode.ENTER) {
        
        handleOK();
    }
    }
    
    /**
     * Invocato quando utente preme tasto Back per tornare al login . 
     */
    @FXML 
    private void handleBack() 
    {
        appMain.initLoginLayout();
    }
    
    /**
     * Verifica dati inseriti dal nuovo utente , ritorna true in caso 
     * validita di dati , false in caso contrario 
     * 
     * @return booleano
     */
    private boolean verificaDati()
    {
        boolean value = true;
        
        if(campoNomeUtente.getText()==null || campoNomeUtente.getText().length()==0)
        {
            value = false;
            NomeUtenteNonValido.setText("Nome utente non valido !");
            NomeUtenteNonValido.setVisible(true);
        }
        if(campoPassword.getText()==null || campoPassword.getText().length()==0)
        {
            value = false;
            PasswordNonInserito.setVisible(true);
        }
        if(!campoConfirmaPassword.getText().equals(campoPassword.getText()))
        {
            value = false;
            ConfirmaPassword.setVisible(true);
        }
        
        // verifica se i dati utente gia usati 
        boolean valore = appMain.verificaUtente(campoNomeUtente.getText(), campoPassword.getText());
        if(valore)
        {
            value = false;
            NomeUtenteNonValido.setText("Nome utente gia usato !");
            NomeUtenteNonValido.setVisible(true);
        }
        
        return value;
            
    }
    
    
    
}
