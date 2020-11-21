package wg.user.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<AlbumResponse> albums;
}
