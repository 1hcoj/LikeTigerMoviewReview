package movie.review.web.controller.movie;

import lombok.Data;
import lombok.Getter;

@Data
public class ReviewForm {

    private String registerReviewMember;
    private String movieTitle;
    private int score;
    private String comment;
}
