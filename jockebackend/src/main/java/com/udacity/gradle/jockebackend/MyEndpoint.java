/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.udacity.gradle.jockebackend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.udacity.gradle.jokes.Joker;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "jockebackend.gradle.udacity.com",
                ownerName = "jockebackend.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that tell a joke from javajokes library
     */
    @ApiMethod(name = "tellMeAJoke")
    public MyBean tellMeAJoke() {
        Joker myJoker = new Joker();

        MyBean response = new MyBean();
        response.setData(myJoker.getJoke());

        return response;
    }

}
