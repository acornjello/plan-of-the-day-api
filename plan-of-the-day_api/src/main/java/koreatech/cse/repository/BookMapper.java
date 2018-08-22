package koreatech.cse.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BookMapper {
    @Select("SELECT CATEGORY_ID FROM BOOKS_CATEGORY WHERE CATEGORY_NAME = #{categoryName}")
    String findCategoryId(@Param("categoryName") String categoryName);

    @Select("SELECT CATEGORY_NAME FROM BOOKS_CATEGORY ORDER BY CATEGORY_ID")
    ArrayList<String> findAllCategoryName();
}
