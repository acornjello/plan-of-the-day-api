package koreatech.cse.domain.rest;


import koreatech.cse.domain.Legs;

public class Festival implements Comparable<Festival>{

    private String name;            // 축제이름
    private String mapX;
    private String mapY;
    private String startLocation;   // 출발 좌표
    private String destLocation;    // 도착 좌표
    private String destAddress;     // 목적지 주소
    private Legs legs = new Legs();         // 경로
    //private Long arrivalTime;
    //private String departureTime;
    private String imgUrl;          // 이미지 url
    private String recommend;
    private Weather weather;
//    private String sky;
//    private String dustValue;
//    private String dustGrade;
    private String sortType; // 정렬기준 0이면 소요시간 1이면 도착시간


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getDestLocation() {
        return destLocation;
    }

    public void setDestLocation(String destLocation) {
        this.destLocation = destLocation;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }
    public String getMapX() {
        return mapX;
    }

    public void setMapX(String mapX) {
        this.mapX = mapX;
    }

    public String getMapY() {
        return mapY;
    }

    public void setMapY(String mapY) {
        this.mapY = mapY;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public Legs getLegs() {
        return legs;
    }

    public void setLegs(Legs legs) {
        this.legs = legs;
    }

    @Override
    public String toString() {
        return "Festival{" +
                "name='" + name + '\'' +
                ", mapX='" + mapX + '\'' +
                ", mapY='" + mapY + '\'' +
                ", startLocation='" + startLocation + '\'' +
                ", destLocation='" + destLocation + '\'' +
                ", destAddress='" + destAddress + '\'' +
                ", legs=" + legs +
                ", imgUrl='" + imgUrl + '\'' +
                ", recommend='" + recommend + '\'' +
                ", weather=" + weather +
                ", sortType='" + sortType + '\'' +
                '}';
    }



    public int compareTo(Festival f) {
        if(f.sortType.equals("1")){
            if (this.getLegs().getArrival().getTimeValue() > f.getLegs().getArrival().getTimeValue()) {
                return 1;
            } else if (this.getLegs().getArrival().getTimeValue() < f.getLegs().getArrival().getTimeValue()) {
                return -1;
            } else {
                return 0;
            }
        }
        else {
            if (this.legs.getDistanceValue() > f.legs.getDistanceValue()) {
                return 1;
            } else if (this.legs.getDurationValue() < f.legs.getDurationValue()) {
                return -1;
            } else {
                return 0;
            }
        }

    }
}
