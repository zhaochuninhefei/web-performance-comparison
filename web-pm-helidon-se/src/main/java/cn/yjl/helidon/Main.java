
package cn.yjl.helidon;


import cn.yjl.helidon.service.AccountService;
import cn.yjl.helidon.service.AssetService;
import io.helidon.http.media.MediaContext;
import io.helidon.http.media.jsonp.JsonpSupport;
import io.helidon.logging.common.LogConfig;
import io.helidon.config.Config;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.HttpRouting;




/**
 * The application main class.
 */
public class Main {


    /**
     * Cannot be instantiated.
     */
    private Main() {
    }


    /**
     * Application main entry point.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        
        // load logging configuration
        LogConfig.configureRuntime();

        // initialize global config from default configuration
        Config config = Config.create();
        Config.global(config);


        WebServer server = WebServer.builder()
                .config(config.get("server"))
                .routing(Main::routing)
                .mediaContext(MediaContext.builder()
                        .mediaSupportsDiscoverServices(false)
                        .addMediaSupport(JsonpSupport.create())
                        .build())
                .build()
                .start();

        System.out.println("WEB server is up! http://localhost:" + server.port() + "/simple-greet");

    }


    /**
     * Updates HTTP Routing.
     */
    static void routing(HttpRouting.Builder routing) {
        routing.register("/asset", new AssetService())
                .register("/account", new AccountService())
                .get("/simple-greet", (req, res) -> res.send("Hello World!"));
    }
}