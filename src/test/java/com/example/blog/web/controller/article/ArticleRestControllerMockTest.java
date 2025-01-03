package com.example.blog.web.controller.article;

import com.example.blog.config.ObjectMapperConfig;
import com.example.blog.config.SecurityConfig;
import com.example.blog.service.article.ArticleEntity;
import com.example.blog.service.article.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
@Import({ObjectMapperConfig.class, SecurityConfig.class})
class ArticleRestControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService mockArticleService;

    @MockBean
    private UserDetailsService MockUserDetailsService;

    @Test
    void mockMvc(){
        assertThat(mockMvc).isNotNull();
    }

    @Test
    @DisplayName("/articles/{id}: 指定されたIDの記事が存在する場合、200 OKを返す")
    void getArticleById_200OK() throws Exception {
        var expected = new ArticleEntity(
                999,
                "999",
                "999",
                LocalDateTime.of(2022, 1,2,3,4,5),
                LocalDateTime.of(2023, 1,2,3,4,5)
        );

        when(mockArticleService.findById(expected.id())).thenReturn(Optional.of(expected));

        var actual = mockMvc.perform(get("/articles/{id}", expected.id()));
        actual.andExpect(status().isOk());
    }
}