import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.util.Ut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @BeforeEach
    void beforeEach(){
        ObjectMapper om = new ObjectMapper();
    }

    @Test
    void assertThatTest(){
        int rs = 1 + 2;
        assertThat(rs).isEqualTo(3);
    }

    @Test
    void objectMapper_objToJsonTest(){
        ArticleDto articleDto = new ArticleDto(1,"제목","내용");
        String jsonStr = Ut.json.toStr(articleDto, "");
        assertThat(jsonStr).isNotBlank();
        assertThat(jsonStr).isEqualTo("""
                 {"id":1,"title":"제목","body":"내용"}
                 """.trim());
    }

    @Test
    void objectMapper_JsonStrToObjTest(){
        ArticleDto articleDto = new ArticleDto(1,"제목","내용");
        String jsonStr = Ut.json.toStr(articleDto, "");

        ArticleDto articleDtoFromJson = Ut.json.toObj(jsonStr, ArticleDto.class, null);

        assertThat(articleDto).isEqualTo(articleDtoFromJson);
    }

    @Test
    void objectMapper_listToJsonStrTest(){
        List<ArticleDto> articleDtoList = new ArrayList<>();
        articleDtoList.add(new ArticleDto(1,"제목1","내용1"));
        articleDtoList.add(new ArticleDto(2,"제목2","내용2"));

        String jsonStr = Ut.json.toStr(articleDtoList, "");
        assertThat("""
                [{"id":1,"title":"제목1","body":"내용1"},{"id":2,"title":"제목2","body":"내용2"}]
                """.trim()).isEqualTo(jsonStr);
    }
    @Test
    void objectMapper_mapToJsonStrTest(){
        Map<String, ArticleDto> articleDtoMap = new HashMap<>();
        articleDtoMap.put("old",new ArticleDto(1,"제목1","내용1"));
        articleDtoMap.put("young",new ArticleDto(2,"제목2","내용2"));

        String jsonStr = Ut.json.toStr(articleDtoMap,"");
        assertThat("""
                {"young":{"id":2,"title":"제목2","body":"내용2"},"old":{"id":1,"title":"제목1","body":"내용1"}}
                """.trim()).isEqualTo(jsonStr);
    }

    @Test
    void objectMapper_jsonStrToArticleDtoList(){
        List<ArticleDto> articleDtoList = new ArrayList<>();
        articleDtoList.add(new ArticleDto(1,"제목1","내용1"));
        articleDtoList.add(new ArticleDto(2,"제목2","내용2"));

        String jsonStr = Ut.json.toStr(articleDtoList, "");

        List<ArticleDto> articleDtosFromJson = Ut.json.toObj(jsonStr, new TypeReference<>() {
        }, null);

        assertThat(articleDtoList).isEqualTo(articleDtosFromJson);
    }

    @Test
    void objectMapper_jsonStrToArticleDtoMap(){
        Map<String, ArticleDto> articleDtoMap = new HashMap<>();
        articleDtoMap.put("old",new ArticleDto(1,"제목1","내용1"));
        articleDtoMap.put("young",new ArticleDto(2,"제목2","내용2"));

        String jsonStr = Ut.json.toStr(articleDtoMap,"");

        Map<String, ArticleDto> articleDtosFromJson = Ut.json.toObj(jsonStr, new TypeReference<>() {
        }, null);

        assertThat(articleDtoMap).isEqualTo(articleDtosFromJson);
    }

    @Test
    void mapOfTest(){
        Map<String, Object> map = Ut.mapOf("age", 11, "name", "Paul");

        assertThat(map.get("age")).isEqualTo(11);
        assertThat(map.get("name")).isEqualTo("Paul");
    }
}
