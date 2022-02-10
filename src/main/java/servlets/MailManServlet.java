package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import helper.SongsHelper;
import helper.UsersHelper;
import models.Songs;
import models.Users;
import utils.ConnectionManager;
import utils.FileLogger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MailManServlet extends HttpServlet {
    String connectionStr;

    //prints out the table of the object placed in or, a specific row if given
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        connectionStr = ConnectionManager.getConnection(); // this is establishing a connection to database
        // for use by ORM
        try {
            String string = req.getRequestURI();

            //gets the last string after the / in the URI
            String[] options = string.split("/");
            String option = options[options.length - 1];

            //checks if there is a query string
            if (req.getQueryString() != null) {

                //if uri contained users
                if (option.contains("users")) {
                    ObjectMapper mapper = new ObjectMapper();
                    Users user = mapper.readValue(req.getInputStream(), Users.class);
                    UsersHelper usersHelper = new UsersHelper(connectionStr);

                    //prints user table where id equals id given in the query string
                    usersHelper.printTable(user, resp, req.getQueryString());

                    resp.setStatus(203);

                    //if the user has songs in uri
                } else if (option.contains("songs")) {
                    ObjectMapper mapper = new ObjectMapper();
                    Songs song = mapper.readValue(req.getInputStream(), Songs.class);
                    SongsHelper songsHelper = new SongsHelper(connectionStr);

                    //prints user table where id equals id given in the query string
                    songsHelper.printTable(song, resp, req.getQueryString());
                    resp.setStatus(200);
                }

                //if they didn't enter a query string
            } else {

                //prints entire users table
                if (option.equals("users")) {
                    ObjectMapper mapper = new ObjectMapper();
                    Users user = mapper.readValue(req.getInputStream(), Users.class);
                    UsersHelper usersHelper = new UsersHelper(connectionStr);
                    usersHelper.printTable(user, resp);

                    resp.setStatus(203);

                    //prints entire songs table
                } else if (option.equals("songs")) {
                    ObjectMapper mapper = new ObjectMapper();
                    Songs song = mapper.readValue(req.getInputStream(), Songs.class);
                    SongsHelper songsHelper = new SongsHelper(connectionStr);
                    songsHelper.printTable(song, resp);
                    resp.setStatus(200);
                }
            }
        } catch (Exception e) {
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
    }

    /** doPost adds a row to the table
     */

    //inserts new row into table,unless id is given, then it updates existing row
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        connectionStr = ConnectionManager.getConnection(); // this is establishing a connection to database
        // for use by ORM
        try {

            //gets last string after / in URI
            String string = req.getRequestURI();
            String[] options = string.split("/");
            String option = options[options.length-1];

            //updates user table
            if(option.equals("users")){
                ObjectMapper mapper = new ObjectMapper();
                Users user = mapper.readValue(req.getInputStream(), Users.class);
                UsersHelper usersHelper = new UsersHelper(connectionStr);
                usersHelper.updatesTable(user);

                resp.setStatus(203);

                //updates songs table
            } else if(option.equals("songs")){
                ObjectMapper mapper = new ObjectMapper();
                Songs song = mapper.readValue(req.getInputStream(), Songs.class);
                SongsHelper songsHelper = new SongsHelper(connectionStr);
                songsHelper.updatesTable(song);
                resp.setStatus(200);
            }
        } catch (Exception e) {
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
    }

    //deletes specified row from table
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        connectionStr = ConnectionManager.getConnection();
        try {

            //gets last string after / in uri
            String string = req.getRequestURI();
            String[] options = string.split("/");
            String option = options[options.length-1];

            //deletes row from users table
            if(option.equals("users")){
                ObjectMapper mapper = new ObjectMapper();
                Users user = mapper.readValue(req.getInputStream(), Users.class);
                UsersHelper usersHelper = new UsersHelper(connectionStr);
                usersHelper.deleteRow(user);

                resp.setStatus(203);

                //deletes row from songs table
            } else if(option.equals("songs")){
                ObjectMapper mapper = new ObjectMapper();
                Songs song = mapper.readValue(req.getInputStream(), Songs.class);
                SongsHelper songsHelper = new SongsHelper(connectionStr);
                songsHelper.deleteRow(song);
                resp.setStatus(200);
            }
        } catch (Exception e) {
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
    }

    //creates a new table in the database
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        connectionStr = ConnectionManager.getConnection(); // this is establishing a connection to database
        // for use by ORM
        try {

            //gets string after last / in uri
            String string = req.getRequestURI();
            String[] options = string.split("/");
            String option = options[options.length-1];

            //creates users table
            if(option.equals("users")){
                ObjectMapper mapper = new ObjectMapper();
                Users user = mapper.readValue(req.getInputStream(), Users.class);
                UsersHelper usersHelper = new UsersHelper(connectionStr);
                usersHelper.createTable(user);

                resp.setStatus(203);

                //creates songs table
            } else if(option.equals("songs")){
                ObjectMapper mapper = new ObjectMapper();
                Songs song = mapper.readValue(req.getInputStream(), Songs.class);
                SongsHelper songsHelper = new SongsHelper(connectionStr);
                songsHelper.createTable(song);
                resp.setStatus(200);
            }
        } catch (Exception e) {
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
    }
}
