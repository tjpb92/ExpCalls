package expcalls;

import bdd.Fmenuit;
import bdd.FmenuitDAO;
import java.io.IOException;
import java.sql.SQLException;
import utils.ApplicationProperties;
import utils.DBManager;
import utils.DBServer;
import utils.DBServerException;
import utils.GetArgsException;

/**
 * TstFmenuitDAO programme permettant de tester le pattern DAO pour Fmenuit.
 *
 * @version 0.51
 * @author Thierry Baribaud
 */
public class TstFmenuitDAO {

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
        FmenuitDAO fmenuitDAO;
        Fmenuit fmenuit1;
        Fmenuit fmenuit;
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
            fmenuitDAO = new FmenuitDAO(dBManager.getConnection());
            fmenuitDAO.setInsertPreparedStatement();
            fmenuit1 = new Fmenuit();
            fmenuit1.setM6extname("message client");
            fmenuit1.setM6name("message");
            System.out.println("Fmenuit(avant insertion)=" + fmenuit1);
            fmenuitDAO.insert(fmenuit1);
            fmenuitDAO.closeInsertPreparedStatement();
            System.out.println("Fmenuit(après insertion)=" + fmenuit1);
            System.out.println("Rangée(s) affectée(s)=" + fmenuitDAO.getNbAffectedRow());

// Essai de mise à jour
            fmenuitDAO.setUpdatePreparedStatement();
            fmenuit1.setM6extname(fmenuit1.getM6extname() + " totolito");
            fmenuitDAO.update(fmenuit1);
            System.out.println("Fmenuit(après mise à jour)=" + fmenuit1);
            System.out.println("Rangée(s) affectée(s)=" + fmenuitDAO.getNbAffectedRow());
            fmenuitDAO.closeUpdatePreparedStatement();

// Essai de lecture
            fmenuitDAO.filterById(fmenuit1.getM6num());
            System.out.println("  SelectStatement=" + fmenuitDAO.getSelectStatement());
            fmenuitDAO.setSelectPreparedStatement();
            i = 0;
            while ((fmenuit = fmenuitDAO.select()) != null) {
                i++;
                System.out.println("Fmenuit(" + i + ")=" + fmenuit);
                System.out.println("  getM6num()=" + fmenuit.getM6num());
                System.out.println("  getM6extname()=" + fmenuit.getM6extname());
                System.out.println("  getM6name()=" + fmenuit.getM6name());
            }
            fmenuitDAO.closeSelectPreparedStatement();

// Essai de suppression
            System.out.println("Suppression de : " + fmenuit1);
            fmenuitDAO.setDeletePreparedStatement();
            fmenuitDAO.delete(fmenuit1.getM6num());
            fmenuitDAO.closeDeletePreparedStatement();
            System.out.println("Rangée(s) affectée(s)=" + fmenuitDAO.getNbAffectedRow());

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
