create table hotel (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name varchar(255),
    location varchar(255),
    rating INTEGER
);


create table room (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    roomNumber varchar(255),
    type varchar(255),
    price DOUBLE,
    hotelId INTEGER,
    FOREIGN KEY (hotelId) REFERENCES Hotel(id)
);
