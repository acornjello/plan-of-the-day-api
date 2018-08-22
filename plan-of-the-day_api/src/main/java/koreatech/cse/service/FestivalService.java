package koreatech.cse.service;

import koreatech.cse.domain.*;
import koreatech.cse.domain.rest.*;
import org.json.simple.JSONArray;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.*;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Service
public class FestivalService {

    @Inject
    WeatherService weatherService;

    //축제 좌표 리스트 반환 함수
    public ArrayList<Festival> findFestivalByDate(String eventStartDate, int festivalTotal){

        ArrayList<Festival> festivals = new ArrayList<Festival>();

        String serviceKey = "l%2FXKLcW5Jj0okWYGuXHBNNOAAmPof7oCXymC6dSQupnglgZ6ePTSmrNu5y3g7NuA6QsToKdDZuL38FkjoPgZyw%3D%3D";
        String serviceKey_Decoder = null;
        try {
            serviceKey_Decoder = URLDecoder.decode(serviceKey.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        URL url = null;
        URLConnection connection = null;
        Document doc = null;
        try {
            url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?ServiceKey=" + serviceKey_Decoder
                    + "&arrange=A&listYN=Y&pageNo=1&numOfRows=10&MobileOS=ETC&MobileApp=AppTest"
                    + "&eventStartDate=" + eventStartDate);
            connection = url.openConnection();
            doc = parseXML(connection.getInputStream());
        } catch (Exception e) {}

        NodeList descNodes = doc.getElementsByTagName("item");
        if ( festivalTotal > descNodes.getLength() ) festivalTotal = descNodes.getLength();

        for(int i=0; i < festivalTotal; i++){
            Festival festival = new Festival();
            for(Node node = descNodes.item(i).getFirstChild(); node!=null; node=node.getNextSibling()){ //첫번째 자식을 시작으로 마지막까지 다음 형제를 실행
                String tagName = node.getNodeName();

                if(tagName.equals("addr1")){
                    System.out.println("addr1 : " + node.getTextContent());
                    festival.setDestAddress(node.getTextContent());
                }else if(tagName.equals("addr2")){
                    festival.setDestAddress(festival.getDestAddress() + node.getTextContent());
                }else if(tagName.equals("firstimage")){
                    festival.setImgUrl(node.getTextContent());
                } else if(tagName.equals("mapx")){
                    festival.setMapX(node.getTextContent());
                } else if(tagName.equals("mapy")){
                    festival.setMapY(node.getTextContent());
                    festival.setDestLocation( festival.getMapY()+","+festival.getMapX());
                    System.out.println(festival.getDestLocation());
                } else if(tagName.equals("title")) {
                    System.out.println("title : " + node.getTextContent());
                    festival.setName(node.getTextContent());
                }
            }
            System.out.println("===============");
            festivals.add(festival);
        }

        return festivals;
    }


    public ArrayList<Festival> setRecommend(ArrayList<Festival> festivals) {

        for(int i=0; i<festivals.size(); i++) {
            String lat = festivals.get(i).getMapY();
            String lon = festivals.get(i).getMapX();
            Weather weather = new Weather();

            try {
                String srchAddr = festivals.get(i).getDestAddress();

                weather.setLocation(srchAddr.replaceAll(" ", ""));
                weatherService.loadWeatherByArea(weather);
            } catch (Exception e) {
            }
            if (weather != null) {
                if (weather.getSky().equals("비")) {
                    festivals.get(i).setRecommend("비추천");
                } else if (weather.getSky().equals("눈")) {
                    festivals.get(i).setRecommend("비추천");
                } else if (weather.getSky().equals("비 또는 눈")) {
                    festivals.get(i).setRecommend("비추천");
                } else {
                    if (weather.getDustGrade().equals("나쁨") || weather.getDustGrade().equals("매우나쁨")) {
                        festivals.get(i).setRecommend("비추천");
                    } else {
                        festivals.get(i).setRecommend("추천");
                    }
                }

            festivals.get(i).setWeather(weather);
            } else {
                festivals.remove(i);
            }
        }

        return festivals;
    }

    public void sortByDistance(ArrayList<Festival> festivals,String origin, String sortType) {
        /** 소요시간 및 정보 가져오기 **/
        for(int i=0; i<festivals.size(); i++) {
            festivals.get(i).setStartLocation(origin);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=UTF-8");
            HttpEntity request = new HttpEntity(headers);
            ResponseEntity<String> response = null;
            RestTemplate rest = new RestTemplate();
            URI url = UriComponentsBuilder.fromUriString("https://maps.googleapis.com/maps/api/directions/json?key=AIzaSyAY8nrL5q2WEN5L1mr6nyeC1NwJ5Va0W2Q&mode=transit")
                    .queryParam("destination", festivals.get(i).getDestLocation())
                    .queryParam("origin", origin)
                    .build()
                    .toUri();

            String result = "";
            try {
                response = rest.exchange(url.toURL().toString(), HttpMethod.GET, request, String.class);
            } catch (MalformedURLException e) {
                System.out.println(e);
            }
            result = response.getBody(); //result에 결과 다 넣기
            try {
                System.out.println(url.toURL().toString());
            } catch (Exception e) {
            }


            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = null;
            try {
                jsonObj = (JSONObject) jsonParser.parse(result);
            } catch (Exception e) {
            }

            JSONArray subObj = (JSONArray) jsonObj.get("routes");
            JSONObject subObj1 = (JSONObject) subObj.get(0);

            JSONArray legsArray = (JSONArray) subObj1.get("legs");
            JSONObject legsObj = (JSONObject) legsArray.get(0);

            /** Legs에 설정 */
            JSONObject arrivalObj = (JSONObject) legsObj.get("arrival_time");
            String arrivalTime = (String) arrivalObj.get("text");
            long arrivalTimeValue = (Long) arrivalObj.get("value");
            JSONObject arrivalLocObj = (JSONObject) legsObj.get("end_location");
            double arrivalY = (Double) arrivalLocObj.get("lat");
            double arrivalX = (Double) arrivalLocObj.get("lng");
            String arrivalAddr = (String) legsObj.get("end_address");

            JSONObject departureObj = (JSONObject) legsObj.get("departure_time");
            String departureTime = (String) departureObj.get("text");
            long departureTimeValue = (Long) departureObj.get("value");
            JSONObject departureLocObj = (JSONObject) legsObj.get("start_location");
            double departureY = (Double) departureLocObj.get("lat");
            double departureX = (Double) departureLocObj.get("lng");
            String departureAddr = (String) legsObj.get("start_address");

            JSONObject distanceObj = (JSONObject) legsObj.get("distance");
            String distance = (String) distanceObj.get("text");
            long distanceValue = (Long) distanceObj.get("value");
            JSONObject durationObj = (JSONObject) legsObj.get("duration");
            String duration = (String) durationObj.get("text");
            long durationValue = (Long) durationObj.get("value");

            Legs legs = new Legs();
            Point2 departurePoint = new Point2();
            Point2 arrivalPoint = new Point2();
            departurePoint.setAll(departureY, departureX, departureAddr, departureTime, departureTimeValue);
            arrivalPoint.setAll(arrivalY, arrivalX, arrivalAddr, arrivalTime, arrivalTimeValue);

            legs.setAll(departurePoint, arrivalPoint, distance, duration, distanceValue, durationValue);

            /** Path에 설정 - steps[0]*/
            JSONArray pathArray = (JSONArray) legsObj.get("steps");
            for(int j=0; j<pathArray.size(); j++) {
                Point start = new Point();
                JSONObject pathObj = (JSONObject) pathArray.get(j);
                JSONObject startObj = (JSONObject) pathObj.get("start_location");
                double startLat = (Double) startObj.get("lat");
                double startLon = (Double) startObj.get("lng");
                start.setLocation(startLat, startLon);

                Point end = new Point();
                JSONObject endObj = (JSONObject) pathObj.get("end_location");
                double endLat = (Double) endObj.get("lat");
                double endLon = (Double) endObj.get("lng");
                end.setLocation(endLat, endLon);

                JSONObject distanceObj2 = (JSONObject) pathObj.get("distance");
                String distance2 = (String) distanceObj2.get("text");
                JSONObject durationObj2 = (JSONObject) pathObj.get("duration");
                String duration2 = (String) durationObj2.get("text");
                String instructions = (String) pathObj.get("html_instructions");

                String travelMode = (String) pathObj.get("travel_mode");
                Travel travel;
                if (travelMode.equals("WALKING")) {
                    travel = new Steps();
                    JSONArray walkingArray = (JSONArray) pathObj.get("steps");
                    JSONObject walkingObj = (JSONObject) walkingArray.get(0);

                    Point start2 = new Point();
                    JSONObject startObj2 = (JSONObject) walkingObj.get("start_location");
                    double startLat2 = (Double) startObj2.get("lat");
                    double startLon2 = (Double) startObj2.get("lng");
                    start2.setLocation(startLat2, startLon2);

                    Point end2 = new Point();
                    JSONObject endObj2 = (JSONObject) walkingObj.get("end_location");
                    double endLat2 = (Double) endObj2.get("lat");
                    double endLon2 = (Double) endObj2.get("lng");
                    end2.setLocation(endLat2, endLon2);

                    String distance3 = (String) ((JSONObject) walkingObj.get("distance")).get("text");
                    String duration3 = (String) ((JSONObject) walkingObj.get("duration")).get("text");

                    ((Steps) travel).setAll(start2, end2, distance3, duration3);
                }
                else {
                    travel = new Transit();

                    JSONObject transitObj = (JSONObject) pathObj.get("transit_details");

                    Point2 start2 = new Point2();
                    JSONObject startObj2 = (JSONObject) transitObj.get("departure_stop");
                    JSONObject startLocObj = (JSONObject) startObj2.get("location");
                    double startLat2 = (Double) startLocObj.get("lat");
                    double startLon2 = (Double) startLocObj.get("lng");
                    String startName = (String) startObj2.get("name");
                    //start2.setName(startName);

                    JSONObject startTimeObj = (JSONObject) transitObj.get("departure_time");
                    String startTime = (String) startTimeObj.get("text");
                    long startTimeValue = (Long) startTimeObj.get("value");

                    start2.setAll(startLat2, startLon2, startName, startTime, startTimeValue);
                    
                    Point2 end2 = new Point2();
                    JSONObject endObj2 = (JSONObject) transitObj.get("arrival_stop");
                    JSONObject endLocObj = (JSONObject) endObj2.get("location");
                    double endLat2 = (Double) endLocObj.get("lat");
                    double endLon2 = (Double) endLocObj.get("lng");
                    String endName = (String) endObj2.get("name");
                    //end2.setName(endName);

                    JSONObject endTimeObj = (JSONObject) transitObj.get("arrival_time");
                    String endTime = (String) endTimeObj.get("text");

                    long endTimeValue = (Long) endTimeObj.get("value");
                    long durationValue2 = endTimeValue - startTimeValue;
                    long hour = durationValue2 / 3600;
                    long min = (durationValue2%3600) / 60;
                    long sec = (durationValue2%3600) % 60;
                    if (sec >= 30) min += 1;
                    String duration3 =  ((hour > 0 && min > 0) ? hour+"시간 " : (hour > 0) ? hour+"시간" : "") + ((min > 0) ? min+"분" : "") ;
                    end2.setAll(endLat2, endLon2, endName, endTime, endTimeValue);

                    JSONObject lineObj = (JSONObject) transitObj.get("line");
                    String transitName = (String) lineObj.get("name");

                    String transitShortName = null;
                    if ( lineObj.get("short_name") != null ) {
                        transitShortName = (String) lineObj.get("short_name");
                    }


                    ((Transit) travel).setAll(start2, end2, duration3, transitName, transitShortName);

                }
                Paths path = new Paths();
                path.setAll(start, end, distance2, duration2, instructions, travelMode, travel);
                legs.getPaths().add(path);
                
            }

            festivals.get(i).setSortType(sortType);
            festivals.get(i).setLegs(legs);
        }

        /** 소요시간 소팅 **/
        Collections.sort(festivals);
    }

    public ArrayList<Integer> indexSortByWeather(ArrayList<Festival> festivals) {
        ArrayList<Integer> idxArray = new ArrayList<Integer>();
        String[] sky = {"맑음", "구름조금", "구름많음", "흐림"};

        for(int i=0; i<sky.length; i++) {
            for (int j = 0; j < festivals.size(); j++) {
                Festival f = festivals.get(j);
                String s = f.getWeather().getSky();
                String dustValue = festivals.get(j).getWeather().getDustValue();
                String recommend = festivals.get(j).getRecommend();

                if (recommend.equals("비추천")) continue;
                if ( s.equals(sky[i]) ) idxArray.add(j);
            }
        }
        int size = idxArray.size();
        for(int i = 0; i < festivals.size(); i++) {
            boolean check = false;
            for (int j = 0; j < size; j++) {
                if ( i == idxArray.get(j) ) check = true;
            }
            if (check == false)
                idxArray.add(i);
        }

        return idxArray;
    }

    private Document parseXML(InputStream stream) throws Exception{

        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;

        try{

            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            doc = objDocumentBuilder.parse(stream);

        }catch(Exception ex){
            throw ex;
        }

        return doc;
    }
}
