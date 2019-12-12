package expcalls;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import utils.GetArgsException;
import utils.ValidServers;

/**
 * Cette classe sert à vérifier et à récupérer les arguments passés en ligne de
 * commande à un programme.
 *
 * @author Thierry Baribaud.
 * @version 0.53
 */
public class GetArgs {

    private static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * sourceServer : prod pour le serveur de production, dev pour le serveur de
     * développement. Valeur par défaut : dev.
     */
    private String sourceServer = "dev";

    /**
     * Unum : Référence du client. Valeur par défaut : doit être spécifié en
     * ligne de commande.
     */
    private int unum = 0;

    /**
     * filename : fichier qui recevra les résultats du chargement. Valeur par
     * défaut : tickets_0000.xml.
     */
    private String filename = "tickets_0000.xml";

    /**
     * Directory : répertoire vers lequel exporter le fichier des résultats. Par
     * défaut c'est le répertoire du programme.
     */
    private String directory = ".";

    /**
     * begDate : date de début de l'export à 0h.
     */
    private Timestamp begDate = new Timestamp((new java.util.Date().getTime()) - 1000 * 60 * 60 * 24);

    /**
     * endDate : date de fin de l'export à 0h.
     */
    private Timestamp endDate = new Timestamp(new java.util.Date().getTime());

    /**
     * nbJour : nombre de jours à compter de la date courante
     */
    private int nbJour;

    /**
     * suffix : suffixe optionnel à rajouter au nom du fichier
     */
    private String suffix = null;

    /**
     * Filtrer les tickets ouverts
     */
    private boolean openedTicket = false;

    /**
     * Filtrer les tickets associés à l'intervenant
     */
    private int tnum;
    
    /**
     * Filtrer les tickets associés à une agence
     */
    private int a6num;
    
    /**
     * debugMode : fonctionnement du programme en mode debug (true/false).
     * Valeur par défaut : false.
     */
    private boolean debugMode = false;

    /**
     * testMode : fonctionnement du programme en mode test (true/false). Valeur
     * par défaut : false.
     */
    private boolean testMode = false;

    /**
     * @return sourceServer : la valeur pour le serveur source.
     */
    public String getSourceServer() {
        return (sourceServer);
    }

    /**
     * @return filename : le nom du fichier où envoyer les résultats.
     */
    public String getFilename() {
        return (filename);
    }

    /**
     * @return Unum : la référence du client.
     */
    public int getUnum() {
        return (unum);
    }

    /**
     * @return begDate : date de début de l'export à 0h.
     */
    public Timestamp getBegDate() {
        return (begDate);
    }

    /**
     * @return endDate : date de fin de l'export à 0h.
     */
    public Timestamp getEndDate() {
        return (endDate);
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
     * @return debugMode : le mode de fonctionnement debug.
     */
    public boolean getDebugMode() {
        return (debugMode);
    }

    /**
     * @return testMode : le mode de fonctionnement test.
     */
    public boolean getTestMode() {
        return (testMode);
    }

    /**
     * @param sourceServer : définit le serveur source.
     */
    public void setSourceServer(String sourceServer) {
        this.sourceServer = sourceServer;
    }

    /**
     * @param filename : définit le fichier où envoyer les résultats.
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @param unum : définit la référence client.
     */
    public void setUnum(int unum) {
        this.unum = unum;
    }

    /**
     * @param begDate : date de début de l'export à 0h.
     */
    public void setBegDate(Timestamp begDate) {
        this.begDate = begDate;
    }

    /**
     * @param endDate : date de fin de l'export à 0h.
     */
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    /**
     * Définit l'état de filtrage des tickets ouverts
     *
     * @param openedTicket état de filtrage des tickets ouverts
     */
    public void setOpenedTicket(boolean openedTicket) {
        this.openedTicket = openedTicket;
    }

    /**
     * @param debugMode : fonctionnement du programme en mode debug
     * (true/false).
     */
    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    /**
     * @param testMode : fonctionnement du programme en mode test (true/false).
     */
    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    /**
     * @param args arguments de la ligne de commande.
     * @throws GetArgsException erreur sur les paramètres.
     */
    public GetArgs(String args[]) throws GetArgsException {

        int i;
        int n;
        int ip1;
        Date MyDate;
        String currentParam;
        String nextParam;

        // Demande une analyse d'une date valide
        dateFormat.setLenient(false);
        n = args.length;

        System.out.println("nargs=" + n);
//    for(i=0; i<n; i++) System.out.println("args["+i+"]="+Args[i]);
        i = 0;
        while (i < n) {
//            System.out.println("args[" + i + "]=" + Args[i]);
            currentParam = args[i];
            ip1 = i + 1;
            nextParam = (ip1 < n) ? args[ip1] : null;
            if (currentParam.equals("-dbserver")) {
                if (ip1 < n) {
                    if (ValidServers.isAValidServer(nextParam)) {
                        sourceServer = nextParam;
                    } else {
                        throw new GetArgsException("Mauvaise source de données : " + nextParam);
                    }
                    i = ip1;
                } else {
                    throw new GetArgsException("Base de données non définie");
                }
            } else if (currentParam.equals("-u")) {
                if (ip1 < n) {
                    try {
                        unum = Integer.parseInt(nextParam);
                        i = ip1;
                    } catch (Exception MyException) {
                        throw new GetArgsException("La référence client doit être numérique : " + nextParam);
                    }
                } else {
                    throw new GetArgsException("Référence client non définie");
                }
            } else if (currentParam.equals("-b")) {
                if (ip1 < n) {
                    try {
                        MyDate = (Date) dateFormat.parse(nextParam);
                        begDate = new Timestamp(MyDate.getTime());
                        i = ip1;
                    } catch (Exception MyException) {
                        throw new GetArgsException("La date de début doit être valide jj/mm/aaaa : " + nextParam);
                    }
                } else {
                    throw new GetArgsException("Date de début non définie");
                }
            } else if (currentParam.equals("-e")) {
                if (ip1 < n) {
                    try {
                        MyDate = (Date) dateFormat.parse(nextParam);
                        endDate = new Timestamp(MyDate.getTime());
                        i = ip1;
                    } catch (Exception MyException) {
                        throw new GetArgsException("La date de fin doit être valide jj/mm/aaaa : " + nextParam);
                    }
                } else {
                    throw new GetArgsException("Date de fin non définie");
                }
            } else if (currentParam.equals("-n")) {
                if (ip1 < n) {
                    try {
                        this.setNbJour(Integer.parseInt(nextParam));
                        i = ip1;
                    } catch (Exception MyException) {
                        throw new GetArgsException("Le nombre de jour(s) doit être numérique : " + nextParam);
                    }
                } else {
                    throw new GetArgsException("Nombre de jour(s) non défini");
                }
            } else if (currentParam.equals("-o")) {
                if (ip1 < n) {
                    filename = nextParam;
                    i = ip1;
                } else {
                    throw new GetArgsException("Nom de fichier non défini");
                }
            } else if (currentParam.equals("-p")) {
                if (ip1 < n) {
                    directory = nextParam;
                    i = ip1;
                } else {
                    throw new GetArgsException("Répertoire non défini");
                }
            } else if (currentParam.equals("-s")) {
                if (ip1 < n) {
                    suffix = nextParam;
                    i = ip1;
                } else {
                    throw new GetArgsException("Suffixe non défini");
                }
            } else if (currentParam.equals("-openedTicket")) {
                openedTicket = true;
            } else if (currentParam.equals("-provider")) {
                if (ip1 < n) {
                    try {
                        tnum = Integer.parseInt(nextParam);
                        i = ip1;
                    } catch (Exception MyException) {
                        throw new GetArgsException("La référence à l'intervenant doit être numérique : " + nextParam);
                    }
                } else {
                    throw new GetArgsException("Référence intervenant non définie");
                }
            } else if (currentParam.equals("-agencyId")) {
                if (ip1 < n) {
                    try {
                        a6num = Integer.parseInt(nextParam);
                        i = ip1;
                    } catch (Exception MyException) {
                        throw new GetArgsException("La référence à l'agence doit être numérique : " + nextParam);
                    }
                } else {
                    throw new GetArgsException("Référence agence non définie");
                }
            } else if (currentParam.equals("-d")) {
                debugMode = true;
            } else if (currentParam.equals("-t")) {
                testMode = true;
            } else {
                usage();
                throw new GetArgsException("Mauvais argument : " + currentParam);
            }
            i++;
        }
        if (begDate.after(endDate)) {
            throw new GetArgsException("La date de début " + dateFormat.format(begDate)
                    + " doit être antérieure à la date de fin " + dateFormat.format(endDate));
        }
    }

    /**
     * Affiche le mode d'utilisation du programme.
     */
    public static void usage() {
        System.out.println("Usage : java ExpCalls -dbserver dbserver -u unum "
                + " [[-b début] [-f fin]|[-n nbJour]] [-o fichier.xml]"
                + " [-p répertoire] [-s suffixe] [-ticketOpened]"
                + " [-provider tnum]"
                + " [-agencyId a6num]"
                + " [-d] [-t]");
    }

    /**
     * @return le répertoire où exporter le fichier des résultats
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * @param directory définit le répertoire où exporter le fichier des
     * résultats
     */
    public void setDirectory(String directory) {
        this.directory = directory;
    }

    /**
     * Affiche le contenu de GetArgs.
     *
     * @return le contenu de GetArgs.
     */
    @Override
    public String toString() {
        return "GetArgs:{"
                + "dbServer=" + sourceServer
                + ", unum=" + unum
                + ", début=" + dateFormat.format(begDate)
                + ", fin=" + dateFormat.format(endDate)
                + ", fichier=" + filename
                + ", répertoire=" + directory
                + ", nbJour=" + nbJour
                + ", suffixe=" + suffix
                + ", ticketOuvert=" + openedTicket
                + ", provider=" + tnum
                + ", agencyId=" + a6num
                + ", debugMode=" + debugMode
                + ", testMode=" + testMode
                + "}";
    }

    /**
     * @return le nombre de jours à compter de la date courante
     */
    public int getNbJour() {
        return nbJour;
    }

    /**
     * @param nbJour définit le nombre de jours à compter de la date courante La
     * date de début et la date de fin sont définit en conséquence.
     */
    public final void setNbJour(int nbJour) {
        Calendar calendar;

        this.nbJour = nbJour;

        // Récupère la date du jour
        calendar = new GregorianCalendar();

        // Elimine les heures, minutes, secondes et millisecondes.
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        setEndDate(new Timestamp(calendar.getTimeInMillis()));

        // Calcule la date de début d'extraction
        calendar.add(Calendar.DAY_OF_YEAR, -nbJour);
        setBegDate(new Timestamp(calendar.getTimeInMillis()));
    }

    /**
     * @return le suffixe à ajouter au nom du fichier
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * @param suffix définit le suffixe à ajouter au nom du fichier
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * @return retourne la référence de l'intervenant à filtrer
     */
    public int getTnum() {
        return tnum;
    }

    /**
     * @param tnum définit la référence de l'intervenant à filter
     */
    public void setTnum(int tnum) {
        this.tnum = tnum;
    }

    /**
     * @return retourne la référence de l'agence à filtrer
     */
    public int getA6num() {
        return a6num;
    }

    /**
     * @param a6num définit la référence de l'agence à filter
     */
    public void setA6num(int a6num) {
        this.a6num = a6num;
    }
}
