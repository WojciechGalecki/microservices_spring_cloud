package wg.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wg.user.model.AlbumResponse;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "album-service", fallback = AlbumFallBack.class)
public interface AlbumServiceClient {

    @GetMapping("/users/{id}/albums")
    List<AlbumResponse> getAlbums(@PathVariable String id);
}

@Component
class AlbumFallBack implements AlbumServiceClient {

    @Override
    public List<AlbumResponse> getAlbums(String id) {
        return Collections.emptyList();
    }
}
