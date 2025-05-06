package com.analyzer.bot.managers;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * Менеджер для формирования сообщений
 */
@Service
public class MessageManager {

    /**
     * Формируем приветственное сообщение
     */
    public SendMessage sendWelcomeMessage(Long chatId, String username) {

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(username + ", welcome to my bot"); // TODO: сделать нормально
        return message;
    }
}
