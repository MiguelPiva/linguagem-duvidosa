# linguagem-duvidosa

### Integrantes

+ Gabriel Jonatas Almada da Silva - 11201810556
+ Miguel Piva de Santana - 11202130872
+ Pedro Henrique Dias Pereira - 11202130640

---

Para o ANTLR gerar os arquivos:
``` 
java -cp ./duvidosacompiler/antlr-4.13.2-complete.jar org.antlr.v4.Tool ./duvidosacompiler/Duvidosa.g4 -o ./duvidosacompiler/src/main/java/com/gthc/duvidosacompiler/core -package com.gthc.duvidosacompiler.core

```


+ O arquivo consultaDuvidosa.txt serve para facilitar a visualização das definições das linguagem;
+ O programa do principal (**DuvidosacompilerApplication.java**) está em src/main/java/com/gthc/duvidosacompiler/;
+ O programa do compilador (**App.java**) está em src/main/java/com/gthc/duvidosacompiler/application/;
+ O arquivo de entrada (**input.in**) está em src/main/java/com/gthc/duvidosacompiler/application/;
