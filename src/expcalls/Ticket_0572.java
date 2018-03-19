package expcalls;

import bdd.EtatTicket;
import bdd.Factivity;
import bdd.FactivityDAO;
import bdd.Fcalls;
import bdd.Fcomplmt;
import bdd.Fessais;
import bdd.FessaisDAO;
import bdd.Ftoubib;
import bdd.FtoubibDAO;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe représentant un ticket pour les clients de la famille du client 572
 *
 * @author Thierry Baribaud
 * @version 0.39
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
     * @param connection connexion à la base de données courante.
     * @param Fcalls_0000 appel,
     * @param Fcomplmt_0000 complément d'appel.
     * @param etatTicket etat du ticket.
     * @throws java.lang.ClassNotFoundException en cas de classe non trouvée.
     * @throws java.sql.SQLException en cas d'erreur SQL.
     */
    public Ticket_0572(Connection connection, Fcalls Fcalls_0000, Fcomplmt Fcomplmt_0000, EtatTicket etatTicket) throws ClassNotFoundException, SQLException {
        super(connection, Fcalls_0000, Fcomplmt_0000, etatTicket);

        Fessais fessais;
        FessaisDAO fessaisDAO;
        int tnum = 0;
        Ftoubib ftoubib;
        FtoubibDAO ftoubibDAO;
        boolean loop = true;
        int n;
        int delay;
        int enumabs1 = 0;
        int a4num;
        String a4name;
        Factivity factivity;
        FactivityDAO factivityDAO;
        StringBuffer etatIntervention;

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
        } else {
            setDegatsDesEaux("NON");
        }

        // Indication de site trouvé en base de données cf. librep0572.4gl en dur.
        if ("SIEGE".equals(getA6abbname())) {
            setSiteEnBase("NON");
        } else {
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
        // Pour la famille du client 572, on gère les status Intervention/Message autrement
        this.setEtatIntervention("Message");

        // Recherche la première mise en sommeil sur un ticket de type message
//        System.out.println("  Récupération de la première mise en sommeil");
        if (this.getEtatIntervention().equals("Message")) {
            fessaisDAO = new FessaisDAO(connection, etatTicket);
//          fessaisDAO.setTrialStatementOrderby("order by edate desc, etime desc");
            fessaisDAO.setTrialStatementOrderby("order by edate, etime");
            fessaisDAO.setTrialPreparedStatement(this.Fcalls_0000.getCnum(), 23);
            n = 0;
            loop = true;
            while (loop && (fessais = fessaisDAO.getTrial()) != null) {
                n++;
                if ((tnum = fessais.getEtnum()) > 0) {
                    ftoubibDAO = new FtoubibDAO(connection);
                    ftoubibDAO.filterById(tnum);
                    ftoubibDAO.setSelectPreparedStatement();
                    if ((ftoubib = ftoubibDAO.select()) != null) {
                        if (!ftoubib.getTlname().contentEquals("message")) {
                            this.setEtatIntervention("Intervention");
                            this.setNomPrestataire1(ftoubib.getTlname());
                            this.setPrenomPrestataire1(ftoubib.getTfname());
                            this.setPrestataire1(ftoubib.getTlname(), ftoubib.getTfname());
                            this.setNoTelephone1(ftoubib.getTel());
                            this.setEmail1(ftoubib.getTemail());
                            delay = ftoubib.getTdelay1() * 60;
                            if (delay > 0) {
                                this.setDelaiIntervention1(CharDur(delay));
                            }
                            a4num = ftoubib.getTa4num();
                            if (a4num > 0) {
                                factivityDAO = new FactivityDAO(connection);
                                factivityDAO.filterById(a4num);
                                factivityDAO.setSelectPreparedStatement();
                                factivity = factivityDAO.select();
                                if (factivity != null) {
                                    a4name = factivity.getA4name();
                                    if (a4name != null) {
                                        this.setA4name1(a4name);
                                    }
                                }
                                factivityDAO.closeSelectPreparedStatement();
                            }
                            loop = false;
                        }
                    }
                    ftoubibDAO.closeSelectPreparedStatement();
                }
            }
            fessaisDAO.closeTrialPreparedStatement();
        }

        // Recherche la première transmission
//        System.out.println("  Récupération de la première transmission");
        fessaisDAO = new FessaisDAO(connection, etatTicket);
        fessaisDAO.setTrialStatementOrderby("order by edate, etime");
        fessaisDAO.setTrialPreparedStatement(this.Fcalls_0000.getCnum(), 1);
        n = 0;
        loop = true;
        while (loop && (fessais = fessaisDAO.getTrial()) != null) {
            n++;
            if ((tnum = fessais.getEtnum()) > 0) {
                ftoubibDAO = new FtoubibDAO(connection);
                ftoubibDAO.filterById(tnum);
                ftoubibDAO.setSelectPreparedStatement();
                if ((ftoubib = ftoubibDAO.select()) != null) {
                    if (!ftoubib.getTlname().contentEquals("message")) {
                        enumabs1 = fessais.getEnumabs();
                        this.setEtatIntervention("Intervention");
                        this.setNomPrestataire1(ftoubib.getTlname());
                        this.setPrenomPrestataire1(ftoubib.getTfname());
                        this.setPrestataire1(ftoubib.getTlname(), ftoubib.getTfname());
                        this.setNoTelephone1(ftoubib.getTel());
                        this.setEmail1(ftoubib.getTemail());
                        delay = ftoubib.getTdelay1() * 60;
                        if (delay > 0) {
                            this.setDelaiIntervention1(CharDur(delay));
                        }
                        a4num = ftoubib.getTa4num();
                        if (a4num > 0) {
                            factivityDAO = new FactivityDAO(connection);
                            factivityDAO.filterById(a4num);
                            factivityDAO.setSelectPreparedStatement();
                            factivity = factivityDAO.select();
                            if (factivity != null) {
                                a4name = factivity.getA4name();
                                if (a4name != null) {
//                                    this.setA4name1(a4name  + " , a4num=" + a4num + ", tnum=" + tnum);
                                    this.setA4name1(a4name);
                                }
                            }
                            factivityDAO.closeSelectPreparedStatement();
                        }
                        loop = false;
                    }
                }
                ftoubibDAO.closeSelectPreparedStatement();
            }
        }
        fessaisDAO.closeTrialPreparedStatement();

        // Recherche la dernière transmission
        //System.out.println("  Récupération de la dernière transmission");
        // ATTENTION : Incorporer enumabs1 dans la requête ultérieurement
        fessaisDAO = new FessaisDAO(connection, etatTicket);
        fessaisDAO.setTrialStatementOrderby("order by edate desc, etime desc");
        fessaisDAO.setTrialPreparedStatement(this.Fcalls_0000.getCnum(), 1);
        n = 0;
        loop = true;
        while (loop && (fessais = fessaisDAO.getTrial()) != null) {
            n++;
            if (fessais.getEnumabs() != enumabs1) {
                if ((tnum = fessais.getEtnum()) > 0) {
                    ftoubibDAO = new FtoubibDAO(connection);
                    ftoubibDAO.filterById(tnum);
                    ftoubibDAO.setSelectPreparedStatement();
                    if ((ftoubib = ftoubibDAO.select()) != null) {
                        if (!ftoubib.getTlname().contentEquals("message")) {
                            this.setEtatIntervention("Intervention");
                            this.setNomPrestataire2(ftoubib.getTlname());
                            this.setPrenomPrestataire2(ftoubib.getTfname());
                            this.setPrestataire2(ftoubib.getTlname(), ftoubib.getTfname());
                            this.setNoTelephone2(ftoubib.getTel());
                            this.setEmail2(ftoubib.getTemail());
                            delay = ftoubib.getTdelay1() * 60;
                            if (delay > 0) {
                                this.setDelaiIntervention2(CharDur(delay));
                            }
                            a4num = ftoubib.getTa4num();
                            if (a4num > 0) {
                                factivityDAO = new FactivityDAO(connection);
                                factivityDAO.filterById(a4num);
                                factivityDAO.setSelectPreparedStatement();
                                factivity = factivityDAO.select();
                                if (factivity != null) {
                                    a4name = factivity.getA4name();
                                    if (a4name != null) {
                                        this.setA4name2(a4name);
                                    }
                                }
                                factivityDAO.closeSelectPreparedStatement();
                            }
                            loop = false;
                        }
                    }
                    ftoubibDAO.closeSelectPreparedStatement();
                }
            } else {
                loop = false;
            }
        }
        fessaisDAO.closeTrialPreparedStatement();

        if (isTicketCanceled()) {
            etatIntervention = new StringBuffer(getEtatIntervention());

            if ("Message".equals(etatIntervention.toString())) {
                etatIntervention.append(" annulé");
            } else {
                etatIntervention.append(" annulée");
            }
            setEtatIntervention(etatIntervention.toString());
        }
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
