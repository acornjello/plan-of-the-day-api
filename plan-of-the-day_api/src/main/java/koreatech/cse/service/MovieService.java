package koreatech.cse.service;

import java.net.MalformedURLException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import koreatech.cse.domain.rest.Movie;
import koreatech.cse.repository.AreaMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;

@Service
public class MovieService {


    public ArrayList<Movie> findMoive(String targetDt, String wideArea, int movieTotal ) {

        /** 해당 지역 영화 정보 받아오기 */

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=UTF-8");

        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = null;
        RestTemplate rest = new RestTemplate();
        URI url = UriComponentsBuilder.fromUriString("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?")
                .queryParam("key", "e02513baa82320de545208781377a3c1")
                .queryParam("targetDt", targetDt)
                .queryParam("wideArea", wideArea)
                .build()
                .toUri();


        String result = "";
        try {
            response = rest.exchange(url.toURL().toString(), HttpMethod.GET, request, String.class);
        } catch (MalformedURLException e) {
            System.out.println(e);
        }

        result = response.getBody();
        System.out.println(result);

        JSONParser jsonparser = new JSONParser();
        JSONObject jsonobject = null;
        try {
            jsonobject = (JSONObject) jsonparser.parse(result);
        }catch (Exception e){}

        JSONObject json =  (JSONObject) jsonobject.get("boxOfficeResult");
        JSONArray resultArray = (JSONArray)json.get("dailyBoxOfficeList");


        ArrayList<Movie> movies = new ArrayList<Movie>();
        targetDt = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        System.out.println(targetDt);

        if (movieTotal > resultArray.size()) movieTotal = resultArray.size();

        for(int i = 0 ; i < movieTotal; i++) {
            Movie tempMovie = new Movie();
            JSONObject entity = (JSONObject)resultArray.get(i);

            String rnum = (String) entity.get("rnum");
            String movieNm = (String) entity.get("movieNm");
            String openDt = (String) entity.get("openDt");

            tempMovie.setRnum(rnum);
            tempMovie.setMovieNm(movieNm);
            tempMovie.setOpenDt(openDt);
            tempMovie.setTargetDt(targetDt);
            tempMovie.setWideArea(wideArea);
            movies.add(tempMovie);
        }

        return movies;
    }

}