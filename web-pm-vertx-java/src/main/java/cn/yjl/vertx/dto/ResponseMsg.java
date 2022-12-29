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
public class ResponseMsg {
    private String resCd;
    private String resMsg;

    public JsonObject toJson() {
        return new JsonObject().put("resCd", this.resCd).put("resMsg", this.resMsg);
    }
}
