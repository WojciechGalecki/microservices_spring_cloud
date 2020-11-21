package wg.album.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import wg.album.data.AlbumEntity;
import wg.album.model.AlbumResponse;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class AlbumService {
    private final ModelMapper modelMapper;

    public AlbumService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<AlbumResponse> getAlbums(String userId) {
        log.info("Trying to fetch albums for user with id: {}", userId);
        List<AlbumEntity> albumEntities = getAlbumEntities(userId);
        List<AlbumResponse> albumResponses = modelMapper
                .map(albumEntities, new TypeToken<List<AlbumResponse>>() {}.getType());
        log.info("Successfully fetched {} album/s for user with id: {}", albumResponses.size(), userId);
        return albumResponses;
    }

    // TODO add repository
    private List<AlbumEntity> getAlbumEntities(String userId) {
        AlbumEntity albumEntity = new AlbumEntity();
        albumEntity.setUserId(userId);
        albumEntity.setAlbumId("album1Id");
        albumEntity.setDescription("album 1 description");
        albumEntity.setId(1L);
        albumEntity.setName("album 1 name");

        AlbumEntity albumEntity2 = new AlbumEntity();
        albumEntity2.setUserId(userId);
        albumEntity2.setAlbumId("album2Id");
        albumEntity2.setDescription("album 2 description");
        albumEntity2.setId(2L);
        albumEntity2.setName("album 2 name");

        return Arrays.asList(albumEntity, albumEntity2);
    }
}