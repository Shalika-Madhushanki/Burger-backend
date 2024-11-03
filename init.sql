-- Create Burger Table
CREATE TABLE Burger (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        price DOUBLE PRECISION NOT NULL,
                        weight INT NOT NULL,
                        is_available BOOLEAN DEFAULT TRUE,
                        image_url VARCHAR(255),
                        is_vegetarian BOOLEAN DEFAULT FALSE,
                        description VARCHAR(500)
);

-- Create Ingredients Table
CREATE TABLE burger_ingredients (
                                    burger_id BIGINT REFERENCES Burger(id),
                                    ingredient VARCHAR(255),
                                    PRIMARY KEY (burger_id, ingredient)
);

-- Create Allergens Table
CREATE TABLE burger_allergens (
                                  burger_id BIGINT REFERENCES Burger(id),
                                  allergen VARCHAR(255),
                                  PRIMARY KEY (burger_id, allergen)
);

-- Insert Burger Data
INSERT INTO Burger (name, price, weight, is_available, image_url, is_vegetarian, description)
VALUES
('Big Burger', 9.99, 180, TRUE, 'https://example.com/images/big-burger.jpg', FALSE, 'A delicious burger with double cheese and crispy bacon.'),
('Small Burger', 5.99, 140, FALSE, 'https://example.com/images/small-burger.jpg', TRUE, 'A small veggie burger packed with flavor.'),
('Medium Burger', 7.99, 120, TRUE, 'https://example.com/images/medium-burger.jpg', FALSE, 'A medium-sized burger with fresh ingredients.');

-- Insert Ingredients for Big Burger
INSERT INTO burger_ingredients (burger_id, ingredient) VALUES
(1, 'Beef Patty'),
(1, 'Cheese'),
(1, 'Lettuce'),
(1, 'Tomato'),
(1, 'Pickles'),
(1, 'Bacon');

-- Insert Allergens for Big Burger
INSERT INTO burger_allergens (burger_id, allergen) VALUES
(1, 'Gluten'),
(1, 'Dairy');

-- Insert Ingredients for Small Burger
INSERT INTO burger_ingredients (burger_id, ingredient) VALUES
(2, 'Veggie Patty'),
(2, 'Lettuce'),
(2, 'Tomato'),
(2, 'Pickles');

-- Insert Allergens for Small Burger
INSERT INTO burger_allergens (burger_id, allergen) VALUES
(2, 'Gluten');

-- Insert Ingredients for Medium Burger
INSERT INTO burger_ingredients (burger_id, ingredient) VALUES
(3, 'Chicken Patty'),
(3, 'Cheese'),
(3, 'Lettuce'),
(3, 'Tomato'),
(3, 'Pickles');

-- Insert Allergens for Medium Burger
INSERT INTO burger_allergens (burger_id, allergen) VALUES
(3, 'Gluten'),
(3, 'Dairy');

-- Create Drink Table
CREATE TABLE Drink (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       price DOUBLE PRECISION NOT NULL,
                       description VARCHAR(500),
                       image_url VARCHAR(255)
);

-- Insert Drink Data
INSERT INTO Drink (name, price, description, image_url)
VALUES
('Long Island Ice Tea', 4.99, 'A refreshing iced tea with a twist.', 'https://example.com/images/long-island-ice-tea.jpg'),
('Orange Juice', 2.99, 'Freshly squeezed orange juice.', 'https://example.com/images/orange-juice.jpg'),
('Coffee', 1.99, 'A cup of freshly brewed coffee.', 'https://example.com/images/coffee.jpg');
