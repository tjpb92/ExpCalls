package expcalls;

import agency.Fagency;
import agency.FagencyDAO;
import expcalls.Ticket_0000.EtatTicket;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import liba2pi.ApplicationProperties;
import liba2pi.DBManager;
import liba2pi.DBServer;
import liba2pi.DBServerException;

/*
 * Ce programme exporte les appels d'un service d'urgence dans un fichier au
 * format XML.
 * @version Mai 2016.
 * @author Thierry Baribaud.
 */
public class ExpCalls {

    private static final DateFormat MyDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Les arguments en ligne de commande permettent de changer le mode de
     * fonctionnement. Voir GetArgs pour plus de détails.
     *
     * @param Args arguments de la ligne de commande.
     * @throws java.io.IOException en cas de fichier non lisible ou absent.
     * @throws liba2pi.DBServerException en cas de propriété incorrecte.
     * @throws java.sql.SQLException en cas d'une erreur SQL.
     */
    public ExpCalls(String[] Args) throws IOException, DBServerException, SQLException {

        CallsXMLDocument MyXMLDocument;
        GetArgs MyArgs;
        ApplicationProperties MyApplicationProperties;
        DBServer MyDBServer;
        DBManager MyDBManager;
        Connection MyConnection;

        // On récupère les arguments de la ligne de commande.
        System.out.println("Récupération des arguments en ligne de commande ...");
        try {
            MyArgs = new GetArgs(Args);
            System.out.println(MyArgs);

            System.out.println("Lecture du fichier de paramètres ...");
            MyApplicationProperties = new ApplicationProperties("MyDatabases.prop");

            System.out.println("Lecture des paramètres de base de données ...");
            MyDBServer = new DBServer(MyArgs.getSourceServer(), MyApplicationProperties);
            System.out.println("  " + MyDBServer);

            MyXMLDocument = new CallsXMLDocument("tickets", "tickets_0000.xsd");

            MyDBManager = new DBManager(MyDBServer);

            MyConnection = MyDBManager.getConnection();

            // Traitement des appels en cours.
            processTickets(MyConnection, MyArgs, MyXMLDocument, EtatTicket.EN_COURS);

            // Traitement des appels archivés.
            processTickets(MyConnection, MyArgs, MyXMLDocument, EtatTicket.ARCHIVE);

            MyXMLDocument.FinalizeXMLDocument(MyArgs.getFileOut());
        } catch (GetArgsException MyException) {
            Logger.getLogger(ExpCalls.class.getName()).log(Level.SEVERE, null, MyException);
            GetArgs.usage();
        } catch (ClassNotFoundException MyException) {
            Logger.getLogger(ExpCalls.class.getName()).log(Level.SEVERE, null, MyException);
        } catch (SQLException MyException) {
            Logger.getLogger(ExpCalls.class.getName()).log(Level.SEVERE, null, MyException);
        }

    }

    private void processTickets(Connection MyConnection, GetArgs MyArgs, CallsXMLDocument MyXMLDocument, EtatTicket MyEtatTicket) {

        Fcalls MyFcalls;
        FcallsDAO MyFcallsDAO;
        int i;
        Ticket_0000 MyTicket_0000;

        try {
            MyFcallsDAO = new FcallsDAO(MyConnection, 0, MyArgs, MyEtatTicket);

            i = 0;
            while ((MyFcalls = MyFcallsDAO.select()) != null) {
                i++;
                MyTicket_0000 = new Ticket_0000(MyFcalls);
                processTicket(MyConnection, MyTicket_0000, MyEtatTicket);
                System.out.println("Ticket(" + i + ")=" + MyTicket_0000);
                MyXMLDocument.AddToXMLDocument(MyTicket_0000);
            }
        } catch (ClassNotFoundException MyException) {
            Logger.getLogger(ExpCalls.class.getName()).log(Level.SEVERE, null, MyException);
        } catch (SQLException MyException) {
            Logger.getLogger(ExpCalls.class.getName()).log(Level.SEVERE, null, MyException);
        }
    }

    private void processTicket(Connection MyConnection, Ticket_0000 MyTicket_0000, EtatTicket MyEtatTicket) throws ClassNotFoundException, SQLException {

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

        // Récupération du complément d'appel
//        System.out.println("  Récupération du complément d'appel");
        cc6num = MyTicket_0000.Fcalls_0000.getCc6num();
        MyFcomplmt = null;
        if (cc6num > 0) {
            MyFcomplmtDAO = new FcomplmtDAO(MyConnection, cc6num);
            MyFcomplmt = MyFcomplmtDAO.select();
            MyTicket_0000.setFcomplmt_0000(MyFcomplmt);
        }

        // Récupération de l'agence
//        System.out.println("  Récupération de l'agence");
        a6num = MyTicket_0000.Fcalls_0000.getCzone();
        MyFagency = null;
        MyA6name = null;
        if (a6num > 0) {
            MyFagencyDAO = new FagencyDAO(MyConnection, a6num);
            MyFagency = MyFagencyDAO.select();
            if (MyFagency != null) {
                MyA6extname = MyFagency.getA6extname();
                MyA6name = (MyA6extname != null) ? MyA6extname : MyFagency.getA6name();
            }
        }
        if (MyA6name != null) {
            MyTicket_0000.setA6name(MyA6name);
        }

        // Récupération de l'item du menu sélectionné
//        System.out.println("  Récupération de l'item du menu sélectionné");
        m6num = MyTicket_0000.Fcalls_0000.getCquery1();
        MyM6name = null;
        MyM6extname = null;
        MyFmenuit = null;
        if (m6num > 0) {
            MyFmenuitDAO = new FmenuitDAO(MyConnection, m6num);
            MyFmenuit = MyFmenuitDAO.select();
            MyM6extname = MyFmenuit.getM6extname();
            MyM6name = (MyM6extname != null) ? MyM6extname : MyFmenuit.getM6name();
        }
        if (MyM6name != null) {
            MyTicket_0000.setM6name(MyM6name);
        }

        // Recherche la première transmission
//        System.out.println("  Récupération de la première transmission");
        MyFessaisDAO = new FessaisDAO(MyConnection, 0, MyTicket_0000.Fcalls_0000.getCnum(), 0, MyEtatTicket);
        MyFessais = MyFessaisDAO.getFirstTransmission();
        if (MyFessais != null) {
            enumabs1 = MyFessais.getEnumabs();
            MyTicket_0000.setEtatIntervention("Intervention");
            MyTicket_0000.setDateMissionnement1(MyDateFormat.format(MyFessais.getEdate()));
            MyTicket_0000.setHeureMissionnement1(MyFessais.getEtime());
            tnum = MyFessais.getEtnum();
            if (tnum > 0) {
                MyFtoubibDAO = new FtoubibDAO(MyConnection, tnum, 0);
                MyFtoubib = MyFtoubibDAO.select();
                if (MyFtoubib != null) {
                    MyTicket_0000.setPrestataire1(MyFtoubib.getTlname() + " " + MyFtoubib.getTfname());
                    MyTicket_0000.setNoTelephone1(MyFtoubib.getTel());
                }
            }
        } else {
            MyTicket_0000.setEtatIntervention("Message");
        }

        // Recherche la dernière transmission
//        System.out.println("  Récupération de la dernière transmission");
        MyFessais = MyFessaisDAO.getLastTransmission();
        if (MyFessais != null) {
            if (enumabs1 != MyFessais.getEnumabs()) {
                MyTicket_0000.setEtatIntervention("Intervention");
                MyTicket_0000.setDateMissionnement2(MyDateFormat.format(MyFessais.getEdate()));
                MyTicket_0000.setHeureMissionnement2(MyFessais.getEtime());
                tnum = MyFessais.getEtnum();
                if (tnum > 0) {
                    MyFtoubibDAO = new FtoubibDAO(MyConnection, tnum, 0);
                    MyFtoubib = MyFtoubibDAO.select();
                    if (MyFtoubib != null) {
                        MyTicket_0000.setPrestataire2(MyFtoubib.getTlname() + " " + MyFtoubib.getTfname());
                        MyTicket_0000.setNoTelephone2(MyFtoubib.getTel());
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
            MyFessaisDAO = new FessaisDAO(MyConnection, 0, MyTicket_0000.Fcalls_0000.getCnum(), egid, MyEtatTicket);
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
                            if (RapportIntervention.length()>0) {
                                RapportIntervention.append(" " + Emessage);
                            }
                            else {
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

            if (RapportIntervention.length() > 0 ) {
                MyClotureAppel.setRapport(RapportIntervention.toString());
            }
            
            MyTicket_0000.setRapportIntervention(MyClotureAppel.getRapport());
            MyTicket_0000.setTechnicienSurSite(MyClotureAppel.getOnSite());
        }
    }

    public static void main(String[] Args) {
        ExpCalls MyExpCalls;

        System.out.println("Lancement de ExpCalls ...");

        try {
            MyExpCalls = new ExpCalls(Args);
        } catch (Exception MyException) {
            System.out.println("Problème lors du lancement de ExpCalls" + MyException);
        }

        System.out.println("Traitement terminé.");

    }

}
