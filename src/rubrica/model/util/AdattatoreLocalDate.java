
package rubrica.model.util;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Classe che permette di gestire conversione {@code String} e {@code LocalDate}
 * per formato xml . Eredita da {@code XmlAdapter} .
 * 
 * @see javax.xml.bind.annotation.adapters.XmlAdapter .
 */
public class AdattatoreLocalDate extends XmlAdapter<String , LocalDate>{

    /**
     * Smista da tipo String a tipo LocalDate .
     * 
     * @param d data di tipo String
     * @return data di tipo LocalDate
     * @throws Exception 
     */
    @Override
    public LocalDate unmarshal(String d) throws Exception {
        return LocalDate.parse(d);
    }

    /**
     * Smista da tipo LocalDate a tipo String .
     * 
     * @param d data di tipo LocalDate
     * @return data di tipo String
     * @throws Exception 
     */
    @Override
    public String  marshal(LocalDate d) throws Exception {
        
        return d.toString();
    }

}
