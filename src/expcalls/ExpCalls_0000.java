package expcalls;

import bdd.FcallsDAO;
import bdd.Fcalls;
import bdd.EtatTicket;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBServerException;

/**
 * Ce programme exporte les appels d'un service d'urgence dans un fichier au
 * format XML.
 *
 * @version 0.44
 * @author Thierry Baribaud
 */
public class ExpCalls_0000 extends AbstractExpCalls {

    /**
     * Les arguments en ligne de commande permettent de changer le mode de
     * fonctionnement. Voir GetArgs pour plus de détails.
     *
     * @param expcallsParams paramètres pour l'extraction des appels.
     * @throws java.io.IOException en cas de fichier non lisible ou absent.
     * @throws utils.DBServerException en cas de propriété incorrecte.
     * @throws java.sql.SQLException en cas d'une erreur SQL.
     */
    public ExpCalls_0000(ExpCallsParams expcallsParams) throws IOException, DBServerException, SQLException {
        
        Calls_0000_XMLDocument MyXMLDocument;
        String aString;

        // Indique les références du client en commentaires
        aString = "Client " + expcallsParams.getUname() + " ("
                + expcallsParams.getUabbname() + "), id=" + expcallsParams.getUnum();

        // Amorçage du fichier XML contenant les résultats.
        MyXMLDocument = new Calls_0000_XMLDocument("tickets",
                expcallsParams.getXSDFilename(), aString);

// Traitement des appels en cours.
        processTickets(expcallsParams, MyXMLDocument, EtatTicket.EN_COURS);

        // Traitement des appels archivés.
        processTickets(expcallsParams, MyXMLDocument, EtatTicket.ARCHIVE);

//            A voir plus tard ...
//            MyXMLDocument.FinalizeXMLDocument(MyArgs.getFileOut());
        MyXMLDocument.FinalizeXMLDocument(expcallsParams.getXMLFilename());
        
    }

    /**
     * Méthode qui traite les tickets.
     *
     * @param expCallsParams paramètres d'extraction des appels.
     * @param MyXMLDocument document XML contenant les appels.
     * @param etatTicket état du ticket.
     */
    @Override
    public void processTickets(ExpCallsParams expCallsParams,
            Calls_0000_XMLDocument MyXMLDocument, EtatTicket etatTicket) {
        
        Fcalls fcalls;
        FcallsDAO fcallsDAO;
        int i;
        Ticket_0000 ticket_0000;
        Connection connection;
        int tnum;
        
        try {
            connection = expCallsParams.getConnection();
            
            fcallsDAO = new FcallsDAO(connection, etatTicket);
            if (expCallsParams.isOpenedTicket()) {
                fcallsDAO.filterOpenedTicket();
            }
            if ((tnum = expCallsParams.getTnum()) > 0) {
                fcallsDAO.filterByProvider(tnum);
            }
            fcallsDAO.filterByDate(expCallsParams.getUnum(),
                    expCallsParams.getBegDate(), expCallsParams.getEndDate());
            fcallsDAO.setSelectPreparedStatement();
            if (expCallsParams.getDebugMode() == true) {
                System.out.println("stmt=" + fcallsDAO.getSelectStatement());
            }
            
            i = 0;
            while ((fcalls = fcallsDAO.select()) != null) {
                i++;
                ticket_0000 = new Ticket_0000(connection, fcalls, etatTicket);
                System.out.println("Ticket(" + i + ")=" + ticket_0000);
                MyXMLDocument.AddToXMLDocument(ticket_0000);
            }
            fcallsDAO.closeSelectPreparedStatement();
        } catch (ClassNotFoundException | SQLException exception) {
            Logger.getLogger(ExpCalls_0000.class.getName()).log(Level.SEVERE, null, exception);
        }
    }
}
