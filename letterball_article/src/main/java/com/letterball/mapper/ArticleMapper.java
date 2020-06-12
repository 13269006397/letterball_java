package com.letterball.mapper;

import com.letterball.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface ArticleMapper {

    void examine(HashMap<String,Object> map);

    List<Article> findArticle(HashMap<String,Object> map);

    int deleteByPrimaryKey(String id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
}