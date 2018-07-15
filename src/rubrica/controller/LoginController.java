
package rubrica.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import rubrica.RubricaMain;

/**
 * Controllo per il login .
 */
public class LoginController  {
    
    @FXML
    private TextField campoNome;
    
    @FXML
    private PasswordField campoPassword;
    
    @FXML
    private Label errore;
    
    // per collegamento main
    private RubricaMain appMain;
    
    // numero dei tentativi del utente per il login
    private int tentativiLogin = 0; 
    
    /**
     * Permette collegamento main con controllo 
     * 
     * @param pAppmain istanza di RubricaMain 
     */
    public final void setAppMain(RubricaMain pAppmain)
    {
        this.appMain = pAppmain;
    }
    
    /**
     * Invocato quando utente preme tasto login .
     */
    @FXML
    public void handleLogin() 
    {
        // value==true se i dati inseriti dall'utente esistono nella lista degli utenti registrati 
        // value==false nel caso contrario
        boolean value = appMain.verificaUtente(campoNome.getText(), campoPassword.getText());
        
        // se value==true apre l'applicazione
        if(value)
        {
            appMain.initRootLayout();
            
        }
        // se value==false rendere visibile un messaggio d'errore
        else 
        {
            errore.setVisible(true);
            tentativiLogin++;
        }
        // se l'utente sbaglia tre volte chiude l'applicazione
        if(tentativiLogin==3) 
            Platform.exit();
        
    }
    
    /**
     * Azione quando utente pigia enter .
     * @param event
     */
    @FXML
    public void handleEnterPressed(KeyEvent event){
    if (event.getCode() == KeyCode.ENTER) {
        
        handleLogin();
    }
    }
    
    
    /**
     * Invocato quando utente preme tasto Sign up .
     */
    @FXML
    public void handleSignIn()
    {
         appMain.registrarsi();     
    }
    
    
}


