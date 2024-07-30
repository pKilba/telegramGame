package com.example.telegramgame.service;

import com.example.telegramgame.entity.User;
import com.example.telegramgame.entity.Wallet;
import com.example.telegramgame.entity.FarmingInfo;
import com.example.telegramgame.repository.UserRepository;
import com.example.telegramgame.repository.FarmingInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FarmingInfoRepository farmingInfoRepository;

    public User saveOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    public Wallet updateBalance(String userId, Wallet.Currency currency, double amount) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Wallet wallet = user.getWallets().stream()
                .filter(w -> w.getCurrency() == currency)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found"));

        wallet.setBalance(wallet.getBalance() + amount);
        userRepository.save(user);

        return wallet;
    }

    public Set<Wallet> getBalances(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return user.getWallets();
    }

    public FarmingInfo getFarmingInfo(String userId) {
        return farmingInfoRepository.findByUserUserId(userId);
    }

    public FarmingInfo updateFarmingInfo(FarmingInfo farmingInfo) {
        return farmingInfoRepository.save(farmingInfo);
    }

    public FarmingInfo claimTokens(String userId) {
        FarmingInfo farmingInfo = getFarmingInfo(userId);
        if (farmingInfo == null) {
            throw new IllegalArgumentException("Farming info not found");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextClaimTime = farmingInfo.getLastClaimTime().plusSeconds(farmingInfo.getWaitTime());

        if (now.isBefore(nextClaimTime)) {
            throw new IllegalArgumentException("Too early to claim");
        }

        // Update the last claim time and tokens
        farmingInfo.setLastClaimTime(now);
        User user = farmingInfo.getUser();
        Wallet wallet = user.getWallets().stream()
                .filter(w -> w.getCurrency() == Wallet.Currency.JUMPBIT)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found"));
        wallet.setBalance(wallet.getBalance() + farmingInfo.getTokenIncrement());
        saveOrUpdateUser(user);

        return updateFarmingInfo(farmingInfo);
    }
}
