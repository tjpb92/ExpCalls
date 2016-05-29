package expcalls;

import agency.PaternDAO;
import java.io.IOException;
import java.sql.*;
import liba2pi.ApplicationProperties;
import liba2pi.DBManager;
import liba2pi.DBServer;
import liba2pi.DBServerException;

/**
 * Classe qui décrit les méthodes pour accéder à la table fmenuit avec JDBC.
 *
 * @author Thierry Baribaud.
 * @version Mai 2015.
 */
public class FmenuitDAO extends PaternDAO {

    /**
     * Constructeur de la classe FmenuitDAO.
     *
     * @param MyConnection connexion à la base de données courante.
     * @param myM6num identifiant interne de l'item du menu,
     * @throws ClassNotFoundException en cas de classse non trouvée.
     * @throws java.sql.SQLException en cas d'erreur SQL.
     */
    public FmenuitDAO(Connection MyConnection, int myM6num)
            throws ClassNotFoundException, SQLException {

        StringBuffer Stmt;

        this.MyConnection = MyConnection;

        Stmt = new StringBuffer("select m6num, m6extname, m6name"
                + " from fmenuit");

        if (myM6num > 0) {
            Stmt.append(" where m6num = ").append(myM6num);
        } else {
            Stmt.append(" where m6num >0");
        }
        Stmt.append(" order by m6num;");
        setReadStatement(Stmt.toString());
        setReadPreparedStatement();
        setReadResultSet();

        setUpdateStatement("update fmenuit"
                + " set m6extname=?, m6name=?"
                + " where m6num=?;");
        setUpdatePreparedStatement();

        setInsertStatement("insert into fmenuit"
                + " (m6extname, m6name)"
                + " values(?, ?);");
        setInsertPreparedStatement();

        setDeleteStatement("delete from fmenuit"
                + " where m6num=?;");
        setDeletePreparedStatement();
    }

    /**
     * Selectionne un item de menu.
     *
     * @return l'item de menu sélectionné.
     */
    @Override
    public Fmenuit select() {
        Fmenuit MyFmenuit = null;

        try {
            if (ReadResultSet.next()) {
                MyFmenuit = new Fmenuit();
                MyFmenuit.setM6num(ReadResultSet.getInt("m6num"));
                MyFmenuit.setM6extname(ReadResultSet.getString("m6extname"));
                MyFmenuit.setM6name(ReadResultSet.getString("m6name"));
            } else {
                System.out.println("Lecture de fmenuit terminée");
            }
        } catch (SQLException MyException) {
            System.out.println("Erreur en lecture de fmenuit "
                    + MyException.getMessage());
        }
        return MyFmenuit;
    }

    /**
     * Met à jour un item de menu.
     *
     * @param MyFmenuit item de menu à mettre à jour.
     */
    public void update(Fmenuit MyFmenuit) {
        try {
            UpdatePreparedStatement.setString(1, MyFmenuit.getM6extname());
            UpdatePreparedStatement.setString(2, MyFmenuit.getM6name());
            UpdatePreparedStatement.setInt(3, MyFmenuit.getM6num());
            setNbAffectedRow(UpdatePreparedStatement.executeUpdate());
            if (getNbAffectedRow() == 0) {
                System.out.println("Impossible de mettre à jour fmenuit");
            }
        } catch (SQLException MyException) {
            System.out.println("Erreur lors de la mise à jour de fmenuit "
                    + MyException.getMessage());
        }
    }

    /**
     * Supprime définitivement un item de menu.
     *
     * @param m6num identiant de l'item à supprimer.
     */
    @Override
    public void delete(int m6num) {
        try {
            DeletePreparedStatement.setInt(1, m6num);
            setNbAffectedRow(DeletePreparedStatement.executeUpdate());
            if (getNbAffectedRow() == 0) {
                System.out.println("Impossible de détruire un item dans fmenuit");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression d'un item dans fmenuit "
                    + e.getMessage());
        }
    }

    /**
     * Ajoute un item de menu dans la table fmenuit.
     *
     * @param MyFmenuit item à ajouter à la table fmenuit.
     */
    public void insert(Fmenuit MyFmenuit) {
        ResultSet MyKeyResultSet = null;

        try {
            System.out.println("m6name=" + MyFmenuit.getM6name());
            InsertPreparedStatement.setString(1, MyFmenuit.getM6extname());
            InsertPreparedStatement.setString(2, MyFmenuit.getM6name());
            setNbAffectedRow(InsertPreparedStatement.executeUpdate());
            if (getNbAffectedRow() == 0) {
                System.out.println("Impossible d'ajouter un item dans fmenuit");
            } else {
                MyKeyResultSet = InsertPreparedStatement.getGeneratedKeys();
                if (MyKeyResultSet.next()) {
                    MyFmenuit.setM6num((int) MyKeyResultSet.getInt(1));
                }
            }
            MyKeyResultSet.close();
        } catch (SQLException MyException) {
            System.out.println("Erreur lors de l'insertion d'un item dans fmenuit "
                    + MyException.getMessage());
        }
    }

    /**
     * <p>
     * Programme principal pour tester la classe FmenuitDAO.</p>
     * <ul><li>Le premier paramètre désigne le serveur de base de données auquel
     * se connecter : dev/pre-prod/prod (obligatoire). </li>
     * <li>Le second paramètre est le nom du fichier des propriétés à charger
     * (obligatoire).</li></ul>
     *
     * @param Args paramètres en ligne de commande.
     */
    public static void main(String[] Args) {
        ApplicationProperties MyApplicationProperties;
        DBServer MyDBServer;
        DBManager MyDBManager;
        FmenuitDAO MyFmenuitDAO;
        Fmenuit MyFmenuit1;
        Fmenuit MyFmenuit;
        long i;
        int myM6num = 552;

        if (Args.length != 2) {
            System.out.println("Usage : java FmenuitDAO server-type filename");
            System.exit(0);
        }

        try {
            MyApplicationProperties = new ApplicationProperties(Args[1]);

            MyDBServer = new DBServer(Args[0], MyApplicationProperties);
            MyDBManager = new DBManager(MyDBServer);

// Essai insertion
            MyFmenuitDAO = new FmenuitDAO(MyDBManager.getConnection(), myM6num);
            MyFmenuit1 = new Fmenuit();
            MyFmenuit1.setM6extname("message client");
            MyFmenuit1.setM6name("message");
            System.out.println("Fmenuit(avant insertion)=" + MyFmenuit1);
            MyFmenuitDAO.insert(MyFmenuit1);
            System.out.println("Fmenuit(après insertion)=" + MyFmenuit1);
            System.out.println("Rangée(s) affectée(s)=" + MyFmenuitDAO.getNbAffectedRow());

// Essai mise à jour
            MyFmenuit1.setM6extname(MyFmenuit1.getM6extname() + " totolito");
            MyFmenuitDAO.update(MyFmenuit1);
            System.out.println("Fmenuit(après mise à jour)=" + MyFmenuit1);
            System.out.println("Rangée(s) affectée(s)=" + MyFmenuitDAO.getNbAffectedRow());
            MyFmenuitDAO.close();

// Essai lecture
            MyFmenuitDAO = new FmenuitDAO(MyDBManager.getConnection(), myM6num);
            i = 0;
            while ((MyFmenuit = MyFmenuitDAO.select()) != null) {
                i++;
                System.out.println("Fmenuit(" + i + ")=" + MyFmenuit);
                System.out.println("  getM6num()=" + MyFmenuit.getM6num());
                System.out.println("  getM6extname()=" + MyFmenuit.getM6extname());
                System.out.println("  getM6name()=" + MyFmenuit.getM6name());
            }

// Essai suppression
            System.out.println("Suppression de : " + MyFmenuit1);
            MyFmenuitDAO.delete(MyFmenuit1.getM6num());
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

    @Override
    public void update(Object MyObject) {
        throw new UnsupportedOperationException("Non supporté actuellement"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Object MyObject) {
        throw new UnsupportedOperationException("Non supporté actuellement"); //To change body of generated methods, choose Tools | Templates.
    }
}
