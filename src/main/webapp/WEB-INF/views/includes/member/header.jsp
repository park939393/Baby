<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <div class="wrapper">
        <div class="wrap">
            <!-- gnv_area -->    
            <div class="top_gnb_area">
                <ul class="list">    
                    <li><a href="/main">메인 페이지</a></li>
                    <li><a href="/member/logout.do">로그아웃</a></li>
                    <li>고객센터</li>            
                </ul>
            </div>
            <!-- top_subject_area -->
            <div class="admin_top_wrap">
                <span>마이 페이지  ${memberInfo.memberId}</span>
                
            </div>
            <!-- contents-area -->
            <div class="admin_wrap">
                <!-- 네비영역 -->
                <div class="admin_navi_wrap">
                  <ul>
                      <li >
                          <a class="admin_list_01" href="/member/myPageModify/${memberInfo.memberId}">비밀번호 변경</a>
                      </li>
                      <li>
                          <a class="admin_list_02" href="/member/myPageDelete/${memberInfo.memberId}">회원 탈퇴</a>
                      </li>
                      <lI>
                          <a class="admin_list_03" href="/member/myOrderList/${memberInfo.memberId}">주문내역</a>                            
                      </lI>
                                                                                                         
                  </ul>
                </div>