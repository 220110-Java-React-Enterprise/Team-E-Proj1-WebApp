package helper;

import Repository.Scriptor;
import models.Songs;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//connects the webapp the orm for the songs Object
public class SongsHelper extends Scriptor {
    private String connectionStr; // holds the connection string

    //passes the connection string to the orm
    public SongsHelper(String connectionStr) {
        super(connectionStr);
    }

    //Update tables to the object passed in
    //if table did not exist, creates it to avoid errors
    public void updatesTable(Songs obj) {
        BuildStatement(obj);
        updateTable(obj);
    }

    //delete row from table given the id in the object
    public void deleteRow (Songs obj){
        DeleteStatement(obj);
    }

    //creates a table with the data from the object
    public void createTable(Songs obj){
        BuildStatement(obj);
    }

    // overloaded function that prints out the table from specific users, based on the header of the request
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

    //prints entire table
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
