package koreatech.cse.repository;

import koreatech.cse.domain.Area;
import koreatech.cse.domain.Searchable;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaMapper {

    @Select("select FULL_CD from areas where KOR_NM = #{korNm}")
    String findOneFullCD(@Param("korNm") String korNm);


    @Select("<script>"
            + "SELECT * FROM areas"
            + "<if test='orderParam != null'>ORDER BY ${orderParam} DESC</if>"
            + "</script>")
    List<Area> findByScript(Searchable searchable);
}
