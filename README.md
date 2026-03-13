# 📦 Ecommerce Inventory API

API REST para **gerenciamento de estoque de um e-commerce**, desenvolvida com **Java e Spring Boot**.  
O sistema permite cadastrar produtos, controlar entradas e saídas de estoque e registrar todas as movimentações realizadas.

O objetivo do projeto é simular um **sistema real utilizado por lojas online**, aplicando boas práticas de desenvolvimento backend como **arquitetura em camadas, separação de responsabilidades, autenticação com JWT e versionamento de banco de dados**.

---

# 🎯 Objetivo do Projeto

Este projeto foi desenvolvido como **projeto de portfólio backend**, com o objetivo de demonstrar habilidades em:

- desenvolvimento de **APIs REST**
- modelagem de **regras de negócio**
- controle de **estoque**
- implementação de **segurança com JWT**
- utilização de **Spring Boot e Spring Data JPA**
- organização de código seguindo **boas práticas de arquitetura**

A aplicação simula um cenário real onde um e-commerce precisa controlar seu estoque e manter registro de todas as movimentações realizadas.

---

# ⚙️ Funcionalidades

### 📦 Gestão de Produtos
- criação de novos produtos
- atualização de informações do produto
- busca por produto por ID
- busca de produtos por nome
- listagem paginada de produtos
- desativação de produtos (soft delete)

### 📊 Controle de Estoque
O sistema permite registrar dois tipos de movimentação:

**Entrada (ENTRY)**  
Adiciona quantidade ao estoque de um produto.

**Saída (EXIT)**  
Remove quantidade do estoque de um produto.

Todas as movimentações são registradas em uma tabela específica para manter **histórico completo do estoque**.

### 📜 Histórico de Movimentações
Cada alteração de estoque gera um registro contendo:

- produto relacionado
- tipo de movimentação
- quantidade movimentada
- data da operação

Isso permite rastrear completamente as alterações no estoque.

### 🔐 Autenticação e Segurança
A API utiliza **JWT (JSON Web Token)** para autenticação.

Fluxo de autenticação:

1. usuário realiza login
2. a API gera um **token JWT**
3. o cliente utiliza o token para acessar rotas protegidas

Essa abordagem permite um sistema **stateless**, amplamente utilizado em APIs modernas.

---

# 🧱 Arquitetura da Aplicação

O projeto segue o padrão de **arquitetura em camadas**, comum em aplicações Spring Boot.

Camadas da aplicação:

**Controller**  
Responsável por receber as requisições HTTP e retornar respostas ao cliente.

**Service**  
Contém as regras de negócio da aplicação.

**Repository**  
Responsável pelo acesso ao banco de dados utilizando Spring Data JPA.

**Entity**  
Representação das tabelas do banco de dados.

Essa separação facilita:

- manutenção do código
- escalabilidade
- organização do projeto
- testes unitários

---

# 🛠 Tecnologias Utilizadas

## Backend

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- JWT (Auth0)
- Lombok

## Banco de Dados

- MySQL
- Flyway (migrations de banco)

---

# 🗄 Estrutura do Banco de Dados

A aplicação possui três tabelas principais.

### products

Armazena as informações dos produtos.

Campos principais:

- id
- product_name
- color
- product_size
- quantity
- price
- is_active

---

### users

Armazena os usuários responsáveis por acessar o sistema.

Campos:

- id
- login
- password

---

### inventory_movement

Registra todas as movimentações de estoque.

Campos:

- id
- product_id
- type (ENTRY ou EXIT)
- quantity
- created_at

Essa tabela mantém um **histórico completo das operações realizadas no estoque**.

---

# 🔗 Endpoints Principais

## Autenticação

POST /login

Realiza login e retorna um token JWT.

---

## Produtos

POST /products  
Cria um novo produto.

GET /products  
Lista produtos ativos com paginação.

GET /products/{id}  
Busca um produto por ID.

GET /products/name  
Busca produtos pelo nome.

PUT /products  
Atualiza um produto.

DELETE /products/{id}  
Desativa um produto.

---

## Movimentação de Estoque

POST /movement/entry  
Registra entrada de estoque.

POST /movement/exit  
Registra saída de estoque.

---

# ▶ Como Executar o Projeto

### 1 Clonar o repositório

git clone https://github.com/seu-usuario/ecommerce-inventory-api

---

### 2 Configurar o banco de dados

Criar um banco MySQL.

Depois configurar no arquivo:

application.properties

---

### 3 Configurar o secret do JWT

No application.properties:

api.security.token.secret=seu_secret

---

### 4 Executar o projeto

mvn spring-boot:run

A API estará disponível em:

http://localhost:8080

---

# 🚀 Possíveis Melhorias Futuras

Algumas melhorias que podem ser implementadas no projeto:

- documentação da API com **Swagger**
- testes automatizados
- controle de usuários e permissões
- dashboard de estoque
- integração com frontend
- relatórios de movimentação de estoque

---

# 👨‍💻 Autor

Tiago Silva

Desenvolvedor backend focado em **Java e Spring Boot**, interessado em construir aplicações robustas e escaláveis.