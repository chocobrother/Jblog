<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.servletContext.contextPath }/assets/css/jblog.css">
<script
	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"
	type="text/javascript"></script>
<script>
$(function() {

	$("#btn-categoryadd")
			.click(
					function() {

		
						var name = $("#name").val();
						var content = $("#content1").val();
					
						if (name == ""||content=="") {
							console.log("hhhhhhhhhh");
							return;
						}
						
						$
								.ajax({

									url : "${pageContext.servletContext.contextPath }/api/blog/categoryadd?name="
											+ name +"&content="+content,
									type : "get",
									data :""
									/* {
										"name":name,
										"content":content
									} */,
									dataType : "json", //응답 받을 데이터 타입
									success : function(response) {
									
										
											$('#cate-table tr:first').after(
													"<tr>"+
													"<td>"+$('#cate-table tr').length+"</td>"+
													"<td>"+name+"</td>"+
													"<td>"+0+"</td>"+
													"<td>"+content+"</td>"+
													"<td><image class = 'cate-delete' data-no='"+response.no+ 
													/* data-no="
													+response.no+ */
													"' src='${pageContext.servletContext.contextPath }/assets/images/delete.jpg'></td>"+
													"</tr>"
												
													);

									},
									error : function(xhr, status, e) {
										console.error(status + ":" + e);
									}

								});
					

					});

});

$(document).on("click", ".cate-delete", function(){
	var tmp = $(this); 
	var no = tmp.attr("data-no")
	console.log(no);
	$
	.ajax({
	url : "${pageContext.servletContext.contextPath}/api/blog/categorydelete", //요청할 URL
	dataType : "json", //응답받을 데이터타입
	type : "post", //요청 방식
	data : {"no" : no}, //서버에 요청시 보낼 파라미터 ex) {name:홍길동}
	success : function(response) { //요청 및 응답에 성공했을 경우
	tmp.closest("tr").remove();
	},
	error : function(xhr, status, e) { //요청 및 응답에 실패 한 경우
	console.error(status + ":" + e);

	}
	});
	})



</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${map.vo.title} 이야기</h1>
			<c:import url ="/WEB-INF/views/includes/blog-header.jsp" />
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.servletContext.contextPath }/blog/admin">기본설정</a></li>
					<li class="selected"><a href="${pageContext.servletContext.contextPath }/blog/category">카테고리</a></li>
					<li><a href="${pageContext.servletContext.contextPath }/blog/write">글작성</a></li>
					<li><a href="${pageContext.servletContext.contextPath }/${authUser.id}">메인화면</a></li>
					
				</ul>
				
		      	<table class="admin-cat" id="cate-table">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<c:set var = "count" value="${fn:length(map.list) }"/>
		      		<c:forEach items="${ map.list }" var="list" varStatus="status">
					<tr>
						<td>${count-status.index}</td>
						<td>${list.posttype }</td>
						<td>${list.count }</td>
						<td>${list.content }</td>
						<td>
						
						<!--  <a href="${pageContext.servletContext.contextPath }/blog/categorydelete?no=${list.no}" class="del" data-no="10"></a>-->
					  	<img class="cate-delete" data-no="${list.no}" src="${pageContext.servletContext.contextPath }/assets/images/delete.jpg">						
						</td>
					</tr>  
					</c:forEach>
				</table>
				<br/>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<!--  <form action="${pageContext.servletContext.contextPath }/blog/categoryadd" method="post">-->
		      	
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input id="name" type="text" name="posttype"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input id="content1" type="text" name="content"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input id="btn-categoryadd" type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
		     	
			</div>
		</div>
		<div id="footer">
			<p>
			<c:import url ="/WEB-INF/views/includes/blog-footer.jsp" />
			</p>
		</div>
	</div>
</body>
</html>