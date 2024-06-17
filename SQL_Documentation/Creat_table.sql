CREATE TYPE TYPELOCATION AS ENUM ('ONLINE', 'ONSITE', 'HYBRIDE');
-- Drop tables if they exist
DROP TABLE IF EXISTS participation;
DROP TABLE IF EXISTS feedback;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS type_event;
DROP TABLE IF EXISTS location;

CREATE TABLE type_event
(
    id_type UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name    VARCHAR(255) NOT NULL UNIQUE
);


CREATE TABLE location
(
    id_city UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name    VARCHAR(255) NOT NULL UNIQUE
);


CREATE TABLE users
(
    id_user   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    email     VARCHAR(255) NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    lastname  VARCHAR(255) NOT NULL,
    firstname VARCHAR(255) NOT NULL,
    avatar    VARCHAR(255) -- Assuming storing URLs for avatar images
);

CREATE TABLE event
(
    id_event      UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    id_organizer  UUID         NOT NULL REFERENCES users (id_user),
    title         VARCHAR(255) NOT NULL,
    description   TEXT         NOT NULL,
    start_time    TIMESTAMP    NOT NULL,
    end_time      TIMESTAMP    NOT NULL,
    id_type_event UUID         NOT NULL REFERENCES type_event (id_type),
    type_location TYPELOCATION NOT NULL, -- Assuming TYPELOCATION is a predefined enum
    image         VARCHAR(255),          -- Assuming storing URLs for images
    id_location   UUID REFERENCES location (id_city),
    note          numeric(10, 2)
);


CREATE TABLE participation
(
    id_event UUID NOT NULL,
    id_user  UUID NOT NULL,
    PRIMARY KEY (id_event, id_user),
    FOREIGN KEY (id_event) REFERENCES event (id_event),
    FOREIGN KEY (id_user) REFERENCES users (id_user)
);

CREATE TABLE feedback
(
    id_feedback UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    id_event    UUID      NOT NULL,
    id_user     UUID      NOT NULL,
    date        TIMESTAMP NOT NULL,
    content     TEXT,
    note        INTEGER CHECK (note <= 5),
    FOREIGN KEY (id_event) REFERENCES event (id_event),
    FOREIGN KEY (id_user) REFERENCES users (id_user)
);

