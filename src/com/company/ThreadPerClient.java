package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ThreadPerClient implements Runnable {

    private Socket socket = null;
    private Thread t3, t4;


    public ThreadPerClient(Socket s) {
        socket = s;
    }

    public void run() {

        try {
            while (true) {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream());

                Calcul question = new Calcul(in.readLine());
                double res = Calcul.solve(question);
                out.println(res);
                out.flush();
            }
        } catch (IOException e) {
            System.err.println("La calculatrice cliente s'est déconnectée.");
        }
    }
}
