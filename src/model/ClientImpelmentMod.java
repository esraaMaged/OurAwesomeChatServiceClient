/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.ClientMAINController;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import model.interf.ClientInterface;

/**
 *
 * @author Deu
 */
public class ClientImpelmentMod extends UnicastRemoteObject implements ClientInterface{
    
    ClientMAINController cc;

    public ClientImpelmentMod(ClientMAINController cc)throws RemoteException
    {
        this.cc=cc;
    }
    @Override
    public void recieve(String s) throws RemoteException {
        cc.sendTextToCon(s);
    }
    
}
