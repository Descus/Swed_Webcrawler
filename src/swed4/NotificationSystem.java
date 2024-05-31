package swed4;

import swed4.channels.*;
import swed4.website.Website;
import swed4.website.WebsiteSubscription;

import java.net.URI;
import java.util.*;

public class NotificationSystem {
    
    private static NotificationSystem instance;
    private final Map<String, Website> knownSites = new HashMap<>();
    private final Map<User, List<WebsiteSubscription>> subscriptionMap = new HashMap<>();
    private final Map<String, User> users = new HashMap<>();
    
    public User createUser(String name, URI website, AvailableChannels... channels){
        
        User user = new User(name, getNotificationChannelList(channels));
        createSubscription(website, user);
        users.put(name, user);
        return user;
    }

    public void createSubscription(URI website, User user) {
        if (!knownSites.containsKey(website.toString())) {
            knownSites.put(website.toString(), new Website(website));
        }
        WebsiteSubscription newSub = new WebsiteSubscription(knownSites.get(website.toString()));
        newSub.addSubscriber(user);
        if (!subscriptionMap.containsKey(user)) {
            List<WebsiteSubscription> subs = new ArrayList<>();
            subs.add(newSub);
            subscriptionMap.put(user, subs);
        } else {
            subscriptionMap.get(user).add(newSub);
        }
    }

    public void checkForUpdates(){
        subscriptionMap.forEach((_, subscription) -> subscription.forEach(WebsiteSubscription::checkForUpdate));
    }
    
    public static Collection<INotificationChannel> getNotificationChannelList(AvailableChannels[] channels){
        List<INotificationChannel> channelList = new ArrayList<>();
        for (AvailableChannels channel : channels) {
            channelList.add(getNotificationChannel(channel));
        }
        return channelList;
    }

    public static INotificationChannel getNotificationChannel(AvailableChannels channel) {
        return switch (channel) {
            case Push -> new PushChannel();
            case Email -> new EmailChannel();
            case Webhook -> new WebhookChannel();
        };
    }

    public static NotificationSystem getInstance(){
        if(instance == null){
            instance = new NotificationSystem();
        }
        return instance;
    }
}
