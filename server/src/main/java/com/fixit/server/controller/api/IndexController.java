package com.fixit.server.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class IndexController {

    class WelcomeMessage {

        public WelcomeMessage(String message) {
            this.message = message;
        }

        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


    @RequestMapping( value = "/")
    public WelcomeMessage welcome() throws IOException {
        return new WelcomeMessage("Welcome to fixit api");
    }


}
