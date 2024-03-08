//******************************************************************************
// 版权所有(c)，科大国创，保留所有权利。
//******************************************************************************

package com.zhaochuninhefei.webpmjava.db.po;

/**
 * Database Table Remarks:
 *   发帖表
 *
 * 本类自动生成，对应的表为[post].
 */
@lombok.Getter
@lombok.Setter
@lombok.ToString
public class Post {
    /**
     *  (post.id).
     */
    private Long id;

    /**
     * 所属账户ID (post.act_id).
     */
    private Long actId;

    /**
     * 帖子标题 (post.title).
     */
    private String title;

    /**
     * 帖子内容 (post.content).
     */
    private String content;
}