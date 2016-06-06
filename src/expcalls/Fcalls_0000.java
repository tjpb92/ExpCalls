/**
 * Fcalls_0000 is a class that describes an active calls. It extends Fcalls
 * class and implements methods to generate CSV and XML records.
 *
 * @version May 2016.
 * @author Thierry Baribaud.
 */
package expcalls;

import bdd.Fcalls;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Fcalls_0000 extends Fcalls {

    private static final DateFormat MyDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Fcalls_0000(Fcalls MyCalls) {
        setCnum(MyCalls.getCnum());
        setCname(MyCalls.getCname());
        setCtel(MyCalls.getCtel());
        setCaddress(MyCalls.getCaddress());
        setCaddress2(MyCalls.getCaddress2());
        setCaccess(MyCalls.getCaccess());
        setCposcode(MyCalls.getCposcode());
        setCity(MyCalls.getCity());
        setCsympt(MyCalls.getCsympt());
        setCnumber4(MyCalls.getCnumber4());
        setCdate(MyCalls.getCdate());
        setCtime(MyCalls.getCtime());
        setCdate2(MyCalls.getCdate2());
        setCtime2(MyCalls.getCtime2());
    }

    public final static String CSV_Title() {
        return ("ID;Nom;Téléphone;Adresse;Complément;Accès;CP;Ville"
                + "Raison d'appel;Digicode;Saisi le;à;Modifié le;à");
    }

    public String toCSV() {
        return (getCnum() + ";" + getCname() + ";" + getCtel() + ";"
                + getCaddress() + ";" + getCaddress2() + ";" + getCaccess() + ";"
                + getCposcode() + ";" + getCity() + ";" + getCsympt() + ";"
                + getCnumber4() + ";"
                + MyDateFormat.format(getCdate()) + ";" + getCtime() + ";"
                + MyDateFormat.format(getCdate2()) + ";" + getCtime2() + ";");
    }

    public void toXML() {
    }
}
