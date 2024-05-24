package swed4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class Website {
    private final URI uri;
    private String websiteContent;
    private boolean update;

    public Website(URI uri){
        this.uri = uri;
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

        if(!newWebsiteContent.toString().equals(websiteContent)) update = true;
        websiteContent = newWebsiteContent.toString();
    }

    public boolean hasUpdate(){
        return update;
    }
    
    public void resetUpdate(){
        update = false;
    }

    public URI getUri() {
        return uri;
    }
}
