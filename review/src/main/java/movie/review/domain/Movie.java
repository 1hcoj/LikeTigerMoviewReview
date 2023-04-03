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
}
