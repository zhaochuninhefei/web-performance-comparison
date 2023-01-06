use actix_web::{get, HttpResponse, post, Responder, Result, web::{self}};
use phf::{Map, phf_map};
use serde::Deserialize;

use crate::dto::asset::{Asset, AssetRequest};
use crate::dto::response_msg::ResponseMsg;

#[derive(Clone, Debug, Deserialize)]
pub struct QueryRequest {
    id: String,
}

const ast1: Asset = Asset {
    id: 1,
    name: "asset001",
    desc: "测试资产001",
};

const ast2: Asset = Asset {
    id: 2,
    name: "asset002",
    desc: "测试资产02",
};

const astMp: Map<&str, Asset> = phf_map! {
    "1" => ast1,
    "2" => ast2
};

const assets: &[Asset] = &[ast1, ast2];

#[get("/asset/list")]
pub async fn list_asset() -> Result<impl Responder> {
    Ok(HttpResponse::Ok().json(assets))
}

#[get("/asset/query")]
pub async fn query_asset(web::Query(request): web::Query<QueryRequest>) -> Result<impl Responder> {
    let ast = match astMp.get(request.id.as_str()) {
        Some(data) => data.clone(),
        None => Asset {
            id: 0,
            name: "",
            desc: "",
        }
    };
    Ok(HttpResponse::Ok().json(ast))
}

#[post("/asset/modify")]
pub async fn modify_asset(web::Json(asset): web::Json<AssetRequest>) -> Result<impl Responder> {
    println!("修改目标：{}", asset);
    Ok(HttpResponse::Ok().json(ResponseMsg { resCd: "1".to_string(), resMsg: format!("{}", asset) }))
}