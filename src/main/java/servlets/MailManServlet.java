package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import helper.SongsHelper;
import helper.UsersHelper;
import models.Songs;
import models.Users;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MailManServlet extends HttpServlet {

    public void init() {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    {
        try
        {
            String string = req.getRequestURI();
            String[] options = string.split("/");
            String option = options[options.length-1];
            if(option.equals("users")){
                List<Users> usersList = UsersHelper.readTable(new Users());
                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writeValueAsString(usersList);
                resp.getWriter().print(jsonString);
                resp.setStatus(200);
            }
            else if(option.equals("songs")){
                List<Songs> songsList = SongsHelper.readTable(new Songs());
                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writeValueAsString(songsList);
                resp.getWriter().print(jsonString);
                resp.setStatus(200);
            }
        }
        catch(Exception e)
        {
            FileLogger.getFileLogger().log(e);
            resp.setStatus(500);
        }

    }
}
