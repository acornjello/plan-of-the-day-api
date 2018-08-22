package koreatech.cse.controller.rest;

import koreatech.cse.domain.rest.Book;
import koreatech.cse.domain.rest.Festival;
import koreatech.cse.domain.rest.Movie;
import koreatech.cse.domain.rest.Today;
import koreatech.cse.domain.rest.Weather;
import koreatech.cse.repository.AreaMapper;
import koreatech.cse.repository.BookMapper;
import koreatech.cse.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.*;

@RestController
@RequestMapping("/todayplan")
public class TodayRestController {
    @Inject
    private AreaMapper areaMapper;
    @Inject
    private BookMapper bookMapper;
    @Inject
    private TodayService todayService;


    /**
     * activityType : 0-movie, book(default) / 1-movie / 2-book
     * festSortType : 0-소요시간순(default) / 1-도착시간순
     * total : movie, book, festival의 결과 개수 설정 (default : 3개, 최대 10개)
     */
    @RequestMapping
    public String home() {
        return "main";
    }

    @Transactional
    @RequestMapping(value="/today", method= RequestMethod.GET, produces = "application/json;charset=utf8")
    public ResponseEntity<Today> today(@RequestParam("location") String location,
                                        @RequestParam(name = "activityType", required = false, defaultValue = "0") String activityType,
                                        @RequestParam(name="festSortType",required = false,defaultValue = "0") String festSortType,
                                        @RequestParam(name = "total", required = false, defaultValue = "3") int total)  {

        //if(! (festSortType != "0" || festSortType.equals()"1"))

        Today today = new Today();
        today.setResponse("성공");

        /** 날씨 */
        Weather weather = new Weather();
        HttpStatus findLocationRequest = todayService.doWeatherService(location, weather);

        /** 요청한 주소가 잘못되었을 때 */
        if ( findLocationRequest == HttpStatus.BAD_REQUEST ) {
            today.setResponse("위치 파라미터요청이 잘못되었습니다.");
            return new ResponseEntity<Today>(HttpStatus.BAD_REQUEST);
        }
        today.setWeather(weather);

        /**영화*/
        ArrayList<Movie> movies;
        String wideArea = areaMapper.findOneFullCD(weather.getAddr_depth1());
        System.out.println("addrDepth1 :" + weather.getAddr_depth1());
        System.out.println("wideArea :" + wideArea);
        movies = todayService.doMovieService(wideArea, total);

        /**책*/
        ArrayList<Book> books;
        //String categoryId = bookMapper.findCategoryId(categoryName);
//        if ( categoryId == null ) {
//            today.setResponse("책 카테고리 파라미터요청이 잘못되었습니다.");
//            return new ResponseEntity<Today>(HttpStatus.BAD_REQUEST);
//        }

        books = todayService.doBookService(total);
        //today.setBook(books);

        /** 축제 */
        ArrayList<Festival> festivals;
        String lat = weather.getLat();
        String lon = weather.getLon();
        ArrayList<Integer> idxArray;
        festivals = todayService.doFestivalService(lat, lon, festSortType, total);
        idxArray = todayService.sortFestivalIdxByWeather(festivals);
        System.out.println(idxArray);

        /** activityType: 0-movie, book / 1- movie / 2-book */

        if(activityType.equals("0")) {
            today.setMovie(movies);
            today.setBook(books);
        } else if(activityType.equals("1")) {
            today.setMovie(movies);
        } else if(activityType.equals("2")) {
            today.setBook(books);
        } else {
            today.setResponse("실패");
            return new ResponseEntity<Today>(today, HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> elem = new HashMap<String, Object>();

        if (festivals.get(idxArray.get(0)).getRecommend().equals("추천")) {
            elem.put("name", "festival");
            elem.put("index", idxArray.get(0));
        } else {
            Random random = new Random();
            int num = random.nextInt(2);
            System.out.println("$$$" + num);
            if (num == 0) {
                elem.put("name", "movie");
                elem.put("index", 0);
            } else {
                elem.put("name", "book");
                elem.put("index", 0);
            }
        }
        today.setRecommend(elem);
        today.setFestival(festivals);

        return new ResponseEntity<Today>(today, HttpStatus.OK);
    }

}
