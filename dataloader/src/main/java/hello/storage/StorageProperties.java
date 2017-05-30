package hello.storage;

/**
 * Created by satya on 5/28/17.
 */

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("hello/storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
