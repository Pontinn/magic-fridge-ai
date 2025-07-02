# 🍳 MagicFridgeAI

O MagicFridgeAI é uma aplicação de backend construída com Spring Boot que transforma a lista de ingredientes da sua geladeira em receitas completas e bem-humoradas, com direito a uma imagem do prato gerada por IA.

A aplicação permite que você gerencie uma lista de alimentos e, com um único comando, solicita à API da OpenAI que crie uma receita usando apenas os ingredientes disponíveis. Em seguida, ela gera uma imagem correspondente para a receita criada.

## ✨ Funcionalidades

* **Gerenciamento de Ingredientes**: Operações CRUD completas para adicionar, listar, atualizar e remover itens alimentares do seu inventário.
* **Geração de Receitas com IA**: Utiliza o modelo `gpt-4.1` da OpenAI para criar receitas a partir dos ingredientes disponíveis. As instruções são geradas com um toque de humor, como se fossem escritas por um chef renomado.
* **Geração de Imagens com IA**: Após criar a receita, a aplicação utiliza o modelo `dall-e-3` para gerar uma imagem realista do prato finalizado.
* **API RESTful**: Endpoints bem definidos para interagir com o sistema, seja para gerenciar ingredientes ou para gerar uma nova receita.
* **Migração de Banco de Dados**: Utiliza o Flyway para gerenciar a evolução do esquema do banco de dados de forma automática e versionada.

## 🛠️ Tecnologias Utilizadas

* **Backend**: Java 21 & Spring Boot 3.5.0
* **Acesso a Dados**: Spring Data JPA
* **Banco de Dados**: H2 (escopo de execução), com esquema gerenciado pelo Flyway.
* **Comunicação Reativa**: Spring WebFlux `WebClient` para fazer chamadas para a API da OpenAI.
* **Inteligência Artificial**:
    * Texto: OpenAI `gpt-4.1`
    * Imagem: OpenAI `dall-e-3`
* **Build**: Apache Maven
* **Utilitários**: Lombok

## 🚀 Como Começar

**Pré-requisitos:**

* Java 21 ou superior
* Apache Maven
* Uma chave de API da OpenAI

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/Pontinn/magic-fridge-ai.git](https://github.com/Pontinn/magic-fridge-ai.git)
    cd magic-fridge-ai
    ```
   
2.  **Configure as Variáveis de Ambiente:**
    A aplicação requer que as seguintes variáveis de ambiente sejam definidas:
    * `API_KEY`: Sua chave de API secreta da OpenAI.
    * `DATABASE_URL`: A URL de conexão JDBC para o seu banco de dados (por exemplo, `jdbc:h2:mem:magicfridgedb` para um banco de dados em memória).
    * `DATABASE_USERNAME`: O nome de usuário para o banco de dados.
    * `DATABASE_PASSWORD`: A senha para o banco de dados.

3.  **Execute a aplicação com o Maven:**
    ```bash
    mvn spring-boot:run
    ```
   
    A aplicação estará disponível em `http://localhost:8080`.

## 🚧 Em Desenvolvimento

Este projeto está em desenvolvimento contínuo. Novas funcionalidades, incluindo uma interface de frontend, serão adicionadas em breve para aprimorar a experiência do usuário.

## 🔌 Endpoints da API

A URL base para os endpoints é `http://localhost:8080`.

### Gerenciamento de Alimentos (`/food`)

* **`POST /food/save`**: Adiciona um novo item alimentar.
    * **Corpo da Requisição**:
        ```json
        {
            "name": "Tomate",
            "foodCategory": "FRUTAS",
            "quantity": 5,
            "expirationDate": "2025-12-31"
        }
        ```
       
    * **Categorias de Alimentos Disponíveis**: `VEGETAIS`, `FRIOS`, `LATICINIOS`, `CARNES`, `CEREAIS`, `FRUTAS`, `BEBIDAS`, `LEGUMES`, `MOLHOS`, `ESPECIARIAS`, `GRAOS`, `MASSAS`, `FRUTOS_DO_MAR`, `DOCES`, `OUTROS`.

* **`GET /food/list`**: Lista todos os itens alimentares registrados.
* **`GET /food/list/{id}`**: Busca um item alimentar pelo seu ID.
* **`PUT /food/update`**: Atualiza um item alimentar existente.
* **`DELETE /food/delete`**: Deleta um item alimentar.

### Geração de Receita (`/recipe`)

* **`GET /recipe/generate`**: Inicia o processo de geração de receita e imagem com base nos itens cadastrados no banco de dados.
    * **Exemplo de Resposta de Sucesso**:
        ```
        Receita gerada: Título: A Tomatada do Chef Maluco ... [resto da receita] ...
        Imagem da receita: [https://url.da.imagem/gerada_pelo_dalle.png](https://url.da.imagem/gerada_pelo_dalle.png)
        ```
