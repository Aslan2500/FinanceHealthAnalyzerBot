package com.analyzer.managers;

import com.analyzer.UserRepository;
import com.analyzer.entity.BotUser;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.OffsetDateTime;

/**
 * Менеджер для работы с {@link BotUser}
 */
@Service
@RequiredArgsConstructor
public class UserManager {

    private final UserRepository userRepository;

    private final MessageManager messageManager;

    /**
     * Сохраняем нового пользователя
     */
    public SendMessage createUser(Message message) {
        User user = message.getFrom();

        BotUser botUser = new BotUser();
        botUser.setName(user.getUserName());
        botUser.setTelegramId(user.getId());
        botUser.setCreatedAt(OffsetDateTime.now());
        userRepository.save(botUser);

        return messageManager.sendWelcomeMessage(message.getChatId(), user.getUserName());
    }
}
