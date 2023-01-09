package cn.yjl.vertx.entity

class AccountsEntity: AbstractEntity<AccountsEntity>() {

    var id: Long
        get() = this.getLong("id", 0L)
        set(value) {
            this.put("id", value)
        }

    var actIntroduction: String
        get() = this.getString("act_introduction", "")
        set(value) {
            this.put("act_introduction", value)
        }

    var actName: String
        get() = this.getString("act_name", "")
        set(value) {
            this.put("act_name", value)
        }

    var actNickName: String
        get() = this.getString("act_nick_name", "")
        set(value) {
            this.put("act_nick_name", value)
        }

    var actPwd: String
        get() = this.getString("act_pwd", "")
        set(value) {
            this.put("act_pwd", value)
        }

    var actRegisterDate: String?
        get() = this.getString("act_register_date", null)
        set(value) {
            this.put("act_register_date", value)
        }

    var actStatus: Int
        get() = this.getInteger("act_status", 0)
        set(value) {
            this.put("act_status", value)
        }

    var createdAt: String?
        get() = this.getString("created_at", null)
        set(value) {
            this.put("created_at", value)
        }

    var deletedAt: String?
        get() = this.getString("deleted_at", null)
        set(value) {
            this.put("deleted_at", value)
        }

    var updatedAt: String?
        get() = this.getString("updated_at", null)
        set(value) {
            this.put("updated_at", value)
        }
}
