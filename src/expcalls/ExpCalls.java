/*
 * Ce programme exporte les appels d'un service d'urgence dans un fichier au
 * format XML.
 * @version Mai 2016.
 * @author Thierry Baribaud.
 */
package expcalls;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import liba2pi.ApplicationProperties;
import liba2pi.DBManager;
import liba2pi.DBServer;
import liba2pi.DBServerException;

public class ExpCalls {

    /**
     * Les arguments en ligne de commande permettent de changer le mode de
     * fonctionnement. Voir GetArgs pour plus de détails.
     *
     * @param Args arguments de la ligne de commande.
     * @throws java.io.IOException
     * @throws liba2pi.DBServerException
     * @throws java.sql.SQLException
     */
    public ExpCalls(String[] Args) throws IOException, DBServerException, SQLException {
        Fcalls MyFcalls;
        FcallsDAO MyFcallsDAO;
        Fcalls_0000 MyFcalls_0000;
//        CallsXMLDocument MyXMLDocument;

        GetArgs MyArgs;
        ApplicationProperties MyApplicationProperties;
        DBServer MyDBServer;
        DBManager MyDBManager;

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

//            MyXMLDocument = new CallsXMLDocument("appels", "calls.xsd");
            MyDBManager = new DBManager(MyDBServer);

            MyFcallsDAO = new FcallsDAO(MyDBManager.getConnection(), 0, MyArgs.getUnum());
            i = 0;
            Fcalls_0000.CSV_Title();
            while ((MyFcalls = MyFcallsDAO.select()) != null) {
                i++;
                MyFcalls_0000 = new Fcalls_0000(MyFcalls);
                MyFcalls_0000.toCSV();
//                System.out.println("Fcalls(" + i + ")=" + MyFcalls);
//                MyXMLDocument.AddToXMLDocument(MyFcalls);
            }
//            MyXMLDocument.FinalizeXMLDocument(MyArgs.getFileOut());
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
