package expcalls;

/**
 * Liste des mod�les de rapports XML.
 *
 * ATTENTION : Pour des raisons de confidentialit�, les noms des clients ne
 * peuvent pas �tre indiqu�s ici. On se contente de r�f�rences num�riques.
 *
 * @author Thierry Baribaud
 * @version 0.57
 */
public enum ModeleDeRapport {

//    VES("0105"), identique � VF, TB, le 17/07/2016
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
     * Famille de client 648
     */
    MIQ("0648"),
    /**
     * Famille de client 703
     */
    BRE("0703"),
    /**
     * Famille de client de base
     */
    STD("0000");

    /**
     * Indice du mod�le de rapport.
     */
    private String Indice = "";

    /**
     * Contructeur du mod�le de rapport.
     *
     * @param Indice du mod�le de rapport.
     */
    ModeleDeRapport(String Indice) {
        this.Indice = Indice;
    }

    /**
     * @return l'indice du mod�le de rapport.
     */
    @Override
    public String toString() {
        return (Indice);
    }
}
