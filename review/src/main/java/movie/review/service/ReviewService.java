package movie.review.service;

import lombok.RequiredArgsConstructor;
import movie.review.domain.Member;
import movie.review.domain.Movie;
import movie.review.domain.Review;
import movie.review.repository.MemberRepository;
import movie.review.repository.MovieRepository;
import movie.review.repository.ReviewRepository;
import movie.review.web.SessionConst;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final MemberRepository memberRepository;

    //리뷰 등록
    @Transactional
    public Review addReview(Review review){
        return reviewRepository.save(review);
    }

    @Transactional
    public Review updateReview(Long id , Review updateReview){
        Review findReview = reviewRepository.findOne(id);

        findReview.setRating(updateReview.getRating());
        findReview.setComment(updateReview.getComment());
        findReview.setMovie(updateReview.getMovie());

        return findReview;
    }

    //리뷰 읽어오기 (한 개)
    public Review findOne(Long id){
        return reviewRepository.findOne(id);
    }

    public List<Review> findAll(){
        return reviewRepository.findReviews();
    }

    @Transactional
    public Review review(Long movieId , String comment, int score, HttpServletRequest request){
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        Long memberId = member.getId();

        Member findMember = memberRepository.findOne(memberId);
        Movie findMovie = movieRepository.findOne(movieId);

        Review review = new Review();

        review.setMember(findMember);
        review.setMovie(findMovie);
        review.setRating(score);
        review.setComment(comment);

        return reviewRepository.save(review);

    }
}
