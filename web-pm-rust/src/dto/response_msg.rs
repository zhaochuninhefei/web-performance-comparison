use serde::{Deserialize, Serialize};

#[derive(Default, Clone, Debug, Serialize, Deserialize)]
#[serde(rename_all = "camelCase", default)]
pub struct ResponseMsg {
    pub res_cd: String,
    pub res_msg: String,
}