package com.ikkikki.demo.config.listener;

import com.ikkikki.demo.mapper.CategoryMapper;
import com.ikkikki.demo.util.PropsLoaderUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Properties;

@WebListener
@Component
public class ContextPathListener implements ServletContextListener {
  @Autowired
  private CategoryMapper mapper;

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext sc = sce.getServletContext();
    sc.setAttribute("cp", sc.getContextPath());

    // 카테고리 정보를 application 영역 객체에 보관.
    sc.setAttribute("cate", mapper.list());

    Properties props = PropsLoaderUtil.getProperties("secret/aws_s3.properties");
    String s3url = String.format("https://%s.s3.%s.amazonaws.com/upload/", props.get("bucket-name"), props.get("region-name"));
    sc.setAttribute("s3url", s3url);
  }
}
