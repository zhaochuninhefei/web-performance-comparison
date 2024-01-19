package com.zhaochuninhefei.webpmsbtvt.db.dao;

import com.zhaochuninhefei.webpmsbtvt.db.po.Accounts;
import com.zhaochuninhefei.webpmsbtvt.db.po.AccountsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountsMapper {
    /**
     * 根据Example条件查询件数.
     *
     * @param example AccountsExample
     * @return long
     */
    long countByExample(AccountsExample example);

    /**
     * 根据Example条件删除数据.
     *
     * @param example AccountsExample
     * @return int
     */
    int deleteByExample(AccountsExample example);

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
     * @param row Accounts
     * @return int
     */
    int insert(Accounts row);

    /**
     * 插入数据（仅仅插入已设值项目）.
     *
     * @param row Accounts
     * @return int
     */
    int insertSelective(Accounts row);

    /**
     * 根据Example条件查询数据.
     *
     * @param example AccountsExample
     * @return java.util.List<com.zhaochuninhefei.webpmjava.db.po.Accounts>
     */
    List<Accounts> selectByExample(AccountsExample example);

    /**
     * 根据主键获取一条数据.
     *
     * @param id Long
     * @return com.zhaochuninhefei.webpmjava.db.po.Accounts
     */
    Accounts selectByPrimaryKey(Long id);

    /**
     * 根据Example条件来更新数据（仅仅更新已设值项目）.
     *
     * @param row Accounts
     * @param example AccountsExample
     * @return int
     */
    int updateByExampleSelective(@Param("row") Accounts row, @Param("example") AccountsExample example);

    /**
     * 根据Example条件来更新数据.
     *
     * @param row Accounts
     * @param example AccountsExample
     * @return int
     */
    int updateByExample(@Param("row") Accounts row, @Param("example") AccountsExample example);

    /**
     * 根据主键更新数据（仅仅更新已设值项目）.
     *
     * @param row Accounts
     * @return int
     */
    int updateByPrimaryKeySelective(Accounts row);

    /**
     * 根据主键来更新数据库记录.
     *
     * @param row Accounts
     * @return int
     */
    int updateByPrimaryKey(Accounts row);
}