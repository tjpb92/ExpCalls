package expcalls;

/**
 * Classe représentant un ticket. Cela correspond à l'association d'un appel
 * Fcalls et d'un complément d'appel Fcomplmt. Il s'agit du ticket basique. Les
 * tickets spécifiques à un client dériveront de celui-ci.
 *
 * @version Mai 2016.
 * @author Thierry Baribaud.
 */
public class Ticket_0000 {

    /**
     * Partie composée de l'appel.
     */
    public Fcalls Fcalls_0000;

    /**
     * Partie composée du complément d'appel.
     */
    public Fcomplmt Fcomplmt_0000;
    
    /**
     * Nom de l'agence.
     */
    private String A6name = "#N/A";

    /**
     * Item de menu sélectionné.
     */
    private String M6name = "#N/A";

    /**
     * Contructeur de la classe Ticket complet.
     *
     * @param Fcalls_0000 appel,
     * @param Fcomplmt_0000 complément d'appel.
     */
    public Ticket_0000(Fcalls Fcalls_0000, Fcomplmt Fcomplmt_0000) {
        this.Fcalls_0000 = Fcalls_0000;
        this.Fcomplmt_0000 = Fcomplmt_0000;
    }

    /**
     * Contructeur secondaire de la classe Ticket sans le complément d'appel.
     *
     * @param Fcalls_0000 appel,
     */
    public Ticket_0000(Fcalls Fcalls_0000) {
        this.Fcalls_0000 = Fcalls_0000;
        this.Fcomplmt_0000 = null;
    }

    /**
     * Retourne l'appel.
     *
     * @return L'appel Fcalls_0000.
     */
    public Fcalls getFcalls_0000() {
        return Fcalls_0000;
    }

    /**
     * Retourne le complément d'appel.
     *
     * @return Le complément d'appel Fcomplmt_0000.
     */
    public Fcomplmt getFcomplmt_0000() {
        return Fcomplmt_0000;
    }

    /**
     * Définit l'appel.
     *
     * @param Fcalls_0000 Définit l'appel.
     */
    public void setFcalls_0000(Fcalls Fcalls_0000) {
        this.Fcalls_0000 = Fcalls_0000;
    }

    /**
     * Définit le complément d'appel.
     *
     * @param Fcomplmt_0000 Définit le complément d'appel.
     */
    public void setFcomplmt_0000(Fcomplmt Fcomplmt_0000) {
        this.Fcomplmt_0000 = Fcomplmt_0000;
    }

    /**
     * Retourne le contenu du ticket.
     *
     * @return le contenu du ticket.
     */
    @Override
    public String toString() {
        return (Fcalls_0000 + " " + Fcomplmt_0000);
    }

    /**
     * @return A6name nom de l'agence.
     */
    public String getA6name() {
        return A6name;
    }

    /**
     * @param A6name définit le nom de l'agence.
     */
    public void setA6name(String A6name) {
        this.A6name = A6name;
    }

    /**
     * @return M6name nom de l'item de menu sélectionné.
     */
    public String getM6name() {
        return M6name;
    }

    /**
     * @param M6name définit le nom de l'item de menu.
     */
    public void setM6name(String M6name) {
        this.M6name = M6name;
    }
}
