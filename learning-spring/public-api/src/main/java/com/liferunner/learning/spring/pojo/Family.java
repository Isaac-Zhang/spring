package com.liferunner.learning.spring.pojo;

/**
 * 家庭类
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/24
 **/
public class Family {

    private String primaryAccount;

    private String wife;

    private String husband;

    private String son;

    private String daughter;

    public String getPrimaryAccount() {
        return primaryAccount;
    }

    public void setPrimaryAccount(String primaryAccount) {
        this.primaryAccount = primaryAccount;
    }

    public String getWife() {
        return wife;
    }

    public void setWife(String wife) {
        this.wife = wife;
    }

    public String getHusband() {
        return husband;
    }

    public void setHusband(String husband) {
        this.husband = husband;
    }

    public String getSon() {
        return son;
    }

    public void setSon(String son) {
        this.son = son;
    }

    public String getDaughter() {
        return daughter;
    }

    public void setDaughter(String daughter) {
        this.daughter = daughter;
    }

    @Override
    public String toString() {
        return "Family{" +
                "primaryAccount='" + primaryAccount + '\'' +
                ", wife='" + wife + '\'' +
                ", husband='" + husband + '\'' +
                ", son='" + son + '\'' +
                ", daughter='" + daughter + '\'' +
                '}';
    }
}
