CREATE SEQUENCE IF NOT EXISTS movies_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS reservations_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS screening_rooms_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS screening_seats_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS screenings_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS seats_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS ticket_types_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS tickets_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS vouchers_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE movies
(
    id       BIGINT       NOT NULL,
    title    VARCHAR(255) NOT NULL,
    duration INTERVAL     NOT NULL,
    CONSTRAINT pk_movies PRIMARY KEY (id)
);

CREATE TABLE reservations
(
    id               BIGINT                      NOT NULL,
    creation_time    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    customer_name    VARCHAR(255)                NOT NULL,
    customer_surname VARCHAR(255)                NOT NULL,
    voucher_id       BIGINT,
    CONSTRAINT pk_reservations PRIMARY KEY (id)
);

CREATE TABLE screening_rooms
(
    id   BIGINT       NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_screening_rooms PRIMARY KEY (id)
);

CREATE TABLE screening_seats
(
    id             BIGINT NOT NULL,
    screening_id   BIGINT NOT NULL,
    seat_id        BIGINT NOT NULL,
    reservation_id BIGINT,
    CONSTRAINT pk_screening_seats PRIMARY KEY (id)
);

CREATE TABLE screenings
(
    id                BIGINT                      NOT NULL,
    movie_id          BIGINT                      NOT NULL,
    screening_room_id BIGINT                      NOT NULL,
    screening_time    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_screenings PRIMARY KEY (id)
);

CREATE TABLE seats
(
    id                BIGINT  NOT NULL,
    screening_room_id BIGINT  NOT NULL,
    row               INTEGER NOT NULL,
    number            INTEGER NOT NULL,
    CONSTRAINT pk_seats PRIMARY KEY (id)
);

CREATE TABLE ticket_types
(
    id    BIGINT       NOT NULL,
    name  VARCHAR(255) NOT NULL,
    price DECIMAL      NOT NULL,
    CONSTRAINT pk_ticket_types PRIMARY KEY (id)
);

CREATE TABLE tickets
(
    id             BIGINT NOT NULL,
    reservation_id BIGINT NOT NULL,
    ticket_type_id BIGINT NOT NULL,
    CONSTRAINT pk_tickets PRIMARY KEY (id)
);

CREATE TABLE vouchers
(
    id     BIGINT       NOT NULL,
    code   VARCHAR(255) NOT NULL,
    type   VARCHAR(255) NOT NULL,
    amount INTEGER      NOT NULL,
    CONSTRAINT pk_vouchers PRIMARY KEY (id)
);

ALTER TABLE reservations
    ADD CONSTRAINT FK_RESERVATIONS_ON_VOUCHER FOREIGN KEY (voucher_id) REFERENCES vouchers (id);

ALTER TABLE screenings
    ADD CONSTRAINT FK_SCREENINGS_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES movies (id);

ALTER TABLE screenings
    ADD CONSTRAINT FK_SCREENINGS_ON_SCREENINGROOM FOREIGN KEY (screening_room_id) REFERENCES screening_rooms (id);

ALTER TABLE screening_seats
    ADD CONSTRAINT FK_SCREENING_SEATS_ON_RESERVATION FOREIGN KEY (reservation_id) REFERENCES reservations (id);

ALTER TABLE screening_seats
    ADD CONSTRAINT FK_SCREENING_SEATS_ON_SCREENING FOREIGN KEY (screening_id) REFERENCES screenings (id);

ALTER TABLE screening_seats
    ADD CONSTRAINT FK_SCREENING_SEATS_ON_SEAT FOREIGN KEY (seat_id) REFERENCES seats (id);

ALTER TABLE seats
    ADD CONSTRAINT FK_SEATS_ON_SCREENINGROOM FOREIGN KEY (screening_room_id) REFERENCES screening_rooms (id);

ALTER TABLE tickets
    ADD CONSTRAINT FK_TICKETS_ON_RESERVATION FOREIGN KEY (reservation_id) REFERENCES reservations (id);

ALTER TABLE tickets
    ADD CONSTRAINT FK_TICKETS_ON_TICKETTYPE FOREIGN KEY (ticket_type_id) REFERENCES ticket_types (id);