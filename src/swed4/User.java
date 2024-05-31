package swed4;

import swed4.channels.AvailableChannels;
import swed4.channels.INotificationChannel;

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
    
    public void addChannels(AvailableChannels... channel){
        notificationChannels.addAll(NotificationSystem.getNotificationChannelList(channel));
    }
    
    public void SendNotifications(String message){
        for(INotificationChannel channel : notificationChannels){
            channel.notify("Hey, " + name + " " + message);
        }
    }
}
