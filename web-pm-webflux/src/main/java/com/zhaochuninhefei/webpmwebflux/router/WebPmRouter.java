package com.zhaochuninhefei.webpmwebflux.router;

import com.zhaochuninhefei.webpmwebflux.handler.AssetHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

/**
 * @author zhaochun
 */
@Configuration
public class WebPmRouter {
    @Bean
    public RouterFunction<ServerResponse> routerAsset(AssetHandler assetHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/asset/list")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        assetHandler::queryAllAssets)
                .andRoute(RequestPredicates.GET("/asset/query")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        assetHandler::queryAssetById)
                .andRoute(RequestPredicates.POST("/asset/modify"),
                        assetHandler::modifyAsset);

    }
}
