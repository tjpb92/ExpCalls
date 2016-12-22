package expcalls;

/**
 * Liste des modèles de rapports XML.
 *
 * ATTENTION : Pour des raisons de confidentialité, les noms des clients ne
 * peuvent pas être indiqués ici. On se contente de références numériques.
 *
 * @author Thierry Baribaud
 * @version 0.27
 */
public enum ModeleDeRapport {

//    VES("0105"), identique à VF, TB, le 17/07/2016
    /**
     * Famille de client 341
     */
    SAU("0341"),
    /**
     * Famille de client 513
     */
    CAR("0513"),
    /**
     * Famille de client 552
     */
    SOL("0552"),
    /**
     * Famille de client 555
     */
    GEO("0555"),
    /**
     * Famille de client 567
     */
    CEG("0567"),
    /**
     * Famille de client 572
     */
    NEX("0572"),
    /**
     * Famille de client 573
     */
    PHI("0573"),
    /**
     * Famille de client 582
     */
    ENE("0582"),
    /**
     * Famille de client 600
     */
    PRI("0600"),
    /**
     * Famille de client 609
     */
    VF("0609"),
    /**
     * Famille de client 635
     */
    BOU("0635"),
    /**
     * Famille de client standard
     */
    STD("0000");

    /**
     * Indice du modèle de rapport.
     */
    private String Indice = "";

    /**
     * Contructeur du modèle de rapport.
     *
     * @param Indice du modèle de rapport.
     */
    ModeleDeRapport(String Indice) {
        this.Indice = Indice;
    }

    /**
     * @return l'indice du modèle de rapport.
     */
    @Override
    public String toString() {
        return (Indice);
    }
}
