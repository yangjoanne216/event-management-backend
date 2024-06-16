/*Type Event*/
INSERT INTO type_event (name)
VALUES ('Conference');
INSERT INTO type_event (name)
VALUES ('Workshop');
INSERT INTO type_event (name)
VALUES ('Meetup');

/*Location*/
delete
from location;
INSERT INTO location (name)
VALUES ('Strasbourg');
INSERT INTO location (name)
VALUES ('Grenoble');
INSERT INTO location (name)
VALUES ('Paris');

/*Users*/
INSERT INTO users (email, password, lastname, firstname, avatar)
VALUES ('john.doe@example.com', 'password123', 'Doe', 'John',
        'https://example.com/avatar/johndoe.png');
INSERT INTO users (email, password, lastname, firstname, avatar)
VALUES ('jane.smith@example.com', 'password456', 'Smith', 'Jane',
        'https://example.com/avatar/janesmith.png');

/*Eventes*/
INSERT INTO event (title, id_organizer, description, start_time, end_time, id_type_event,
                   type_location, image,
                   id_location)
VALUES ('Annual Tech Conference', '676d15ad-626c-41a9-bd5b-40770d6a6509',
        'A conference for all tech lovers.', '2024-08-15 09:00:00',
        '2024-08-15 17:00:00', '1195d9af-1e61-40bf-a3ce-82d5a5eaa8e7', 'ONSITE',
        'https://example.com/images/event1.png', '02fc1d55-0b82-41e8-8a95-3b88b366d3b2');

INSERT INTO event (title, id_organizer, description, start_time, end_time, id_type_event,
                   type_location, image,
                   id_location)
VALUES ('Web Development Workshop', 'd9f9bf91-2139-4c25-90f6-aee50e2d6180',
        'Learn about the latest in web technology.',
        '2024-08-20 10:00:00', '2024-08-20 15:00:00', '1df5ed3f-f556-4116-b175-3b2c7db7a25d',
        'ONLINE',
        'https://example.com/images/event2.png', null);

/*Participations*/
INSERT INTO participation (id_event, id_user)
VALUES ('4f34ea9d-a7a9-4db0-8d87-cc5c52b42b14', '676d15ad-626c-41a9-bd5b-40770d6a6509');
INSERT INTO participation (id_event, id_user)
VALUES ('2bd3f23e-b65e-4d5c-bad4-99be9b1c2976', 'd9f9bf91-2139-4c25-90f6-aee50e2d6180');

/*Feedbacks*/
INSERT INTO feedback (id_event, id_user, date, content, note)
VALUES ('4f34ea9d-a7a9-4db0-8d87-cc5c52b42b14', '676d15ad-626c-41a9-bd5b-40770d6a6509',
        '2024-08-15 18:00:00', 'Great conference with insightful talks.', 5);

INSERT INTO feedback (id_event, id_user, date, content, note)
VALUES ('2bd3f23e-b65e-4d5c-bad4-99be9b1c2976', 'd9f9bf91-2139-4c25-90f6-aee50e2d6180',
        '2024-08-20 16:00:00', 'Very informative workshop on web tech.', 4);






