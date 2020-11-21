package wg.user.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import wg.user.client.AlbumServiceClient;
import wg.user.data.UserEntity;
import wg.user.data.UserRepository;
import wg.user.model.UserDetails;
import wg.user.model.UserResponse;

import java.util.ArrayList;
import java.util.UUID;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    //private final RestTemplate restTemplate;
    private final AlbumServiceClient albumServiceClient;

    //@Value("${albums.url}")
    //private String albumUrl;

    public UserService(ModelMapper modelMapper,
                       UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       AlbumServiceClient albumServiceClient) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.albumServiceClient = albumServiceClient;
    }

    public void createUser(UserDetails userDetails) {
        log.info("Trying to create new user with email: {}", userDetails.getEmail());
        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setEncryptedPassword(passwordEncoder.encode(userDetails.getPassword()));
        userRepository.save(userEntity);
        log.info("Successfully create new user with email: {}", userDetails.getEmail());
    }

    public UserEntity getUserDetailsByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return userEntity;
    }

    public UserResponse getUser(String userId) {
        log.info("Trying to fetch user with id: {}", userId);
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new UsernameNotFoundException(userId);
        }
        UserResponse userResponse = modelMapper.map(userEntity, UserResponse.class);
        //userResponse.setAlbums(getAlbums(userId));
        userResponse.setAlbums(albumServiceClient.getAlbums(userId));
        log.info("Successfully fetched user with id: {}", userId);
        return userResponse;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(userEntity.getEmail(),
                userEntity.getEncryptedPassword(),
                true,
                true,
                true,
                true,
                new ArrayList<>());
    }

//    private List<AlbumResponse> getAlbums(String userId) {
//        ResponseEntity<List<AlbumResponse>> responseEntity = restTemplate.exchange(
//                String.format(albumUrl, userId),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<>() {
//                }
//        );
//        return responseEntity.getBody();
//    }
}
