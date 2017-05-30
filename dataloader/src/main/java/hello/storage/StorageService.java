package hello.storage;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by satya on 5/28/17.
 */
public interface StorageService {
    void store(MultipartFile file);

    void init();
}
