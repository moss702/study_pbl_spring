<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/head.jsp" %>
</head>
<body>
	<div class="container">
		<div class="d-grid my-2 attach-area">
			<label class="btn btn-info">파일 첨부<input type="file" multiple class="d-none" id="f1"></label>
			<ul class="list-group my-2 attach-list">
			</ul>  
			<div class="row justify-content-around w-75 mx-auto attach-thumb">
			</div> 
		</div>
	</div>
	<script>
	$(function() {

		//return true / false
		function validateFiles(files) {
			const MAX_COUNT = 5;
			const MAX_FILE_SIZE = 10 * 1024 * 1024; //10mb
			const MAX_TOTAL_SIZE = 50 * 1024 * 1024; //50mb
			const BLOCK_EXT = ['exe', 'sh', 'jsp', 'java', 'class', 'html', 'js'];		

			//파일 개수 체크
			if(files.length > MAX_COUNT){
				alert('파일은 최대 5개만 업로드 가능합니다');
				return false;
			}
			 
			//파일 확장자 체크 & 파일들의 총 용량제한 체크
			let totalSize = 0;
			const isValid = files.every(f => {
				const ext = f.name.split(".").pop().toLowerCase(); //확장자 추출 및 소문자 변환
				totalSize += f.size;
				return !BLOCK_EXT.includes(ext) && f.size <= MAX_FILE_SIZE; //위에서 명시한 확장자가 아닐경우
			}) && totalSize <= MAX_TOTAL_SIZE

			if(!isValid) {
				alert('다음 파일 확장자는 업로드가 불가합니다.\n - [exe, sh, jsp, java, class, html, js] \n 또한 각 파일은 10MB 이하, 전체 합계는 50MB 이하여야 합니다.')
			}
			return isValid;
		}
		
		// x 버튼 클릭시 삭제
		$(".attach-area").on("click", "i", function(){
			const uuid = $(this).closest("[data-uuid]").data("uuid");
			$('[data-uuid="' + uuid + '"]').remove();
		});

		$("#f1").change(function() {
 			event.preventDefault();
			const formData = new FormData();
		
			const files = this.files; //여기서 this는 input
			for(let i = 0; i < files.length; i++){
				formData.append("f1", files[i]);
			}
		
 		/* $("#uploadForm").submit(function() { //파일 업로드 버튼 클릭시
 			event.preventDefault();
			const formData = new FormData(this);
			const files = this.f1.files; */

			const valid = validateFiles([...files]);
			/* console.log(formData, valid); */
			if(!valid) {
				return;
			}
			
			$.ajax({
				url : '${cp}/upload',
				method : 'POST',
				data : formData,
				processData : false, //data를 queryString으로 쓰지 않겠다
				contentType : false, 
				//multipart/form-data;가 들어가야 하지만, false:내가 정의하지않겠다.
				//이후에 나오게 될 브라우저 정보도 포함시키겠다. == 즉 기본 브라우저 설정을 따르겠다는 옵션
				success : function(data){
					console.log(data);
					
					//확인용
					let str = "";
					let thumbStr = "";
					for(let a of data){
						// $(".container").append("<p>" + data[a].origin + "</p>");
						str += `<li class="list-group-item d-flex align-items-center justify-content-between"
							data-uuid="\${a.uuid}"
							data-origin="\${a.origin}"
							data-image="\${a.image}"
							data-path="\${a.path}"
							data-odr="\${a.odr}"
						>
							<a href="${cp}/download?uuid=\${a.uuid}&origin=\${a.origin}&path=\${a.path}">\${a.origin}</a>
							<i class="fa-solid fa-xmark float-end text-danger"></i>
						</li>`;
						
						
						//이미지인 경우(썸네일 표시)
						if(a.image){
							thumbStr += `<div class="my-2 col-12 col-sm-4 col-lg-2"
											data-uuid="\${a.uuid}" >
								<div class="my-2 bg-primary" 
									style="height: 150px; background-size: cover; background-image:url('${cp}/display?uuid=\${a.uuid}&path=\${a.path}')">
									<i class="fa-solid fa-xmark float-end text-danger m-2"></i>
									</div>
								</div>`
						}
					}
					console.log(thumbStr);
					$(".attach-list").html(str);
					$(".attach-thumb").html(thumbStr);
					
				}
			})
		})
	})
	</script>
</body>
</html>