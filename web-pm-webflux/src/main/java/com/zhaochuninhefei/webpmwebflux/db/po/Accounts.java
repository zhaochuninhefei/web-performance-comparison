package com.zhaochuninhefei.webpmwebflux.db.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

/**
 * @author zhaochun
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Accounts {
    @Id
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
