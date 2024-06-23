package swed4;

import swed4.channels.*;
import swed4.strategy.ComparisonStrategy;
import swed4.website.Website;

import java.net.URI;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class NotificationSystem {

    private static NotificationSystem instance;
    private ComparisonStrategy comparisonStrategy;
    private final Map<String, Website> knownSites = new HashMap<>();
    private final Map<String, User> users = new HashMap<>();

    public User createUser(String name, URI website, AvailableChannels... channels) {
        User user = new User(name, getNotificationChannelList(channels));
        createSubscription(website, user);
        users.put(name, user);
        return user;
    }

    public void createSubscription(URI website, User user) {
        if (!knownSites.containsKey(website.toString())) {
            knownSites.put(website.toString(), new Website(website, comparisonStrategy));
        }
        Website site = knownSites.get(website.toString());
        site.Attach(user);
    }

    public void checkForUpdates() {
        knownSites.forEach((_, website) -> website.updateWebsiteContent());
    }

    public static Collection<INotificationChannel> getNotificationChannelList(AvailableChannels[] channels) {
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

    public void setComparisonStrategy(ComparisonStrategy comparisonStrategy) {
        this.comparisonStrategy = comparisonStrategy;
    }

    public static NotificationSystem getInstance() {
        if (instance == null) {
            instance = new NotificationSystem();
        }
        return instance;
    }
}
