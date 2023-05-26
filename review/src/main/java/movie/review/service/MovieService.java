package movie.review.service;

import lombok.RequiredArgsConstructor;
import movie.review.domain.*;
import movie.review.repository.MemberRepository;
import movie.review.repository.MovieRepository;
import movie.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    //영화 등록
    @Transactional
    public Movie addMovie(Movie movie){
        return movieRepository.save(movie);
    }

    //영화 수정
    @Transactional
    public Movie updateMovie(Long id,Movie updateMovie){
        return movieRepository.updateMovie(id,updateMovie);
    }

    //영화 읽어오기 (한 개)
    public Movie findOne(Long id){
        return movieRepository.findOne(id);
    }

    //영화 읽어오기 (모두)
    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    public void delete(Movie movie){
        movieRepository.delete(movie);
    }

    /**
     * 여기부터는 Open API 에서 가져온 Movie Data 가공 하는 Method
     */

    public void addPublicDataMovie(PublicDataMovie movie){
        movieRepository.publicDataMovieSave(movie);
    }

    public List<PublicDataMovie> publicDataMovieFindAll(){
        return movieRepository.publicDataMovieFindAll();

    }

    public List<Movie> findByMovieName(MovieSearch movieSearch){
        return movieRepository.findByMovieName(movieSearch);
    }


    @Transactional
    public List<Review> findByReviewOfMovie(Movie movie) {
        Movie findMovie = movieRepository.findOne(movie.getId());
        List<Review> reviews = findMovie.getReviews();
        for (Review review : reviews) {
            reviewRepository.findOne(review.getId());
            memberRepository.findOne(review.getMember().getId());
            movieRepository.findOne(review.getId());
        }

        return reviews;
    }
}
