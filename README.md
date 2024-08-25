# linguagem-duvidosa

Para o ANTLR gerar os arquivos:
``` 
java -cp ./projeto_compiladores/antlr-4.13.2-complete.jar org.antlr.v4.Tool ./projeto_compiladores/Duvidosa.g4 -o ./projeto_compiladores/src/io/compiler/core -package io.compiler.core 
```


+ O arquivo consultaDuvidosa.txt serve para facilitar a visualização das definições das linguagem;
+ O programa principal (**App.java**) está em src/io/compiler/main/;
+ O arquivo de entrada (**input.in**) está em src/io/compiler/main/;
