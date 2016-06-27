package expcalls;

import bdd.Furgent;
import bdd.FurgentDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Classe servant à stocker les paramètres pour exporter les appels.
 *
 * @version Juin 2016
 * @author Thierry Baribaud
 */
public class ExpCallsParams {

    /**
     * Connexion à la base de données courante.
     */
    private Connection MyConnection;

    /**
     * Identifiant du client.
     */
    private int unum;

    /**
     * Nom du client.
     */
    private String Uname;

    /**
     * Nom abrégé du client.
     */
    private String Uabbname;

    /**
     * Date de début de l'export à 0h.
     */
    private Timestamp BegDate;

    /**
     * Date de fin de l'export à 0h.
     */
    private Timestamp EndDate;

    /**
     * Nom du fichier de sortie au format XML.
     */
    private String XMLFilename = DetermineXMLFilename(0);

    /**
     * Nom du fichier contenant le schéma XML.
     */
    private String XSDFilename = DetermineXSDFilename(0);

    /**
     * Nom du fichier de sortie au format Excel.
     */
    private String ExcelFilename = DetermineExcelFilename(0);

    public ExpCallsParams(Connection MyConnection, GetArgs MyArgs) throws ClassNotFoundException, SQLException {
        FurgentDAO MyFurgentDAO;
        Furgent MyFurgent;

        setMyConnection(MyConnection);

        setUnum(MyArgs.getUnum());
        MyFurgentDAO = new FurgentDAO(MyConnection);
        MyFurgentDAO.filterById(unum);
        MyFurgentDAO.setSelectPreparedStatement();
        MyFurgent = MyFurgentDAO.select();
        if (MyFurgent != null) {
            setUname(MyFurgent.getUname());
            setUabbname(MyFurgent.getUabbname());
        } else {
            setUname("Inconnu");
            setUabbname("INCONNU");
        }
        MyFurgentDAO.closeSelectPreparedStatement();

        setBegDate(MyArgs.getBegDate());
        setEndDate(MyArgs.getEndDate());
        setXMLFilename(DetermineXMLFilename(unum));
        setXSDFilename(DetermineXSDFilename(unum));
        setExcelFilename(DetermineExcelFilename(unum));
    }

    /**
     * @return XMLFilename le nom du fichier de sortie au format XML.
     */
    public String getXMLFilename() {
        return XMLFilename;
    }

    /**
     * @param unum définit la référence client.
     */
    public void setUnum(int unum) {
        this.unum = unum;
    }

    /**
     * @param BegDate définit la date de début de l'export à 0h.
     */
    public void setBegDate(Timestamp BegDate) {
        this.BegDate = BegDate;
    }

    /**
     * @param EndDate définit la date de fin de l'export à 0h.
     */
    public void setEndDate(Timestamp EndDate) {
        this.EndDate = EndDate;
    }

    /**
     * @return Unum l'identifiant du client.
     */
    public int getUnum() {
        return (unum);
    }

    /**
     * @return BegDate la date de début de l'export à 0h.
     */
    public Timestamp getBegDate() {
        return (BegDate);
    }

    /**
     * @return EndDate la date de fin de l'export à 0h.
     */
    public Timestamp getEndDate() {
        return (EndDate);
    }

    /**
     * Méthode qui détermine la racine du nom d'un fichier par rapport à
     * l'identifiant du client.
     *
     * @param unum identifiant du client.
     * @return Filename la racine du nom du fichier.
     */
    private String DefaultFilename(int unum) {
        String Filename = "tickets_0000";

        switch (unum) {
            case 572:
                Filename = "tickets_0572";
                break;
        }
        return (Filename);
    }

    /**
     * Méthode qui détermine le nom du fichier de sortie au format XML.
     *
     * @param unum identifiant du client.
     * @return Filename la racine du nom du fichier.
     */
    private String DetermineXMLFilename(int unum) {
        return (DefaultFilename(unum) + ".xml");
    }

    /**
     * Méthode qui détermine le nom du fichier contenant le schéma XML.
     *
     * @param unum identifiant du client.
     * @return Filename la racine du nom du fichier.
     */
    private String DetermineXSDFilename(int unum) {
        return (DefaultFilename(unum) + ".xsd");
    }

    /**
     * Méthode qui détermine le nom du fichier de sortie au format Excel.
     *
     * @param unum identifiant du client.
     * @return Filename la racine du nom du fichier.
     */
    private String DetermineExcelFilename(int unum) {
        return (DefaultFilename(unum) + ".xlsx");
    }

    /**
     * @param XMLFilename définit le nom du fichier de sortie au format XML.
     */
    public void setXMLFilename(String XMLFilename) {
        this.XMLFilename = XMLFilename;
    }

    /**
     * @return XSDFilename le nom du fichier contenant le schéma XML.
     */
    public String getXSDFilename() {
        return XSDFilename;
    }

    /**
     * @param XSDFilename définit le nom du fichier contenant le schéma XML.
     */
    public void setXSDFilename(String XSDFilename) {
        this.XSDFilename = XSDFilename;
    }

    /**
     * @return ExcelFilename le nom du fichier de sortie au format Excel.
     */
    public String getExcelFilename() {
        return ExcelFilename;
    }

    /**
     * @param ExcelFilename définit le nom du fichier de sortie au format Excel.
     */
    public void setExcelFilename(String ExcelFilename) {
        this.ExcelFilename = ExcelFilename;
    }

    /**
     * @return MyConnection connection à la base de données locale.
     */
    public Connection getMyConnection() {
        return MyConnection;
    }

    /**
     * @param MyConnection définit la connection à la base de données locale.
     */
    public void setMyConnection(Connection MyConnection) {
        this.MyConnection = MyConnection;
    }

    /**
     * @return Uname le nom du client.
     */
    public String getUname() {
        return Uname;
    }

    /**
     * @param Uname définit le nom du client.
     */
    public void setUname(String Uname) {
        this.Uname = Uname;
    }

    /**
     * @return Uabbname le nom abrégé du client.
     */
    public String getUabbname() {
        return Uabbname;
    }

    /**
     * @param Uabbname définit le nom abrégé du client.
     */
    public void setUabbname(String Uabbname) {
        this.Uabbname = Uabbname;
    }

}
