package com.spjiang.article.dao;

import com.spjiang.article.po.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Package: com.spjiang.article.dao
 *
 * @description:
 * @author: jiangshengping <spjiang@aliyun.com>
 * @create: 2021-07-02 11:28
 */
public interface CommentRepository extends MongoRepository<Comment,String> {
    Page<Comment> findByParentid(String parentid, Pageable pageable);
}
