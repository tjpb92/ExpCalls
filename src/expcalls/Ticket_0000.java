package expcalls;

import bdd.Fcalls;
import bdd.Fcomplmt;

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
     * Etat de l'intervention.
     */
    private String EtatIntervention = "#N/A";

    /**
     * Prestataire1 : prestataire sur la première transmission.
     */
    private String Prestataire1;
    /**
     * DateMissionnement1 : date de la première transmission.
     */
    private String DateMissionnement1;
    /**
     * HeureMissionnement1 : heure de la première transmission.
     */
    private String HeureMissionnement1;
    /**
     * NoTelephone1 : Numéro de téléphone du prestataire.
     */
    private String NoTelephone1;
    /**
     * Prestataire1 : prestataire sur la dernière transmission.
     */
    private String Prestataire2;
    /**
     * DateMissionnement1 : date de la dernière transmission.
     */
    private String DateMissionnement2;
    /**
     * HeureMissionnement1 : heure de la dernière transmission.
     */
    private String HeureMissionnement2;
    /**
     * NoTelephone1 : Numéro de téléphone du prestataire.
     */
    private String NoTelephone2;
    /**
     * Rapport d'intervention
     */
    private String RapportIntervention;
    /**
     * Le technicien est-il encore sur site ?
     */
    private String TechnicienSurSite;

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

    /**
     * @return EtatIntervention l'état de l'intevention
     */
    public String getEtatIntervention() {
        return EtatIntervention;
    }

    /**
     * @param EtatIntervention définit l'état de l'intervention.
     */
    public void setEtatIntervention(String EtatIntervention) {
        this.EtatIntervention = EtatIntervention;
    }

    /**
     * @return the Prestataire1
     */
    public String getPrestataire1() {
        return Prestataire1;
    }

    /**
     * @param Prestataire1 the Prestataire1 to set
     */
    public void setPrestataire1(String Prestataire1) {
        this.Prestataire1 = Prestataire1;
    }

    /**
     * @return the DateMissionnement1
     */
    public String getDateMissionnement1() {
        return DateMissionnement1;
    }

    /**
     * @param DateMissionnement1 the DateMissionnement1 to set
     */
    public void setDateMissionnement1(String DateMissionnement1) {
        this.DateMissionnement1 = DateMissionnement1;
    }

    /**
     * @return the HeureMissionnement1
     */
    public String getHeureMissionnement1() {
        return HeureMissionnement1;
    }

    /**
     * @param HeureMissionnement1 the HeureMissionnement1 to set
     */
    public void setHeureMissionnement1(String HeureMissionnement1) {
        this.HeureMissionnement1 = HeureMissionnement1;
    }

    /**
     * @return the NoTelephone1
     */
    public String getNoTelephone1() {
        return NoTelephone1;
    }

    /**
     * @param NoTelephone1 the NoTelephone1 to set
     */
    public void setNoTelephone1(String NoTelephone1) {
        this.NoTelephone1 = NoTelephone1;
    }

    /**
     * @return the Prestataire2
     */
    public String getPrestataire2() {
        return Prestataire2;
    }

    /**
     * @param Prestataire2 the Prestataire2 to set
     */
    public void setPrestataire2(String Prestataire2) {
        this.Prestataire2 = Prestataire2;
    }

    /**
     * @return the DateMissionnement2
     */
    public String getDateMissionnement2() {
        return DateMissionnement2;
    }

    /**
     * @param DateMissionnement2 the DateMissionnement2 to set
     */
    public void setDateMissionnement2(String DateMissionnement2) {
        this.DateMissionnement2 = DateMissionnement2;
    }

    /**
     * @return the HeureMissionnement2
     */
    public String getHeureMissionnement2() {
        return HeureMissionnement2;
    }

    /**
     * @param HeureMissionnement2 the HeureMissionnement2 to set
     */
    public void setHeureMissionnement2(String HeureMissionnement2) {
        this.HeureMissionnement2 = HeureMissionnement2;
    }

    /**
     * @return the NoTelephone2
     */
    public String getNoTelephone2() {
        return NoTelephone2;
    }

    /**
     * @param NoTelephone2 the NoTelephone2 to set
     */
    public void setNoTelephone2(String NoTelephone2) {
        this.NoTelephone2 = NoTelephone2;
    }

    /**
     * @return the RapportIntervention
     */
    public String getRapportIntervention() {
        return RapportIntervention;
    }

    /**
     * @param RapportIntervention the RapportIntervention to set
     */
    public void setRapportIntervention(String RapportIntervention) {
        this.RapportIntervention = RapportIntervention;
    }

    /**
     * @return the TechnicienSurSite
     */
    public String getTechnicienSurSite() {
        return TechnicienSurSite;
    }

    /**
     * @param TechnicienSurSite the TechnicienSurSite to set
     */
    public void setTechnicienSurSite(String TechnicienSurSite) {
        this.TechnicienSurSite = TechnicienSurSite;
    }

}
