CREATE TABLE users_tb
(
    user_id       UUID                        NOT NULL,
    username      VARCHAR(255)                NOT NULL,
    email         VARCHAR(255)                NOT NULL,
    password      VARCHAR(255)                NOT NULL,
    birthday_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    gender        VARCHAR(255)                NOT NULL,
    age           INTEGER                     NOT NULL,
    gym_center_id BIGINT,
    created_at    TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    role          VARCHAR(255)                NOT NULL,
    user_status   VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_users_tb PRIMARY KEY (user_id)
);

ALTER TABLE users_tb
    ADD CONSTRAINT uc_users_tb_email UNIQUE (email);

ALTER TABLE users_tb
    ADD CONSTRAINT FK_USERS_TB_ON_GYM_CENTER FOREIGN KEY (gym_center_id) REFERENCES gym_centers_tb (gym_center_id);