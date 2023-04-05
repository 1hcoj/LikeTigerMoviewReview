package movie.review.controller.api.movie;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import movie.review.domain.Movie;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MovieDto {

    @ApiModelProperty(example = "영화 제목")
    private String title;

    @ApiModelProperty(example = "영화 감독")
    private String director;

    @ApiModelProperty(example = "영화 러닝 타임")
    private int runningTime;

    @ApiModelProperty(example = "영화 주연 배우")
    private String mainActor;

    @ApiModelProperty(example = "영화 리뷰들")
    private List<ReviewDto> reviews;

    public MovieDto(Movie movie) {
        this.title = title;
        this.director = director;
        this.runningTime = runningTime;
        this.mainActor = mainActor;

        reviews = movie.getReviews().stream().map(r-> new ReviewDto(r)).collect(Collectors.toList());
    }
}
