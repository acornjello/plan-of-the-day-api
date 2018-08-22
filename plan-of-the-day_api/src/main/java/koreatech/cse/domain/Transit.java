package koreatech.cse.domain;

public class Transit implements Travel {

    private Point2 start;
    private Point2 end;
    private String duration;        /***/
    private String transitName;     // name;
    private String transitShortName;// shortName; -- 있으면

    public Point2 getStart() {
        return start;
    }

    public void setStart(Point2 start) {
        this.start = start;
    }

    public Point2 getEnd() {
        return end;
    }

    public void setEnd(Point2 end) {
        this.end = end;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTransitName() {
        return transitName;
    }

    public void setTransitName(String transitName) {
        this.transitName = transitName;
    }

    public String getTransitShortName() {
        return transitShortName;
    }

    public void setTransitShortName(String transitShortName) {
        this.transitShortName = transitShortName;
    }

    public void setAll(Point2 start, Point2 end, String duration, String transitionName, String transitShortName) {
        this.start = start;
        this.end = end;
        this.duration = duration;
        this.transitName = transitionName;
        this.transitShortName = transitShortName;
    }
    @Override
    public String toString() {
        return "Transit{" +
                "start=" + start +
                ", end=" + end +
                ", duration='" + duration + '\'' +
                ", transitName='" + transitName + '\'' +
                ", transitShortName='" + transitShortName + '\'' +
                '}';
    }
}
