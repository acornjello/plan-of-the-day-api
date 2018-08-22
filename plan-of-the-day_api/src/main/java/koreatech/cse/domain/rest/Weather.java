package koreatech.cse.domain.rest;


public class Weather {
    private String sky;
    private String tmax;
    private String tmin;
    private String dustValue;
    private String dustGrade;
    private String lon;
    private String lat;
    private String addr_depth1;
    private String addr_depth2;
    private String addr_depth3;
    private String location;
    
    private String version = "1";

    public String getSky() {
        return sky;
    }

    public void setSky(String sky) {
        this.sky = sky;
    }

    public String getTmax() {
        return tmax;
    }

    public void setTmax(String tmax) {
        this.tmax = tmax;
    }

    public String getTmin() {
        return tmin;
    }

    public void setTmin(String tmin) {
        this.tmin = tmin;
    }

    public String getDustValue() {
        return dustValue;
    }

    public void setDustValue(String dustValue) {
        this.dustValue = dustValue;
    }

    public String getDustGrade() {
        return dustGrade;
    }

    public void setDustGrade(String dustGrade) {
        this.dustGrade = dustGrade;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAddr_depth1() {
        return addr_depth1;
    }

    public void setAddr_depth1(String addr_depth1) {
        this.addr_depth1 = addr_depth1;
    }

    public String getAddr_depth2() {
        return addr_depth2;
    }

    public void setAddr_depth2(String addr_depth2) {
        this.addr_depth2 = addr_depth2;
    }

    public String getAddr_depth3() {
        return addr_depth3;
    }

    public void setAddr_depth3(String addr_depth3) {
        this.addr_depth3 = addr_depth3;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "sky='" + sky + '\'' +
                ", tmax='" + tmax + '\'' +
                ", tmin='" + tmin + '\'' +
                ", dustValue='" + dustValue + '\'' +
                ", dustGrade='" + dustGrade + '\'' +
                ", lon='" + lon + '\'' +
                ", lat='" + lat + '\'' +
                ", addr_depth1='" + addr_depth1 + '\'' +
                ", addr_depth2='" + addr_depth2 + '\'' +
                ", addr_depth3='" + addr_depth3 + '\'' +
                ", location='" + location + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
