package org.example;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MenuPiatti
{
    private List <Piatto> piatti;

    public MenuPiatti()
    {
        piatti = new ArrayList<>();
    }

    public void esempioDati()
    {
        piatti.add(new Piatto(1,"il piatto di milano",false,"Risotto alla milanese",325.94));
        piatti.add(new Piatto(2,"il famoso piatto",false,"Costata",133.0));
        piatti.add(new Piatto(3,"il piatto vegano",true,"insalata",50.0));
        piatti.add(new Piatto(4,"il piatto che mangiano tutti i bambini",false,"patatine e cotoletta",300.50));
    }

    public List viewPiatti()
    {
        List <Piatto> piattiVegani = new ArrayList<>();
        for (Piatto p: piatti
             ) {
            if(p.isVegan()==true)
            {
                piattiVegani.add(new Piatto(p.getId(),p.getDescrizione(),p.isVegan(),p.getName(),p.getCalories()));
            }
        }

        return piattiVegani;
    }

    public Piatto  mostCaloric()
    {
        Piatto p2 = null;
        int id = 0;
        double max=0;
        int i=0;

        for (Piatto p: piatti
        ) {
            if(i==0)
            {
                max = p.getCalories();
                id = p.getId();
            }
            if(p.getCalories()>max)
            {
                max = p.getCalories();
                id = p.getId();
            }
            i++;
        }

        for (Piatto p: piatti
        ) {
            if(p.getId()==id)
            {
                p2 = new Piatto(p.getId(),p.getDescrizione(),p.isVegan(),p.getName(),p.getCalories());
                return p2;
            }
        }

        return p2;
    }

    public String getListAsJSON()
    {
        Gson gson = new Gson();
        String jsonS = gson.toJson(piatti);
        return jsonS;
    }

    public String getListAsJSON(Piatto p)
    {
        Gson gson = new Gson();
        String jsonS = gson.toJson(p);
        return jsonS;
    }

    public String getListAsJSON(List piatti)
    {
        Gson gson = new Gson();
        String jsonS = gson.toJson(piatti);
        return jsonS;
    }
}
