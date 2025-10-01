# 📦 Projeto Spring Boot com Redis - Cache de Produtos

Este projeto consiste em uma aplicação **Spring Boot** que implementa um CRUD simples de produtos com cache utilizando **Redis**.
O objetivo é demonstrar o uso de cache para melhorar a performance das consultas de produtos por `id`.

---

## 🚀 Funcionalidades

* **Entidade Produto**

  * `id (Long)`
  * `nome (String)`
  * `preco (BigDecimal)`

* **Endpoints REST**

  * `GET /produtos/{id}` → Consulta um produto por `id`.

    * Caso o produto esteja em cache, o retorno vem diretamente do Redis.
    * Caso contrário, consulta no banco de dados e armazena o resultado no cache.

  * `PUT /produtos/atualizar/{id}` → Atualiza o preço de um produto.

    * Após a atualização, o cache correspondente é **invalidado** ou **atualizado**.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17+**
* **Spring Boot**
* **Spring Data JPA**
* **Spring Data Redis**
* **Redis** (Cache)
* **H2 Database** (para testes locais)

---

## ⚙️ Configuração

### Pré-requisitos

* Java 17 ou superior instalado
* Redis em execução (local ou em container)

  * Exemplo com Docker:

    ```bash
    docker run --name redis -p 6379:6379 -d redis
    ```

### Arquivo `application.properties`

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update

spring.redis.host=localhost
spring.redis.port=6379
```

---

## ▶️ Como Executar

1. Clone este repositório:

   ```bash
   git clone https://github.com/seu-usuario/nome-do-repo.git
   ```
2. Acesse a pasta do projeto:

   ```bash
   cd nome-do-repo
   ```
3. Compile e rode a aplicação:

   ```bash
   mvn spring-boot:run
   ```

---

## 📡 Exemplos de Uso

### Criar um produto

```http
POST /produtos
Content-Type: application/json

{
  "nome": "Notebook",
  "preco": 3500.00
}
```

### Consultar produto (com cache)

```http
GET /produtos/1
```

### Atualizar preço de um produto

```http
PUT /produtos/atualizar/1
Content-Type: application/json

{
  "preco": 3999.99
}
```

---

## 📖 Observações

* Caso o produto esteja no **Redis**, o retorno será imediato sem acessar o banco.
* Após a atualização de um produto, o cache é **removido** ou **atualizado**, garantindo consistência.

---

## 👨‍💻 Autor

Desenvolvido por **Vinicius Leal de Melo**
