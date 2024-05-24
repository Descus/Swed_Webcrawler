package swed4;

import java.util.ArrayList;
import java.util.List;

public class WebsiteSubscription {
    List<Tuple<User, Integer>> users = new ArrayList<>();
    private final Website website;

    public WebsiteSubscription(Website website) {
        this.website = website;
    }
    
    public void addSubscriber(User user, Integer frequency) {
        users.add(new Tuple<>(user, frequency));
    }
    
    public void checkForUpdate(){
        if(website.hasUpdate()){
            notifySubscribers();
        }
    }
    
    private void notifySubscribers(){
        for (Tuple<User, Integer> user : users) {
            user.getT().SendNotifications(website.getUri().toString() + " has been updated");
        }
        website.resetUpdate();
    }
}
