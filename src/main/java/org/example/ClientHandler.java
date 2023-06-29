package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ClientHandler implements  Runnable{

    private Socket clientSocket;

    private MenuPiatti menuP;

    private InetAddress inetAddress;
    public ClientHandler(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
        menuP = new MenuPiatti();
        inetAddress = clientSocket.getInetAddress();
        menuP.esempioDati();
    }

    private boolean menu()
    {
        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            //e.printStackTrace();
            return false;
        }

        PrintWriter out = null;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            //e.printStackTrace();
            return false;
        }

        System.out.println("Connected from: " +inetAddress);
        out.println("Benvenuto Client: "+inetAddress);

        Gson g = new Gson();
        String s ="";
        while (true)
        {
            out.println("Inserisci la tua richiesta (premi exit per uscire): ");
            try {
                s = in.readLine();
                if (s == null) {
                    System.out.println("Exit from: " + inetAddress);
                    clientSocket.close();
                    return false;
                } else if (s.equalsIgnoreCase("exit")) {
                    System.out.println("Exit from: " + inetAddress);
                    clientSocket.close();
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            out.println("Working on...");

            Command cmd  = null;

            try {
                cmd = g.fromJson(s, Command.class);
            } catch (Exception e) {
                ////
            }

            if(cmd!=null)
            {
                cmd = g.fromJson(s, Command.class);
            }
            out.println("Answer: ");
            String result = executeCommand(cmd);
            out.println(result);
        }
    }

    private String executeCommand(Command cmd)
    {
        if(cmd==null)
        {
            return "Error command not recognized";
        }

        if(cmd.cmd.equals("all"))
        {
            return menuP.getListAsJSON();
        }
        else if(cmd.cmd.equals("all_vegans"))
        {
            return menuP.getListAsJSON(menuP.viewPiatti());
        }
        else if(cmd.cmd.equals("most_caloric"))
        {
            return menuP.getListAsJSON(menuP.mostCaloric());
        }

        return "Error command not recognized";
    }

    @Override
    public void run() {
        menu();
    }
}
