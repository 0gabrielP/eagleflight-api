# Eagle Flight Airlines API

Uma API REST de gerenciamento de linhas aéreas e fidelização de passageiros desenvolvida em Java com Spring Boot e Spring Data JPA. O sistema calcula automaticamente descontos em passagens e acúmulo de milhas baseado na categoria de fidelidade do passageiro.

## Funcionalidades Principais
- **Cadastro de Passageiros:** Gerenciamento de clientes com atribuição de categorias automáticas (`BRONZE`, `PRATA`, `DIAMANTE`).
- **Cálculo de Regras de Negócio Isoladas:** Camada de serviço (`Service`) robusta para calcular descontos e milhas sem poluir as rotas controladoras.
- **Relacionamento Relacional:** Vínculo de integridade no banco de dados mapeando que muitos voos pertencem a um passageiro (`@ManyToOne`).

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA** (Persistência e Mapeamento Objeto-Relacional)
- **H2 Database** (Banco de dados relacional em arquivo/memória)
- **Maven** (Gerenciador de dependências)

## Arquitetura do Projeto
O projeto segue o padrão arquitetural em camadas para garantir a separação de responsabilidades:
- `model`: Representação das tabelas e entidades do banco de dados.
- `repository`: Interfaces que estendem o `JpaRepository` para operações de CRUD.
- `service`: Concentração das regras de negócio e cálculos matemáticos da companhia.
- `controller`: Exposição dos endpoints REST e recepção das requisições HTTP.

## Como Executar o Projeto
1. Clone o repositório:
   ```bash
   git clone [https://github.com/seu-usuario-aqui/banco_eagleFlight.git](https://github.com/seu-usuario-aqui/banco_eagleFlight.git)