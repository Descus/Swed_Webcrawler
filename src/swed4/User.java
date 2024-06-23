package swed4;

import swed4.observer.Observer;
import swed4.channels.AvailableChannels;
import swed4.channels.INotificationChannel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements Observer {
    String name;
    private final List<INotificationChannel> notificationChannels = new ArrayList<>();
    
    public User(String name, Collection<INotificationChannel> channels){
        this.name = name;
        notificationChannels.addAll(channels);
    }
    
    public void addChannels(AvailableChannels... channel){
        notificationChannels.addAll(NotificationSystem.getNotificationChannelList(channel));
    }

    @Override
    public void update() {
        for(INotificationChannel channel : notificationChannels){
            channel.notify("Hey, " + name + " a Website has been  updated!");
        }
    }
}
