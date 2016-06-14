package expcalls;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.w3c.dom.Comment;
import org.w3c.dom.Element;
import utils.XMLDocument;

/**
 * Classe pour générer un fichier au format XML décrivant des tickets pour les
 * clients de la famille du client 572.
 *
 * @version Juin 2016
 * @author Thierry Baribaud
 */
public class Calls_0572_XMLDocument extends XMLDocument {

    public Calls_0572_XMLDocument(String RootName, String XsdFile) {
        super(RootName, XsdFile);
    }

    /**
     * Ajoute un ticket au document XML. Le ticket est décrit de manière non
     * structurée - flat schema -
     *
     * @param MyTicket ticket à transcrire en XML.
     */
    public void AddToXMLDocument(Ticket_0572 MyTicket) {

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

        // Date de saisie
        MyElement = MyDocument.createElement("DateDAppel");
        Ticket.appendChild(MyElement);
        MyTimestamp = MyTicket.Fcalls_0000.getCdate();
        if (MyTimestamp != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyDateFormat.format(MyTimestamp)));
        }

        // Heure de saisie
        MyElement = MyDocument.createElement("HeureDAppel");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcalls_0000.getCtime();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Numéro de ticket
        MyElement = MyDocument.createElement("NumeroDeDossier");
        Ticket.appendChild(MyElement);
        myInt = MyTicket.Fcalls_0000.getCseqno();
        if (myInt > 0) {
            MyElement.appendChild(MyDocument.createTextNode(String.valueOf(myInt) 
                    + "/" + String.valueOf(MyTicket.Fcalls_0000.getCnum())));
        }

        // Degré d'urgence
        MyElement = MyDocument.createElement("DegreDUrgence");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getDegreDUrgence();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Agence
        MyElement = MyDocument.createElement("Patrimoine");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getA6name();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Nature du site
        MyElement = MyDocument.createElement("NatureDuSite");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcomplmt_0000.getC6alpha1();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Site en base ?
        MyElement = MyDocument.createElement("SiteEnBase");
        Ticket.appendChild(MyElement);
//        MyString = MyTicket.Fcomplmt_0000.getC6alpha2();
//        if (MyString != null) {
//            MyElement.appendChild(MyDocument.createTextNode(MyString));
//        }
        
        // Code immeuble
        MyElement = MyDocument.createElement("CodeImmeuble");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcomplmt_0000.getC6alpha2();
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
        MyElement = MyDocument.createElement("ComplementAdresse");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcalls_0000.getCaddress2();
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

        // Bâtiment
        MyElement = MyDocument.createElement("Batiment");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcalls_0000.getCnumber4();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }
        // Escalier
        MyElement = MyDocument.createElement("Escalier");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcalls_0000.getCnumber5();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Nature appelant
        MyElement = MyDocument.createElement("NatureAppelant");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcomplmt_0000.getC6city();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // nom du gardien/locataire n°1
        MyElement = MyDocument.createElement("NomGardienLocataire1");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcalls_0000.getCname();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Téléphone de l'appelant n°1
        MyElement = MyDocument.createElement("TelAppelant1");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcalls_0000.getCtel();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // nom du gardien/locataire n°2
        MyElement = MyDocument.createElement("NomGardienLocataire2");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcomplmt_0000.getC6name();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Téléphone de l'appelant n°2
        MyElement = MyDocument.createElement("TelAppelant2");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcomplmt_0000.getC6tel();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Raison d'appel
        MyElement = MyDocument.createElement("RaisonDeLAppel");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getM6name();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Type d'intervention
        MyElement = MyDocument.createElement("TypeIntervention");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcalls_0000.getCsector2();
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

        // Dégâts des eaux
        MyElement = MyDocument.createElement("DegatsDesEaux");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getDegatsDesEaux();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Type de demande
        // Numéro d'OS
        MyElement = MyDocument.createElement("NumeroOS");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcomplmt_0000.getC6access();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }
        
        // Etat de l'intervention
        MyElement = MyDocument.createElement("EtatIntervention");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getEtatIntervention();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Prestataire1
        MyElement = MyDocument.createElement("Prestataire1");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getPrestataire1();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // DateMissionnement1
        MyElement = MyDocument.createElement("DateMissionnement1");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getDateMissionnement1();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // HeureMissionnement1
        MyElement = MyDocument.createElement("HeureMissionnement1");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getHeureMissionnement1();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // NoTelephone1
        MyElement = MyDocument.createElement("NoTelephone1");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getNoTelephone1();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Prestataire2
        MyElement = MyDocument.createElement("Prestataire2");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getPrestataire2();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // DateMissionnement2
        MyElement = MyDocument.createElement("DateMissionnement2");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getDateMissionnement2();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // HeureMissionnement2
        MyElement = MyDocument.createElement("HeureMissionnement2");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getHeureMissionnement2();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // NoTelephone2
        MyElement = MyDocument.createElement("NoTelephone2");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getNoTelephone2();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Cloture de l'appel
        myInt = MyTicket.Fcalls_0000.getCnote();
        MyString = (myInt == 1) ? "Appel clôturé" : "Appel non clôturé";
        MyElement = MyDocument.createElement("ResultatIntervention");
        Ticket.appendChild(MyElement);
        MyElement.appendChild(MyDocument.createTextNode(MyString));

        // Rapport d'intervention
        MyElement = MyDocument.createElement("RapportIntervention");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getRapportIntervention();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Le technicien est-il encore sur site ?
        MyElement = MyDocument.createElement("TechnicienSurSite");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getTechnicienSurSite();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }
    }
}
