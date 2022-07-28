import com.fasterxml.jackson.databind.ObjectMapper;
import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.util.Ut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void objectMapperTest(){
        ArticleDto articleDto = new ArticleDto(1,"제목","내용");
        String jsonStr = Ut.json.toJsonStr(articleDto, "");
        assertThat(jsonStr).isNotBlank();
        assertThat(jsonStr).isEqualTo("""
                 {"id":1,"title":"제목","body":"내용"}
                 """.trim());
    }
}
