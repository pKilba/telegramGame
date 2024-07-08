package com.example.telegramgame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class TelegramController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public void handleWebhook(@RequestBody TelegramUpdate update) {
        if (update.getMessage() != null) {
            Long userId = update.getMessage().getFrom().getId();
            String username = update.getMessage().getFrom().getUsername();

            User user = new User(userId, username);
            userRepository.save(user);

            // Дополнительная логика обработки
        }
    }
}
