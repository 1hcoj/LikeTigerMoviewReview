package movie.review;

import javax.persistence.*;

@Entity
@Table
public class Test {
    @Id @GeneratedValue
    private Long id;

    @Column(name="memo_test_set")
    private String memoText;
}
