package com.company;

import java.io.IOException;
import java.net.ServerSocket;

public class Calculator {
    private static Thread t;

    public void run() {
        ServerSocket entryPoint;
        try {
            entryPoint = new ServerSocket(7777);
            t = new Thread(new AcceptConnection(entryPoint));
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
