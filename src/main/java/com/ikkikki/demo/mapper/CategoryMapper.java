package com.ikkikki.demo.mapper;

import com.ikkikki.demo.domain.Category;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryMapper {
	@Select("select * from tbl_category tc where tc.status = 'ACTIVE' order by odr")
	List<Category> list();
}
