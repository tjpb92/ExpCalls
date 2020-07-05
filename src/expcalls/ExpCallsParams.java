package expcalls;

import bdd.Furgent;
import bdd.FurgentDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;

/**
 * Classe servant � stocker les param�tres pour exporter les appels.
 *
 * @author Thierry Baribaud
 * @version 0.57
 */
public class ExpCallsParams {

    /**
     * Connexion � la base de donn�es courante.
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
     * Nom abr�g� du client.
     */
    private String uabbname;

    /**
     * Date de d�but de l'export � 0h.
     */
    private Timestamp begDate;

    /**
     * Date de fin de l'export � 0h.
     */
    private Timestamp endDate;

    /**
     * suffix : Suffixe optionel � rajouter au nom du fichier
     */
    private String suffix = null;

    /**
     * Mod�le de rapport XML.
     */
    private ModeleDeRapport modeleDeRapport;

    /**
     * Nom du fichier de sortie au format XML.
     */
    private String XMLFilename = DetermineXMLFilename(0);

    /**
     * Nom du fichier contenant le sch�ma XML.
     */
    private String XSDFilename = DetermineXSDFilename(0);

    /**
     * Nom du fichier de sortie au format Excel.
     */
    private String ExcelFilename = DetermineExcelFilename(0);

    /**
     * Filtrer les tickets ouverts
     */
    private boolean openedTicket = false;

    /**
     * Filter les tickets associ�s � l'intervenant
     */
    private int tnum;

    /**
     * Filtrer les tickets associ�s � une agence
     */
    private int a6num;

    /**
     * debugMode : fonctionnement du programme en mode debug (true/false).
     * Valeur par d�faut : false.
     */
    private static boolean debugMode = false;

    /**
     *
     * @param connection connexion � la base de donn�es locale
     * @param args argument(s) de la ligne de commande
     * @throws ClassNotFoundException en cas de classe non trouv�e
     * @throws SQLException en cas d'erreur SQL
     */
    public ExpCallsParams(Connection connection, GetArgs args) throws ClassNotFoundException, SQLException {
        FurgentDAO furgentDAO;
        Furgent furgent;

        this.connection = connection;

        unum = args.getUnum();
        furgentDAO = new FurgentDAO(connection);
        furgentDAO.filterById(unum);
        furgentDAO.setSelectPreparedStatement();
        furgent = furgentDAO.select();
        if (furgent != null) {
            uname = furgent.getUname();
            uabbname = furgent.getUabbname();
        } else {
            uname = "Inconnu";
            uabbname = "INCONNU";
        }
        furgentDAO.closeSelectPreparedStatement();

        begDate = args.getBegDate();
        endDate = args.getEndDate();
        suffix = args.getSuffix();
        XMLFilename = DetermineXMLFilename(unum);
        XSDFilename = DetermineXSDFilename(unum);
        ExcelFilename = DetermineExcelFilename(unum);
        openedTicket = args.isOpenedTicket();
        tnum = args.getTnum();
        a6num = args.getA6num();
        debugMode = args.getDebugMode();
    }

    /**
     * M�thode qui retourne le nom du fichier de sortie au format XML.
     *
     * @return XMLFilename le nom du fichier de sortie au format XML.
     */
    public String getXMLFilename() {
        return XMLFilename;
    }

    /**
     * M�thode qui d�finit la r�f�rence client.
     *
     * @param unum d�finit la r�f�rence client.
     */
    public void setUnum(int unum) {
        this.unum = unum;
    }

    /**
     * M�thode qui d�finit la date de d�but de l'export � 0h.
     *
     * @param begDate d�finit la date de d�but de l'export � 0h.
     */
    public void setBegDate(Timestamp begDate) {
        this.begDate = begDate;
    }

    /**
     * M�thode qui d�finit la date de fin de l'export � 0h.
     *
     * @param endDate d�finit la date de fin de l'export � 0h.
     */
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    /**
     * M�thode qui retourne l'identifiant du client.
     *
     * @return Unum l'identifiant du client.
     */
    public int getUnum() {
        return (unum);
    }

    /**
     * M�thode qui retourne la date de d�but de l'export � 0h.
     *
     * @return begDate la date de d�but de l'export � 0h.
     */
    public Timestamp getBegDate() {
        return (begDate);
    }

    /**
     * M�thode qui retourne la date de fin de l'export � 0h.
     *
     * @return endDate la date de fin de l'export � 0h.
     */
    public Timestamp getEndDate() {
        return (endDate);
    }

    /**
     * M�thode qui retourne le suffixe � ajouter au nom du fichier.
     *
     * @return le suffixe � ajouter au nom du fichier.
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * M�thode qui d�finit le suffixe � ajouter au nom du fichier.
     *
     * @param suffix d�finit le suffixe � ajouter au nom du fichier.
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * M�thode qui d�termine la racine du nom d'un fichier par rapport �
     * l'identifiant du client.
     *
     * @param unum identifiant du client.
     * @return Filename la racine du nom du fichier.
     */
    private String DefaultFilename(int unum) {
        DecimalFormat MyFormatter = new DecimalFormat("0000");
        StringBuffer filename;

        filename = new StringBuffer("tickets_" + MyFormatter.format(unum));
        if (suffix != null) {
            filename.append("_").append(suffix);
        }

        return (filename.toString());
    }

    /**
     * M�thode qui d�termine le nom du fichier de sortie au format XML.
     *
     * @param unum identifiant du client.
     * @return Filename la racine du nom du fichier.
     */
    private String DetermineXMLFilename(int unum) {
        return (DefaultFilename(unum) + ".xml");
    }

    /**
     * M�thode qui d�termine le nom du fichier contenant le sch�ma XML.
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
            case 681:
                setModeleDeRapport(ModeleDeRapport.NEX);
                break;
            case 691:
                setModeleDeRapport(ModeleDeRapport.NEX);
                break;
            case 694:
                setModeleDeRapport(ModeleDeRapport.VF);
                break;
            case 703:
                setModeleDeRapport(ModeleDeRapport.BRE);
                break;
            default:
                setModeleDeRapport(ModeleDeRapport.STD);
                break;
        }
        return ("tickets_" + modeleDeRapport + ".xsd");
    }

    /**
     * M�thode qui d�termine le nom du fichier de sortie au format Excel.
     *
     * @param unum identifiant du client.
     * @return Filename la racine du nom du fichier.
     */
    private String DetermineExcelFilename(int unum) {
        return (DefaultFilename(unum) + ".xlsx");
    }

    /**
     * M�thode qui d�finit le le nom du fichier de sortie au format XML.
     *
     * @param XMLFilename d�finit le nom du fichier de sortie au format XML.
     */
    public void setXMLFilename(String XMLFilename) {
        this.XMLFilename = XMLFilename;
    }

    /**
     * M�thode qui retourne le nom du fichier contenant le sch�ma XML.
     *
     * @return XSDFilename le nom du fichier contenant le sch�ma XML.
     */
    public String getXSDFilename() {
        return XSDFilename;
    }

    /**
     * M�thode qui d�finit le nom du fichier contenant le sch�ma XML.
     *
     * @param XSDFilename d�finit le nom du fichier contenant le sch�ma XML.
     */
    public void setXSDFilename(String XSDFilename) {
        this.XSDFilename = XSDFilename;
    }

    /**
     * M�thode qui retourne le nom du fichier de sortie au format Excel.
     *
     * @return ExcelFilename le nom du fichier de sortie au format Excel.
     */
    public String getExcelFilename() {
        return ExcelFilename;
    }

    /**
     * M�hode qui d�finit le nom du fichier de sortie au format Excel.
     *
     * @param ExcelFilename d�finit le nom du fichier de sortie au format Excel.
     */
    public void setExcelFilename(String ExcelFilename) {
        this.ExcelFilename = ExcelFilename;
    }

    /**
     * M�thode qui retourne la connection � la base de donn�es locale.
     *
     * @return connection connection � la base de donn�es locale.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * M�thode qui d�finit la connection � la base de donn�es locale.
     *
     * @param connection d�finit la connection � la base de donn�es locale.
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * M�thode qui retourne le nom du client.
     *
     * @return uname le nom du client.
     */
    public String getUname() {
        return uname;
    }

    /**
     * M�thode qui d�finit le nom du client.
     *
     * @param uname d�finit le nom du client.
     */
    public void setUname(String uname) {
        this.uname = uname;
    }

    /**
     * M�thode qui retourne le nom abr�g� du client.
     *
     * @return uabbname le nom abr�g� du client.
     */
    public String getUabbname() {
        return uabbname;
    }

    /**
     * M�thode qui d�finit le nom abr�g� du client.
     *
     * @param uabbname d�finit le nom abr�g� du client.
     */
    public void setUabbname(String uabbname) {
        this.uabbname = uabbname;
    }

    /**
     * M�thode qui retourne le mod�le de rappport XML.
     *
     * @return modeleDeRapport le mod�le de rappport XML.
     */
    public ModeleDeRapport getModeleDeRapport() {
        return modeleDeRapport;
    }

    /**
     * M�thode qui d�finit le mod�le de rappport XML.
     *
     * @param modeleDeRapport d�finit le mod�le de rappport XML.
     */
    public void setModeleDeRapport(ModeleDeRapport modeleDeRapport) {
        this.modeleDeRapport = modeleDeRapport;
    }

    /**
     * Retourne s'il y a ou non filtrage des tickets ouverts
     *
     * @return s'il y a ou non filtrage des tickets ouverts
     */
    public boolean isOpenedTicket() {
        return openedTicket;
    }

    /**
     * D�finit l'�tat de filtrage des tickets ouverts
     *
     * @param openedTicket �tat de filtrage des tickets ouverts
     */
    public void setOpenedTicket(boolean openedTicket) {
        this.openedTicket = openedTicket;
    }

    /**
     * M�thode qui retourne le mode de fonctionnement debug.
     *
     * @return debugMode : retourne le mode de fonctionnement debug.
     */
    public boolean getDebugMode() {
        return (debugMode);
    }

    /**
     * M�thode qui d�finit le fonctionnement du programme en mode debug.
     *
     * @param debugMode : fonctionnement du programme en mode debug
     * (true/false).
     */
    public void setDebugMode(boolean debugMode) {
        ExpCallsParams.debugMode = debugMode;
    }

    /**
     * @return retourne la r�f�rence de l'intervenant � filtrer
     */
    public int getTnum() {
        return tnum;
    }

    /**
     * @param tnum d�finit la r�f�rence de l'intervenant � filter
     */
    public void setTnum(int tnum) {
        this.tnum = tnum;
    }

    /**
     * @return retourne la r�f�rence de l'agence � filtrer
     */
    public int getA6num() {
        return a6num;
    }

    /**
     * @param a6num d�finit la r�f�rence de l'agence � filter
     */
    public void setA6num(int a6num) {
        this.a6num = a6num;
    }

}
