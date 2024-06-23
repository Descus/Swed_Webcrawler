package swed4.website;

import swed4.observer.Subject;
import swed4.strategy.ComparisonStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class Website extends Subject {
    private final URI uri;
    private final ComparisonStrategy comparisonStrategy;
    private String websiteContent;
    private boolean update;

    public Website(URI uri, ComparisonStrategy comparisonStrategy){
        this.uri = uri;
        this.comparisonStrategy = comparisonStrategy;
        updateWebsiteContent();
    }

    public void updateWebsiteContent() {
        StringBuilder newWebsiteContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(uri.toURL().openStream(), StandardCharsets.UTF_8))) {
            for (String line; (line = reader.readLine()) != null;) {
                newWebsiteContent.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(!comparisonStrategy.Compare(websiteContent, newWebsiteContent.toString())) {
            Notify();
            websiteContent = newWebsiteContent.toString();
        }
    }
}
