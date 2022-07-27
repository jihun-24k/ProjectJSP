<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.ll.exam.article.dto.ArticleDto" %>
<%@ include file="../common/head.jspf"%>

<%
List<ArticleDto> articles = (List<ArticleDto>)request.getAttribute("articles");
%>
<section>
    <div class="container px-3 mx-auto">
        <h1 class="font-bold text-lg">게시물 리스트</h1>

        <ul class ="mt-5">
             <% for ( ArticleDto article : articles ) { %>
             <li class ="flex">
                <a href="/usr/article/detail/free/<%=article.getId()%>" class="flex-grow w-[40px] hover:underline hover:text-[red]"><%=article.getId()%>. <%=article.getTitle()%></a>
                <a href="/usr/article/modify/free/<%=article.getId()%>" class=" hover:underline hover:text-[red] mr-2"><input type="button" value="수정" /></a>
                <a href="/usr/article/delete/free/<%=article.getId()%>" class="flex-grow hover:underline hover:text-[red]"><input type="button" value="삭제" /></a>
             </li>
             <% } %>
        </ul>
    </div>
</section>

<%@ include file="../common/foot.jspf"%>