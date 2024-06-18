/*Type Event*/
INSERT INTO type_event (id_type, name)
VALUES ('f7fa6aba-8d66-4199-b900-ffbe0c7f0eed', 'Conference');
INSERT INTO type_event (id_type, name)
VALUES ('ed1ed0f6-1980-4734-9981-7f9d0bc1c8b8', 'Workshop');
INSERT INTO type_event (id_type, name)
VALUES ('56307841-2b00-4f1f-bd1f-3ff17570ccb7', 'Meetup');

/*Location*/
INSERT INTO location (id_city, name)
VALUES ('567fc524-2ba3-4430-95de-a37e923c7f81', 'Strasbourg');
INSERT INTO location (id_city, name)
VALUES ('98f2de8b-7938-4751-97f1-b5a29cd5b03b', 'Grenoble');
INSERT INTO location (id_city, name)
VALUES ('efb63b10-266d-4172-9b38-16866f4144be', 'Paris');

/*Users*/
INSERT INTO users (id_user, email, password, lastname, firstname, avatar)
VALUES ('a67a6a4a-2196-42f8-8a06-63a7e66c71a6', 'john.doe@example.com', 'password123', 'Doe',
        'John',
        'https://example.com/avatar/johndoe.png');
INSERT INTO users (id_user, email, password, lastname, firstname, avatar)
VALUES ('b221c140-7a90-4036-a64d-3f141b710d7f', 'jane.smith@example.com', 'password456', 'Smith',
        'Jane',
        'https://example.com/avatar/janesmith.png');
INSERT INTO users (id_user, email, password, lastname, firstname, avatar)
VALUES ('58bdba14-9cec-4f39-bc27-43a01afef3ae', 'yangjoanne216@gmail.com', 'password123', 'Yang',
        'Yang',
        'https://example.com/avatar/yangyang.png');
INSERT INTO users(id_user, email, password, lastname, firstname, avatar)
VALUES ('5e56d063-98fe-4b73-ab44-5467ed73b159', 'binh-minh.nguyen@dauphine.com', 'password456',
        'Nguyen', 'Minh', 'https://example.com/avatar/minhnguyen.png');


/*Eventes*/
/*Organizer; yangyang,id_type_event:conference,id_location:paris*/
INSERT INTO event (id_event, title, id_organizer, description, start_time, end_time, id_type_event,
                   type_location, image,
                   id_location)
VALUES ('c8172fa3-02ba-440c-a01a-ef0f48201ef1', 'Annual Tech Conference',
        '58bdba14-9cec-4f39-bc27-43a01afef3ae',
        'A conference for all tech lovers.', '2024-08-15 09:00:00',
        '2024-08-15 17:00:00', 'f7fa6aba-8d66-4199-b900-ffbe0c7f0eed', 'ONSITE',
        'https://example.com/images/event1.png', 'efb63b10-266d-4172-9b38-16866f4144be');

/*id_event,Organizer:DoeJonh,id_type_event:workshop,id_location:null*/
INSERT INTO event (id_event, title, id_organizer, description, start_time, end_time, id_type_event,
                   type_location, image,
                   id_location)
VALUES ('d35b7712-4657-40b1-8654-410b1110d361', 'Web Development Workshop',
        'a67a6a4a-2196-42f8-8a06-63a7e66c71a6',
        'Learn about the latest in web technology.',
        '2024-08-20 10:00:00', '2024-08-20 15:00:00', 'ed1ed0f6-1980-4734-9981-7f9d0bc1c8b8',
        'ONLINE',
        'https://example.com/images/event2.png', null);

/*id_event,Organizer:jane smith,id_type_event:workshop,id_location:null*/
INSERT INTO event (id_event, title, id_organizer, description, start_time, end_time, id_type_event,
                   type_location, image,
                   id_location)
VALUES ('6a499e16-b42a-4d25-bfb4-7a0c2906f7d5', 'an usual meetup',
        'b221c140-7a90-4036-a64d-3f141b710d7f',
        'Talk about the future project',
        '2024-08-16 10:00:00', '2024-08-16 15:00:00', 'ed1ed0f6-1980-4734-9981-7f9d0bc1c8b8',
        'HYBRID',
        'https://example.com/images/event3.png', '98f2de8b-7938-4751-97f1-b5a29cd5b03b');

/*Participations*/
/*Annual Tech Conference-YangYang*/
INSERT INTO participation (id_event, id_user)
VALUES ('c8172fa3-02ba-440c-a01a-ef0f48201ef1', '58bdba14-9cec-4f39-bc27-43a01afef3ae');
/*Annual Tech Conference-jane.smith*/
INSERT INTO participation (id_event, id_user)
VALUES ('c8172fa3-02ba-440c-a01a-ef0f48201ef1', 'b221c140-7a90-4036-a64d-3f141b710d7f');
/*Web Development Workshop-DoeJonh*/
INSERT INTO participation (id_event, id_user)
VALUES ('d35b7712-4657-40b1-8654-410b1110d361', 'a67a6a4a-2196-42f8-8a06-63a7e66c71a6');
/*Web Development Workshop-mingNguyen*/
INSERT INTO participation (id_event, id_user)
VALUES ('d35b7712-4657-40b1-8654-410b1110d361', '5e56d063-98fe-4b73-ab44-5467ed73b159');

/*Feedbacks //Todo only the participantes cans give the feedbacks*/
/*Annual Tech Conference-jane.smith*/
INSERT INTO feedback (id_event, id_user, date, content, score)
VALUES ('c8172fa3-02ba-440c-a01a-ef0f48201ef1', 'b221c140-7a90-4036-a64d-3f141b710d7f',
        '2024-08-15 18:00:00', 'Great conference with insightful talks.', 5);
/*Annual Tech Conference-YangYang*/
INSERT INTO feedback (id_event, id_user, date, content, score)
VALUES ('c8172fa3-02ba-440c-a01a-ef0f48201ef1', '58bdba14-9cec-4f39-bc27-43a01afef3ae',
        '2024-08-15 18:00:00', 'Great conference with insightful talks.', 4);
/*Web Development Workshop-mingNguyen*/
INSERT INTO feedback (id_event, id_user, date, content, score)
VALUES ('d35b7712-4657-40b1-8654-410b1110d361', '5e56d063-98fe-4b73-ab44-5467ed73b159',
        '2024-08-20 16:00:00', 'Very informative workshop on web tech.', 4);

