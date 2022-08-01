<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../common/head.jspf"%>

<script>
let Rooms__lastId = 0;
function Rooms__loadMore() {
    fetch(`/usr/chat/getRooms?fromId=\${Rooms__lastId}`)
        .then(data => data.json())
        .then(responseData => {
            const rooms = responseData.datum;
            for ( const key in rooms ) {
                const room = rooms[key];
                const html = `
                    <li class = "flex">
                    <a class="w-[40px] hover:underline hover:text-[red]" href="/usr/chat/room/\${room.id}">\${room.id}</a>
                    <a class="flex-grow hover:underline hover:text-[red]" href="/usr/chat/room/\${room.id}">\${room.title}</a>
                    <button class="hover:bg-[blue] bg-[#F8F8F8] p-[8px_15px_5px_15px] rounded-[25px] hover:text-white"><a href="/usr/chat/modifyRoom/\${room.id}">수정</a></button>
                    <button class="hover:bg-[red] bg-[#F8F8F8] p-[8px_15px_5px_15px] rounded-[25px] hover:text-white"><a onclick="if ( !confirm('정말로 삭제하시겠습니까?') ) return false;" href="/usr/chat/deleteRoom/\${room.id}">삭제</a></button>
                    </li>
                `;

                $('.rooms').append(html);
            }
            if (rooms.length > 0){
                Rooms__lastId = rooms[rooms.length - 1].id;
            }
            setTimeout(Rooms__loadMore, 3000); // Rooms__loadMore(); 를 3초 뒤에 수행
        });
}
</script>

<section>
    <div class="container px-3 mx-auto">
        <h1 class="font-bold text-lg">채팅방 리스트</h1>

        <ul class="rooms mt-5">
            <!-- 이 부분에 자바스크립트를 통해서 HTML을 채우겠습니다. -->
        </ul>
    </div>
</section>

<script>
Rooms__loadMore();
</script>

<%@ include file="../common/foot.jspf"%>