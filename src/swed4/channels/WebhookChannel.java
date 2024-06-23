package swed4.channels;

public class WebhookChannel implements INotificationChannel {
    @Override
    public void notify(String message) {
        System.out.println("Webhook: " + message);
    }
}

