package expcalls;

import bdd.Fa2pi;
import bdd.Fa2piDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import utils.ApplicationProperties;
import utils.DBManager;
import utils.DBServer;
import utils.DBServerException;
import utils.GetArgsException;

/**
 * TstFa2piDAO programme permettant de tester le pattern DAO pour Fa2pi.
 *
 * @version 0.51
 * @author Thierry Baribaud
 */
public class TstFa2piDAO {

    /**
     * Les arguments en ligne de commande permettent de changer le mode de
     * fonctionnement. Voir GetArgs pour plus de détails.
     *
     * @param args arguments de la ligne de commande.
     * @throws GetArgsException en cas de problème sur les paramètres.
     */
    public static void main(String[] args) throws GetArgsException {
        GetArgs getArgs;
        ApplicationProperties applicationProperties;
        DBServer dBServer;
        DBManager dBManager;
        Fa2piDAO fa2piDAO;
        Fa2pi fa2pi1;
        Fa2pi fa2pi;
        long i;

        try {
            System.out.println("Récupération des arguments en ligne de commande ...");
            getArgs = new GetArgs(args);
            System.out.println(getArgs);

            System.out.println("Lecture du fichier de paramètres ...");
            applicationProperties = new ApplicationProperties("ExpCalls.prop");

            System.out.println("Lecture des paramètres de base de données ...");
            dBServer = new DBServer(getArgs.getSourceServer(), applicationProperties);
            System.out.println("  " + dBServer);

            dBManager = new DBManager(dBServer);

// Essai d'insertion
            fa2piDAO = new Fa2piDAO(dBManager.getConnection());
            fa2piDAO.setInsertPreparedStatement();
            fa2pi1 = new Fa2pi();
            fa2pi1.setA10unum(getArgs.getUnum());
            fa2pi1.setA10onum0(36);
            fa2pi1.setA10laguid("t:87047");
            fa2pi1.setA10a6num(1234);
            fa2pi1.setA10evttype(445);
            fa2pi1.setA10data("\"date\":\"2017-11-15T10:19:01\"");
            fa2pi1.setA10status(-1);
            fa2pi1.setA10nberr(5);
            fa2pi1.setA10size(10);
            fa2pi1.setA10credate(new Timestamp(new java.util.Date().getTime()));
            fa2pi1.setA10update(Timestamp.valueOf("2050-12-31 23:59:59.0"));
            System.out.println("Fa2pi(avant insertion)=" + fa2pi1);
            fa2piDAO.insert(fa2pi1);
            fa2piDAO.closeInsertPreparedStatement();
            System.out.println("Fa2pi(après insertion)=" + fa2pi1);
            System.out.println("Rangée(s) affectée(s)=" + fa2piDAO.getNbAffectedRow());

// Essai de mise à jour
            fa2piDAO.setUpdatePreparedStatement();
            fa2pi1.setA10onum(36);
            fa2piDAO.update(fa2pi1);
            System.out.println("Fa2pi(après mise-à-jour)=" + fa2pi1);
            System.out.println("Rangée(s) affectée(s)=" + fa2piDAO.getNbAffectedRow());
            fa2piDAO.closeUpdatePreparedStatement();

// Essai de lecture
            fa2piDAO.filterByGid("t:87047");
            System.out.println("  SelectStatement=" + fa2piDAO.getSelectStatement());
            fa2piDAO.setSelectPreparedStatement();
            i = 0;
            while ((fa2pi = fa2piDAO.select()) != null) {
                i++;
                System.out.println("Fa2pi(" + i + ")=" + fa2pi);
            }
            fa2piDAO.closeSelectPreparedStatement();

// Essai de suppression
            System.out.println("Suppression de : " + fa2pi1);
            fa2piDAO.setDeletePreparedStatement();
            fa2piDAO.delete(fa2pi1.getA10num());
            fa2piDAO.closeDeletePreparedStatement();
            System.out.println("Rangée(s) affectée(s)=" + fa2piDAO.getNbAffectedRow());

        } catch (IOException exception) {
            System.out.println("Erreur en lecture du fichier des propriétés " + exception);
        } catch (DBServerException exception) {
            System.out.println("Erreur avec le serveur de base de données " + exception);
        } catch (ClassNotFoundException exception) {
            System.out.println("Erreur classe non trouvée " + exception);
        } catch (SQLException exception) {
            System.out.println("Erreur SQL rencontrée " + exception);
        }
    }
}
