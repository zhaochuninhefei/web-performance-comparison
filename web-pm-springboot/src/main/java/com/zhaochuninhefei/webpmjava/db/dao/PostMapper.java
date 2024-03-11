//******************************************************************************
// 版权所有(c)，科大国创，保留所有权利。
//******************************************************************************

package com.zhaochuninhefei.webpmjava.db.dao;

import com.zhaochuninhefei.webpmjava.db.po.Post;
import com.zhaochuninhefei.webpmjava.db.po.PostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PostMapper {
    /**
     * 根据Example条件查询件数.
     *
     * @param example PostExample
     * @return long
     */
    long countByExample(PostExample example);

    /**
     * 根据Example条件删除数据.
     *
     * @param example PostExample
     * @return int
     */
    int deleteByExample(PostExample example);

    /**
     * 根据主键删除数据.
     *
     * @param id Long
     * @return int
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据.
     *
     * @param row Post
     * @return int
     */
    int insert(Post row);

    /**
     * 插入数据（仅仅插入已设值项目）.
     *
     * @param row Post
     * @return int
     */
    int insertSelective(Post row);

    /**
     * 根据Example条件查询数据.
     *
     * @param example PostExample
     * @return java.util.List<com.zhaochuninhefei.webpmjava.db.po.Post>
     */
    List<Post> selectByExample(PostExample example);

    /**
     * 根据主键获取一条数据.
     *
     * @param id Long
     * @return com.zhaochuninhefei.webpmjava.db.po.Post
     */
    Post selectByPrimaryKey(Long id);

    /**
     * 根据Example条件来更新数据（仅仅更新已设值项目）.
     *
     * @param row Post
     * @param example PostExample
     * @return int
     */
    int updateByExampleSelective(@Param("row") Post row, @Param("example") PostExample example);

    /**
     * 根据Example条件来更新数据.
     *
     * @param row Post
     * @param example PostExample
     * @return int
     */
    int updateByExample(@Param("row") Post row, @Param("example") PostExample example);

    /**
     * 根据主键更新数据（仅仅更新已设值项目）.
     *
     * @param row Post
     * @return int
     */
    int updateByPrimaryKeySelective(Post row);

    /**
     * 根据主键来更新数据库记录.
     *
     * @param row Post
     * @return int
     */
    int updateByPrimaryKey(Post row);

    int updateTest(@Param("row") Post row, @Param("example") PostExample example);
}