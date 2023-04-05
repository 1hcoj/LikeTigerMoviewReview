package movie.review.controller.api.movie;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import movie.review.domain.Review;

@Data
public class ReviewDto {

    @ApiModelProperty(example = "영화 평점")
    private int rating;

    @ApiModelProperty(example = "영화 한줄평")
    private String comment;

    public ReviewDto(Review r) {
        this.rating = r.getRating();
        this.comment = r.getComment();
    }
}
