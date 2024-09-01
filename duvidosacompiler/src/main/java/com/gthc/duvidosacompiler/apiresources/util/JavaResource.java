package com.gthc.duvidosacompiler.apiresources.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gthc.duvidosacompiler.apicompiler.JavaAPI;
import com.gthc.duvidosacompiler.core.exceptions.DuvidosaSemanticException;


@RestController
@RequestMapping("/java")
public class JavaResource {

        @PostMapping()
        public ResponseEntity<String> postMethodName(@RequestBody String entity) {
        try {
            JavaAPI compiler = new JavaAPI();
            String code = compiler.compile(entity);
            System.out.println(code);
            return ResponseEntity.ok().body(code);
            }
            catch (DuvidosaSemanticException e) {
                throw new DuvidosaSemanticException(e.getMessage());
            }
        }
}

