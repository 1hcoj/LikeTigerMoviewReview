package movie.review.repository;

import lombok.RequiredArgsConstructor;
import movie.review.domain.Movie;
import movie.review.domain.PublicDataMovie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovieRepository {

    private final EntityManager em;

    public Movie save(Movie movie){
        em.persist(movie);
        return movie;
    }

    public void delete(Movie movie){
        em.remove(movie);
    }

    public Movie findOne(Long id){
        return em.find(Movie.class,id);
    }
    public List<Movie> findAll(){
        return em.createQuery("select m from Movie m",Movie.class)
                .getResultList();
    }

    //수정
    public Movie updateMovie(Long id,Movie updateMovie){
        Movie findMovie = em.find(Movie.class, id);
        findMovie.setDirector(updateMovie.getDirector());
        findMovie.setTitle(updateMovie.getTitle());
        findMovie.setMainActor(updateMovie.getMainActor());
        findMovie.setRunningTime(updateMovie.getRunningTime());
        return findMovie;
    }

    //영화 목록 조회 API 페이징을 위한 로직
    public List<Movie> findAllForPaging(int offset , int limit)
    {
        return em.createQuery("select m from Movie m",Movie.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

    }

    /**
     * 여기부터는 Open API 에서 가져온 Movie Data 가공 하는 Method
     */
    public void publicDataMovieSave(PublicDataMovie movie){
        em.persist(movie);
    }

    public List<PublicDataMovie> publicDataMovieFindAll(){
        return em.createQuery("select m from PublicDataMovie m", PublicDataMovie.class)
                .getResultList();
    }

}
