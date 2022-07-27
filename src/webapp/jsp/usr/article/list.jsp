<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ include file="../common/head.jspf"%>

<section>
    <div class="container px-10 mx-auto">
        <h1 class="font-bold text-lg">게시물 리스트</h1>

        <ul class="mt-5">
                    <c:forEach items="${articles}" var="article">
                    <li class="flex">
                        <a class="w-[40px] hover:underline hover:text-[red]" href="/usr/article/detail/free/${article.id}">${article.id}</a>
                        <!-- flex-grow : 성장성 1 -->
                        <a class="flex-grow hover:underline hover:text-[red]" href="/usr/article/detail/free/${article.title}">${article.title}</a>
                        <button class="hover:bg-[blue] bg-[#F8F8F8] p-[8px_15px_5px_15px] rounded-[25px] hover:text-white"><a href="/usr/article/modify/free/${article.id}">수정</a></button>
                        <button class="hover:bg-[red] bg-[#F8F8F8] p-[8px_15px_5px_15px] rounded-[25px] hover:text-white"><a onclick="if ( !confirm('정말로 삭제하시겠습니까?') ) return false;" href="/usr/article/delete/free/${article.id}">삭제</a></button>
                    </li>
                    </c:forEach>
        </ul>
    </div>
</section>

<%@ include file="../common/foot.jspf"%>