package expcalls;

import bdd.EtatTicket;

/**
 * Interface pour les objets de type ExpCalls_xxxx.
 * 
 * @version Juin 2016
 * @author Thierry Baribaud
 */
public interface ExpCallsInterface {
    abstract void processTickets(ExpCallsParams MyExpcallsParams,
            Calls_0000_XMLDocument MyXMLDocument, EtatTicket MyEtatTicket);
}
