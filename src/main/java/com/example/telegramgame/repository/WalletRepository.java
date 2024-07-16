package com.example.telegramgame.repository;
import com.example.telegramgame.entity.User;
import com.example.telegramgame.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.Set;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUserAndCurrency(User user, Wallet.Currency currency);
    Set<Wallet> findByUser(User user);
}
