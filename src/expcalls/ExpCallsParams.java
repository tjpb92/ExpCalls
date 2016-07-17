package expcalls;

import bdd.Furgent;
import bdd.FurgentDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;

/**
 * Classe servant à stocker les paramètres pour exporter les appels.
 *
 * @version Juillet 2016
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
     * Modèle de rapport XML.
     */
    private ModeleDeRapport MyModeleDeRapport;
    
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
        DecimalFormat MyFormatter = new DecimalFormat("0000");

        return ("tickets_" + MyFormatter.format(unum));
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
        String Filename = "tickets_0000";

        switch (unum) {
            case 105:
//                setMyModeleDeRapport(ModeleDeRapport.VES);
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 125:
//                setMyModeleDeRapport(ModeleDeRapport.VES);
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 341:
                setMyModeleDeRapport(ModeleDeRapport.SAU);
                break;
            case 513:
                setMyModeleDeRapport(ModeleDeRapport.CAR);
                break;
            case 515:
                setMyModeleDeRapport(ModeleDeRapport.SAU);
                break;
            case 552:
                setMyModeleDeRapport(ModeleDeRapport.SOL);
                break;
            case 555:
                setMyModeleDeRapport(ModeleDeRapport.GEO);
                break;
            case 557:
                setMyModeleDeRapport(ModeleDeRapport.SAU);
                break;
            case 567:
                setMyModeleDeRapport(ModeleDeRapport.CEG);
                break;
            case 572:
                setMyModeleDeRapport(ModeleDeRapport.NEX);
                break;
            case 573:
                setMyModeleDeRapport(ModeleDeRapport.PHI);
                break;
            case 582:
                setMyModeleDeRapport(ModeleDeRapport.ENE);
                break;
            case 592:
//                setMyModeleDeRapport(ModeleDeRapport.VES);
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 600:
                setMyModeleDeRapport(ModeleDeRapport.PRI);
                break;
            case 602:
                setMyModeleDeRapport(ModeleDeRapport.SAU);
                break;
            case 603:
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 604:
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 605:
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 606:
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 607:
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 608:
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 609:
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 610:
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 611:
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 612:
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 613:
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 614:
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 615:
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 616:
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 617:
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 620:
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 626:
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 627:
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 629:
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 630:
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 632:
                setMyModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 634:
                setMyModeleDeRapport(ModeleDeRapport.NEX);
                break;
            case 635:
                setMyModeleDeRapport(ModeleDeRapport.BOU);
                break;
            default:
                setMyModeleDeRapport(ModeleDeRapport.STD);
                break;
        }
        return ("tickets_" + MyModeleDeRapport + ".xsd");
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

    /**
     * @return MyModeleDeRapport le modèle de rappport XML.
     */
    public ModeleDeRapport getMyModeleDeRapport() {
        return MyModeleDeRapport;
    }

    /**
     * @param MyModeleDeRapport définit le modèle de rappport XML.
     */
    public void setMyModeleDeRapport(ModeleDeRapport MyModeleDeRapport) {
        this.MyModeleDeRapport = MyModeleDeRapport;
    }

}
