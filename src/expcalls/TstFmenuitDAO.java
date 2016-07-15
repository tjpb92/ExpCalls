package expcalls;

import bdd.Fmenuit;
import bdd.FmenuitDAO;
import java.io.IOException;
import java.sql.SQLException;
import utils.ApplicationProperties;
import utils.DBManager;
import utils.DBServer;
import utils.DBServerException;

/**
 * TstFmenuitDAO programme permettant de tester le pattern DAO pour Fmenuit.
 *
 * @version Juin 2016
 * @author Thierry Baribaud
 */
public class TstFmenuitDAO {
    
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
        FmenuitDAO MyFmenuitDAO;
        Fmenuit MyFmenuit1;
        Fmenuit MyFmenuit;
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
            MyFmenuitDAO = new FmenuitDAO(MyDBManager.getConnection());
            MyFmenuitDAO.setInsertPreparedStatement();
            MyFmenuit1 = new Fmenuit();
            MyFmenuit1.setM6extname("message client");
            MyFmenuit1.setM6name("message");
            System.out.println("Fmenuit(avant insertion)=" + MyFmenuit1);
            MyFmenuitDAO.insert(MyFmenuit1);
            MyFmenuitDAO.closeInsertPreparedStatement();
            System.out.println("Fmenuit(après insertion)=" + MyFmenuit1);
            System.out.println("Rangée(s) affectée(s)=" + MyFmenuitDAO.getNbAffectedRow());

// Essai de mise à jour
            MyFmenuitDAO.setUpdatePreparedStatement();
            MyFmenuit1.setM6extname(MyFmenuit1.getM6extname() + " totolito");
            MyFmenuitDAO.update(MyFmenuit1);
            System.out.println("Fmenuit(après mise à jour)=" + MyFmenuit1);
            System.out.println("Rangée(s) affectée(s)=" + MyFmenuitDAO.getNbAffectedRow());
            MyFmenuitDAO.closeUpdatePreparedStatement();

// Essai de lecture
            MyFmenuitDAO.filterById(MyFmenuit1.getM6num());
            System.out.println("  SelectStatement=" + MyFmenuitDAO.getSelectStatement());
            MyFmenuitDAO.setSelectPreparedStatement();
            i = 0;
            while ((MyFmenuit = MyFmenuitDAO.select()) != null) {
                i++;
                System.out.println("Fmenuit(" + i + ")=" + MyFmenuit);
                System.out.println("  getM6num()=" + MyFmenuit.getM6num());
                System.out.println("  getM6extname()=" + MyFmenuit.getM6extname());
                System.out.println("  getM6name()=" + MyFmenuit.getM6name());
            }
            MyFmenuitDAO.closeSelectPreparedStatement();

// Essai de suppression
            System.out.println("Suppression de : " + MyFmenuit1);
            MyFmenuitDAO.setDeletePreparedStatement();
            MyFmenuitDAO.delete(MyFmenuit1.getM6num());
            MyFmenuitDAO.closeDeletePreparedStatement();
            System.out.println("Rangée(s) affectée(s)=" + MyFmenuitDAO.getNbAffectedRow());

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
