# 🍳 MagicFridgeAI

MagicFridgeAI is a backend application built with Spring Boot that transforms your fridge's ingredient list into
complete, humorous recipes, complete with an AI-generated image of the dish.

The application allows you to manage a list of food items and, with a single command, requests the OpenAI API to create
a recipe using only the available ingredients. It then generates a corresponding image for the created recipe.

## ✨ Features

* **Ingredient Management**: Full CRUD operations to add, list, update, and remove food items from your inventory.
* **AI-Powered Recipe Generation**: Uses OpenAI's `gpt-4.1` model to create recipes from available ingredients. The
  instructions are generated with a touch of humor, as if written by a renowned chef.
* **AI-Powered Image Generation**: After creating the recipe, the application uses the `dall-e-3` model to generate a
  realistic image of the finished dish.
* **RESTful API**: Well-defined endpoints to interact with the system, whether for managing ingredients or generating a
  new recipe.
* **Database Migration**: Uses Flyway to manage the database schema evolution automatically and in a versioned manner.

## 🛠️ Technologies Used

* **Backend**: Java 21 & Spring Boot 3.5.0
* **Data Access**: Spring Data JPA
* **Database**: H2 (runtime scope), with schema managed by Flyway.
* **Reactive Communication**: Spring WebFlux `WebClient` for making calls to the OpenAI API.
* **Artificial Intelligence**:
    * Text: OpenAI `gpt-4.1`
    * Image: OpenAI `dall-e-3`
* **Build**: Apache Maven
* **Utilities**: Lombok

## 🚀 Getting Started

**Prerequisites:**

* Java 21 or higher
* Apache Maven
* An OpenAI API key

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/Pontinn/magic-fridge-ai.git](https://github.com/Pontinn/magic-fridge-ai.git)
   cd magic-fridge-ai
   ```

2. **Configure Environment Variables:**
   The application requires the following environment variables to be set:
    * `API_KEY`: Your secret OpenAI API key.
    * `DATABASE_URL`: The JDBC connection URL for your database (e.g., `jdbc:h2:mem:magicfridgedb` for an in-memory
      database).
    * `DATABASE_USERNAME`: The username for the database.
    * `DATABASE_PASSWORD`: The password for the database.

3. **Run the application with Maven:**
   ```bash
   mvn spring-boot:run
   ```
   The application will be available at `http://localhost:8080`.

## 🔌 API Endpoints

The base URL for the endpoints is `http://localhost:8080`.

### Food Management (`/food`)

* **`POST /food/save`**: Adds a new food item.
    * **Request Body**:
        ```json
        {
            "name": "Tomato",
            "foodCategory": "FRUITS",
            "quantity": 5,
            "expirationDate": "2025-12-31"
        }
        ```
    * **Available Food Categories**: `VEGETABLES`, `COLD_CUTS`, `DAIRY`, `MEATS`, `CEREALS`, `FRUITS`, `BEVERAGES`,
      `LEGUMES`, `SAUCES`, `SPICES`, `GRAINS`, `PASTA`, `SEAFOOD`, `SWEETS`, `OTHERS`.

* **`GET /food/list`**: Lists all registered food items.
* **`GET /food/list/{id}`**: Fetches a food item by its ID.
* **`PUT /food/update`**: Updates an existing food item.
* **`DELETE /food/delete`**: Deletes a food item.

### Recipe Generation (`/recipe`)

* **`GET /recipe/generate`**: Starts the recipe and image generation process based on the items registered in the
  database.
    * **Example Success Response**:
        ```
        Recipe generated: Title: The Mad Chef's Tomatada ... [rest of the recipe] ...
        Recipe image: [https://url.of.the.image/generated_by_dalle.png](https://url.of.the.image/generated_by_dalle.png)
        ```