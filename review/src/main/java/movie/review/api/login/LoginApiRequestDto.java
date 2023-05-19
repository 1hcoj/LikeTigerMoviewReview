package movie.review.api.login;

import lombok.Data;

@Data
public class LoginApiRequestDto {

    private String loginId;
    private String password;
}
