/*
 * Ce programme exporte les appels d'un service d'urgence dans un fichier au
 * format XML.
 * @version Mai 2016.
 * @author Thierry Baribaud.
 */
package expcalls;

public class ExpCalls {

    /**
     * Les arguments en ligne de commande permettent de changer le mode de 
     * fonctionnement. Voir GetArgs pour plus de détails.
     * @param Args arguments de la ligne de commande. 
     */
    public static void main(String[] Args) {
        GetArgs MyArgs = new GetArgs(Args);
        
        MyArgs.usage();
        System.out.println(MyArgs);
    }
    
}
