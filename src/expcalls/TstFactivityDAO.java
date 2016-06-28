package expcalls;

import bdd.Factivity;
import bdd.FactivityDAO;
import java.io.IOException;
import java.sql.SQLException;
import utils.ApplicationProperties;
import utils.DBManager;
import utils.DBServer;
import utils.DBServerException;

/**
 * TstFactivityDAO programme permettant de tester le pattern DAO pour Factivity.
 *
 * @version Juin 2016
 * @author Thierry Baribaud
 */
public class TstFactivityDAO {

    /**
     * Les arguments en ligne de commande permettent de changer le mode de
     * fonctionnement. Voir GetArgs pour plus de détails.
     *
     * @param Args arguments de la ligne de commande.
     * @throws GetArgsException en cas de problème sur les paramètres.
     */
    public static void main(String[] Args) throws GetArgsException {
        GetArgs MyArgs;
        ApplicationProperties MyApplicationProperties;
        DBServer MyDBServer;
        DBManager MyDBManager;
        FactivityDAO MyFactivityDAO;
        Factivity MyFactivity1;
        Factivity MyFactivity;
        long i;

        try {
            System.out.println("Récupération des arguments en ligne de commande ...");
            MyArgs = new GetArgs(Args);
            System.out.println(MyArgs);

            System.out.println("Lecture du fichier de paramètres ...");
            MyApplicationProperties = new ApplicationProperties("MyDatabases.prop");

            System.out.println("Lecture des paramètres de base de données ...");
            MyDBServer = new DBServer(MyArgs.getSourceServer(), MyApplicationProperties);
            System.out.println("  " + MyDBServer);

            MyDBManager = new DBManager(MyDBServer);

// Essai d'insertion
            MyFactivityDAO = new FactivityDAO(MyDBManager.getConnection());
            MyFactivityDAO.setInsertPreparedStatement();
            MyFactivity1 = new Factivity();
            MyFactivity1.setA4abbname("PLBZNG");
            MyFactivity1.setA4name("plombier zingueur");
            System.out.println("Factivity(avant insertion)=" + MyFactivity1);
            MyFactivityDAO.insert(MyFactivity1);
            MyFactivityDAO.closeInsertPreparedStatement();
            System.out.println("Factivity(après insertion)=" + MyFactivity1);
            System.out.println("Rangée(s) affectée(s)=" + MyFactivityDAO.getNbAffectedRow());

// Essai de mise à jour
            MyFactivityDAO.setUpdatePreparedStatement();
            MyFactivity1.setA4name(MyFactivity1.getA4name() + " couvreur");
            MyFactivityDAO.update(MyFactivity1);
            System.out.println("Factivity(après mise à jour)=" + MyFactivity1);
            System.out.println("Rangée(s) affectée(s)=" + MyFactivityDAO.getNbAffectedRow());
            MyFactivityDAO.closeUpdatePreparedStatement();

// Essai de lecture
            MyFactivityDAO.filterById(MyFactivity1.getA4num());
            System.out.println("  SelectStatement=" + MyFactivityDAO.getSelectStatement());
            MyFactivityDAO.setSelectPreparedStatement();
            i = 0;
            while ((MyFactivity = MyFactivityDAO.select()) != null) {
                i++;
                System.out.println("Factivity(" + i + ")=" + MyFactivity);
                System.out.println("  getA4num()=" + MyFactivity.getA4num());
                System.out.println("  getA4abbname()=" + MyFactivity.getA4abbname());
                System.out.println("  getA4name()=" + MyFactivity.getA4name());
            }
            MyFactivityDAO.closeSelectPreparedStatement();

// Essai de suppression
            System.out.println("Suppression de : " + MyFactivity1);
            MyFactivityDAO.setDeletePreparedStatement();
            MyFactivityDAO.delete(MyFactivity1.getA4num());
            MyFactivityDAO.closeDeletePreparedStatement();
            System.out.println("Rangée(s) affectée(s)=" + MyFactivityDAO.getNbAffectedRow());

        } catch (IOException MyException) {
            System.out.println("Erreur en lecture du fichier des propriétés " + MyException);
        } catch (DBServerException MyException) {
            System.out.println("Erreur avec le serveur de base de données " + MyException);
        } catch (ClassNotFoundException MyException) {
            System.out.println("Erreur classe non trouvée " + MyException);
        } catch (SQLException MyException) {
            System.out.println("Erreur SQL rencontrée " + MyException);
        }
    }

}
