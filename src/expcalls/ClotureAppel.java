package expcalls;

import java.sql.Timestamp;

/**
 * ClotureAppel est une classe qui décrit une clôture d'appel standard. Les
 * clôtures d'appel spécifique hériteront de cette classe.
 *
 * @version Juin 2016.
 * @author Thierry Baribaud.
 */
public class ClotureAppel {

    /**
     * Date de début d'intervention.
     */
    private Timestamp BegDate;

    /**
     * Date de fin d'intervention.
     */
    private Timestamp EndDate;

    /**
     * Résultat de l'intervention.
     * <P>
     * Modalités : </P>
     * <UL><LI> 1 : "Réparation définitive",</LI>
     * <LI> 2 : "Réparation partielle",</LI>
     * <LI> 3 : "Réparation impossible",</LI>
     * <LI> 4 : "Réparation reportée en HO",</LI>
     * <LI> 98 : "Cloture admin - appel hors périmètre",</LI>
     * <LI> 99 : "Cloture infructueuse malgré relances ANSTEL".</LI></UL>
     */
    private String Resultat;

    /**
     * Nature de l'intervention.
     * <P>
     * Modalités : </P>
     * <UL><LI>1 : "Panne matériel",</LI>
     * <LI>2 : "Vandalisme",</LI>
     * <LI>3 : "Autre". </LI></UL>
     */
    private String Nature;

    /**
     * Rapport d'intervention.
     */
    private String Rapport;

    /**
     * Le technicien est-il encore sur site ?
     * <P>
     * Modalités : </P>
     * <UL><LI>1 : "Oui", </LI>
     * <LI>2 : "Non",  </LI>
     * <LI>3 : "Ne sait pas". </LI></UL>
     */
    private String OnSite;

    /**
     * @return BegDate la date de début d'intervention.
     */
    public Timestamp getBegDate() {
        return BegDate;
    }

    /**
     * @param BegDate définit la date de début d'intervention.
     */
    public void setBegDate(Timestamp BegDate) {
        this.BegDate = BegDate;
    }

    /**
     * @return EndDate la date de fin d'intervention.
     */
    public Timestamp getEndDate() {
        return EndDate;
    }

    /**
     * @param EndDate définit la date de fin d'intervention.
     */
    public void setEndDate(Timestamp EndDate) {
        this.EndDate = EndDate;
    }

    /**
     * @return Rapport le rapport d'intervention.
     */
    public String getRapport() {
        return Rapport;
    }

    /**
     * @param Rapport définit le rapport d'intervention.
     */
    public void setRapport(String Rapport) {
        this.Rapport = Rapport;
    }

    public ClotureAppel() {

    }

    /**
     * @return Resultat le résultat de l'intervention.
     */
    public String getResultat() {
        return Resultat;
    }

    /**
     * @param Resultat Définit le résultat de l'intervention.
     */
    public void setResultat(String Resultat) {
        this.Resultat = Resultat;
    }

    /**
     * @return Nature la nature de l'intervention.
     */
    public String getNature() {
        return Nature;
    }

    /**
     * @param Nature définit la nature de l'intervention.
     */
    public void setNature(String Nature) {
        this.Nature = Nature;
    }

    /**
     * @return OnSite la localisation du technicien.
     */
    public String getOnSite() {
        return OnSite;
    }

    /**
     * @param OnSite définit la localisation du technicien.
     */
    public void setOnSite(String OnSite) {
        this.OnSite = OnSite;
    }
}
