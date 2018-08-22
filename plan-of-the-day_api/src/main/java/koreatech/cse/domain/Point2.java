package koreatech.cse.domain;

public class Point2 {
    private double lat;         /***/
    private double lon;         /***/
    private String name;
    private String time;
    private long timeValue = 0;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(long timeValue) {
        this.timeValue = timeValue;
    }

    public void setLocation(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public void setAll(double lat, double lon, String name, String time, long timeValue) {
        this.lat = lat;
        this.lon = lon;
        this.name = name;
        this.time = time;
        this.timeValue = timeValue;
    }

    @Override
    public String toString() {
        String str;

        str = "Point{" +
                "lat=" + lat +
                ", lon=" + lon;
        if (name != null)
            str +=  ", name='" + name + '\'';
        if (time != null)
            str +=  ", time='" + time + '\'';
        if (timeValue > 0)
            str += ", timeValue=" + timeValue;

        str += '}';

        return str;
    }
}
