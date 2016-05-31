package expcalls;

import java.io.IOException;
import java.sql.*;
import liba2pi.ApplicationProperties;
import liba2pi.DBManager;
import liba2pi.DBServer;
import liba2pi.DBServerException;
import agency.PaternDAO;

/**
 * Classe qui décrit les méthodes pour accéder à la table ftoubib au travers de
 * JDBC.
 *
 * @author Thierry Baribaud.
 * @version Mai 2016.
 */
public class FtoubibDAO extends PaternDAO {

    /**
     * Constructeur de la classe FtoubibDAO.
     *
     * @param MyConnection connexion à la base de données courante.
     * @param tnum identifiant de l'intervenant,
     * @param tunum identifiant du service d'urgence,
     * @throws ClassNotFoundException en cas de classse non trouvée.
     * @throws java.sql.SQLException en cas d'erreur SQL.
     */
    public FtoubibDAO(Connection MyConnection, int tnum, int tunum)
            throws ClassNotFoundException, SQLException {

        StringBuffer Stmt;

        this.MyConnection = MyConnection;

        Stmt = new StringBuffer("select tnum, tunum, ta6num,"
                + " tlname, tfname, tabbname, tel,"
                + " tel2, telper, tel4, tel5, tel6,"
                + " telfax, temail, taddress, taddress2, tcomment"
                + " from ftoubib"
                + " where tnum > 0");
        if (tnum > 0) {
            Stmt.append(" and tnum = ").append(tnum);
        }
        if (tunum > 0) {
            Stmt.append(" and tunum = ").append(tunum);
        }
        Stmt.append(" order by tnum;");
//        System.out.println(Stmt);
        setReadStatement(Stmt.toString());
        setReadPreparedStatement();
        setReadResultSet();

        setUpdateStatement("update ftoubib"
                + " set tunum=?, ta6num=?, tlname=?, tfname=?, tabbname=?, tel=?,"
                + " tel2=?, telper=?, tel4=?, tel5=?, tel6=?,"
                + " telfax=?, temail=?, taddress=?, taddress2=?, tcomment=?"
                + " where tnum=?;");
        setUpdatePreparedStatement();

        setInsertStatement("insert into ftoubib"
                + " (tunum, ta6num, tlname, tfname, tabbname, tel,"
                + " tel2, telper, tel4, tel5, tel6,"
                + " telfax, temail, taddress, taddress2, tcomment)"
                + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
        setInsertPreparedStatement();

        setDeleteStatement("delete from ftoubib where tnum=?;");
        setDeletePreparedStatement();
    }

    /**
     * Selectionne un intervenant.
     *
     * @return l'intervenant sélectionné.
     */
    @Override
    public Ftoubib select() {
        Ftoubib MyFtoubib = null;

        try {
            if (ReadResultSet.next()) {
                MyFtoubib = new Ftoubib();
                MyFtoubib.setTnum(ReadResultSet.getInt("tnum"));
                MyFtoubib.setTunum(ReadResultSet.getInt("tunum"));
                MyFtoubib.setTa6num(ReadResultSet.getInt("ta6num"));
                MyFtoubib.setTlname(ReadResultSet.getString("tlname"));
                MyFtoubib.setTfname(ReadResultSet.getString("tfname"));
                MyFtoubib.setTabbname(ReadResultSet.getString("tabbname"));
                MyFtoubib.setTel(ReadResultSet.getString("tel"));
                MyFtoubib.setTel2(ReadResultSet.getString("tel2"));
                MyFtoubib.setTelper(ReadResultSet.getString("telper"));
                MyFtoubib.setTel4(ReadResultSet.getString("tel4"));
                MyFtoubib.setTel5(ReadResultSet.getString("tel5"));
                MyFtoubib.setTel6(ReadResultSet.getString("tel6"));
                MyFtoubib.setTelfax(ReadResultSet.getString("telfax"));
                MyFtoubib.setTemail(ReadResultSet.getString("temail"));
                MyFtoubib.setTaddress(ReadResultSet.getString("taddress"));
                MyFtoubib.setTaddress2(ReadResultSet.getString("taddress2"));
                MyFtoubib.setTcomment(ReadResultSet.getString("tcomment"));
            } else {
                System.out.println("Lecture de ftoubib terminée");
            }
        } catch (SQLException MyException) {
            System.out.println("Erreur en lecture de ftoubib "
                    + MyException.getMessage());
        }
        return MyFtoubib;
    }

    /**
     * Met à jour un intervenant.
     *
     * @param MyFtoubib intervenant à mettre à jour.
     */
    public void update(Ftoubib MyFtoubib) {
        try {
            UpdatePreparedStatement.setInt(1, MyFtoubib.getTunum());
            UpdatePreparedStatement.setInt(2, MyFtoubib.getTa6num());
            UpdatePreparedStatement.setString(3, MyFtoubib.getTlname());
            UpdatePreparedStatement.setString(4, MyFtoubib.getTfname());
            UpdatePreparedStatement.setString(5, MyFtoubib.getTabbname());
            UpdatePreparedStatement.setString(6, MyFtoubib.getTel());
            UpdatePreparedStatement.setString(7, MyFtoubib.getTel2());
            UpdatePreparedStatement.setString(8, MyFtoubib.getTelper());
            UpdatePreparedStatement.setString(9, MyFtoubib.getTel4());
            UpdatePreparedStatement.setString(10, MyFtoubib.getTel5());
            UpdatePreparedStatement.setString(11, MyFtoubib.getTel6());
            UpdatePreparedStatement.setString(12, MyFtoubib.getTelfax());
            UpdatePreparedStatement.setString(13, MyFtoubib.getTemail());
            UpdatePreparedStatement.setString(14, MyFtoubib.getTaddress());
            UpdatePreparedStatement.setString(15, MyFtoubib.getTaddress2());
            UpdatePreparedStatement.setString(16, MyFtoubib.getTcomment());
            UpdatePreparedStatement.setInt(17, MyFtoubib.getTnum());
            setNbAffectedRow(UpdatePreparedStatement.executeUpdate());
            if (getNbAffectedRow() == 0) {
                System.out.println("Impossible de mettre à jour ftoubib");
            }
        } catch (SQLException MyException) {
            System.out.println("Erreur lors de la mise à jour de ftoubib"
                    + " " + MyException.getMessage());
        }
    }

    /**
     * Supprime définitivement un intervenant.
     *
     * @param tnum identiant de l'intervenant à supprimer.
     */
    @Override
    public void delete(int tnum) {
        try {
            DeletePreparedStatement.setInt(1, tnum);
            setNbAffectedRow(DeletePreparedStatement.executeUpdate());
            if (getNbAffectedRow() == 0) {
                System.out.println("Impossible de détruire un intervenant dans ftoubib");
            }
        } catch (SQLException MyException) {
            System.out.println("Erreur lors de la suppression d'un intervenant dans "
                    + "ftoubib " + MyException.getMessage());
        }
    }

    /**
     * Ajoute un intervenant dans la table ftoubib.
     *
     * @param MyFtoubib intervenant à ajouter à la table ftoubib.
     */
    public void insert(Ftoubib MyFtoubib) {
        ResultSet MyKeyResultSet = null;

        try {
            System.out.println("tlname=" + MyFtoubib.getTlname());
            InsertPreparedStatement.setInt(1, MyFtoubib.getTunum());
            InsertPreparedStatement.setInt(2, MyFtoubib.getTa6num());
            InsertPreparedStatement.setString(3, MyFtoubib.getTlname());
            InsertPreparedStatement.setString(4, MyFtoubib.getTfname());
            InsertPreparedStatement.setString(5, MyFtoubib.getTabbname());
            InsertPreparedStatement.setString(6, MyFtoubib.getTel());
            InsertPreparedStatement.setString(7, MyFtoubib.getTel2());
            InsertPreparedStatement.setString(8, MyFtoubib.getTelper());
            InsertPreparedStatement.setString(9, MyFtoubib.getTel4());
            InsertPreparedStatement.setString(10, MyFtoubib.getTel5());
            InsertPreparedStatement.setString(11, MyFtoubib.getTel6());
            InsertPreparedStatement.setString(12, MyFtoubib.getTelfax());
            InsertPreparedStatement.setString(13, MyFtoubib.getTemail());
            InsertPreparedStatement.setString(14, MyFtoubib.getTaddress());
            InsertPreparedStatement.setString(15, MyFtoubib.getTaddress2());
            InsertPreparedStatement.setString(16, MyFtoubib.getTcomment());
            setNbAffectedRow(InsertPreparedStatement.executeUpdate());
            if (getNbAffectedRow() == 0) {
                System.out.println("Impossible d'ajouter un intervenant dans ftoubib");
            } else {
                MyKeyResultSet = InsertPreparedStatement.getGeneratedKeys();
                if (MyKeyResultSet.next()) {
                    MyFtoubib.setTnum((int) MyKeyResultSet.getInt(1));
                }
            }
            MyKeyResultSet.close();
        } catch (SQLException MyException) {
            System.out.println("Erreur lors de l'insertion d'un intervenant dans "
                    + "ftoubib " + MyException.getMessage());
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
        FtoubibDAO MyFtoubibDAO;
        Ftoubib MyFtoubib1;
        Ftoubib MyFtoubib;
        long i;
        int tnum = 0;

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
            MyFtoubibDAO = new FtoubibDAO(MyDBManager.getConnection(), tnum, MyArgs.getUnum());
            MyFtoubib1 = new Ftoubib();
            MyFtoubib1.setTunum(MyArgs.getUnum());
            MyFtoubib1.setTlname("polo");
            MyFtoubib1.setTfname("marco");
            MyFtoubib1.setTabbname("MARCOPOLO");
            MyFtoubib1.setTel("01.01.01.01.01");
            MyFtoubib1.setTel2("02.02.02.02.02");
            MyFtoubib1.setTelper("03.03.03.03.03");
            MyFtoubib1.setTel4("04.04.04.04.04");
            MyFtoubib1.setTel5("05.05.05.05.05");
            MyFtoubib1.setTel6("06.06.06.06.06");
            MyFtoubib1.setTelfax("01.01.01.01.11");
            MyFtoubib1.setTa6num(1);
            MyFtoubib1.setTemail("marco.polo@gmail.com");
            MyFtoubib1.setTaddress("je fais mon");
            MyFtoubib1.setTaddress2("tour du monde");
            MyFtoubib1.setTcomment("ne pas déranger");
            System.out.println("Ftoubib(avant insertion)=" + MyFtoubib1);
            MyFtoubibDAO.insert(MyFtoubib1);
            System.out.println("Ftoubib(après insertion)=" + MyFtoubib1);
            System.out.println("Rangée(s) affectée(s)=" + MyFtoubibDAO.getNbAffectedRow());

// Essai mise à jour
            MyFtoubib1.setTemail(MyFtoubib1.getTemail() + ",utopia@free.fr");
            MyFtoubibDAO.update(MyFtoubib1);
            System.out.println("Ftoubib(après mise-à-jour)=" + MyFtoubib1);
            System.out.println("Rangée(s) affectée(s)=" + MyFtoubibDAO.getNbAffectedRow());
            MyFtoubibDAO.close();

// Essai lecture
            MyFtoubibDAO = new FtoubibDAO(MyDBManager.getConnection(), tnum, MyArgs.getUnum());
            i = 0;
            while ((MyFtoubib = MyFtoubibDAO.select()) != null) {
                i++;
                System.out.println("Ftoubib(" + i + ")=" + MyFtoubib);
                System.out.println("  getTnum()=" + MyFtoubib.getTnum());
                System.out.println("  getTunum()=" + MyFtoubib.getTunum());
                System.out.println("  getTa6num()=" + MyFtoubib.getTa6num());
                System.out.println("  getTlname()=" + MyFtoubib.getTlname());
                System.out.println("  getTfname()=" + MyFtoubib.getTfname());
                System.out.println("  getTabbname()=" + MyFtoubib.getTabbname());
                System.out.println("  getTel()=" + MyFtoubib.getTel());
                System.out.println("  getTel2()=" + MyFtoubib.getTel2());
                System.out.println("  getTelper()=" + MyFtoubib.getTelper());
                System.out.println("  getTel4()=" + MyFtoubib.getTel4());
                System.out.println("  getTel5()=" + MyFtoubib.getTel5());
                System.out.println("  getTel6()=" + MyFtoubib.getTel6());
                System.out.println("  getTelfax()=" + MyFtoubib.getTelfax());
                System.out.println("  getTemail()=" + MyFtoubib.getTemail());
                System.out.println("  getTaddress()=" + MyFtoubib.getTaddress());
                System.out.println("  getTaddress2()=" + MyFtoubib.getTaddress2());
                System.out.println("  getTcomment()=" + MyFtoubib.getTcomment());
            }

// Essai suppression
            System.out.println("Suppression de : " + MyFtoubib1);
            MyFtoubibDAO.delete(MyFtoubib1.getTnum());
            System.out.println("Rangée(s) affectée(s)=" + MyFtoubibDAO.getNbAffectedRow());

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
