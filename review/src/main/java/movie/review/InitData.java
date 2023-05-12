package movie.review;

import lombok.RequiredArgsConstructor;
import movie.review.domain.Member;
import movie.review.domain.Movie;
import movie.review.domain.Review;
import movie.review.domain.qualityStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitData {
    private final initService initService;

    @PostConstruct
    public void init(){
        initService.init();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class initService{
        private final EntityManager em;
        public void init(){

            // 새로운 회원 2명이 각 영화 1개씩을 보고 리뷰 하는 initData 생성
            // 양방향 연관관계라 연관관계 메서드도 만들어야함 cascadeALL , 고아객체? 이거 설정하기

            Member member1 = new Member();
            member1.setName("치현");
            member1.setLoginId("abc");
            member1.setPassword("abc");
            member1.setQual(qualityStatus.BASIC);
            member1.setPhoneNumber("010-1111-1111");



            Member member2 = new Member();
            member2.setName("남기");
            member2.setLoginId("qwer");
            member2.setPassword("qwer");
            member2.setQual(qualityStatus.BASIC);
            member2.setPhoneNumber("010-2222-2222");



            Movie movie1 = new Movie();
            movie1.setTitle("아이언맨");
            movie1.setRunningTime(120);
            movie1.setMainActor("로버트 다우니 주니어");
            movie1.setDirector("존 파브로");


            Movie movie2 = new Movie();
            movie2.setTitle("토르");
            movie2.setRunningTime(127);
            movie2.setMainActor("크리스 헴스워스");
            movie2.setDirector("타이카 와이티티");


            //치현 -> 아이언맨 -> 리뷰
            Review review1 = new Review();
            review1.setMovie(movie1);
            review1.setComment("흥미로웠다");
            review1.setRating(4);
            review1.setMember(member1);
            em.persist(review1);

            //남기 -> 토르 -> 리뷰
            Review review2 = new Review();
            review2.setMovie(movie2);
            review2.setComment("지루했다");
            review2.setRating(3);
            review2.setMember(member2);
            em.persist(review2);
        }

    }

}
