package movie.review.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Movie {

    @Id @GeneratedValue
    @Column(name="movie_id")
    private Long id;

    private String title;

    private String director;

    private String mainActor;

    private int runningTime;

    @OneToMany(mappedBy = "movie")
    private List<Review> reviews = new ArrayList<>();

    /**
     * 영화 평균 평점 비즈니스 로직
     */
    public Long getAvgRating()
    {
        Long sumRating=0L;
        for(Review review : reviews)
        {
            sumRating+=review.getRating();
        }
        return (sumRating/reviews.size());
    }

}
