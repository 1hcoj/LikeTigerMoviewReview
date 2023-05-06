package movie.review.controller.api.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import movie.review.controller.api.movie.ReviewDto;
import movie.review.domain.Review;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberDto {

    @ApiModelProperty(example = "회원 이름")
    private String name;

    @ApiModelProperty(example = "회원이 작성한 리뷰")
    private List<ReviewDto> reviews;

    public MemberDto(String name, List<Review> reviews) {
        this.name = name;
        this.reviews = reviews.stream().map(r-> new ReviewDto(r)).collect(Collectors.toList());
    }
}
