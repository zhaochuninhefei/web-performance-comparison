//******************************************************************************
// 版权所有(c)，安徽科大国创软件科技有限公司，保留所有权利。
//******************************************************************************

package com.zhaochuninhefei.webpmjava.db.po;

import java.time.LocalDateTime;

/**
 * Database Table Remarks:
 *   帐号表
 *
 * 本类自动生成，对应的表为[accounts].
 */
@lombok.Getter
@lombok.Setter
@lombok.ToString
public class Accounts {
    /**
     *  (accounts.id).
     */
    private Long id;

    /**
     *  (accounts.created_at).
     */
    private LocalDateTime createdAt;

    /**
     *  (accounts.updated_at).
     */
    private LocalDateTime updatedAt;

    /**
     *  (accounts.deleted_at).
     */
    private LocalDateTime deletedAt;

    /**
     * 帐户名 (accounts.act_name).
     */
    private String actName;

    /**
     * 帐户密码 (accounts.act_pwd).
     */
    private String actPwd;

    /**
     * 帐户昵称 (accounts.act_nick_name).
     */
    private String actNickName;

    /**
     * 帐户介绍 (accounts.act_introduction).
     */
    private String actIntroduction;

    /**
     * 帐户状态 (accounts.act_status).
     */
    private Byte actStatus;

    /**
     * 帐户注册时间 (accounts.act_register_date).
     */
    private LocalDateTime actRegisterDate;
}