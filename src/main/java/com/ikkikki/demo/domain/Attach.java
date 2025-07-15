package com.ikkikki.demo.domain;


//import controller.attach.UploadFile;
import lombok.*;
import org.apache.ibatis.type.Alias;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("attach")
public class Attach {
	private String uuid;	//첨부파일ID * 유니버셜유니크아이디
	private String path;	//업로드 경로
	private boolean image; //이미지
	private String origin; //덮어쓰기방지
	private Long bno;	//게시글번호
	private Long rno;	//댓글번호
	private int odr; //첨부파일 순서 * 인티저 기본값 null, 인트 기본값 0
	private long size; //첨부파일의 크기

	public Attach(String uuid, String path, boolean image, String origin, Long bno, int odr, long size) {
		super();
		this.uuid = uuid;
		this.path = path;
		this.image = image;
		this.origin = origin;
		this.bno = bno;
		this.odr = odr;
		this.size = size;
	}
	
//	public File toFile() {
//		return new File(UploadFile.UPLOAD_PATH + "/" + path, uuid);
//	}
	
	public Attach toThumb() {
		return Attach.builder().bno(bno).image(image).uuid("t_" + uuid).path(path).origin(origin).odr(odr).size(size).build();
	}
}
