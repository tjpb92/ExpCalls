package expcalls;

/**
 * Furgent est une classe décrivant un service d'urgence.
 *
 * @version Mai 2016.
 * @author Thierry Baribaud.
 */
public class Furgent {

    private int unum;
    private String Uname;
    private String Uabbname;

    /**
     * @return unum l'identifiant du service d'urgence.
     */
    public int getUnum() {
        return unum;
    }

    /**
     * @param unum définit l'identifiant du service d'urgence.
     */
    public void setUnum(int unum) {
        this.unum = unum;
    }

    /**
     * @return Uname le nom du service d'urgence.
     */
    public String getUname() {
        return Uname;
    }

    /**
     * @param Uname définit le nom du service d'urgence.
     */
    public void setUname(String Uname) {
        this.Uname = (Uname != null) ? Uname.trim() : null;
    }

    /**
     * @return Uabbname le nom abrégé du service d'urgence.
     */
    public String getUabbname() {
        return Uabbname;
    }

    /**
     * @param Uabbname Uabbname définit le nom abrégé du service d'urgence.
     */
    public void setUabbname(String Uabbname) {
        this.Uabbname = (Uabbname != null) ? Uabbname.trim() : null;
    }

}
