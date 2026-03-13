<div align="center">

# 📦 Ecommerce Inventory API

API REST para **gerenciamento de estoque de e-commerce** desenvolvida com **Java e Spring Boot**.  
O sistema permite cadastro de produtos, controle de estoque e registro de movimentações com autenticação segura usando **JWT**.

![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-JWT-6DB33F?logo=springsecurity&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1?logo=mysql&logoColor=white)

</div>

---

## 🎯 Visão Geral

A **Ecommerce Inventory API** simula um sistema real de controle de estoque utilizado em e-commerces.

A aplicação permite:

- cadastro e gerenciamento de produtos
- controle de entradas e saídas de estoque
- histórico de movimentações
- autenticação segura com JWT
- paginação e busca de produtos

O projeto foi desenvolvido seguindo boas práticas de **arquitetura em camadas** e **separação de responsabilidades**.

---

## ✨ Funcionalidades

| Recurso | Descrição |
|------|------|
| 📦 Gestão de produtos | CRUD completo de produtos |
| 📊 Controle de estoque | Entrada e saída de produtos |
| 🔍 Busca | Pesquisa de produtos por nome |
| 📄 Paginação | Listagem paginada |
| 🗑 Soft Delete | Produtos não são removidos fisicamente |
| 🔐 Segurança | Autenticação com JWT |

---

## 🏗 Arquitetura

A aplicação segue uma arquitetura em camadas:

Client → Controller → Service → Repository → Database

Cada camada possui uma responsabilidade específica, facilitando manutenção e escalabilidade.

---

## 🧰 Tech Stack

Backend:

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT (Auth0)
- Hibernate
- Flyway
- Lombok

Banco de Dados:

- MySQL

---

## 📡 Endpoints

Autenticação:

POST /login

Produtos:

POST /products  
GET /products  
GET /products/{id}  
GET /products/name  
PUT /products  
DELETE /products/{id}

Movimentação de estoque:

POST /movement/entry  
POST /movement/exit

---

## 🗄 Banco de Dados

O banco é versionado utilizando **Flyway** e possui três tabelas principais:

- products
- users
- inventory_movement

A tabela **inventory_movement** registra todas as entradas e saídas de estoque, permitindo auditoria completa das movimentações.

---

## ▶ Como Rodar

1. Clonar o repositório  
   git clone https://github.com/seu-usuario/ecommerce-inventory-api

2. Configurar banco MySQL

3. Definir secret do JWT na aplicação

4. Executar o projeto  
   mvn spring-boot:run

API disponível em:

http://localhost:8080

---

<div align="center">

Desenvolvido por **Tiago Silva**

</div>