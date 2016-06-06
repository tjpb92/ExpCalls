package expcalls;

import bdd.EtatTicket;
import bdd.Fcalls;
import bdd.FcallsDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import utils.ApplicationProperties;
import utils.DBManager;
import utils.DBServer;
import utils.DBServerException;

/**
 * Programme pour tester la classe FcallsDAO.
 * @author Thierry Baribaud
 * @version Juin 2016
 */
public class tstFcallsDAO {

    /**
     * Les arguments en ligne de commande permettent de changer le mode de
     * fonctionnement. Voir GetArgs pour plus de détails.
     *
     * @param Args arguments de la ligne de commande.
     * @throws expcalls.GetArgsException en cas de problème sur les paramètres.
     */
    public static void main(String[] Args) throws GetArgsException {
        GetArgs MyArgs;
        ApplicationProperties MyApplicationProperties;
        DBServer MyDBServer;
        DBManager MyDBManager;
        FcallsDAO MyFcallsDAO;
        Fcalls MyFcalls1;
        Fcalls MyFcalls;
        long i;
        int cnum = 0;

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

// Essai insertion
            MyFcallsDAO = new FcallsDAO(MyDBManager.getConnection(), cnum, 
                    MyArgs.getUnum(), MyArgs.getBegDate(), MyArgs.getEndDate(), 
                    EtatTicket.EN_COURS);
            MyFcalls1 = new Fcalls();
            MyFcalls1.setCunum(99999);
            MyFcalls1.setCname("terra incognita");
            MyFcalls1.setCtel("01.01.01.01.01");
            MyFcalls1.setCaddress("UTOPIA");
            MyFcalls1.setCaddress2("utopia@gmail.com");
            MyFcalls1.setCaccess("12, rue des rèves");
            MyFcalls1.setCposcode("92400");
            MyFcalls1.setCity("UTOPIA CITY");
            MyFcalls1.setCsympt("appel de test");
            MyFcalls1.setCnumber4("12345");
            MyFcalls1.setCc6num(1);
            MyFcalls1.setCdate(new Timestamp(new java.util.Date().getTime()));
            MyFcalls1.setCtime("14:00:00");
            MyFcalls1.setCdate2(Timestamp.valueOf("2050-12-31 23:59:59.0"));
            MyFcalls1.setCtime2("15:00:00");
            MyFcalls1.setCorp("anstel");
            MyFcalls1.setCnumber5("5678");
            MyFcalls1.setCseqno(9123);
            MyFcalls1.setCquery1(1);
            MyFcalls1.setCquery2(2);
            MyFcalls1.setCzone(92);
            MyFcalls1.setCage(12);
            MyFcalls1.setCtype(1);
            MyFcalls1.setCtnum(1234);
            MyFcalls1.setCnote(0);
            System.out.println("Fcalls(before insert)=" + MyFcalls1);
            MyFcallsDAO.insert(MyFcalls1);
            System.out.println("Fcalls(after insert)=" + MyFcalls1);
            System.out.println("Affected row(s)=" + MyFcallsDAO.getNbAffectedRow());

// Essai mise à jour
            MyFcalls1.setCaddress2(MyFcalls1.getCaddress2() + ",utopia@free.fr");
            MyFcallsDAO.update(MyFcalls1);
            System.out.println("Fcalls(after update)=" + MyFcalls1);
            System.out.println("Affected row(s)=" + MyFcallsDAO.getNbAffectedRow());
            MyFcallsDAO.close();

// Essai lecture
            MyFcallsDAO = new FcallsDAO(MyDBManager.getConnection(), cnum, 
                    MyArgs.getUnum(), MyArgs.getBegDate(), MyArgs.getEndDate(), 
                    EtatTicket.EN_COURS);
            i = 0;
            while ((MyFcalls = MyFcallsDAO.select()) != null) {
                i++;
                System.out.println("Fcalls(" + i + ")=" + MyFcalls);
                System.out.println("  getCnum()=" + MyFcalls.getCnum());
                System.out.println("  getCunum()=" + MyFcalls.getCunum());
                System.out.println("  getCname()=" + MyFcalls.getCname());
                System.out.println("  getCtel()=" + MyFcalls.getCtel());
                System.out.println("  getCaddress()=" + MyFcalls.getCaddress());
                System.out.println("  getCaddress2()=" + MyFcalls.getCaddress2());
                System.out.println("  getCaccess()=" + MyFcalls.getCaccess());
                System.out.println("  getCposcode()=" + MyFcalls.getCposcode());
                System.out.println("  getCity()=" + MyFcalls.getCity());
                System.out.println("  getCsympt()=" + MyFcalls.getCsympt());
                System.out.println("  getCnumber4()=" + MyFcalls.getCnumber4());
                System.out.println("  getCc6num()=" + MyFcalls.getCc6num());
                System.out.println("  getCdate()=" + MyFcalls.getCdate());
                System.out.println("  getCtime()=" + MyFcalls.getCtime());
                System.out.println("  getCdate2()=" + MyFcalls.getCdate2());
                System.out.println("  getCtime2()=" + MyFcalls.getCtime2());
                System.out.println("  getCorp()=" + MyFcalls.getCorp());
                System.out.println("  getCnumber5()=" + MyFcalls.getCnumber5());
                System.out.println("  getCseqno()=" + MyFcalls.getCseqno());
                System.out.println("  getCquery1()=" + MyFcalls.getCquery1());
                System.out.println("  getCquery2()=" + MyFcalls.getCquery2());
                System.out.println("  getCzone()=" + MyFcalls.getCzone());
                System.out.println("  getCage()=" + MyFcalls.getCage());
                System.out.println("  getCtype()=" + MyFcalls.getCtype());
                System.out.println("  getCtnum()=" + MyFcalls.getCtnum());
                System.out.println("  getCnote()=" + MyFcalls.getCnote());
            }

// Essai suppression
            System.out.println("Deleting : " + MyFcalls1);
            MyFcallsDAO.delete(MyFcalls1.getCnum());
            System.out.println("Affected row(s)=" + MyFcallsDAO.getNbAffectedRow());

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
