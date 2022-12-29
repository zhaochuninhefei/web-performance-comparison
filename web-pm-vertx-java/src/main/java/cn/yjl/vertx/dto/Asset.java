package cn.yjl.vertx.dto;

import io.vertx.core.json.JsonObject;
import lombok.*;

/**
 * @author jiaolongyin
 * @description: TODO
 * @date 2022/12/28 10:55
 */
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Asset {
    private int id;
    private String name;
    private String desc;

    public JsonObject toJson() {
        return new JsonObject().put("id", this.id).put("name", this.name).put("desc", this.desc);
    }
}
