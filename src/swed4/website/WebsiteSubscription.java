package swed4.website;

import swed4.User;

import java.util.ArrayList;
import java.util.List;

public class WebsiteSubscription {
    List<User> users = new ArrayList<>();
    private final Website website;

    public WebsiteSubscription(Website website) {
        this.website = website;
    }
    
    public void addSubscriber(User user){
        users.add(user);
    }
    
    public void checkForUpdate(){
        website.updateWebsiteContent();
        if(website.hasUpdate()){
            notifySubscribers();
            website.resetUpdate();
        }
    }
    
    private void notifySubscribers(){
        for (User user : users) {
            user.SendNotifications(website.getUri().toString() + " has been updated");
        }
    }
}
