
package rubrica.model.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility per gestire data di nascita .
 * 
 */
public class DateUtil {
    
    // formato della data di nascita che si deve utilizzare per conversione
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    
    // formattatore data
    private static final DateTimeFormatter DATE_FORMATTER =DateTimeFormatter.ofPattern(DATE_PATTERN);
    
    
    /**
     * Ritorna una stringa da tipo {@code LocalDate} .
     * 
     * @param data data di nascita in formato LocalDate
     * @return una stringa con la data di nascita 
     */
    public static String format(LocalDate data)
    {
        if(data==null)
            return null;
        return DATE_FORMATTER.format(data);
    }
    
    /**
     * Converte una data in formato {@code String} in un'altra di tipo {@code localDate}
     * 
     * @param data data di nascita
     * @return LocalDate oggetto
     */
    public static LocalDate parse(String data)
    {
        try {
            return DATE_FORMATTER.parse(data, LocalDate::from);
        } 
        catch (DateTimeParseException e) {
            e.getMessage();
            return null;
                    }
    }
    
    /**
     * Verifica se la stringa contiene una data di nascita
     * @param stringaData data da analizzare
     * @return boolean value
     */
    public static boolean verificaData(String stringaData)
    {
        return DateUtil.parse(stringaData) != null;
    }
    
}
