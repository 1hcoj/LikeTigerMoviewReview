package movie.review;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;

@RestController
@Slf4j
public class PublicMovieDataApiController  {

    @GetMapping("/findMovie")
    public String showMovies(Model model) throws IOException {
        // 1. URL을 만들기 위한 StringBuilder.
        StringBuilder urlBuilder = new StringBuilder("http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jspcollection=kmdb_new2&nation=대한민국&ServiceKey=9LQ162LECD22G596F42K"); /*URL*/
        // 2. 오픈 API의요청 규격에 맞는 파라미터 생성, 발급받은 인증키.
        urlBuilder.append("&" + URLEncoder.encode("val001", "UTF-8") + "=" + URLEncoder.encode("2018", "UTF-8")); /*상영년도*/
        urlBuilder.append("&" + URLEncoder.encode("val002", "UTF-8") + "=" + URLEncoder.encode("01", "UTF-8")); /*상영 월*/

        URL url = new URL(urlBuilder.toString());
        // 4. 요청하고자 하는 URL과 통신하기 위한 Connection 객체 생성.
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 5. 통신을 위한 메소드 SET.
        conn.setRequestMethod("GET");
        // 6. 통신을 위한 Content-type SET.
        conn.setRequestProperty("Content-type", "application/json");
        // 7. 통신 응답 코드 확인.
        log.info("Response code: {}" + conn.getResponseCode());
        // 8. 전달받은 데이터를 BufferedReader 객체로 저장.
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        // 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        // 10. 객체 해제.
        rd.close();
        conn.disconnect();
        // 11. 전달받은 데이터 확인.
        log.info("data check : {}", sb.toString());
        return "redirect:/";





    }




}



