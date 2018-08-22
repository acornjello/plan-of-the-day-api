package koreatech.cse.service;


import koreatech.cse.domain.rest.Weather;
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
public class WeatherService {

    public HttpStatus loadWeatherByArea(Weather weather) {

        /** 주소로 위도 경도 파싱 */
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", "application/json;charset=UTF-8");
        headers.add("Authorization", "KakaoAK a4330178b5106aa974e333858b635131");

        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = null;
        RestTemplate rest = new RestTemplate();
        URI url = UriComponentsBuilder.fromUriString("https://dapi.kakao.com/v2/local/search/address?")
                .queryParam("query", weather.getLocation().replaceAll(" ", ""))
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
        JSONObject metaObject = (JSONObject) jsonObj.get("meta");
        long totalCount = (Long) metaObject.get("total_count");

        if(totalCount == 0) return HttpStatus.BAD_REQUEST;

        /** 결과 있으면 위치 읽기 **/
        JSONArray subObj = (JSONArray) jsonObj.get("documents");
        JSONObject subObj1= (JSONObject) subObj.get(0);
        JSONObject AddressObj = (JSONObject) subObj1.get("address");
        String address_name= (String) AddressObj.get("address_name");
        String y = (String) subObj1.get("y");
        String x = (String) subObj1.get("x");


        weather.setLocation(address_name);
        weather.setLat(y);
        weather.setLon(x);

        /** 주소를 도, 시, 동으로 나누고 weather에 설정하기 -> Movie wideArea 설정에 필요 */
        ArrayList<String> split_addr = new ArrayList<String>();
        String str = "";
        for(int i=0; i<address_name.length(); i++) {
            if (address_name.charAt(i) == ' ') {
                split_addr.add(str);
                str = "";
                continue;
            }
            str += address_name.charAt(i);
            if (i == address_name.length() - 1) {
                split_addr.add(str);
            }
        }
        for(int i=0; i<split_addr.size(); i++) {
            switch (i) {
                case 0 :
                    weather.setAddr_depth1(split_addr.get(i)); break;
                case 1 :
                    weather.setAddr_depth2(split_addr.get(i)); break;
                case 2 :
                    weather.setAddr_depth3(split_addr.get(i)); break;
                default:
                    break;
            }
        }

        /** 날씨 로드 */
        parseWeatherInformation(weather);

        /** 미세먼지 로드 */
        parseDustInformation(weather);

        return HttpStatus.OK;
    }

    public HttpStatus loadWeatherByAxis(Weather weather) {
        /** 위도 경도로 주소 파싱 */
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", "application/json;charset=UTF-8");
        headers.add("Authorization", "KakaoAK a4330178b5106aa974e333858b635131");

        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = null;
        RestTemplate rest = new RestTemplate();
        URI url = UriComponentsBuilder.fromUriString("https://dapi.kakao.com/v2/local/geo/coord2address?")
                .queryParam("y", weather.getLat())
                .queryParam("x", weather.getLon())
                .build()
                .toUri();

        String result = "";
        try {
            response = rest.exchange(url.toURL().toString(),HttpMethod.GET, request, String.class);
            System.out.println(url.toURL());
        } catch (MalformedURLException e) {
            System.out.println(e);
        }

        result = response.getBody();


        System.out.println("W : "+result);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj = null;
        try {
            jsonObj = (JSONObject) jsonParser.parse(result);
        } catch(Exception e) {}

        String address_name = "";


        /** 결과 있는지 확인 **/
        JSONObject metaObject = (JSONObject) jsonObj.get("meta");
        long totalCount = (Long) metaObject.get("total_count");

        if(totalCount == 0) return HttpStatus.BAD_REQUEST;

        /** 결과 있으면 위치 읽기 **/
        JSONArray subObj = (JSONArray) jsonObj.get("documents");
        JSONObject subObj1= (JSONObject) subObj.get(0);
        JSONObject AddressObj = (JSONObject) subObj1.get("address");
        address_name= (String) AddressObj.get("address_name");
        String y = weather.getLat();
        String x = weather.getLon();


        weather.setLocation(address_name);
        weather.setLat(y);
        weather.setLon(x);

        /** 주소를 도, 시, 동으로 나누고 weather에 설정하기 -> Movie wideArea 설정에 필요 */
        ArrayList<String> split_addr = new ArrayList<String>();
        String str = "";
        for(int i=0; i<address_name.length(); i++) {
            if (address_name.charAt(i) == ' ') {
                split_addr.add(str);
                str = "";
                continue;
            }
            str += address_name.charAt(i);
            if (i == address_name.length() - 1) {
                split_addr.add(str);
            }
        }
        for(int i=0; i<split_addr.size(); i++) {
            switch (i) {
                case 0 :
                    weather.setAddr_depth1(split_addr.get(i)); break;
                case 1 :
                    weather.setAddr_depth2(split_addr.get(i)); break;
                case 2 :
                    weather.setAddr_depth3(split_addr.get(i)); break;
                default:
                    break;
            }
        }

        /** 날씨 로드 */
        parseWeatherInformation(weather);

        /** 미세먼지 로드 */
        parseDustInformation(weather);
        return HttpStatus.OK;

    }

    public void splitArea(String address_name, Weather weather) {

    }

    public void parseWeatherInformation(Weather weather) {
        HttpHeaders weather_headers = new HttpHeaders();

        weather_headers.add("Content-Type", "application/json;charset=UTF-8");
        weather_headers.add("appKey", "81f7e8c5-a25e-3a6a-b0fb-f180ea5aec60");

        HttpEntity weather_request = new HttpEntity(weather_headers);
        ResponseEntity<String> weather_response = null;
        RestTemplate weather_rest = new RestTemplate();
        URI weather_url = UriComponentsBuilder.fromUriString("http://apis.skplanetx.com/weather/summary?")
                .queryParam("version", 1)
                .queryParam("lat", weather.getLat())
                .queryParam("lon", weather.getLon())
                .build()
                .toUri();

        String weather_result = "";
        try {
            weather_response = weather_rest.exchange(weather_url.toURL().toString(), HttpMethod.GET, weather_request, String.class);
        } catch (MalformedURLException e) {
            System.out.println(e);
        }


        weather_result = weather_response.getBody();
        System.out.println("WEATHER : " + weather_result);

        JSONParser jsonParser = new JSONParser();
        JSONObject jobj = null;
        try {
            jobj = (JSONObject) jsonParser.parse(weather_result);
        } catch(Exception e) {}

        JSONObject obj1= (JSONObject) jobj.get("weather");
        JSONArray obj2= (JSONArray) obj1.get("summary");
        JSONObject obj3= (JSONObject) obj2.get(0);
        JSONObject todayObj= (JSONObject) obj3.get("today");
        JSONObject temperatureObj= (JSONObject) todayObj.get("temperature");
        JSONObject skyObj= (JSONObject) todayObj.get("sky");

        String tmax = (String) temperatureObj.get("tmax");
        String tmin = (String) temperatureObj.get("tmin");
        String sky = (String) skyObj.get("name");

        weather.setTmin(tmin);
        weather.setTmax(tmax);
        weather.setSky(sky);
    }

    public void parseDustInformation(Weather weather) {
        HttpHeaders dust_headers = new HttpHeaders();

        dust_headers.add("Content-Type", "application/json;charset=UTF-8");
        dust_headers.add("appKey", "81f7e8c5-a25e-3a6a-b0fb-f180ea5aec60");

        HttpEntity dust_request = new HttpEntity(dust_headers);
        ResponseEntity<String> dust_response = null;
        RestTemplate dust_rest = new RestTemplate();
        URI dust_url = UriComponentsBuilder.fromUriString("http://apis.skplanetx.com/weather/dust?")
                .queryParam("version", 1)
                .queryParam("lat", weather.getLat())
                .queryParam("lon", weather.getLon())
                .build()
                .toUri();

        String dust_result = "";
        try {
            dust_response = dust_rest.exchange(dust_url.toURL().toString(), HttpMethod.GET, dust_request, String.class);
        } catch (MalformedURLException e) {
            System.out.println(e);
        }

        dust_result = dust_response.getBody();
        //System.out.println("DUST : " + dust_result);
        JSONParser jsonParser = new JSONParser();
        JSONObject jobj = null;
        try {
            jobj = (JSONObject) jsonParser.parse(dust_result);
        } catch(Exception e) {}

        JSONObject obj1= (JSONObject) jobj.get("weather");
        JSONArray obj2= (JSONArray) obj1.get("dust");
        JSONObject obj3= (JSONObject) obj2.get(0);
        JSONObject pm10Obj= (JSONObject) obj3.get("pm10");
        String dustValue= (String) pm10Obj.get("value");
        String dustGrade= (String) pm10Obj.get("grade");

        weather.setDustValue(dustValue);
        weather.setDustGrade(dustGrade);
    }

}
