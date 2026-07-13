# 🏨 Hotel API

API REST desenvolvida com **Java** e **Spring Boot** para gerenciamento de hóspedes, quartos e hospedagens.

O projeto foi desenvolvido com foco em praticar os principais conceitos do desenvolvimento Back-end utilizando Spring Boot, aplicando boas práticas de organização em camadas, persistência de dados com JPA/Hibernate, validações e documentação da API com Swagger.

---

## 📌 Funcionalidades

### 🛏️ Quartos

* Cadastrar quarto
* Listar todos os quartos
* Buscar quarto por ID
* Atualizar quarto
* Excluir quarto

---

### 👤 Hóspedes

* Cadastrar hóspede
* Listar todos os hóspedes
* Buscar hóspede por ID
* Atualizar hóspede
* Excluir hóspede

---

### 🏨 Hospedagens

* Realizar check-in
* Realizar check-out
* Listar hospedagens
* Buscar hospedagem por ID
* Buscar hospedagens por hóspede
* Buscar hospedagens por quarto

---

## 📋 Regras de negócio

* Não permite cadastrar dois quartos com o mesmo número.
* Não permite cadastrar dois hóspedes com o mesmo CPF.
* O check-in só pode ser realizado em quartos disponíveis.
* A data de saída deve ser posterior à data de entrada.
* O valor total da hospedagem é calculado automaticamente com base na quantidade de diárias.
* Ao realizar o check-in, o quarto passa para o status **OCUPADO**.
* Ao realizar o check-out, o quarto volta para o status **DISPONIVEL**.
* Uma hospedagem finalizada não pode receber um novo check-out.

---

## 🛠️ Tecnologias utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Bean Validation
* Lombok
* Swagger / OpenAPI
* Maven

---

## 📂 Estrutura do projeto

```text
src/main/java/com/maarimo/hotel
│
├── controller
├── dto
│   ├── request
│   └── response
├── entity
├── exception
├── repository
├── service
```

---

## 🗄️ Modelo da aplicação

### Quarto

| Campo       | Tipo         |
| ----------- | ------------ |
| id          | Long         |
| numero      | Integer      |
| tipo        | String       |
| valorDiaria | BigDecimal   |
| status      | StatusQuarto |

### Hospede

| Campo    | Tipo   |
| -------- | ------ |
| id       | Long   |
| nome     | String |
| cpf      | String |
| telefone | String |
| email    | String |

### Hospedagem

| Campo       | Tipo             |
| ----------- | ---------------- |
| id          | Long             |
| dataEntrada | LocalDate        |
| dataSaida   | LocalDate        |
| valorTotal  | BigDecimal       |
| status      | StatusHospedagem |
| hospede     | Hospede          |
| quarto      | Quarto           |

---

## 🔗 Endpoints

### Quartos

| Método | Endpoint        |
| ------ | --------------- |
| POST   | `/quartos`      |
| GET    | `/quartos`      |
| GET    | `/quartos/{id}` |
| PUT    | `/quartos/{id}` |
| DELETE | `/quartos/{id}` |

---

### Hóspedes

| Método | Endpoint         |
| ------ | ---------------- |
| POST   | `/hospedes`      |
| GET    | `/hospedes`      |
| GET    | `/hospedes/{id}` |
| PUT    | `/hospedes/{id}` |
| DELETE | `/hospedes/{id}` |

---

### Hospedagens

| Método | Endpoint                      |
| ------ | ----------------------------- |
| POST   | `/hospedagens/check-in`       |
| PUT    | `/hospedagens/{id}/check-out` |
| GET    | `/hospedagens`                |
| GET    | `/hospedagens/{id}`           |
| GET    | `/hospedagens/hospede/{id}`   |
| GET    | `/hospedagens/quarto/{id}`    |

---

## 📖 Documentação da API

Após iniciar a aplicação, a documentação pode ser acessada em:

```text
http://localhost:8080/swagger-ui.html
```

---

## 🎯 Objetivo do projeto

Este projeto foi desenvolvido para praticar conceitos fundamentais do ecossistema Spring Boot, incluindo:

* Desenvolvimento de APIs REST
* Arquitetura em camadas
* CRUD completo
* Relacionamentos com JPA/Hibernate
* Validações com Bean Validation
* Tratamento global de exceções
* Regras de negócio
* Documentação com Swagger

---

## 👨‍💻 Autor

**Matheus Amorim**

Desenvolvedor Back-end Java em formação, com foco em construção de APIs REST utilizando Java, Spring Boot e PostgreSQL.

Sempre buscando evoluir por meio de projetos práticos e boas práticas de desenvolvimento.
