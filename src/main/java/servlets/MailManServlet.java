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

//    public void init(){
//        try {
//            String connectionStr = ConnectionManager.getConnection();
//        } catch(IOException e) {
//            FileLogger.getFileLogger().log(e);
//        }
//    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        connectionStr = ConnectionManager.getConnection(); // this is establishing a connection to database
        // for use by ORM
        try {
            String string = req.getRequestURI();
            String[] options = string.split("/");
            String option = options[options.length - 1];
            if (req.getQueryString() != null) {
                if (option.contains("users")) {
                    ObjectMapper mapper = new ObjectMapper();
                    Users user = mapper.readValue(req.getInputStream(), Users.class);
                    UsersHelper usersHelper = new UsersHelper(connectionStr);
                    usersHelper.printTable(user, resp, req.getQueryString());

                    resp.setStatus(203);
                } else if (option.contains("songs")) {
                    ObjectMapper mapper = new ObjectMapper();
                    Songs song = mapper.readValue(req.getInputStream(), Songs.class);
                    SongsHelper songsHelper = new SongsHelper(connectionStr);
                    songsHelper.printTable(song, resp, req.getQueryString());
                    resp.setStatus(200);
                }
            } else {
                if (option.equals("users")) {
                    ObjectMapper mapper = new ObjectMapper();
                    Users user = mapper.readValue(req.getInputStream(), Users.class);
                    UsersHelper usersHelper = new UsersHelper(connectionStr);
                    usersHelper.printTable(user, resp);

                    resp.setStatus(203);
                } else if (option.equals("songs")) {
                    ObjectMapper mapper = new ObjectMapper();
                    Songs song = mapper.readValue(req.getInputStream(), Songs.class);
                    SongsHelper songsHelper = new SongsHelper(connectionStr);
                    songsHelper.printTable(song, resp);
                    resp.setStatus(200);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
//        try
//        {
//            String string = req.getRequestURI();
//            String[] options = string.split("/");
//            String option = options[options.length-1];
//            if(option.equals("users")){
//                List<Users> usersList = UsersHelper.readTable(new Users());
//                ObjectMapper mapper = new ObjectMapper();
//                String jsonString = mapper.writeValueAsString(usersList);
//                resp.getWriter().print(jsonString);
//                resp.setStatus(200);
//            }
//            else if(option.equals("songs")){
//                List<Songs> songsList = SongsHelper.readTable(new Songs());
//                ObjectMapper mapper = new ObjectMapper();
//                String jsonString = mapper.writeValueAsString(songsList);
//                resp.getWriter().print(jsonString);
//                resp.setStatus(200);
//            }
//        }
//        catch(Exception e)
//        {
//            FileLogger.getFileLogger().log(e);
//            resp.setStatus(500);
//        }
    }

        /** doPost adds a row to the table

     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        connectionStr = ConnectionManager.getConnection(); // this is establishing a connection to database
                                                            // for use by ORM
        try {
            String string = req.getRequestURI();
            String[] options = string.split("/");
            String option = options[options.length-1];
            if(option.equals("users")){
                ObjectMapper mapper = new ObjectMapper();
                Users user = mapper.readValue(req.getInputStream(), Users.class);
                UsersHelper usersHelper = new UsersHelper(connectionStr);
                usersHelper.updatesTable(user);

                resp.setStatus(203);
            } else if(option.equals("songs")){
                ObjectMapper mapper = new ObjectMapper();
                Songs song = mapper.readValue(req.getInputStream(), Songs.class);
                SongsHelper songsHelper = new SongsHelper(connectionStr);
                songsHelper.updatesTable(song);
                resp.setStatus(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        connectionStr = ConnectionManager.getConnection();
        try {
            String string = req.getRequestURI();
            String[] options = string.split("/");
            String option = options[options.length-1];
            if(option.equals("users")){
                ObjectMapper mapper = new ObjectMapper();
                Users user = mapper.readValue(req.getInputStream(), Users.class);
                UsersHelper usersHelper = new UsersHelper(connectionStr);
                usersHelper.deleteRow(user);

                resp.setStatus(203);
            } else if(option.equals("songs")){
                ObjectMapper mapper = new ObjectMapper();
                Songs song = mapper.readValue(req.getInputStream(), Songs.class);
                SongsHelper songsHelper = new SongsHelper(connectionStr);
                songsHelper.deleteRow(song);
                resp.setStatus(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        connectionStr = ConnectionManager.getConnection(); // this is establishing a connection to database
        // for use by ORM
        try {
            String string = req.getRequestURI();
            String[] options = string.split("/");
            String option = options[options.length-1];
            if(option.equals("users")){
                ObjectMapper mapper = new ObjectMapper();
                Users user = mapper.readValue(req.getInputStream(), Users.class);
                UsersHelper usersHelper = new UsersHelper(connectionStr);
                usersHelper.createTable(user);

                resp.setStatus(203);
            } else if(option.equals("songs")){
                ObjectMapper mapper = new ObjectMapper();
                Songs song = mapper.readValue(req.getInputStream(), Songs.class);
                SongsHelper songsHelper = new SongsHelper(connectionStr);
                songsHelper.createTable(song);
                resp.setStatus(200);
            }
        } catch (Exception e) {
            e.printStackTrace();
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }
    }
}
