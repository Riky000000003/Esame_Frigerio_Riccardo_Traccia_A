package org.example;

public class Command
{
    String cmd;
    String param1;
    String param2;


    public Command(String cmd, String param1, String param2) {
        this.cmd = cmd;
        this.param1 = param1;
        this.param2 = param2;
    }

    //{"cmd":"all"}
    //{"cmd":"all_vegans"}
    //{"cmd":"most_caloric"}
}
