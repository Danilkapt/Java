package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BotHandler extends TelegramLongPollingBot {

    private static final String TOKEN = "7055796656:AAGHzmU7DvP5W34TORF6wGrpzrI8J_4SGAA";
    private static final String CHAT_ID = "@Tutobotvz_bot";

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("Received: " + update.getMessage().getText());
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendServerStatus(AbsSender absSender, Update update) {
        String serverStatus = "Server 1: Online\nServer 2: Online\nServer 3: Offline\nServer 4: Online\nServer 5: Online";
        SendMessage message = new SendMessage()
                .setChatId(update.getMessage().getChatId())
                .setText(serverStatus);
        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new BotHandler());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}