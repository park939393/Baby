<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../resources/css/admin/productDetail.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdn.ckeditor.com/ckeditor5/26.0.0/classic/ckeditor.js"></script>
</head>
<body>

	<%@include file="../includes/admin/header.jsp"%>
	<div class="admin_content_wrap">
		<div class="admin_content_subject">
			<span>상품 상세</span>
		</div>

		<div class="admin_content_main">

			<div class="form_section">
				<div class="form_section_title">
					<label>책 제목</label>
				</div>
				<div class="form_section_content">
					<input name="productName"
						value="<c:out value="${productInfo.productName}"/>" disabled>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>등록 날짜</label>
				</div>
				<div class="form_section_content">
					<input
						value="<fmt:formatDate value='${productInfo.regDate}' pattern='yyyy-MM-dd'/>"
						disabled>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>최근 수정 날짜</label>
				</div>
				<div class="form_section_content">
					<input
						value="<fmt:formatDate value='${productInfo.updateDate}' pattern='yyyy-MM-dd'/>"
						disabled>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>작가</label>
				</div>
				<div class="form_section_content">
					<input id="brandName_input" readonly="readonly"
						value="${productInfo.brandName }" disabled>

				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>출시일</label>
				</div>
				<div class="form_section_content">
					<input name="releaseDate" autocomplete="off" readonly="readonly"
						value="<c:out value="${productInfo.releaseDate}"/>" disabled>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>판매처</label>
				</div>
				<div class="form_section_content">
					<input name="company"
						value="<c:out value="${productInfo.company}"/>" disabled>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>상품 카테고리</label>
				</div>
				<div class="form_section_content">
					<div class="cate_wrap">
						<span>대분류</span> <select class="cate1" disabled>
							<option value="none">선택</option>
						</select>
					</div>
					<div class="cate_wrap">
						<span>중분류</span> <select class="cate2" disabled>
							<option value="none">선택</option>
						</select>
					</div>
					<div class="cate_wrap">
						<span>소분류</span> <select class="cate3" name="cateCode" disabled>
							<option value="none">선택</option>
						</select>
					</div>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>상품 가격</label>
				</div>
				<div class="form_section_content">
					<input name="productPrice"
						value="<c:out value="${productInfo.productPrice}"/>" disabled>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>상품 재고</label>
				</div>
				<div class="form_section_content">
					<input name="productStock"
						value="<c:out value="${productInfo.productStock}"/>" disabled>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>상품 할인율</label>
				</div>
				<div class="form_section_content">
					<input id="discount_interface" maxlength="2" disabled>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>상품 소개</label>
				</div>
				<div class="form_section_content bit">
					<textarea name="productIntro" id="productIntro_textarea" disabled>${productInfo.productIntro}</textarea>
				</div>
			</div>
			<div class="form_section">
				<div class="form_section_title">
					<label>상품 내용</label>
				</div>
				<div class="form_section_content bct">
					<textarea name="productContents" id="productContents_textarea"
						disabled>${productInfo.productContents}</textarea>
				</div>
			</div>

			<div class="btn_section">
				<button id="cancelBtn" class="btn">상품 목록</button>
				<button id="enrollBtn" class="btn enroll_btn">수정</button>
				${productInfo.cateCode}
			</div>
		</div>


		<form id="moveForm" action="/admin/productManage" method="get">
			<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
			<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
			<input type="hidden" name="keyword" value="${pageMaker.cri.keyword}">
		</form>

	</div>
	<%@include file="../includes/admin/footer.jsp"%>

	<script>
	
$(document).ready(function(){
			
			/* 할인율 값 삽입 */
			let productDiscount = '<c:out value="${productInfo.productDiscount}"/>' * 100;
			$("#discount_interface").attr("value", productDiscount);
			
			
			/* 카테고리 */
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
			
			let targetCate2 = '';
			let targetCate3 = '${productInfo.cateCode}';
			

				/* 카테고리 배열 초기화 메서드 */
				function makeCateArray(obj,array,cateList, tier){
					for(let i = 0; i < cateList.length; i++){
						if(cateList[i].tier === tier){
							obj = new Object();
							
							obj.cateName = cateList[i].cateName;
							obj.cateCode = cateList[i].cateCode;
							obj.cateParent = cateList[i].cateParent;
							
							array.push(obj);				
							
						}
					}
				}	
			
			/* 배열 초기화 */
			makeCateArray(cate1Obj,cate1Array,cateList,1);
			makeCateArray(cate2Obj,cate2Array,cateList,2);
			makeCateArray(cate3Obj,cate3Array,cateList,3);
			
				
				
				
				for (let i = 0; i < cate3Array.length; i++) {
					if (targetCate3 == cate3Array[i].cateCode) {
					   
					    targetCate3 = cate3Array[i];
					    break;
					  }
					}
				
				
					for(let i = 0; i < cate3Array.length; i++){
						if(targetCate3.cateParent === cate3Array[i].cateParent){
						cateSelect3.append("<option value='"+cate3Array[i].cateCode+"'>" + cate3Array[i].cateName + "</option>");
						}
					}
					
					
					$(".cate3 option").each(function(i,obj){
 	 					if(targetCate3.cateCode === obj.value){
 	 						$(obj).attr("selected", "selected");
 	 					}
 	 				});
					
					
					for(let i = 0; i < cate2Array.length; i++){
 	 					if(targetCate3.cateParent === cate2Array[i].cateCode){
 	 						targetCate2 = cate2Array[i];	
 	 					}
 	 				}			
 	 				
 	 				
 	 				for(let i = 0; i < cate2Array.length; i++){
 	 					if(targetCate2.cateParent === cate2Array[i].cateParent){
 	 						cateSelect2.append("<option value='"+cate2Array[i].cateCode+"'>" + cate2Array[i].cateName + "</option>");
 	 					}
 	 				}		
 	 				
 	 				$(".cate2 option").each(function(i,obj){
 	 					if(targetCate2.cateCode === obj.value){
 	 						$(obj).attr("selected", "selected");
 	 					}
 	 				});	
 	 				
 	 				
 	 				for(let i = 0; i < cate1Array.length; i++){
 	 					cateSelect1.append("<option value='"+cate1Array[i].cateCode+"'>" + cate1Array[i].cateName + "</option>");
 	 				}
 	 				$(".cate1 option").each(function(i,obj){
 	 					if(targetCate2.cateParent === obj.value){
 	 						$(obj).attr("selected", "selected");
 	 					}
 	 				});	
	 		
		}); 
		
		/* 책 소개 */
		ClassicEditor
			.create(document.querySelector('#productIntro_textarea'))
			.then(editor => {
					console.log(editor);
					editor.isReadOnly = true;
				})
			.catch(error=>{
				console.error(error);
			});
			
		/* 책 목차 */	
		ClassicEditor
		.create(document.querySelector('#productContents_textarea'))
		.then(editor => {
					console.log(editor);
					editor.isReadOnly = true;
				})
		.catch(error=>{
			console.error(error);
		});		
	
	</script>
</body>
</html>