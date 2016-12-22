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
 * @version 0.27
 */
public class ExpCalls {

    /**
     * debugMode : fonctionnement du programme en mode debug (true/false).
     * Valeur par défaut : false.
     */
    private static boolean debugMode = false;

    /**
     * testMode : fonctionnement du programme en mode test (true/false). Valeur
     * par défaut : false.
     */
    private static boolean testMode = false;

    /**
     * Les arguments en ligne de commande permettent de changer le mode de
     * fonctionnement. Voir GetArgs pour plus de détails.
     *
     * @param args arguments de la ligne de commande.
     * @throws java.io.IOException en cas de fichier non lisible ou absent.
     * @throws utils.DBServerException en cas de propriété incorrecte.
     * @throws java.sql.SQLException en cas d'une erreur SQL.
     */
    public ExpCalls(String[] args) throws IOException, DBServerException, SQLException {

        GetArgs getArgs;
        ApplicationProperties applicationProperties;
        DBServer dBServer;
        DBManager dBManager;
        Connection connection;
        ExpCallsParams expCallsParams;
        ExpCalls_0000 expCalls_0000;
        ExpCalls_0513 expCalls_0513;
        ExpCalls_0572 expCalls_0572;
        ExpCalls_0582 expCalls_0582;
        ExpCalls_0609 expCalls_0609;

        try {
            System.out.println("Analyse des arguments de la ligne de commande ...");
            getArgs = new GetArgs(args);
            setDebugMode(getArgs.getDebugMode());
            setTestMode(getArgs.getTestMode());
            if (debugMode) System.out.println(getArgs);

            System.out.println("Lecture des paramètres d'exécution ...");
            applicationProperties = new ApplicationProperties("ExpCalls.prop");

            System.out.println("Lecture des paramètres de base de données ...");
            dBServer = new DBServer(getArgs.getSourceServer(), applicationProperties);
            if (debugMode) System.out.println("  " + dBServer);

            dBManager = new DBManager(dBServer);

            connection = dBManager.getConnection();
            expCallsParams = new ExpCallsParams(connection, getArgs);

            switch (expCallsParams.getModeleDeRapport()) {
                case CAR:
                    expCalls_0513 = new ExpCalls_0513(expCallsParams);
                    break;
                case NEX:
                    expCalls_0572 = new ExpCalls_0572(expCallsParams);
                    break;
                case ENE:
                    expCalls_0582 = new ExpCalls_0582(expCallsParams);
                    break;
                case VF:
                    expCalls_0609 = new ExpCalls_0609(expCallsParams);
                    break;
                default:
                    expCalls_0000 = new ExpCalls_0000(expCallsParams);
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
     * @param args paramètres de la ligne de commande.
     */
    public static void main(String[] args) {
        ExpCalls expCalls = null;

        System.out.println("Lancement de ExpCalls ...");

        try {
            expCalls = new ExpCalls(args);
        } catch (Exception exception) {
            System.out.println("Problème lors du lancement de ExpCalls " + exception);
            exception.printStackTrace();
        }

        System.out.println("Traitement terminé.");

    }

    /**
     * @param debugMode : fonctionnement du programme en mode debug
     * (true/false).
     */
    public void setDebugMode(boolean debugMode) {
        ExpCalls.debugMode = debugMode;
    }

    /**
     * @param testMode : fonctionnement du programme en mode test (true/false).
     */
    public void setTestMode(boolean testMode) {
        ExpCalls.testMode = testMode;
    }

    /**
     * @return debugMode : retourne le mode de fonctionnement debug.
     */
    public boolean getDebugMode() {
        return (debugMode);
    }

    /**
     * @return testMode : retourne le mode de fonctionnement test.
     */
    public boolean getTestMode() {
        return (testMode);
    }
}
