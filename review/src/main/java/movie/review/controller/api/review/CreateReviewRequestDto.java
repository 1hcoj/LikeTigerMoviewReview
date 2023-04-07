package movie.review.controller.api.review;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import movie.review.domain.Member;
import movie.review.domain.Movie;

@Data
public class CreateReviewRequestDto {
    @ApiModelProperty(example = "리뷰 점수")
    private int rating;
    @ApiModelProperty(example = "리뷰 (한줄평) ")
    private String comment;

}
