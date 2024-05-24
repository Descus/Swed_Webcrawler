package swed4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User {
    String name;
    private final List<INotificationChannel> notificationChannels = new ArrayList<>();
    
    public User(String name, Collection<INotificationChannel> channels){
        this.name = name;
        notificationChannels.addAll(channels);
    }
    
    public void addChannel(AvailableChannels channel){
        notificationChannels.add(System.getNotificationChannel(channel));
    }
    
    public void SendNotifications(String message){
        for(INotificationChannel channel : notificationChannels){
            channel.notify("Hey, " + name + " " + message);
        }
    }
}
