package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AcceptConnection implements Runnable {
    private ServerSocket calculatorSocket = null;
    private Socket socket = null;
    public Thread t1;

    public AcceptConnection(ServerSocket ss) {
        calculatorSocket = ss;
    }

    public void run() {
        try {
            while (true) {
                socket = calculatorSocket.accept();
                System.out.println("Connexion entrante...");
                t1 = new Thread(new ThreadPerClient(socket));
                t1.start();
            }
        } catch (IOException e) {
            System.err.println("Erreur serveur");
        }
    }
}
