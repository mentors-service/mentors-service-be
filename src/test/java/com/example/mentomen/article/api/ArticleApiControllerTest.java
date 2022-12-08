package com.example.mentomen.article.api;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("local")
@WebMvcTest(ArticleApiController.class)
@AutoConfigureMockMvc
public class ArticleApiControllerTest {

}
