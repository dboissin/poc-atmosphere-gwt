package fr.dbo.poc.shared.dto;

public class MessageDTO {
    
    private String user;
    private String message;
    private String room;

    public MessageDTO() {
    }

    public MessageDTO(String user, String message, String room) {
        super();
        this.user = user;
        this.message = message;
        this.setRoom(room);
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

}
