package swed4.channels;

public class EmailChannel implements INotificationChannel {
    @Override
    public void notify(String message) {
        System.out.println("Email: " + message);
    }
}
