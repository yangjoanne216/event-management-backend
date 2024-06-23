-- Drop tables if they exist
DROP TABLE IF EXISTS participation;
DROP TABLE IF EXISTS feedback;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS type_event;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS typeEvent;
DROP TYPE if exists TYPELOCATION;
CREATE TYPE TYPELOCATION AS ENUM ('ONLINE', 'ONSITE', 'HYBRID');

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
    score         numeric(10, 2)
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
    id_event UUID                       NOT NULL,
    id_user  UUID                       NOT NULL,
    date     TIMESTAMP                  NOT NULL,
    content  TEXT,
    score    INTEGER CHECK (score <= 5) NOT NULL,
    PRIMARY KEY (id_event, id_user),
    FOREIGN KEY (id_event) REFERENCES event (id_event),
    FOREIGN KEY (id_user) REFERENCES users (id_user)
);

/*Create trigger for calculate average score based on feedback */
CREATE OR REPLACE FUNCTION update_event_score()
    RETURNS TRIGGER AS
$$
BEGIN
    IF TG_OP = 'DELETE' THEN
        UPDATE event
        SET score = (SELECT COALESCE(AVG(score), 0)
                     FROM feedback
                     WHERE id_event = OLD.id_event)
        WHERE id_event = OLD.id_event;
    ELSE
        UPDATE event
        SET score = (SELECT COALESCE(AVG(score), 0)
                     FROM feedback
                     WHERE id_event = NEW.id_event)
        WHERE id_event = NEW.id_event;
    END IF;
    RETURN NULL;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trg_feedback_insert
    AFTER INSERT
    ON feedback
    FOR EACH ROW
EXECUTE FUNCTION update_event_score();

CREATE TRIGGER trg_feedback_update
    AFTER UPDATE
    ON feedback
    FOR EACH ROW
EXECUTE FUNCTION update_event_score();

CREATE TRIGGER trg_feedback_delete
    AFTER DELETE
    ON feedback
    FOR EACH ROW
EXECUTE FUNCTION update_event_score();

CREATE OR REPLACE FUNCTION check_feedback_date()
    RETURNS TRIGGER AS
$$
BEGIN
    -- Check if feedback date is before today and after event start time
    IF (NEW.date >= CURRENT_DATE) THEN
        RAISE EXCEPTION 'Feedback date must be before today.';
    END IF;

    -- Check if the feedback date is after the event's start time
    IF (NEW.date <= (SELECT start_time FROM event WHERE id_event = NEW.id_event)) THEN
        RAISE EXCEPTION 'Feedback date must be after the event start time.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- 创建触发器：防止组织者参加自己创建的事件
CREATE OR REPLACE FUNCTION prevent_organizer_participation()
    RETURNS TRIGGER AS
$$
BEGIN
    -- 检查插入的用户ID是否为该事件的组织者ID
    IF (SELECT id_organizer FROM event WHERE id_event = NEW.id_event) = NEW.id_user THEN
        RAISE EXCEPTION 'Organizer cannot participate in their own event';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER prevent_organizer_participation_trigger
    BEFORE INSERT
    ON participation
    FOR EACH ROW
EXECUTE FUNCTION prevent_organizer_participation();


/* AI in Healthcare Meetup - Yang YANG */
INSERT INTO participation (id_event, id_user)
VALUES ('a81e5b58-61d3-4427-bd17-2fd816a2a7e8', '58bdba14-9cec-4f39-bc27-43a01afef3ae');

select *
from users
