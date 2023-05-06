package movie.review.controller.api.movie;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
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

    @ApiModelProperty(example = "영화 총 평점")
    private Long avgRating;

    public MovieDto(Movie m) {
        this.title = m.getTitle();
        this.director = m.getDirector();
        this.runningTime = m.getRunningTime();
        this.mainActor = m.getMainActor();
        this.avgRating=m.getAvgRating();
    }
}
