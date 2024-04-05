CREATE TABLE plane(
	id_plane INT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(40) NOT NULL,
    capacity INT NOT NULL
);

SELECT * FROM plane ORDER BY plane.id_plane;
DELETE FROM plane WHERE plane.id_plane = 2;
UPDATE plane SET model = "Ambush 400", capacity = 60 WHERE id_plane = 3;
INSERT INTO plane(model,capacity) VALUES ("Airbus 330", 50);




CREATE TABLE flight(
	id_flight INT AUTO_INCREMENT PRIMARY KEY,
    destiny varchar(40) NOT NULL,
	departure_date DATE NOT NULL,
    departure_time TIME NOT NULL,
    id_plane INT ,
    FOREIGN KEY (id_plane) REFERENCES plane(id_plane) ON UPDATE CASCADE ON DELETE CASCADE
);

SELECT * FROM flight ORDER BY flight.id_flight;
INSERT INTO flight(destiny,departure_date,departure_time,id_plane) VALUES ("Mdellin", "2024-5-6", "13:30:00", 1);

SELECT * FROM flight
INNER JOIN plane on plane.id_plane = flight.id_plane
ORDER BY flight.id_flight ASC;

DELETE FROM flight WHERE id_flight = 3;
UPDATE flight SET destiny = "Bogota", departure_date = "2025-2-4", departure_time = "02:30:00",id_plane = 1 WHERE id_flight = 4;

SELECT * FROM flight WHERE destiny LIKE '%me%' ;

SELECT * FROM flight
INNER JOIN plane on plane.id_plane = flight.id_plane
WHERE destiny LIKE '%me%'
ORDER BY flight.id_flight ASC;

CREATE TABLE passenger(
	id_passenger INT AUTO_INCREMENT PRIMARY KEY,
    name varchar(40) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    identification_document VARCHAR(40) NOT NULL
);

SELECT * FROM passenger ORDER BY passenger.id_passenger;
INSERT INTO passenger(name,last_name,identification_document) VALUES ("Juan", "Loaiza", "556565");
DELETE FROM passenger WHERE id_passenger = 3;
UPDATE passenger SET name = "Fernando", last_name = "Vidales",identification_document ="2424242"  WHERE id_passenger = 2;

CREATE TABLE reservation(
	id_reservation INT AUTO_INCREMENT PRIMARY KEY,
    reservation_date DATE NOT NULL,
    seat VARCHAR(40) NOT NULL,
	id_passenger INT,
    id_flight INT,
    FOREIGN KEY (id_passenger) REFERENCES passenger(id_passenger) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (id_flight) REFERENCES flight(id_flight) ON UPDATE CASCADE ON DELETE CASCADE
);

SELECT * FROM reservation ORDER BY id_reservation ASC;

INSERT INTO reservation(reservation_date,seat,id_passenger,id_flight) VALUES ("2026-5-6", "5B", 1,5);

SELECT * FROM reservation
INNER JOIN passenger on passenger.id_passenger = reservation.id_passenger
INNER JOIN flight on flight.id_flight = reservation.id_flight
INNER JOIN plane on plane.id_plane = flight.id_plane
ORDER BY reservation.id_reservation ASC;

DELETE FROM reservation WHERE id_reservation = 18;

UPDATE reservation SET reservation_date = "2014-6-12", seat = "4A", id_passenger = 2, id_flight = 3 WHERE id_reservation = 1;

SELECT * FROM reservation
INNER JOIN passenger on passenger.id_passenger = reservation.id_passenger
INNER JOIN flight on flight.id_flight = reservation.id_flight
INNER JOIN plane on plane.id_plane = flight.id_plane
WHERE passenger.name LIKE '%juan%'
ORDER BY reservation.id_reservation ASC;


SELECT * FROM reservation
INNER JOIN passenger on passenger.id_passenger = reservation.id_passenger
INNER JOIN flight on flight.id_flight = reservation.id_flight
INNER JOIN plane on plane.id_plane = flight.id_plane
WHERE flight.id_flight = 6
ORDER BY reservation.id_reservation ASC;

