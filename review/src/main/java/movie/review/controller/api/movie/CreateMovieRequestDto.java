package movie.review.controller.api.movie;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CreateMovieRequestDto {

    @ApiModelProperty(example = "영화 제목")
    private String title;

    @ApiModelProperty(example = "영화 감독")
    private String director;

    @ApiModelProperty(example = "영화 러닝 타임")
    private int runningTime;

    @ApiModelProperty(example = "영화 주연 배우")
    private String mainActor;

}
