CREATE TABLE tb_food_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    food_category ENUM('VEGETABLES', 'COLD_CUTS', 'DAIRY', 'MEATS', 'CEREALS', 'FRUITS', 'BEVERAGES', 'LEGUMES', 'SAUCES', 'SPICES', 'GRAINS', 'PASTA', 'SEAFOOD', 'SWEETS', 'OTHERS') NOT NULL,
    quantity INT NOT NULL,
    expiration_date DATE NOT NULL
);
