package koreatech.cse.domain;

public class Steps implements Travel{
    private Point start;
    private Point end;

    private String distance;
    private String duration;


    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setAll(Point start, Point end, String distance, String duration) {
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Steps{" +
                "start=" + start +
                ", end=" + end +
                ", distance='" + distance + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
