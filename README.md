- Eagle Flight Airlines API

Uma API robusta para gerenciamento de passageiros, viagens e programas de fidelidade, desenvolvida com Spring Boot, JPA/Hibernate e MySQL dentro de um ambiente Docker.

## Funcionalidades Atuais
- Cadastro, listagem, atualização e deleção de Passageiros (CRUD Completo).
- Validação de dados de entrada (Bean Validation) como e-mail e CPF obrigatórios.
- Cadastro de Viagens com vinculação automatizada ao Passageiro via banco relacional.
- **Regra de Negócio Integrada:** Cálculo automático de preço final com descontos baseados na Categoria de Fidelidade (Prata, Ouro/Gold, etc.) e acúmulo automático de milhas no perfil do passageiro após o faturamento do voo.

## Rotas da API (Endpoints)

### Passageiros (`/passageiros`)
- `POST /passageiros` - Cadastra um novo passageiro (Campos blindados: Nome, E-mail, CPF, Idade).
- `GET /passageiros` - Lista todos os passageiros cadastrados e seus saldos de milhas.
- `PUT /passageiros/{id}` - Atualiza dados cadastrais de um passageiro mantendo o histórico de milhas intacto.
- `DELETE /passageiros/{id}` - Remove um passageiro do sistema.

### Viagens (`/viagens`)
- `POST /viagens` - Cadastra uma nova viagem associada a um passageiro existente, aplicando regras de desconto e gerando milhas.
- `GET /viagens` - Lista o histórico de todas as viagens faturadas no sistema.

## Tecnologias Utilizadas
- Java 17 / Spring Boot
- Spring Data JPA & Hibernate
- Bean Validation (Mapeamento e Blindagem)
- Banco de Dados MySQL (Rodando via Docker)
- Postman (Para testes de integração)