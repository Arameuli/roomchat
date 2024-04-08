package org.example.Classes;

import jakarta.websocket.Session;

public class UserRec {
    private String name;
    private Session session;
    private String roomname;

    public UserRec() {
    }

    public UserRec(String name, Session session, String roomname) {
        this.name = name;
        this.session = session;
        this.roomname = roomname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    @Override
    public String toString() {
        return "UserRec{" +
                "name='" + name + '\'' +
                ", session=" + session +
                ", roomname='" + roomname + '\'' +
                '}';
    }
}
