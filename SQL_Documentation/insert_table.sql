/*User information : insérer par /register
id_user,email,password,lastname,firstname,avatar
28cd5eb1-5588-43fb-9e13-172ce94b557b,email1,$2a$10$KGLPMbPeHKxe/NA/xYX6lecuCuxrxtlMAdAOFDWI3gpzxZDitpmkK,anne,anne,string
3f227c58-055d-43c5-9713-8cf9d98c3a1a,email2,$2a$10$VoBY14w/6wcn9KN4cLMpYuL5GBPoTqJy.0vYdDeVfwuUukPYq5A0q,bnne,bnne,string
dc023029-7944-4114-afc2-93e6c6eea28f,email3,$2a$10$1layPknlp3FD5HazXgH7tuOS6rzSY/x4FrTnv1nERaz1nQfUCX2xa,cnne,cnne,string
e1aa51f7-6df6-4637-862e-15bf2fdc62a3,email4,$2a$10$2nFL.llRMqPB0md4LktsFeMUvxFcElshEcBaCPSx7YI0uoqFr51iC,dnne,dnne,string*/

/*Attention : password is password1 for email1, password2 for email2*/

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

------------------------------------------------------------
/*Event*/

/* AI in Healthcare Meetup - Organizer: anne (id_user: 28cd5eb1-5588-43fb-9e13-172ce94b557b), id_type_event: Meetup (id_type: 56307841-2b00-4f1f-bd1f-3ff17570ccb7), type_location: ONLINE, id_location: NULL */
INSERT INTO event (id_event, id_organizer, title, description, start_time, end_time, id_type_event,
                   type_location, image, id_location, score)
VALUES ('c1d2e3f4-5678-4a5b-cdef-1234567890ab', '28cd5eb1-5588-43fb-9e13-172ce94b557b',
        'AI in Healthcare Meetup', 'A discussion on the impact of AI in healthcare.',
        '2024-06-15 10:00:00', '2024-06-15 12:00:00', '56307841-2b00-4f1f-bd1f-3ff17570ccb7',
        'ONLINE', 'url_to_image', NULL, 0);

/* Cybersecurity Conference - Organizer: bnne (id_user: 3f227c58-055d-43c5-9713-8cf9d98c3a1a), id_type_event: Conference (id_type: f7fa6aba-8d66-4199-b900-ffbe0c7f0eed), type_location: ONSITE, id_location: Strasbourg (id_city: 567fc524-2ba3-4430-95de-a37e923c7f81) */
INSERT INTO event (id_event, id_organizer, title, description, start_time, end_time, id_type_event,
                   type_location, image, id_location, score)
VALUES ('d2e3f4e5-6789-4abc-adef-234567890abc', '3f227c58-055d-43c5-9713-8cf9d98c3a1a',
        'Cybersecurity Conference', 'Exploring the latest in cybersecurity technologies.',
        '2024-07-10 09:00:00', '2024-07-10 17:00:00', 'f7fa6aba-8d66-4199-b900-ffbe0c7f0eed',
        'ONSITE', 'url_to_image', '567fc524-2ba3-4430-95de-a37e923c7f81', 0);

/* Data Science Workshop - Organizer: cnne (id_user: dc023029-7944-4114-afc2-93e6c6eea28f), id_type_event: Workshop (id_type: ed1ed0f6-1980-4734-9981-7f9d0bc1c8b8), type_location: HYBRID, id_location: Grenoble (id_city: 98f2de8b-7938-4751-97f1-b5a29cd5b03b) */
INSERT INTO event (id_event, id_organizer, title, description, start_time, end_time, id_type_event,
                   type_location, image, id_location, score)
VALUES ('e3f4a5b6-7890-4ab1-cdef-34567890abcd', 'dc023029-7944-4114-afc2-93e6c6eea28f',
        'Data Science Workshop', 'A workshop on the latest data science techniques.',
        '2024-05-20 14:00:00', '2024-05-20 18:00:00', 'ed1ed0f6-1980-4734-9981-7f9d0bc1c8b8',
        'HYBRID', 'url_to_image', '98f2de8b-7938-4751-97f1-b5a29cd5b03b', 0);

/*Participation*/

/* Participation by anne (id_user: 28cd5eb1-5588-43fb-9e13-172ce94b557b) in Cybersecurity Conference (id_event: d2e3f4e5-6789-4abc-adef-234567890abc) */
INSERT INTO participation (id_event, id_user)
VALUES ('d2e3f4e5-6789-4abc-adef-234567890abc', '28cd5eb1-5588-43fb-9e13-172ce94b557b');

/* Participation by bnne (id_user: 3f227c58-055d-43c5-9713-8cf9d98c3a1a) in Data Science Workshop (id_event: e3f4a5b6-7890-4ab1-cdef-34567890abcd) */
INSERT INTO participation (id_event, id_user)
VALUES ('e3f4a5b6-7890-4ab1-cdef-34567890abcd', '3f227c58-055d-43c5-9713-8cf9d98c3a1a');

/*Feedback*/

/* Feedback by anne (id_user: 28cd5eb1-5588-43fb-9e13-172ce94b557b) for Cybersecurity Conference (id_event: d2e3f4e5-6789-4abc-adef-234567890abc) with score 4 */
INSERT INTO feedback (id_event, id_user, date, content, score)
VALUES ('d2e3f4e5-6789-4abc-adef-234567890abc', '28cd5eb1-5588-43fb-9e13-172ce94b557b',
        '2024-07-10 18:00:00', 'Great insights on cybersecurity!', 4);

/* Feedback by bnne (id_user: 3f227c58-055d-43c5-9713-8cf9d98c3a1a) for Data Science Workshop (id_event: e3f4a5b6-7890-4ab1-cdef-34567890abcd) with score 5 */
INSERT INTO feedback (id_event, id_user, date, content, score)
VALUES ('e3f4a5b6-7890-4ab1-cdef-34567890abcd', '3f227c58-055d-43c5-9713-8cf9d98c3a1a',
        '2024-05-20 19:00:00', 'Very informative workshop!', 5);