# Eagle Flight Airlines API

Uma API REST robusta e escalável desenvolvida para o gerenciamento de programas de fidelidade e cálculo dinâmico de milhas aéreas. O projeto foi estruturado seguindo as melhores práticas de mercado, com forte separação de responsabilidades, validações defensivas e uma ampla cobertura de testes automatizados.

## Tecnologias e Ferramentas Utilizadas

* **Linguagem:** Java 17
* **Framework Principal:** Spring Boot 3
* **Persistência de Dados:** Spring Data JPA / Hibernate
* **Banco de Dados:** MySQL
* **Gerenciamento de Migrações:** Flyway
* **Containerização:** Docker & Docker Compose
* **Testes Automatizados:** JUnit 5 & Mockito
* **Validação de Dados:** Jakarta Validation

## Arquitetura do Sistema

O projeto adota a arquitetura em camadas padrão de mercado, garantindo manutenibilidade e baixo acoplamento:
* **Controllers:** Exposição dos endpoints REST e validação inicial das requisições com `jakarta.validation`.
* **Services:** Concentração e isolamento de todas as regras de negócio complexas (como cálculo dinâmico de descontos e acúmulo de milhas por categoria).
* **Repositories:** Camada de abstração e comunicação com o banco de dados via JPA.
* **Models/Entities:** Mapeamento objeto-relacional (ORM) e definição de constraints do banco de dados.

## Diferenciais Técnicos (Práticas de Produção)

* **Tratamento Global de Exceções:** Implementação de um `GlobalExceptionHandler` utilizando `@RestControllerAdvice`, garantindo que a API responda com status HTTP semanticamente corretos e mensagens amigáveis em cenários de erro.
* **Migrações Automatizadas:** Uso do Flyway para versionamento do esquema do banco de dados (`V1__criar_tabelas.sql`), garantindo consistência entre ambientes de desenvolvimento e produção.
* **Ambiente Isolado com Docker:** Configuração do MySQL via Docker Compose, permitindo que a aplicação seja executada localmente com um único comando, sem necessidade de instalações locais complexas.

## Qualidade de Código e TestesAutomatizados

O projeto possui uma forte cultura de qualidade, com testes cobrindo os fluxos críticos da aplicação:
* **Testes Unitários (Mockito):** Isolamento de regras de negócio na camada de serviço (`FidelidadeServiceTest`), garantindo o comportamento correto dos fluxos com mocks controlados e asserções rigorosas (incluindo testes de lançamento de exceções).
* **Testes de Integração de API (MockMvc):** Validação dos contratos dos controllers (`PassageiroControllerTest`), testando o comportamento das rotas HTTP, status de retorno e payload JSON (`jsonPath`).

## Como Executar o Projeto

### Pré-requisitos
* Java 17 instalado
* Docker e Docker Compose instalados

### Passo a Passo
1. Clone o repositório:
   ```bash
   git clone [https://github.com/0gabrielP/eagle-flight-airlines.git](https://github.com/0gabrielP/eagle-flight-airlines.git)