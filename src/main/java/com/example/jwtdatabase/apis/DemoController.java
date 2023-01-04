package com.example.jwtdatabase.apis;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello Authenticated User");
    }

    @GetMapping("/bye")
    public ResponseEntity<String> bye(){
        return ResponseEntity.ok("Bye Authenticated User");
    }
}
