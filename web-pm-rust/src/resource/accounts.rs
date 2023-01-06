use actix_web::{get, HttpResponse, post, Responder, Result, web::{self}};
use serde::Deserialize;

use crate::AppState;
use crate::dto::response_msg::ResponseMsg;
use crate::entity::accounts::Model as Account;
use crate::util::app_util;

#[derive(Clone, Debug, Deserialize)]
pub struct QueryRequest {
    id: i32,
}

#[get("/account/list")]
pub async fn list_accounts(data: web::Data<AppState>) -> Result<impl Responder> {
    let db = &data.db;
    let accounts: Vec<Account> = Account::find_all(db).await;
    Ok(HttpResponse::Ok().json(accounts))
}

#[get("/account/query")]
pub async fn query_account(data: web::Data<AppState>, web::Query(request): web::Query<QueryRequest>) -> Result<impl Responder> {
    let db = &data.db;
    let account: Account = Account::find_by_id(db, request.id).await.unwrap_or_default();
    Ok(HttpResponse::Ok().json(account))
}

#[post("/account/add")]
pub async fn add_account(data: web::Data<AppState>, web::Json(mut request): web::Json<Account>) -> Result<impl Responder> {
    let db = &data.db;
    request.act_register_date = Some(app_util::now());
    let id = Account::save(db, request).await;
    let result = ResponseMsg { res_cd: "1".to_string(), res_msg: format!("新增账户ID:{}", id) };
    Ok(HttpResponse::Ok().json(result))
}