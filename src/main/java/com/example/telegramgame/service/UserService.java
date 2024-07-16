package com.example.telegramgame.service;

import com.example.telegramgame.entity.User;
import com.example.telegramgame.entity.Wallet;
import com.example.telegramgame.repository.UserRepository;
import com.example.telegramgame.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    public User saveOrUpdateUser(User user) {
        Optional<User> existingUser = userRepository.findByUserId(user.getUserId());
        if (existingUser.isPresent()) {
//            User updatedUser = existingUser.get();
//            updatedUser.setUsername(user.getUsername());
//            updatedUser.setScore(user.getScore());
//            updatedUser.setUserId(user.getUserId());
//            updatedUser.setReferralId(user.getReferralId()); // Ensure referralId is updated
 //           return userRepository.save(updatedUser);
            return user;
        } else {
            User newUser = userRepository.save(user);
            createInitialWallets(newUser); // Create wallets for the new user
            return newUser;
        }
    }

    public Optional<User> getUserByTelegramId(String userId) {
        return userRepository.findByUserId(userId);
    }

    public Wallet updateBalance(String telegramId, Wallet.Currency currency, double amount) {
        Optional<User> existingUser = userRepository.findByUserId(telegramId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            Optional<Wallet> walletOpt = walletRepository.findByUserAndCurrency(user, currency);
            Wallet wallet;
            if (walletOpt.isPresent()) {
                wallet = walletOpt.get();
                wallet.setBalance(wallet.getBalance() + amount);
            } else {
                wallet = new Wallet(user, currency, amount);
            } 
            return walletRepository.save(wallet);
        } else {
            throw new IllegalArgumentException("User not found with telegramId: " + telegramId);
        }
    }

    public Set<Wallet> getBalances(String telegramId) {
        Optional<User> existingUser = userRepository.findByUserId(telegramId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            return walletRepository.findByUser(user);
        } else {
            throw new IllegalArgumentException("User not found with telegramId: " + telegramId);
        }
    }

    private void createInitialWallets(User user) {
        for (Wallet.Currency currency : Wallet.Currency.values()) {
            Wallet wallet = new Wallet(user, currency, 0.0);
            walletRepository.save(wallet);
        }
    }
}
