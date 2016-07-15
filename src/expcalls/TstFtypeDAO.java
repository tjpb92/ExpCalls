package expcalls;

import bdd.Ftype;
import bdd.FtypeDAO;
import java.io.IOException;
import java.sql.SQLException;
import utils.ApplicationProperties;
import utils.DBManager;
import utils.DBServer;
import utils.DBServerException;

/**
 * TstFtypeDAO programme permettant de tester le pattern DAO pour Ftype.
 *
 * @version Juillet 2016
 * @author Thierry Baribaud
 */
public class TstFtypeDAO {

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
        FtypeDAO MyFtypeDAO;
        Ftype MyFtype1;
        Ftype MyFtype;
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
            MyFtypeDAO = new FtypeDAO(MyDBManager.getConnection());
            MyFtypeDAO.setInsertPreparedStatement();
            MyFtype1 = new Ftype();
            MyFtype1.setTtnum(0);
            MyFtype1.setTtunum(MyArgs.getUnum());
            MyFtype1.setTtextname("message sans suite");
            MyFtype1.setTtypename("message");
            MyFtype1.setTtype(99);
            System.out.println("Ftype(avant insertion)=" + MyFtype1);
            MyFtypeDAO.insert(MyFtype1);
            MyFtypeDAO.closeInsertPreparedStatement();
            System.out.println("Ftype(après insertion)=" + MyFtype1);
            System.out.println("Rangée(s) affectée(s)=" + MyFtypeDAO.getNbAffectedRow());

// Essai de mise à jour
            MyFtypeDAO.setUpdatePreparedStatement();
            MyFtype1.setTtypename(MyFtype1.getTtypename() + " simple");
            MyFtypeDAO.update(MyFtype1);
            System.out.println("Ftype(après mise-à-jour)=" + MyFtype1);
            System.out.println("Rangée(s) affectée(s)=" + MyFtypeDAO.getNbAffectedRow());
            MyFtypeDAO.closeUpdatePreparedStatement();

// Essai de lecture
            MyFtypeDAO.filterByName(MyArgs.getUnum(), "m");
            System.out.println("  SelectStatement=" + MyFtypeDAO.getSelectStatement());
            MyFtypeDAO.setSelectPreparedStatement();
            i = 0;
            while ((MyFtype = MyFtypeDAO.select()) != null) {
                i++;
                System.out.println("Ftype(" + i + ")=" + MyFtype);
            }
            MyFtypeDAO.closeSelectPreparedStatement();

// Essai de suppression
            System.out.println("Suppression de : " + MyFtype1);
            MyFtypeDAO.setDeletePreparedStatement();
            MyFtypeDAO.delete(MyFtype1.getTtnum());
            MyFtypeDAO.closeDeletePreparedStatement();
            System.out.println("Rangée(s) affectée(s)=" + MyFtypeDAO.getNbAffectedRow());

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
