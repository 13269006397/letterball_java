package com.letterball.Service;

import com.letterball.entity.Article;
import com.letterball.vo.ArticleVO;

import java.util.List;

public interface ArticleSerivce {

    void examine(ArticleVO articleVO);

    List<Article> findArticle(ArticleVO articleVO);

}
