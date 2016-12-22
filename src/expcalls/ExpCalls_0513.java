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
 * client 513 dans un fichier au format XML.
 *
 * @author Thierry Baribaud
 * @version 0.27
 */
public class ExpCalls_0513 extends AbstractExpCalls {

    /**
     * Les arguments en ligne de commande permettent de changer le mode de
     * fonctionnement. Voir GetArgs pour plus de détails.
     *
     * @param MyExpcallsParams paramètres pour l'extraction des appels.
     * @throws java.io.IOException en cas de fichier non lisible ou absent.
     * @throws utils.DBServerException en cas de propriété incorrecte.
     * @throws java.sql.SQLException en cas d'une erreur SQL.
     */
    public ExpCalls_0513(ExpCallsParams MyExpcallsParams) throws IOException, DBServerException, SQLException {

        Calls_0513_XMLDocument MyXMLDocument;
        String MyString;

        // Indique les références du client en commentaires
        MyString = "Client " + MyExpcallsParams.getUname() + " ("
                + MyExpcallsParams.getUabbname() + "), id=" + MyExpcallsParams.getUnum();

        // Amorçage du fichier XML contenant les résultats.
        MyXMLDocument = new Calls_0513_XMLDocument("tickets",
                MyExpcallsParams.getXSDFilename(), MyString);

        // Traitement des appels en cours.
        processTickets(MyExpcallsParams, MyXMLDocument, EtatTicket.EN_COURS);

        // Traitement des appels archivés.
        processTickets(MyExpcallsParams, MyXMLDocument, EtatTicket.ARCHIVE);

//            A voir plus tard ...
//            MyXMLDocument.FinalizeXMLDocument(MyArgs.getFileOut());
        MyXMLDocument.FinalizeXMLDocument(MyExpcallsParams.getXMLFilename());

    }

    /**
     * Méthode qui traite les tickets.
     *
     * @param MyExpcallsParams paramètres d'extraction des appels.
     * @param MyXMLDocument document XML contenant les appels.
     * @param MyEtatTicket état du ticket.
     */
    public void processTickets(ExpCallsParams MyExpcallsParams,
            Calls_0513_XMLDocument MyXMLDocument, EtatTicket MyEtatTicket) {

        Fcalls MyFcalls;
        FcallsDAO MyFcallsDAO;
        int i;
        Ticket_0513 MyTicket_0513;
        Connection MyConnection;

        try {
            MyConnection = MyExpcallsParams.getConnection();

            MyFcallsDAO = new FcallsDAO(MyConnection, MyEtatTicket);
            MyFcallsDAO.filterByDate(MyExpcallsParams.getUnum(),
                    MyExpcallsParams.getBegDate(), MyExpcallsParams.getEndDate());
            MyFcallsDAO.setSelectPreparedStatement();

            i = 0;
            while ((MyFcalls = MyFcallsDAO.select()) != null) {
                i++;
                MyTicket_0513 = new Ticket_0513(MyConnection, MyFcalls, MyEtatTicket);
                System.out.println("Ticket(" + i + ")=" + MyTicket_0513);
                MyXMLDocument.AddToXMLDocument(MyTicket_0513);
            }
            MyFcallsDAO.closeSelectPreparedStatement();
        } catch (ClassNotFoundException MyException) {
            Logger.getLogger(ExpCalls_0000.class.getName()).log(Level.SEVERE, null, MyException);
        } catch (SQLException MyException) {
            Logger.getLogger(ExpCalls_0000.class.getName()).log(Level.SEVERE, null, MyException);
        }
    }

    /**
     *
     * @param MyExpcallsParams paramètres d'exécution
     * @param MyXMLDocument document XML
     * @param MyEtatTicket état du ticket
     */
    @Override
    public void processTickets(ExpCallsParams MyExpcallsParams, Calls_0000_XMLDocument MyXMLDocument, EtatTicket MyEtatTicket) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
