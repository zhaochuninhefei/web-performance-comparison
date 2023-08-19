package com.zhaochuninhefei.webpmwebflux.router;

import com.zhaochuninhefei.webpmwebflux.handler.AssetHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 采用路由器模式实现httpAPI,暂时禁用
 *
 * @author zhaochun
 */
//@Configuration
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
