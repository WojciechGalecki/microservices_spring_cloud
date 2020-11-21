package wg.album.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import wg.album.model.AlbumResponse;
import wg.album.service.AlbumService;

import java.util.List;

@RestController
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/users/{id}/albums")
    public List<AlbumResponse> getUserAlbums(@PathVariable String id) {
        return albumService.getAlbums(id);
    }
}