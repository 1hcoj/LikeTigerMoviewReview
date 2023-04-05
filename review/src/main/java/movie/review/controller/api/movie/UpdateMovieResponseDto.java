package movie.review.controller.api.movie;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateMovieResponseDto {

    private Long id;

    private String title;

}
