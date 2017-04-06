package com.ge.hc.emrad.xer.domain;

/**
 * Created by karstenspakowski on 05/04/17.
 */
public class Mapping {

    int userId;

    int siteId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    @Override
    public String toString() {
        return "Mapping{" +
                "userId=" + userId +
                ", siteId=" + siteId +
                '}';
    }
}
