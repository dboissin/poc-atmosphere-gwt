package fr.dbo.poc.shared.dto;

import java.io.Serializable;

public class RoomInfosDTO implements Serializable {

    private static final long serialVersionUID = -6021626895523328326L;
    private String roomName;
    private Integer nbUsers;
    
    public RoomInfosDTO() {
    }
    public RoomInfosDTO(String name, Integer nbUsers) {
        this.roomName = name;
        this.nbUsers = nbUsers;
    }
    
    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String name) {
        this.roomName = name;
    }
    public Integer getNbUsers() {
        return nbUsers;
    }
    public void setNbUsers(Integer nbUsers) {
        this.nbUsers = nbUsers;
    }

}
