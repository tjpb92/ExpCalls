package expcalls;

import bdd.FcallsDAO;
import bdd.Fcalls;
import bdd.EtatTicket;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Comment;
import utils.DBServerException;

/**
 * Ce programme exporte les appels d'un service d'urgence dans un fichier au
 * format XML.
 * 
 * @version Juin 2016
 * @author Thierry Baribaud
 */
public class ExpCalls_0000 implements ExpCallsInterface{

    /**
     * Les arguments en ligne de commande permettent de changer le mode de
     * fonctionnement. Voir GetArgs pour plus de détails.
     *
     * @param MyExpcallsParams paramètres pour l'extraction des appels.
     * @throws java.io.IOException en cas de fichier non lisible ou absent.
     * @throws utils.DBServerException en cas de propriété incorrecte.
     * @throws java.sql.SQLException en cas d'une erreur SQL.
     */
    public ExpCalls_0000(ExpCallsParams MyExpcallsParams) throws IOException, DBServerException, SQLException {

        Calls_0000_XMLDocument MyXMLDocument;
        String MyString;

        // Indique les références du client en commentaires
        MyString = "Client " + MyExpcallsParams.getUname() + " (" + 
                MyExpcallsParams.getUabbname() + "), id=" + MyExpcallsParams.getUnum();

        // Amorçage du fichier XML contenant les résultats.
        MyXMLDocument = new Calls_0000_XMLDocument("tickets", 
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
     * @param MyExpcallsParams paramètres d'extraction des appels.
     * @param MyXMLDocument document XML contenant les appels.
     * @param MyEtatTicket état du ticket.
     */
    @Override
    public void processTickets(ExpCallsParams MyExpcallsParams,
            Calls_0000_XMLDocument MyXMLDocument, EtatTicket MyEtatTicket) {

        Fcalls MyFcalls;
        FcallsDAO MyFcallsDAO;
        int i;
        Ticket_0000 MyTicket_0000;
        Connection MyConnection;

        try {
            MyConnection = MyExpcallsParams.getMyConnection();
            MyFcallsDAO = new FcallsDAO(MyConnection, 0, MyExpcallsParams.getUnum(),
                    MyExpcallsParams.getBegDate(), MyExpcallsParams.getEndDate(),
                    MyEtatTicket);

            i = 0;
            while ((MyFcalls = MyFcallsDAO.select()) != null) {
                i++;
                MyTicket_0000 = new Ticket_0000(MyConnection, MyFcalls, MyEtatTicket);
                System.out.println("Ticket(" + i + ")=" + MyTicket_0000);
                MyXMLDocument.AddToXMLDocument(MyTicket_0000);
            }
            MyFcallsDAO.close();
        } catch (ClassNotFoundException MyException) {
            Logger.getLogger(ExpCalls_0000.class.getName()).log(Level.SEVERE, null, MyException);
        } catch (SQLException MyException) {
            Logger.getLogger(ExpCalls_0000.class.getName()).log(Level.SEVERE, null, MyException);
        }
    }
}
