create database ecommercedb;

use ecommercedb;


# _________________ [tables] _________________

create table Products(
	id int primary key auto_increment,
    title varchar(85) not null unique,
    description varchar(255) not null,
    imageAddr text not null,
    price double not null,
    Qnt int not null
);

select * from Products;


create table Users(
	id int primary key auto_increment,
    firstname varchar(55) not null,
    lastname varchar(55) not null,
    email varchar(55) not null unique,
    password varchar(55) not null,
    addr varchar(255) not null,
    phone varchar(20) not null,
	createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
	updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


create table ShoppingCartItems (
    id int primary key auto_increment,
    productId int not null,
    userId int not null,
    quantity int not null,
    foreign key (productId) references Products(id),
    foreign key (userId) references Users(id),
    unique key (productId, userId)
);





# _________________ [stored procedures] _________________

# used to register a new user to our Ecommerce store:
DELIMITER //
CREATE PROCEDURE registerNewUser(in firstname varchar(55),in lastname varchar(55),in email varchar(55),in password varchar(55), in addr varchar(255), in phone varchar(20))
BEGIN
    INSERT INTO Users (firstname, lastname, email, password, addr, phone)
	VALUES (firstname, lastname, email, password, addr, phone);
END //
DELIMITER ;


# used to indicate if the user is authenticate users:
DELIMITER //
CREATE PROCEDURE LoginUser(IN emailParam VARCHAR(55), IN passwordParam VARCHAR(55))
BEGIN
    DECLARE userCount INT;

    -- Check if the provided email and password match a user in the Users table
    SELECT COUNT(*) INTO userCount
    FROM Users
    WHERE email = emailParam AND password = passwordParam;

    -- Return a result indicating success (1) or failure (0)
    IF userCount > 0 THEN
        SELECT 1 AS loginResult; -- Success
    ELSE
        SELECT 0 AS loginResult; -- Failure
    END IF;
END //
DELIMITER ;



# used to add new product in out store:
DELIMITER //
CREATE PROCEDURE AddNewProduct(IN productTitle VARCHAR(85), IN des VARCHAR(255), IN imageUrl TEXT, IN cost double, IN qnt INT)
BEGIN
	insert into products(title,description,imageAddr,price,Qnt) values(productTitle,des,imageUrl,cost,qnt);
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE EditProduct(IN prodTitle text, IN prodDes varchar(255), IN prodPrice double, IN prodQnt int)
BEGIN
	update products
    set description=prodDes ,
    price=prodPrice ,
    Qnt=prodQnt
    where title= prodTitle;
END //
DELIMITER ;
drop procedure if exists EditProduct;


DELIMITER //
CREATE PROCEDURE DeleteProduct(in productTitle text)
BEGIN
	delete from products where title= productTitle; 
END //
DELIMITER ;


# used to get all products in products landing page:
DELIMITER //
CREATE PROCEDURE getAllProducts()
BEGIN
    SELECT * FROM Products ORDER BY title ASC;
END //
DELIMITER ;


# used to add a item/product to shopping cart:
DELIMITER //
CREATE PROCEDURE addProductToShoppingCart(in productTitle varchar(85),in userEmail varchar(55))
BEGIN
	declare productIdVar int;
    declare userIdVar int;
    
    set productIdVar=(select id from Products where title=productTitle);
    set userIdVar=(select id from Users where email=userEmail);
    
	INSERT INTO ShoppingCartItems(productId, userId, quantity)
	VALUES (productIdVar, userIdVar, 1);
END //
DELIMITER ;
DROP PROCEDURE IF EXISTS addProductToShoppingCart;

# used to increase/decrease item Qnt in shopping cart:
DELIMITER //
CREATE PROCEDURE editProductQntInShoppingCart(in productTitle varchar(85),in userEmail varchar(55),in productQnt int)
BEGIN
	declare productIdVar int;
    declare userIdVar int;
    
    set productIdVar=(select id from Products where title=productTitle);
    set userIdVar=(select id from Users where email=userEmail);
    
    update ShoppingCartItems set quantity=productQnt where productId=productIdVar and userId=userIdVar;
END //
DELIMITER ;
DROP PROCEDURE IF EXISTS editProductQntInShoppingCart;


DELIMITER //
CREATE PROCEDURE getUserShoppingCartItems(in userEmail varchar(55))
BEGIN
    declare userIdVar int;
    
    set userIdVar=(select id from Users where email=userEmail);
    
    select ShoppingCartItems.quantity,Products.title,Products.imageAddr,Products.price,(Products.price *  ShoppingCartItems.quantity) as Amount from Products
    inner join ShoppingCartItems on Products.id=ShoppingCartItems.productId where ShoppingCartItems.userId=userIdVar;
END //
DELIMITER ;
DROP PROCEDURE IF EXISTS getUserShoppingCartItems;


DELIMITER //
CREATE PROCEDURE removeUserShoppingCartItem(in productTitle varchar(85) ,in userEmail varchar(55))
BEGIN
	declare productIdVar int;
    declare userIdVar int;
    
    set productIdVar=(select id from Products where title=productTitle);
    set userIdVar=(select id from Users where email=userEmail);
    
    delete from ShoppingCartItems where userId=userIdVar and productId=productIdVar;
END //
DELIMITER ;
DROP PROCEDURE IF EXISTS removeUserShoppingCartItem;


# _________________ [stored procedures testing] _________________
call registerNewUser("FName" , "LName" ,"youremail@gmail.com","yourPassword","yourADDR","+0123456789");
select * from Users;

call LoginUser("youremail@gmail.com","yourPassword");

call getAllProducts();

call addProductToShoppingCart("Rocket League","youremail@gmail.com");
select * from ShoppingCartItems;

call editProductQntInShoppingCart("Rocket League","youremail@gmail.com",2);
select * from ShoppingCartItems;

call getUserShoppingCartItems("youremail@gmail.com");

call removeUserShoppingCartItem("Rocket League","youremail@gmail.com");

call AddNewProduct("Hoowel Smart Watch for Men","any","https://m.media-amazon.com/images/I/71oM+R9n1kL._AC_SY300_SX300_.jpg",22.00,5);
select * from products;

call deleteProduct("Amzhero Fitness Smart Watch with Alexa for Women");
select * from products where title= "aero Fitness Smart Watch with Alexa for Women";

call EditProduct("AMD Ryzen 9 5950X 16-core","16-core, 32-thread unlocked desktop processor",450.00,2);
select * from products where title="AMD Ryzen 9 5950X 16-core";


#------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
ALTER TABLE shoppingcartitems
DROP FOREIGN KEY shoppingcartitems_ibfk_1;

ALTER TABLE shoppingcartitems
ADD FOREIGN KEY (productId) REFERENCES products(id) ON DELETE CASCADE;
