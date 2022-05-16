package com.mycompany.socketes1udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPClient {
    private int port;
    private InetAddress serverAddress;
    private DatagramSocket dSocket;
    private DatagramPacket outPacket;
    private DatagramPacket inPacket;
    byte[] buffer;
    String message;
    String response;
   
    public UDPClient(int port)
    {
        this.port = port; 
        try 
        {
            serverAddress = InetAddress.getLocalHost();
        } 
        catch (UnknownHostException ex) 
        {
            Logger.getLogger(
                    UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void send(String message)
    {
        try 
        {
            dSocket = new DatagramSocket();
            
            outPacket = new DatagramPacket(
                    message.getBytes(), message.length(), serverAddress, port);

            dSocket.send(outPacket);
        } 
        catch (SocketException se)
        {
            Logger.getLogger(
                    UDPClient.class.getName()).log(Level.SEVERE, null, se);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(
                    UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void receive()
    {
        buffer = new byte[256];
        inPacket = new DatagramPacket(buffer, buffer.length);
        
        try 
        {
            dSocket.receive(inPacket);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(
                    UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response = new String(inPacket.getData(), 0, inPacket.getLength());
        System.out.println("Data e ora del server: " + response);
    }
}
