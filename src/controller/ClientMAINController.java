/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ClientImpelmentMod;
import model.DataBase.ClientDB;
import model.interf.ServerInterface;
import view.VChatFram;
import view.VSignIn;

/**
 *
 * @author Deu
 */
public class ClientMAINController {

    ServerInterface serverIntRef;
    ClientImpelmentMod clientImplRef;

    VChatFram chatFrameRef;
    
    VSignIn signInRef;

    public ClientMAINController() {
       // chatFrameRef = new VChatFram(this);
       // chatFrameRef.setVisible(true);
        signInRef = new VSignIn(this);
        signInRef.setVisible(true);

        try {
            Registry reg = LocateRegistry.getRegistry("127.0.0.1");
            serverIntRef = (ServerInterface) reg.lookup("OurAwesomeChatService");
            System.out.println("client connected to server");

            clientImplRef = new ClientImpelmentMod(this);
            serverIntRef.register(clientImplRef);

        } catch (Exception ex) {
            System.out.println("error: can't contact server");
        }
    }

    //--------model(implementation)calls this method---------
    public void sendTextToCon(String s) {
        System.out.println(s);
        //todo: sent the string to the view
        
        chatFrameRef.displayMsgToclients(s);
    }

    //-----------view calls this method------------
    public void sendToAllCon(String s) {
        try {
            serverIntRef.sendToAll(s);
        } catch (RemoteException ex) {
            System.out.println("error: can't send the message");
        }
    }

    //-------------to sent obj of class ClientDB to the server------------
    public void copyOfClientDbObj(ClientDB clientDBRef) {
        try {
            serverIntRef.insertNewUser(clientDBRef);
        } catch (RemoteException ex) {
            System.out.println("error: can't insert new user!");
        }
    }

    public static void main(String[] args) {
        new ClientMAINController();
    }
}
