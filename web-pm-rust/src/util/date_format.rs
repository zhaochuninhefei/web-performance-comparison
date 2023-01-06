use sea_orm::prelude::*;
use serde::{self, Deserialize, Deserializer, Serializer};

const FORMAT: &'static str = "%Y-%m-%d %H:%M:%S";

pub fn serialize<S>(
    date: &DateTime,
    serializer: S,
) -> Result<S::Ok, S::Error>
    where
        S: Serializer,
{
    let s = format!("{}", date.format(FORMAT));
    serializer.serialize_str(&s)
}

pub fn deserialize<'de, D>(
    deserializer: D,
) -> Result<DateTime, D::Error>
    where
        D: Deserializer<'de>,
{
    let s = String::deserialize(deserializer)?;
    DateTime::parse_from_str(&s, FORMAT).map_err(serde::de::Error::custom)
    // Utc.datetime_from_str(&s, FORMAT).map_err(serde::de::Error::custom)
}
