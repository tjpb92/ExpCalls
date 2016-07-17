package expcalls;

/**
 * Liste des modèles de rapports XML.
 *
 * @version Juillet 2016
 * @author Thierry Baribaud
 */
public enum ModeleDeRapport {

//    VES("0105"), identique à VF, TB, le 17/07/2016
    SAU("0341"),
    CAR("0513"),
    SOL("0552"),
    GEO("0555"),
    CEG("0567"),
    NEX("0572"),
    PHI("0573"),
    ENE("0582"),
    PRI("0600"),
    VF("0609"),
    BOU("0635"),
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
