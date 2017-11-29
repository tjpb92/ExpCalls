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
 * clients de la famille 648.
 *
 * @author Thierry Baribaud
 * @version 0.33
 */
public class Calls_0648_XMLDocument extends XMLDocument {

    /**
     * Format pour le rendu des dates : jj/mm/aaaa.
     */
    private final static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Format pour le rendu des mois : 01, 02, ..., 12.
     */
    private final static DateFormat monthFormat = new SimpleDateFormat("MM");

    /**
     * Initialise le document XML - constructeur principal.
     *
     * @param RootName nom de la racine du document XML.
     * @param XsdFile nom du fichier contenant le schéma XML.
     * @param MyComment commentaire sur le contenu du fichier.
     */
    public Calls_0648_XMLDocument(String RootName, String XsdFile, String MyComment) {
        super(RootName, XsdFile, MyComment);
    }

    /**
     * Initialise le document XML - constructeur secondaire.
     *
     * @param RootName nom de la racine du document XML.
     * @param XsdFile nom du fichier contenant le schéma XML.
     */
    public Calls_0648_XMLDocument(String RootName, String XsdFile) {
        this(RootName, XsdFile, null);
    }

    /**
     * Ajoute un ticket au document XML. Le ticket est décrit de manière non
     * structurée - flat schema -
     *
     * @param ticket_0648 ticket à transcrire en XML.
     */
    public void AddToXMLDocument(Ticket_0648 ticket_0648) {

        Comment comment;
        Element element;
        String aString;
        int anInt;
        Timestamp aTimestamp;
        ClotureAppel clotureAppel;

        Element Ticket;

        aString = "Ticket ref=" + ticket_0648.Fcalls_0000.getCnum();
        if (aString != null) {
            comment = MyDocument.createComment(aString);
            MyElements.appendChild(comment);
        }

        Ticket = MyDocument.createElement("ticket");
        MyElements.appendChild(Ticket);

        // Date de saisie
        element = MyDocument.createElement("DateDeSaisie");
        Ticket.appendChild(element);
        aTimestamp = ticket_0648.Fcalls_0000.getCdate();
        if (aTimestamp != null) {
            element.appendChild(MyDocument.createTextNode(dateFormat.format(aTimestamp)));
        }

        // Mois de saisie
        element = MyDocument.createElement("MoisDeSaisie");
        Ticket.appendChild(element);
        if (aTimestamp != null) {
            element.appendChild(MyDocument.createTextNode(monthFormat.format(aTimestamp)));
        }

        // Heure de saisie
        element = MyDocument.createElement("HeureDeSaisie");
        Ticket.appendChild(element);
        aString = ticket_0648.Fcalls_0000.getCtime();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Numéro de ticket
        element = MyDocument.createElement("NumeroDeDossier");
        Ticket.appendChild(element);
        anInt = ticket_0648.Fcalls_0000.getCseqno();
        if (anInt > 0) {
            element.appendChild(MyDocument.createTextNode(String.valueOf(anInt)));
//                    + "/" + String.valueOf(ticket_0609.Fcalls_0000.getCnum())));
        }

        // Agence
        element = MyDocument.createElement("ProgrammeAgence");
        Ticket.appendChild(element);
        if ((aString = ticket_0648.getA6extname()) == null) {
            aString = ticket_0648.getA6name();
        }
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Code groupe
        element = MyDocument.createElement("CodeGroupe");
        Ticket.appendChild(element);
        if ((aString = ticket_0648.Fcomplmt_0000.getC6alpha2()) != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // libellé groupe
        element = MyDocument.createElement("LibelleGroupe");
        Ticket.appendChild(element);
        if ((aString = ticket_0648.Fcalls_0000.getCorp()) != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Adresse complète
        element = MyDocument.createElement("Adresse");
        Ticket.appendChild(element);
        aString = ticket_0648.Fcalls_0000.getCaddress();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        element = MyDocument.createElement("Complement");
        Ticket.appendChild(element);
        aString = ticket_0648.Fcalls_0000.getCaddress2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        element = MyDocument.createElement("CP");
        Ticket.appendChild(element);
        aString = ticket_0648.Fcalls_0000.getCposcode();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        element = MyDocument.createElement("Ville");
        Ticket.appendChild(element);
        aString = ticket_0648.Fcalls_0000.getCity();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Bâtiment
        element = MyDocument.createElement("Batiment");
        Ticket.appendChild(element);
        aString = ticket_0648.Fcalls_0000.getCnumber4();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Escalier
        element = MyDocument.createElement("Escalier");
        Ticket.appendChild(element);
        aString = ticket_0648.Fcalls_0000.getCnumber5();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Type de contrat
        element = MyDocument.createElement("TypeDeContrat");
        Ticket.appendChild(element);
        aString = ticket_0648.Fcalls_0000.getCnumber8();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Nom de l'appelant
        // On remplace la barre verticale qui sépare le nom du prénom par un espace.
        element = MyDocument.createElement("Nom");
        Ticket.appendChild(element);
        aString = ticket_0648.Fcalls_0000.getCname().replace('|', ' ');
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Téléphone de l'appelant
        element = MyDocument.createElement("TelAppelant");
        Ticket.appendChild(element);
        aString = ticket_0648.Fcalls_0000.getCtel();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Raison d'appel
        element = MyDocument.createElement("RaisonAppel");
        Ticket.appendChild(element);
        aString = ticket_0648.getM6name();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Demande d'intervention
        element = MyDocument.createElement("DemandeIntervention");
        Ticket.appendChild(element);
        aString = ticket_0648.Fcalls_0000.getCsympt();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Etat de l'intervention
        element = MyDocument.createElement("EtatIntervention");
        Ticket.appendChild(element);
        aString = ticket_0648.getEtatIntervention();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Prestataire1
        element = MyDocument.createElement("Prestataire1");
        Ticket.appendChild(element);
        aString = ticket_0648.getNomPrestataire1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // DateMissionnement1
        element = MyDocument.createElement("DateMissionnement1");
        Ticket.appendChild(element);
        aString = ticket_0648.getDateMissionnement1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // HeureMissionnement1
        element = MyDocument.createElement("HeureMissionnement1");
        Ticket.appendChild(element);
        aString = ticket_0648.getHeureMissionnement1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // NoTelephone1
        element = MyDocument.createElement("NoTelephone1");
        Ticket.appendChild(element);
        aString = ticket_0648.getNoTelephone1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Prestataire2
        element = MyDocument.createElement("Prestataire2");
        Ticket.appendChild(element);
        aString = ticket_0648.getNomPrestataire2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // DateMissionnement2
        element = MyDocument.createElement("DateMissionnement2");
        Ticket.appendChild(element);
        aString = ticket_0648.getDateMissionnement2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // HeureMissionnement2
        element = MyDocument.createElement("HeureMissionnement2");
        Ticket.appendChild(element);
        aString = ticket_0648.getHeureMissionnement2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // NoTelephone2
        element = MyDocument.createElement("NoTelephone2");
        Ticket.appendChild(element);
        aString = ticket_0648.getNoTelephone2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Cloture de l'appel
        clotureAppel = ticket_0648.getClotureAppel();
        anInt = ticket_0648.Fcalls_0000.getCnote();
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

        // Résultat de l'intervention
        element = MyDocument.createElement("ResultatIntervention");
        Ticket.appendChild(element);
        aString = clotureAppel.getResultat();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Rapport d'intervention
        element = MyDocument.createElement("RapportIntervention");
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
    }
}
