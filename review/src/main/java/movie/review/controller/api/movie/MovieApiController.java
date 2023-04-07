package movie.review.controller.api.movie;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import movie.review.controller.api.Result;
import movie.review.domain.Movie;
import movie.review.repository.MovieRepository;
import movie.review.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@Api(tags = {"MovieReviewProject Movie API"})
public class MovieApiController {

    private final MovieService movieService;

    private final MovieRepository movieRepository;

    @PostMapping("api/movies")
    @ApiOperation(value = "영화 등록" , notes = "영화 등록 API")
    public CreateMovieResponseDto saveMovie(@RequestBody CreateMovieRequestDto request)
    {
        Movie movie = new Movie();
        Movie createMovie = createMovieSetting(request, movie);
        movieService.addMovie(createMovie);
        return new CreateMovieResponseDto(movie.getId());
    }

    @PutMapping("api/movies/{id}")
    @ApiOperation(value = "영화 정보 수정" , notes = "영화 정보 수정 API")
    public UpdateMovieResponseDto updateMovie(@PathVariable("id") Long id , @RequestBody CreateMovieRequestDto request)
    {
        Movie findMovie = movieService.findOne(id);

        Movie movie = new Movie();
        updateMovieSetting(request, movie);
        Movie updateMovie = movieService.updateMovie(id, movie);
        return new UpdateMovieResponseDto(updateMovie.getId(),updateMovie.getTitle());
    }

    @GetMapping("api/movies")
    @ApiOperation(value = "모든 영화 목록 조회" , notes = "모든 영화 목록 조회 API (페이징 가능) ")
    public Result movies(@RequestParam(value="offset", defaultValue = "0") int offset,
                         @RequestParam(value="limit", defaultValue = "100") int limit){
        List<Movie> movies = movieRepository.findAllForPaging(offset, limit);
        for (Movie movie : movies) {
            System.out.println(movie.getTitle());
            System.out.println(movie.getRunningTime());
        }
        List<MovieDto> collect = movies.stream()
                .map(m -> new MovieDto(m))
                .collect(Collectors.toList());
        return new Result(collect);
    }

    @GetMapping("api/movies/{id}")
    @ApiOperation(value="영화 상세 정보 조회" , notes = "영화 상세 정보 조회 API (리뷰 포함) ")
    public Result movie(@PathVariable("id") Long id)
    {
        Movie findMovie = movieRepository.findOne(id);

        SpecificMovieResponseDto dto = new SpecificMovieResponseDto(findMovie);

        return new Result(dto);
    }


    private static void updateMovieSetting(CreateMovieRequestDto request, Movie movie) {
        movie.setTitle(request.getTitle());
        movie.setRunningTime(request.getRunningTime());
        movie.setDirector(request.getDirector());
        movie.setMainActor(request.getMainActor());
    }

    private static Movie createMovieSetting(CreateMovieRequestDto request, Movie movie) {
        movie.setRunningTime(request.getRunningTime());
        movie.setTitle(request.getTitle());
        movie.setMainActor(request.getMainActor());
        movie.setDirector(request.getDirector());
        return movie;
    }


}
