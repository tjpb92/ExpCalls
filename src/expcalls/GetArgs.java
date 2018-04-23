package expcalls;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Cette classe sert à vérifier et à récupérer les arguments passés en ligne de
 * commande à un programme.
 *
 * @author Thierry Baribaud.
 * @version 0.43
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
     * Retourne s'il y a ou non filtrage des tickets ouverts
     *
     * @return s'il y a ou non filtrage des tickets ouverts
     */
    public boolean isOpenedTicket() {
        return openedTicket;
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
     * <p>
     * Les arguments en ligne de commande permettent de changer le mode de
     * fonctionnement.</p><ul>
     * <li>-dbserver : référence à la base de donnée, par défaut fait référence
     * à la base de développement, cf. fichier de paramÃ¨tres
     * <i>myDatabases.prop</i> (optionnel)</li>
     * <li>-u unum : identifiant du service d'urgence (obligatoire).</li>
     * <li>-b début : date de début de l'extraction à 0h, hier par défaut,
     * format DD/MM/AAAA (optionnel).</li>
     * <li>-b fin : date de fin de l'extraction à 0h, aujourd'hui par défaut,
     * format DD/MM/AAAA (optionnel).</li>
     * <li>-o fichier : fichier vers lequel exporter les données des appels, nom
     * par défaut <i>calls_0000.xml</i>(optionnel).</li>
     * <li>-n nbJour : nombre de jour(s) à compter de la date courante.</li>
     * <li>-s suffixe : suffixe optionnel à ajouter au nom du fichier.</li>
     * <li>-openedTicket : permet de filtrer les tickets ouverts. inactif par
 défaut (optionnel)</li>
     * <li>-d : le programme fonctionne en mode débug le rendant plus verbeux,
     * désactivé par défaut (optionnel).</li>
     * <li>-t : le programme fonctionne en mode de test, les transactions en
     * base de données ne sont pas exécutées, désactivé par défaut
     * (optionnel).</li>
     * </ul>
     *
     * @param Args arguments de la ligne de commande.
     * @throws expcalls.GetArgsException erreur sur les paramètres.
     */
    public GetArgs(String Args[]) throws GetArgsException {

        int i;
        int n;
        int ip1;
        Date MyDate;

        // Demande une analyse d'une date valide
        dateFormat.setLenient(false);
        n = Args.length;

        System.out.println("nargs=" + n);
//    for(i=0; i<n; i++) System.out.println("args["+i+"]="+Args[i]);
        i = 0;
        while (i < n) {
//            System.out.println("args[" + i + "]=" + Args[i]);
            ip1 = i + 1;
            if (Args[i].equals("-dbserver")) {
                if (ip1 < n) {
                    if (Args[ip1].equals("dev") || Args[ip1].equals("prod") || Args[ip1].equals("mysql")) {
                        sourceServer = Args[ip1];
                    } else {
                        throw new GetArgsException("Mauvaise source de données : " + Args[ip1]);
                    }
                    i = ip1;
                } else {
                    throw new GetArgsException("Base de données non définie");
                }
            } else if (Args[i].equals("-u")) {
                if (ip1 < n) {
                    try {
                        unum = Integer.parseInt(Args[ip1]);
                        i = ip1;
                    } catch (Exception MyException) {
                        throw new GetArgsException("La référence client doit être numérique : " + Args[ip1]);
                    }
                } else {
                    throw new GetArgsException("Référence client non définie");
                }
            } else if (Args[i].equals("-b")) {
                if (ip1 < n) {
                    try {
                        MyDate = (Date) dateFormat.parse(Args[ip1]);
                        begDate = new Timestamp(MyDate.getTime());
                        i = ip1;
                    } catch (Exception MyException) {
                        throw new GetArgsException("La date de début doit être valide jj/mm/aaaa : " + Args[ip1]);
                    }
                } else {
                    throw new GetArgsException("Date de début non définie");
                }
            } else if (Args[i].equals("-e")) {
                if (ip1 < n) {
                    try {
                        MyDate = (Date) dateFormat.parse(Args[ip1]);
                        endDate = new Timestamp(MyDate.getTime());
                        i = ip1;
                    } catch (Exception MyException) {
                        throw new GetArgsException("La date de fin doit être valide jj/mm/aaaa : " + Args[ip1]);
                    }
                } else {
                    throw new GetArgsException("Date de fin non définie");
                }
            } else if (Args[i].equals("-n")) {
                if (ip1 < n) {
                    try {
                        this.setNbJour(Integer.parseInt(Args[ip1]));
                        i = ip1;
                    } catch (Exception MyException) {
                        throw new GetArgsException("Le nombre de jour(s) doit être numérique : " + Args[ip1]);
                    }
                } else {
                    throw new GetArgsException("Nombre de jour(s) non défini");
                }
            } else if (Args[i].equals("-o")) {
                if (ip1 < n) {
                    filename = Args[ip1];
                    i = ip1;
                } else {
                    throw new GetArgsException("Nom de fichier non défini");
                }
            } else if (Args[i].equals("-p")) {
                if (ip1 < n) {
                    directory = Args[ip1];
                    i = ip1;
                } else {
                    throw new GetArgsException("Répertoire non défini");
                }
            } else if (Args[i].equals("-s")) {
                if (ip1 < n) {
                    suffix = Args[ip1];
                    i = ip1;
                } else {
                    throw new GetArgsException("Suffixe non défini");
                }
            } else if (Args[i].equals("-openedTicket")) {
                openedTicket = true;
            } else if (Args[i].equals("-d")) {
                debugMode = true;
            } else if (Args[i].equals("-t")) {
                testMode = true;
            } else {
                throw new GetArgsException("Mauvais argument : " + Args[i]);
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
        System.out.println("Usage : java ExpCalls -dbserver prod -u unum "
                + " [[-b début] [-f fin]|[-n nbJour]] [-o fichier.xml]"
                + " [-p répertoire] [-s suffixe] [-ticketOpened]"
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
}
