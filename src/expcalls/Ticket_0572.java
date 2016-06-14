package expcalls;

import bdd.EtatTicket;
import bdd.Fcalls;
import bdd.Fcomplmt;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe représentant un ticket pour les clients de la famille du client 572
 *
 * @version Juin 2016
 * @author Thierry Baribaud
 */
public class Ticket_0572 extends Ticket_0000 {

    /**
     * Degré d'urgence de la demande d'intervention.
     */
    private String DegreDUrgence;
    
    /**
     * Indication de dégâts des eaux OUI/NON..
     */
    private String DegatsDesEaux;

    /**
     * Indication de site trouvé en base de données.
     */
    private String SiteEnBase;
    
    /**
     * Type de demande.
     */
    private String TypeDeDemande;
    
    /**
     * Contructeur principal de la classe Ticket.
     *
     * @param MyConnection connexion à la base de données courante.
     * @param Fcalls_0000 appel,
     * @param Fcomplmt_0000 complément d'appel.
     * @param MyEtatTicket etat du ticket.
     * @throws java.lang.ClassNotFoundException en cas de classe non trouvée.
     * @throws java.sql.SQLException en cas d'erreur SQL.
     */
    public Ticket_0572(Connection MyConnection, Fcalls Fcalls_0000, Fcomplmt Fcomplmt_0000, EtatTicket MyEtatTicket) throws ClassNotFoundException, SQLException {

        super(MyConnection, Fcalls_0000, Fcomplmt_0000, MyEtatTicket);

        // Degré d'urgence cf. tra_nat_urg_0572() dans libspc0572.4gl.
//        System.out.println("  cdelay1="+Fcalls_0000.getCdelay1());
        switch (this.Fcalls_0000.getCdelay1()) {
            case 1:
                setDegreDUrgence("Immediate");
                break;
            case 2:
                setDegreDUrgence("Courante");
                break;
            default:
                setDegreDUrgence(null);
                break;
        }

        // Indication de dégâts des eaux cf. tra_degat_eau_0572() dans libspc0572.4gl.
        if ("1".equals(this.Fcalls_0000.getCsector1())) {
            setDegatsDesEaux("OUI");
        }
        else {
            setDegatsDesEaux("NON");
        }

        // Indication de site trouvé en base de données cf. librep0572.4gl en dur.
        if ("SIEGE".equals(getA6abbname())) {
            setSiteEnBase("NON");
        }
        else {
            setSiteEnBase("OUI");
        }

        // Indication du type de demande cf. tra_typinter_0572() dans libspc0572.4gl.
        switch (this.Fcomplmt_0000.getC6int2()) {
            case 1: 
                setTypeDeDemande("DIC");
                break;
            case 2: 
                setTypeDeDemande("DI");
                break;
            default:
                setTypeDeDemande("AUTRE");
                break;
        }

        // tra_type_interv_0572
//        switch (this.Fcomplmt_0000.getC6int2()) {
//            case 0: 
//                setTypeDeDemande("Message");
//                break;
//            case 1: 
//                setTypeDeDemande("Immédiate");
//                break;
//            case 2: 
//                setTypeDeDemande("Courante");
//                break;
//            case 3: 
//                setTypeDeDemande("Demande de renseignement");
//                break;
//            case 4: 
//                setTypeDeDemande("Site non couvert en HO");
//                break;
//            default:
//                setTypeDeDemande("Inconnu");
//                break;
//        }
    }

    /**
     * Contructeur secondaire de la classe Ticket.
     *
     * @param MyConnection connexion à la base de données courante.
     * @param Fcalls_0000 appel,
     * @param MyEtatTicket etat du ticket.
     * @throws java.lang.ClassNotFoundException en cas de classe non trouvée.
     * @throws java.sql.SQLException en cas d'erreur SQL.
     */
    public Ticket_0572(Connection MyConnection, Fcalls Fcalls_0000, EtatTicket MyEtatTicket) throws ClassNotFoundException, SQLException {
        this(MyConnection, Fcalls_0000, null, MyEtatTicket);
    }

    /**
     * @return DegreDUrgence le degré d'urgence de la demande d'intervention.
     */
    public String getDegreDUrgence() {
        return DegreDUrgence;
    }

    /**
     * @param DegreDUrgence définit le degré d'urgence de la demande
     * d'intervention.
     */
    public void setDegreDUrgence(String DegreDUrgence) {
        this.DegreDUrgence = DegreDUrgence;
    }

    /**
     * @return DegatsDesEaux indication de dégâts des eaux OUI/NON.
     */
    public String getDegatsDesEaux() {
        return DegatsDesEaux;
    }

    /**
     * @param DegatsDesEaux définit l'indication de dégâts des eaux OUI/NON.
     */
    public void setDegatsDesEaux(String DegatsDesEaux) {
        this.DegatsDesEaux = DegatsDesEaux;
    }

    /**
     * @return SiteEnBase Indication de site trouvé en base de données. 
     */
    public String getSiteEnBase() {
        return SiteEnBase;
    }

    /**
     * @param SiteEnBase définit l'indication de site trouvé en base de données.
     */
    public void setSiteEnBase(String SiteEnBase) {
        this.SiteEnBase = SiteEnBase;
    }

    /**
     * @return TypeDeDemande le type de demande.
     */
    public String getTypeDeDemande() {
        return TypeDeDemande;
    }

    /**
     * @param TypeDeDemande définit le type de demande.
     */
    public void setTypeDeDemande(String TypeDeDemande) {
        this.TypeDeDemande = TypeDeDemande;
    }

}
