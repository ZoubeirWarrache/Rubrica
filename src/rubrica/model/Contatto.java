
package rubrica.model;

import rubrica.model.util.AdattatoreLocalDate;
import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Classe che definisce nome , cognome , sesso , data di nascita, 
 * numero di telefono , indirizzo di residenza, citta di residenza,
 * CAP(codice di avviamento postale) di un contatto .
 * 
 */
public class Contatto {
    
    private final StringProperty nome;
    private final StringProperty cognome;
    private final StringProperty sesso;
    private final ObjectProperty <LocalDate>dataDiNascita;
    private final LongProperty numTelefono;
    private final StringProperty indirizzoResidenza;
    private final StringProperty cittaResidenza;
    private final IntegerProperty CAP;
    
    public Contatto()
    {
        this(null,null);
    }
    
    /**
     * Costruttore .
     * @param pNome nome di un contatto .
     * @param pCognome  cognome di un contatto .
     */
    public Contatto(String pNome , String pCognome)
    {
       this.nome = new SimpleStringProperty(pNome);
       this.cognome = new SimpleStringProperty(pCognome);
       this.sesso = new SimpleStringProperty();
       this.dataDiNascita = new SimpleObjectProperty<>(LocalDate.of(1996, 2, 28));
       this.numTelefono = new SimpleLongProperty();
       this.indirizzoResidenza = new SimpleStringProperty();
       this.cittaResidenza = new SimpleStringProperty();
       this.CAP = new SimpleIntegerProperty();

    }
    
    /**
     * Modificatore del nome 
     * 
     * @param pNome nome tipo String .
     */
    public final void setNome(String pNome)
    {
        this.nome.set(pNome);
    }
    
    /**
     * Estrattore del nome .
     * 
     * @return nome di tipo String .
     */
    public final String getNome()
    {
        return nome.get();
    }
    
    /**
     * Ritorna propriet del Nome .
     * 
     * @return nome di tipo StringProperty .
     */
    public final StringProperty nomeProperty()
    {
        return nome;
    }
    
    
    /**
     * Modificatore del cognome 
     * 
     * @param pCognome cognome tipo String 
     */
    public final void setCognome(String pCognome)
    {
        this.cognome.set(pCognome);
    }
    
    /**
     * Estrattore del cognome
     * 
     * @return cognome di tipo String
     */
    public final String getCognome()
    {
        return cognome.get();
    }
    
    /**
     * Ritorna propriet del cognome
     * 
     * @return cognome di tipo StringProperty
     */
    public final StringProperty cognomeProperty()
    {
        return cognome;
    }
    
    
    /**
     * Modificatore del sesso
     * 
     * @param pSesso sesso tipo String 
     */
    public final void setSesso(String pSesso)
    {
        this.sesso.set(pSesso);
    }
    
    /**
     * Estrattore del sesso
     * 
     * @return sesso di tipo String
     */
    public final String getSesso()
    {
        return sesso.get();
    }
    
    /**
     * Ritorna propriet del Sesso
     * 
     * @return sesso di tipo StringProperty
     */
    public final StringProperty sessoProperty()
    {
        return sesso;
    }
    
    
    /**
     * Modificatore del indirizzo di residenza
     * 
     * @param pIndirizzo indirizzo di residenza tipo String 
     */
    public final void setIndirizzoResidenza(String pIndirizzo)
    {
        this.indirizzoResidenza.set(pIndirizzo);
    }
    
    /**
     * Estrattore dell' indirizzo di residenza
     * 
     * @return indirizzo di residenza di tipo String
     */
    public final String getIndirizzoResidenza()
    {
        return indirizzoResidenza.get();
    }
    
    /**
     * Ritorna propriet dell' indirizzo di residenza
     * 
     * @return indirizzo di residenza di tipo StringProperty
     */
    public final StringProperty IndirizzoResidenzaProperty()
    {
        return indirizzoResidenza;
    }
    
    
    
    /**
     * Modificatore del città di residenza
     * 
     * @param pCitta citta di residenza tipo String 
     */
    public final void setCittaResidenza(String pCitta)
    {
        this.cittaResidenza.set(pCitta);
    }
    
    /**
     * Estrattore della città di residenza
     * 
     * @return città di residenza di tipo String
     */
    public final String getCittaResidenza()
    {
        return cittaResidenza.get();
    }
    
    /**
     * Ritorna propriet della città di residenza
     * 
     * @return città di residenza di tipo StringProperty
     */
    public final StringProperty CittaResProperty()
    {
        return cittaResidenza;
    }
    
    
    /**
     * Modificatore del CAP (codice di avviamento postale)  
     * 
     * @param pCAP  CAP tipo int
     */
    public final void setCAP(int  pCAP)
    {
        this.CAP.set(pCAP);
    }
    
    /**
     * Estrattore del CAP(codice di avviamento postale)
     * 
     * @return CAP di tipo int
     */
    public final int getCAP()
    {
        return CAP.get();
    }
    
    /**
     * Ritorna propriet del CAP
     * 
     * @return CAP di tipo IntegerProperty
     */
    public final IntegerProperty CAPProperty()
    {
        return CAP;
    }
    
    
    /**
     * Modificatore del numero di telefono
     * 
     * @param pNum numero di telefono tipo long
     */
    public final void setNumTelefono(long pNum)
    {
        this.numTelefono.set(pNum);
    }
    
    /**
     * Estrattore del numero di telefono
     * 
     * @return numero di telefono di tipo long
     */
    public final long getNumTelefono()
    {
        return numTelefono.get();
    }
    
    /**
     * Ritorna propriet del numero di telefono
     * 
     * @return numero telefono di tipo LongProperty
     */
    public final LongProperty numTelProperty()
    {
        return numTelefono;
    }
    
    
    /**
     * Modificatore della data di nascita
     * 
     * @param pData data di nascita tipo LocaleDate 
     */
    public final void setDataNascita(LocalDate pData)
    {
        this.dataDiNascita.set(pData);
    }
    
    /**
     * Estrattore data di nascita . data viene adattata per formato xml
     * 
     * @return data d nascita di tipo localDate
     */
    @XmlJavaTypeAdapter(AdattatoreLocalDate.class)
    public final LocalDate getDataNascita()
    {
        return dataDiNascita.get();
    }
    
    /**
     * Ritorna propriet della data di nascita
     * 
     * @return data di nascita 
     */
    public ObjectProperty<LocalDate> dataNascitaProperty()
    {
        return dataDiNascita;
    }
    
    /**
     * Sovrascrive toString della classe Object 
     * 
     * @return stringa per riportare stato oggetto istanza di Contatto
     */
    @Override
    public String toString()
    {
        return  "Nome                   : "+nome+"\n"+
                "Cognome                : "+cognome+"\n"+
                "Sesso                  : "+sesso+"\n"+
                "data di nascita        : "+dataDiNascita+"\n"+
                "Numero di telefono     : "+numTelefono+"\n"+
                "Indirizzo di residenza : "+indirizzoResidenza+"\n"+
                "Città di residenza     : "+cittaResidenza+"\n"+
                "Codice postale         : "+CAP+"\n";
    }
    
    /**
     * Stampa stato di un oggetto di tipo Contatto
     */
    public void printData()
    {
        System.out.println(toString());
    }
}
