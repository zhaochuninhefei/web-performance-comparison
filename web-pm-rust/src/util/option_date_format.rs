use sea_orm::prelude::*;
use serde::{self, Deserialize, Deserializer, Serializer};

const FORMAT: &'static str = "%Y-%m-%d %H:%M:%S";

pub fn serialize<S>(
    date: &Option<DateTime>,
    serializer: S,
) -> Result<S::Ok, S::Error>
    where
        S: Serializer,
{
    match date {
        Some(value) => serializer.serialize_str(format!("{}", value.format(FORMAT)).as_str()),
        None => serializer.serialize_none()
    }
}

pub fn deserialize<'de, D>(
    deserializer: D,
) -> Result<Option<DateTime>, D::Error>
    where
        D: Deserializer<'de>,
{
    let binding = String::deserialize(deserializer)?;
    let s = binding.as_str();
    match s {
        "" => Ok(None),
        value => DateTime::parse_from_str(value, FORMAT).map_err(serde::de::Error::custom).map(Some)
    }

    // Utc.datetime_from_str(&s, FORMAT).map_err(serde::de::Error::custom)
}
