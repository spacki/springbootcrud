package com.ge.hc.emrad.xer.domain;

import com.sun.istack.internal.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by karstenspakowski on 21/03/17.
 */
@Entity
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "name is required.")
    private String name;

    @NotEmpty(message = "city is required.")
    private String city;


    @NotNull
    @Pattern(regexp="^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$")
    private String imsAddress;


    @Min(9000)
    @Max(10000)
    private int webservicePort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getWebservicePort() {
        return webservicePort;
    }

    public void setWebservicePort(int webservicePort) {
        this.webservicePort = webservicePort;
    }

    public String getImsAddress() {
        return imsAddress;
    }

    public void setImsAddress(String imsAddress) {
        this.imsAddress = imsAddress;
    }

    public Site() {
    }

    public Site(String name, String city) {
        this.name = name;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Site{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
