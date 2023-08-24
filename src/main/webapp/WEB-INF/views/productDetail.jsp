<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome Baby room</title>
<link rel="stylesheet" href="/resources/css/productDetail.css">
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
</head>
<body>

	<div class="wrapper">
		<div class="wrap">
			<div class="top_gnb_area">
				<ul class="list">

					<c:if test="${member == null}">
						<!-- 로그인 x -->
						<li><a href="/member/login">로그인</a></li>
						<li><a href="/member/join">회원가입</a></li>
					</c:if>

					<c:if test="${member != null}">
						<!-- 로그인 o -->
						<c:if test="${member.adminCk == 1 }">
							<li><a href="/admin/main">관리자 페이지</a></li>
						</c:if>

						<li><a id="gnb_logout_button"> 로그아웃 </a></li>
						<li>마이룸</li>
						<li><a href="/cart/${member.memberId}">장바구니</a></li>
					</c:if>

					<li>고객센터</li>

				</ul>



			</div>
			<div class="top_area">
				<div class="logo_area">
					<a href="/main"><img src="/resources/img/Logo.png"></a>
				</div>
				<div class="search_area">
					<div class="search_wrap">
						<form id="searchForm" action="/search" method="get">
							<div class="search_input">
								<select name="type">
									<option value="T">상품 명</option>
									<option value="B">브랜드</option>
								</select> <input type="text" name="keyword"
									value="<c:out value="${pageMaker.cri.keyword}"/>">
								<button class='btn search_btn'>검 색</button>
							</div>
						</form>
					</div>
				</div>
				<div class="login_area">
					<!--  로그인 하지 않은 상태 -->
					<c:if test="${member == null}">
						<div class="login_button">
							<a href="/member/login">로그인</a>
						</div>
						<span><a href="/member/join">회원가입</a></span>
					</c:if>

					<!--  로그인 상태 -->
					<c:if test="${member != null}">
						<div class="login_success_area">
							<span>회원 : ${member.memberName}</span> <span>충전금액 : <fmt:formatNumber
									value="${member.money }" pattern="\#,###.##" /></span> <span>포인트
								: <fmt:formatNumber value="${member.point }" pattern="#,###" />
							</span> <a href="/member/logout.do">로그아웃</a>
						</div>

					</c:if>

				</div>
				<div class="clearfix"></div>
			</div>

		</div>
		<div class="content_area">
		<div class="line">
			</div>			
			<div class="content_top">
				<div class="ct_left_area">
					<div class="image_wrap" data-productid="${productInfo.imageList[0].productId}" data-path="${productInfo.imageList[0].uploadPath}" data-uuid="${productInfo.imageList[0].uuid}" data-filename="${productInfo.imageList[0].fileName}">
						<img>
					</div>				
				</div>
				<div class="ct_right_area">
					<div class="title">
						<h1>
							${productInfo.productName}
						</h1>
					</div>
					<div class="line">
					</div>
					<div class="brand">
						 <span>
						 브랜드 ${productInfo.brandName} 
						 </span>
						 <span>|</span>
						 <span>
						 판매처 ${productInfo.company}
						 </span>
						 <span>|</span>
						 <span class="releaseDate">
						 	${productInfo.releaseDate}
						 </span>
					</div>
					<div class="line">
					</div>	
					<div class="price">
						<div class="sale_price">정가 : <fmt:formatNumber value="${productInfo.productPrice}" pattern="#,### 원" /></div>
						<div class="discount_price">
							판매가 : <span class="discount_price_number"><fmt:formatNumber value="${productInfo.productPrice - (productInfo.productPrice*productInfo.productDiscount)}" pattern="#,### 원" /></span> 
							[<fmt:formatNumber value="${productInfo.productDiscount*100}" pattern="###" />% 
							<fmt:formatNumber value="${productInfo.productPrice*productInfo.productDiscount}" pattern="#,### 원" /> 할인]</div>							
					</div>	
					<div>
							적립 포인트 : <span class="point_span"></span>원
						</div>		
					<div class="line">
					</div>	
					<div class="button">						
						<div class="button_quantity">
							주문수량
							<input type="text"  class="quantity_input" value="1">
							<span>
								<button class="plus_btn">+</button>
								<button class="minus_btn">-</button>
							</span>
						</div>
						<div class="button_set">
							<a class="btn_cart">장바구니 담기</a>
							<a class="btn_buy">바로구매</a>
						</div>
					</div>
				</div>
			</div>
			<div class="line">
			</div>				
			<div class="content_middle">
				<div class="product_intro">
					${productInfo.productIntro}
				</div>
				<div class="product_content">
					${productInfo.productContents }
				</div>
			</div>
			<div class="line">
			</div>				
			<div class="content_bottom">
				<div class="review_subject">
					<h2>리뷰</h2>
				</div>
				<c:if test="${member != null}">
				<div class="review_button_wrap">
					<button>리뷰 쓰기</button>
				</div>
			</c:if>
			
			<div class="review_not_div">
					
				</div>
				<ul class="review_content_ul">
				<!-- 
				<li>
						<div class="comment_wrap">
							<div class="review_top">
								<span class="id_span">sjinjin7</span>
								<span class="date_span">2021-10-11</span>
								<span class="rating_span">평점 : <span class="rating_value_span">4</span>점</span>
								<a class="update_review_btn">수정</a><a class="delete_review_btn">삭제</a>
							</div>
							<div class="review_bottom">
								<div class="review_bottom_txt">
									사실 기대를 많이하고 읽기시작했는데 읽으면서 가가 쓴것이 맞는지 의심들게합니다 문체도그렇고 간결하지 않네요 제가 기대가 크던 작았던간에 책장이 사실 안넘겨집니다.
								</div>
							</div>
						</div>
					</li>
					 -->
				</ul>
				<div class="review_pageInfo_div">
				<ul class="pageMaker">
				
				<!-- 
						<li class="pageMaker_btn prev">
							<a>이전</a>
						</li>
						<li class="pageMaker_btn">
							<a>1</a>
						</li>
						<li class="pageMaker_btn">
							<a>2</a>
						</li>
						<li class="pageMaker_btn active">
							<a>3</a>
						</li>													
						<li class="pageMaker_btn next">
							<a>다음</a>
						</li>
						-->
					</ul>

				</div>
			
			
			</div>
			<!-- 주문 form -->
			<form action="/order/${member.memberId}" method="get" class="order_form">
				<input type="hidden" name="orders[0].productId" value="${productInfo.productId}">
				<input type="hidden" name="orders[0].productCount" value="">
			</form>

		</div>

		<!-- Footer 영역 -->
		<div class="footer_nav">
			<div class="footer_nav_container">
				<ul>
					<li>회사소개</li>
					<span class="line">|</span>
					<li>이용약관</li>
					<span class="line">|</span>
					<li>고객센터</li>
					<span class="line">|</span>
					<li>광고문의</li>
					<span class="line">|</span>
					<li>채용정보</li>
					<span class="line">|</span>
				</ul>
			</div>
		</div>
		<!-- class="footer_nav" -->

		<div class="footer">
			<div class="footer_container">

				<div class="footer_left">
					<img src="/resources/img/Logo.png">
				</div>
				<div class="footer_right">
						(주) BABY ROOM 대표이사 : OOO <br> 사업자등록번호 : ooo-oo-ooooo <br>
						대표전화 : oooo-oooo(발신자 부담전화) <br> <br> COPYRIGHT(C) <strong>https://github.com/park939393/Byby</strong>
						ALL RIGHTS RESERVED.
					</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<!-- class="footer" -->


	</div>
	

</body>

<script>


$(document).ready(function(){
	
	/* 이미지 삽입 */
	const bobj = $(".image_wrap");
	
	if(bobj.data("productid")){
		const uploadPath = bobj.data("path");
		const uuid = bobj.data("uuid");
		const fileName = bobj.data("filename");
		
		const fileCallPath = encodeURIComponent(uploadPath + "/s_" + uuid + "_" + fileName);
		
		bobj.find("img").attr('src', '/display?fileName=' + fileCallPath);
	} else {
		bobj.find("img").attr('src', '/resources/img/productNoImage.png');
	}
	
	
	/* releaseDate */
	const year = "${productInfo.releaseDate}";
	
	let tempYear = year.substr(0,10);
	
	let yearArray = tempYear.split("-")
	let releaseDate = yearArray[0] + "년 " + yearArray[1] + "월 " + yearArray[2] + "일";
	
	$(".releaseDate").html(releaseDate);
	
	/* 포인트 삽입 */
	let salePrice = "${productInfo.productPrice - (productInfo.productPrice*productInfo.productDiscount)}"
	let point = salePrice*0.05;
	point = Math.floor(point);
	$(".point_span").text(point);
	
	/*리뷰 리스트 출력*/
	
	const productId = '${productInfo.productId}';	

	$.getJSON("/review/list", {productId : productId}, function(obj){
		
		makeReviewContent(obj);
		
	});
	
	
});	

//수량 버튼 조작
let quantity = $(".quantity_input").val();
$(".plus_btn").on("click", function(){
	$(".quantity_input").val(++quantity);
});
$(".minus_btn").on("click", function(){
	if(quantity > 1){
	$(".quantity_input").val(--quantity);
	}
});

//서버로 전송할 데이터
const form = {
		memberId : '${member.memberId}',
		productId : '${productInfo.productId}',
		productCount : ''
}
//장바구니 추가 버튼
$(".btn_cart").on("click", function(e){
		form.productCount = $(".quantity_input").val();
		$.ajax({
			url: '/cart/add',
			type: 'POST',
			data: form,
			success: function(result){
				cartAlert(result);
			}
		})
	});
	
	function cartAlert(result){
		if(result == '0'){
			alert("장바구니에 추가를 하지 못하였습니다.");
		} else if(result == '1'){
			alert("장바구니에 추가되었습니다.");
		} else if(result == '2'){
			alert("장바구니에 이미 추가되어져 있습니다.");
		} else if(result == '5'){
			alert("로그인이 필요합니다.");	
		}
	}
	
	/* 바로구매 버튼 */
	$(".btn_buy").on("click", function(){
		let productCount = $(".quantity_input").val();
		$(".order_form").find("input[name='orders[0].productCount']").val(productCount);
		$(".order_form").submit();
	});
	
	/* 리뷰쓰기 */
	$(".review_button_wrap").on("click", function(e){
		
		e.preventDefault();		
		
		const memberId = '${member.memberId}';
		const productId = '${productInfo.productId}';
		
		$.ajax({
			data : {
				productId : productId,
				memberId : memberId
			},
			url : '/review/check',
			type : 'POST',
			success : function(result){
				
				if(result === '1'){
					alert("이미 등록된 리뷰가 존재 합니다.")
				} else if(result === '0'){
				let popUrl = "/reviewEnroll/" + memberId + "?productId=" + productId;
				console.log(popUrl);
				let popOption = "width = 490px, height=490px, top=300px, left=300px, scrollbars=yes";
				
				window.open(popUrl,"리뷰 쓰기",popOption);
				}

			}
		});
	
		
	});
	
	/* 댓글 데이터 서버 요청 및 댓글 동적 생성 메서드 */
	let reviewListInit = function(){
		$.getJSON("/review/list", cri , function(obj){
			
			makeReviewContent(obj);
			
		});		
	}
	/* 댓글(리뷰) 동적 생성 메서드 */
	function makeReviewContent(obj){
		if(obj.list.length === 0){
			$(".review_not_div").html('<span>리뷰가 없습니다.</span>');
			$(".review_content_ul").html('');
			$(".pageMaker").html('');
		} else{
			
			$(".review_not_div").html('');
			
			const list = obj.list;
			const pf = obj.pageInfo;
			const userId = '${member.memberId}';		
			
			/* list */
			
			let review_list = '';			
			
			$(list).each(function(i,obj){
				review_list += '<li>';
				review_list += '<div class="comment_wrap">';
				review_list += '<div class="review_top">';
				/* 아이디 */
				review_list += '<span class="id_span">'+ obj.memberId+'</span>';
				/* 날짜 */
				review_list += '<span class="date_span">'+ obj.regDate +'</span>';
				/* 평점 */
				review_list += '<span class="rating_span">평점 : <span class="rating_value_span">'+ obj.rating +'</span>점</span>';
				if(obj.memberId === userId){
					review_list += '<a class="update_review_btn" href="'+ obj.reviewId +'">수정</a><a class="delete_review_btn" href="'+ obj.reviewId +'">삭제</a>';
				}
				review_list += '</div>'; //<div class="review_top">
				review_list += '<div class="review_bottom">';
				review_list += '<div class="review_bottom_txt">'+ obj.content +'</div>';
				review_list += '</div>';//<div class="review_bottom">
				review_list += '</div>';//<div class="comment_wrap">
				review_list += '</li>';
			});		
			
			$(".review_content_ul").html(review_list);			
			
			/* 페이지 버튼 */
			
			let review_pageMaker = '';	
			
				/* prev */
				if(pf.prev){
					let prev_num = pf.pageStart -1;
					review_pageMaker += '<li class="pageMaker_btn prev">';
					review_pageMaker += '<a href="'+ prev_num +'">이전</a>';
					review_pageMaker += '</li>';	
				}
				/* numbre btn */
				for(let i = pf.pageStart; i < pf.pageEnd+1; i++){
					review_pageMaker += '<li class="pageMaker_btn ';
					if(pf.cri.pageNum === i){
						review_pageMaker += 'active';
					}
					review_pageMaker += '">';
					review_pageMaker += '<a href="'+i+'">'+i+'</a>';
					review_pageMaker += '</li>';
				}
				/* next */
				if(pf.next){
					let next_num = pf.pageEnd +1;
					review_pageMaker += '<li class="pageMaker_btn next">';
					review_pageMaker += '<a href="'+ next_num +'">다음</a>';
					review_pageMaker += '</li>';	
				}	
				
			$(".pageMaker").html(review_pageMaker);				
			
		}
	}
	
	/* 댓글 페이지 정보 */
	 const cri = {
		productId : '${productInfo.productId}',
		pageNum : 1,
		amount : 10
	}
	
	 /* 댓글 페이지 이동 버튼 동작 */
		$(document).on('click', '.pageMaker_btn a', function(e){
			e.preventDefault();
			
			let page = $(this).attr("href");	
			cri.pageNum = page;		
			
			reviewListInit();
				
		 });
	
	 /* 리뷰 수정 버튼 */
	 $(document).on('click', '.update_review_btn', function(e){
		 
		 e.preventDefault();
			let reviewId = $(this).attr("href");		 
			let popUrl = "/reviewUpdate?reviewId=" + reviewId + "&productId=" + '${productInfo.productId}' + "&memberId=" + '${member.memberId}';	
			let popOption = "width = 490px, height=490px, top=300px, left=300px, scrollbars=yes"	
			
			window.open(popUrl,"리뷰 수정",popOption);	
			
	 });
	 
		/* 리뷰 삭제 버튼 */
	 $(document).on('click', '.delete_review_btn', function(e){
		 
		 e.preventDefault();
			let reviewId = $(this).attr("href");	
			
			$.ajax({
				data : {
					reviewId : reviewId,
					productId : '${productInfo.productId}'
				},
				url : '/review/delete',
				type : 'POST',
				success : function(result){
					reviewListInit();
					alert('삭제가 완료되엇습니다.');
				}
			});		
				
			
	 });
	 /* gnb_area 로그아웃 버튼 작동 */
		$("#gnb_logout_button").click(function() {
			//alert("버튼 작동");
			$.ajax({
				type : "POST",
				url : "/member/logout.do",
				success : function(data) {
					//alert("로그아웃 성공");
					document.location.reload();
				}
			}); // ajax	
		});
	
	
</script>
	

</html>