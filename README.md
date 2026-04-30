# 📦 Ecommerce Inventory API

API REST desenvolvida em **Java com Spring Boot** para gestão completa de estoque e pedidos em um cenário de e-commerce real.

O sistema integra produtos, movimentações de estoque e pedidos com controle automático de quantidade, autenticação JWT e containerização com Docker.

---

## 🚀 Tecnologias

| Tecnologia | Versão | Uso |
|---|---|---|
| Java | 17 | Linguagem principal |
| Spring Boot | 3.x | Framework base |
| Spring Security | 6.x | Autenticação e autorização |
| Spring Data JPA | 3.x | Persistência de dados |
| MySQL | 8 | Banco de dados relacional |
| Flyway | - | Migrations de banco |
| Docker & Docker Compose | - | Containerização |
| JWT (auth0) | - | Tokens de autenticação |
| BCrypt | - | Hash de senhas |
| JUnit + Mockito | - | Testes unitários |

---

## 🧠 Arquitetura

O projeto segue arquitetura em camadas com separação clara de responsabilidades:

```
src/
├── controller/       → Entrada HTTP, validação de request
├── service/          → Regras de negócio
├── repository/       → Acesso a dados (Spring Data JPA)
├── entity/           → Modelagem do domínio
├── dto/              → Objetos de transferência de dados
├── config/
│   └── security/     → Configuração do Spring Security + Filtro JWT
└── migrations/       → Scripts Flyway
```

---

## 🔐 Segurança

- Autenticação via **JWT (Bearer Token)**
- Senhas criptografadas com **BCrypt**
- Filtro customizado `SecurityFilter` que intercepta e valida o token em cada requisição
- Sessão **stateless** (sem estado no servidor)
- Endpoint `/login` público — demais endpoints protegidos

---

## 📋 Pré-requisitos

- [Docker](https://www.docker.com/) e Docker Compose instalados
- Java 17+ *(somente para rodar sem Docker)*

---

## ⚙️ Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto (ou configure diretamente no `docker-compose.yml`):

```env
SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/ecommerce_db
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=root
JWT_SECRET=sua_chave_secreta_aqui
```

---

## 🐳 Como rodar com Docker

```bash
# Subir todos os containers
docker compose up --build

# Rodar em background
docker compose up --build -d

# Parar os containers
docker compose down
```

📡 Após subir:
- **API:** `http://localhost:8080`
- **MySQL:** porta `3307`

---

## 🔑 Autenticação

### 1. Login

```http
POST /login
Content-Type: application/json
```

```json
{
  "login": "usuario@ecommerce.com",
  "password": "suasenha"
}
```

**Resposta:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### 2. Usar o token nas demais requisições

```http
Authorization: Bearer SEU_TOKEN_AQUI
```

---

## 📌 Endpoints

### 🛍️ Produtos

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/products` | Criar produto |
| `GET` | `/products` | Listar produtos (paginado) |
| `GET` | `/products?name=xx` | Buscar produto por nome |
| `PUT` | `/products/{id}` | Atualizar produto |
| `DELETE` | `/products/{id}` | Soft delete do produto |

**Criar produto:**
```http
POST /products
Authorization: Bearer SEU_TOKEN
Content-Type: application/json
```
```json
{
  "name": "Notebook Dell",
  "description": "Notebook i7 16GB RAM",
  "price": 4500.00,
  "quantity": 10
}
```

---

### 📊 Estoque

| Método | Endpoint              | Descrição |
|---|-----------------------|---|
| `POST` | `/movement/entry`     | Entrada de estoque |
| `POST` | `/movement/exit`      | Saída de estoque |

**Entrada de estoque:**
```http
POST /inventory/entry
Authorization: Bearer SEU_TOKEN
Content-Type: application/json
```
```json
{
  "productId": 1,
  "quantity": 50
}
```

---

### 📦 Pedidos

| Método | Endpoint | Descrição |
|---|---|---|
| `POST` | `/orders` | Criar pedido |
| `GET` | `/orders` | Listar pedidos |
| `GET` | `/orders/{id}` | Buscar pedido por ID |

**Criar pedido:**
```http
POST /orders
Authorization: Bearer SEU_TOKEN
Content-Type: application/json
```
```json
{
  "customerName": "Tiago Silva",
  "items": [
    {
      "productId": 1,
      "quantity": 2
    },
    {
      "productId": 3,
      "quantity": 1
    }
  ]
}
```

**Resposta:**
```json
{
  "id": 1,
  "customerName": "Tiago Silva",
  "totalValue": 9500.00,
  "items": [...]
}
```

---

## ⚠️ Regras de Negócio

- ❌ Não permite estoque negativo
- ❌ Pedido não é criado se não houver estoque suficiente
- ✅ Baixa automática no estoque após criação de pedido
- ✅ Cálculo automático do valor total do pedido
- ✅ Soft delete em produtos (não remove do banco)
- ✅ Validações com Bean Validation (`@Valid`)

---

## 🧪 Testes

Testes unitários implementados com **JUnit + Mockito** para as camadas de serviço:

```bash
# Rodar os testes
./mvnw test
```

Cobertura:
- `ProductService`
- `InventoryMovementService`
- `OrderService`

---

## 🗂️ Migrations (Flyway)

O banco é versionado via Flyway. As migrations rodam automaticamente ao subir a aplicação, criando todas as tabelas necessárias.

---

## 🚀 Possíveis Melhorias

- [ ] Controle de roles (ADMIN / USER)
- [ ] Paginação em pedidos
- [ ] Logs estruturados (ex: Logback + ELK)
- [ ] Swagger / OpenAPI para documentação interativa
- [ ] Deploy em cloud (AWS / Render / Railway)
- [ ] Testes de integração
- [ ] Rate limiting

---

## 🎯 Objetivo do Projeto

Demonstrar domínio em:

- Desenvolvimento backend com **Java**
- Construção de **APIs REST**
- **Segurança** com JWT e Spring Security
- **Modelagem de domínio** e relacionamentos JPA
- Boas práticas: **Clean Code, SOLID, separação de camadas**
- **Containerização** com Docker
- **Versionamento de banco** com Flyway
- **Testes unitários** com JUnit e Mockito

---

## 👨‍💻 Autor

**Tiago Silva**  
[GitHub](https://github.com/tiagosilva06) • [LinkedIn](https://linkedin.com/in/tiago-silvadev)