package expcalls;

import bdd.ClotureAppel;
import bdd.EtatTicket;
import bdd.Fagency;
import bdd.FagencyDAO;
import bdd.Fcalls;
import bdd.Fcomplmt;
import bdd.FcomplmtDAO;
import bdd.Fessais;
import bdd.FessaisDAO;
import bdd.Fmenuit;
import bdd.FmenuitDAO;
import bdd.Ftoubib;
import bdd.FtoubibDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Classe représentant un ticket. Cela correspond à l'association d'un appel
 * Fcalls et d'un complément d'appel Fcomplmt s'il existe. Il s'agit du ticket 
 * basique. Les tickets spécifiques à un client dériveront de celui-ci.
 *
 * @version Juin 2016
 * @author Thierry Baribaud
 */
public class Ticket_0000 {

    /**
     * Connection à la base de données courante.
     */
    private Connection MyConnection;
    
    /**
     * Etat du ticket.
     */
    private EtatTicket MyEtatTicket;
    
    /**
     * Partie composée de l'appel.
     */
    public Fcalls Fcalls_0000;

    /**
     * Partie composée du complément d'appel.
     */
    public Fcomplmt Fcomplmt_0000;

    /**
     * Format de date "dd/mm/aaaa".
     */
    private static final DateFormat MyDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Nom de l'agence.
     */
    private String A6name = "#N/A";

    /**
     * Item de menu sélectionné.
     */
    private String M6name = "#N/A";

    /**
     * Etat de l'intervention.
     */
    private String EtatIntervention = "#N/A";

    /**
     * Prestataire1 : prestataire sur la première transmission.
     */
    private String Prestataire1;
    /**
     * DateMissionnement1 : date de la première transmission.
     */
    private String DateMissionnement1;
    /**
     * HeureMissionnement1 : heure de la première transmission.
     */
    private String HeureMissionnement1;
    /**
     * NoTelephone1 : Numéro de téléphone du prestataire.
     */
    private String NoTelephone1;
    /**
     * Prestataire1 : prestataire sur la dernière transmission.
     */
    private String Prestataire2;
    /**
     * DateMissionnement1 : date de la dernière transmission.
     */
    private String DateMissionnement2;
    /**
     * HeureMissionnement1 : heure de la dernière transmission.
     */
    private String HeureMissionnement2;
    /**
     * NoTelephone1 : Numéro de téléphone du prestataire.
     */
    private String NoTelephone2;
    /**
     * Rapport d'intervention
     */
    private String RapportIntervention;
    /**
     * Le technicien est-il encore sur site ?
     */
    private String TechnicienSurSite;

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
    public Ticket_0000(Connection MyConnection, Fcalls Fcalls_0000, 
            Fcomplmt Fcomplmt_0000, EtatTicket MyEtatTicket) 
            throws ClassNotFoundException, SQLException {

        int cc6num;
        Fcomplmt MyFcomplmt;
        FcomplmtDAO MyFcomplmtDAO;
        int a6num;
        String MyA6extname;
        String MyA6name;
        int m6num;
        String MyM6name;
        String MyM6extname;
        Fagency MyFagency;
        FagencyDAO MyFagencyDAO;
        Fmenuit MyFmenuit;
        FmenuitDAO MyFmenuitDAO;
        int enumabs1 = 0;
        Fessais MyFessais;
        FessaisDAO MyFessaisDAO;
        int tnum = 0;
        Ftoubib MyFtoubib;
        FtoubibDAO MyFtoubibDAO;
        int egid = 0;
        ClotureAppel MyClotureAppel;
        int eresult = 0;
        StringBuffer RapportIntervention = null;
        String Emessage = null;
        
        this.MyConnection = MyConnection;
        this.Fcalls_0000 = Fcalls_0000;
        this.Fcomplmt_0000 = Fcomplmt_0000;
        this.MyEtatTicket = MyEtatTicket;
        
        // Récupération du complément d'appel
//        System.out.println("  Récupération du complément d'appel");
        cc6num = this.Fcalls_0000.getCc6num();
        MyFcomplmt = null;
        if (cc6num > 0) {
            MyFcomplmtDAO = new FcomplmtDAO(MyConnection, cc6num);
            MyFcomplmt = MyFcomplmtDAO.select();
            this.setFcomplmt_0000(MyFcomplmt);
            MyFcomplmtDAO.close();
        }

        // Récupération de l'agence
//        System.out.println("  Récupération de l'agence");
        a6num = this.Fcalls_0000.getCzone();
        MyFagency = null;
        MyA6name = null;
        if (a6num > 0) {
            MyFagencyDAO = new FagencyDAO(MyConnection, a6num);
            MyFagency = MyFagencyDAO.select();
            if (MyFagency != null) {
                MyA6extname = MyFagency.getA6extname();
                MyA6name = (MyA6extname != null) ? MyA6extname : MyFagency.getA6name();
            }
            MyFagencyDAO.close();
        }
        if (MyA6name != null) {
            this.setA6name(MyA6name);
        }

        // Récupération de l'item du menu sélectionné
//        System.out.println("  Récupération de l'item du menu sélectionné");
        m6num = this.Fcalls_0000.getCquery1();
        MyM6name = null;
        MyM6extname = null;
        MyFmenuit = null;
        if (m6num > 0) {
            MyFmenuitDAO = new FmenuitDAO(MyConnection, m6num);
            MyFmenuit = MyFmenuitDAO.select();
            MyM6extname = MyFmenuit.getM6extname();
            MyM6name = (MyM6extname != null) ? MyM6extname : MyFmenuit.getM6name();
            MyFmenuitDAO.close();
        }
        if (MyM6name != null) {
            this.setM6name(MyM6name);
        }

        // Recherche la première transmission
//        System.out.println("  Récupération de la première transmission");
        MyFessaisDAO = new FessaisDAO(MyConnection, 0, this.Fcalls_0000.getCnum(), 0, MyEtatTicket);
        MyFessais = MyFessaisDAO.getFirstTransmission();
        if (MyFessais != null) {
            enumabs1 = MyFessais.getEnumabs();
            this.setEtatIntervention("Intervention");
            this.setDateMissionnement1(MyDateFormat.format(MyFessais.getEdate()));
            this.setHeureMissionnement1(MyFessais.getEtime());
            tnum = MyFessais.getEtnum();
            if (tnum > 0) {
                MyFtoubibDAO = new FtoubibDAO(MyConnection, tnum, 0);
                MyFtoubib = MyFtoubibDAO.select();
                if (MyFtoubib != null) {
                    this.setPrestataire1(MyFtoubib.getTlname() + " " + MyFtoubib.getTfname());
                    this.setNoTelephone1(MyFtoubib.getTel());
                }
            }
        } else {
            this.setEtatIntervention("Message");
        }
        
        // Recherche la dernière transmission
//        System.out.println("  Récupération de la dernière transmission");
        MyFessais = MyFessaisDAO.getLastTransmission();
        if (MyFessais != null) {
            if (enumabs1 != MyFessais.getEnumabs()) {
                this.setEtatIntervention("Intervention");
                this.setDateMissionnement2(MyDateFormat.format(MyFessais.getEdate()));
                this.setHeureMissionnement2(MyFessais.getEtime());
                tnum = MyFessais.getEtnum();
                if (tnum > 0) {
                    MyFtoubibDAO = new FtoubibDAO(MyConnection, tnum, 0);
                    MyFtoubib = MyFtoubibDAO.select();
                    if (MyFtoubib != null) {
                        this.setPrestataire2(MyFtoubib.getTlname() + " " + MyFtoubib.getTfname());
                        this.setNoTelephone2(MyFtoubib.getTel());
                    }
                }
            }
        }

        // Recherche la clôture d'appel
//        System.out.println("  Récupération de la clôture d'appel");
        MyFessais = MyFessaisDAO.getPartOfEOM();
        if (MyFessais != null) {
            egid = MyFessais.getEgid();
//            System.out.println("    Une clôture d'appel trouvée : egid=" + egid);
            MyClotureAppel = new ClotureAppel();
            RapportIntervention = new StringBuffer();
            MyFessaisDAO = new FessaisDAO(MyConnection, 0, this.Fcalls_0000.getCnum(), egid, MyEtatTicket);
            while ((MyFessais = MyFessaisDAO.select()) != null) {
                eresult = MyFessais.getEresult();
//                System.out.println("      eresult=" + eresult + ", emessage=" + MyFessais.getEmessage());
                switch (eresult) {
                    case 69:    // Heure de début d'intervention.
                        break;
                    case 70:    // Heure de fin d'intervention.
                        break;
                    case 71:    // Résultat de l'intervention.
                        MyClotureAppel.setResultat(MyFessais.getEmessage());
                        break;
                    case 72:    // Rapport d'intervention.
                        Emessage = MyFessais.getEmessage();
                        if (Emessage.length() > 0) {
                            if (RapportIntervention.length() > 0) {
                                RapportIntervention.append(" " + Emessage);
                            } else {
                                RapportIntervention.append(Emessage);
                            }
                        }
                        break;
                    case 73:    // Le technicien est-il encore sur site ?
                        MyClotureAppel.setOnSite(MyFessais.getEmessage());
                        break;
                    case 93:    // Nature de la panne.
                        MyClotureAppel.setNature(MyFessais.getEmessage());
                        break;
                }
            }

            if (RapportIntervention.length() > 0) {
                MyClotureAppel.setRapport(RapportIntervention.toString());
            }

            this.setRapportIntervention(MyClotureAppel.getRapport());
            this.setTechnicienSurSite(MyClotureAppel.getOnSite());
        }
        MyFessaisDAO.close();    }

    /**
     * Contructeur secondaire de la classe Ticket sans le complément d'appel.
     *
     * @param MyConnection connexion à la base de données courante.
     * @param Fcalls_0000 appel,
     * @param MyEtatTicket etat du ticket.
     * @throws java.lang.ClassNotFoundException en cas de classe non trouvée.
     * @throws java.sql.SQLException en cas d'erreur SQL.
     */
    public Ticket_0000(Connection MyConnection, Fcalls Fcalls_0000, 
            EtatTicket MyEtatTicket) 
            throws ClassNotFoundException, SQLException {
//        this.Fcalls_0000 = Fcalls_0000;
//        this.Fcomplmt_0000 = null;
        this(MyConnection, Fcalls_0000, null, MyEtatTicket);
    }

    /**
     * Retourne l'appel.
     *
     * @return L'appel Fcalls_0000.
     */
    public Fcalls getFcalls_0000() {
        return Fcalls_0000;
    }

    /**
     * Retourne le complément d'appel.
     *
     * @return Le complément d'appel Fcomplmt_0000.
     */
    public Fcomplmt getFcomplmt_0000() {
        return Fcomplmt_0000;
    }

    /**
     * Définit l'appel.
     *
     * @param Fcalls_0000 Définit l'appel.
     */
    public void setFcalls_0000(Fcalls Fcalls_0000) {
        this.Fcalls_0000 = Fcalls_0000;
    }

    /**
     * Définit le complément d'appel.
     *
     * @param Fcomplmt_0000 Définit le complément d'appel.
     */
    public void setFcomplmt_0000(Fcomplmt Fcomplmt_0000) {
        this.Fcomplmt_0000 = Fcomplmt_0000;
    }

    /**
     * Retourne le contenu du ticket.
     *
     * @return le contenu du ticket.
     */
    @Override
    public String toString() {
        return (Fcalls_0000 + " " + Fcomplmt_0000);
    }



    /**
     * @return A6name nom de l'agence.
     */
    public String getA6name() {
        return A6name;
    }

    /**
     * @param A6name définit le nom de l'agence.
     */
    public void setA6name(String A6name) {
        this.A6name = A6name;
    }

    /**
     * @return M6name nom de l'item de menu sélectionné.
     */
    public String getM6name() {
        return M6name;
    }

    /**
     * @param M6name définit le nom de l'item de menu.
     */
    public void setM6name(String M6name) {
        this.M6name = M6name;
    }

    /**
     * @return EtatIntervention l'état de l'intevention
     */
    public String getEtatIntervention() {
        return EtatIntervention;
    }

    /**
     * @param EtatIntervention définit l'état de l'intervention.
     */
    public void setEtatIntervention(String EtatIntervention) {
        this.EtatIntervention = EtatIntervention;
    }

    /**
     * @return the Prestataire1
     */
    public String getPrestataire1() {
        return Prestataire1;
    }

    /**
     * @param Prestataire1 the Prestataire1 to set
     */
    public void setPrestataire1(String Prestataire1) {
        this.Prestataire1 = Prestataire1;
    }

    /**
     * @return the DateMissionnement1
     */
    public String getDateMissionnement1() {
        return DateMissionnement1;
    }

    /**
     * @param DateMissionnement1 the DateMissionnement1 to set
     */
    public void setDateMissionnement1(String DateMissionnement1) {
        this.DateMissionnement1 = DateMissionnement1;
    }

    /**
     * @return the HeureMissionnement1
     */
    public String getHeureMissionnement1() {
        return HeureMissionnement1;
    }

    /**
     * @param HeureMissionnement1 the HeureMissionnement1 to set
     */
    public void setHeureMissionnement1(String HeureMissionnement1) {
        this.HeureMissionnement1 = HeureMissionnement1;
    }

    /**
     * @return the NoTelephone1
     */
    public String getNoTelephone1() {
        return NoTelephone1;
    }

    /**
     * @param NoTelephone1 the NoTelephone1 to set
     */
    public void setNoTelephone1(String NoTelephone1) {
        this.NoTelephone1 = NoTelephone1;
    }

    /**
     * @return the Prestataire2
     */
    public String getPrestataire2() {
        return Prestataire2;
    }

    /**
     * @param Prestataire2 the Prestataire2 to set
     */
    public void setPrestataire2(String Prestataire2) {
        this.Prestataire2 = Prestataire2;
    }

    /**
     * @return the DateMissionnement2
     */
    public String getDateMissionnement2() {
        return DateMissionnement2;
    }

    /**
     * @param DateMissionnement2 the DateMissionnement2 to set
     */
    public void setDateMissionnement2(String DateMissionnement2) {
        this.DateMissionnement2 = DateMissionnement2;
    }

    /**
     * @return the HeureMissionnement2
     */
    public String getHeureMissionnement2() {
        return HeureMissionnement2;
    }

    /**
     * @param HeureMissionnement2 the HeureMissionnement2 to set
     */
    public void setHeureMissionnement2(String HeureMissionnement2) {
        this.HeureMissionnement2 = HeureMissionnement2;
    }

    /**
     * @return the NoTelephone2
     */
    public String getNoTelephone2() {
        return NoTelephone2;
    }

    /**
     * @param NoTelephone2 the NoTelephone2 to set
     */
    public void setNoTelephone2(String NoTelephone2) {
        this.NoTelephone2 = NoTelephone2;
    }

    /**
     * @return the RapportIntervention
     */
    public String getRapportIntervention() {
        return RapportIntervention;
    }

    /**
     * @param RapportIntervention the RapportIntervention to set
     */
    public void setRapportIntervention(String RapportIntervention) {
        this.RapportIntervention = RapportIntervention;
    }

    /**
     * @return the TechnicienSurSite
     */
    public String getTechnicienSurSite() {
        return TechnicienSurSite;
    }

    /**
     * @param TechnicienSurSite the TechnicienSurSite to set
     */
    public void setTechnicienSurSite(String TechnicienSurSite) {
        this.TechnicienSurSite = TechnicienSurSite;
    }

    /**
     * @return MyEtatTicket l'état du ticket.
     */
    public EtatTicket getMyEtatTicket() {
        return MyEtatTicket;
    }

    /**
     * @param MyEtatTicket définit l'état du ticket.
     */
    public void setMyEtatTicket(EtatTicket MyEtatTicket) {
        this.MyEtatTicket = MyEtatTicket;
    }

}
