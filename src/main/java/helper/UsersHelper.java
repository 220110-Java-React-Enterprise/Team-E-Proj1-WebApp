package helper;

import Repository.Scriptor;
import models.Users;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class UsersHelper extends Scriptor {
    private String connectionStr;
    public UsersHelper(String connectionStr) {
        super(connectionStr);
    }
    public void updatesTable(Users obj){
        BuildStatement(obj);
        updateTable(obj);

    }
    public void getTable(Users obj){

    }

    public void deleteRow (Users obj){
    DeleteStatement(obj);
    }

    public void createTable(Users obj){
        BuildStatement(obj);
    }

    public void printTable(Users obj, HttpServletResponse resp, String userID){
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
    public void printTable(Users obj, HttpServletResponse resp){
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
