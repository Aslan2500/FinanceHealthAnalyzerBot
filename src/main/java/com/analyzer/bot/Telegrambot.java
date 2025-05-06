package com.analyzer.bot;

import com.analyzer.bot.managers.UserManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Telegrambot extends TelegramLongPollingBot {

    private final String name;

    private final UserManager userManager;

    public Telegrambot(@Value("${telegram.bot.token}") String token,
                       @Value("${telegram.bot.name}") String name,
                       UserManager userManager) {
        super(token);
        this.name = name;
        this.userManager = userManager;
    }

    private void sendMessage(Long chatId, String messageToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message incomeMessage = update.getMessage();

            Long chatId = incomeMessage.getChatId();
            if (incomeMessage.hasText()) {
                String text = incomeMessage.getText();

                if (text.equals("/start")) {
                    SendMessage message = userManager.createUser(incomeMessage);

                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                } else {
                    sendMessage(chatId, "Unknown command");
                }
            } else {
                sendMessage(chatId, "Incorrect data form");
            }
        }
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }
}
