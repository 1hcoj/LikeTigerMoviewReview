package movie.review.api.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginApiResponseDto {
    private String name;
    private String token;
}
