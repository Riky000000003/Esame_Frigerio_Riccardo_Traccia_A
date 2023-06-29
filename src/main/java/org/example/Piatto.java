package org.example;

public class Piatto
{
    private int id;
    private String descrizione;
    private boolean vegan;
    private String name;
    private double calories;


    public Piatto(int id, String descrizione, boolean vegan, String name, double calories) {
        this.id = id;
        this.descrizione = descrizione;
        this.vegan = vegan;
        this.name = name;
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }
}
