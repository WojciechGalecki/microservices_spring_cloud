package wg.user.client;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wg.user.model.AlbumResponse;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "album-service", fallbackFactory = AlbumFallbackFactory.class)
public interface AlbumServiceClient {

    @GetMapping("/users/{id}/albums")
    List<AlbumResponse> getAlbums(@PathVariable String id);
}

@Component
class AlbumFallbackFactory implements FallbackFactory<AlbumServiceClient> {
    @Override
    public AlbumServiceClient create(Throwable cause) {
        return new AlbumFallback(cause);
    }
}

@Slf4j
class AlbumFallback implements AlbumServiceClient {
    private final Throwable cause;

    public AlbumFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public List<AlbumResponse> getAlbums(String id) {
        log.error("An error occurred during calling the Album Service: {}", cause.getLocalizedMessage());
        return Collections.emptyList();
    }
}
