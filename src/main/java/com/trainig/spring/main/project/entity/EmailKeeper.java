package com.trainig.spring.main.project.entity;

import java.util.Objects;

public class EmailKeeper {

    private String keeperName;
    private String keeperEmail;
    private String keeperPassword;

    public EmailKeeper() {
    }

    public EmailKeeper(String keeperName, String keeperEmail, String keeperPassword) {
        this.keeperName = keeperName;
        this.keeperEmail = keeperEmail;
        this.keeperPassword = keeperPassword;
    }

    public String getKeeperName() {
        return keeperName;
    }

    public void setKeeperName(String keeperName) {
        this.keeperName = keeperName;
    }

    public String getKeeperEmail() {
        return keeperEmail;
    }

    public void setKeeperEmail(String keeperEmail) {
        this.keeperEmail = keeperEmail;
    }

    public String getKeeperPassword() {
        return keeperPassword;
    }

    public void setKeeperPassword(String keeperPassword) {
        this.keeperPassword = keeperPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailKeeper that = (EmailKeeper) o;
        return Objects.equals(keeperName, that.keeperName) &&
                Objects.equals(keeperEmail, that.keeperEmail) &&
                Objects.equals(keeperPassword, that.keeperPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keeperName, keeperEmail, keeperPassword);
    }

    @Override
    public String toString() {
        return "EmailKeeper{" +
                "keeperName='" + keeperName + '\'' +
                ", keeperEmail='" + keeperEmail + '\'' +
                '}';
    }

}
