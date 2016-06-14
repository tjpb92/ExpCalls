package expcalls;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ApplicationProperties;
import utils.DBManager;
import utils.DBServer;
import utils.DBServerException;

/**
 * Ce programme exporte les appels d'un service d'urgence dans un fichier au
 * format XML.
 * 
 * @version Juin 2016
 * @author Thierry Baribaud
 */
public class ExpCalls {

    /**
     * Les arguments en ligne de commande permettent de changer le mode de
     * fonctionnement. Voir GetArgs pour plus de détails.
     *
     * @param Args arguments de la ligne de commande.
     * @throws java.io.IOException en cas de fichier non lisible ou absent.
     * @throws utils.DBServerException en cas de propriété incorrecte.
     * @throws java.sql.SQLException en cas d'une erreur SQL.
     */
    public ExpCalls(String[] Args) throws IOException, DBServerException, SQLException {

        GetArgs MyArgs;
        ApplicationProperties MyApplicationProperties;
        DBServer MyDBServer;
        DBManager MyDBManager;
        Connection MyConnection;
        ExpCallsParams MyExpCallsParams;
        ExpCalls_0000 MyExpCalls_0000;
        ExpCalls_0572 MyExpCalls_0572;

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

            MyDBManager = new DBManager(MyDBServer);

            MyConnection = MyDBManager.getConnection();
            MyExpCallsParams = new ExpCallsParams(MyConnection, MyArgs);

            switch (MyArgs.getUnum()) {
                case 572:
                    MyExpCalls_0572 = new ExpCalls_0572(MyExpCallsParams);
                    break;
                default:
                    MyExpCalls_0000 = new ExpCalls_0000(MyExpCallsParams);
                    break;
            }

        } catch (GetArgsException MyException) {
            Logger.getLogger(ExpCalls.class.getName()).log(Level.SEVERE, null, MyException);
            GetArgs.usage();
        } catch (ClassNotFoundException MyException) {
            Logger.getLogger(ExpCalls.class.getName()).log(Level.SEVERE, null, MyException);
        } catch (SQLException MyException) {
            Logger.getLogger(ExpCalls.class.getName()).log(Level.SEVERE, null, MyException);
        }

    }

    /**
     * Méthode permettant le lancement du programme d'extraction d'appels.
     *
     * @param Args paramètres de la ligne de commande.
     */
    public static void main(String[] Args) {
        ExpCalls MyExpCalls = null;

        System.out.println("Lancement de ExpCalls ...");

        try {
            MyExpCalls = new ExpCalls(Args);
        } catch (Exception MyException) {
            System.out.println("Problème lors du lancement de ExpCalls" + MyException);
        }

        System.out.println("Traitement terminé.");

    }

}
