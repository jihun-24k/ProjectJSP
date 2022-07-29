<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../common/head.jspf"%>

<script>
let Articles__lastId = 0;
function Articles__loadMore() {
    fetch(`/usr/article/getArticles/free?fromId=\${Articles__lastId}`)
        .then(data => data.json())
        .then(responseData => {
            const articles = responseData.datum;
            for ( const key in articles ) {
                const article = articles[key];
                const html = `
                    <li class = "flex">
                    <a class="w-[40px] hover:underline hover:text-[red]" href="/usr/article/detail/free/\${article.id}">\${article.id}</a>
                    <a class="flex-grow hover:underline hover:text-[red]" href="/usr/article/detail/free/\${article.id}">\${article.title}</a>
                    <button class="hover:bg-[blue] bg-[#F8F8F8] p-[8px_15px_5px_15px] rounded-[25px] hover:text-white"><a href="/usr/article/modify/free/\${article.id}">수정</a></button>
                    <button class="hover:bg-[red] bg-[#F8F8F8] p-[8px_15px_5px_15px] rounded-[25px] hover:text-white"><a onclick="if ( !confirm('정말로 삭제하시겠습니까?') ) return false;" href="/usr/article/delete/free/\${article.id}">삭제</a></button>
                    </li>
                `;

                $('.articles').append(html);
            }
            if (articles.length > 0){
                Articles__lastId = articles[articles.length - 1].id;
            }
            //Articles__loadMore(); // 즉시 실행
            setTimeout(Articles__loadMore, 3000); // Articles__loadMore(); 를 3초 뒤에 수행
        });
}
</script>

<section>
    <div class="container px-3 mx-auto">
        <h1 class="font-bold text-lg">게시물 리스트</h1>

        <ul class="articles mt-5">
            <!-- 이 부분에 자바스크립트를 통해서 HTML을 채우겠습니다. -->
        </ul>
    </div>
</section>

<script>
Articles__loadMore();
</script>

<%@ include file="../common/foot.jspf"%>