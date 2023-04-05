package movie.review.controller.api.review;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateReviewResponseDto {

    private Long id;
    private String comment;
}
