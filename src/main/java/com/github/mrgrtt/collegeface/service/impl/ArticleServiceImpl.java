package com.github.mrgrtt.collegeface.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.mrgrtt.collegeface.domain.entity.Article;
import com.github.mrgrtt.collegeface.domain.entity.ArticleContent;
import com.github.mrgrtt.collegeface.mapper.ArticleContentMapper;
import com.github.mrgrtt.collegeface.mapper.ArticleMapper;
import com.github.mrgrtt.collegeface.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author haylen
 * @since 2020-10-12
 */
@Service
public class ArticleServiceImpl  implements IArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleContentMapper articleContentMapper;

    @Override
    public List<Article> getAll(int type, int start, int limit) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        String lastSql = " limit ";
        lastSql = lastSql + String.valueOf(limit) + " offset " + String.valueOf(start);
        queryWrapper.eq("type",type).last(lastSql);
        return articleMapper.selectList(queryWrapper);
    }

    @Override
    public String getContent(long id) {
        long articleId;
        Article article = articleMapper.selectById(id);
        if(article == null){
            return null;
        }else{
            articleId = article.getArticleContentId();
            ArticleContent articleContent = articleContentMapper.selectById(articleId);
            String s = null;
            if(articleContent != null){
                s = articleContent.getDetail();
            }
            return s;
        }
    }

    @Override
    public Long add(int type, String title, String content) {
            //先插入文章内容表,自动生成文章id
            LocalDateTime localDateTime = LocalDateTime.now();
            ArticleContent articleContent = new ArticleContent();
            articleContent.setCreateTime(localDateTime);
            articleContent.setUpdateTime(localDateTime);
            articleContent.setDetail(content);
            articleContentMapper.insert(articleContent);

            Long article_content_id = articleContent.getId();

            //插入文章表
            Article article = new Article();
            article.setArticleContentId(article_content_id);
            article.setCreateTime(localDateTime);
            article.setUpdateTime(localDateTime);
            article.setTitle(title);
            article.setType(type);
            articleMapper.insert(article);

            return article.getId();
    }

    @Override
    public void update(long id, int type, String title, String content) {
        LocalDateTime  updateTime = LocalDateTime.now();

        //更新文章表
        Article article = new Article();
        article.setId(id);
        article.setUpdateTime(updateTime);
        article.setType(type);
        article.setTitle(title);
        articleMapper.updateById(article);
        //更新文章内容表
        ArticleContent articleContent = new ArticleContent();
        article = articleMapper.selectById(id);
        articleContent.setId(article.getArticleContentId());
        articleContent.setUpdateTime(updateTime);
        articleContent.setDetail(content);
        articleContentMapper.updateById(articleContent);
    }

    @Override
    public void delete(long id) {
        Article article = articleMapper.selectById(id);
        Long article_id = article.getArticleContentId();
        articleContentMapper.deleteById(article_id);
        articleMapper.deleteById(id);
    }

    @Override
    public Article get(long id) {
        return articleMapper.selectById(id);
    }
}
