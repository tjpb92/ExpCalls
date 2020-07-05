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
 * client 703 dans un fichier au format XML.
 *
 * @author Thierry Baribaud
 * @version 0.57
 */
public class ExpCalls_0703 extends AbstractExpCalls {

    /**
     * Les arguments en ligne de commande permettent de changer le mode de
     * fonctionnement. Voir GetArgs pour plus de d�tails.
     *
     * @param expCallsParams param�tres pour l'extraction des appels.
     * @throws java.io.IOException en cas de fichier non lisible ou absent.
     * @throws utils.DBServerException en cas de propri�t� incorrecte.
     * @throws java.sql.SQLException en cas d'une erreur SQL.
     */
    public ExpCalls_0703(ExpCallsParams expCallsParams) throws IOException, DBServerException, SQLException {

        Calls_0703_XMLDocument xmlDocument;
        String aString;

        // Indique les r�f�rences du client en commentaires
        aString = "Client " + expCallsParams.getUname() + " ("
                + expCallsParams.getUabbname() + "), id=" + expCallsParams.getUnum();

        // Amor�age du fichier XML contenant les r�sultats.
        xmlDocument = new Calls_0703_XMLDocument("tickets",
                expCallsParams.getXSDFilename(), aString);

        // Traitement des appels en cours.
        processTickets(expCallsParams, xmlDocument, EtatTicket.EN_COURS);

        // Traitement des appels archiv�s.
        processTickets(expCallsParams, xmlDocument, EtatTicket.ARCHIVE);

//            A voir plus tard ...
//            xmlDocument.FinalizeXMLDocument(MyArgs.getFileOut());
        xmlDocument.FinalizeXMLDocument(expCallsParams.getXMLFilename());

    }

    /**
     * M�thode qui traite les tickets.
     *
     * @param expCallsParams param�tres d'extraction des appels.
     * @param xmlDocument document XML contenant les appels.
     * @param etatTicket �tat du ticket.
     */
    public void processTickets(ExpCallsParams expCallsParams,
            Calls_0703_XMLDocument xmlDocument, EtatTicket etatTicket) {

        Fcalls fcalls;
        FcallsDAO fcallsDAO;
        int i;
        Ticket_0703 ticket_0703;
        Connection connection;
        int tnum;
        int a6num;

        try {
            connection = expCallsParams.getConnection();

            fcallsDAO = new FcallsDAO(connection, etatTicket);
            if (expCallsParams.isOpenedTicket()) {
                fcallsDAO.filterOpenedTicket();
            }
            if ((tnum = expCallsParams.getTnum()) > 0) {
                fcallsDAO.filterByProvider(tnum);
            }
            if ((a6num = expCallsParams.getA6num()) > 0) {
                fcallsDAO.filterByAgencyId(a6num);
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
                ticket_0703 = new Ticket_0703(connection, fcalls, etatTicket);
                System.out.println("Ticket(" + i + ")=" + ticket_0703);
                xmlDocument.AddToXMLDocument(ticket_0703);
            }
            fcallsDAO.closeSelectPreparedStatement();
        } catch (ClassNotFoundException | SQLException exception) {
            Logger.getLogger(ExpCalls_0000.class.getName()).log(Level.SEVERE, null, exception);
        }
    }

    /**
     *
     * @param expCallsParams param�tres d'ex�cution
     * @param MyXMLDocument document XML
     * @param etatTicket �tat du ticket
     */
    @Override
    public void processTickets(ExpCallsParams expCallsParams, Calls_0000_XMLDocument MyXMLDocument, EtatTicket etatTicket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
