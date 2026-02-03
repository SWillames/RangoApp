# 🍽️ RangoApp API

API REST para gerenciamento de usuários do sistema **RangoApp**, desenvolvida com **Spring Boot**, **PostgreSQL**, **Docker** e documentada com **Swagger/OpenAPI**.

Este projeto foi construído com foco em **boas práticas de engenharia de software**, **arquitetura em camadas**, **padronização de ambientes** e **versionamento por branches**, simulando um cenário profissional real.

---

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.3.x**
    - Spring Web
    - Spring Data JPA
    - Spring Validation
- **PostgreSQL 16**
- **Docker & Docker Compose**
- **SpringDoc OpenAPI (Swagger)**
- **ModelMapper**
- **Lombok**
- **Maven**

---

## 📁 Estrutura de Pacotes

```text
br.com.swsoftware.rangoapp
├── application
│   ├── controller
│   ├── dto
│   ├── mapper
│   └── service
├── domain
│   └── model
├── infrastructure
│   └── repository
├── config
└── exception
```

Essa organização promove **separação de responsabilidades**, **facilidade de manutenção** e **evolução do sistema**.

---

## 🧠 Principais Funcionalidades

- Cadastro de usuários
- Atualização de dados cadastrais
- Alteração de senha
- Autenticação (login)
- Busca de usuários por nome
- Tratamento de erros padronizado com **ProblemDetail**

---

## 📘 Documentação da API (Swagger)

Após a aplicação estar em execução, a documentação estará disponível em:

- **Swagger UI**  
  http://localhost:8080/swagger-ui/index.html

- **OpenAPI (JSON)**  
  http://localhost:8080/v3/api-docs

---

## 🔄 Execução Local e com Docker

O projeto foi preparado para ser executado **tanto em ambiente local quanto em ambiente containerizado**, utilizando a **mesma configuração de variáveis de ambiente**, garantindo consistência entre os cenários.

---

### ▶️ Execução com Docker (Recomendada)

A execução via Docker é a forma recomendada, pois elimina dependências do ambiente local e garante padronização.

#### Pré-requisitos
- Docker
- Docker Compose v2+
- Git
- (Windows) WSL ou Git Bash

#### Passos

1. Crie o arquivo `.env` na raiz do projeto:

```env
POSTGRES_DB=tech_challenge
POSTGRES_USER=rm_user
POSTGRES_PASSWORD=rm_pass
HIBERNATE_DDL_AUTO=update
```

2. Suba a aplicação:

```bash
./run.sh
```

3. Para interromper a execução:

```bash
./stop.sh
```

⚠️ **Observação para ambientes Unix (Linux, macOS e WSL)**  
Caso os scripts não possuam permissão de execução após o clone do repositório, execute:

```bash
chmod +x run.sh stop.sh
```
Esse passo é necessário apenas uma vez.

---

### ▶️ Execução Local (Sem Docker)

Também é possível executar a aplicação localmente, desde que o ambiente esteja devidamente configurado.

#### Pré-requisitos
- Java 21 ou superior
- Maven
- PostgreSQL em execução

#### Passos

1. Crie o arquivo `.env` na raiz do projeto (o mesmo utilizado na execução via Docker).

2. Configure o banco de dados local com os mesmos valores definidos no `.env`.

3. Execute a aplicação:

```bash
mvn spring-boot:run
```

> O arquivo `application.yml` está preparado para consumir variáveis de ambiente, permitindo alternar entre execução local e Docker sem necessidade de alterações no código.

---

## 🧪 Testes

O projeto está preparado para testes utilizando:

- JUnit 5
- Spring Boot Test

Durante o build da imagem Docker, os testes são ignorados para acelerar o processo:

```bash
-Dmaven.test.skip=true
```

---

## 🌱 Estratégia de Branches

O desenvolvimento seguiu uma estratégia inspirada em ambientes corporativos:

- `develop` — branch principal de desenvolvimento
- `feature/*` — novas funcionalidades
- `fix/*` — correções de bugs
- `refactor/*` — melhorias estruturais

---

## 👨‍💻 Autor

**SW Software**  
📧 sergiowoc@gmail.com

---

## 📌 Observações Finais

- O projeto utiliza **Spring Boot 3.3.x** por compatibilidade com o ecossistema atual (Swagger/OpenAPI).
- O uso de variáveis de ambiente garante **portabilidade** e **reprodutibilidade**.
- O ambiente Docker foi pensado para evitar dependência do sistema operacional local.

---

Projeto desenvolvido como parte de um **Tech Challenge**, com foco em aprendizado profundo e aplicação de boas práticas de engenharia de software.