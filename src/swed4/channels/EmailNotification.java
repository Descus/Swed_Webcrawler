package swed4.channels;

import swed4.INotificationChannel;

public class EmailNotification implements INotificationChannel {
    @Override
    public void notify(String message) {
        System.out.println("Email: " + message);
    }
}
