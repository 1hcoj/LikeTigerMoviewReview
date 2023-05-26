package movie.review.web.controller.movie;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movie.review.domain.*;
import movie.review.service.MovieService;
import movie.review.service.ReviewService;
import movie.review.web.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MovieController {

    private final MovieService movieService;
    private final ReviewService reviewService;


    @GetMapping("/movies")
    public String showMovie(Model model){

        List<Movie> movies = movieService.findAll();
        model.addAttribute("movies",movies);
        return "movies/movieList";
    }

//    @GetMapping("/movies")
//    public String showMovieV2(@ModelAttribute("movieSearch") MovieSearch movieSearch, Model model){
//        List<Movie> movies = movieService.findByMovieName(movieSearch);
//        model.addAttribute("movies",movies);
//        return "movies/movieList";
//    }

    @GetMapping("/movies/{id}/review")
    public String createReview(@PathVariable("id") Long id, Model model, HttpServletRequest request){
        Movie movie = movieService.findOne(id);

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        ReviewForm form = new ReviewForm();
        form.setRegisterReviewMember(member.getName());
        form.setMovieTitle(movie.getTitle());

        log.info("memberName : {}",member.getName());

        model.addAttribute("form",form);

        return "reviews/createReview";
    }

    @PostMapping("/movies/{id}/review")
    public String create(@PathVariable("id") Long id , @ModelAttribute("form") ReviewForm form,HttpServletRequest request){
        reviewService.review(id,form.getComment(), form.getScore(), request);

        return "redirect:/";
    }

    @GetMapping("/movies/{id}/reviews")
    public String showReviewOfMovie(@PathVariable("id") Long id,Model model){
        Movie movie = movieService.findOne(id);

        List<Review> reviews = movieService.findByReviewOfMovie(movie);


        model.addAttribute("reviews",reviews);
        model.addAttribute("title",movie.getTitle());

        return "movies/reviewList";


    }

}
