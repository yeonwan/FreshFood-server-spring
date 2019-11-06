package com.foodmanager.server.service.controller;

import com.foodmanager.server.service.model.ResponseClass;
import com.foodmanager.server.service.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class SendMessageController {
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/send-message", method = RequestMethod.PUT)
    public ResponseClass handleRequest(@RequestBody User user) throws Exception {
        if (user.getUserName() == null) {
            throw new Exception("invalid username");
        }

        return new ResponseClass(
                String.format("User: %s | clicked the button!", user.getUserName()),
                200,
                "SUCCESS"
        );
    }
}
