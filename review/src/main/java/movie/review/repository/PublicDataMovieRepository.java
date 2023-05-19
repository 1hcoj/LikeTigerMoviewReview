package movie.review.repository;

import lombok.RequiredArgsConstructor;
import movie.review.domain.PublicDataMovie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PublicDataMovieRepository {

    private final EntityManager em;

    public List<PublicDataMovie> findAll(){
        return em.createQuery("select p from PublicDataMovie  p", PublicDataMovie.class)
                .getResultList();
    }
}
