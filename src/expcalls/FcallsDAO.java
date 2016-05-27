package expcalls;

import java.io.IOException;
import java.sql.*;
import liba2pi.ApplicationProperties;
import liba2pi.DBManager;
import liba2pi.DBServer;
import liba2pi.DBServerException;
import agency.PaternDAO;

/**
 * Class that describes the ways to access table fcalls through JDBC.
 * @author Thierry Baribaud.
 * @version October 2015.
 */
public class FcallsDAO extends PaternDAO {

  /**
   * Class constructor.
   * @param MyConnection an active connection to a database.
   * @param cnum call's ID,
   * @param cunum customer's ID,
   * @throws ClassNotFoundException, SQLException.
   * @throws java.sql.SQLException
   */
  public FcallsDAO(Connection MyConnection, int cnum, int cunum)
    throws ClassNotFoundException, SQLException {

    StringBuffer Stmt;

    this.MyConnection = MyConnection;

    Stmt = new StringBuffer("select cnum, cunum, cname, ctel, caddress, caddress2," +
             " caccess, cposcode, city, csympt," +
             " cnumber4, cc6num, cdate, ctime, cdate2, ctime2," +
             " corp, cnumber5" +
             " from fcalls" +
             " where (cinternal = 0 or cinternal is null)" +
             " and (ctest = 0 or ctest is null)");
    if (cnum > 0) {
      Stmt.append(" and cnum = ").append(cnum);
    }
    if (cunum > 0) {
      Stmt.append(" and cunum = ").append(cunum);
      }
    Stmt.append(" order by cnum;");
    setReadStatement(Stmt.toString());
    setReadPreparedStatement();
    setReadResultSet();

    setUpdateStatement("update fcalls" +
                       " set cunum=?, cname=?, ctel=?, caddress=?, caddress2=?," +
                       " caccess=?, cposcode=?, city=?, csympt=?," +
                       " cnumber4=?, cc6num=?, cdate=?, ctime=?, cdate2=?, ctime2=?," +
                       " corp=?, cnumber5=?" +
                       " where cnum=?;");
    setUpdatePreparedStatement();
  
    setInsertStatement("insert into fcalls" +
                       " (cunum, cname, ctel, caddress, caddress2," +
                       " caccess, cposcode, city, csympt," +
                       " cnumber4, cc6num, cdate, ctime, cdate2, ctime2)" +
                       " corp, cnumber5" +
                       " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                       " ?,?");
    setInsertPreparedStatement();

    setDeleteStatement("delete from fcalls where cnum=?;");
    setDeletePreparedStatement();
    }

  /**
   * Select a record.
    * @return the selected calls.
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
        }
      else {
        System.out.println("No more record in fcalls");
        }
      }
    catch (SQLException MyException) {
      System.out.println("Problem reading fcalls record " + 
                         MyException.getMessage());
      }
    return MyFcalls;
    }

  /**
   * Update a record.
   * @param MyFcalls calls to update
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
      UpdatePreparedStatement.setInt(18, MyFcalls.getCc6num());
      setNbAffectedRow(UpdatePreparedStatement.executeUpdate());
      if (getNbAffectedRow() == 0) {
        System.out.println("Failed to update data into fcalls");
        }
      }
    catch (SQLException MyException) {
      System.out.println("Problem reading fcalls record " +
                         MyException.getMessage());
      }
    }

  /**
   * Delete a record.
     * @param cnum calls identifier
   */
  @Override
  public void delete(int cnum) {
    try {
      DeletePreparedStatement.setInt(1, cnum);
      setNbAffectedRow(DeletePreparedStatement.executeUpdate());
      if (getNbAffectedRow() == 0) {
        System.out.println("Failed to delete data from fcalls");
        }
      }
    catch (SQLException e) {
      System.out.println("Problem deleting fcalls record " +
                         e.getMessage());
      }
    }
  /**
   * Insert a record.
   * @param MyFcalls calls to insert in database
   */
  public void insert(Fcalls MyFcalls) {
    ResultSet MyKeyResultSet = null;

    try {
      System.out.println("cname="+MyFcalls.getCname());
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
      setNbAffectedRow(InsertPreparedStatement.executeUpdate());
      if (getNbAffectedRow() == 0) {
        System.out.println("Failed to insert data into fcalls");
        }
      else {
        MyKeyResultSet = InsertPreparedStatement.getGeneratedKeys();
        if (MyKeyResultSet.next()) {
            MyFcalls.setCnum((int) MyKeyResultSet.getInt(1));
          }
        }
        MyKeyResultSet.close();
      }
    catch (SQLException MyException) {
      System.out.println("Problem inserting fcalls record " +
                         MyException.getMessage());
      }
    }

  /**
   * Main method to test FcallsDAO class.
   * @param Args command line arguments.
   * First argument must be the type of Database server : dev/pre-prod/prod.
   * Second argument must be the name of the application properties file to load.
   */
  public static void main(String[] Args) {
    ApplicationProperties MyApplicationProperties;
    DBServer MyDBServer;
    DBManager MyDBManager;
    FcallsDAO MyFcallsDAO;
    Fcalls MyFcalls1;
    Fcalls MyFcalls;
    long i;
    int cnum = 0;
    int cunum = 552;

    if (Args.length != 2) {
      System.out.println("Usage : java FcallsDAO server-type filename");
      System.exit(0);
    }

    try {
      MyApplicationProperties = new ApplicationProperties(Args[1]);

      MyDBServer = new DBServer(Args[0], MyApplicationProperties);
      MyDBManager = new DBManager(MyDBServer);

// Essai insertion
      MyFcallsDAO = new FcallsDAO(MyDBManager.getConnection(), cnum, cunum);
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
      System.out.println("Fcalls(before insert)=" + MyFcalls1);
      MyFcallsDAO.insert(MyFcalls1);
      System.out.println("Fcalls(after insert)=" + MyFcalls1);
      System.out.println("Affected row(s)=" + MyFcallsDAO.getNbAffectedRow());

// Essai mise Ã  jour
      MyFcalls1.setCaddress2(MyFcalls1.getCaddress2() + ",utopia@free.fr");
      MyFcallsDAO.update(MyFcalls1);
      System.out.println("Fcalls(after update)=" + MyFcalls1);
      System.out.println("Affected row(s)=" + MyFcallsDAO.getNbAffectedRow());
      MyFcallsDAO.close();

// Essai lecture
      MyFcallsDAO = new FcallsDAO(MyDBManager.getConnection(), cnum, cunum);
      i = 0;
      while ((MyFcalls = MyFcallsDAO.select()) != null) {
        i++;
        System.out.println("Fcalls("+i+")=" + MyFcalls);
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
        }

// Essai suppression
      System.out.println("Deleting : " + MyFcalls1);
      MyFcallsDAO.delete(MyFcalls1.getCnum());
      System.out.println("Affected row(s)=" + MyFcallsDAO.getNbAffectedRow());

      }
    catch (IOException MyException) {
      System.out.println("Problem while creating FcallsDAO " + MyException);
    }
    catch (DBServerException MyException) {
      System.out.println("Problem while creating FcallsDAO " + MyException);
    }
    catch (ClassNotFoundException MyException) {
      System.out.println("Problem while creating FcallsDAO " + MyException);
    }
    catch (SQLException MyException) {
      System.out.println("Problem while creating FcallsDAO " + MyException);
    }
  }

    @Override
    public void update(Object MyObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Object MyObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
