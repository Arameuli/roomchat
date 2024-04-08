package org.example.Classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "room")
public class Room {
    @Id
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="maxmember")
    private int maxmember;

    public Room(int id, String name, int maxmember) {
        this.id = id;
        this.name = name;
        this.maxmember = maxmember;
    }

    public Room(String name, int maxmember) {
        this.name = name;
        this.maxmember = maxmember;
    }

    public Room() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxmember() {
        return maxmember;
    }

    public void setMaxmember(int maxmember) {
        this.maxmember = maxmember;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxmember=" + maxmember +
                '}';
    }
}
