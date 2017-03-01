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
 * @author Thierry Baribaud
 * @version 0.29
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
     * @param ticket_0513 ticket à transcrire en XML.
     */
    public void AddToXMLDocument(Ticket_0513 ticket_0513) {

        Comment comment;
        Element element;
        String aString;
        int anInt;
        Timestamp aTimestamp;

        Element Ticket;

        DateFormat MyDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        aString = "Ticket ref=" + ticket_0513.Fcalls_0000.getCnum();
        if (aString != null) {
            comment = MyDocument.createComment(aString);
            MyElements.appendChild(comment);
        }

        Ticket = MyDocument.createElement("ticket");
        MyElements.appendChild(Ticket);

        // Numéro de DI
        element = MyDocument.createElement("NoOT");
        Ticket.appendChild(element);
        anInt = ticket_0513.Fcalls_0000.getCseqno();
        if (anInt > 0) {
            element.appendChild(MyDocument.createTextNode(String.valueOf(anInt)));
//                    + "/" + String.valueOf(ticket_0513.Fcalls_0000.getCnum())));
        }

        // Origine de la demande
        element = MyDocument.createElement("OrigineDemande");
        Ticket.appendChild(element);
        aString = ticket_0513.getOrigineDemande();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Urgence de la demande
        element = MyDocument.createElement("OTUrgente");
        Ticket.appendChild(element);
        aString = ticket_0513.getOTUrgente();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Date de saisie
        element = MyDocument.createElement("DateAppel");
        Ticket.appendChild(element);
        aTimestamp = ticket_0513.Fcalls_0000.getCdate();
        if (aTimestamp != null) {
            element.appendChild(MyDocument.createTextNode(MyDateFormat.format(aTimestamp)));
        }

        // Heure de saisie
        element = MyDocument.createElement("HeureOuvertureDossier");
        Ticket.appendChild(element);
        aString = ticket_0513.Fcalls_0000.getCtime().substring(0, 5);
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Heure de réception du mail
        element = MyDocument.createElement("HeureMail");
        Ticket.appendChild(element);
        aString = ticket_0513.getHeureMail();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString.substring(0, 5)));
        }

        // Temps de traitement du mail exprimé en minutes.
        element = MyDocument.createElement("TempsTraitement");
        Ticket.appendChild(element);
        anInt = ticket_0513.getTempsTraitement();
        if (anInt > 0) {
            element.appendChild(MyDocument.createTextNode(String.valueOf(anInt)));
        }

        // Agence
        element = MyDocument.createElement("DO");
        Ticket.appendChild(element);
        if ((aString = ticket_0513.getA6extname()) == null) {
            aString = ticket_0513.getA6name();
        }
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Code du magasin
        element = MyDocument.createElement("CodeMagasin");
        Ticket.appendChild(element);
        aString = ticket_0513.Fcomplmt_0000.getC6alpha2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Nom du magasin
        element = MyDocument.createElement("NomMagasin");
        Ticket.appendChild(element);
        aString = ticket_0513.Fcalls_0000.getCorp();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Téléphone de l'appelant
        element = MyDocument.createElement("TelAppelant");
        Ticket.appendChild(element);
        aString = ticket_0513.Fcalls_0000.getCtel();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Adresse complète
        element = MyDocument.createElement("Adresse");
        Ticket.appendChild(element);
        aString = ticket_0513.Fcalls_0000.getCaddress();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        element = MyDocument.createElement("CP");
        Ticket.appendChild(element);
        aString = ticket_0513.Fcalls_0000.getCposcode();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        element = MyDocument.createElement("Ville");
        Ticket.appendChild(element);
        aString = ticket_0513.Fcalls_0000.getCity();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Qualification de la demande
        element = MyDocument.createElement("Qualification");
        Ticket.appendChild(element);
        aString = ticket_0513.getQualification();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Code panne
        element = MyDocument.createElement("CodePanne");
        Ticket.appendChild(element);
        aString = ticket_0513.Fcalls_0000.getCnumber9();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Libelle associé au code panne
        element = MyDocument.createElement("LibelleCodePanne");
        Ticket.appendChild(element);
        aString = ticket_0513.Fcomplmt_0000.getC6alpha7();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Demande d'intervention
        element = MyDocument.createElement("DemandeIntervention");
        Ticket.appendChild(element);
        aString = ticket_0513.Fcalls_0000.getCsympt();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Le nom du premier prestataire contacté.
        element = MyDocument.createElement("Prestataire1");
        Ticket.appendChild(element);
        aString = ticket_0513.getNomPrestataire1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Date de missionnement
        element = MyDocument.createElement("DateMissionnement1");
        Ticket.appendChild(element);
        aString = ticket_0513.getDateMissionnement1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Heure de missionnement
        element = MyDocument.createElement("HeureMissionnement1");
        Ticket.appendChild(element);
        aString = ticket_0513.getHeureMissionnement1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString.substring(0, 5)));
        }

        // Le nom du second/dernier prestataire contacté.
        element = MyDocument.createElement("Prestataire2");
        Ticket.appendChild(element);
        aString = ticket_0513.getNomPrestataire2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Date de missionnement
        element = MyDocument.createElement("DateMissionnement2");
        Ticket.appendChild(element);
        aString = ticket_0513.getDateMissionnement2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Heure de missionnement
        element = MyDocument.createElement("HeureMissionnement2");
        Ticket.appendChild(element);
        aString = ticket_0513.getHeureMissionnement2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString.substring(0, 5)));
        }

        // Le numéro de log de l'opérateur
        element = MyDocument.createElement("LogOperateur");
        Ticket.appendChild(element);
        anInt = ticket_0513.getLogOperateur();
        if (anInt > 2000) {
            element.appendChild(MyDocument.createTextNode(String.valueOf(anInt)));
        }
    }
}
