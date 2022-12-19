package com.example.mentomen.member.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mentomen.article.service.ArticleService;
import com.example.mentomen.article.vo.ArticleListVO;
import com.example.mentomen.article.vo.ArticleVO;
import com.example.mentomen.comment.service.CommentService;
import com.example.mentomen.comment.vo.CommentVO;
import com.example.mentomen.member.config.auth.PrincipalDetails;
import com.example.mentomen.member.dto.UserDto;
import com.example.mentomen.member.service.UserService;
import com.example.mentomen.member.vo.MyUserVO;
import com.example.mentomen.member.vo.OtherUserVO;
import com.example.mentomen.scrap.service.ScrapService;

@RestController
@RequestMapping(value = "/api/users")
public class UserApiController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ScrapService scrapService;
    @Autowired
    private CommentService commentService;

    @CrossOrigin(origins = "*")
    @GetMapping
    public MyUserVO getMyInfo(Authentication authentication,
            @RequestParam(name = "offset") Integer offset,
            @RequestParam(name = "searchObj", required = false) String searchObj,
            @RequestParam(name = "searchVal", required = false) String searchVal) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        UserDto userDto = userService.findByEmail(principal.getUser().getEmail());
        ArticleListVO articles = articleService.articlesByUserId(offset, searchObj, searchVal,
                principal.getUser().getId());
        List<CommentVO> comments = commentService.getCommentsByUserId(principal.getUser().getId());
        List<ArticleVO> scraps = scrapService.getScrapArticlesByUserId(principal.getUser().getId());
        return MyUserVO.builder().articles(articles).comments(comments).scraps(scraps).user(userDto).build();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public OtherUserVO getOtherInfo(@PathVariable Long id,
            @RequestParam(name = "offset") Integer offset,
            @RequestParam(name = "searchObj", required = false) String searchObj,
            @RequestParam(name = "searchVal", required = false) String searchVal) {
        UserDto user = userService.findById(id);
        ArticleListVO articles = articleService.articlesByUserId(offset, searchObj, searchVal, id);
        return OtherUserVO.builder().user(user).articles(articles).build();
    }

    @CrossOrigin(origins = "*")
    @PatchMapping
    public void update(@Valid @RequestBody UserDto userDto, Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        userService.update(principal.getUser().getEmail(), userDto);
    }

}
