<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp" %>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/nav.jsp" %>
    <div class="container p-0">
        <main>
            <form method="post" action="modify" id="modifyForm">
                <div class="small border-bottom border-3 border-secondary p-0 pb-2">
                	<a href="#" class="small">
	                	<span class="text-primary">
	                		<c:forEach items="${cate}" var="c">
	                			<c:if test="${c.cno == cri.cno}">
	                				${c.cname}
	                			</c:if>
	                		</c:forEach>
	                	</span> 
                		카테고리
                	</a>
               	</div>
                <div class="small p-0 py-2">
                    <input placeholder="글 제목 입력" class="form-control" name="title" id="title" value="${board.title}">
                </div>
                <div class="p-0 py-2 ps-1 border-top border-1 border-muted">
                    <textarea name="content" id="editor1" class="form-control resize-none">${board.content}</textarea>
                </div>
                <div>
                    <a href="${cp}/board/list?${cri.qs2}" class="btn btn-secondary btn-sm"><i class="fa-solid fa-list-ul"></i> 목록</a>
                    <div class="float-end">
                        <button class="btn btn-outline-secondary btn-sm"><i class="fa-solid fa-pen"></i>  글 수정</button>
                    </div>
                </div>
                <input type="hidden" name="id" value="${member.id}" />
                <input type="hidden" name="bno" value="${board.bno}" />
                <input type="hidden" name="cno" value="${cri.cno}" />
                <input type="hidden" name="page" value="${cri.page}" />
                <input type="hidden" name="amount" value="${cri.amount}" />
                <input type="hidden" name="type" value="${cri.type}" />
                <input type="hidden" name="keyword" value="${cri.keyword}" />
                <input type="hidden" name="encodedStr" value="">
            </form>
            
<!-- 첨부파일 유무 -->
			<div class="d-grid my-2 attach-area">
				<div class="small my-1 border-bottom border-1 border-muted p-0 pb-2"><i class="fa-solid fa-paperclip"></i> 첨부파일</div>
				<label class="btn btn-info">파일 첨부<input type="file" multiple="" class="d-none" id="f1"></label>
				<ul class="list-group my-2 attach-list">
					<c:forEach items="${board.attachs}" var="a">
					<li class="list-group-item d-flex align-items-center justify-content-between" 
						data-uuid="${a.uuid}" 
						data-origin="${a.origin}"
						data-image="${a.image}"
						data-path="${a.path}"
						data-size="${a.size}"
						data-odr="${a.odr}">
						<a href="/pbl/download?uuid=${a.uuid}&origin=${a.origin}&path=${a.path}">${a.origin}</a>
						<i class="fa-solid fa-xmark float-end text-danger"></i>
					</li>
					</c:forEach>
				</ul>  
<!-- 첨부파일 유무 + 이미지 여부 -->				
				<div class="row justify-content-around w-75 mx-auto attach-thumb">
					<c:forEach items="${board.attachs}" var="a">
					<c:if test="${a.image}">
						<div class="my-2 col-12 col-sm-4 col-lg-2" 
							data-uuid="${a.uuid}">
							<div class="my-2 bg-primary" style="height: 150px; background-size: cover; 
								background-image:url('/pbl/display?uuid=t_${a.uuid}&path=${a.path}')">
								<i class="fa-solid fa-xmark float-end text-danger m-2"></i>
							</div>
						</div>
    				</c:if>
					</c:forEach>
				</div> 
			</div>
			
        </main>
    </div>
    <script src="https://code.jquery.com/ui/1.14.1/jquery-ui.js"></script>
    <script>
        $(function() {
            CKEDITOR.replace('editor1', {
                height:400
            });
        });        
    </script>
   	<script>
	$(function() {
		$(".attach-list").sortable();
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
		
			const files = this.files; //여기서 this는 input type
			
			const data = []; // 기존 파일목록이 들어갈 곳
			$(".attach-list li").each(function(){
				//console.log({...this.dataset});
				data.push({...this.dataset});
			});
			
			console.log('기존', data);
			console.log('신규', [...files]);
			
			const mixedFiles = [...data.map(d => { return {name:d.origin, size:d.size/1} }), ...files];
			console.log(mixedFiles);

			for(let i = 0; i < files.length; i++){
				formData.append("f1", files[i]);
			}
		
 		/* $("#uploadForm").submit(function() { //파일 업로드 버튼 클릭시
 			event.preventDefault();
			const formData = new FormData(this);
			const files = this.f1.files; */

			const valid = validateFiles(mixedFiles);
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
					
					//확인용 * 비동기처리를 한 이후에 보여져야 할 동적 데이터
					let str = "";
					let thumbStr = "";
					for(let a of data){
						// $(".container").append("<p>" + data[a].origin + "</p>");
						str += `<li class="list-group-item d-flex align-items-center justify-content-between"
							data-uuid="\${a.uuid}"
							data-origin="\${a.origin}"
							data-image="\${a.image}"
							data-path="\${a.path}"
							data-size="\${a.size}"
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
									style="height: 150px; background-size: cover; background-image:url('${cp}/display?uuid=t_\${a.uuid}&path=\${a.path}')">
									<i class="fa-solid fa-xmark float-end text-danger m-2"></i>
									</div>
								</div>`
						}
					}
					$(".attach-list").append(str);
					$(".attach-thumb").append(thumbStr);
				}
			})
		})
	$('#modifyForm').submit(function(){
		event.preventDefault();
		if(!confirm('수정하시겠습니까?')){
			return;
		}
		const data = [];
		$(".attach-list li").each(function(){
			//console.log({...this.dataset});
			data.push({...this.dataset});
		});
		console.log(JSON.stringify(data));
		data.forEach((item, idx) => item.odr = idx);
		$("[name='encodedStr']").val(JSON.stringify(data));
		this.submit();
	})
})
	</script>
    
<%@ include file="../common/footer.jsp" %>
</body>
</html>