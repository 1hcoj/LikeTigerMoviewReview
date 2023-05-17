package movie.review.service;

import lombok.RequiredArgsConstructor;
import movie.review.domain.Movie;
import movie.review.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

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




}
