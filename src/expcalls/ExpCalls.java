/*
 * Ce programme exporte les appels d'un service d'urgence dans un fichier au
 * format XML.
 * @version Mai 2016.
 * @author Thierry Baribaud.
 */
package expcalls;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExpCalls {

    /**
     * Les arguments en ligne de commande permettent de changer le mode de 
     * fonctionnement. Voir GetArgs pour plus de détails.
     * @param Args arguments de la ligne de commande. 
     */
    public static void main(String[] Args) {
        try {
            GetArgs MyArgs = new GetArgs(Args);
            System.out.println(MyArgs);
        } catch (GetArgsException ex) {
            Logger.getLogger(ExpCalls.class.getName()).log(Level.SEVERE, null, ex);
            GetArgs.usage();
        }
    }
    
}
