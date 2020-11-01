package wg.user.service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import wg.user.data.UserEntity;
import wg.user.data.UserRepository;
import wg.user.model.CreateUser;

import java.util.UUID;

@Service
@Slf4j
public class UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(ModelMapper modelMapper,
                       UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(CreateUser userDetails) {
        log.info("Trying to create new user with email: {}", userDetails.getEmail());
        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setEncryptedPassword(passwordEncoder.encode(userDetails.getPassword()));
        userRepository.save(userEntity);
        log.info("Successfully create new user with email: {}", userDetails.getEmail());
    }
}
