package expcalls;

import agency.Fagency;
import agency.FagencyDAO;
import java.io.IOException;
import java.sql.SQLException;
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
        Fcalls MyFcalls;
        FcallsDAO MyFcallsDAO;
//        Fcalls_0000 MyFcalls_0000;
        CallsXMLDocument MyXMLDocument;
        Fcomplmt MyFcomplmt;
        FcomplmtDAO MyFcomplmtDAO;
        Ticket_0000 MyTicket_0000;
        Fagency MyFagency;
        FagencyDAO MyFagencyDAO;

        GetArgs MyArgs;
        ApplicationProperties MyApplicationProperties;
        DBServer MyDBServer;
        DBManager MyDBManager;
        int cc6num;
        int a6num;
        String MyA6name;

        int i;

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

            MyFcallsDAO = new FcallsDAO(MyDBManager.getConnection(), 0, MyArgs.getUnum());
            i = 0;
//            System.out.println(Fcalls_0000.CSV_Title());
            while ((MyFcalls = MyFcallsDAO.select()) != null) {
                i++;
                cc6num = MyFcalls.getCc6num();
                MyFcomplmt = null;
                if (cc6num > 0) {
                    MyFcomplmtDAO = new FcomplmtDAO(MyDBManager.getConnection(), cc6num);
                    MyFcomplmt = MyFcomplmtDAO.select();
                }
                a6num = MyFcalls.getCzone();
                MyFagency = null;
                MyA6name = null;
                if (a6num > 0) {
                    MyFagencyDAO = new FagencyDAO(MyDBManager.getConnection(), a6num);
                    MyFagency = MyFagencyDAO.select();
                    MyA6name = MyFagency.getA6name();
                }
                MyTicket_0000 = new Ticket_0000(MyFcalls, MyFcomplmt);
                if (MyA6name != null) {
                    MyTicket_0000.setA6name(MyA6name);
                }
                System.out.println("Ticket(" + i + ")=" + MyTicket_0000);
                MyXMLDocument.AddToXMLDocument(MyTicket_0000);
            }
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
