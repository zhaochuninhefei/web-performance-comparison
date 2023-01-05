use std::error::Error;

use actix_web::{
    error, get, post,
    web::{self},
    App, HttpRequest, HttpResponse, HttpServer, Responder, Result
};
use sea_orm::entity::prelude::*;
use sea_orm::sea_query::Mode;
use sea_orm::{Database, DatabaseConnection};
use serde::{Deserialize, Serialize};

#[derive(Clone, Debug, PartialEq, DeriveEntityModel, Serialize, Deserialize)]
#[sea_orm(table_name = "accounts")]
pub struct Model {
    #[sea_orm(primary_key)]
    pub id: i32,
    pub act_introduction: Option<String>,
    pub act_name: Option<String>,
    pub act_nick_name: Option<String>,
    pub act_pwd: Option<String>,
    pub act_register_date: Option<DateTime>,
    pub act_status: Option<i8>,
    pub created_at: Option<DateTime>,
    pub deleted_at: Option<DateTime>,
    pub updated_at: Option<DateTime>,
}

#[derive(Copy, Clone, Debug, EnumIter, DeriveRelation)]
pub enum Relation {}

impl ActiveModelBehavior for ActiveModel {}

#[get("/account/list")]
async fn list_accounts() -> Result<impl Responder> {
    let db: DatabaseConnection =
        Database::connect("mysql://root:yinjiaolong@localhost:3306/quarkus_test")
            .await?;
    let accounts: Vec<Model> = Entity::find().all(&db).await?;
    // HttpResponse::Ok().json(accounts)
    return Ok(HttpResponse::Ok().json(accounts))
}

#[actix_web::main]
async fn main() {
    let address = "0.0.0.0:8082";
    HttpServer::new(|| App::new().service(list_accounts))
        .bind(address)
        .unwrap()
        .run()
        .await
        .unwrap();
}
