package fr.dbo.poc.shared.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SomeDTO implements Serializable {

    private static final long serialVersionUID = -6176891495186519452L;

    private String someString;

    private Integer someInteger;

    private List<OtherDTO> otherDTO = new ArrayList<OtherDTO>();

    public String getSomeString() {
        return someString;
    }

    public void setSomeString(String someString) {
        this.someString = someString;
    }

    public Integer getSomeInteger() {
        return someInteger;
    }

    public void setSomeInteger(Integer someInteger) {
        this.someInteger = someInteger;
    }

    public List<OtherDTO> getOtherDTO() {
        return otherDTO;
    }

    public void setOtherBeans(List<OtherDTO> otherDTO) {
        this.otherDTO = otherDTO;
    }


}
