package expcalls;

import bdd.ClotureAppel;
import bdd.EtatTicket;
import bdd.Factivity;
import bdd.FactivityDAO;
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
 * Classe représentant un ticket d'un client basique. Cela correspond à
 * l'association d'un appel Fcalls et d'un complément d'appel Fcomplmt s'il
 * existe. Les tickets spécifiques à un client dériveront de celui-ci.
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
    private String A6name = null;

    /**
     * Appelation externe de l'agence.
     */
    private String A6extname = null;

    /**
     * Nom abrégé de l'agence.
     */
    private String A6abbname = null;

    /**
     * Item de menu sélectionné.
     */
    private String M6name = null;

    /**
     * Appelation externe de l'tem de menu sélectionné.
     */
    private String M6extname = null;

    /**
     * Etat de l'intervention.
     */
    private String EtatIntervention = null;

    /**
     * Prestataire sur la première transmission.
     */
    private String Prestataire1 = null;

    /**
     * Date de la première transmission.
     */
    private String DateMissionnement1 = null;

    /**
     * Heure de la première transmission.
     */
    private String HeureMissionnement1 = null;

    /**
     * Numéro de téléphone du prestataire.
     */
    private String NoTelephone1 = null;

    /**
     * Email du prestataire.
     */
    private String Email1 = null;

    /**
     * Activité du prestataire.
     */
    private String A4name1 = null;

    /**
     * Prestataire sur la dernière transmission.
     */
    private String Prestataire2 = null;

    /**
     * Date de la dernière transmission.
     */
    private String DateMissionnement2 = null;

    /**
     * Heure de la dernière transmission.
     */
    private String HeureMissionnement2 = null;

    /**
     * Numéro de téléphone du prestataire.
     */
    private String NoTelephone2 = null;

    /**
     * Email du prestataire.
     */
    private String Email2 = null;

    /**
     * Activité du prestataire.
     */
    private String A4name2 = null;

    /**
     * Rapport d'intervention
     */
    private String RapportIntervention = null;

    /**
     * Le technicien est-il encore sur site ?
     */
    private String TechnicienSurSite = null;

    /**
     * Nature de l'intervention
     */
    private String Nature = null;

    /**
     * Résultat de l'intervention
     */
    private String Resultat = null;

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
        int a4num;
        String A4name;
        Factivity MyFactivity;
        FactivityDAO MyFactivityDAO;

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
        MyA6extname = null;
        if (a6num > 0) {
            MyFagencyDAO = new FagencyDAO(MyConnection);
            MyFagencyDAO.filterById(a6num);
            MyFagencyDAO.setSelectPreparedStatement();
            MyFagency = MyFagencyDAO.select();
            if (MyFagency != null) {
                MyA6name = MyFagency.getA6name();
                MyA6extname = MyFagency.getA6extname();
            }
            MyFagencyDAO.closeSelectPreparedStatement();
        }
        if (MyA6name != null) {
            this.setA6name(MyA6name);
        }
        if (MyA6extname != null) {
            this.setA6extname(MyA6extname);
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
            MyM6name = MyFmenuit.getM6name();
            MyM6extname = MyFmenuit.getM6extname();
            MyFmenuitDAO.close();
        }
        if (MyM6name != null) {
            this.setM6name(MyM6name);
        }
        if (MyM6extname != null) {
            this.setM6extname(MyM6extname);
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
                    this.setPrestataire1(MyFtoubib.getTlname(), MyFtoubib.getTfname());
                    this.setNoTelephone1(MyFtoubib.getTel());
                    this.setEmail1(MyFtoubib.getTemail());
                    a4num = MyFtoubib.getTa4num();
                    if (a4num > 0) {
                        MyFactivityDAO = new FactivityDAO(MyConnection, a4num);
                        MyFactivity = MyFactivityDAO.select();
                        if (MyFactivity != null) {
                            A4name = MyFactivity.getA4name();
                            if (A4name != null) {
                                this.setA4name1(A4name);
                            }
                        }
                        MyFactivityDAO.close();
                    }
                }
                MyFtoubibDAO.close();
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
                        this.setPrestataire2(MyFtoubib.getTlname(), MyFtoubib.getTfname());
                        this.setNoTelephone2(MyFtoubib.getTel());
                        this.setEmail2(MyFtoubib.getTemail());
                        a4num = MyFtoubib.getTa4num();
                        if (a4num > 0) {
                            MyFactivityDAO = new FactivityDAO(MyConnection, a4num);
                            MyFactivity = MyFactivityDAO.select();
                            if (MyFactivity != null) {
                                A4name = MyFactivity.getA4name();
                                if (A4name != null) {
                                    this.setA4name2(A4name);
                                }
                            }
                            MyFactivityDAO.close();
                        }
                    }
                    MyFtoubibDAO.close();
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
            RapportIntervention = new StringBuffer("egid=" + egid);
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
                                RapportIntervention.append(" ").append(Emessage);
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
//            System.out.println("  " + MyClotureAppel);

            this.setRapportIntervention(MyClotureAppel.getRapport());
            this.setTechnicienSurSite(MyClotureAppel.getOnSite());
            this.setNature(MyClotureAppel.getNature());
            this.setResultat(MyClotureAppel.getResultat());
        }
        MyFessaisDAO.close();
    }

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
     * @return A6extname appellation externe de l'agence.
     */
    public String getA6extname() {
        return A6extname;
    }

    /**
     * @param A6extname définit l'appellation externe de l'agence.
     */
    public void setA6extname(String A6extname) {
        this.A6extname = A6extname;
    }

    /**
     * @return A6abbname nom abrégé de l'agence.
     */
    public String getA6abbname() {
        return A6abbname;
    }

    /**
     * @param A6abbname définit le nom abrégé de l'agence.
     */
    public void setA6abbname(String A6abbname) {
        this.A6abbname = A6abbname;
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
     * @return M6extname appellation externe de l'item de menu sélectionné.
     */
    public String getM6extname() {
        return M6extname;
    }

    /**
     * @param M6extname définit l'appellation externe de l'item de menu.
     */
    public void setM6extname(String M6extname) {
        this.M6extname = M6extname;
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
     * @return Prestataire1 le nom du prestataire.
     */
    public String getPrestataire1() {
        return Prestataire1;
    }

    /**
     * @param Prestataire1 définit le nom du prestataire.
     */
    public void setPrestataire1(String Prestataire1) {
        this.Prestataire1 = Prestataire1;
    }

    /**
     * @param Lastname nom du prestataire,
     * @param Firstname prénom du prestataire,
     */
    public void setPrestataire1(String Lastname, String Firstname) {
        StringBuffer MyName = null;
        
        if (Lastname != null) MyName = new StringBuffer(Lastname);
        if (Firstname != null) {
            if (MyName != null)
                MyName.append(" ").append(Firstname);
            else
                MyName = new StringBuffer(Firstname);
        }
        if (MyName != null) this.setPrestataire1(MyName.toString());
    }

    /**
     * @return DateMissionnement1 la première date de missionnement.
     */
    public String getDateMissionnement1() {
        return DateMissionnement1;
    }

    /**
     * @param DateMissionnement1 définit la première date de missionnement.
     */
    public void setDateMissionnement1(String DateMissionnement1) {
        this.DateMissionnement1 = DateMissionnement1;
    }

    /**
     * @return HeureMissionnement1 la première heure de missionnement.
     */
    public String getHeureMissionnement1() {
        return HeureMissionnement1;
    }

    /**
     * @param HeureMissionnement1 définit la première heure de missionnement.
     */
    public void setHeureMissionnement1(String HeureMissionnement1) {
        this.HeureMissionnement1 = HeureMissionnement1;
    }

    /**
     * @return NoTelephone1 le numéro de téléphone du prestataire.
     */
    public String getNoTelephone1() {
        return NoTelephone1;
    }

    /**
     * @param NoTelephone1 définit le numéro de téléphone du prestataire.
     */
    public void setNoTelephone1(String NoTelephone1) {
        this.NoTelephone1 = NoTelephone1;
    }

    /**
     * @return Email1 l'email du prestataire.
     */
    public String getEmail1() {
        return Email1;
    }

    /**
     * @param Email1 définit l'email du prestataire.
     */
    public void setEmail1(String Email1) {
        this.Email1 = Email1;
    }

    /**
     * @return A4name1 l'activité du prestataire.
     */
    public String getA4name1() {
        return A4name1;
    }

    /**
     * @param A4name1 définit l'activité du prestataire.
     */
    public void setA4name1(String A4name1) {
        this.A4name1 = A4name1;
    }

    /**
     * @return Prestataire2 le nom du prestataire.
     */
    public String getPrestataire2() {
        return Prestataire2;
    }

    /**
     * @param Prestataire2 définit le nom du prestataire.
     */
    public void setPrestataire2(String Prestataire2) {
        this.Prestataire2 = Prestataire2;
    }

    /**
     * @param Lastname nom du prestataire,
     * @param Firstname prénom du prestataire,
     */
    public void setPrestataire2(String Lastname, String Firstname) {
        StringBuffer MyName = null;
        
        if (Lastname != null) MyName = new StringBuffer(Lastname);
        if (Firstname != null) {
            if (MyName != null)
                MyName.append(" ").append(Firstname);
            else
                MyName = new StringBuffer(Firstname);
        }
        if (MyName != null) this.setPrestataire1(MyName.toString());
    }
    
    /**
     * @return DateMissionnement2 la dernière date de missionnement.
     */
    public String getDateMissionnement2() {
        return DateMissionnement2;
    }

    /**
     * @param DateMissionnement2 définit la dernière date de missionnement.
     */
    public void setDateMissionnement2(String DateMissionnement2) {
        this.DateMissionnement2 = DateMissionnement2;
    }

    /**
     * @return HeureMissionnement2 la dernière heure de missionnement.
     */
    public String getHeureMissionnement2() {
        return HeureMissionnement2;
    }

    /**
     * @param HeureMissionnement2 définit la dernière heure de missionnement.
     */
    public void setHeureMissionnement2(String HeureMissionnement2) {
        this.HeureMissionnement2 = HeureMissionnement2;
    }

    /**
     * @return NoTelephone2 le numéro de téléphone du prestataire.
     */
    public String getNoTelephone2() {
        return NoTelephone2;
    }

    /**
     * @param NoTelephone2 définit le numéro de téléphone du prestataire.
     */
    public void setNoTelephone2(String NoTelephone2) {
        this.NoTelephone2 = NoTelephone2;
    }

    /**
     * @return Email2 l'email du prestataire.
     */
    public String getEmail2() {
        return Email2;
    }

    /**
     * @param Email2 définit l'email du prestataire.
     */
    public void setEmail2(String Email2) {
        this.Email2 = Email2;
    }

    /**
     * @return A4name2 l'activité du prestataire.
     */
    public String getA4name2() {
        return A4name2;
    }

    /**
     * @param A4name2 définit l'activité du prestataire.
     */
    public void setA4name2(String A4name2) {
        this.A4name2 = A4name2;
    }

    /**
     * @return RapportIntervention le rapport d'intervention.
     */
    public String getRapportIntervention() {
        return RapportIntervention;
    }

    /**
     * @param RapportIntervention définit le rapport d'intervention.
     */
    public void setRapportIntervention(String RapportIntervention) {
        this.RapportIntervention = RapportIntervention;
    }

    /**
     * @return TechnicienSurSite indication sur la présence du prestataire sur
     * site.
     */
    public String getTechnicienSurSite() {
        return TechnicienSurSite;
    }

    /**
     * @param TechnicienSurSite définit la présence du prestataire sur site.
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

    /**
     * @return Nature la nature de l'intervention.
     */
    public String getNature() {
        return Nature;
    }

    /**
     * @param Nature définit la nature de l'intervention.
     */
    public void setNature(String Nature) {
        this.Nature = Nature;
    }

    /**
     * @return Resultat le résultat de l'intervention.
     */
    public String getResultat() {
        return Resultat;
    }

    /**
     * @param Resultat définit le résultat de l'intervention.
     */
    public void setResultat(String Resultat) {
        this.Resultat = Resultat;
    }

}
