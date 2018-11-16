<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp"%>



<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->




		<div class="hr hr-18 dotted hr-double"></div>

		<div class="row">
			<div class="col-xs-12">
				<h3 class="header smaller lighter blue">JIIN's FREEBOARD</h3>

				<div class="clearfix">
					<div class="pull-right tableTools-container"></div>
				</div>
				<div class="table-header">자유게시판</div>

				<!-- div.table-responsive -->

				<!-- div.dataTables_borderWrap -->
				<div>
					<table id="dynamic-table"
						class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th class="center"><label class="pos-rel"> <input
										type="checkbox" class="ace" /> <span class="lbl"></span>
								</label></th>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th class="hidden-480">수정일</th>
								<th>ㅇㅇ</th>

							</tr>
						</thead>
						<tbody>

							<c:forEach items="${list}" var="board">
								<tr>
									<td class="center"><label class="pos-rel"> <input
											type="checkbox" class="ace" /> <span class="lbl"></span>
									</label></td>
									<td><c:out value="${board.bno}" /></td>



									<td>
									<a class='move' href='<c:out value="${board.bno}"/>'>
											<c:out value="${board.title}" />
									</a></td>

									<td><c:out value="${board.writer}" /></td>
									<td><fmt:formatDate pattern="yyyy-MM-dd"
											value="${board.regdate}" /></td>
									<td><fmt:formatDate pattern="yyyy-MM-dd"
											value="${board.updatedate}" /></td>
									<td>
										<div class="hidden-sm hidden-xs action-buttons">
											<a class="blue" href="#"> <i
												class="ace-icon fa fa-search-plus bigger-130"></i>
											</a> <a class="green" href="#"> <i
												class="ace-icon fa fa-pencil bigger-130"></i>
											</a> <a class="red" href="#"> <i
												class="ace-icon fa fa-trash-o bigger-130"></i>
											</a>
										</div>

										<div class="hidden-md hidden-lg">
											<div class="inline pos-rel">
												<button class="btn btn-minier btn-yellow dropdown-toggle"
													data-toggle="dropdown" data-position="auto">
													<i class="ace-icon fa fa-caret-down icon-only bigger-120"></i>
												</button>

												<ul
													class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
													<li><a href="#" class="tooltip-info"
														data-rel="tooltip" title="View"> <span class="blue">
																<i class="ace-icon fa fa-search-plus bigger-120"></i>
														</span>
													</a></li>

													<li><a href="#" class="tooltip-success"
														data-rel="tooltip" title="Edit"> <span class="green">
																<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
														</span>
													</a></li>

													<li><a href="#" class="tooltip-error"
														data-rel="tooltip" title="Delete"> <span class="red">
																<i class="ace-icon fa fa-trash-o bigger-120"></i>
														</span>
													</a></li>
												</ul>


											</div>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<div>
					<form id='searchForm' action="/board/list" method='get'>
					
                <select name ="type">
                  <option value="" <c:out value="${pageMaker.cri.type == null?'selected':''}"/>>--</option>
                  <option value="t"
                    <c:out value="${pageMaker.cri.type == 't'?'selected':''}"/>>제목</option>
                  <option value="c"
                    <c:out value="${pageMaker.cri.type == 'c'?'selected':''}"/>>내용</option>
                  <option value="w"
                    <c:out value="${pageMaker.cri.type == 'w'?'selected':''}"/>>작성자</option>
                  <option value="tc"
                    <c:out value="${pageMaker.cri.type == 'tc'?'selected':''}"/>>제목+내용</option>
                  <option value="tcw"
                    <c:out value="${pageMaker.cri.type == 'tcw'?'selected':''}"/>>제목+내용+작성자</option>
               
                </select>
                
                
                  
                  <input type='text' name='keyword' value='<c:out value="${pageMaker.cri.keyword}"/>'/>
               	  <input type='hidden' name='pageNum' value='<c:out value ="${pageMaker.cri.pageNum}"/>'/>
                  <input type='hidden' name='amount' value='<c:out value ="${pageMaker.cri.amount}"/>'/>
                  <button data-toggle="dropdown" class="btn btn-primary btn-white dropdown-toggle">Search</button>
                  </form>
                </div>
				
                </div>        
					
					
					
					<div class="col-sm-6">
					<ul class="pagination">
						
						<c:if test="${pageMaker.prev}">
						<li class="paginate_button" data-page='${num}'>
							
								<a href="${pageMaker.startPage-1}">Previous</a></li>
						</c:if>
						
						<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="num">
							<li class="paginate_button" data-page='${num}'
								aria-controls="dataTables-example" tabindex="0">
								<a href="${num}"><c:out value="${num}" /></a></li>
						</c:forEach>
						
						<c:if test="${pageMaker.next}">
							<li class="paginate_button" data-page='${num}'>
								<a href="${pageMaker.endPage+1}">next</a></li>
						</c:if>
					</ul>
					</div>

					

					<button class="btn" id='regBtn'>
						<i class="ace-icon fa fa-pencil align-top bigger-125"></i> 글쓰기
					</button>

					<!-- <ul class="pagination pull-right no-margin"> -->


				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<form id='actionForm' action="/board/list" method='get'>
		<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
		<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
		<input type='hidden' name='type' value='<c:out value ="${pageMaker.cri.type}"/>'/>
		<input type='hidden' name='keyword' value='<c:out value ="${pageMaker.cri.keyword}"/>'/>
		
		 
	</form>









	<div id="modal-form" class="modal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">modal</button>
					<h4 class="blue bigger">띵동 모달창</h4>
				</div>

				<div class="modal-body">
					<div class="row">
						<div class="col-xs-12 col-sm-5"></div>

					</div>
				</div>

				<div class="modal-footer">
					<button class="btn btn-sm" data-dismiss="modal">
						<i class="ace-icon fa fa-times"></i> 닫기
					</button>
				</div>
			</div>
		</div>
	</div>
	<!-- PAGE CONTENT ENDS -->

</div>

</div>
<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->
</div>
<!-- PAGE CONTENT ENDS -->
</div>
<!-- /.col -->
</div>
<!-- /.row -->
<%@include file="../includes/footer.jsp"%>


<script type="text/javascript">
	$(document).ready(
			function() {
				var result = '<c:out value="${result}"/>';
				var searchForm = $("#searchForm");
				checkModal(result);
				
				history.replaceState({},null,null);

				function checkModal(result) {
					if (result === ''|| history.state) {
						return;
					}
					if (parseInt(result) > 0) {
						$(".modal-body").html(
								"게시글 " + parseInt(result) + " 번이 등록되었습니다.");
					}
					
					$("#modal-form").modal("show");
				} //checkModal

				$("#regBtn").on("click", function() {
					self.location = "/board/register";
				});//regBtn
				
				 
				
				$(".paginate_button a").on("click",function(e){
					
					e.preventDefault();
					var actionForm  = $("#actionForm");
					console.log('click');
					actionForm.find("input[name='pageNum']").val($(this).attr("href"));
					
					actionForm.submit();
					
				});
				
				
				
				 
				$(".move").on("click",function(e){
					e.preventDefault();
					
					var actionForm  = $("#actionForm");
					actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
					actionForm.attr("action","/board/get");
					actionForm.submit();
				});
				
				
				
				$("#searchForm button").on("click", function(e){
					
					if(!searchForm.find("option:selected").val()){
						alert("검색 종류를 선택하세요");
						return false;
					}
					if(!searchForm.find("input[name='keyword']").val()){
						alert("키워드를 입력하세요");
					}
					
					searchForm.find("input[name='pageNum']").val("1");
					e.preventDefault();
					
					searchForm.submit();
				});//search
				

			});//ready
	/*
	 $(document)
	 .ready(
	 function() {
	 var actionForm = $("#actionForm");
	 var pageNum = $
	 {
	 pageObj.page
	 }
	 ;
	 $("#searchBtn")
	 .on(
	 "click",
	 function(e) {

	 var searchTypeValue = $(
	 "select[name='type'] option:selected")
	 .val();
	 console.log(searchTypeValue);

	 var searchKeyword = $(
	 "input[name='keyword']")
	 .val();
	 console.log(searchKeyword);

	 if (searchKeyword.trim().length == 0) {
	 alert("검색어 없음");
	 return;
	 }

	 actionForm.attr("action",
	 "/board/list");
	 actionForm.find(
	 "input[name='type']").val(
	 searchTypeValue);
	 actionForm.find(
	 "input[name='keyword']")
	 .val(searchKeyword);
	 $("#page").val(1);

	 actionForm.submit();

	 });

	 //게시판 제목 클릭하기
	 $(".board")
	 .on(
	 "click",
	 function(e) {
	 e.preventDefault();
	 var bno = $(this).attr("href");
	 actionForm
	 .append("<input type='hidden' name='bno' value='"+bno+"'>");
	 actionForm.attr("action",
	 "/board/read").attr(
	 "method", "get").submit();
	 });
	 //버튼 활성화
	 $('.pagination li[data-page=' + pageNum + ']')
	 .addClass("active");
	 //버튼 클릭
	 $('.pagination li a').on(
	 "click",
	 function(e) {
	 e.preventDefault();

	 var target = $(this).attr("href");
	 console.log(target);
	 $("#page").val(target);
	 actionForm.attr("action", "/board/list")
	 .attr("method", "get").submit();
	 });
	 //selectbox 코드
	 $('#select').change(
	 function(e) {
	 e.preventDefault();
	 var display = $(this).val();
	 $("#display").val(display);
	 actionForm.attr("action", "/board/list")
	 .attr("method", "get").submit();
	 });
	 var msg = $("#myModal");
	 var result = '<c:out value="${result}"/>';
	 checkModal(result);
	 history.replaceState({}, null, null);
	 function checkModal(result) {
	 if (result === '' || history.state) {
	 return;
	 }
	 if (result === 'success') {
	 $(".modal-body").html("작업 성공");
	 msg.modal("show");
	 }
	 }
	 });
	
	 */
</script>
