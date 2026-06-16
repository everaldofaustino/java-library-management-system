# 📚 Library Management System

![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-6DB33F?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?logo=apachemaven&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?logo=swagger&logoColor=black)
![REST API](https://img.shields.io/badge/API-REST-blue)

API REST desenvolvida com Java e Spring Boot para gerenciamento de livros e categorias em uma biblioteca.

O projeto foi desenvolvido com foco em boas práticas de desenvolvimento backend, utilizando arquitetura em camadas, DTOs, tratamento global de exceções, persistência com JPA/Hibernate e documentação via Swagger/OpenAPI.

---

# 🚀 Tecnologias Utilizadas

## Backend

- Java 21
- Spring Boot 3
- Spring Data JPA
- Hibernate
- Maven
- Lombok
- ModelMapper

## Banco de Dados

- MySQL

## Validação

- Jakarta Validation

## Documentação

- Swagger/OpenAPI

---

# 🏗 Arquitetura do Projeto

```text
Cliente
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
Banco de Dados
```

---

# 📂 Estrutura do Projeto

```text
src/main/java
│
├── controllers
│   ├── CategoriaController
│   └── LivroController
│
├── dtos
│   ├── CategoriaDto
│   └── LivroDto
│
├── enums
│   └── Edicao
│
├── exceptions
│   ├── GlobalException
│   ├── StandardError
│   ├── ValidationErrors
│   └── FieldError
│
├── mapper
│   └── ModelMapperConfig
│
├── models
│   ├── Categoria
│   └── Livro
│
├── repositories
│   ├── CategoriaRepository
│   └── LivroRepository
│
├── services
│   ├── CategoriaService
│   └── LivroService
│
└── BibliotecaApplication
```

---

# 📖 Funcionalidades

## Categorias

- Criar categoria
- Buscar categoria por ID
- Buscar categoria por nome
- Atualizar categoria
- Excluir categoria
- Listar todas as categorias

---

## Livros

- Criar livro
- Buscar livro por ID
- Buscar livro por título
- Buscar livros por categoria
- Buscar livros pelo nome da categoria
- Atualizar livro
- Excluir livro
- Listar todos os livros

---

# 🗄 Modelo de Dados

## Categoria

| Campo | Tipo |
|---------|---------|
| id | Integer |
| nome | String |
| descricao | String |

---

## Livro

| Campo | Tipo |
|---------|---------|
| id | Integer |
| titulo | String |
| autor | String |
| texto | String |
| edicao | Enum |
| categoria | Categoria |

---

# 🔗 Relacionamentos

```text
Categoria
    1
    │
    │
    N
Livro
```

Uma categoria pode possuir vários livros.

Um livro pertence a apenas uma categoria.

---

# 📡 Endpoints

## Categoria

### Buscar Categoria por ID

```http
GET /categoria/{id}
```

Exemplo:

```http
GET /categoria/1
```

---

### Buscar Categoria por Nome

```http
GET /categoria/nomes/{nome}
```

Exemplo:

```http
GET /categoria/nomes/Informática
```

---

### Listar Categorias

```http
GET /categoria
```

---

### Criar Categoria

```http
POST /categoria
```

Body:

```json
{
  "nome": "Informática",
  "descricao": "Livros da área de TI"
}
```

---

### Atualizar Categoria

```http
PUT /categoria/{id}
```

Body:

```json
{
  "nome": "Tecnologia",
  "descricao": "Livros sobre tecnologia"
}
```

---

### Excluir Categoria

```http
DELETE /categoria/{id}
```

---

# 📚 Livro

## Buscar Livro por ID

```http
GET /livro/{id}
```

Exemplo:

```http
GET /livro/1
```

---

## Buscar Livro por Título

```http
GET /livro/titulo/{nome}
```

Exemplo:

```http
GET /livro/titulo/Clean Code
```

---

## Buscar Livros por Categoria

```http
GET /livro?categoria=1
```

---

## Buscar Livros pelo Nome da Categoria

```http
GET /livro/categoria/nome/Informática
```

---

## Listar Todos os Livros

```http
GET /livro/todos
```

---

## Criar Livro

```http
POST /livro?categoria=1
```

Body:

```json
{
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "texto": "Livro sobre boas práticas de programação",
  "edicao": "PRIMEIRA"
}
```

---

## Atualizar Livro

```http
PUT /livro/{id}?categoria=1
```

Body:

```json
{
  "titulo": "Clean Architecture",
  "autor": "Robert C. Martin",
  "texto": "Arquitetura de Software",
  "edicao": "SEGUNDA"
}
```

---

## Excluir Livro

```http
DELETE /livro/{id}
```

---

# ⚠ Tratamento de Exceções

O projeto utiliza tratamento global de exceções através de:

```java
@ControllerAdvice
```

Tratando:

- Objeto não encontrado
- Erros de validação
- Violação de integridade
- Requisições inválidas

Exemplo:

```json
{
  "timestamp": "2025-06-12T10:30:00",
  "status": 404,
  "error": "Objeto não encontrado",
  "message": "Livro não encontrado"
}
```

---

# 📄 Documentação Swagger

Após iniciar a aplicação:

```text
http://localhost:8082/swagger-ui/index.html
```

A documentação OpenAPI estará disponível em:

```text
http://localhost:8082/v3/api-docs
```

---

# 🧪 Como Executar o Projeto

## Clonar Repositório

```bash
git clone git@github.com:everaldofaustino/java-library-management-system.git
```

---

## Entrar no Projeto

```bash
cd java-library-management-system
```

---

## Criar Banco de Dados

```sql
CREATE DATABASE biblioteca;
```

---

## Configurar application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
spring.datasource.username=root
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## Executar Aplicação

```bash
mvn spring-boot:run
```

ou

```bash
mvn clean install
java -jar target/biblioteca.jar
```

---

# 🎯 Conceitos Aplicados

- API REST
- Spring Boot
- DTO Pattern
- Service Layer Pattern
- Repository Pattern
- JPA/Hibernate
- Relacionamentos entre Entidades
- ModelMapper
- Bean Validation
- Tratamento Global de Exceções
- Swagger/OpenAPI
- Arquitetura em Camadas

---

# 🚀 Melhorias Futuras

- Docker
- Docker Compose
- Spring Security
- JWT Authentication
- Testes Unitários com JUnit
- Mockito
- Deploy AWS
- GitHub Actions
- CI/CD

---

## ⚠️ Validação de Campos

A API utiliza Bean Validation (`@Valid`) para validar os dados recebidos.

Exemplo de resposta:

```json
{
  "timestamp": "16/06/2026 11:00:00",
  "status": 400,
  "error": "Erro na validação dos campos",
  "path": "/livro",
  "erros": [
    {
      "fieldName": "titulo",
      "message": "Título é obrigatório"
    }
  ]
}
```

# 👨‍💻 Autor

**Everaldo Faustino**

Desenvolvedor Backend Java.

GitHub:

https://github.com/everaldofaustino

LinkedIn:

