package cn.yjl.helidon.service;

import cn.yjl.helidon.JsonObjectEnable;
import io.helidon.dbclient.DbRow;
import io.helidon.http.media.ReadableEntity;
import io.helidon.webserver.http.HttpService;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

import java.util.stream.Stream;

import static cn.yjl.helidon.Util.JSON_FACTORY;

public abstract class BaseHttpService implements HttpService {

	protected <T extends JsonObjectEnable<T>> JsonArray convert(Stream<DbRow> searchResult, Class<T> clazz) {
		return searchResult.map(row -> row.as(clazz).toJson())
			.collect(JSON_FACTORY::createArrayBuilder, JsonArrayBuilder::add, JsonArrayBuilder::addAll)
			.build();
	}

}
