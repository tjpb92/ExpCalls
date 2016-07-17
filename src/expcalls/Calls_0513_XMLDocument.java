package expcalls;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.w3c.dom.Comment;
import org.w3c.dom.Element;
import utils.XMLDocument;

/**
 * Classe pour générer un fichier au format XML décrivant des tickets des
 * clients de la famille 513.
 *
 * @version Juillet 2016
 * @author Thierry Baribaud
 */
public class Calls_0513_XMLDocument extends XMLDocument {

    /**
     * Initialise le document XML - constructeur principal.
     *
     * @param RootName nom de la racine du document XML.
     * @param XsdFile nom du fichier contenant le schéma XML.
     * @param MyComment commentaire sur le contenu du fichier.
     */
    public Calls_0513_XMLDocument(String RootName, String XsdFile, String MyComment) {
        super(RootName, XsdFile, MyComment);
    }

    /**
     * Initialise le document XML - constructeur secondaire.
     *
     * @param RootName nom de la racine du document XML.
     * @param XsdFile nom du fichier contenant le schéma XML.
     */
    public Calls_0513_XMLDocument(String RootName, String XsdFile) {
        this(RootName, XsdFile, null);
    }

    /**
     * Ajoute un ticket au document XML. Le ticket est décrit de manière non
     * structurée - flat schema -
     *
     * @param MyTicket ticket à transcrire en XML.
     */
    public void AddToXMLDocument(Ticket_0513 MyTicket) {

        Comment MyComment;
        Element MyElement;
        String MyString;
        int myInt;
        Timestamp MyTimestamp;

        Element Ticket;

        DateFormat MyDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        MyString = "Ticket ref=" + MyTicket.Fcalls_0000.getCnum();
        if (MyString != null) {
            MyComment = MyDocument.createComment(MyString);
            MyElements.appendChild(MyComment);
        }

        Ticket = MyDocument.createElement("ticket");
        MyElements.appendChild(Ticket);

        // Numéro de DI
        MyElement = MyDocument.createElement("NoOT");
        Ticket.appendChild(MyElement);
        myInt = MyTicket.Fcalls_0000.getCseqno();
        if (myInt > 0) {
            MyElement.appendChild(MyDocument.createTextNode(String.valueOf(myInt)
                    + "/" + String.valueOf(MyTicket.Fcalls_0000.getCnum())));
        }

        // Origine de la demande
        MyElement = MyDocument.createElement("OrigineDemande");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getOrigineDemande();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Urgence de la demande
        MyElement = MyDocument.createElement("OTUrgente");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getOTUrgente();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Date de saisie
        MyElement = MyDocument.createElement("DateAppel");
        Ticket.appendChild(MyElement);
        MyTimestamp = MyTicket.Fcalls_0000.getCdate();
        if (MyTimestamp != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyDateFormat.format(MyTimestamp)));
        }

        // Heure de saisie
        MyElement = MyDocument.createElement("HeureOuvertureDossier");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcalls_0000.getCtime().substring(0, 5);
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Heure de réception du mail
        MyElement = MyDocument.createElement("HeureMail");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getHeureMail();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString.substring(0, 5)));
        }

        // Temps de traitement du mail exprimé en minutes.
        MyElement = MyDocument.createElement("TempsTraitement");
        Ticket.appendChild(MyElement);
        myInt = MyTicket.getTempsTraitement();
        if (myInt > 0) {
            MyElement.appendChild(MyDocument.createTextNode(String.valueOf(myInt)));
        }

        // Agence
        MyElement = MyDocument.createElement("DO");
        Ticket.appendChild(MyElement);
        if ((MyString = MyTicket.getA6extname()) == null) {
            MyString = MyTicket.getA6name();
        }
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Code du magasin
        MyElement = MyDocument.createElement("CodeMagasin");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcomplmt_0000.getC6alpha2();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Nom du magasin
        MyElement = MyDocument.createElement("NomMagasin");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcalls_0000.getCorp();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Téléphone de l'appelant
        MyElement = MyDocument.createElement("TelAppelant");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcalls_0000.getCtel();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Adresse complète
        MyElement = MyDocument.createElement("Adresse");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcalls_0000.getCaddress();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }
        MyElement = MyDocument.createElement("CP");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcalls_0000.getCposcode();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }
        MyElement = MyDocument.createElement("Ville");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcalls_0000.getCity();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Qualification de la demande
        MyElement = MyDocument.createElement("Qualification");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getQualification();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Code panne
        MyElement = MyDocument.createElement("CodePanne");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcalls_0000.getCnumber9();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Libelle associé au code panne
        MyElement = MyDocument.createElement("LibelleCodePanne");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcomplmt_0000.getC6alpha7();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Demande d'intervention
        MyElement = MyDocument.createElement("DemandeIntervention");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcalls_0000.getCsympt();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Le nom du premier prestataire contacté.
        MyElement = MyDocument.createElement("Prestataire1");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getNomPrestataire1();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Date de missionnement
        MyElement = MyDocument.createElement("DateMissionnement1");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getDateMissionnement1();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Heure de missionnement
        MyElement = MyDocument.createElement("HeureMissionnement1");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getHeureMissionnement1();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString.substring(0, 5)));
        }

        // Le nom du second/dernier prestataire contacté.
        MyElement = MyDocument.createElement("Prestataire2");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getNomPrestataire2();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Date de missionnement
        MyElement = MyDocument.createElement("DateMissionnement2");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getDateMissionnement2();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Heure de missionnement
        MyElement = MyDocument.createElement("HeureMissionnement2");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getHeureMissionnement2();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString.substring(0, 5)));
        }

        // Le numéro de log de l'opérateur
        MyElement = MyDocument.createElement("LogOperateur");
        Ticket.appendChild(MyElement);
        myInt = MyTicket.getLogOperateur();
        if (myInt > 2000) {
            MyElement.appendChild(MyDocument.createTextNode(String.valueOf(myInt)));
        }
    }
}
