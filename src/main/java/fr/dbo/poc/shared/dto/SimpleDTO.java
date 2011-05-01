package fr.dbo.poc.shared.dto;

import java.io.Serializable;

public class SimpleDTO implements Serializable {

    private static final long serialVersionUID = -2683595743002213892L;

    public SimpleDTO() {
    }

    public SimpleDTO(String content) {
        this.content = content;
    }

    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}
