use std::time::{SystemTime, UNIX_EPOCH};

use sea_orm::prelude::DateTime;

pub fn now() -> DateTime {
    let start = SystemTime::now();
    let since_the_epoch = start
        .duration_since(UNIX_EPOCH)
        .expect("Time went backwards");
    let mut ms = since_the_epoch.as_secs() as i64 * 1000i64 + (since_the_epoch.subsec_nanos() as f64 / 1_000_000.0) as i64;
    // 东8区校正
    ms += 3600 * 1000 * 8;
    DateTime::from_timestamp_millis(ms).unwrap()
}