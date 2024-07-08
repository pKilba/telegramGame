package com.example.telegramgame;

public class TelegramUpdate {
    private TelegramMessage message;

    public TelegramMessage getMessage() {
        return message;
    }

    public void setMessage(TelegramMessage message) {
        this.message = message;
    }

    public static class TelegramMessage {
        private TelegramUser from;

        public TelegramUser getFrom() {
            return from;
        }

        public void setFrom(TelegramUser from) {
            this.from = from;
        }
    }

    public static class TelegramUser {
        private Long id;
        private String username;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
