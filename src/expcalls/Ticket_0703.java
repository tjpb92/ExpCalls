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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe repr�sentant un ticket pour les clients de la famille du client 703
 *
 * @author Thierry Baribaud
 * @version 0.57
 */
public class Ticket_0703 extends Ticket_0000 {

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
     * Nature de l'appelant
     */
    private String natureOfTheCaller;

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
    public Ticket_0703(Connection connection, Fcalls Fcalls_0000, Fcomplmt Fcomplmt_0000, EtatTicket etatTicket) throws ClassNotFoundException, SQLException {
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
        int unum;
        int cquery1;

        // Nature de l'appelant cf. tra_nat_caller_xxxx() de libcontact.4gl et
        // w2l_screen1() de libutilxxx.4gl
        setNatureOfTheCaller(tra_nat_caller_0572(w2l_screen1(this.Fcomplmt_0000.getC6alpha10()), this.Fcalls_0000.getCunum()));

        // Degr� de criticit� de la demande cf. tra_nat_urg_xxxx() dans libutilxxx.4gl
//        System.out.println("  cdelay1="+Fcalls_0000.getCdelay1());
        setCriticalLevel(tra_nat_urgence(w2l_screen3(this.Fcomplmt_0000.getC6alpha10())));

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
        // Par d�faut, le suivi donn� � la demande est "Message"
        this.setEtatIntervention("Message");

        // Evolution au 19/11/2019 : Ajout de la mention "Demande administrative" pour les clients 572 et 634
        unum = this.Fcalls_0000.getCunum();
        cquery1 = this.Fcalls_0000.getCquery1();

        // for debug only ...
//        this.Fcalls_0000.setCname(this.Fcalls_0000.getCname() + " - " + cquery1 + " - ");
        // TODO : A enleverplus tard
        if (unum == 572 && (cquery1 == 17191 || cquery1 == 24041 || cquery1 == 24043)) {
            this.setEtatIntervention("Demande administrative");
        } else if (unum == 634 && (cquery1 == 20975)) {
            this.setEtatIntervention("Demande administrative");
        }

        // Recherche la premi�re mise en sommeil
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

                            // for debug only ...
//                            this.Fcalls_0000.setCname(this.Fcalls_0000.getCname() + "A");
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

                            // for debug only ...
//                            this.Fcalls_0000.setCname(this.Fcalls_0000.getCname() + "B");
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

                                // for debug only ...
//                                this.Fcalls_0000.setCname(this.Fcalls_0000.getCname() + "C");
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
        }

        // Changement vers intervention pour certains cas particuliers
        // On ne tient pas compte de l'intervenant r�cup�r� pour l'instant, TB, le 19/11/2019.
        // Cas Appel sortant sur ..., essais #40
        if (this.getEtatIntervention().equals("Message")) {
            fessaisDAO = new FessaisDAO(super.getConnection(), super.getMyEtatTicket());
            fessaisDAO.setLastTrialPreparedStatement(this.Fcalls_0000.getCnum(), 40);
            fessais = fessaisDAO.getLastTrial();
            fessaisDAO.closeLastTrialPreparedStatement();
            if (fessais != null) {
                this.setEtatIntervention("Intervention");

                // for debug only ...
//                this.Fcalls_0000.setCname(this.Fcalls_0000.getCname() + "L");
            }
        }

        // Cas R�ponse du ..., essais #76
        if (this.getEtatIntervention().equals("Message")) {
            fessaisDAO = new FessaisDAO(super.getConnection(), super.getMyEtatTicket());
            fessaisDAO.setLastTrialPreparedStatement(this.Fcalls_0000.getCnum(), 76);
            fessais = fessaisDAO.getLastTrial();
            fessaisDAO.closeLastTrialPreparedStatement();
            if (fessais != null) {
                this.setEtatIntervention("Intervention");

                // for debug only ...
//                this.Fcalls_0000.setCname(this.Fcalls_0000.getCname() + "M");
            }
        }

        // Cas Prise en charge, essais #77
        if (this.getEtatIntervention().equals("Message")) {
            fessaisDAO = new FessaisDAO(super.getConnection(), super.getMyEtatTicket());
            fessaisDAO.setLastTrialPreparedStatement(this.Fcalls_0000.getCnum(), 77);
            fessais = fessaisDAO.getLastTrial();
            fessaisDAO.closeLastTrialPreparedStatement();
            if (fessais != null) {
                this.setEtatIntervention("Intervention");

                // for debug only ...
//                this.Fcalls_0000.setCname(this.Fcalls_0000.getCname() + "N");
            }
        }

        // Cas DIC envoy� par mail, essais #87
        if (this.getEtatIntervention().equals("Message")) {
            fessaisDAO = new FessaisDAO(super.getConnection(), super.getMyEtatTicket());
            fessaisDAO.setLastTrialPreparedStatement(this.Fcalls_0000.getCnum(), 87);
            fessais = fessaisDAO.getLastTrial();
            fessaisDAO.closeLastTrialPreparedStatement();
            if (fessais != null) {
                this.setEtatIntervention("Intervention");

                // for debug only ...
//                this.Fcalls_0000.setCname(this.Fcalls_0000.getCname() + "J");
            }
        }

        // Cas DI envoy� par mail, essais #88
        if (this.getEtatIntervention().equals("Message")) {
            fessaisDAO = new FessaisDAO(super.getConnection(), super.getMyEtatTicket());
            fessaisDAO.setLastTrialPreparedStatement(this.Fcalls_0000.getCnum(), 88);
            fessais = fessaisDAO.getLastTrial();
            fessaisDAO.closeLastTrialPreparedStatement();
            if (fessais != null) {
                this.setEtatIntervention("Intervention");

                // for debug only ...
//                this.Fcalls_0000.setCname(this.Fcalls_0000.getCname() + "K");
            }
        }

        // Cas d'un envoi GMAO, essais #100
        if (this.getEtatIntervention().equals("Message")) {
            fessaisDAO = new FessaisDAO(super.getConnection(), super.getMyEtatTicket());
            fessaisDAO.setLastTrialPreparedStatement(this.Fcalls_0000.getCnum(), 100);
            fessais = fessaisDAO.getLastTrial();
            fessaisDAO.closeLastTrialPreparedStatement();
            if (fessais != null) {
                this.setEtatIntervention("Intervention");

                // for debug only ...
//                this.Fcalls_0000.setCname(this.Fcalls_0000.getCname() + "D");
            }
        }

        // Cas d'une demande de devis, essais #400
        if (this.getEtatIntervention().equals("Message")) {
            fessaisDAO = new FessaisDAO(super.getConnection(), super.getMyEtatTicket());
            fessaisDAO.setLastTrialPreparedStatement(this.Fcalls_0000.getCnum(), 400);
            fessais = fessaisDAO.getLastTrial();
            fessaisDAO.closeLastTrialPreparedStatement();
            if (fessais != null) {
                this.setEtatIntervention("Intervention");

                // for debug only ...
//                this.Fcalls_0000.setCname(this.Fcalls_0000.getCname() + "E");
            }
        }

        // Cas d'une attente de validation de devis, essais #401
        if (this.getEtatIntervention().equals("Message")) {
            fessaisDAO = new FessaisDAO(super.getConnection(), super.getMyEtatTicket());
            fessaisDAO.setLastTrialPreparedStatement(this.Fcalls_0000.getCnum(), 401);
            fessais = fessaisDAO.getLastTrial();
            fessaisDAO.closeLastTrialPreparedStatement();
            if (fessais != null) {
                this.setEtatIntervention("Intervention");

                // for debug only ...
//                this.Fcalls_0000.setCname(this.Fcalls_0000.getCname() + "F");
            }
        }

        // Cas en attente de cloture prestataire, essais #405
        if (this.getEtatIntervention().equals("Message")) {
            fessaisDAO = new FessaisDAO(super.getConnection(), super.getMyEtatTicket());
            fessaisDAO.setLastTrialPreparedStatement(this.Fcalls_0000.getCnum(), 405);
            fessais = fessaisDAO.getLastTrial();
            fessaisDAO.closeLastTrialPreparedStatement();
            if (fessais != null) {
                this.setEtatIntervention("Intervention");

                // for debug only ...
//                this.Fcalls_0000.setCname(this.Fcalls_0000.getCname() + "G");
            }
        }

        // Cas en attente retour client, essais #406
        if (this.getEtatIntervention().equals("Message")) {
            fessaisDAO = new FessaisDAO(super.getConnection(), super.getMyEtatTicket());
            fessaisDAO.setLastTrialPreparedStatement(this.Fcalls_0000.getCnum(), 406);
            fessais = fessaisDAO.getLastTrial();
            fessaisDAO.closeLastTrialPreparedStatement();
            if (fessais != null) {
                this.setEtatIntervention("Intervention");

                // for debug only ...
//                this.Fcalls_0000.setCname(this.Fcalls_0000.getCname() + "H");
            }
        }

        // Cas envoi GMAO, essais #407
        if (this.getEtatIntervention().equals("Message")) {
            fessaisDAO = new FessaisDAO(super.getConnection(), super.getMyEtatTicket());
            fessaisDAO.setLastTrialPreparedStatement(this.Fcalls_0000.getCnum(), 407);
            fessais = fessaisDAO.getLastTrial();
            fessaisDAO.closeLastTrialPreparedStatement();
            if (fessais != null) {
                this.setEtatIntervention("Intervention");

                // for debug only ...
//                this.Fcalls_0000.setCname(this.Fcalls_0000.getCname() + "I");
            }
        }

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
    public Ticket_0703(Connection MyConnection, Fcalls Fcalls_0000, EtatTicket MyEtatTicket) throws ClassNotFoundException, SQLException {
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

    /**
     * @return retourne la nature de l'appelant
     */
    public String getNatureOfTheCaller() {
        return natureOfTheCaller;
    }

    /**
     * @param natureOfTheCaller d�finit la nature de l'appelant
     */
    public final void setNatureOfTheCaller(String natureOfTheCaller) {
        this.natureOfTheCaller = natureOfTheCaller;
    }

    /**
     * V�rifie si le ticket est annul� ou non
     *
     * @return retourne l'�tat du ticket : annul� ou non.
     */
    @Override
    public boolean isTicketCanceled() {
        boolean isTicketCanceled;
        FessaisDAO fessaisDAO;
        Fessais fessais;

        isTicketCanceled = super.isTicketCanceled();
        fessais = null;

        try {
            fessaisDAO = new FessaisDAO(super.getConnection(), super.getMyEtatTicket());
            fessaisDAO.setLastTrialPreparedStatement(this.Fcalls_0000.getCnum(), 404);
            fessais = fessaisDAO.getLastTrial();
            fessaisDAO.closeLastTrialPreparedStatement();
        } catch (ClassNotFoundException | SQLException exception) {
            Logger.getLogger(Ticket_0703.class.getName()).log(Level.WARNING, null, exception);
        }

        isTicketCanceled = isTicketCanceled || (fessais != null);

        return isTicketCanceled;
    }

    /**
     * Traduction du degr� d'urgence bas�e sur tra_nat_urgence() de
     * libutilxxx.4gl
     *
     * @param code code d�signant le degr� d'urgence
     * @return le libell� correspondant
     */
    private String tra_nat_urgence(int code) {
        String label = "NA";

        switch (code) {
            case 1:
                label = "Urgente";
                break;
            case 2:
                label = "Non Urgente";
                break;
            case 3:
                label = "Crise av�r�e";
                break;
            case 4:
                label = "Ne sais pas";
                break;
        }

        return label;
    }

    /**
     * D�codage de la nature de l'appelant pour le client 703 bas�e sur
     * w2l_screen1() de libtuilxxx.4gl La nature de l'appelant est stock�e dans
     * les deux premiers caract�res.
     *
     * @param c6alpha10 chaine de caract�res contenant l'encodage de la nature
     * de l'appelant
     * @return le codage de la nature de l'appelant
     */
    private int w2l_screen1(String c6alpha10) {

        int code = 0;

        try {
            code = Math.max(Character.getNumericValue(c6alpha10.charAt(0)), 0);
            code = code * 10 + Math.max(Character.getNumericValue(c6alpha10.charAt(1)), 0);
//            System.out.println("  c6alpha10:" + c6alpha10 + ", code:" + code);
        } catch (Exception exception) {
            System.out.println("WARNING : index out of bound in c6alpha10 in w2l_screen1()");
        }

        return code;
    }

    /**
     * D�codage du degr� d'urgence pour le client 703 bas�e sur w2l_screen3() de
     * libtuilxxx.4gl Le degr� d'urgence est stock� dans le 25i�me caract�re.
     *
     * @param c6alpha10 chaine de caract�res contenant l'encodage du degr�
     * d'urgence
     * @return le codage du degr� d'urgence
     */
    private int w2l_screen3(String c6alpha10) {

        int code = 0;

        try {
            code = Math.max(Character.getNumericValue(c6alpha10.charAt(24)), 0);
//            System.out.println("  c6alpha10:" + c6alpha10 + ", code:" + code);
        } catch (Exception exception) {
            System.out.println("WARNING : index out of bound in c6alpha10 in w2l_screen3()");
        }

        return code;
    }

    /**
     * Traduction de la nature de l'appelant bas�e sur tra_nat_caller_xxxx() de
     * libcontact.4gl
     *
     * @param code code d�signant la nature de l'appelant
     * @return le libell� correspondant
     */
    private String tra_nat_caller_0572(int code, int unum) {
        String label = "NA";

        if (unum == 703) {
            switch (code) {
                case 1:
                    label = "Responsable site";
                    break;
                case 2:
                    label = "Gardien";
                    break;
                case 3:
                    label = "PC Securite";
                    break;
                case 4:
                    label = "Gestionnaire";
                    break;
                case 5:
                    label = "Manager";
                    break;
                case 6:
                    label = "Inspecteur Technique";
                    break;
                case 7:
                    label = "Responsable technique";
                    break;
                case 8:
                    label = "Telesurveillance";
                    break;
                case 9:
                    label = "Cadre astreinte";
                    break;
                case 10:
                    label = "Locataire";
                    break;
                case 11:
                    label = "Proprietaire";
                    break;
                case 12:
                    label = "Syndic";
                    break;
                case 13:
                    label = "Promoteur";
                    break;
                case 14:
                    label = "Accueil";
                    break;
                case 15:
                    label = "Autre";
                    break;
            }
        }

        return label;
    }
}
