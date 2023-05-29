INSERT INTO movies
VALUES (nextval('movies_seq'), 'The Shawshank Redemption', '2:22:00'),
       (nextval('movies_seq'), 'The Dark Knight', '2:32:00'),
       (nextval('movies_seq'), 'The Green Mile', '3:09:00');

INSERT INTO screening_rooms
VALUES (nextval('screening_rooms_seq'), 'Alfa'),
       (nextval('screening_rooms_seq'), 'Beta'),
       (nextval('screening_rooms_seq'), 'Gamma');

INSERT INTO seats
VALUES (nextval('seats_seq'), 1, 1, 1),
       (nextval('seats_seq'), 1, 1, 2),
       (nextval('seats_seq'), 1, 1, 3),
       (nextval('seats_seq'), 1, 1, 4),
       (nextval('seats_seq'), 1, 1, 5),
       (nextval('seats_seq'), 1, 1, 6),
       (nextval('seats_seq'), 1, 1, 7),
       (nextval('seats_seq'), 1, 1, 8),
       (nextval('seats_seq'), 1, 1, 9),
       (nextval('seats_seq'), 1, 1, 10);

INSERT INTO ticket_types
VALUES (nextval('ticket_types_seq'), 'Adult', 25),
       (nextval('ticket_types_seq'), 'Student', 18),
       (nextval('ticket_types_seq'), 'Child', 12.50);

INSERT INTO vouchers
VALUES (nextval('vouchers_seq'), 'TICKET_50', 'PERCENTAGE', 50);

INSERT INTO screenings
VALUES (nextval('screenings_seq'), 1, 1, NOW() + INTERVAL '1 DAY'),
       (nextval('screenings_seq'), 51, 1, NOW() + INTERVAL '2 DAY'),
       (nextval('screenings_seq'), 1, 1, NOW() + INTERVAL '3 DAY');