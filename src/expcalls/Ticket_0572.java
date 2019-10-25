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
import bdd.Survey;
import java.sql.Timestamp;

/**
 * Classe repr�sentant un ticket pour les clients de la famille du client 572
 *
 * @author Thierry Baribaud
 * @version 0.51
 */
public class Ticket_0572 extends Ticket_0000 {

    /**
     * Date of first CriticalLevel definition change
     */
    private final Timestamp criticalLevelFirstChangeDate = Timestamp.valueOf("2019-06-20 12:00:00.0");

    /**
     * Indication de d�g�ts des eaux OUI/NON..
     */
    private String DegatsDesEaux;

    /**
     * Indication de site trouv� en base de donn�es.
     */
    private String SiteEnBase;

    /**
     * Type de demande : DIC=Courante, DI=Imm�diate, Autre.
     */
    private String TypeDeDemande;

    /**
     * Enqu�te de satisfaction.
     */
    private Survey survey;

    /**
     * P�riode durant laquelle a �t� saisie la demande HO/HNO
     */
    private String period;

    /**
     * Degr� de criticit� de la demande
     */
    private String criticalLevel;

    /**
     * Contructeur principal de la classe Ticket.
     *
     * @param connection connexion � la base de donn�es courante.
     * @param Fcalls_0000 appel,
     * @param Fcomplmt_0000 compl�ment d'appel.
     * @param etatTicket etat du ticket.
     * @throws java.lang.ClassNotFoundException en cas de classe non trouv�e.
     * @throws java.sql.SQLException en cas d'erreur SQL.
     */
    public Ticket_0572(Connection connection, Fcalls Fcalls_0000, Fcomplmt Fcomplmt_0000, EtatTicket etatTicket) throws ClassNotFoundException, SQLException {
        super(connection, Fcalls_0000, Fcomplmt_0000, etatTicket);

        Fessais fessais;
        FessaisDAO fessaisDAO;
        int tnum;
        Ftoubib ftoubib;
        FtoubibDAO ftoubibDAO;
        boolean loop;
        int n;
        int delay;
        int enumabs1 = 0;
        int a4num;
        String a4name;
        Factivity factivity;
        FactivityDAO factivityDAO;
        StringBuffer etatIntervention;

        // Degr� de criticit� de la demande cf. tra_nat_urg_xxxx() dans libutilxxx.4gl
//        System.out.println("  cdelay1="+Fcalls_0000.getCdelay1());
        if (this.Fcalls_0000.getCdate().after(criticalLevelFirstChangeDate)) {
            switch (this.Fcalls_0000.getCdelay1()) {
                case 1:
                    setCriticalLevel("Non urgente");
                    break;
                case 2:
                    setCriticalLevel("Urgente");
                    break;
                case 3:
                    setCriticalLevel("Strat�gique");
                    break;
                case 4:
                    setCriticalLevel("Prioritaire");
                    break;
                default:
                    setCriticalLevel("Non pr�cis�");
                    break;
            }
        } else {
            switch (this.Fcalls_0000.getCdelay1()) {
                case 1:
                    setCriticalLevel("Urgente");
                    break;
                case 2:
                    setCriticalLevel("Non urgente");
                    break;
                default:
                    setCriticalLevel("Non pr�cis�");
                    break;
            }
        }

        // Indication de d�g�ts des eaux cf. tra_degat_eau_0572() dans libspc0572.4gl.
        if ("1".equals(this.Fcalls_0000.getCsector1())) {
            setDegatsDesEaux("OUI");
        } else {
            setDegatsDesEaux("NON");
        }

        // Indication de site trouv� en base de donn�es cf. librep0572.4gl en dur.
        if ("SIEGE".equals(getA6abbname())) {
            setSiteEnBase("NON");
        } else {
            setSiteEnBase("OUI");
        }

        // Indication du type de demande cf. tra_typinter_xxxx() dans libutilxxx.4gl.
//        System.out.println("  c6int2="+Fcomplmt_0000.getC6int2());
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

        // Pour la famille du client 572, on g�re les status Intervention/Message autrement
        this.setEtatIntervention("Message");

        // Recherche la premi�re mise en sommeil sur un ticket de type message
//        System.out.println("  R�cup�ration de la premi�re mise en sommeil");
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

        // Recherche la premi�re transmission
//        System.out.println("  R�cup�ration de la premi�re transmission");
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

        // Recherche la derni�re transmission
        //System.out.println("  R�cup�ration de la derni�re transmission");
        // ATTENTION : Incorporer enumabs1 dans la requ�te ult�rieurement
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
                etatIntervention.append(" annul�");
            } else {
                etatIntervention.append(" annul�e");
            }
            setEtatIntervention(etatIntervention.toString());
        }
        setSurvey(new Survey(connection, this.Fcalls_0000.getCnum(), this.Fcomplmt_0000.getC6int4(), etatTicket));

        setPeriod(this.Fcalls_0000.getCage());
    }

    /**
     * Contructeur secondaire de la classe Ticket.
     *
     * @param MyConnection connexion � la base de donn�es courante.
     * @param Fcalls_0000 appel,
     * @param MyEtatTicket etat du ticket.
     * @throws java.lang.ClassNotFoundException en cas de classe non trouv�e.
     * @throws java.sql.SQLException en cas d'erreur SQL.
     */
    public Ticket_0572(Connection MyConnection, Fcalls Fcalls_0000, EtatTicket MyEtatTicket) throws ClassNotFoundException, SQLException {
        this(MyConnection, Fcalls_0000, null, MyEtatTicket);
    }

    /**
     * @return DegatsDesEaux indication de d�g�ts des eaux OUI/NON.
     */
    public String getDegatsDesEaux() {
        return DegatsDesEaux;
    }

    /**
     * @param DegatsDesEaux d�finit l'indication de d�g�ts des eaux OUI/NON.
     */
    public final void setDegatsDesEaux(String DegatsDesEaux) {
        this.DegatsDesEaux = DegatsDesEaux;
    }

    /**
     * @return SiteEnBase Indication de site trouv� en base de donn�es.
     */
    public String getSiteEnBase() {
        return SiteEnBase;
    }

    /**
     * @param SiteEnBase d�finit l'indication de site trouv� en base de donn�es.
     */
    public final void setSiteEnBase(String SiteEnBase) {
        this.SiteEnBase = SiteEnBase;
    }

    /**
     * @return TypeDeDemande le type de demande.
     */
    public String getTypeDeDemande() {
        return TypeDeDemande;
    }

    /**
     * @param TypeDeDemande d�finit le type de demande.
     */
    public final void setTypeDeDemande(String TypeDeDemande) {
        this.TypeDeDemande = TypeDeDemande;
    }

    /**
     * @return l'enqu�te de satisfaction
     */
    public Survey getSurvey() {
        return survey;
    }

    /**
     * @param survey d�finit l'enqu�te de satisfaction
     */
    public final void setSurvey(Survey survey) {
        this.survey = survey;
    }

    /**
     * @return la p�riode � laquelle a �t� saisie la demande
     */
    public String getPeriod() {
        return period;
    }

    /**
     * @param period d�finit la p�riode � laquelle a �t� saisie la demande
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * @param period d�finit la p�riode � laquelle a �t� saisie la demande
     */
    public final void setPeriod(int period) {

        switch (period) {
            case 0:
                this.period = "HNO";
                break;
            case 1:
                this.period = "HO";
                break;
            default:
                this.period = "NA";
                break;
        }
    }

    /**
     * @return retourne le degr� de criticit� de la demande
     */
    public String getCriticalLevel() {
        return criticalLevel;
    }

    /**
     * @param criticalLevel d�finit le degr� de criticit� de la demande
     */
    public final void setCriticalLevel(String criticalLevel) {
        this.criticalLevel = criticalLevel;
    }
}
