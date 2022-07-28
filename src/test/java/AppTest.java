import com.ll.exam.article.dto.ArticleDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    void JUnitTest(){
        int rs = 1 + 2;
        assertEquals(rs,3);
    }

    @Test
    void ObjectMapperTest(){
        ArticleDto articleDto = new ArticleDto(1,"제목","내용");
        String jsonStr = Ut.json.toJsonStr(articleDto, "");
        assertThat(jsonStr).isNotBlank();
        assertThat(jsonStr).isEqualTo("""
                 {"id":1,"title":"제목","body":"내용"}
                 """.trim());
    }
}
