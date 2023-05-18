package movie.review;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import movie.review.domain.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitData {
    private final initService initService;

    @PostConstruct
    public void init() throws IOException, ParseException {
        initService.init();
        initService.initPublicDataMovie();
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
            member1.setLoginId("치현");
            member1.setPassword("치현");
            member1.setQual(qualityStatus.BASIC);
            member1.setPhoneNumber("010-1111-1111");



            Member member2 = new Member();
            member2.setName("남기");
            member2.setLoginId("남기");
            member2.setPassword("남기");
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

        public void initPublicDataMovie() throws IOException, ParseException {
            StringBuilder urlBuilder = new StringBuilder("http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=8ce49816ac05b5c8522b414133b6ffa6&targetDt=20120101"); /*URL*/

            URL url = new URL(urlBuilder.toString());

            String result="";

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            conn.setRequestProperty("Content-type", "application/json");

            BufferedReader rd;
            BufferedReader bf;

            log.info("Response Code : {}",conn.getResponseCode());

            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                bf = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
                result=bf.readLine();
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
                JSONObject boxOfficeResult = (JSONObject) jsonObject.get("boxOfficeResult");
                JSONArray array = (JSONArray) boxOfficeResult.get("dailyBoxOfficeList");
                JSONObject o = (JSONObject) array.get(1);
                String movieNm = (String) o.get("movieNm");

                for (int i=0; i<array.size();i++){
                    JSONObject myArray = (JSONObject) array.get(i);
                    String movieTitle = (String) myArray.get("movieNm");
                    String movieCd = (String) myArray.get("movieCd");
                    log.info("{} 번째의 영화 제목은 {} 번호는 {}",i,movieTitle,movieCd);
                    PublicDataMovie movie = new PublicDataMovie();
                    movie.setTitle(movieTitle);
                    movie.setMovieCd(movieCd);
                    em.persist(movie);
                }

            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

        }

    }

}
