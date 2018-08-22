package koreatech.cse.domain;

public class Point {
    private double lat;         /***/
    private double lon;         /***/

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


    public void setLocation(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public void setAll(double lat, double lon, String name, String time, long timeValue) {
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        String str;

        str = "Point{" +
                "lat=" + lat +
                ", lon=" + lon;
        str += '}';

        return str;
    }
}
