package myhelpertraining_bot;


import net.thauvin.erik.crypto.CryptoPrice;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyHelperBot extends TelegramLongPollingBot {
    public MyHelperBot() {
        super("6976806605:AAEayMSW2rhyfqdcYW5GtLeB7PetLzny6xg");
    }

    @Override
    public void onUpdateReceived(Update update) {
        var chatId = update.getMessage().getChatId();
        var text = update.getMessage().getText();

        try {
            var message = new SendMessage();
            var btc = CryptoPrice.spotPrice("BTC");
            var eth = CryptoPrice.spotPrice("ETH");
            var doge = CryptoPrice.spotPrice("DOGE");

            message.setChatId(chatId);

            if (text.equals("/start")) {
                message.setText("Hello!");
            } else if (text.equals("btc")) {
                message.setText("BTC price: " + btc.getAmount().doubleValue());
            } else if (text.equals("eth")) {
                message.setText("ETH price: " + eth.getAmount().doubleValue());
            } else if (text.equals("doge")) {
                message.setText("ETH price: " + doge.getAmount().doubleValue());
            } else if (text.equals("all")) {
                message.setText("Crypto price: " +
                        "\n BTC price: " + btc.getAmount().doubleValue() +
                        "\n Dogecoin price: " + doge.getAmount().doubleValue() +
                        "\n ETH price: " + eth.getAmount().doubleValue());
            } else if (text.equals("btc,eth")) {
                message.setText("Crypto price: " +
                        "\n BTC price: " + btc.getAmount().doubleValue() +
                        "\n ETH price: " + eth.getAmount().doubleValue());
            } else if (text.equals("eth,btc,doge")) {
                message.setText("Crypto price: " +
                        "\n BTC price: " + btc.getAmount().doubleValue() +
                        "\n Dogecoin price: " + doge.getAmount().doubleValue() +
                        "\n ETH price: " + eth.getAmount().doubleValue());
            } else {
                message.setText("Unknown command!");
            }

            execute(message);
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    @Override
    public String getBotUsername() {
        return "myhelpertraining_bot";
    }

}
