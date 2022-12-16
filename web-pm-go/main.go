package main

import "github.com/zhaochuninhefei/web-performance-comparison/web-pm-go/route"

func main() {
	router := route.RegisterWebRoute()
	// listen and serve on 0.0.0.0:18080 (for windows "localhost:18080")
	err := router.Run(":18080")
	if err != nil {
		return
	}
}
