package expcalls;

import bdd.Fcomplmt;
import bdd.FcomplmtDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
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
public class TstFcomplmtDAO {

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
        FcomplmtDAO MyFcomplmtDAO;
        Fcomplmt MyFcomplmt1;
        Fcomplmt MyFcomplmt;
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
            MyFcomplmtDAO = new FcomplmtDAO(MyDBManager.getConnection());
            MyFcomplmtDAO.setInsertPreparedStatement();
            MyFcomplmt1 = new Fcomplmt();
            MyFcomplmt1.setC6int2(99999);
            MyFcomplmt1.setC6alpha1("terra incognita");
            MyFcomplmt1.setC6alpha2("utopia");
            MyFcomplmt1.setC6name("UTOPIA");
            MyFcomplmt1.setC6access("porte B");
            MyFcomplmt1.setC6city("12, rue des rèves");
            MyFcomplmt1.setC6tel("bâtiment B");
            MyFcomplmt1.setC6alpha3("92400");
            MyFcomplmt1.setC6alpha4("UTOPIA CITY");
            MyFcomplmt1.setC6alpha5("01.01.01.01.01");
            MyFcomplmt1.setC6alpha6("02.02.02.02.02");
            MyFcomplmt1.setC6alpha7("03.03.03.03.03");
            MyFcomplmt1.setC6int1(1);
            MyFcomplmt1.setC6date(new Timestamp(new java.util.Date().getTime()));
            MyFcomplmt1.setC6date1(Timestamp.valueOf("2050-12-31 23:59:59.0"));
            MyFcomplmt1.setC6int3(3);
            MyFcomplmt1.setC6int4(4);
            MyFcomplmt1.setC6onum(36);
            MyFcomplmt1.setC6corp("UTOPIA DISTRIBUTION");
            MyFcomplmt1.setC6address("14, rue de la réalité");
            MyFcomplmt1.setC6address2("porte 1");
            MyFcomplmt1.setC6poscode("92130");
            System.out.println("Fcomplmt(avant insertion)=" + MyFcomplmt1);
            MyFcomplmtDAO.insert(MyFcomplmt1);
            MyFcomplmtDAO.closeInsertPreparedStatement();
            System.out.println("Fcomplmt(après insertion)=" + MyFcomplmt1);
            System.out.println("Rangée(s) affectée(s)=" + MyFcomplmtDAO.getNbAffectedRow());

// Essai de mise à jour
            MyFcomplmtDAO.setUpdatePreparedStatement();
            MyFcomplmt1.setC6access(MyFcomplmt1.getC6access() + ", escalier 9");
            MyFcomplmtDAO.update(MyFcomplmt1);
            System.out.println("Fcomplmt(après mise à jour)=" + MyFcomplmt1);
            System.out.println("Rangée(s) affectée(s)=" + MyFcomplmtDAO.getNbAffectedRow());
            MyFcomplmtDAO.closeUpdatePreparedStatement();

// Essai de lecture
            MyFcomplmtDAO.filterById(MyFcomplmt1.getC6num());
            System.out.println("  SelectStatement=" + MyFcomplmtDAO.getSelectStatement());
            MyFcomplmtDAO.setSelectPreparedStatement();
            i = 0;
            while ((MyFcomplmt = MyFcomplmtDAO.select()) != null) {
                i++;
                System.out.println("Fcomplmt(" + i + ")=" + MyFcomplmt);
                System.out.println("  getC6num()=" + MyFcomplmt.getC6num());
                System.out.println("  getC6int2()=" + MyFcomplmt.getC6int2());
                System.out.println("  getC6alpha1()=" + MyFcomplmt.getC6alpha1());
                System.out.println("  getC6alpha2()=" + MyFcomplmt.getC6alpha2());
                System.out.println("  getC6name()=" + MyFcomplmt.getC6name());
                System.out.println("  getC6access()=" + MyFcomplmt.getC6access());
                System.out.println("  getC6city()=" + MyFcomplmt.getC6city());
                System.out.println("  getC6tel()=" + MyFcomplmt.getC6tel());
                System.out.println("  getC6alpha3()=" + MyFcomplmt.getC6alpha3());
                System.out.println("  getC6alpha4()=" + MyFcomplmt.getC6alpha4());
                System.out.println("  getC6alpha5()=" + MyFcomplmt.getC6alpha5());
                System.out.println("  getC6alpha6()=" + MyFcomplmt.getC6alpha6());
                System.out.println("  getC6alpha7()=" + MyFcomplmt.getC6alpha7());
                System.out.println("  getC6int1()=" + MyFcomplmt.getC6int1());
                System.out.println("  getC6date()=" + MyFcomplmt.getC6date());
                System.out.println("  getC6date1()=" + MyFcomplmt.getC6date1());
                System.out.println("  getC6int3()=" + MyFcomplmt.getC6int3());
                System.out.println("  getC6int4()=" + MyFcomplmt.getC6int4());
                System.out.println("  getC6onum()=" + MyFcomplmt.getC6onum());
                System.out.println("  getC6corp()=" + MyFcomplmt.getC6corp());
                System.out.println("  getC6address()=" + MyFcomplmt.getC6address());
                System.out.println("  getC6address2()=" + MyFcomplmt.getC6address2());
                System.out.println("  getC6poscode()=" + MyFcomplmt.getC6poscode());
            }
            MyFcomplmtDAO.closeSelectPreparedStatement();

// Essai de suppression
            System.out.println("Suppression de : " + MyFcomplmt1);
            MyFcomplmtDAO.setDeletePreparedStatement();
            MyFcomplmtDAO.delete(MyFcomplmt1.getC6num());
            MyFcomplmtDAO.closeDeletePreparedStatement();
            System.out.println("Rangée(s) affectée(s)=" + MyFcomplmtDAO.getNbAffectedRow());

        } catch (IOException MyException) {
            System.out.println("Problem while reading database properties " + MyException);
        } catch (DBServerException MyException) {
            System.out.println("Problem while reading DBserver properties " + MyException);
        } catch (ClassNotFoundException MyException) {
            System.out.println("Problem while creating FcomplmtDAO " + MyException);
        } catch (SQLException MyException) {
            System.out.println("Problem while creating FcomplmtDAO " + MyException);
        }
    }
}
