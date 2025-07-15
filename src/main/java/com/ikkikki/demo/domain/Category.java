package com.ikkikki.demo.domain;

import com.ikkikki.demo.domain.en.CategoryStatus;
import com.ikkikki.demo.domain.en.CategoryViewType;
import lombok.Builder;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("category")
@Data
@Builder
public class Category {
	private Long cno;
	private String cname;
	private String regdate;
	private CategoryViewType cViewType;
	private int odr;
	private CategoryStatus status;
}
