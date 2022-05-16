package com.mycompany.socketes1udp;

public class UDPClientManager {
    public static void main(String[] args){
        UDPClient client = new UDPClient(2000);
        
        client.send("RICHIESTA DATA E ORA");
        
        client.receive();
    }
}
