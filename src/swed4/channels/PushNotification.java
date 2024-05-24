package swed4.channels;

import swed4.INotificationChannel;

public class PushNotification implements INotificationChannel {
    @Override
    public void notify(String message) {
        System.out.println("Push: " + message);
    }
}
