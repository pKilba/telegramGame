package com.example.telegramgame.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "username")
    private String username;

    @Column(name = "score")
    private Integer score;

    @Column(name = "referral_id")
    private String referralId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<UserUpgrade> userUpgrades;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Wallet> wallets;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private FarmingInfo farmingInfo;

    // Constructors, getters, and setters

    public User() {}

    public User(String userId, String username, String referralId) {
        this.userId = userId;
        this.username = username;
        this.referralId = referralId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getReferralId() {
        return referralId;
    }

    public void setReferralId(String referralId) {
        this.referralId = referralId;
    }

    public Set<UserUpgrade> getUserUpgrades() {
        return userUpgrades;
    }

    public void setUserUpgrades(Set<UserUpgrade> userUpgrades) {
        this.userUpgrades = userUpgrades;
    }

    public Set<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(Set<Wallet> wallets) {
        this.wallets = wallets;
    }

    public FarmingInfo getFarmingInfo() {
        return farmingInfo;
    }

    public void setFarmingInfo(FarmingInfo farmingInfo) {
        this.farmingInfo = farmingInfo;
    }
}
