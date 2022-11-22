
CREATE SEQUENCE ARTCILE_ID_SEQ INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;
CREATE SEQUENCE MEMBER_ID_SEQ INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;
CREATE SEQUENCE COMMENT_ID_SEQ INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;
CREATE SEQUENCE CREATER_ID_SEQ INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;
CREATE SEQUENCE SCRAP_ID_SEQ INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1;
-- 임시 테이블 User Table로 변경 예정
CREATE TABLE CREATOR
(
    CREATOR_ID          INTEGER NOT NULL DEFAULT nextval('creater_id_seq' :: regclass),
    CREATED_AT          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    NAME                TEXT NOT NULL,
    IMG                 TEXT 
);
CREATE TABLE ARTICLE
(
    ARTCILE_ID          INTEGER NOT NULL DEFAULT nextval('article_id_seq' :: regclass),
    TITLE               TEXT NOT NULL,
    PLACE               TEXT,
    CONTENTS            TEXT,
    CREATED_AT          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CREATOR_ID          INTEGER,
    MODIFIED_AT         TIMESTAMP WITHOUT TIME ZONE,
    MODIFIER_ID         INTEGER,
    MEMBER_ID           INTEGER,
    SCRAP_ID            INTEGER,
    STATUS              VARCHAR(64)
    CONSTRAINT ARTCILE_PK PRIMARY KEY (ARTCILE_ID)
    CONSTRAINT ARTCILE_FK1 FOREIGN KEY (CREATOR_ID)
    REFERENCES CREATOR (CREATOR_ID) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);
CREATE TABLE MEMBER
(
    MEMBER_ID           INTEGER NOT NULL DEFAULT nextval('member_id_seq' :: regclass),
    JOIN_CNT            INTEGER ,
    TOTAL_CNT			INTEGER ,
    ARTCILE_ID          INTEGER NOT NULL,
    CONSTRAINT MEMBER_PK PRIMARY KEY (MEMBER_ID),
    CONSTRAINT MEMBER_FK FOREIGN KEY (ARTCILE_ID)
    REFERENCES ARTICLE (ARTCILE_ID) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);

CREATE TABLE COMMENT
(
    COMMENT_ID          INTEGER NOT NULL DEFAULT nextval('comment_id_seq' :: regclass),
    CREATED_AT          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CREATOR_ID          INTEGER,
    MODIFIED_AT         TIMESTAMP WITHOUT TIME ZONE,
    CONTENTS            TEXT NOT NULL,
    PARENT_ID           INTEGER,
    ARTCILE_ID          INTEGER,
    CONSTRAINT COMMENT_PK PRIMARY KEY (COMMENT_ID),
    CONSTRAINT COMMENT_FK1 FOREIGN KEY (ARTCILE_ID)
    REFERENCES ARTICLE (ARTCILE_ID) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    CONSTRAINT COMMENT_FK2 FOREIGN KEY (CREATOR_ID)
    REFERENCES CREATOR (CREATOR_ID) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);



CREATE TABLE SCRAP
(
    SCRAP_ID            INTEGER NOT NULL DEFAULT nextval('scrap_id_seq' :: regclass),
    CREATED_AT          TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CREATOR_ID          INTEGER,
    MODIFIED_AT         TIMESTAMP WITHOUT TIME ZONE,
    ARTCILE_ID          INTEGER,
    CONSTRAINT SCRAP_PK PRIMARY KEY (SCRAP_ID),
    CONSTRAINT SCRAP_FK1 FOREIGN KEY (ARTCILE_ID)
    REFERENCES ARTICLE (ARTCILE_ID) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    CONSTRAINT SCRAP_FK2 FOREIGN KEY (CREATOR_ID)
    REFERENCES CREATOR (CREATOR_ID) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);

-- test insert
INSERT INTO CREATOR (CREATED_AT, NAME) values( now() ,'testUser01' );
INSERT INTO CREATOR (CREATED_AT, NAME, IMG) values( now() ,'testUser02',"img" );
INSERT INTO CREATOR (CREATED_AT, NAME) values( now() ,'testUser03' );