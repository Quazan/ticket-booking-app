INSERT INTO ticket_types
VALUES (nextval('ticket_types_seq'), 'Adult', 25, 29),
       (nextval('ticket_types_seq'), 'Student', 18, 22),
       (nextval('ticket_types_seq'), 'Child', 12.50, 16.5);

INSERT INTO vouchers
VALUES (nextval('vouchers_seq'), 'TICKET_50', 'PERCENTAGE', 50);

INSERT INTO movies
VALUES (nextval('movies_seq'), 'Avengers: Infinity War', '2:29:00'),
       (nextval('movies_seq'), 'Gladiator', '2:35:00'),
       (nextval('movies_seq'), 'The Green Mile', '3:09:00');

INSERT INTO screening_rooms
VALUES (nextval('screening_rooms_seq'), 'Alfa'),
       (nextval('screening_rooms_seq'), 'Beta'),
       (nextval('screening_rooms_seq'), 'Gamma');

INSERT INTO seats
SELECT nextval('seats_seq'), sr.id, row, seatNumber
FROM screening_rooms sr
         CROSS JOIN generate_series(1, 5) row
         CROSS JOIN generate_series(1, 5) seatNumber;

INSERT INTO screenings
VALUES (nextval('screenings_seq'), 1, 1, '2023-06-09 12:30:00'),
       (nextval('screenings_seq'), 1, 1, '2023-06-09 17:00:00'),
       (nextval('screenings_seq'), 51, 51, '2023-06-09 13:30:00'),
       (nextval('screenings_seq'), 51, 51, '2023-06-09 18:00:00'),
       (nextval('screenings_seq'), 101, 101, '2023-06-10 10:00:00'),
       (nextval('screenings_seq'), 101, 101, '2023-06-10 15:00:00');

INSERT INTO screening_seats (id, screening_id, seat_id)
SELECT nextval('screening_seats_seq'), scr.id, s.id
FROM screenings scr
         JOIN seats s on scr.screening_room_id = s.screening_room_id;