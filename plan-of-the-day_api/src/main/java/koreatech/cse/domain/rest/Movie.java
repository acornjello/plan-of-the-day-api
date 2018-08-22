package koreatech.cse.domain.rest;

public class Movie {
    private String rnum;
    private String targetDt;
    private String movieNm;
    private String openDt;
    private String wideArea;

    public void setRnum(String rnum) {
        this.rnum = rnum;
    }

    public void setMovieNm(String movieNm) {
        this.movieNm = movieNm;
    }

    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }

    public void setTargetDt(String targetDt) {
        this.targetDt = targetDt;
    }

    public String getRnum() {
        return rnum;
    }

    public String getMovieNm() {
        return movieNm;
    }

    public String getOpenDt() {
        return openDt;
    }

    public String getTargetDt() {
        return targetDt;
    }

    public String getWideArea() {
        return wideArea;
    }

    public void setWideArea(String wideArea) {
        this.wideArea = wideArea;
    }

    @Override
    public String toString() {
        return "Movie{" +
                ", targetDt='" + targetDt + '\'' +
                ", rnum='" + rnum + '\'' +
                ", movieNm='" + movieNm + '\'' +
                ", openDt='" + openDt + '\'' +
                ", wideAreaCd='" + wideArea + '\'' +
                '}';
    }
}
