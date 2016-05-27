/**
 * Classe représentant un ticket. Cela correspond à l'association d'un appel
 * Fcalls et d'un complément d'appel Fcomplmt. Il s'agit du ticket basique. Les
 * tickets spécifiques à un client dériveront de celui-ci.
 *
 * @version Mai 2016.
 * @author Thierry Baribaud.
 */
package expcalls;

public class Ticket_0000 {

    /**
     * Partie composée de l'appel.
     */
    private Fcalls Fcalls_0000;

    /**
     * Partie composée du complément d'appel.
     */
    private Fcomplmt Fcomplmt_0000;

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
}
