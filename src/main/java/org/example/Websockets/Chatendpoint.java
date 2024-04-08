package org.example.Websockets;

import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.example.Classes.UserRec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ServerEndpoint("/chat")
public class Chatendpoint {
    private static final Map<String, Session> sessions=new HashMap<>();// sessionid and session
    private static final Map<String, String> roommap=new HashMap<>();// sessionid and roomname
    private static final List<String> room=new ArrayList<>();//roonnames
    private static final List<UserRec> userList = new ArrayList<>();//for usernames with its sesions
    private static int onlinememb=0;
    private void sendmessage(String message, Session session){
        String temproomname="";
        for (int k=0; k<userList.size(); k++){
            if(session==userList.get(k).getSession()){
                temproomname=userList.get(k).getRoomname();
            }
        }
        List<UserRec> roomusers=new ArrayList<>();
        for(int i=0; i<userList.size(); i++){
            if(userList.get(i).getRoomname().equals(temproomname)){
                roomusers.add(userList.get(i));
            }
        }
        roomusers.forEach(item->{
            try {
                item.getSession().getBasicRemote().sendText(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @OnOpen
    public void onOpen(Session session){
        sessions.put(session.getId(),session);
    }
    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println(message);
        String[] temp = message.split(":");
        UserRec userRec = new UserRec();
        if(temp[0].equalsIgnoreCase("nameandroomname")){
            String[] temp2=temp[1].split("\n");
            userRec.setName(temp2[0]);
            userRec.setRoomname(temp2[1]);
            userRec.setSession(session);
            userList.add(userRec);
        }else if(temp[0].equalsIgnoreCase("chatText")){
            sendmessage(message, session);
        }
    }
}
