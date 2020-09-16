package com.letterball.Service.impl;

import com.letterball.common.Constants;
import com.letterball.entity.Article;
import com.letterball.mapper.ArticleMapper;
import com.letterball.Service.ArticleSerivce;
import com.letterball.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleSerivce {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void examine(ArticleVO articleVO) {
        HashMap<String, Object> requestParams = new HashMap<>();
        if (StringUtils.isEmpty(articleVO.getId())) {
            requestParams.put(Constants.SERCH_DATA_ID, articleVO.getId());
        }
        requestParams.put(Constants.SEARCH_STATE,articleVO.getState());
        //判断前端点赞是否传值过来 如果有值 sql+1
        if (StringUtils.isEmpty(articleVO.getThumbup())) {
            requestParams.put(Constants.SEARCH_THUMBUP,articleVO.getThumbup() + 1 );
        }
        articleMapper.examine(requestParams);
    }

    /**
     * 查询文章
     * @param articleVO
     * @return
     */
    @Override
    public List<Article> findArticle(ArticleVO articleVO) {
        HashMap<String, Object> requestParmas = new HashMap<>();
        return articleMapper.findArticle(requestParmas);
    }
}
