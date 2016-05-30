package expcalls;

import java.io.IOException;
import java.sql.*;
import liba2pi.ApplicationProperties;
import liba2pi.DBManager;
import liba2pi.DBServer;
import liba2pi.DBServerException;
import agency.PaternDAO;
import expcalls.Ticket_0000.EtatTicket;

/**
 * Classe qui décrit les méthodes pour accéder aux tables fcalls ou f99calls au
 * travers de JDBC.
 *
 * @author Thierry Baribaud.
 * @version Mai 2016.
 */
public class FcallsDAO extends PaternDAO {

    private String MyTable = "fcalls";

    /**
     * Constructeur de la classe FcallsDAO.
     *
     * @param MyConnection connexion à la base de données courante.
     * @param cnum identifiant de l'appel,
     * @param MyArgs paramètres de la ligne de commande,
     * @param MyEtatTicket indique si l'on travaille sur les tickets en cours ou
     * archivés.
     * @throws ClassNotFoundException en cas de classse non trouvée.
     * @throws java.sql.SQLException en cas d'erreur SQL.
     */
    public FcallsDAO(Connection MyConnection, int cnum, GetArgs MyArgs, EtatTicket MyEtatTicket)
            throws ClassNotFoundException, SQLException {

        StringBuffer Stmt;

        MyTable = EtatTicket.EN_COURS.equals(MyEtatTicket)?"fcalls":"f99calls";
        
        int cunum = MyArgs.getUnum();
        Timestamp BegDate = MyArgs.getBegDate();
        int idxBegDate = 0;
        Timestamp EndDate = MyArgs.getEndDate();
        int idxEndDate = 0;
        int idx = 0;

        this.MyConnection = MyConnection;

        Stmt = new StringBuffer("select cnum, cunum, cname, ctel, caddress, caddress2,"
                + " caccess, cposcode, city, csympt,"
                + " cnumber4, cc6num, cdate, ctime, cdate2, ctime2,"
                + " corp, cnumber5, cseqno, cquery1, cquery2, czone, cage, ctype,"
                + " ctnum, cnote"
                + " from " + MyTable
                + " where (cinternal = 0 or cinternal is null)"
                + " and (ctest = 0 or ctest is null)");
        if (cnum > 0) {
            Stmt.append(" and cnum = ").append(cnum);
        }
        if (cunum > 0) {
            Stmt.append(" and cunum = ").append(cunum);
        }
        if (BegDate != null) {
            Stmt.append(" and cdate >= ?");
            idx++;
            idxBegDate = idx;
        }
        if (EndDate != null) {
            Stmt.append(" and cdate < ?");
            idx++;
            idxEndDate = idx;
        }
        Stmt.append(" order by cnum;");
//        System.out.println(Stmt);
        setReadStatement(Stmt.toString());
        setReadPreparedStatement();
        if (idxBegDate > 0) {
            ReadPreparedStatement.setTimestamp(idxBegDate, BegDate);
        }
        if (idxEndDate > 0) {
            ReadPreparedStatement.setTimestamp(idxEndDate, EndDate);
        }
        setReadResultSet();

        setUpdateStatement("update " + MyTable
                + " set cunum=?, cname=?, ctel=?, caddress=?, caddress2=?,"
                + " caccess=?, cposcode=?, city=?, csympt=?,"
                + " cnumber4=?, cc6num=?, cdate=?, ctime=?, cdate2=?, ctime2=?,"
                + " corp=?, cnumber5=?, cseqno=?, cquery1=?, cquery2=?,"
                + " czone=?, cage=?, ctype=?, ctnum=?, cnote=?"
                + " where cnum=?;");
        setUpdatePreparedStatement();

        setInsertStatement("insert into " + MyTable
                + " (cunum, cname, ctel, caddress, caddress2,"
                + " caccess, cposcode, city, csympt,"
                + " cnumber4, cc6num, cdate, ctime, cdate2, ctime2,"
                + " corp, cnumber5, cseqno, cquery1, cquery2, czone,"
                + " cage, ctype, ctnum, cnote)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + " ?,?,?,?,?,?,?,?,?,?);");
        setInsertPreparedStatement();

        setDeleteStatement("delete from " + MyTable + " where cnum=?;");
        setDeletePreparedStatement();
    }

    /**
     * Selectionne un appel.
     *
     * @return l'appel sélectionné.
     */
    @Override
    public Fcalls select() {
        Fcalls MyFcalls = null;

        try {
            if (ReadResultSet.next()) {
                MyFcalls = new Fcalls();
                MyFcalls.setCnum(ReadResultSet.getInt("cnum"));
                MyFcalls.setCunum(ReadResultSet.getInt("cunum"));
                MyFcalls.setCname(ReadResultSet.getString("cname"));
                MyFcalls.setCtel(ReadResultSet.getString("ctel"));
                MyFcalls.setCaddress(ReadResultSet.getString("caddress"));
                MyFcalls.setCaddress2(ReadResultSet.getString("caddress2"));
                MyFcalls.setCaccess(ReadResultSet.getString("caccess"));
                MyFcalls.setCposcode(ReadResultSet.getString("cposcode"));
                MyFcalls.setCity(ReadResultSet.getString("city"));
                MyFcalls.setCsympt(ReadResultSet.getString("csympt"));
                MyFcalls.setCnumber4(ReadResultSet.getString("cnumber4"));
                MyFcalls.setCc6num(ReadResultSet.getInt("cc6num"));
                MyFcalls.setCdate(ReadResultSet.getTimestamp("cdate"));
                MyFcalls.setCtime(ReadResultSet.getString("ctime"));
                MyFcalls.setCdate2(ReadResultSet.getTimestamp("cdate2"));
                MyFcalls.setCtime2(ReadResultSet.getString("ctime2"));
                MyFcalls.setCorp(ReadResultSet.getString("corp"));
                MyFcalls.setCnumber5(ReadResultSet.getString("cnumber5"));
                MyFcalls.setCseqno(ReadResultSet.getInt("cseqno"));
                MyFcalls.setCquery1(ReadResultSet.getInt("cquery1"));
                MyFcalls.setCquery2(ReadResultSet.getInt("cquery2"));
                MyFcalls.setCzone(ReadResultSet.getInt("czone"));
                MyFcalls.setCage(ReadResultSet.getInt("cage"));
                MyFcalls.setCtype(ReadResultSet.getInt("ctype"));
                MyFcalls.setCtnum(ReadResultSet.getInt("ctnum"));
                MyFcalls.setCnote(ReadResultSet.getInt("cnote"));
            } else {
                System.out.println("Lecture de " + MyTable + " terminée");
            }
        } catch (SQLException MyException) {
            System.out.println("Erreur en lecture de " + MyTable + " "
                    + MyException.getMessage());
        }
        return MyFcalls;
    }

    /**
     * Met à jour un appel.
     *
     * @param MyFcalls appel à mettre à jour.
     */
    public void update(Fcalls MyFcalls) {
        try {
            UpdatePreparedStatement.setInt(1, MyFcalls.getCunum());
            UpdatePreparedStatement.setString(2, MyFcalls.getCname());
            UpdatePreparedStatement.setString(3, MyFcalls.getCtel());
            UpdatePreparedStatement.setString(4, MyFcalls.getCaddress());
            UpdatePreparedStatement.setString(5, MyFcalls.getCaddress2());
            UpdatePreparedStatement.setString(6, MyFcalls.getCaccess());
            UpdatePreparedStatement.setString(7, MyFcalls.getCposcode());
            UpdatePreparedStatement.setString(8, MyFcalls.getCity());
            UpdatePreparedStatement.setString(9, MyFcalls.getCsympt());
            UpdatePreparedStatement.setString(10, MyFcalls.getCnumber4());
            UpdatePreparedStatement.setInt(13, MyFcalls.getCc6num());
            UpdatePreparedStatement.setTimestamp(14, MyFcalls.getCdate());
            UpdatePreparedStatement.setString(11, MyFcalls.getCtime());
            UpdatePreparedStatement.setTimestamp(15, MyFcalls.getCdate2());
            UpdatePreparedStatement.setString(12, MyFcalls.getCtime2());
            UpdatePreparedStatement.setString(16, MyFcalls.getCorp());
            UpdatePreparedStatement.setString(17, MyFcalls.getCnumber5());
            UpdatePreparedStatement.setInt(18, MyFcalls.getCseqno());
            UpdatePreparedStatement.setInt(19, MyFcalls.getCquery1());
            UpdatePreparedStatement.setInt(20, MyFcalls.getCquery2());
            UpdatePreparedStatement.setInt(21, MyFcalls.getCzone());
            UpdatePreparedStatement.setInt(22, MyFcalls.getCage());
            UpdatePreparedStatement.setInt(23, MyFcalls.getCtype());
            UpdatePreparedStatement.setInt(24, MyFcalls.getCtnum());
            UpdatePreparedStatement.setInt(25, MyFcalls.getCnote());
            UpdatePreparedStatement.setInt(26, MyFcalls.getCnum());
            setNbAffectedRow(UpdatePreparedStatement.executeUpdate());
            if (getNbAffectedRow() == 0) {
                System.out.println("Impossible de mettre à jour " + MyTable);
            }
        } catch (SQLException MyException) {
            System.out.println("Erreur lors de la mise à jour de " + MyTable
                    + " " + MyException.getMessage());
        }
    }

    /**
     * Supprime définitivement un appel.
     *
     * @param cnum identiant de l'appel à supprimer.
     */
    @Override
    public void delete(int cnum) {
        try {
            DeletePreparedStatement.setInt(1, cnum);
            setNbAffectedRow(DeletePreparedStatement.executeUpdate());
            if (getNbAffectedRow() == 0) {
                System.out.println("Impossible de détruire un appel dans " + MyTable);
            }
        } catch (SQLException MyException) {
            System.out.println("Erreur lors de la suppression d'un appel dans "
                    + MyTable + " " + MyException.getMessage());
        }
    }

    /**
     * Ajoute un appel dans la table fcalls ou f99calls.
     *
     * @param MyFcalls appel à ajouter à la table fcalls ou f99calls.
     */
    public void insert(Fcalls MyFcalls) {
        ResultSet MyKeyResultSet = null;

        try {
            System.out.println("cname=" + MyFcalls.getCname());
            InsertPreparedStatement.setInt(1, MyFcalls.getCunum());
            InsertPreparedStatement.setString(2, MyFcalls.getCname());
            InsertPreparedStatement.setString(3, MyFcalls.getCtel());
            InsertPreparedStatement.setString(4, MyFcalls.getCaddress());
            InsertPreparedStatement.setString(5, MyFcalls.getCaddress2());
            InsertPreparedStatement.setString(6, MyFcalls.getCaccess());
            InsertPreparedStatement.setString(7, MyFcalls.getCposcode());
            InsertPreparedStatement.setString(8, MyFcalls.getCity());
            InsertPreparedStatement.setString(9, MyFcalls.getCsympt());
            InsertPreparedStatement.setString(10, MyFcalls.getCnumber4());
            InsertPreparedStatement.setInt(13, MyFcalls.getCc6num());
            InsertPreparedStatement.setTimestamp(14, MyFcalls.getCdate());
            InsertPreparedStatement.setString(11, MyFcalls.getCtime());
            InsertPreparedStatement.setTimestamp(15, MyFcalls.getCdate2());
            InsertPreparedStatement.setString(12, MyFcalls.getCtime2());
            InsertPreparedStatement.setString(16, MyFcalls.getCorp());
            InsertPreparedStatement.setString(17, MyFcalls.getCnumber5());
            InsertPreparedStatement.setInt(18, MyFcalls.getCseqno());
            InsertPreparedStatement.setInt(19, MyFcalls.getCquery1());
            InsertPreparedStatement.setInt(20, MyFcalls.getCquery2());
            InsertPreparedStatement.setInt(21, MyFcalls.getCzone());
            InsertPreparedStatement.setInt(22, MyFcalls.getCage());
            InsertPreparedStatement.setInt(23, MyFcalls.getCtype());
            InsertPreparedStatement.setInt(24, MyFcalls.getCtnum());
            InsertPreparedStatement.setInt(25, MyFcalls.getCnote());
            setNbAffectedRow(InsertPreparedStatement.executeUpdate());
            if (getNbAffectedRow() == 0) {
                System.out.println("Impossible d'ajouter un appel dans " + MyTable);
            } else {
                MyKeyResultSet = InsertPreparedStatement.getGeneratedKeys();
                if (MyKeyResultSet.next()) {
                    MyFcalls.setCnum((int) MyKeyResultSet.getInt(1));
                }
            }
            MyKeyResultSet.close();
        } catch (SQLException MyException) {
            System.out.println("Erreur lors de l'insertion d'un appel dans "
                    + MyTable + " " + MyException.getMessage());
        }
    }

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
            MyFcallsDAO = new FcallsDAO(MyDBManager.getConnection(), cnum, MyArgs, EtatTicket.EN_COURS);
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
            MyFcallsDAO = new FcallsDAO(MyDBManager.getConnection(), cnum, MyArgs, EtatTicket.EN_COURS);
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

    @Override
    public void update(Object MyObject) {
        throw new UnsupportedOperationException("Non supporté actuellement"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Object MyObject) {
        throw new UnsupportedOperationException("Non supporté actuellement"); //To change body of generated methods, choose Tools | Templates.
    }
}
