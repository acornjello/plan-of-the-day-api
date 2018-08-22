package koreatech.cse.domain;

public class Area {
    private int id;
    private String fullCd;
    private String korNm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullCd() {
        return fullCd;
    }

    public void setFullCd(String fullCd) {
        this.fullCd = fullCd;
    }

    public String getKorNm() {
        return korNm;
    }

    public void setKorNm(String korNm) {
        this.korNm = korNm;
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", fullCd='" + fullCd + '\'' +
                ", korNm='" + korNm + '\'' +
                '}';
    }
}
