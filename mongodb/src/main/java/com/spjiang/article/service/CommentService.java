package com.spjiang.article.service;

import com.spjiang.article.dao.CommentRepository;
import com.spjiang.article.po.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package: com.spjiang.article.service
 * 评论业务层
 *
 * @description: 评论业务层
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2021-07-02 11:31
 */
@Service
public class CommentService {

    /**
     * 注入dao
     */
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存一个评论
     * @param comment 对象
     */
    public void saveComment(Comment comment){
        // 如果需要自定义主键，可以在这里指定主键。如果不指定主键，MongoDB会自动生成主键
        // 设置一些默认值
        // 调用dao
        commentRepository.save(comment);
    }

    /**
     * 更新评论
     *
     * @param comment 对象
     */
    public void updateComment(Comment comment){
        // 调用dao
        commentRepository.save(comment);
    }

    /**
     *根据id删除评论
     *
     * @param id 主键ID
     */
    public void deleteCommentById(String id){
        commentRepository.deleteById(id);
    }

    /**
     * 查询所有评论
     * @return Comment
     */
    public List<Comment> findCommentList(){
        // 调用dao
        return commentRepository.findAll();
    }

    /**
     * 根据ID查询评论
     * @param id
     * @return
     */
    public Comment findCommentById(String id){
        return commentRepository.findById(id).get();
    }

    /**
     * 分页查询
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    public Page<Comment> findCommentListByParentid(String parentid,int page,int size){
        return commentRepository.findByParentid(parentid, PageRequest.of(page,size));
    }

    /**
     * 更新
     * @param id
     */
    public void updateCommentLikenum(String id){
        // 查询条件
        Query query = Query.query(Criteria.where("_id").is(id));
        // 更新条件
        Update update = new Update();
        update.inc("likenum");
        mongoTemplate.updateFirst(query,update,Comment.class);
    }
}
