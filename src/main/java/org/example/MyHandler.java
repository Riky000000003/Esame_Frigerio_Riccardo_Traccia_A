package org.example;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URI;

public class MyHandler implements HttpHandler {

    private MenuPiatti menuP;
    public MyHandler()
    {
        menuP = new MenuPiatti();
        menuP.esempioDati();
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody(); //si riempe nel post
        URI uri = exchange.getRequestURI(); //si riempe nel get

        String params="";
        String method = exchange.getRequestMethod();
        if(method.equals("POST"))
        {
            params = read(is);
        }
        else if(method.equals("GET"))
        {
            params = uri.getQuery();
        }

        System.out.println(method + ":" +params);
        Gson g = new Gson();

        String response;
        response = executeCmd(method, g, params);
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private String read(InputStream is) {
        BufferedReader br = new BufferedReader( new InputStreamReader(is) );
        System.out.println("\n");
        String received = "";
        while (true) {
            String s = "";
            try {
                if ((s = br.readLine()) == null)
                {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(s);
            received += s;
        }
        return received;
    }

    private String executeCmd( String method, Gson g, String params)
    {
       Command cmd = null;
       if(method.equals("GET"))
       {
           String[] response;
           response = params.split("=");
           String response2 = response[1];
           if(response2.equals("all"))
           {
               return menuP.getListAsJSON();
           }
           else if(response2.equals("all_vegans"))
           {
               return menuP.getListAsJSON(menuP.viewPiatti());
           }
           else if(response2.equals("most_caloric"))
           {
               return menuP.getListAsJSON(menuP.mostCaloric());
           }
           return "Error cmd not recognized";
       }
       else if(method.equals("POST"))
       {
           cmd = g.fromJson(params, Command.class);
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

           return "Error cmd not recognized";
       }

       return "Error cmd not recognized";
    }
}
