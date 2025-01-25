# FoodRecommender

**FoodRecommender** is a Java-based Spring application designed to recommend recipes based on the ingredients you have. The project leverages data from over 10,000 recipes, with recipe recommendations powered by Elasticsearch.

---

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Technologies Used](#technologies-used)
4. [File Structure](#file-structure)
5. [Installation](#installation)
6. [Usage](#usage)


---

## Introduction
The **FoodRecommender** project allows users to input ingredients they have at hand and receive personalized recipe recommendations. By utilizing Elasticsearch for efficient data retrieval and search capabilities, the application provides highly relevant suggestions from a database of over 10,000 recipes. This project is ideal for anyone looking to reduce food waste and get creative with their cooking.

---

## Features
- **Ingredient-based Recipe Search**: Input a list of ingredients, and the app will recommend recipes that match.
- **Efficient Search with Elasticsearch**: High-speed, scalable search optimized for large datasets.
- **Recipe Crawling**: Data sourced from over 10,000 recipes through automated web scraping.
- **Spring Boot Backend**: Backend framework.
- **Docker Support**: Easy deployment with Docker.

---

## Technologies Used
- **Java** (Spring Boot): Backend development
- **Elasticsearch**: Recipe search and recommendation engine
- **Maven**: Build and dependency management
- **Docker**: Containerization for deployment
- **Travis CI**: Continuous integration
- **HTML & JSON**: Data storage and communication

---

## File Structure
```
FoodRecommender/
├── .gitignore             # Git configuration
├── .travis.yml            # Travis CI configuration
├── Dockerfile             # Docker setup
├── build.sh               # Build script
├── pom.xml                # Maven dependencies and project config
└── src/
    ├── main/
    │   ├── java/          # Main application source code
    │   ├── resources/     # Configuration files (application.yml)
    │   └── webapp/        # Web resources (if applicable)
    └── test/              # Unit tests
```

---

## Installation

### Prerequisites
- Java 8 or later
- Maven
- Docker
- Elasticsearch (running locally or hosted)

### Steps
1. **Clone the repository**:
   ```bash
   git clone https://github.com/yeonwan/FoodRecommender.git
   cd FoodRecommender
   ```

2. **Set up Elasticsearch**:
   - Install Elasticsearch locally or use a hosted service.
   - Configure `application.yml` with your Elasticsearch endpoint.

3. **Build the project**:
   ```bash
   mvn clean install
   ```

4. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

5. **(Optional) Run with Docker**:
   - Build the Docker image:
     ```bash
     docker build -t freshfood-server .
     ```
   - Run the Docker container:
     ```bash
     docker run -p 8080:8080 freshfood-server
     ```

---

## Usage

1. **Input Ingredients**:
   - Use the provided API endpoint or frontend (if integrated) to submit a list of ingredients.

2. **Get Recipe Recommendations**:
   - The application will return a list of recipes matching the input ingredients, ranked by relevance.

3. **Explore Recipes**:
   - View detailed instructions and ingredients for each recommended recipe.

---

