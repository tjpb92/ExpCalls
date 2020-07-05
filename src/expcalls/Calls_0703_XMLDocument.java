package expcalls;

import bdd.ClotureAppel;
import bdd.Survey;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.w3c.dom.Comment;
import org.w3c.dom.Element;
import utils.XMLDocument;

/**
 * Classe pour g�n�rer un fichier au format XML d�crivant des tickets pour les
 * clients de la famille du client 703.
 *
 * @author Thierry Baribaud
 * @version 0.57
 */
public class Calls_0703_XMLDocument extends XMLDocument {

    /**
     * Format pour le rendu des dates : jj/mm/aaaa.
     */
    private final static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Format pour le rendu des mois : janvier, f�vrier, ..., d�cembre.
     */
    private final static DateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.FRENCH);

    /**
     * Initialise le document XML - constructeur principal.
     *
     * @param RootName nom de la racine du document XML.
     * @param XsdFile nom du fichier contenant le sch�ma XML.
     * @param comment commentaire sur le contenu du fichier.
     */
    public Calls_0703_XMLDocument(String RootName, String XsdFile, String comment) {
        super(RootName, XsdFile, comment);
    }

    /**
     * Initialise le document XML - constructeur secondaire.
     *
     * @param RootName nom de la racine du document XML.
     * @param XsdFile nom du fichier contenant le sch�ma XML.
     */
    public Calls_0703_XMLDocument(String RootName, String XsdFile) {
        this(RootName, XsdFile, null);
    }

    /**
     * Ajoute un ticket au document XML. Le ticket est d�crit de mani�re non
     * structur�e - flat schema -
     *
     * @param ticket_0703 ticket � transcrire en XML.
     */
    public void AddToXMLDocument(Ticket_0703 ticket_0703) {

        Comment comment;
        Element element;
        String aString;
        int anInt;
        Timestamp aTimestamp;
        ClotureAppel clotureAppel;
        Survey survey;

        Element Ticket;

        aString = "Ticket ref=" + ticket_0703.Fcalls_0000.getCnum();
        if (aString != null) {
            comment = MyDocument.createComment(aString);
            MyElements.appendChild(comment);
        }

        Ticket = MyDocument.createElement("ticket");
        MyElements.appendChild(Ticket);

        // Date de saisie
        element = MyDocument.createElement("DateDAppel");
        Ticket.appendChild(element);
        aTimestamp = ticket_0703.Fcalls_0000.getCdate();
        if (aTimestamp != null) {
            element.appendChild(MyDocument.createTextNode(dateFormat.format(aTimestamp)));
        }

        // Heure de saisie
        element = MyDocument.createElement("HeureDAppel");
        Ticket.appendChild(element);
        aString = ticket_0703.Fcalls_0000.getCtime();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Mois
        element = MyDocument.createElement("Mois");
        Ticket.appendChild(element);
        if (aTimestamp != null) {
            element.appendChild(MyDocument.createTextNode(monthFormat.format(aTimestamp)));
        }

        // Num�ro de ticket
        element = MyDocument.createElement("NumeroDeDossier");
        Ticket.appendChild(element);
        anInt = ticket_0703.Fcalls_0000.getCseqno();
        if (anInt > 0) {
            element.appendChild(MyDocument.createTextNode(String.valueOf(anInt)));
//                    + "/" + String.valueOf(MyTicket.Fcalls_0000.getCnum())));
        }

        // Degr� d'urgence
        element = MyDocument.createElement("DegreDUrgence");
        Ticket.appendChild(element);
        aString = ticket_0703.getCriticalLevel();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Agence
        element = MyDocument.createElement("Patrimoine");
        Ticket.appendChild(element);
        aString = ticket_0703.getA6name();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Nature du site
        element = MyDocument.createElement("NatureDuSite");
        Ticket.appendChild(element);
        aString = ticket_0703.Fcomplmt_0000 != null ? ticket_0703.Fcomplmt_0000.getC6alpha1() : null;
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Site en base ?
        element = MyDocument.createElement("SiteEnBase");
        Ticket.appendChild(element);
        aString = ticket_0703.getSiteEnBase();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Code immeuble
        element = MyDocument.createElement("CodeImmeuble");
        Ticket.appendChild(element);
        aString = ticket_0703.Fcomplmt_0000 != null ? ticket_0703.Fcomplmt_0000.getC6alpha2() : null;
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Adresse compl�te
        element = MyDocument.createElement("Adresse");
        Ticket.appendChild(element);
        aString = ticket_0703.Fcalls_0000.getCaddress();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        element = MyDocument.createElement("ComplementAdresse");
        Ticket.appendChild(element);
        aString = ticket_0703.Fcalls_0000.getCaddress2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        element = MyDocument.createElement("CP");
        Ticket.appendChild(element);
        aString = ticket_0703.Fcalls_0000.getCposcode();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        element = MyDocument.createElement("Ville");
        Ticket.appendChild(element);
        aString = ticket_0703.Fcalls_0000.getCity();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // B�timent
        element = MyDocument.createElement("Batiment");
        Ticket.appendChild(element);
        aString = ticket_0703.Fcalls_0000.getCnumber4();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        // Escalier
        element = MyDocument.createElement("Escalier");
        Ticket.appendChild(element);
        aString = ticket_0703.Fcalls_0000.getCnumber5();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Nature appelant
        element = MyDocument.createElement("NatureAppelant");
        Ticket.appendChild(element);
        aString = ticket_0703.getNatureOfTheCaller();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // nom du gardien/locataire n�1
        element = MyDocument.createElement("NomGardienLocataire1");
        Ticket.appendChild(element);
        aString = ticket_0703.Fcalls_0000.getCname();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // T�l�phone de l'appelant n�1
        element = MyDocument.createElement("TelAppelant1");
        Ticket.appendChild(element);
        aString = ticket_0703.Fcalls_0000.getCtel();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // nom du gardien/locataire n�2
        element = MyDocument.createElement("NomGardienLocataire2");
        Ticket.appendChild(element);
        aString = ticket_0703.Fcomplmt_0000 != null ? ticket_0703.Fcomplmt_0000.getC6name() : null;
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // T�l�phone de l'appelant n�2
        element = MyDocument.createElement("TelAppelant2");
        Ticket.appendChild(element);
        aString = ticket_0703.Fcomplmt_0000 != null ? ticket_0703.Fcomplmt_0000.getC6tel() : null;
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Raison d'appel
        element = MyDocument.createElement("RaisonDeLAppel");
        Ticket.appendChild(element);
        aString = ticket_0703.getM6name();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Type d'intervention
        element = MyDocument.createElement("TypeIntervention");
        Ticket.appendChild(element);
        aString = ticket_0703.Fcalls_0000.getCsector2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Demande d'intervention
        element = MyDocument.createElement("DemandeIntervention");
        Ticket.appendChild(element);
        aString = ticket_0703.Fcalls_0000.getCsympt();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // D�g�ts des eaux
        element = MyDocument.createElement("DegatsDesEaux");
        Ticket.appendChild(element);
        aString = ticket_0703.getDegatsDesEaux();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Type de demande
        element = MyDocument.createElement("TypeDeDemande");
        Ticket.appendChild(element);
        aString = ticket_0703.getTypeDeDemande();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Num�ro d'OS
        element = MyDocument.createElement("NumeroOS");
        Ticket.appendChild(element);
        aString = ticket_0703.Fcomplmt_0000 != null ? ticket_0703.Fcomplmt_0000.getC6access() : null;
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Suivi donn� � la demande
        element = MyDocument.createElement("SuiviDonneALaDemande");
        Ticket.appendChild(element);
        aString = ticket_0703.getEtatIntervention();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Contact n�1
        element = MyDocument.createElement("Contact1");
        Ticket.appendChild(element);
        aString = ticket_0703.getPrestataire1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Type de contact n�1
        element = MyDocument.createElement("TypeDeContact1");
        Ticket.appendChild(element);
        aString = ticket_0703.getA4name1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // DateMissionnement1
        element = MyDocument.createElement("DateDeMissionnement1");
        Ticket.appendChild(element);
        aString = ticket_0703.getDateMissionnement1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // HeureMissionnement1
        element = MyDocument.createElement("HeureDeMissionnement1");
        Ticket.appendChild(element);
        aString = ticket_0703.getHeureMissionnement1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // NoTelephone1
        element = MyDocument.createElement("NoTelephone1");
        Ticket.appendChild(element);
        aString = ticket_0703.getNoTelephone1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // AdresseEmail1
        element = MyDocument.createElement("AdresseEmail1");
        Ticket.appendChild(element);
        aString = ticket_0703.getEmail1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // DelaiIntervention1
        element = MyDocument.createElement("DelaiIntervention1");
        Ticket.appendChild(element);
        aString = ticket_0703.getDelaiIntervention1();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Contact n�2
        element = MyDocument.createElement("Contact2");
        Ticket.appendChild(element);
        aString = ticket_0703.getPrestataire2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Type de contact n�2
        element = MyDocument.createElement("TypeDeContact2");
        Ticket.appendChild(element);
        aString = ticket_0703.getA4name2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // DateMissionnement2
        element = MyDocument.createElement("DateDeMissionnement2");
        Ticket.appendChild(element);
        aString = ticket_0703.getDateMissionnement2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // HeureMissionnement2
        element = MyDocument.createElement("HeureDeMissionnement2");
        Ticket.appendChild(element);
        aString = ticket_0703.getHeureMissionnement2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // NoTelephone2
        element = MyDocument.createElement("NoTelephone2");
        Ticket.appendChild(element);
        aString = ticket_0703.getNoTelephone2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // AdresseEmail2
        element = MyDocument.createElement("AdresseEmail2");
        Ticket.appendChild(element);
        aString = ticket_0703.getEmail2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // DelaiIntervention2
        element = MyDocument.createElement("DelaiIntervention2");
        Ticket.appendChild(element);
        aString = ticket_0703.getDelaiIntervention2();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Cloture de l'appel
        clotureAppel = ticket_0703.getClotureAppel();
        anInt = ticket_0703.Fcalls_0000.getCnote();
        aString = (anInt == 1) ? "Appel cl�tur�" : "Appel non cl�tur�";
        element = MyDocument.createElement("InterventionCloturee");
        Ticket.appendChild(element);
        element.appendChild(MyDocument.createTextNode(aString));

        // Date de d�but d'intervention
        element = MyDocument.createElement("DateDebutIntervention");
        Ticket.appendChild(element);
//        aString = ticket_0703.getDateDebutInterventionRelevee();
        aString = clotureAppel.getDateDebutInterventionRelevee();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Heure de d�but d'intervention
        element = MyDocument.createElement("HeureDebutIntervention");
        Ticket.appendChild(element);
//        aString = ticket_0703.getHeureDebutInterventionRelevee();
        aString = clotureAppel.getHeureDebutInterventionRelevee();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Date de fin d'intervention
        element = MyDocument.createElement("DateFinIntervention");
        Ticket.appendChild(element);
//        aString = ticket_0703.getDateFinInterventionRelevee();
        aString = clotureAppel.getDateFinInterventionRelevee();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Heure de fin d'intervention
        element = MyDocument.createElement("HeureFinIntervention");
        Ticket.appendChild(element);
//        aString = ticket_0703.getHeureDebutInterventionRelevee();
        aString = clotureAppel.getHeureFinInterventionRelevee();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // R�sultat de l'intervention
        element = MyDocument.createElement("ResultatIntervention");
        Ticket.appendChild(element);
        aString = clotureAppel.getResultat();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Nature de la panne
        element = MyDocument.createElement("NaturePanne");
        Ticket.appendChild(element);
        aString = clotureAppel.getNature();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Rapport de cl�ture d'intervention
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
            element.appendChild(MyDocument.createTextNode(ticket_0703.CharDur(anInt)));
        }

        // Enqu�te de satisfaction
        survey = ticket_0703.getSurvey();
        element = MyDocument.createElement("EnqueteEffectuee");
        Ticket.appendChild(element);
        aString = survey.getEnqueteEffectuee();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Qualit� de l'accueil t�l�phonique
        element = MyDocument.createElement("QualiteAccueilTelephonique");
        Ticket.appendChild(element);
        aString = survey.getQualiteAccueilTelephonique();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Qualit� du d�lai d'intervention
        element = MyDocument.createElement("QualiteDelaiIntervention");
        Ticket.appendChild(element);
        aString = survey.getQualiteDelaiIntervention();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Respect du rendez-vous
        element = MyDocument.createElement("RespectRendezVous");
        Ticket.appendChild(element);
        aString = survey.getRespectRendezVous();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Qualit� de l'intervention
        element = MyDocument.createElement("QualiteIntervention");
        Ticket.appendChild(element);
        aString = survey.getQualiteIntervention();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Satisfaction globale
        element = MyDocument.createElement("SatisfactionGlobale");
        Ticket.appendChild(element);
        aString = survey.getSatisfactionGlobale();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Mois de l'enqu�te
        element = MyDocument.createElement("MoisEnquete");
        Ticket.appendChild(element);
        aString = survey.getMoisDeLEnquete();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Un SMS a-t-il �t� envoy� ?
        element = MyDocument.createElement("SmsEnvoye");
        Ticket.appendChild(element);
        aString = ticket_0703.isSmsSent() ? "OUI" : "NON";
        element.appendChild(MyDocument.createTextNode(aString));

        // P�riode durant laquelle a �t� saisie la demande
        element = MyDocument.createElement("Periode");
        Ticket.appendChild(element);
        aString = ticket_0703.getPeriod();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

        // Num�ro de devis : cnumber8
        element = MyDocument.createElement("NumeroDevis");
        Ticket.appendChild(element);
        aString = ticket_0703.Fcalls_0000.getCnumber8();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        
        // Montant du devis : cnumber10
        element = MyDocument.createElement("MontantDevis");
        Ticket.appendChild(element);
        aString = ticket_0703.Fcalls_0000.getCnumber10();
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        
        // Montant de la facture finale : c6alpha8
        element = MyDocument.createElement("MontantFactureFinale");
        Ticket.appendChild(element);
        aString = ticket_0703.Fcomplmt_0000 != null ? ticket_0703.Fcomplmt_0000.getC6alpha8() : null;
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }
        
        // Nom du gestionnaire : cnumber9
        element = MyDocument.createElement("NomDuGestionnaire");
        Ticket.appendChild(element);
        aString = ticket_0703.Fcalls_0000.getCnumber9();
//        System.out.println("  cnumber9:" + ticket_0703.Fcalls_0000.getCnumber9() + ", aString:" + aString);
        if (aString != null) {
            element.appendChild(MyDocument.createTextNode(aString));
        }

    }
}
