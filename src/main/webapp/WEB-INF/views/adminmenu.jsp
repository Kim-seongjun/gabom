<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서비스업체 등록 심사</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- 제이쿼리 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- 부트스트랩 -->

<style>
#admin_div{
	border: 1px solid black;
	width: 1560px;
	height: 1000px;
}
/* #admin_menu{ */
/* 	border: 1px solid black; */
/* 	width: 10%; */
/* 	height: 50%; */
/* 	float: left; */
/* } */
#admin_list{
	
/* 	float: right; */
}
#service_judge{
	border: 1px solid black; 
}
#tour_judge{
	border: 1px solid black;
}
ul{
	list-style:none;
}
li{
	
	float: left;
	
}

</style>
</head>
<body>
<h3>adminmenu.jsp</h3>
<div id="admin_div">
	
	<div id="admin_menu" class="btn-group btn-group-sm" role="group"> <!-- aria-label="..." -->
  		<ul>
			<li><a href="#"> 서비스업체 등록심사 &nbsp</a></li>
			<li><a href="#"> 서비스업체 신고관리 &nbsp</a></li>
			<li><a href="#"> 여행객계획 등록심사 &nbsp</a></li>
			<li><a href="#"> 여행객 신고관리 &nbsp</a></li>
			<li><a href="#"> 전체회원 공지사항 &nbsp</a></li>
			<li><a href="#"> Q&A &nbsp</a></li>
			<li><a href="#"> @ &nbsp</a></li>
		</ul>
	</div>
	<div id="admin_list">
		<div id="service_judge">
			<form action="servicejudgelist" method="post">
				서비스업체 신청내역
			</form>
		</div>
		<div id="tour_judge">
			<form action="touristjudgelist" method="post">
				여행계획 신청 내역
			</form>
		</div>	
	</div>

</div>

</body>
</html>