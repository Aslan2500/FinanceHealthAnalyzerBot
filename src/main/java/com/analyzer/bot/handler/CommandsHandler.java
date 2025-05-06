package com.analyzer.bot.handler;

import com.analyzer.bot.commands.Command;
import com.analyzer.bot.commands.Consts;
import com.analyzer.bot.commands.StartCommand;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;

/**
 * Обработчик запросов
 */
@Service
public class CommandsHandler {

    private final Map<String, Command> commands;

    public CommandsHandler(StartCommand startCommand) {
        this.commands = Map.of("/start", startCommand);
    }

    /**
     * Обработка запросов начинающихся с '/'
     */
    public SendMessage handleCommands(Update update) {
        String message = update.getMessage().getText();
        String command = message.split(" ")[0];

        Long chatId = update.getMessage().getChatId();

        Command commandHandler = commands.get(command);

        if (commandHandler != null) {
            return commandHandler.apply(update);
        }
        return new SendMessage(String.valueOf(chatId), Consts.UNKNOWN_COMMAND);
    }
}
