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
 * @author Thierry Baribaud
 * @version 0.36
 */
public class ExpCallsParams {

    /**
     * Connexion à la base de données courante.
     */
    private Connection connection;

    /**
     * Identifiant du client.
     */
    private int unum;

    /**
     * Nom du client.
     */
    private String uname;

    /**
     * Nom abrégé du client.
     */
    private String uabbname;

    /**
     * Date de début de l'export à 0h.
     */
    private Timestamp begDate;

    /**
     * Date de fin de l'export à 0h.
     */
    private Timestamp endDate;

    /**
     * suffix : Suffixe optionel à rajouter au nom du fichier
     */
    private String suffix = null;

    /**
     * Modèle de rapport XML.
     */
    private ModeleDeRapport modeleDeRapport;
    
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

    /**
     * debugMode : fonctionnement du programme en mode debug (true/false).
     * Valeur par défaut : false.
     */
    private static boolean debugMode = false;

    /**
     *
     * @param connection connexion à la base de données locale
     * @param args argument(s) de la ligne de commande
     * @throws ClassNotFoundException en cas de classe non trouvée
     * @throws SQLException en cas d'erreur SQL
     */
    public ExpCallsParams(Connection connection, GetArgs args) throws ClassNotFoundException, SQLException {
        FurgentDAO furgentDAO;
        Furgent furgent;

        setConnection(connection);

        setUnum(args.getUnum());
        furgentDAO = new FurgentDAO(connection);
        furgentDAO.filterById(unum);
        furgentDAO.setSelectPreparedStatement();
        furgent = furgentDAO.select();
        if (furgent != null) {
            setUname(furgent.getUname());
            setUabbname(furgent.getUabbname());
        } else {
            setUname("Inconnu");
            setUabbname("INCONNU");
        }
        furgentDAO.closeSelectPreparedStatement();

        setBegDate(args.getBegDate());
        setEndDate(args.getEndDate());
        setSuffix(args.getSuffix());
        setXMLFilename(DetermineXMLFilename(unum));
        setXSDFilename(DetermineXSDFilename(unum));
        setExcelFilename(DetermineExcelFilename(unum));
        setDebugMode(args.getDebugMode());
    }

    /**
     * Méthode qui retourne le nom du fichier de sortie au format XML.
     * 
     * @return XMLFilename le nom du fichier de sortie au format XML.
     */
    public String getXMLFilename() {
        return XMLFilename;
    }

    /**
     * Méthode qui définit la référence client.
     * 
     * @param unum définit la référence client.
     */
    public void setUnum(int unum) {
        this.unum = unum;
    }

    /**
     * Méthode qui définit la date de début de l'export à 0h.
     * 
     * @param begDate définit la date de début de l'export à 0h.
     */
    public void setBegDate(Timestamp begDate) {
        this.begDate = begDate;
    }

    /**
     * Méthode qui définit la date de fin de l'export à 0h.
     * 
     * @param endDate définit la date de fin de l'export à 0h.
     */
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    /**
     * Méthode qui retourne l'identifiant du client.
     * @return Unum l'identifiant du client.
     */
    public int getUnum() {
        return (unum);
    }

    /**
     * Méthode qui retourne la date de début de l'export à 0h.
     * @return begDate la date de début de l'export à 0h.
     */
    public Timestamp getBegDate() {
        return (begDate);
    }

    /**
     * Méthode qui retourne la date de fin de l'export à 0h.
     * @return endDate la date de fin de l'export à 0h.
     */
    public Timestamp getEndDate() {
        return (endDate);
    }

    /**
     * Méthode qui retourne le suffixe à ajouter au nom du fichier.
     * 
     * @return le suffixe à ajouter au nom du fichier.
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * Méthode qui définit le suffixe à ajouter au nom du fichier.
     * 
     * @param suffix définit le suffixe à ajouter au nom du fichier.
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
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
        StringBuffer filename;

        filename = new StringBuffer("tickets_" + MyFormatter.format(unum));
        if (suffix != null) filename.append("_").append(suffix);
        
        return (filename.toString());
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
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 125:
//                setMyModeleDeRapport(ModeleDeRapport.VES);
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 341:
                setModeleDeRapport(ModeleDeRapport.SAU);
                break;
            case 513:
                setModeleDeRapport(ModeleDeRapport.CAR);
                break;
            case 515:
                setModeleDeRapport(ModeleDeRapport.SAU);
                break;
            case 552:
                setModeleDeRapport(ModeleDeRapport.SOL);
                break;
            case 555:
                setModeleDeRapport(ModeleDeRapport.GEO);
                break;
            case 557:
                setModeleDeRapport(ModeleDeRapport.SAU);
                break;
            case 567:
                setModeleDeRapport(ModeleDeRapport.CEG);
                break;
            case 572:
                setModeleDeRapport(ModeleDeRapport.NEX);
                break;
            case 573:
                setModeleDeRapport(ModeleDeRapport.PHI);
                break;
            case 582:
                setModeleDeRapport(ModeleDeRapport.ENE);
                break;
            case 592:
//                setMyModeleDeRapport(ModeleDeRapport.VES);
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 600:
                setModeleDeRapport(ModeleDeRapport.PRI);
                break;
            case 602:
                setModeleDeRapport(ModeleDeRapport.SAU);
                break;
            case 603:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 604:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 605:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 606:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 607:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 608:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 609:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 610:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 611:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 612:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 613:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 614:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 615:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 616:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 617:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 620:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 626:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 627:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 629:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 630:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 632:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 634:
                setModeleDeRapport(ModeleDeRapport.NEX);
                break;
            case 635:
                setModeleDeRapport(ModeleDeRapport.BOU);
                break;
            case 648:
                setModeleDeRapport(ModeleDeRapport.MIQ);
                break;
            case 654:
                setModeleDeRapport(ModeleDeRapport.NEX);
                break;
            case 655:
                setModeleDeRapport(ModeleDeRapport.NEX);
                break;
            case 670:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            default:
                setModeleDeRapport(ModeleDeRapport.STD);
                break;
        }
        return ("tickets_" + modeleDeRapport + ".xsd");
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
     * Méthode qui définit le le nom du fichier de sortie au format XML.
     * 
     * @param XMLFilename définit le nom du fichier de sortie au format XML.
     */
    public void setXMLFilename(String XMLFilename) {
        this.XMLFilename = XMLFilename;
    }

    /**
     * Méthode qui retourne le nom du fichier contenant le schéma XML.
     * 
     * @return XSDFilename le nom du fichier contenant le schéma XML.
     */
    public String getXSDFilename() {
        return XSDFilename;
    }

    /**
     * Méthode qui définit le nom du fichier contenant le schéma XML.
     * 
     * @param XSDFilename définit le nom du fichier contenant le schéma XML.
     */
    public void setXSDFilename(String XSDFilename) {
        this.XSDFilename = XSDFilename;
    }

    /**
     * Méthode qui retourne le nom du fichier de sortie au format Excel.
     * 
     * @return ExcelFilename le nom du fichier de sortie au format Excel.
     */
    public String getExcelFilename() {
        return ExcelFilename;
    }

    /**
     * Méhode qui définit le nom du fichier de sortie au format Excel.
     * 
     * @param ExcelFilename définit le nom du fichier de sortie au format Excel.
     */
    public void setExcelFilename(String ExcelFilename) {
        this.ExcelFilename = ExcelFilename;
    }

    /**
     * Méthode qui retourne la connection à la base de données locale.
     * 
     * @return connection connection à la base de données locale.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Méthode qui définit la connection à la base de données locale.
     * 
     * @param connection définit la connection à la base de données locale.
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Méthode qui retourne le nom du client.
     * 
     * @return uname le nom du client.
     */
    public String getUname() {
        return uname;
    }

    /**
     * Méthode qui définit le nom du client.
     * 
     * @param uname définit le nom du client.
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     * Méthode qui retourne le nom abrégé du client.
     * 
     * @return uabbname le nom abrégé du client.
     */
    public String getUabbname() {
        return uabbname;
    }

    /**
     * Méthode qui définit le nom abrégé du client.
     * 
     * @param uabbname définit le nom abrégé du client.
     */
    public void setUabbname(String uabbname) {
        this.uabbname = uabbname;
    }

    /**
     * Méthode qui retourne le modèle de rappport XML.
     * 
     * @return modeleDeRapport le modèle de rappport XML.
     */
    public ModeleDeRapport getModeleDeRapport() {
        return modeleDeRapport;
    }

    /**
     * Méthode qui définit le modèle de rappport XML.
     * 
     * @param modeleDeRapport définit le modèle de rappport XML.
     */
    public void setModeleDeRapport(ModeleDeRapport modeleDeRapport) {
        this.modeleDeRapport = modeleDeRapport;
    }

    /**
     * Méthode qui retourne le mode de fonctionnement debug.
     * 
     * @return debugMode : retourne le mode de fonctionnement debug.
     */
    public boolean getDebugMode() {
        return (debugMode);
    }

    /**
     * Méthode qui définit le fonctionnement du programme en mode debug.
     * 
     * @param debugMode : fonctionnement du programme en mode debug
     * (true/false).
     */
    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

}
