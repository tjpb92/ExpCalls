package expcalls;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import liba2pi.XMLDocument;
import org.w3c.dom.Comment;
import org.w3c.dom.Element;

/*
 * Classe pour générer un fichier au format XML décrivant des tickets.
 * @version Mai 2016.
 * @author Thierry Baribaud.
 */
public class CallsXMLDocument extends XMLDocument {

    public CallsXMLDocument(String RootName, String XsdFile) {
        super(RootName, XsdFile);
    }

    /**
     * Ajoute un ticket au document XML. Le ticket est décrit de manière non
     * structurée - flat schema -
     *
     * @param MyTicket ticket à transcrire en XML.
     */
    public void AddToXMLDocument(Ticket_0000 MyTicket) {

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
        MyTimestamp = MyTicket.Fcalls_0000.getCdate();
        if (MyTimestamp != null) {
            MyElement = MyDocument.createElement("DateDeSaisie");
            MyElement.appendChild(MyDocument.createTextNode(MyDateFormat.format(MyTimestamp)));
            Ticket.appendChild(MyElement);
        }

        // Heure de saisie
        MyString = MyTicket.Fcalls_0000.getCtime();
        if (MyString != null) {
            MyElement = MyDocument.createElement("HeureDeSaisie");
            MyElement.appendChild(MyDocument.createTextNode(MyString));
            Ticket.appendChild(MyElement);
        }

        // Numéro de ticket
        myInt = MyTicket.Fcalls_0000.getCseqno();
        if (myInt > 0) {
            MyElement = MyDocument.createElement("NumeroDeDossier");
            MyElement.appendChild(MyDocument.createTextNode(String.valueOf(myInt)));
            Ticket.appendChild(MyElement);
        }

        // Agence
        MyString = MyTicket.getA6name();
        if (MyString != null) {
            MyElement = MyDocument.createElement("ProgrammeAgence");
            MyElement.appendChild(MyDocument.createTextNode(MyString));
            Ticket.appendChild(MyElement);
        }

        // Adresse complète
        MyString = MyTicket.Fcalls_0000.getCaddress();
        if (MyString != null) {
            MyElement = MyDocument.createElement("Adresse");
            MyElement.appendChild(MyDocument.createTextNode(MyString));
            Ticket.appendChild(MyElement);
        }
        MyString = MyTicket.Fcalls_0000.getCaddress2();
        if (MyString != null) {
            MyElement = MyDocument.createElement("Complement");
            MyElement.appendChild(MyDocument.createTextNode(MyString));
            Ticket.appendChild(MyElement);
        }
        MyString = MyTicket.Fcalls_0000.getCposcode();
        if (MyString != null) {
            MyElement = MyDocument.createElement("CP");
            MyElement.appendChild(MyDocument.createTextNode(MyString));
            Ticket.appendChild(MyElement);
        }
        MyString = MyTicket.Fcalls_0000.getCity();
        if (MyString != null) {
            MyElement = MyDocument.createElement("Ville");
            MyElement.appendChild(MyDocument.createTextNode(MyString));
            Ticket.appendChild(MyElement);
        }

        // Bâtiment
        MyString = MyTicket.Fcalls_0000.getCnumber4();
        if (MyString != null) {
            MyElement = MyDocument.createElement("Batiment");
            MyElement.appendChild(MyDocument.createTextNode(MyString));
            Ticket.appendChild(MyElement);
        }
        // Escalier
        MyString = MyTicket.Fcalls_0000.getCnumber5();
        if (MyString != null) {
            MyElement = MyDocument.createElement("Escalier");
            MyElement.appendChild(MyDocument.createTextNode(MyString));
            Ticket.appendChild(MyElement);
        }

        // Nom de l'appelant
        MyString = MyTicket.Fcalls_0000.getCname();
        if (MyString != null) {
            MyElement = MyDocument.createElement("Nom");
            MyElement.appendChild(MyDocument.createTextNode(MyString));
            Ticket.appendChild(MyElement);
        }

        // Téléphone de l'appelant
        MyString = MyTicket.Fcalls_0000.getCtel();
        if (MyString != null) {
            MyElement = MyDocument.createElement("TelAppelant");
            MyElement.appendChild(MyDocument.createTextNode(MyString));
            Ticket.appendChild(MyElement);
        }

        // Raison d'appel
        MyString = MyTicket.getM6name();
        if (MyString != null) {
            MyElement = MyDocument.createElement("RaisonAppel");
            MyElement.appendChild(MyDocument.createTextNode(MyString));
            Ticket.appendChild(MyElement);
        }

        // Demande d'intervention
        MyString = MyTicket.Fcalls_0000.getCsympt();
        if (MyString != null) {
            MyElement = MyDocument.createElement("DemandeIntervention");
            MyElement.appendChild(MyDocument.createTextNode(MyString));
            Ticket.appendChild(MyElement);
        }

        // Etat de l'intervention
        MyString = MyTicket.getEtatIntervention();
        if (MyString != null) {
            MyElement = MyDocument.createElement("EtatIntervention");
            MyElement.appendChild(MyDocument.createTextNode(MyString));
            Ticket.appendChild(MyElement);
        }

        // Prestataire1
        // DateMissionnement1
        // HeureMissionnement1
        // NoTelephone1
        
        // Prestataire2
        // DateMissionnement2
        // HeureMissionnement2
        // NoTelephone2
        
        // Cloture de l'appel
        myInt = MyTicket.Fcalls_0000.getCnote();
        MyString = (myInt == 1) ? "Appel clôturé" : "Appel non clôturé";
        MyElement = MyDocument.createElement("ResultatIntervention");
        MyElement.appendChild(MyDocument.createTextNode(MyString));
        Ticket.appendChild(MyElement);

        // Rapport d'intervention
        // Le technicien est-il encore sur site ?
        
    }
}
