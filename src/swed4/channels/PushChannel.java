package swed4.channels;

public class PushChannel implements INotificationChannel {
    @Override
    public void notify(String message) {
        System.out.println("Push: " + message);
    }
}
