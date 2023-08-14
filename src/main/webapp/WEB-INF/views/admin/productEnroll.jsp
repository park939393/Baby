<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/admin/productEnroll.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />

<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous"></script>
<script
	src="https://cdn.ckeditor.com/ckeditor5/39.0.1/classic/ckeditor.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
</head>
</head>
<body>

	<%@include file="../includes/admin/header.jsp"%>
	<div class="admin_content_wrap">
		<div class="admin_content_subject">
			<span>상품 등록</span>
		</div>
		<div class="admin_content_main">
			<form action="/admin/productEnroll" method="post" id="enrollForm">
				<div class="form_section">
					<div class="form_section_title">
						<label>상품 제목</label>
					</div>
					<div class="form_section_content">
						<input name="productName">
						<span class="ck_warn productName_warn">상품 이름을 입력해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>브랜드</label>
					</div>
					<div class="form_section_content">
						<input id="brandName_input" readonly="readonly"> <input
							id="brandId_input" name="brandId" type="hidden">
						<button class="brandId_btn">브랜드 선택</button>
						<span class="ck_warn brandId_warn">작가를 선택해주세요</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>출시일</label>
					</div>
					<div class="form_section_content">
						<input name="releaseDate" autocomplete="off" readonly="readonly">
						<span class="ck_warn releaseDate_warn">출시일을 선택해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>판매처</label>
					</div>
					<div class="form_section_content">
						<input name="company">
						<span class="ck_warn company_warn">판매처를 입력해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>상품 카테고리</label>
					</div>
					<div class="form_section_content">
						<div class="cate_wrap">
							<span>대분류</span> <select class="cate1">
								<option selected value="none">선택</option>
							</select>
						</div>
						<div class="cate_wrap">
							<span>중분류</span> <select class="cate2">
								<option selected value="none">선택</option>
							</select>
						</div>
						<div class="cate_wrap">
							<span>소분류</span> <select class="cate3" name="cateCode">
								<option selected value="none">선택</option>
							</select>
						</div>
						<span class="ck_warn cateCode_warn">카테고리를 선택해주세요.</span> 
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>상품 가격</label>
					</div>
					<div class="form_section_content">
						<input name="productPrice" value="0">
						<span class="ck_warn productPrice_warn">상품 가격을 입력해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>상품 재고</label>
					</div>
					<div class="form_section_content">
						<input name="productStock" value="0">
						<span class="ck_warn productStock_warn">상품 재고를 입력해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>상품 할인율</label>
					</div>
					<div class="form_section_content">
						<input id="discount_interface" maxlength="2" value="0">
						<input name="productDiscount" type="hidden" value="0">
						<span class="step_val">할인 가격 : <span class="span_discount"></span></span>
						<span class="ck_warn productDiscount_warn">1~99 숫자를 입력해주세요.</span>
					
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>상품 소개</label>
					</div>
					<div class="form_section_content bit">
						<textarea name="productIntro" id="productIntro_textarea"></textarea>
						<span class="ck_warn productIntro_warn">상품 소개를 입력해주세요.</span>
					</div>
				</div>
				<div class="form_section">
					<div class="form_section_title">
						<label>상품 내용</label>
					</div>
					<div class="form_section_content bct">
						<textarea name="productContents" id="productContents_textarea"></textarea>
						<span class="ck_warn productContents_warn">상품 내용를 입력해주세요.</span>
					</div>
				</div>
			</form>
			<div class="btn_section">
				<button id="cancelBtn" class="btn">취 소</button>
				<button id="enrollBtn" class="btn enroll_btn">등 록</button>
			</div>
		</div>
	</div>
	<div class="clearfix"></div>
	</div>

	<%@include file="../includes/admin/footer.jsp"%>
	<script>

	let enrollForm = $("#enrollForm")
	
/* 취소 버튼 */
$("#cancelBtn").click(function(){
	
	location.href="/admin/productManage"
	
});

/* 상품 등록 버튼 */
$("#enrollBtn").on("click",function(e){
	
	e.preventDefault();
	
	/* 체크 변수 */
	let productNameCk = false;
	let brandIdCk = false;
	let releaseDateCk = false;
	let companyCk = false;
	let cateCodeCk = false;
	let priceCk = false;
	let stockCk = false;
	let discountCk = false;
	let introCk = false;
	let contentsCk = false;
	
	/* 체크 대상 변수 */
	let productName = $("input[name='productName']").val();
	let brandId = $("input[name='brandId']").val();
	let releaseDate = $("input[name='releaseDate']").val();
	let company = $("input[name='company']").val();
	let cateCode = $("select[name='cateCode']").val();
	let productPrice = $("input[name='productPrice']").val();
	let productStock = $("input[name='productStock']").val();
	let productDiscount = $("#discount_interface").val();
	let productIntro = $(".bit p").html();
	let productContents = $(".bct p").html();
	
	/* 공란 체크 */
	
	if(productName){
		$(".productName_warn").css('display','none');
		productNameCk = true;
	} else {
		$(".productName_warn").css('display','block');
		productNameCk = false;
	}
	
	if(brandId){
		$(".brandId_warn").css('display','none');
		brandIdCk = true;
	} else {
		$(".brandId_warn").css('display','block');
		brandIdCk = false;
	}
	
	if(releaseDate){
		$(".releaseDate_warn").css('display','none');
		releaseDateCk = true;
	} else {
		$(".releaseDate_warn").css('display','block');
		releaseDateCk = false;
	}	
	
	if(company){
		$(".company_warn").css('display','none');
		companyCk = true;
	} else {
		$(".company_warn").css('display','block');
		companyCk = false;
	}
	
	if(cateCode != 'none'){
		$(".cateCode_warn").css('display','none');
		cateCodeCk = true;
	} else {
		$(".cateCode_warn").css('display','block');
		cateCodeCk = false;
	}	
	
	if(productPrice != 0){
		$(".productPrice_warn").css('display','none');
		priceCk = true;
	} else {
		$(".productPrice_warn").css('display','block');
		priceCk = false;
	}	
	
	if(productStock != 0){
		$(".productStock_warn").css('display','none');
		stockCk = true;
	} else {
		$(".productStock_warn").css('display','block');
		stockCk = false;
	}		
	
	if(!isNaN(productDiscount)){
		$(".productDiscount_warn").css('display','none');
		discountCk = true;
	} else {
		$(".productDiscount_warn").css('display','block');
		discountCk = false;
	}	
	
	if(productIntro != '<br data-cke-filler="true">'){
		$(".productIntro_warn").css('display','none');
		introCk = true;
	} else {
		$(".productIntro_warn").css('display','block');
		introCk = false;
	}	
	
	if(productContents != '<br data-cke-filler="true">'){
		$(".productContents_warn").css('display','none');
		contentsCk = true;
	} else {
		$(".productContents_warn").css('display','block');
		contentsCk = false;
	}
	
	if(productNameCk && brandIdCk && releaseDateCk && companyCk && cateCodeCk && priceCk && stockCk && discountCk && introCk && contentsCk ){
		//alert('통과');
		enrollForm.submit();
	} else {
		return false;
	}
	
	
});


/*위지윅 적용*/
 

	/*상품 소개*/
ClassicEditor
		.create(document.querySelector('#productIntro_textarea'))
		.catch(error=>{
			 	console.error(error);
		});
		
	/*상품 내용*/
ClassicEditor
		.create(document.querySelector('#productContents_textarea'))
		.catch(error=>{
			 	console.error(error);
		});
	
/*캘린더 적용*/

/* 설정 */
const config = {
		dateFormat: 'yy-mm-dd',
		showOn : "button",
		buttonText:"날짜 선택",
		prevText: '이전 달',
	    nextText: '다음 달',
	    monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	    dayNames: ['일','월','화','수','목','금','토'],
	    dayNamesShort: ['일','월','화','수','목','금','토'],
	    dayNamesMin: ['일','월','화','수','목','금','토'],
	    yearSuffix: '년',
	    changeMonth: true,
        changeYear: true
}

/* 캘린더 */
$(function() {
	  $( "input[name='releaseDate']" ).datepicker(config);
	});

/* 브랜드 선택 버튼 */
$('.brandId_btn').on("click",function(e){
	
	e.preventDefault();
	
	let popUrl = "/admin/brandPop";
	let popOption = "width = 650px, height = 550px, top=300px, left=300px, scrollbars=yes";
	
	window.open(popUrl, "브랜드 찾기", popOption);
	
});

/* $(document).ready(function(){
 	console.log('${cateList}');
 	}); */
 
 /*카테고리 */
 
 let cateList = JSON.parse('${cateList}');
 	
 	let cate1Array = new Array();
	let cate2Array = new Array();
	let cate3Array = new Array();
	let cate1Obj = new Object();
	let cate2Obj = new Object();
	let cate3Obj = new Object();
	
	let cateSelect1 = $(".cate1");		
	let cateSelect2 = $(".cate2");
	let cateSelect3 = $(".cate3");
	
	
	/* 카테고리 배열 초기화 메서드 */
	function makeCateArray(obj,array,cateList, tier){
			for(let i = 0; i < cateList.length; i++){
				if(cateList[i].tier === tier){
					Obj = new Object();
				
					Obj.cateName = cateList[i].cateName;
					Obj.cateCode = cateList[i].cateCode;
					Obj.cateParent = cateList[i].cateParent;
				
				array.push(Obj);
			}	
		} 
	}
	
	/*배열 초기화*/
	makeCateArray(cate1Obj,cate1Array,cateList,1);
	makeCateArray(cate2Obj,cate2Array,cateList,2);
	makeCateArray(cate3Obj,cate3Array,cateList,3);
	
	/*
	$(document).ready(function(){
			console.log(cate1Array);
			console.log(cate2Array);
			console.log(cate3Array);
			
	});
	*/
	
	/* 대분류 <option> 태그 */
	for(let i = 0; i < cate1Array.length; i++){
		cateSelect1.append("<option value='"+cate1Array[i].cateCode+"'>" + cate1Array[i].cateName + "</option>");
	}
	
	/* 중분류 <option> 태그 */
	$(cateSelect1).on("change",function(){
		
		let selectVal1 = $(this).find("option:selected").val();
		
		cateSelect2.children().remove();
		cateSelect3.children().remove();
		
		cateSelect2.append("<option value='none'>선택</option>")
		cateSelect3.append("<option value='none'>선택</option>");
		
		for(let i = 0; i < cate2Array.length; i++){
			if(selectVal1 === cate2Array[i].cateParent){
				cateSelect2.append("<option value='"+cate2Array[i].cateCode+"'>" + cate2Array[i].cateName + "</option>");	
			}
		} // for
			
	});
	
	/* 소분류 <option>태그 */
	$(cateSelect2).on("change",function(){
		
		let selectVal2 = $(this).find("option:selected").val();
		
		cateSelect3.children().remove();
		
		cateSelect3.append("<option value='none'>선택</option>");
		
		for(let i = 0; i < cate3Array.length; i++){
			if(selectVal2 === cate3Array[i].cateParent){
				cateSelect3.append("<option value='"+cate3Array[i].cateCode+"'>" + cate3Array[i].cateName + "</option>");	
			}
		}// for		
		
		
	});
	
	/* 할인율 Input 설정 */
	$("#discount_interface").on("propertychange change keyup paste input", function(){
		
		let userInput = $("#discount_interface");
		let discountInput = $("input[name='productDiscount']");
		
		let discountRate = userInput.val();					// 사용자가 입력할 할인값
		let sendDiscountRate = discountRate / 100;		// 서버에 전송할 할인값
		
		let goodsPrice = $("input[name='productPrice']").val();			// 원가
		let discountPrice = goodsPrice * (1 - sendDiscountRate);		// 할인가격
		 
		if(!isNaN(discountRate)){
			
		$(".span_discount").html(discountPrice);
		
		discountInput.val(sendDiscountRate);
		}
		
	});	
	
	$("input[name='productPrice']").on("change", function(){
		
		let userInput = $("#discount_interface");
		let discountInput = $("input[name='productDiscount']");
		
		let discountRate = userInput.val();					// 사용자가 입력한 할인값
		let sendDiscountRate = discountRate / 100;			// 서버에 전송할 할인값
		let goodsPrice = $("input[name='productPrice']").val();			// 원가
		let discountPrice = goodsPrice * (1 - sendDiscountRate);		// 할인가격
		
		if(!isNaN(discountRate)){
			
			$(".span_discount").html(discountPrice);
		}
	});
	
	
	
	
	





</script>
</body>
</html>
