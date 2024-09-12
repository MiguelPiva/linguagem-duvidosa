# linguagem Duvidosa

### Integrantes

+ Gabriel Jonatas Almada da Silva - 11201810556
+ Miguel Piva de Santana - 11202130872
+ Pedro Henrique Dias Pereira - 11202130640

---

<br>

A linguagem Duvidosa pode ser convertida para 4 outras linguagens, sendo elas: **C**, **C#**, **Java** e **Rust**. É gerado apenas um arquivo na linguagem Java a partir do arquivo [input.in](https://github.com/MiguelPiva/linguagem-duvidosa/blob/main/duvidosacompiler/src/main/java/com/gthc/duvidosacompiler/application/input.in), ele terá o nome "testando" por padrão e ficará localizado na raiz do projeto. Porém, os códigos de todas as linguagens podem ser gerados pela API localizada na porta 8080 (c, csharp, java, rust). Abaixo, segue um exemplo de uso da API.

<details>
<summary>Exemplo API</summary>

## POST localhost:8080/rust
### Corpo da requisição
```
inicioprog postman
    escreva("Hello World!");
fimprog
```

<br>

### Retorno

```
using System;

public class postman {
	public static void Main (string[] args) {
		Console.WriteLine("Hello World!");
	}
}
```

<br>
<br>
</details>
<br>

Ademais, as regras e os tokens da linguagem duvidosa podem ser visualizados sem códigos no arquivo de texto [consultaDuvidosa.txt](https://github.com/MiguelPiva/linguagem-duvidosa/blob/main/duvidosacompiler/consultaDuvidosa.txt) ou com códigos no arquivo para ANTLR [Duvidosa.g4](https://github.com/MiguelPiva/linguagem-duvidosa/blob/main/duvidosacompiler/Duvidosa.g4).

<br>

## Obrigatórios

<div align="center">

| Número | Característica                                     |
|-------|--------------------------------------------------|
| **1**     | Possui 2 tipos de variáveis                          |
| **2**     | Possui a estrutura if...else                         |
| **3**     | Possui estrutura de controle while/do while           |
| **4**     | Operações Aritméticas executadas corretamente      |
| **5**     | Atribuições realizadas corretamente               |
| **6**     | Possui operações de Entrada e Saída                 |
| **7**     | Aceita números decimais                           |
| **8**     | Verificar se a variável já foi previamente declarada |
| **9**     | Verificar se a variável foi declarada e não foi usada |
| **10**    | Verificar se uma variável está sendo usada sem ter valor inicial |

</div>
<br>

## Opcionais

<div align="center">

| Item | Descrição                                     | Complexidade | Feito |
|-----|--------------------------------------------------|-------------| --- |
| **1**   | Editor Highlight (simulando uma pequena IDE)  | 3           | X |
| **2**   | Avaliador de expressões aritméticas              | 3           |   |
| **3**   | Inserção de Operadores lógicos                  | 2           |   |
| **4**   | Geração de várias linguagens-alvo                 | 3           | X |
| **5**   | Uma API Rest para implementação do compilador    | 4           | X |
| **6**   | Um Interpretador (runtime) para a linguagem     | 5           |   |

</div>
