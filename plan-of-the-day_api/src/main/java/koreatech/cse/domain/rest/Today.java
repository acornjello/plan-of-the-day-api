package koreatech.cse.domain.rest;

import java.util.ArrayList;
import java.util.Map;

public class Today {
    private Map<String, Object> recommend;
    private Weather weather;
    private ArrayList<Movie> movie=new ArrayList<Movie>();
    private ArrayList<Book> book=new ArrayList<Book>();
    private ArrayList<Festival> festival;
    private String response;

    public Map<String, Object> getRecommend() {
        return recommend;
    }

    public void setRecommend(Map<String, Object> recommend) {
        this.recommend = recommend;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public ArrayList<Movie> getMovie() {
        return movie;
    }

    public void setMovie(ArrayList<Movie> movie) {
        this.movie = movie;
    }

    public ArrayList<Book> getBook() {
        return book;
    }

    public void setBook(ArrayList<Book> book) {
        this.book = book;
    }

    public ArrayList<Festival> getFestival() {
        return festival;
    }

    public void setFestival(ArrayList<Festival> festival) {
        this.festival = festival;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Today{" +
                "recommend='" + recommend + '\'' +
                ", weather=" + weather +
                ", movie=" + movie +
                ", book=" + book +
                ", festival=" + festival +
                ", response=" + response +
                '}';
    }
}
