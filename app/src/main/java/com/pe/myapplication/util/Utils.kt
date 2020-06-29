package com.pe.myapplication.util

private const val tag = "Utils"

class Utils {

    companion object {
        fun matchUri(
            uri: String,
            route: String,
            properties: MutableMap<String?, String?>
        ): Boolean {
            if (route.isEmpty() || uri.isEmpty()) {
                return false
            }
            var lastRouteIndex = route.indexOf("/")
            var lastUriIndex = uri.indexOf("/")

            // detects uris that do not contain '/'
            if (lastRouteIndex != 0 || lastUriIndex != 0) {
                return false
            }

            // route consists of only one character, simple compare it with the uri
            if (route.length <= lastRouteIndex + 1) {
                return route == uri
            }

            // uri consists of only one character, simple compare it with the route
            if (uri.length <= lastUriIndex + 1) {
                return route == uri
            }
            var newRouteIndex: Int
            var newUriIndex: Int
            var routeEnded = false
            var uriEnded = false
            while (true) {
                newRouteIndex = route.indexOf("/", lastRouteIndex + 1)
                newUriIndex = uri.indexOf("/", lastUriIndex + 1)
                if (newRouteIndex == -1) {
                    newRouteIndex = route.length
                    routeEnded = true
                }
                if (newUriIndex == -1) {
                    newUriIndex = uri.length
                    uriEnded = true
                }
                val routeToken = route.substring(lastRouteIndex + 1, newRouteIndex)
                val token = uri.substring(lastUriIndex + 1, newUriIndex)

                // copy uri params into the properties
                when {
                    routeToken.startsWith(":") -> {
                        properties[routeToken.substring(1)] = token
                        // copy uri remainder for parsing by nested routers into the properties
                    }
                    routeToken == "..." -> {
                        properties["uriRemainder"] = uri.substring(lastUriIndex)
                        return true
                        // mismatch found, go to the next route
                    }
                    routeToken != token -> {
                        return false
                    }
                }
                if (routeEnded && !uriEnded || uriEnded && !routeEnded) {
                    return false
                }
                if (uriEnded && routeEnded) {
                    break
                }
                lastRouteIndex = newRouteIndex
                lastUriIndex = newUriIndex
            }
            return true
        }
    }

}