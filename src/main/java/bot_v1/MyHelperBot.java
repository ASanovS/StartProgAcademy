package bot_v1;


import net.thauvin.erik.crypto.CryptoPrice;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MyHelperBot extends TelegramLongPollingBot {
    public MyHelperBot() {
        super("6976806605:AAEayMSW2rhyfqdcYW5GtLeB7PetLzny6xg");
    }

    @Override
    public void onUpdateReceived(Update update) {
        var chatId = update.getMessage().getChatId();
        var text = update.getMessage().getText();

        try {
            if (text.equals("/start")) {
                sendMessage(chatId, "Hello! Input 'btc', 'eth' or 'doge'");
            } else if (text.equals("btc")) {
                sendPicture(chatId, "btc.png");
                sendPrice(chatId, "BTC");
            } else if (text.equals("eth")) {
                sendPicture(chatId, "eth.png");
                sendPrice(chatId, "ETH");
            } else if (text.equals("doge")) {
                sendPicture(chatId, "doge.png");
                sendPrice(chatId, "DOGE");
            } else if (text.equals("/Buy")) {
                sendMessage(chatId, "Ok! Input please format: 'btc 200'");
            } else {
                String[] tokens = text.split(" ");
                if (tokens.length == 2) {
                    String currency = tokens[0].toLowerCase();
                    double amount = Double.parseDouble(tokens[1]);
                    calculate(chatId, currency, amount);
                } else {
                    sendMessage(chatId, "Unknown command!");
                }
            }
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    void calculate(long chatID, String name, double inputSum) throws Exception {
        var spotPrice = CryptoPrice.spotPrice(name.toUpperCase());
        sendMessage(chatID, name.toUpperCase() + " you will receive cryptocurrencies = " + inputSum / spotPrice.getAmount().doubleValue());
    }

    void sendPrice(long chatId, String name) throws Exception {
        var price = CryptoPrice.spotPrice(name.toUpperCase());
        sendMessage(chatId, name + " price: " + price.getAmount().doubleValue());
    }

    void sendPicture(long chatId, String name) throws Exception {
        var photo = getClass().getClassLoader().getResourceAsStream(name);
        var message = new SendPhoto();
        message.setChatId(chatId);
        message.setPhoto(new InputFile(photo, name));
        execute(message);
    }

    void sendMessage(long chatId, String text) throws Exception {
        var message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        execute(message);
    }

    @Override
    public String getBotUsername() {
        return "myhelpertraining_bot";
    }
}
