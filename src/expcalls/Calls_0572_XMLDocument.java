package expcalls;

import bdd.ClotureAppel;
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
 * @author Thierry Baribaud
 * @version 0.28
 */
public class Calls_0572_XMLDocument extends XMLDocument {

    /**
     * Initialise le document XML - constructeur principal.
     *
     * @param RootName nom de la racine du document XML.
     * @param XsdFile nom du fichier contenant le schéma XML.
     * @param MyComment commentaire sur le contenu du fichier.
     */
    public Calls_0572_XMLDocument(String RootName, String XsdFile, String MyComment) {
        super(RootName, XsdFile, MyComment);
    }

    /**
     * Initialise le document XML - constructeur secondaire.
     *
     * @param RootName nom de la racine du document XML.
     * @param XsdFile nom du fichier contenant le schéma XML.
     */
    public Calls_0572_XMLDocument(String RootName, String XsdFile) {
        this(RootName, XsdFile, null);
    }

    /**
     * Ajoute un ticket au document XML. Le ticket est décrit de manière non
     * structurée - flat schema -
     *
     * @param ticket_0572 ticket à transcrire en XML.
     */
    public void AddToXMLDocument(Ticket_0572 ticket_0572) {

        Comment comment;
        Element element;
        String aString;
        int anInt;
        Timestamp aTimestamp;
        ClotureAppel clotureAppel;

        Element Ticket;

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat monthFormat = new SimpleDateFormat("MMM");

        aString = "Ticket ref=" + ticket_0572.Fcalls_0000.getCnum();
        if (aString != null) {
            comment = MyDocument.createComment(aString);
            MyElements.appendChild(comment);
        }

        Ticket = MyDocument.createElement("ticket");
        MyElements.appendChild(Ticket);

        // Date de saisie
        element = MyDocument.createElement("DateDAppel");
        Ticket.appendChild(element);
        aTimestamp = ticket_0572.Fcalls_0000.getCdate();
        if (aTimestamp != null) {
            element.appendChild(MyDocument.createTextNode(dateFormat.format(aTimestamp)));
        }

        // Heure de saisie
        element = MyDocument.createElement("HeureDAppel");
        Ticket.appendChild(element);
        aString = ticket_0572.Fcalls_0000.getCtime();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Mois
        element = MyDocument.createElement("Mois");
        Ticket.appendChild(element);
        if (aTimestamp != null) {
            element.appendChild(MyDocument.createTextNode(monthFormat.format(aTimestamp)));
        }

        // Numéro de ticket
        element = MyDocument.createElement("NumeroDeDossier");
        Ticket.appendChild(element);
        anInt = ticket_0572.Fcalls_0000.getCseqno();
        if (anInt > 0) {
            element.appendChild(MyDocument.createTextNode(String.valueOf(anInt))); 
//                    + "/" + String.valueOf(MyTicket.Fcalls_0000.getCnum())));
        }

        // Degré d'urgence
        element = MyDocument.createElement("DegreDUrgence");
        Ticket.appendChild(element);
        aString = ticket_0572.getDegreDUrgence();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Agence
        element = MyDocument.createElement("Patrimoine");
        Ticket.appendChild(element);
        aString = ticket_0572.getA6name();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Nature du site
        element = MyDocument.createElement("NatureDuSite");
        Ticket.appendChild(element);
        aString = ticket_0572.Fcomplmt_0000.getC6alpha1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Site en base ?
        element = MyDocument.createElement("SiteEnBase");
        Ticket.appendChild(element);
        aString = ticket_0572.getSiteEnBase();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        
        // Code immeuble
        element = MyDocument.createElement("CodeImmeuble");
        Ticket.appendChild(element);
        aString = ticket_0572.Fcomplmt_0000.getC6alpha2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        
        // Adresse complète
        element = MyDocument.createElement("Adresse");
        Ticket.appendChild(element);
        aString = ticket_0572.Fcalls_0000.getCaddress();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        element = MyDocument.createElement("ComplementAdresse");
        Ticket.appendChild(element);
        aString = ticket_0572.Fcalls_0000.getCaddress2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        element = MyDocument.createElement("CP");
        Ticket.appendChild(element);
        aString = ticket_0572.Fcalls_0000.getCposcode();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        element = MyDocument.createElement("Ville");
        Ticket.appendChild(element);
        aString = ticket_0572.Fcalls_0000.getCity();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Bâtiment
        element = MyDocument.createElement("Batiment");
        Ticket.appendChild(element);
        aString = ticket_0572.Fcalls_0000.getCnumber4();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        // Escalier
        element = MyDocument.createElement("Escalier");
        Ticket.appendChild(element);
        aString = ticket_0572.Fcalls_0000.getCnumber5();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Nature appelant
        element = MyDocument.createElement("NatureAppelant");
        Ticket.appendChild(element);
        aString = ticket_0572.Fcomplmt_0000.getC6city();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // nom du gardien/locataire n°1
        element = MyDocument.createElement("NomGardienLocataire1");
        Ticket.appendChild(element);
        aString = ticket_0572.Fcalls_0000.getCname();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Téléphone de l'appelant n°1
        element = MyDocument.createElement("TelAppelant1");
        Ticket.appendChild(element);
        aString = ticket_0572.Fcalls_0000.getCtel();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // nom du gardien/locataire n°2
        element = MyDocument.createElement("NomGardienLocataire2");
        Ticket.appendChild(element);
        aString = ticket_0572.Fcomplmt_0000.getC6name();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Téléphone de l'appelant n°2
        element = MyDocument.createElement("TelAppelant2");
        Ticket.appendChild(element);
        aString = ticket_0572.Fcomplmt_0000.getC6tel();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Raison d'appel
        element = MyDocument.createElement("RaisonDeLAppel");
        Ticket.appendChild(element);
        aString = ticket_0572.getM6name();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Type d'intervention
        element = MyDocument.createElement("TypeIntervention");
        Ticket.appendChild(element);
        aString = ticket_0572.Fcalls_0000.getCsector2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Demande d'intervention
        element = MyDocument.createElement("DemandeIntervention");
        Ticket.appendChild(element);
        aString = ticket_0572.Fcalls_0000.getCsympt();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Dégâts des eaux
        element = MyDocument.createElement("DegatsDesEaux");
        Ticket.appendChild(element);
        aString = ticket_0572.getDegatsDesEaux();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Type de demande
        element = MyDocument.createElement("TypeDeDemande");
        Ticket.appendChild(element);
        aString = ticket_0572.getTypeDeDemande();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Numéro d'OS
        element = MyDocument.createElement("NumeroOS");
        Ticket.appendChild(element);
        aString = ticket_0572.Fcomplmt_0000.getC6access();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        
        // Suivi donné à la demande
        element = MyDocument.createElement("SuiviDonneALaDemande");
        Ticket.appendChild(element);
        aString = ticket_0572.getEtatIntervention();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Contact n°1
        element = MyDocument.createElement("Contact1");
        Ticket.appendChild(element);
        aString = ticket_0572.getPrestataire1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Type de contact n°1
        element = MyDocument.createElement("TypeDeContact1");
        Ticket.appendChild(element);
        aString = ticket_0572.getA4name1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // DateMissionnement1
        element = MyDocument.createElement("DateDeMissionnement1");
        Ticket.appendChild(element);
        aString = ticket_0572.getDateMissionnement1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // HeureMissionnement1
        element = MyDocument.createElement("HeureDeMissionnement1");
        Ticket.appendChild(element);
        aString = ticket_0572.getHeureMissionnement1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // NoTelephone1
        element = MyDocument.createElement("NoTelephone1");
        Ticket.appendChild(element);
        aString = ticket_0572.getNoTelephone1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // AdresseEmail1
        element = MyDocument.createElement("AdresseEmail1");
        Ticket.appendChild(element);
        aString = ticket_0572.getEmail1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // DelaiIntervention1
        element = MyDocument.createElement("DelaiIntervention1");
        Ticket.appendChild(element);
        aString = ticket_0572.getDelaiIntervention1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Contact n°2
        element = MyDocument.createElement("Contact2");
        Ticket.appendChild(element);
        aString = ticket_0572.getPrestataire2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Type de contact n°2
        element = MyDocument.createElement("TypeDeContact2");
        Ticket.appendChild(element);
        aString = ticket_0572.getA4name2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // DateMissionnement2
        element = MyDocument.createElement("DateDeMissionnement2");
        Ticket.appendChild(element);
        aString = ticket_0572.getDateMissionnement2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // HeureMissionnement2
        element = MyDocument.createElement("HeureDeMissionnement2");
        Ticket.appendChild(element);
        aString = ticket_0572.getHeureMissionnement2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // NoTelephone2
        element = MyDocument.createElement("NoTelephone2");
        Ticket.appendChild(element);
        aString = ticket_0572.getNoTelephone2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // AdresseEmail2
        element = MyDocument.createElement("AdresseEmail2");
        Ticket.appendChild(element);
        aString = ticket_0572.getEmail2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // DelaiIntervention2
        element = MyDocument.createElement("DelaiIntervention2");
        Ticket.appendChild(element);
        aString = ticket_0572.getDelaiIntervention2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Cloture de l'appel
        clotureAppel = ticket_0572.getClotureAppel();
        anInt = ticket_0572.Fcalls_0000.getCnote();
        aString = (anInt == 1) ? "Appel clôturé" : "Appel non clôturé";
        element = MyDocument.createElement("InterventionCloturee");
        Ticket.appendChild(element);
        element.appendChild(MyDocument.createTextNode(aString));

        // Date d'intervention
        element = MyDocument.createElement("DateIntervention");
        Ticket.appendChild(element);
        aString = ticket_0572.getDateInterventionRelevee();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Heure d'intervention
        element = MyDocument.createElement("HeureIntervention");
        Ticket.appendChild(element);
        aString = ticket_0572.getHeureInterventionRelevee();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Résultat de l'intervention
        element = MyDocument.createElement("ResultatIntervention");
        Ticket.appendChild(element);
        aString = clotureAppel.getResultat();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Rapport de clôture d'intervention
        element = MyDocument.createElement("RapportIntervention");
        Ticket.appendChild(element);
        aString = clotureAppel.getRapport();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // DelaiIntervention
        element = MyDocument.createElement("DelaiIntervention");
        Ticket.appendChild(element);
        anInt = clotureAppel.getDelaiIntervention2();
        if (anInt > 0) {
            element.appendChild(MyDocument.createTextNode(ticket_0572.CharDur(anInt)));
        }
    }
}
