package movie.review.controller.api.review;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import movie.review.domain.Review;

@Data
public class ReviewDto {

    @ApiModelProperty(example = "리뷰 등록한 회원 이름")
    private String name;

    @ApiModelProperty(example = "리뷰 점수")
    private int rating;

    @ApiModelProperty(example = "리뷰 한줄평")
    private String comment;

    @ApiModelProperty(example = "리뷰 등록한 영화 제목")
    private String title;

    public ReviewDto(Review r) {
        this.name = r.getMember().getName();
        this.rating = r.getRating();
        this.comment = r.getComment();
        this.title = r.getMovie().getTitle();
    }
}
