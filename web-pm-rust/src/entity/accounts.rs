use sea_orm::{ActiveModelBehavior, IntoActiveModel};
use sea_orm::entity::prelude::*;
use serde::{Deserialize, Serialize};

use crate::util::option_date_format;

#[derive(Default, Clone, Debug, PartialEq, DeriveEntityModel, Serialize, Deserialize)]
#[sea_orm(table_name = "accounts")]
#[serde(rename_all = "camelCase", default)]
pub struct Model {
    #[sea_orm(primary_key)]
    pub id: i32,
    pub act_introduction: Option<String>,
    pub act_name: String,
    pub act_nick_name: Option<String>,
    pub act_pwd: String,
    #[serde(with = "option_date_format")]
    pub act_register_date: Option<DateTime>,
    pub act_status: Option<i8>,
    #[serde(with = "option_date_format")]
    pub created_at: Option<DateTime>,
    #[serde(with = "option_date_format")]
    pub deleted_at: Option<DateTime>,
    #[serde(with = "option_date_format")]
    pub updated_at: Option<DateTime>,
}

#[derive(Copy, Clone, Debug, EnumIter, DeriveRelation)]
pub enum Relation {}

impl ActiveModelBehavior for ActiveModel {}

impl Model {
    pub async fn find_all(db: &DbConn) -> Vec<Model> {
        Entity::find().all(db).await.unwrap()
    }

    pub async fn find_by_id(db: &DbConn, id: i32) -> Option<Model> {
        Entity::find_by_id(id).one(db).await.unwrap()
    }

    pub async fn save(db: &DbConn, account: Model) -> i32 {
        let insert_account: ActiveModel = account.into_active_model();
        let insert_result = Entity::insert(insert_account).exec(db).await.unwrap();
        insert_result.last_insert_id
    }
}