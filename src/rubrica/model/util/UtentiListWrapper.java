
package rubrica.model.util;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import rubrica.model.Utente;


/**
 * Classe che permette di salvare la lista degli utenti in xml specificando i marcatori .
 */
@XmlRootElement(name="Utenti")
public class UtentiListWrapper {
    
    private List<Utente> Utenti = new ArrayList<>();
    
    
    /**
     * Estrae utenti .
     * 
     * @return {@code List<Utente>} la lista Utenti
     */
    @XmlElement(name = "Utente")
    public final List<Utente> getUtenti()
    {
        return Utenti;
    }
    
    /**
     * Definisce lista utenti
     * 
     * @param listaUtenti  la lista utenti
     */
    public final void setUtenti(List<Utente> listaUtenti)
    {
        this.Utenti = listaUtenti;
    }
    
}
