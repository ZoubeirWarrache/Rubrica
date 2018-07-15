
package rubrica.model.util;

import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import rubrica.model.Contatto;

/**
 * Classe che permette di selezionare dati da registro con predicato ,
 * si realizza con {@code Predicate} e {@code Filtro} la parametrizzazione del  
 * comportamento di {@code filtraContatti} .
 */
public class Filtro {
    
   public static ObservableList<Contatto> filtraContatti(ObservableList<Contatto> registro , Predicate<Contatto> ps)
   {
       ObservableList<Contatto> filtrati = FXCollections.observableArrayList();
       registro.stream().filter((s) -> (ps.test(s))).forEachOrdered((s) -> {
           filtrati.add(s);
       });
       return filtrati;
   }
    
}
