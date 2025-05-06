package com.analyzer.bot.commands;

import com.analyzer.bot.entity.BotUser;
import com.analyzer.bot.managers.MessageManager;
import com.analyzer.bot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
public class StartCommand implements Command {

    private final UserRepository userRepository;

    private final MessageManager messageManager;

    @Override
    public SendMessage apply(Update update) {
        Message message = update.getMessage();

        User user = message.getFrom();

        BotUser botUser = new BotUser();
        botUser.setName(user.getUserName());
        botUser.setTelegramId(user.getId());
        botUser.setCreatedAt(OffsetDateTime.now());
        userRepository.save(botUser);

        return messageManager.sendWelcomeMessage(message.getChatId(), user.getUserName());
    }
}
