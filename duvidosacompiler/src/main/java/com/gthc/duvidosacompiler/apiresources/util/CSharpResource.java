package com.gthc.duvidosacompiler.apiresources.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gthc.duvidosacompiler.apicompiler.CSharpAPI;
import com.gthc.duvidosacompiler.core.exceptions.DuvidosaSemanticException;


@RestController
@RequestMapping("/csharp")
public class CSharpResource {

        @PostMapping()
        public ResponseEntity<String> postMethodName(@RequestBody String entity) {
            try {
                CSharpAPI compiler = new CSharpAPI();
                String code = compiler.compile(entity);
                System.out.println(code);
                return ResponseEntity.ok().body(code);
            }
            catch (DuvidosaSemanticException e) {
                throw new DuvidosaSemanticException(e.getMessage());
            }
        }
}

