package com.spjiang.article.service;


import com.spjiang.article.po.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Package: com.spjiang.aritcle.service
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2021-07-02 11:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;


     @Test
    public void testFindCommentList(){
         commentService.findCommentList();
     }

    @Test
     public void testDate() throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //格式化为日期/时间字符串
        Date ccc = sdf.parse("2021-07-02 15:48:40");
        System.out.println(ccc);
     }

    @Test
    public void testSave(){
        Comment comment = new Comment();
        comment.setArticleid("1");
        comment.setContent("content");
        comment.setCreatedatetime(LocalDateTime.now());
        comment.setLikenum(1);
        comment.setNickename("spjiang2");
        comment.setState("1");
        comment.setPublishtime(new Date());
        comment.setParentid("3");
        commentService.saveComment(comment);
    }

    @Test
    public void testFindCommentListByParentid(){
         Page<Comment> page = commentService.findCommentListByParentid("2222",0,2);
         System.out.println(page.getTotalElements());
         System.out.println(page.getContent());
    }
}
