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
 * @author Thierry Baribaud
 * @version 0.25
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
        ExpCalls_0513 MyExpCalls_0513;
        ExpCalls_0572 MyExpCalls_0572;
        ExpCalls_0609 MyExpCalls_0609;

        // On récupère les arguments de la ligne de commande.
        System.out.println("Récupération des arguments en ligne de commande ...");
        try {
            MyArgs = new GetArgs(Args);
            System.out.println(MyArgs);

            System.out.println("Lecture du fichier de paramètres ...");
            MyApplicationProperties = new ApplicationProperties("ExpCalls.prop");

            System.out.println("Lecture des paramètres de base de données ...");
            MyDBServer = new DBServer(MyArgs.getSourceServer(), MyApplicationProperties);
            System.out.println("  " + MyDBServer);

            MyDBManager = new DBManager(MyDBServer);

            MyConnection = MyDBManager.getConnection();
            MyExpCallsParams = new ExpCallsParams(MyConnection, MyArgs);

            switch (MyExpCallsParams.getModeleDeRapport()) {
                case CAR:
                    MyExpCalls_0513 = new ExpCalls_0513(MyExpCallsParams);
                    break;
                case NEX:
                    MyExpCalls_0572 = new ExpCalls_0572(MyExpCallsParams);
                    break;
                case VF:
                    MyExpCalls_0609 = new ExpCalls_0609(MyExpCallsParams);
                    break;
                default:
                    MyExpCalls_0000 = new ExpCalls_0000(MyExpCallsParams);
                    break;
            }

        } catch (GetArgsException exception) {
            Logger.getLogger(ExpCalls.class.getName()).log(Level.SEVERE, null, exception);
            GetArgs.usage();
        } catch (ClassNotFoundException exception) {
            Logger.getLogger(ExpCalls.class.getName()).log(Level.SEVERE, null, exception);
        } catch (SQLException exception) {
            Logger.getLogger(ExpCalls.class.getName()).log(Level.SEVERE, null, exception);
        }

    }

    /**
     * Méthode permettant le lancement du programme d'extraction d'appels.
     *
     * @param Args paramètres de la ligne de commande.
     */
    public static void main(String[] Args) {
        ExpCalls expCalls = null;

        System.out.println("Lancement de ExpCalls ...");

        try {
            expCalls = new ExpCalls(Args);
        } catch (Exception exception) {
            System.out.println("Problème lors du lancement de ExpCalls " + exception);
            exception.printStackTrace();
        }

        System.out.println("Traitement terminé.");

    }

}
