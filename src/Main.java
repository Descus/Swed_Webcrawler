import swed4.channels.AvailableChannels;
import swed4.NotificationSystem;
import swed4.User;

import java.net.URI;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        NotificationSystem notificationSystem = NotificationSystem.getInstance();
        try {
            notificationSystem.createUser("Peter", new URI("https://www.anisearch.de/anime/calendar"), AvailableChannels.Email, AvailableChannels.Push);
            notificationSystem.createUser("Gibson", new URI("https://www.anisearch.de/anime/calendar"), AvailableChannels.Email);
            notificationSystem.createUser("Mr.Frage", new URI("https://github.com"), AvailableChannels.Email, AvailableChannels.Push);
            User rauch = notificationSystem.createUser("Mr.Rauch", new URI("https://www.anisearch.de/anime/calendar"), AvailableChannels.Push);
            rauch.addChannels(AvailableChannels.Email);
            
            Thread thread = new Thread(() -> {
                while (true){
                    try {
                        notificationSystem.checkForUpdates();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            
            thread.start();
            
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}