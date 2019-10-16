package com.education.sample.service.sendMessage;

import org.springframework.web.bind.annotation.*;

@RestController
public class SendMessageController {
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/send-message", method = RequestMethod.PUT)
    public ResponseClass handleRequest(@RequestBody RequestClass requestClass) throws Exception {

        if (requestClass.getUserName() == null) {
            throw new Exception("invalid username");
        }

        return new ResponseClass(
                String.format("User: %s | clicked the button!", requestClass.getUserName()),
                200,
                "SUCCESS"
        );
    }
}
