use std::fmt::{Display, Formatter};
use serde::{Deserialize, Serialize};

#[derive(Default, Clone, Debug, Serialize, Deserialize)]
#[serde(rename_all = "camelCase", default)]
pub struct Asset<'a> {
    pub id: i32,
    pub name: &'a str,
    pub desc: &'a str,
}

#[derive(Default, Clone, Debug, Serialize, Deserialize)]
#[serde(rename_all = "camelCase", default)]
pub struct AssetRequest {
    pub id: i32,
    pub name: String,
    pub desc: String,
}

impl Display for AssetRequest {
    fn fmt(&self, f: &mut Formatter<'_>) -> std::fmt::Result {
        write!(f, "{{id: {}, name: \"{}\", desc: \"{}\"}}", self.id, self.name, self.desc)
    }
}