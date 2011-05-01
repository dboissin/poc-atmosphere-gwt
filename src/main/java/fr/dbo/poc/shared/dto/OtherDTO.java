package fr.dbo.poc.shared.dto;

import java.io.Serializable;

public class OtherDTO implements Serializable {

    public OtherDTO() {

    }

    public OtherDTO(String otherString, Integer otherInteger) {
        this.otherString = otherString;
        this.otherInteger = otherInteger;
    }

    private static final long serialVersionUID = 4214185754902038575L;

    private String otherString;

    private Integer otherInteger;

    public String getOtherString() {
        return otherString;
    }

    public void setOtherString(String otherString) {
        this.otherString = otherString;
    }

    public Integer getOtherInteger() {
        return otherInteger;
    }

    public void setOtherInteger(Integer otherInteger) {
        this.otherInteger = otherInteger;
    }

}
