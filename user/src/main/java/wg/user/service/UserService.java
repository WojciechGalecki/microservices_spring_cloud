package wg.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import wg.user.data.UserEntity;
import wg.user.data.UserRepository;
import wg.user.model.CreateUser;

import java.util.UUID;

@Service
public class UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserService(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public void createUser(CreateUser userDetails) {
        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
        userEntity.setUserId(UUID.randomUUID().toString());
        //TODO - encrypt, logs
        userEntity.setEncryptedPassword("test");
        userRepository.save(userEntity);
    }
}
