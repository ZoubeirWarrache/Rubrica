
package rubrica.model.util;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import rubrica.model.Contatto;

/**
 * Classe che permette di salvare la lista dei contatti in xml specificando i marcatori .
 */
@XmlRootElement(name="Contatti")
public class ContactListWrapper {
    
    private List<Contatto> Contatti;
    
    /**
     * Estrae contatti .
     * 
     * @return {@code List<Conttato>} la lista contatti
     */
    @XmlElement(name = "Contatto")
    public final List<Contatto> getContatti()
    {
        return Contatti;
    }
    
    /**
     * Definisce lista contatti 
     * 
     * @param pContatti la lista contatti
     */
    public final void setContatti(List<Contatto> pContatti)
    {
        this.Contatti = pContatti;
    }
}
