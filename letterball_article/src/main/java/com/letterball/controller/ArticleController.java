package com.letterball.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.letterball.common.BaseService;
import com.letterball.common.Constants;
import com.letterball.entity.Article;
import com.letterball.entity.ResponseBase;
import com.letterball.service.ArticleSerivce;
import com.letterball.utils.RedisUtils;
import com.letterball.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController extends BaseService {

    @Autowired
    private ArticleSerivce articleSerivce;

    @Resource
    private RedisUtils redisUtils;

    /**
     * 文章审核 点赞
     */
    @RequestMapping("/examine")
    public ResponseBase examine(ArticleVO articleVO){
        try {
            articleSerivce.examine(articleVO);
        }catch (Exception e){
            return setResultError(Constants.EXAMINE_ERROR);
        }
        return setResultError(Constants.EXAMINE_SUCCESS);
    }

    /**
     * 查询文章列表
     * @param articleVO
     * @return
     */
    @RequestMapping("/findArticle")
    public ResponseBase findArticle(ArticleVO articleVO){
        HashMap<String, Object> resultMap = new HashMap<>();
        long total = Constants.COMM_DATA_ZERO;


        PageHelper.startPage(articleVO.getPage(),articleVO.getLimit());
        List<Article> findSearchList = articleSerivce.findArticle(articleVO);
        total = ((Page<Article>)findSearchList).getTotal();

        resultMap.put(Constants.COMM_QUERY_RESP_ITEM,findSearchList);
        resultMap.put(Constants.COMM_QUERY_RESP_TOTAL,total);
        return setResultSuccessData(findSearchList);
    }
}
