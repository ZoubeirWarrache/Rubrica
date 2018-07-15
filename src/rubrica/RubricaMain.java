
package rubrica;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import rubrica.controller.DialogoEditaContattiController;
import rubrica.controller.LoginController;
import rubrica.controller.RegistrarsiController;
import rubrica.controller.RicercaCAPController;
import rubrica.controller.RicercaFemineController;
import rubrica.controller.RicercaMaschiController;
import rubrica.controller.RootLayoutController;
import rubrica.model.Contatto;
import rubrica.model.util.ContactListWrapper;
import rubrica.model.util.UtentiListWrapper;
import rubrica.model.Utente;

/**
 * Classe gestisce l'applicazione Rubrica
 */
public class RubricaMain extends Application {
    
    // stage primario per login e sign up
    private Stage primaryStage;
    
    // stage dialogo per editare i dati di un contatto
    private Stage dialogStage;
    
    // stage per la finestra della rubrica che mostra i dati di un contatto
    private final Stage rubricaStage = new Stage();
    
    private BorderPane rootLayout;
    private AnchorPane loginLayout;
    private AnchorPane signUpLayout;
    
    // stringa contiene nome del'utente , se l'utente è nuovo la usiamo per creare 
    // una cartella con il suo nome utente , e anche nel path per arrivare 
    // alla propria cartella sia per un utente nuovo o gia registrato 
    public String folderUtente;
    
    // path per trovare cartella Documents in ogni sistema operativo 
    String path = System.getProperty("user.home")+"/Documents";
    
    // file in formato xml contiene dati degli Utenti
    File dataUtenti = new File(path+"/my rubrica/Utenti.xml");
   
    // lista degli utenti
    private final ObservableList<Utente> listaUtente = FXCollections.observableArrayList();
    // lista di contatti
    private final ObservableList<Contatto> listaContatti = FXCollections.observableArrayList();
    
    /**
     * Estrattore lista utenti
     * @return listaUtente di tipo {@code ObservableList}
     * @see javafx.collections.ObservableList
     * @see javafx.collections.FXCollections
     */
    public final ObservableList<Utente> getDatiUtente()
    {
        return listaUtente;
    }
     
    /**
     * Estrattore lista contatti
     * @return lista contatti
     */
    public final ObservableList<Contatto> getListaContatti()
    {
        return listaContatti;
    }
     
    public RubricaMain()
    {
    }
    
    /**
     * Permette di ottenere lo stage della rubrica
     * @return rubrica stage
     */
    public Stage getRubricaStage(){
        return rubricaStage;
    }
   
    /**
     * creare una cartella dove mettere i dati dell'applicazione rubrica
     * 
     * carica la lista degli utenti registrati
     * 
     * Avvia il login layout 
     * 
     * @param primaryStage stage primario
     */
    @Override
    public void start(Stage primaryStage)  {
        
        this.primaryStage= primaryStage;
        
        createRubricaFolder();
        caricaUtenti();
        
        initLoginLayout();
       
    }
    
     /**
     * Inizializza il nodo root , crea la scena che contiene root e collegarla 
     * allo stage primario
     */
    public void initLoginLayout() 
    {
        try{
        // si carica il file Login.fxml    
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RubricaMain.class.getResource("/rubrica.view/Login.fxml"));
        loginLayout = loader.load();
        
        //titolo per lo stage primario
        primaryStage.setTitle("Login");
        // iconna per lo stage primario
        primaryStage.getIcons().add(new Image("/images/rubrica.png"));
        primaryStage.setResizable(false);
        
        // si  inizializza scena che contiene nodo root 
        Scene scene = new Scene(loginLayout);
        // collega scena allo stage principale
        primaryStage.setScene(scene);
        
        // si permette al controllo accesso all'applicazione
        LoginController controller =loader.getController();
        controller.setAppMain(this);
        
        
        primaryStage.show();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    
    /**
     * Inizializza il nodo root , crea la scena che contiene root e collegarla 
     * allo stage primario
     */
    public void registrarsi() 
    {
        try{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RubricaMain.class.getResource("/rubrica.view/Registrarsi.fxml"));
        signUpLayout = (AnchorPane)loader.load();
        
        primaryStage.setTitle("Sign up");
        
        // si  inizializza scena che contiene nodo root 
        Scene scene = new Scene(signUpLayout);
        // collega scena allo stage principale
        primaryStage.setScene(scene);
        
        // si permette al controllo accesso all'applicazione
        RegistrarsiController controller =loader.getController();
        controller.setAppMain(this);
        
        }catch(IOException e)
        {
            e.getMessage();
        }
    }
    
    
    /**
     * Initializza il nodo root , crea la scena che contiene root e 
     * collegarla allo stage 
     */
    public void initRootLayout() 
    {
        try{
           
        // si carica il file RootLayout.fxml    
        FXMLLoader loader  = new FXMLLoader();
        loader.setLocation(RubricaMain.class.getResource("/rubrica.view/RootLayout.fxml"));
        rootLayout = (BorderPane)loader.load();
        
        // si inizializza scena che contiene nodo root
        Scene scene = new Scene(rootLayout);
        // collega scena allo stage della rubrica
        rubricaStage.setScene(scene);
        
        rubricaStage.setResizable(false);
        rubricaStage.setTitle("Rubrica");
        rubricaStage.getIcons().add(new Image("/images/rubrica.png"));
        
        // si permette al controllo acesso all'applicazione
        RootLayoutController rootController = loader.getController();
        rootController.setAppMain(this);
        
        rubricaStage.show();
        primaryStage.close();
        
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
      
    }
   
    /**
     * Apre finestra di dialogo per editare i dati di un contatto , selezionando
     * SALVA i dati del contatto vengono salvati nell'istanza del contatto
     * e si ritorna true .
     * 
     * @param contact contatto
     * @return true se SALVA selezionato altrimenti ritorna false 
     */
    public boolean showDialogoContatti(Contatto contact) 
    {
        try {
        // carica file DialogoEditaContatti.fxml
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RubricaMain.class.getResource("/rubrica.view/DialogoEditaContatti.fxml"));
        AnchorPane anchorPane = (AnchorPane)loader.load();
        
        // crea nuovo stage per dialogo
        dialogStage = new Stage();
        // titolo dello stage
        dialogStage.setTitle("Inserisci Dati Contatto");
        // iconna dello stage
        dialogStage.getIcons().add(new Image("/images/rubrica.png"));
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(rubricaStage);
        dialogStage.setResizable(false);
        
        // crea scena 
        Scene scene = new Scene(anchorPane);
        // collega scena allo stage dialogo
        dialogStage.setScene(scene);
        
        // aggancia i dati contatto da mostrare nel controllo
        DialogoEditaContattiController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setContatto(contact);
        
        // mostra dialogo fino a quando l'utente lo chiude
        dialogStage.showAndWait();
        
        return controller.salvaClick();
        }catch(IOException e)
        {
            e.getMessage();
            return false;
            
        }
    }
    
    /**
     * Apre finestra dialogo per la ricerca di nominativi maschi .
     */
    public void showRicercaMaschi() 
    {
        try{
            
        // carica il file RicercaMaschi.fxml    
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RubricaMain.class.getResource("/rubrica.view/RicercaMaschi.fxml"));
        AnchorPane anchorPane =(AnchorPane)loader.load();
        
        // crea nuovo stage per dialogo
        Stage ricercaStage = new Stage();
        
        // titolo dello stage
        ricercaStage.setTitle("Rubrica - Nominativi di sesso maschile");
        // iconna dello stage
        ricercaStage.getIcons().add(new Image("/images/rubrica.png"));
        ricercaStage.initOwner(rubricaStage);
        ricercaStage.setResizable(false);
        // crea scena
        Scene scene = new Scene(anchorPane);
        // collega scena allo stage dialogo
        ricercaStage.setScene(scene);
        
        // aggancia i dati contatti per ricerca nel controllo
        RicercaMaschiController controller = loader.getController();
        controller.setContattiMaschi(listaContatti);
        
        // mostra finestra fino quando l'utente lo chiude 
        ricercaStage.showAndWait();
        }
        catch(IOException e)
        {
            e.getMessage();
        }
    }
    
    /**
     * Apre finestra dialogo per la ricerca di nominativi femmine 
     */
    public void showRicercaFemine() 
    {
        try{
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RubricaMain.class.getResource("/rubrica.view/RicercaFemine.fxml"));
        AnchorPane anchorPane =(AnchorPane)loader.load();
        
        
        Stage ricercaStage = new Stage();
        ricercaStage.setTitle("Rubrica - Nominativi di sesso femminile");
        ricercaStage.getIcons().add(new Image("/images/rubrica.png"));
        ricercaStage.initOwner(rubricaStage);
        ricercaStage.setResizable(false);
        
        Scene scene = new Scene(anchorPane);
        ricercaStage.setScene(scene);
        
        RicercaFemineController controller = loader.getController();
        controller.setContattiFemine(listaContatti);
        
        ricercaStage.showAndWait();
        }catch(IOException e)
        {
            e.getMessage();
        }
    }
    
    /**
     * Apre finestra dialogo per ricerca di nominativi secondo il 
     * codice di avviamenti postale CAP .
     */
    public void showRicercaCAP()
    {
        try{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RubricaMain.class.getResource("/rubrica.view/RicercaCAP.fxml"));
        AnchorPane anchorPane =(AnchorPane)loader.load();
        
        
        Stage ricercaStage = new Stage();
        ricercaStage.setTitle("Rubrica - Nominativi residenti in specifiche aree postali");
        ricercaStage.getIcons().add(new Image("/images/rubrica.png"));
        ricercaStage.initOwner(rubricaStage);
        ricercaStage.setResizable(false);
        
        Scene scene = new Scene(anchorPane);
        ricercaStage.setScene(scene);
        
        RicercaCAPController controller = loader.getController();
        controller.setContatti(listaContatti);
        
        ricercaStage.showAndWait();
        }catch(IOException e)
        {
            e.getMessage();
        }
    }
    
    /**
     * crea una cartella dove mettere tutti i dati dell'applicazione
     * si crea solo una volta nel sistema 
     * se la cartella gia esiste non crearla .
     */
    public void createRubricaFolder()
    {
        
        File file = new File(path+"/my rubrica");
        if(!file.exists())
        {
            if(file.mkdir())
                System.out.println("rubrica folder is created");
        }
    }
    
    /**
     * crea una cartella per ogni utente si registra dove salva i dati contatti
     * @param folderName nome dell'utente per metterlo come nome della cartella
     */ 
    public void createUserFolder(String folderName)
    {
        folderUtente = folderName;
        
        File file = new File(path+"/my rubrica/"+folderUtente);
        
        if(!file.exists())
        {
            if(file.mkdir())
                System.out.println(folderUtente+" folder is created");
        }
    }
    
    /**
     * verifica nome utente e password inseriti dall'utente se esistono nella 
     * lista degli utenti registrati o no , se esistono ritorna true altrimenti 
     * ritorna false .
     * 
     * @param nomeUtente nome utente da verificare
     * @param password password da verificare
     * @return booleano
     */
    public boolean verificaUtente(String nomeUtente , String password )
    {
        boolean value = false ;
        
        // se la lista vuota ritorna false
        if(listaUtente==null) value = false;
        else
        {
            for(Utente var : listaUtente)
         {
             value = var.getNomeUtente().equals(nomeUtente) && var.getPassword().equals(password);
             if(value){
                 folderUtente = nomeUtente;
                 break;
             }
         }
        }
        return value;
        
    }
    
    /**
     * carica lista di dati utenti da file .
     */
    public void caricaUtenti()
    {
        try{
            JAXBContext context = JAXBContext.newInstance(UtentiListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            
            // smista i dati letti dal file in formato xml
            UtentiListWrapper wrapper2 = (UtentiListWrapper)um.unmarshal(dataUtenti);
            
            listaUtente.clear();
            listaUtente.addAll(wrapper2.getUtenti());
            
        }catch(JAXBException e){System.out.println(e.getMessage());}
    }
    
    /**
     * salva dati utenti su file .
     */
    public void saveUtente() 
    {
        try{
            JAXBContext context = JAXBContext.newInstance(UtentiListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            // utilizza wrapper per i dati persone
            UtentiListWrapper wrapper = new UtentiListWrapper();
            wrapper.setUtenti(listaUtente);
            
            // smista e salva dati nel formato xml
            m.marshal(wrapper, dataUtenti);
        }
        catch(JAXBException e )
        {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Sovrascrive preferenze file caricato, utilizzando {@code Preferences} .
     * @param file il file oppure null per rimuovere path
     * @see java.util.prefs.Preferences
     */
    public final void setFilePathContatti(File file) {
        
        Preferences prefs = Preferences.userNodeForPackage(RubricaMain.class);
        if(file != null)
        {
            prefs.put("filePath", file.getPath());
            
            // aggiorna titolo stage
            rubricaStage.setTitle("Rubrica - "+file.getName());
        }
        else
        {
            prefs.remove("filePath");
            
            // aggiorna titolo stage
            rubricaStage.setTitle("Rubrica");
        }
    }
    /**
     * Estrae preferenze file aperto per ultimo da sistema operativo . se non 
     * ci sono ritorna null
     * @return 
     */
    public final File getFilePathContatti() {
         
        Preferences prefs = Preferences.userNodeForPackage(RubricaMain.class);
        String filePath = prefs.get("filePath", null);
        if(filePath != null)
        {
            return new File(filePath);
        }
        else
        {
            return null;
        }
    }
    
    /**
     * carica dati contatti da file sostituendo i dati attuali .
     * 
     * @param file dati
     * @see javafx.scene.control.Alert
     * @see javafx.scene.control.Alert.AlertType
     */
    public void caricaDaFileDatiContatti(File file) {
        try
        {
            JAXBContext context = JAXBContext.newInstance(ContactListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            
            // smista i dati letti dal file in formato xml
            ContactListWrapper wrapper = (ContactListWrapper)um.unmarshal(file);
            
            listaContatti.clear();
            listaContatti.addAll(wrapper.getContatti());
            
            // salva path file nei registri 
            setFilePathContatti(file);
            
        }
        catch(JAXBException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("impossibile caricare dati");
            alert.setContentText("impossibile caricare dati da file "+file.getPath());
            
            // mostra finestra dialogo fino quando l'utente lo chiude
            alert.showAndWait();
        }
    }

    /**
     * salva dati contatti su file specificato 
     * @param file dati
     * @see javafx.scene.control.Alert
     * @see javafx.scene.control.Alert.AlertType
     */
    public void salvaSuFileDatiContatti(File file) {
        try
        {
            JAXBContext context = JAXBContext.newInstance(ContactListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            ContactListWrapper wrapper = new ContactListWrapper();
            wrapper.setContatti(listaContatti);
            m.marshal(wrapper, file);
            setFilePathContatti(file);
        }
        catch(JAXBException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("impossibile salvare dati");
            alert.setContentText("impossibile salvare dati nel file "+file.getPath());
            
            // mostra finestra dialogo fino quando l'utente lo chiude
            alert.showAndWait();
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

   
}
