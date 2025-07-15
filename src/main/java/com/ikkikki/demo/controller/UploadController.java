package com.ikkikki.demo.controller;

import com.google.gson.Gson;
import com.ikkikki.demo.domain.Attach;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
public class UploadController {
  public final static String UPLOAD_PATH = "d:/upload/files";

  @GetMapping("upload")
  public String uploadForm() {
    return "uploadForm";
  }

  @PostMapping("upload")
  @ResponseBody
  public ResponseEntity<?> upload(List<MultipartFile> f1) throws IOException{
    f1.forEach(f -> log.info(f.getOriginalFilename()));
    List<Attach> attachs = new ArrayList<Attach>();

    int odr = 0;

    for(MultipartFile part : f1) {
      if(part.getSize() == 0) {
        continue; //무시하고 반복해라
      }
      Long fileSize = part.getSize();
      String origin = part.getOriginalFilename();
      String contentType = part.getContentType();

      //ext 확장자 추출
      int idx = origin.lastIndexOf("."); //못찾으면 -1 출력
      String ext = "";
      if (idx >= 0) { //확장자가 존재함
        ext = origin.substring(idx);
      }

      //UUID 생성
      UUID uuid = UUID.randomUUID();
      String fileName = uuid + ext;

      //이미지 여부 확인
      boolean image = contentType.startsWith("image");

      String path = genPath();

      //날짜별 폴더 생성
      String realPath = UPLOAD_PATH + "/" + path + "/";
      File file = new File(realPath);
      if(!file.exists()) {
        file.mkdirs();
      }

      part.transferTo(new File(realPath + fileName));

      if(image) {
        try {
          Thumbnails.of(new File(realPath + fileName)).size(150, 150).toFile(realPath + "t_" + fileName);
        }
        catch (Exception e) {
          image = false;
        }
      }

      log.info("{} :: {} :: {} :: {}", fileSize, origin, contentType, ext);
      attachs.add(Attach.builder()
              .uuid(fileName)
              .origin(origin)
              .image(image)
              .path(path)
              .odr(odr++)
              .size(fileSize)
              .build());
    }
    return ResponseEntity.ok().body(attachs);
  }

  private String genPath() {
    return new SimpleDateFormat("yyyy/MM/dd").format(new Date().getTime());
  }
}
