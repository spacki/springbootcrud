package com.ge.hc.emrad.xer.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

/**
 * Created by karstenspakowski on 21/03/17.
 */
@Entity
public class ReportingPhysician {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Site> sites;

    @NotEmpty(message = "Last Name is required.")
    @Size(min = 2, message = "Last Name must be at least 2 characters.")
    private String lastName;

    @NotEmpty(message = "First Name is required.")
    @Size(min = 2, message = "First Name must be at least 2 characters.")
    private String firstName;

    @NotEmpty(message = "user id is required.")
    private String userId;

    @NotEmpty(message = "email address is required.")
    private String email;

    @NotEmpty(message = "reporting physician must belong to one site")
    public String homeDomain;

    public Date created;

    public Date lastSynchronized;

    public boolean activeStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastSynchronized() {
        return lastSynchronized;
    }

    public void setLastSynchronized(Date lastSynchronized) {
        this.lastSynchronized = lastSynchronized;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getHomeDomain() {
        return homeDomain;
    }

    public void setHomeDomain(String homeDomain) {
        this.homeDomain = homeDomain;
    }


    public void addSite(Site site) {
        if (!getSites().contains(site)) {
            getSites().add(site);
        }
        if (!site.getReportingPhysician().contains(this)) {
            site.getReportingPhysician().add(this);
        }
    }

    public Collection<Site> getSites() {
        return sites;
    }

    @Override
    public String toString() {
        return "ReportingPhysician{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", homeDomain='" + homeDomain + '\'' +
                ", created=" + created +
                ", lastSynchronized=" + lastSynchronized +
                ", activeStatus=" + activeStatus +
                ", number of mapped sites=" + this.getSites().size() +
                '}';
    }
}
