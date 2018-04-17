package expcalls;

import bdd.EtatTicket;
import bdd.Fcalls;
import bdd.FcallsDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBServerException;

/**
 * Ce programme exporte les appels des services d'urgence de la famille du
 * client 609 dans un fichier au format XML.
 *
 * @author Thierry Baribaud
 * @version 0.42
 */
public class ExpCalls_0609 extends AbstractExpCalls {

    /**
     * Les arguments en ligne de commande permettent de changer le mode de
     * fonctionnement. Voir GetArgs pour plus de détails.
     *
     * @param expCallsParams paramètres pour l'extraction des appels.
     * @throws java.io.IOException en cas de fichier non lisible ou absent.
     * @throws utils.DBServerException en cas de propriété incorrecte.
     * @throws java.sql.SQLException en cas d'une erreur SQL.
     */
    public ExpCalls_0609(ExpCallsParams expCallsParams) throws IOException, DBServerException, SQLException {

        Calls_0609_XMLDocument MyXMLDocument;
        String aString;

        // Indique les références du client en commentaires
        aString = "Client " + expCallsParams.getUname() + " ("
                + expCallsParams.getUabbname() + "), id=" + expCallsParams.getUnum();

        // Amorçage du fichier XML contenant les résultats.
        MyXMLDocument = new Calls_0609_XMLDocument("tickets",
                expCallsParams.getXSDFilename(), aString);

        // Traitement des appels en cours.
        processTickets(expCallsParams, MyXMLDocument, EtatTicket.EN_COURS);

        // Traitement des appels archivés.
        processTickets(expCallsParams, MyXMLDocument, EtatTicket.ARCHIVE);

//            A voir plus tard ...
//            MyXMLDocument.FinalizeXMLDocument(MyArgs.getFileOut());
        MyXMLDocument.FinalizeXMLDocument(expCallsParams.getXMLFilename());

    }

    /**
     * Méthode qui traite les tickets.
     *
     * @param expCallsParams paramètres d'extraction des appels.
     * @param MyXMLDocument document XML contenant les appels.
     * @param etatTicket état du ticket.
     */
    public void processTickets(ExpCallsParams expCallsParams,
            Calls_0609_XMLDocument MyXMLDocument, EtatTicket etatTicket) {

        Fcalls fcalls;
        FcallsDAO fcallsDAO;
        int i;
        Ticket_0609 ticket_0609;
        Connection connection;

        try {
            connection = expCallsParams.getConnection();

            fcallsDAO = new FcallsDAO(connection, etatTicket);
            if (expCallsParams.isOpenedTicket()) {
                fcallsDAO.filterOpenedTicket();
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
                ticket_0609 = new Ticket_0609(connection, fcalls, etatTicket);
                System.out.println("Ticket(" + i + ")=" + ticket_0609);
                MyXMLDocument.AddToXMLDocument(ticket_0609);
            }
            fcallsDAO.closeSelectPreparedStatement();
        } catch (ClassNotFoundException | SQLException exception) {
            Logger.getLogger(ExpCalls_0000.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

    /**
     *
     * @param expCallsParams paramètres d'exécution
     * @param MyXMLDocument document XML
     * @param etatTicket état du ticket
     */
    @Override
    public void processTickets(ExpCallsParams expCallsParams, Calls_0000_XMLDocument MyXMLDocument, EtatTicket etatTicket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
