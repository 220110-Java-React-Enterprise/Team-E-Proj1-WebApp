package helper;

import Repository.Scriptor;
import models.Songs;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class SongsHelper extends Scriptor {
    private String connectionStr;

    public SongsHelper(String connectionStr) {
        super(connectionStr);
    }

    public void updatesTable(Songs obj) {
        BuildStatement(obj);
        updateTable(obj);
    }

    public void deleteRow (Songs obj){
        DeleteStatement(obj);
    }

    public void createTable(Songs obj){
        BuildStatement(obj);
    }

    public void printTable(Songs obj, HttpServletResponse resp, String userID){
        ArrayList<ArrayList<Object>> allData = readTable(obj, userID);
        String printString = "{\n";
        for (int i = 0; i < allData.size(); i++) {
            printString += "\t{";
            for (int j = 0; j < allData.get(i).size(); j++) {
                printString += allData.get(i).get(j);
                printString += " ";
            }
            printString += "\n}\n";
        }
        printString += "}";
        try {
            resp.getWriter().write(printString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void printTable(Songs obj, HttpServletResponse resp){
        ArrayList<ArrayList<Object>> allData = readTable(obj);
        String printString = "{\n";
        for (int i = 0; i < allData.size(); i++) {
            printString += "\t{";
            for (int j = 0; j < allData.get(i).size(); j++) {
                printString += allData.get(i).get(j);
                printString += " ";
            }
            printString += "\n}\n";
        }
        printString += "}";
        try {
            resp.getWriter().write(printString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
