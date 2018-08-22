package koreatech.cse.service;

import koreatech.cse.domain.rest.Book;
import koreatech.cse.domain.rest.Festival;
import koreatech.cse.domain.rest.Movie;
import koreatech.cse.domain.rest.Today;
import koreatech.cse.domain.rest.Weather;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TodayService {

    @Inject
    WeatherService weatherService;
    @Inject
    private MovieService movieService;
    @Inject
    private BookService bookService;
    @Inject
    private FestivalService festivalService;

   public HttpStatus doWeatherService(String location, Weather weather) {
              /** y,x */
        if ( location.charAt(0) >= '0' && location.charAt(0) <= '9' ) {
            weather.setLat( location.substring(0, location.indexOf(",")) );
            weather.setLon( location.substring(location.indexOf(",")+1, location.length()) );
            return weatherService.loadWeatherByAxis(weather);
        }
        else {
            weather.setLocation(location);
            return weatherService.loadWeatherByArea(weather);
        }
   }
   public ArrayList<Movie> doMovieService(String wideArea, int movieTotal) {

       Calendar calendar = new GregorianCalendar();
       calendar.add(Calendar.DATE, -1);
       String targetDt = new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
       System.out.println("targetDt : " + targetDt);

       ArrayList<Movie> movies = movieService.findMoive(targetDt, wideArea, movieTotal);
       return movies;
   }
    public ArrayList<Book> doBookService(int bookTotal) {
        Book book = new Book();

        ArrayList<Book> books = bookService.loadBestSellerByCategory(book, bookTotal);

        return books;
    }
   public ArrayList<Festival> doFestivalService(String lat, String lon, String festSortType, int festivalTotal) {

       String origin =lat+","+lon;
       SimpleDateFormat formatter01 = new SimpleDateFormat("yyyyMMdd");
       String eventStartDate=formatter01.format(new Date());
       System.out.println("eventStartDate : "+ eventStartDate);

       //festival 목록받기
       ArrayList<Festival> festivals  = festivalService.findFestivalByDate(eventStartDate, festivalTotal);
       festivalService.setRecommend(festivals);

       //sortType에 맞춰 정렬하기
       festivalService.sortByDistance(festivals,origin,festSortType);


       return festivals;
   }

   public ArrayList<Integer> sortFestivalIdxByWeather(ArrayList<Festival> festivals) {
       ArrayList<Integer> idxSortByWeather = festivalService.indexSortByWeather(festivals);
       return idxSortByWeather;
   }
}
