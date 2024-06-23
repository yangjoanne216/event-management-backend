-- 1. 插入类型事件
/*Type Event*/
INSERT INTO type_event (id_type, name)
VALUES ('f7fa6aba-8d66-4199-b900-ffbe0c7f0eed', 'Conference');
INSERT INTO type_event (id_type, name)
VALUES ('ed1ed0f6-1980-4734-9981-7f9d0bc1c8b8', 'Workshop');
INSERT INTO type_event (id_type, name)
VALUES ('56307841-2b00-4f1f-bd1f-3ff17570ccb7', 'Meetup');

-- 2. 插入位置
/*Location*/
INSERT INTO location (id_city, name)
VALUES ('567fc524-2ba3-4430-95de-a37e923c7f81', 'Strasbourg');
INSERT INTO location (id_city, name)
VALUES ('98f2de8b-7938-4751-97f1-b5a29cd5b03b', 'Grenoble');
INSERT INTO location (id_city, name)
VALUES ('efb63b10-266d-4172-9b38-16866f4144be', 'Paris');

-- 3. 插入用户
/*Users*/
INSERT INTO users (id_user, email, password, lastname, firstname, avatar)
VALUES ('a67a6a4a-2196-42f8-8a06-63a7e66c71a6', 'john.doe@example.com', 'password123', 'Doe',
        'John', 'https://example.com/avatar/johndoe.png');
INSERT INTO users (id_user, email, password, lastname, firstname, avatar)
VALUES ('b221c140-7a90-4036-a64d-3f141b710d7f', 'jane.smith@example.com', 'password456', 'Smith',
        'Jane', 'https://example.com/avatar/janesmith.png');
INSERT INTO users (id_user, email, password, lastname, firstname, avatar)
VALUES ('58bdba14-9cec-4f39-bc27-43a01afef3ae', 'yangjoanne216@gmail.com', 'password123', 'Yang',
        'Yang', 'https://example.com/avatar/yangyang.png');
INSERT INTO users(id_user, email, password, lastname, firstname, avatar)
VALUES ('5e56d063-98fe-4b73-ab44-5467ed73b159', 'binh-minh.nguyen@dauphine.com', 'password456',
        'Nguyen', 'Minh', 'https://example.com/avatar/minhnguyen.png');


-- 4. 插入事件
/*Events*/
/*Annual Tech Conference- Organizer: Yang Yang, id_type_event: Conference, id_location: Paris */
INSERT INTO event (id_event, title, id_organizer, description, start_time, end_time, id_type_event,
                   type_location, image, id_location)
VALUES ('c8172fa3-02ba-440c-a01a-ef0f48201ef1', 'Annual Tech Conference',
        '58bdba14-9cec-4f39-bc27-43a01afef3ae', 'A conference for all tech lovers.',
        '2024-06-12 09:00:00', '2024-06-12 17:00:00', 'f7fa6aba-8d66-4199-b900-ffbe0c7f0eed',
        'ONSITE', 'https://example.com/images/event1.png', 'efb63b10-266d-4172-9b38-16866f4144be');

/*Web Development Workshop'-Organizer: John Doe, id_type_event: Workshop, id_location: null */
INSERT INTO event (id_event, title, id_organizer, description, start_time, end_time, id_type_event,
                   type_location, image, id_location)
VALUES ('d35b7712-4657-40b1-8654-410b1110d361', 'Web Development Workshop',
        'a67a6a4a-2196-42f8-8a06-63a7e66c71a6', 'Learn about the latest in web technology.',
        '2024-06-15 10:00:00', '2024-06-15 15:00:00', 'ed1ed0f6-1980-4734-9981-7f9d0bc1c8b8',
        'ONLINE', 'https://example.com/images/event2.png', null);

/*An Usual Meetup- Organizer: Jane Smith, id_type_event: Workshop, id_location: Grenoble */
INSERT INTO event (id_event, title, id_organizer, description, start_time, end_time, id_type_event,
                   type_location, image, id_location)
VALUES ('6a499e16-b42a-4d25-bfb4-7a0c2906f7d5', 'An Usual Meetup',
        'b221c140-7a90-4036-a64d-3f141b710d7f', 'Talk about the future project',
        '2024-08-16 10:00:00', '2024-08-16 15:00:00', 'ed1ed0f6-1980-4734-9981-7f9d0bc1c8b8',
        'HYBRID', 'https://example.com/images/event3.png', '98f2de8b-7938-4751-97f1-b5a29cd5b03b');

/*Data Science Conference-Organizer: Jane Smith, id_type_event: Conference, id_location: Paris */
INSERT INTO event (id_event, title, id_organizer, description, start_time, end_time, id_type_event,
                   type_location, image, id_location)
VALUES ('fa2b8910-2f08-4a9f-8c2f-e5b10e2f3b21', 'Data Science Conference',
        'b221c140-7a90-4036-a64d-3f141b710d7f', 'Exploring data science techniques.',
        '2024-09-01 09:00:00', '2024-09-01 17:00:00', 'f7fa6aba-8d66-4199-b900-ffbe0c7f0eed',
        'ONSITE', 'https://example.com/images/event4.png', 'efb63b10-266d-4172-9b38-16866f4144be');

/*Machine Learning Workshop-Organizer: Yang Yang, id_type_event: Workshop, id_location: null */
INSERT INTO event (id_event, title, id_organizer, description, start_time, end_time, id_type_event,
                   type_location, image, id_location)
VALUES ('ebf6c970-9147-4e89-8a5f-530ca5cb2b1f', 'Machine Learning Workshop',
        '58bdba14-9cec-4f39-bc27-43a01afef3ae', 'Hands-on machine learning workshop.',
        '2024-09-15 10:00:00', '2024-09-15 15:00:00', 'ed1ed0f6-1980-4734-9981-7f9d0bc1c8b8',
        'ONLINE', 'https://example.com/images/event5.png', null);

/*AI in Healthcare Meetup -Organizer: Minh Nguyen, id_type_event: Meetup, id_location: Strasbourg */
INSERT INTO event (id_event, title, id_organizer, description, start_time, end_time, id_type_event,
                   type_location, image, id_location)
VALUES ('a81e5b58-61d3-4427-bd17-2fd816a2a7e8', 'AI in Healthcare Meetup',
        '5e56d063-98fe-4b73-ab44-5467ed73b159', 'Discussing AI applications in healthcare.',
        '2024-10-10 10:00:00', '2024-10-10 15:00:00', '56307841-2b00-4f1f-bd1f-3ff17570ccb7',
        'HYBRID', 'https://example.com/images/event6.png', '567fc524-2ba3-4430-95de-a37e923c7f81');


-- 5. 插入参与记录（避免组织者在参与表中）
/*Participations*/
/* Web Development Workshop - Minh Nguyen */
INSERT INTO participation (id_event, id_user)
VALUES ('d35b7712-4657-40b1-8654-410b1110d361', '5e56d063-98fe-4b73-ab44-5467ed73b159');
/* An Usual Meetup - Minh Nguyen */
INSERT INTO participation (id_event, id_user)
VALUES ('6a499e16-b42a-4d25-bfb4-7a0c2906f7d5', '5e56d063-98fe-4b73-ab44-5467ed73b159');
/* Annual Tech Conference - Jane Smith */
INSERT INTO participation (id_event, id_user)
VALUES ('c8172fa3-02ba-440c-a01a-ef0f48201ef1', 'b221c140-7a90-4036-a64d-3f141b710d7f');
/* Data Science Conference - John Doe */
INSERT INTO participation (id_event, id_user)
VALUES ('fa2b8910-2f08-4a9f-8c2f-e5b10e2f3b21', 'a67a6a4a-2196-42f8-8a06-63a7e66c71a6');
/* Machine Learning Workshop - Minh Nguyen */
INSERT INTO participation (id_event, id_user)
VALUES ('ebf6c970-9147-4e89-8a5f-530ca5cb2b1f', '5e56d063-98fe-4b73-ab44-5467ed73b159');
/* AI in Healthcare Meetup - Jane Smith */
INSERT INTO participation (id_event, id_user)
VALUES ('a81e5b58-61d3-4427-bd17-2fd816a2a7e8', 'b221c140-7a90-4036-a64d-3f141b710d7f');
/* AI in Healthcare Meetup - Yang YANG */
INSERT INTO participation (id_event, id_user)
VALUES ('a81e5b58-61d3-4427-bd17-2fd816a2a7e8', '58bdba14-9cec-4f39-bc27-43a01afef3ae');


-- 6. 插入反馈（确保反馈日期在今天之前并且在事件结束之后，只有参与者可以给出反馈）
/*Feedbacks*/
/* Annual Tech Conference - Jane Smith */
INSERT INTO feedback (id_event, id_user, date, content, score)
VALUES ('c8172fa3-02ba-440c-a01a-ef0f48201ef1', 'b221c140-7a90-4036-a64d-3f141b710d7f',
        '2024-06-14 18:00:00', 'Great conference with insightful talks.', 5);
/* Web Development Workshop - Minh Nguyen */
INSERT INTO feedback (id_event, id_user, date, content, score)
VALUES ('d35b7712-4657-40b1-8654-410b1110d361', '5e56d063-98fe-4b73-ab44-5467ed73b159',
        '2024-06-15 16:00:00', 'Very informative workshop on web tech.', 4);

