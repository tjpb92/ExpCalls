package expcalls;

import bdd.ClotureAppel;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import utils.XMLDocument;
import org.w3c.dom.Comment;
import org.w3c.dom.Element;

/**
 * Classe pour générer un fichier au format XML décrivant des tickets des
 * clients de la famille 582.
 *
 * @author Thierry Baribaud
 * @version 0.25
 */
public class Calls_0582_XMLDocument extends XMLDocument {

    /**
     * Initialise le document XML - constructeur principal.
     *
     * @param RootName nom de la racine du document XML.
     * @param XsdFile nom du fichier contenant le schéma XML.
     * @param MyComment commentaire sur le contenu du fichier.
     */
    public Calls_0582_XMLDocument(String RootName, String XsdFile, String MyComment) {
        super(RootName, XsdFile, MyComment);
    }

    /**
     * Initialise le document XML - constructeur secondaire.
     *
     * @param RootName nom de la racine du document XML.
     * @param XsdFile nom du fichier contenant le schéma XML.
     */
    public Calls_0582_XMLDocument(String RootName, String XsdFile) {
        this(RootName, XsdFile, null);
    }

    /**
     * Ajoute un ticket au document XML. Le ticket est décrit de manière non
     * structurée - flat schema -
     *
     * @param MyTicket ticket à transcrire en XML.
     */
    public void AddToXMLDocument(Ticket_0582 MyTicket) {

        Comment MyComment;
        Element MyElement;
        String MyString;
        int myInt;
        Timestamp MyTimestamp;
        ClotureAppel MyClotureAppel;

        Element Ticket;

        DateFormat MyDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        MyString = "Ticket ref=" + MyTicket.Fcalls_0000.getCnum();
        if (MyString != null) {
            MyComment = MyDocument.createComment(MyString);
            MyElements.appendChild(MyComment);
        }

        Ticket = MyDocument.createElement("ticket");
        MyElements.appendChild(Ticket);

        // Numéro de semaine
        MyElement = MyDocument.createElement("Semaine");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcalls_0000.getCWeekNum();
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
        MyElement = MyDocument.createElement("HeureAppel");
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
            MyElement.appendChild(MyDocument.createTextNode(String.valueOf(myInt)));
        }

        // Agence
        MyElement = MyDocument.createElement("Entreprise");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getA6name();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Site trouvé en base de données ?
        MyElement = MyDocument.createElement("SiteEnBase");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getSiteEnBase();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Site sous contrat
        MyElement = MyDocument.createElement("SiteSousContrat");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcalls_0000.getCnumber8();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Numéro responsable
        MyElement = MyDocument.createElement("NumeroResponsable");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcalls_0000.getCnumber9();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Délai d'intervention
        MyElement = MyDocument.createElement("Delai");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getDelaiIntervention();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Numéro HUGO
        MyElement = MyDocument.createElement("NumeroHugo");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcomplmt_0000.getC6alpha2();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Client
        MyElement = MyDocument.createElement("Client");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcalls_0000.getCorp();
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
        MyElement = MyDocument.createElement("Complement");
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

        // Digicode
        MyElement = MyDocument.createElement("Digicode");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcalls_0000.getCnumber6();
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

        // Nom de l'appelant
        // On remplace la barre verticale qui sépare le nom du prénom par un espace.
        MyElement = MyDocument.createElement("Nom");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.Fcalls_0000.getCname().replace('|', ' ');
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

        // Raison d'appel
        MyElement = MyDocument.createElement("RaisonAppel");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getM6name();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Famille/Métier
        MyElement = MyDocument.createElement("FamilleMetier");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getM6extname();
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

        // Etat de l'intervention
        MyElement = MyDocument.createElement("SuiviDonneALaDemande");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getEtatIntervention();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Prestataire1
        MyElement = MyDocument.createElement("Prestataire1");
        Ticket.appendChild(MyElement);
        MyString = MyTicket.getNomPrestataire1();
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
        MyString = MyTicket.getNomPrestataire2();
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
        MyClotureAppel = MyTicket.getClotureAppel();
        myInt = MyTicket.Fcalls_0000.getCnote();
        MyString = (myInt == 1) ? "Appel clôturé" : "Appel non clôturé";
        MyElement = MyDocument.createElement("InterventionCloturee");
        Ticket.appendChild(MyElement);
        MyElement.appendChild(MyDocument.createTextNode(MyString));

        // Heure de début d'intervention
        MyElement = MyDocument.createElement("HeureDebutIntervention");
        Ticket.appendChild(MyElement);
        MyString = MyClotureAppel.getHeureDebutRelevee();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Heure de fin d'intervention
        MyElement = MyDocument.createElement("HeureFinIntervention");
        Ticket.appendChild(MyElement);
        MyString = MyClotureAppel.getHeureFinRelevee();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Durée d'intervention
        MyElement = MyDocument.createElement("DureeIntervention");
        Ticket.appendChild(MyElement);
        myInt = MyClotureAppel.getDureeIntervention();
        if (myInt > 0) {
            MyElement.appendChild(MyDocument.createTextNode(MyTicket.CharDur(myInt)));
        }

        // Résultat de l'intervention
        MyElement = MyDocument.createElement("ResultatIntervention");
        Ticket.appendChild(MyElement);
        MyString = MyClotureAppel.getResultat();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Rapport d'intervention
        MyElement = MyDocument.createElement("Commentaires");
        Ticket.appendChild(MyElement);
        MyString = MyClotureAppel.getRapport();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }

        // Le technicien est-il encore sur site ?
        MyElement = MyDocument.createElement("TechnicienSurSite");
        Ticket.appendChild(MyElement);
        MyString = MyClotureAppel.getOnSite();
        if (MyString != null) {
            MyElement.appendChild(MyDocument.createTextNode(MyString));
        }
    }
}
