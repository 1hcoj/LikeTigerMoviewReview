package movie.review.web.controller.movie;

import lombok.RequiredArgsConstructor;
import movie.review.domain.Movie;
import movie.review.domain.PublicDataMovie;
import movie.review.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movies")
    public String showMovie(Model model){

        List<Movie> movies = movieService.findAll();
        model.addAttribute("movies",movies);
        return "movies/movieList";

    }
}
