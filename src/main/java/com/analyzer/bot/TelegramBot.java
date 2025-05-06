package com.analyzer.bot;

import com.analyzer.bot.commands.Consts;
import com.analyzer.bot.handler.CommandsHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final String name;

    private final CommandsHandler commandsHandler;

    public TelegramBot(@Value("${telegram.bot.token}") String token,
                       @Value("${telegram.bot.name}") String name,
                       CommandsHandler commandsHandler) {
        super(token);
        this.name = name;
        this.commandsHandler = commandsHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            String chatId = update.getMessage().getChatId().toString();

            if (update.getMessage().getText().startsWith("/")) {
                sendMessage(commandsHandler.handleCommands(update));
            } else {
                sendMessage(new SendMessage(chatId, Consts.UNKNOWN_CALL));
            }

        }
    }

    /**
     * Метод отправляет сообщение
     */
    private void sendMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
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
