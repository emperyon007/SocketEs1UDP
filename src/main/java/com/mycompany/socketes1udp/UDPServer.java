package com.mycompany.socketes1udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPServer {
    int port;
    DatagramSocket dSocket;
    DatagramPacket inPacket;
    DatagramPacket outPacket;
    byte[] buffer;
    String messageIn, messageOut;
    Date d;
    
    public UDPServer(int port){
        this.port = port;
        try {
            dSocket = new DatagramSocket(port);
            System.out.println("Apertura porta in corso!");
        } catch (SocketException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void receiver(){
        buffer = new byte[256];
        inPacket = new DatagramPacket(buffer, buffer.length);
        try {
            dSocket.receive(inPacket);
        } catch (IOException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        InetAddress clientAddress = inPacket.getAddress();
	int clientPort = inPacket.getPort();
        
        messageIn = new String(inPacket.getData(), 0, inPacket.getLength());
        System.out.println("Client " + clientAddress +
                ":" + clientPort + "> " + messageIn);
    }
    
    void sender(){
        d = new Date();
        messageOut = d.toString();

        InetAddress clientAddress = inPacket.getAddress();
	int clientPort = inPacket.getPort();
        
        outPacket = new DatagramPacket(messageOut.getBytes(), messageOut.length(), clientAddress, clientPort);
        
        try {
            dSocket.send(outPacket);
        } catch (IOException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Risposta inviata!");
    }
}
