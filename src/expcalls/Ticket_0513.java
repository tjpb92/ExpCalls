package expcalls;

import bdd.EtatTicket;
import bdd.Fcalls;
import bdd.Fcomplmt;
import bdd.Foperat;
import bdd.FoperatDAO;
import bdd.Ftype;
import bdd.FtypeDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;

/**
 * Classe repr�sentant un ticket pour les clients de la famille du client 513
 *
 * @version 0.59
 * @author Thierry Baribaud
 */
public class Ticket_0513 extends Ticket_0000 {

    /**
     * Degr� d'urgence de la demande d'intervention.
     */
    private String OTUrgente;

    /**
     * Origine de la demande de l'appel.
     */
    private String OrigineDemande;

    /**
     * Heure r�ception du mail.
     */
    private String HeureMail;

    /**
     * Num�ro de poste de l'op�rateur.
     */
    private int logOperateur;

    /**
     * Temps de traitement du mail exprim� en minutes.
     */
    private int tempsTraitement;

    /**
     * Qualification de la demande.
     */
    private String Qualification;

    /**
     * Type d'OT
     */
    private String otType;

    /**
     * Contructeur principal de la classe Ticket.
     *
     * @param MyConnection connexion � la base de donn�es courante.
     * @param Fcalls_0000 appel,
     * @param Fcomplmt_0000 compl�ment d'appel.
     * @param MyEtatTicket etat du ticket.
     * @throws java.lang.ClassNotFoundException en cas de classe non trouv�e.
     * @throws java.sql.SQLException en cas d'erreur SQL.
     */
    public Ticket_0513(Connection MyConnection, Fcalls Fcalls_0000, Fcomplmt Fcomplmt_0000, EtatTicket MyEtatTicket) throws ClassNotFoundException, SQLException {

        super(MyConnection, Fcalls_0000, Fcomplmt_0000, MyEtatTicket);

        int codeOrigine;

        // Degr� d'urgence cf. tra_urgence() dans libutil.4gl.
//        System.out.println("  cquery2="+Fcalls_0000.getCquery2());
        setOTUrgente(Fcalls_0000.getCquery2());

        // Origine de l'appel cf. tra_origine() dans libutil.4gl.
//        System.out.println("  cage="+Fcalls_0000.getCage());
        codeOrigine = Fcalls_0000.getCage();
        setOrigineDemande(codeOrigine);

        // Cas particulier pour une demande d'intervention par mail.
        if (codeOrigine == 2) {
            // Heure de r�ception du mail
            setHeureMail(Fcalls_0000.getCnumber4());

            // Temps de traitement du mail lorsque pertinent.
            setTempsTraitement(Fcalls_0000.getCnumber4(), Fcalls_0000.getCtime());
        }

        // Qualification de la demande.
        setQualification(Fcalls_0000.getCunum(), Fcalls_0000.getCtype());

        // Num�ro de poste de l'op�rateur.
        setLogOperateur(Fcalls_0000.getConum(), 0);
        
        // Type d'OT
        setOtType(this.Fcomplmt_0000.getC6int3());
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
    public Ticket_0513(Connection MyConnection, Fcalls Fcalls_0000, EtatTicket MyEtatTicket) throws ClassNotFoundException, SQLException {
        this(MyConnection, Fcalls_0000, null, MyEtatTicket);
    }

    /**
     * @return OTUrgente le degr� d'urgence de la demande d'intervention.
     */
    public String getOTUrgente() {
        return OTUrgente;
    }

    /**
     * @param OTUrgente d�finit le degr� d'urgence de la demande d'intervention.
     */
    public void setOTUrgente(String OTUrgente) {
        this.OTUrgente = OTUrgente;
    }

    /**
     * @param codeUrgence d�finit le degr� d'urgence de la demande
     * d'intervention.
     */
    public void setOTUrgente(int codeUrgence) {
        setOTUrgente(traUrgence(codeUrgence));
    }

    /**
     * @return OrigineDemande indication sur l'origine de l'appel.
     */
    public String getOrigineDemande() {
        return OrigineDemande;
    }

    /**
     * @param OrigineDemande d�finit l'origine de l'appel.
     */
    public void setOrigineDemande(String OrigineDemande) {
        this.OrigineDemande = OrigineDemande;
    }

    /**
     * @param codeOrigineDemande d�finit l'origne de l'appel � partir d'un code.
     */
    public void setOrigineDemande(int codeOrigineDemande) {
        setOrigineDemande(traOrigine(codeOrigineDemande));
    }

    /**
     * @return HeureMail l'heure de r�ception du mail.
     */
    public String getHeureMail() {
        return HeureMail;
    }

    /**
     * @param HeureMail d�finit l'heure de r�ception du mail.
     */
    public void setHeureMail(String HeureMail) {
        this.HeureMail = HeureMail;
    }

    /**
     * @return logOperateur le num�ro de poste de l'op�ateur.
     */
    public int getLogOperateur() {
        return logOperateur;
    }

    /**
     * @param logOperateur d�finit le num�ro de poste de l'op�ateur.
     */
    public void setLogOperateur(int logOperateur) {
        this.logOperateur = logOperateur;
    }

    /**
     * D�finit le num�ro de poste de l'op�ateur � partir de l'identifiant unique
     * de l'op�rateur.
     *
     * @param onum identifiant unique de l'op�rateur.
     * @param inutile pour avoir une signature diff�rente.
     * @throws java.sql.SQLException en ca d'erreur SQL.
     * @throws java.lang.ClassNotFoundException en cas de classe non trouv�e.
     */
    public void setLogOperateur(int onum, int inutile) throws ClassNotFoundException, SQLException {
        FoperatDAO MyFoperatDAO;
        Foperat MyFoperat;

        MyFoperatDAO = new FoperatDAO(getConnection());
        MyFoperatDAO.filterById(onum);
        MyFoperatDAO.setSelectPreparedStatement();
        MyFoperat = MyFoperatDAO.select();
        if (MyFoperat != null) {
            setLogOperateur(MyFoperat.getOnumpabx() + 2000);
        }
        MyFoperatDAO.closeSelectPreparedStatement();
    }

    /**
     * @return tempsTraitement le temps de traitement du mail exprim� en
     * minutes.
     */
    public int getTempsTraitement() {
        return tempsTraitement;
    }

    /**
     * @param tempsTraitement d�finit le temps de traitement du mail exprim� en
     * minutes.
     */
    public void setTempsTraitement(int tempsTraitement) {
        this.tempsTraitement = tempsTraitement;
    }

    /**
     * D�finit le temps de traitement du mail exprim� en minutes.
     *
     * @param BegTime heure d'arriv�e du mail,
     * @param EndTime heure de saisie de l'appel,
     */
    public void setTempsTraitement(String BegTime, String EndTime) {
        int delai;

        delai = 0;

        if (BegTime.matches("[0-2][0-9]:[0-5][0-9]:[0-5][0-9]")) {
            delai = (int) Math.abs(Time.valueOf(BegTime).getTime()
                    - Time.valueOf(EndTime).getTime()) / 60000;
        }
        setTempsTraitement(delai);
    }

    /**
     * @return Qualification indication sur la qualification de la demande.
     */
    public String getQualification() {
        return Qualification;
    }

    /**
     * @param Qualification d�finit la qualification de la demande.
     */
    public void setQualification(String Qualification) {
        this.Qualification = Qualification;
    }

    /**
     * @return otType type d'OT
     */
    public String getOtType() {
        return otType;
    }

    /**
     * @param otType d�finit le type d'OT.
     */
    public void setOtType(String otType) {
        this.otType = otType;
    }

    /**
     * @param otTypeCode d�finit le type d'OT.
     */
    public void setOtType(int otTypeCode) {
        this.otType = tra_type_ot(otTypeCode);
    }

    /**
     * D�finit la qualification de la demande � partir de x et y.
     *
     * @param unum identifiant unique du client via furgent.
     * @param ttype indice de la raison d'appel via ftype.
     * @throws java.sql.SQLException en ca d'erreur SQL.
     * @throws java.lang.ClassNotFoundException en cas de classe non trouv�e.
     */
    public void setQualification(int unum, int ttype) throws SQLException, ClassNotFoundException {
        FtypeDAO MyFtypeDAO;
        Ftype MyFtype;

        MyFtypeDAO = new FtypeDAO(getConnection());
        MyFtypeDAO.filterByCode(Fcalls_0000.getCunum(), Fcalls_0000.getCtype());
        MyFtypeDAO.setSelectPreparedStatement();
        MyFtype = MyFtypeDAO.select();
        if (MyFtype != null) {
            setQualification(MyFtype.getTtypename());
        }
        MyFtypeDAO.closeSelectPreparedStatement();
    }

    /**
     * Fonction servant � convertir le code du type OT en libell�
     *
     * @param otTypeCode code du type d'OT (c6int3)
     * @return le libell� du type d'OT.
     */
    private String tra_type_ot(int otTypeCode) {
        String retString;

//      Indication du type d'OT cf. tra_type_ot() dans libutilxxx.4gl.
        switch (otTypeCode) {
            case 2:
                retString = "Pr�ventif";
                break;
            case 3:
                retString = "R�glementaire";
                break;
            default:
                retString = "Curative";
                break;
        }

        return retString;
    }
}
