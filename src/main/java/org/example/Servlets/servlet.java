package org.example.Servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Classes.Room;
import org.example.DatabaseManager.DatabaseManager;

import java.io.IOException;
import java.util.List;

@WebServlet("/room")
public class servlet extends HttpServlet {
    private DatabaseManager databaseManager;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        databaseManager = DatabaseManager.getInstance();
        List<Room> list= databaseManager.read();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);
        list.forEach(item ->{
            System.out.println(item);
        });
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String roomname=req.getParameter("roomname");
        int maxmember=Integer.parseInt(req.getParameter("maxmember"));
        databaseManager = DatabaseManager.getInstance();
        System.out.println(roomname+" "+maxmember);
        Room room=new Room(roomname,maxmember);
        databaseManager.write(room);
    }
}
