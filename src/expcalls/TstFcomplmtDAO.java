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
import utils.GetArgsException;

/**
 * TstFmenuitDAO programme permettant de tester le pattern DAO pour Fmenuit.
 *
 * @version 0.51
 * @author Thierry Baribaud
 */
public class TstFcomplmtDAO {

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
        FcomplmtDAO fcomplmtDAO;
        Fcomplmt fcomplmt1;
        Fcomplmt fcomplmt;
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
            fcomplmtDAO = new FcomplmtDAO(dBManager.getConnection());
            fcomplmtDAO.setInsertPreparedStatement();
            fcomplmt1 = new Fcomplmt();
            fcomplmt1.setC6int2(99999);
            fcomplmt1.setC6alpha1("terra incognita");
            fcomplmt1.setC6alpha2("utopia");
            fcomplmt1.setC6name("UTOPIA");
            fcomplmt1.setC6access("porte B");
            fcomplmt1.setC6city("12, rue des rèves");
            fcomplmt1.setC6tel("bâtiment B");
            fcomplmt1.setC6alpha3("92400");
            fcomplmt1.setC6alpha4("UTOPIA CITY");
            fcomplmt1.setC6alpha5("01.01.01.01.01");
            fcomplmt1.setC6alpha6("02.02.02.02.02");
            fcomplmt1.setC6alpha7("03.03.03.03.03");
            fcomplmt1.setC6int1(1);
            fcomplmt1.setC6date(new Timestamp(new java.util.Date().getTime()));
            fcomplmt1.setC6date1(Timestamp.valueOf("2050-12-31 23:59:59.0"));
            fcomplmt1.setC6int3(3);
            fcomplmt1.setC6int4(4);
            fcomplmt1.setC6onum(36);
            fcomplmt1.setC6corp("UTOPIA DISTRIBUTION");
            fcomplmt1.setC6address("14, rue de la réalité");
            fcomplmt1.setC6address2("porte 1");
            fcomplmt1.setC6poscode("92130");
            System.out.println("Fcomplmt(avant insertion)=" + fcomplmt1);
            fcomplmtDAO.insert(fcomplmt1);
            fcomplmtDAO.closeInsertPreparedStatement();
            System.out.println("Fcomplmt(après insertion)=" + fcomplmt1);
            System.out.println("Rangée(s) affectée(s)=" + fcomplmtDAO.getNbAffectedRow());

// Essai de mise à jour
            fcomplmtDAO.setUpdatePreparedStatement();
            fcomplmt1.setC6access(fcomplmt1.getC6access() + ", escalier 9");
            fcomplmtDAO.update(fcomplmt1);
            System.out.println("Fcomplmt(après mise à jour)=" + fcomplmt1);
            System.out.println("Rangée(s) affectée(s)=" + fcomplmtDAO.getNbAffectedRow());
            fcomplmtDAO.closeUpdatePreparedStatement();

// Essai de lecture
            fcomplmtDAO.filterById(fcomplmt1.getC6num());
            System.out.println("  SelectStatement=" + fcomplmtDAO.getSelectStatement());
            fcomplmtDAO.setSelectPreparedStatement();
            i = 0;
            while ((fcomplmt = fcomplmtDAO.select()) != null) {
                i++;
                System.out.println("Fcomplmt(" + i + ")=" + fcomplmt);
                System.out.println("  getC6num()=" + fcomplmt.getC6num());
                System.out.println("  getC6int2()=" + fcomplmt.getC6int2());
                System.out.println("  getC6alpha1()=" + fcomplmt.getC6alpha1());
                System.out.println("  getC6alpha2()=" + fcomplmt.getC6alpha2());
                System.out.println("  getC6name()=" + fcomplmt.getC6name());
                System.out.println("  getC6access()=" + fcomplmt.getC6access());
                System.out.println("  getC6city()=" + fcomplmt.getC6city());
                System.out.println("  getC6tel()=" + fcomplmt.getC6tel());
                System.out.println("  getC6alpha3()=" + fcomplmt.getC6alpha3());
                System.out.println("  getC6alpha4()=" + fcomplmt.getC6alpha4());
                System.out.println("  getC6alpha5()=" + fcomplmt.getC6alpha5());
                System.out.println("  getC6alpha6()=" + fcomplmt.getC6alpha6());
                System.out.println("  getC6alpha7()=" + fcomplmt.getC6alpha7());
                System.out.println("  getC6int1()=" + fcomplmt.getC6int1());
                System.out.println("  getC6date()=" + fcomplmt.getC6date());
                System.out.println("  getC6date1()=" + fcomplmt.getC6date1());
                System.out.println("  getC6int3()=" + fcomplmt.getC6int3());
                System.out.println("  getC6int4()=" + fcomplmt.getC6int4());
                System.out.println("  getC6onum()=" + fcomplmt.getC6onum());
                System.out.println("  getC6corp()=" + fcomplmt.getC6corp());
                System.out.println("  getC6address()=" + fcomplmt.getC6address());
                System.out.println("  getC6address2()=" + fcomplmt.getC6address2());
                System.out.println("  getC6poscode()=" + fcomplmt.getC6poscode());
            }
            fcomplmtDAO.closeSelectPreparedStatement();

// Essai de suppression
            System.out.println("Suppression de : " + fcomplmt1);
            fcomplmtDAO.setDeletePreparedStatement();
            fcomplmtDAO.delete(fcomplmt1.getC6num());
            fcomplmtDAO.closeDeletePreparedStatement();
            System.out.println("Rangée(s) affectée(s)=" + fcomplmtDAO.getNbAffectedRow());

        } catch (IOException exception) {
            System.out.println("Problem while reading database properties " + exception);
        } catch (DBServerException exception) {
            System.out.println("Problem while reading DBserver properties " + exception);
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println("Problem while creating FcomplmtDAO " + exception);
        }
    }
}
