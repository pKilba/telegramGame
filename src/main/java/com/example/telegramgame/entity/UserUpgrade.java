package com.example.telegramgame.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_upgrades")
public class UserUpgrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "upgrade_id", nullable = false)
    private Upgrade upgrade;

    @Column(name = "current_level")
    private Integer currentLevel;

    // Constructors, getters, and setters

    public UserUpgrade() {}

    public UserUpgrade(User user, Upgrade upgrade, Integer currentLevel) {
        this.user = user;
        this.upgrade = upgrade;
        this.currentLevel = currentLevel;
    }

    // getters and setters
}
