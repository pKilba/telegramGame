package com.example.telegramgame.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "upgrades")
public class Upgrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "skin_name")
    private String skinName;

    @Column(name = "level")
    private Integer level;

    @Column(name = "upgrade_price")
    private Double upgradePrice;

    @OneToMany(mappedBy = "upgrade", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserUpgrade> userUpgrades;

    // Constructors, getters, and setters

    public Upgrade() {}

    public Upgrade(String skinName, Integer level, Double upgradePrice) {
        this.skinName = skinName;
        this.level = level;
        this.upgradePrice = upgradePrice;
    }

    // getters and setters
}
