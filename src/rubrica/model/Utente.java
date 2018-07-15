
package rubrica.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * Classe che definisce nome utente e password di un utente .
 * 
 */
public class Utente {
    
    private final StringProperty nomeUtente;
    private final StringProperty password;
    
    /**
     * Costruttore di default .
     */
    public Utente()
    {
        this.nomeUtente = new SimpleStringProperty("");
        this.password = new SimpleStringProperty("");
    }
    
    /**
     * Costruttore
     * @param nome di un utente
     * @param password di un utente
     */
    public Utente(String nome , String password )
    {
        this.nomeUtente = new SimpleStringProperty(nome);
        this.password = new SimpleStringProperty(password);
    }
    
    
    /**
     * Modificatore del nome utente
     * 
     * @param nome tipo String 
     */
    public void setNomeUtente(String nome)
    {
        this.nomeUtente.set(nome);
    }
    
    /**
     * Estrattore del nome utente
     * 
     * @return nome utente di tipo String
     */
    public String getNomeUtente()
    {
        return this.nomeUtente.get();
    }
    
    /**
     * Ritorna propriet del nome utente
     * 
     * @return nome utente di tipo StringProperty
     */
    public StringProperty getNomeUtenteProperty()
    {
        return this.nomeUtente;
    }
    
    
    /**
     * Modificatore del password
     * 
     * @param password tipo String 
     */
    public void setPassword( String password)
    {
        this.password.set(password);
    }
  
    /**
     * Estrattore del password
     * 
     * @return password di tipo String
     */
    public String getPassword()
    {
        return this.password.get();
    }
    
    /**
     * Ritorna propriet del password
     * 
     * @return password di tipo StringProperty
     */
    public StringProperty getPasswordProperty()
    {
        return this.password;
    }
}
