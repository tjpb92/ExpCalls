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
 * clients de la famille 609.
 *
 * @author Thierry Baribaud
 * @version 0.29
 */
public class Calls_0609_XMLDocument extends XMLDocument {

    /**
     * Initialise le document XML - constructeur principal.
     *
     * @param RootName nom de la racine du document XML.
     * @param XsdFile nom du fichier contenant le schéma XML.
     * @param MyComment commentaire sur le contenu du fichier.
     */
    public Calls_0609_XMLDocument(String RootName, String XsdFile, String MyComment) {
        super(RootName, XsdFile, MyComment);
    }

    /**
     * Initialise le document XML - constructeur secondaire.
     *
     * @param RootName nom de la racine du document XML.
     * @param XsdFile nom du fichier contenant le schéma XML.
     */
    public Calls_0609_XMLDocument(String RootName, String XsdFile) {
        this(RootName, XsdFile, null);
    }

    /**
     * Ajoute un ticket au document XML. Le ticket est décrit de manière non
     * structurée - flat schema -
     *
     * @param ticket_0609 ticket à transcrire en XML.
     */
    public void AddToXMLDocument(Ticket_0609 ticket_0609) {

        Comment comment;
        Element element;
        String aString;
        int anInt;
        Timestamp aTimestamp;
        ClotureAppel clotureAppel;

        Element Ticket;

        DateFormat MyDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        aString = "Ticket ref=" + ticket_0609.Fcalls_0000.getCnum();
        if (aString != null) {
            comment = MyDocument.createComment(aString);
            MyElements.appendChild(comment);
        }

        Ticket = MyDocument.createElement("ticket");
        MyElements.appendChild(Ticket);

        // Numéro de semaine
        element = MyDocument.createElement("Semaine");
        Ticket.appendChild(element);
        aString = ticket_0609.Fcalls_0000.getCWeekNum();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        
        // Date de saisie
        element = MyDocument.createElement("DateAppel");
        Ticket.appendChild(element);
        aTimestamp = ticket_0609.Fcalls_0000.getCdate();
        if (aTimestamp != null) {
            element.appendChild(MyDocument.createTextNode(MyDateFormat.format(aTimestamp)));
        }

        // Heure de saisie
        element = MyDocument.createElement("HeureAppel");
        Ticket.appendChild(element);
        aString = ticket_0609.Fcalls_0000.getCtime();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Numéro de ticket
        element = MyDocument.createElement("NumeroDeDossier");
        Ticket.appendChild(element);
        anInt = ticket_0609.Fcalls_0000.getCseqno();
        if (anInt > 0) {
            element.appendChild(MyDocument.createTextNode(String.valueOf(anInt)));
//                    + "/" + String.valueOf(ticket_0609.Fcalls_0000.getCnum())));
        }

        // Agence
        // ATTENTION : Cas particulier pour client 620.
        element = MyDocument.createElement("ZoneGeographique");
        Ticket.appendChild(element);
        if (ticket_0609.Fcalls_0000.getCseqno() == 620) 
            aString = ticket_0609.getA6name();
        else
            if ((aString = ticket_0609.getA6extname()) == null) aString = ticket_0609.getA6name();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Site trouvé en base de données ?
        element = MyDocument.createElement("SiteEnBase");
        Ticket.appendChild(element);
        aString = ticket_0609.getSiteEnBase();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        
        // Numéro du site
        element = MyDocument.createElement("NumeroSite");
        Ticket.appendChild(element);
        aString = ticket_0609.Fcomplmt_0000.getC6alpha1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Délai d'intervention
        element = MyDocument.createElement("Delai");
        Ticket.appendChild(element);
        aString = ticket_0609.getDelaiIntervention();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        
        // Client
        element = MyDocument.createElement("Client");
        Ticket.appendChild(element);
        aString = ticket_0609.Fcalls_0000.getCorp();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Adresse complète
        element = MyDocument.createElement("Adresse");
        Ticket.appendChild(element);
        aString = ticket_0609.Fcalls_0000.getCaddress();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        element = MyDocument.createElement("Complement");
        Ticket.appendChild(element);
        aString = ticket_0609.Fcalls_0000.getCaddress2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        element = MyDocument.createElement("CP");
        Ticket.appendChild(element);
        aString = ticket_0609.Fcalls_0000.getCposcode();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        element = MyDocument.createElement("Ville");
        Ticket.appendChild(element);
        aString = ticket_0609.Fcalls_0000.getCity();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Bâtiment
        element = MyDocument.createElement("Batiment");
        Ticket.appendChild(element);
        aString = ticket_0609.Fcalls_0000.getCnumber4();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        // Escalier
        element = MyDocument.createElement("Escalier");
        Ticket.appendChild(element);
        aString = ticket_0609.Fcalls_0000.getCnumber5();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Nom de l'appelant
        // On remplace la barre verticale qui sépare le nom du prénom par un espace.
        element = MyDocument.createElement("Nom");
        Ticket.appendChild(element);
        aString = ticket_0609.Fcalls_0000.getCname().replace('|', ' ');
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Téléphone de l'appelant
        element = MyDocument.createElement("TelAppelant");
        Ticket.appendChild(element);
        aString = ticket_0609.Fcalls_0000.getCtel();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Raison d'appel
        element = MyDocument.createElement("RaisonAppel");
        Ticket.appendChild(element);
        aString = ticket_0609.getM6name();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Famille/Métier
        element = MyDocument.createElement("FamilleMetier");
        Ticket.appendChild(element);
        aString = ticket_0609.getM6extname();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Demande d'intervention
        element = MyDocument.createElement("DemandeIntervention");
        Ticket.appendChild(element);
        aString = ticket_0609.Fcalls_0000.getCsympt();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Etat de l'intervention
        element = MyDocument.createElement("SuiviDonneALaDemande");
        Ticket.appendChild(element);
        aString = ticket_0609.getEtatIntervention();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Prestataire1
        element = MyDocument.createElement("Prestataire1");
        Ticket.appendChild(element);
        aString = ticket_0609.getNomPrestataire1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // DateMissionnement1
        element = MyDocument.createElement("DateMissionnement1");
        Ticket.appendChild(element);
        aString = ticket_0609.getDateMissionnement1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // HeureMissionnement1
        element = MyDocument.createElement("HeureMissionnement1");
        Ticket.appendChild(element);
        aString = ticket_0609.getHeureMissionnement1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // NoTelephone1
        element = MyDocument.createElement("NoTelephone1");
        Ticket.appendChild(element);
        aString = ticket_0609.getNoTelephone1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Prestataire2
        element = MyDocument.createElement("Prestataire2");
        Ticket.appendChild(element);
        aString = ticket_0609.getNomPrestataire2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // DateMissionnement2
        element = MyDocument.createElement("DateMissionnement2");
        Ticket.appendChild(element);
        aString = ticket_0609.getDateMissionnement2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // HeureMissionnement2
        element = MyDocument.createElement("HeureMissionnement2");
        Ticket.appendChild(element);
        aString = ticket_0609.getHeureMissionnement2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // NoTelephone2
        element = MyDocument.createElement("NoTelephone2");
        Ticket.appendChild(element);
        aString = ticket_0609.getNoTelephone2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Cloture de l'appel
        clotureAppel = ticket_0609.getClotureAppel();
        anInt = ticket_0609.Fcalls_0000.getCnote();
        aString = (anInt == 1) ? "Appel clôturé" : "Appel non clôturé";
        element = MyDocument.createElement("InterventionCloturee");
        Ticket.appendChild(element);
        element.appendChild(MyDocument.createTextNode(aString));

        // Heure de début d'intervention
        element = MyDocument.createElement("HeureDebutIntervention");
        Ticket.appendChild(element);
        aString = clotureAppel.getHeureDebutRelevee();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Heure de fin d'intervention
        element = MyDocument.createElement("HeureFinIntervention");
        Ticket.appendChild(element);
        aString = clotureAppel.getHeureFinRelevee();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Durée d'intervention
        element = MyDocument.createElement("DureeIntervention");
        Ticket.appendChild(element);
        anInt = clotureAppel.getDureeIntervention();
        if (anInt > 0) {
            element.appendChild(MyDocument.createTextNode(ticket_0609.CharDur(anInt)));
        }

        // Résultat de l'intervention
        element = MyDocument.createElement("ResultatIntervention");
        Ticket.appendChild(element);
        aString = clotureAppel.getResultat();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Rapport d'intervention
        element = MyDocument.createElement("Commentaires");
        Ticket.appendChild(element);
        aString = clotureAppel.getRapport();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Le technicien est-il encore sur site ?
        element = MyDocument.createElement("TechnicienSurSite");
        Ticket.appendChild(element);
        aString = clotureAppel.getOnSite();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Le site est-il sous contrat ?
        element = MyDocument.createElement("SiteSousContrat");
        Ticket.appendChild(element);
        aString = ticket_0609.getSiteSousContrat();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
    }
}
