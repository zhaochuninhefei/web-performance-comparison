use std::env;
use std::fmt::Debug;

use actix_web::{App, HttpServer, web};
use sea_orm::{ConnectOptions, Database, DatabaseConnection};

use resource::accounts;

use crate::resource::asset;

mod entity;
mod resource;
mod util;
mod dto;

#[derive(Debug, Clone)]
pub struct AppState {
    db: DatabaseConnection,
}

fn get_env_u32(key: &str, default_value: u32) -> u32 {
    return match env::var_os(key) {
        Some(value) => {
            let temp = value.into_string().expect(format!("{} in .env file can not parse to String", key).as_str());
            temp.parse().expect(format!("{} in .env file can not parse to specified type", key).as_str())
        }
        None => default_value
    };
}

#[actix_web::main]
async fn main() -> std::io::Result<()> {
    dotenvy::dotenv().ok();
    let db_url = env::var("db_url").expect("db_url is not set in .env file");
    let max_connections = get_env_u32("max_connections", 5);
    let min_connections = get_env_u32("min_connections", 5);
    let mut opt = ConnectOptions::new(db_url);
    opt.max_connections(max_connections)
        .min_connections(min_connections);
    let db = Database::connect(opt).await.unwrap();
    let app_state = AppState { db };
    let port = get_env_u32("port", 8080);
    let address = format!("0.0.0.0:{}", port);
    // let mut server = HttpServer::new(move || {
    //     App::new()
    //         .service(list_accounts)
    //         .app_data(web::Data::new(app_state.clone()))
    //         .default_service(web::route().to(not_found)))
    // });
    HttpServer::new(move || App::new()
        .app_data(web::Data::new(app_state.clone()))
        .service(accounts::list_accounts)
        .service(accounts::add_account)
        .service(accounts::query_account)
        .service(asset::list_asset)
        .service(asset::query_asset)
        .service(asset::modify_asset))
        .bind(address)?
        .run()
        .await?;

    Ok(())
}
