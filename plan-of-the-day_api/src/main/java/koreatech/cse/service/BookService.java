package koreatech.cse.service;


import koreatech.cse.domain.rest.Book;
import org.json.simple.JSONArray;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.*;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


@Service
public class BookService {

    public ArrayList<Book> loadBestSellerByCategory(Book book, int bookTotal) {

        System.out.println("####" + book.getCategoryId());

        /** 추천 도서 api */
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", "application/json;charset=UTF-8");
        //headers.add("Authorization", "KakaoAK a4330178b5106aa974e333858b635131");

        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = null;
        RestTemplate rest = new RestTemplate();
        URI url = UriComponentsBuilder.fromUriString("http://book.interpark.com/api/bestSeller.api?")
                .queryParam("key", "interpark")
                .queryParam("output", "json")
                .queryParam("categoryId", "100")
                .build()
                .toUri();
        String result = "";
        try {
            response = rest.exchange(url.toURL().toString(),HttpMethod.GET, request, String.class);
            System.out.println(url.toURL().toString());
        } catch (MalformedURLException e) {
            System.out.println(e);
        }

        result = response.getBody();
        System.out.println("#HTTP STATUS#" + response.getStatusCode().toString());

        JSONParser jsonParser = new JSONParser();
        System.out.println(result);

        JSONObject jsonObj = null;
        try {
            jsonObj = (JSONObject) jsonParser.parse(result);
        } catch(Exception e) {}

        /** 결과 있는지 확인 **/
        long totalResults = (Long) jsonObj.get("totalResults");
        totalResults = bookTotal;
        if (totalResults > 10) totalResults = 10;
        if(totalResults == 0) return null;


        /** 결과 있으면 읽기 **/
        JSONArray array = (JSONArray)jsonObj.get("item");
        ArrayList<Book> books = new ArrayList<Book>();

        for(int i = 0 ; i < totalResults; i++) {
            Book tempBook = new Book();
            JSONObject entity = (JSONObject)array.get(i);

            String title = (String) entity.get("title");
            String author = (String) entity.get("author");
            String imageUrl = (String) entity.get("coverLargeUrl");
            String description = (String) entity.get("description");
            String publisher = (String) entity.get("publisher");
            String link = (String) entity.get("link");
            String mobileLink = (String) entity.get("mobileLink");
            String categoryId = (String) entity.get("categoryId");
            String categoryName = (String) entity.get("categoryName");
            long price = (Long) entity.get("priceStandard");

            description = description.replaceAll("&#39", "\'");
            description = description.replaceAll("&#34", "\"");

            tempBook.setAll(title, author, imageUrl, description, publisher, link, mobileLink, categoryId, categoryName, price);

            books.add(tempBook);
        }
        return books;
    }



}
