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
 * Classe représentant un ticket pour les clients de la famille du client 513
 *
 * @version Juillet 2016
 * @author Thierry Baribaud
 */
public class Ticket_0513 extends Ticket_0000 {

    /**
     * Degré d'urgence de la demande d'intervention.
     */
    private String OTUrgente;

    /**
     * Origine de la demande de l'appel.
     */
    private String OrigineDemande;

    /**
     * Heure réception du mail.
     */
    private String HeureMail;

    /**
     * Numéro de poste de l'opérateur.
     */
    private int logOperateur;

    /**
     * Temps de traitement du mail exprimé en minutes.
     */
    private int tempsTraitement;

    /**
     * Qualification de la demande.
     */
    private String Qualification;

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
    public Ticket_0513(Connection MyConnection, Fcalls Fcalls_0000, Fcomplmt Fcomplmt_0000, EtatTicket MyEtatTicket) throws ClassNotFoundException, SQLException {

        super(MyConnection, Fcalls_0000, Fcomplmt_0000, MyEtatTicket);

        int codeOrigine;

        // Degré d'urgence cf. tra_urgence() dans libutil.4gl.
//        System.out.println("  cquery2="+Fcalls_0000.getCquery2());
        setOTUrgente(Fcalls_0000.getCquery2());

        // Origine de l'appel cf. tra_origine() dans libutil.4gl.
//        System.out.println("  cage="+Fcalls_0000.getCage());
        codeOrigine = Fcalls_0000.getCage();
        setOrigineDemande(codeOrigine);

        // Cas particulier pour une demande d'intervention par mail.
        if (codeOrigine == 2) {
            // Heure de réception du mail
            setHeureMail(Fcalls_0000.getCnumber4());

            // Temps de traitement du mail lorsque pertinent.
            setTempsTraitement(Fcalls_0000.getCnumber4(), Fcalls_0000.getCtime());
        }

        // Qualification de la demande.
        setQualification(Fcalls_0000.getCunum(), Fcalls_0000.getCtype());

        // Numéro de poste de l'opérateur.
        setLogOperateur(Fcalls_0000.getConum(), 0);
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
    public Ticket_0513(Connection MyConnection, Fcalls Fcalls_0000, EtatTicket MyEtatTicket) throws ClassNotFoundException, SQLException {
        this(MyConnection, Fcalls_0000, null, MyEtatTicket);
    }

    /**
     * @return OTUrgente le degré d'urgence de la demande d'intervention.
     */
    public String getOTUrgente() {
        return OTUrgente;
    }

    /**
     * @param OTUrgente définit le degré d'urgence de la demande d'intervention.
     */
    public void setOTUrgente(String OTUrgente) {
        this.OTUrgente = OTUrgente;
    }

    /**
     * @param codeUrgence définit le degré d'urgence de la demande
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
     * @param OrigineDemande définit l'origine de l'appel.
     */
    public void setOrigineDemande(String OrigineDemande) {
        this.OrigineDemande = OrigineDemande;
    }

    /**
     * @param codeOrigineDemande définit l'origne de l'appel à partir d'un code.
     */
    public void setOrigineDemande(int codeOrigineDemande) {
        setOrigineDemande(traOrigine(codeOrigineDemande));
    }

    /**
     * @return HeureMail l'heure de réception du mail.
     */
    public String getHeureMail() {
        return HeureMail;
    }

    /**
     * @param HeureMail définit l'heure de réception du mail.
     */
    public void setHeureMail(String HeureMail) {
        this.HeureMail = HeureMail;
    }

    /**
     * @return logOperateur le numéro de poste de l'opéateur.
     */
    public int getLogOperateur() {
        return logOperateur;
    }

    /**
     * @param logOperateur définit le numéro de poste de l'opéateur.
     */
    public void setLogOperateur(int logOperateur) {
        this.logOperateur = logOperateur;
    }

    /**
     * Définit le numéro de poste de l'opéateur à partir de l'identifiant unique
     * de l'opérateur.
     *
     * @param onum identifiant unique de l'opérateur.
     * @param inutile pour avoir une signature différente.
     * @throws java.sql.SQLException en ca d'erreur SQL.
     * @throws java.lang.ClassNotFoundException en cas de classe non trouvée.
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
     * @return tempsTraitement le temps de traitement du mail exprimé en
     * minutes.
     */
    public int getTempsTraitement() {
        return tempsTraitement;
    }

    /**
     * @param tempsTraitement définit le temps de traitement du mail exprimé en
     * minutes.
     */
    public void setTempsTraitement(int tempsTraitement) {
        this.tempsTraitement = tempsTraitement;
    }

    /**
     * Définit le temps de traitement du mail exprimé en minutes.
     *
     * @param BegTime heure d'arrivée du mail,
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
     * @param Qualification définit la qualification de la demande.
     */
    public void setQualification(String Qualification) {
        this.Qualification = Qualification;
    }

    /**
     * Définit la qualification de la demande à partir de x et y.
     *
     * @param unum identifiant unique du client via furgent.
     * @param ttype indice de la raison d'appel via ftype.
     * @throws java.sql.SQLException en ca d'erreur SQL.
     * @throws java.lang.ClassNotFoundException en cas de classe non trouvée.
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

}
