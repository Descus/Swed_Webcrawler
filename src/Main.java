import swed4.AvailableChannels;
import swed4.System;
import swed4.User;

import java.net.URI;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        System system = System.getInstance();
        try {
            system.createUser("Peter", new URI("https://www.anisearch.de/anime/calendar"), 5, AvailableChannels.Email, AvailableChannels.Push);
            system.createUser("Gibson", new URI("https://www.anisearch.de/anime/calendar"), 9, AvailableChannels.Email);
            system.createUser("Mr.Frage", new URI("https://www.anisearch.de/anime/calendar"), 8, AvailableChannels.Email, AvailableChannels.Push);
            User rauch = system.createUser("Rauch", new URI("https://www.anisearch.de/anime/calendar"),1, AvailableChannels.Push);
            rauch.addChannel(AvailableChannels.Email);
            
            while (true){
                system.checkForUpdates();
            }
            
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}