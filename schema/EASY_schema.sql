CREATE TABLE RECIPE(
RECIPE_NO NUMBER(5)  CONSTRAINT RECIPE_NO_PK PRIMARY KEY ,
NAME VARCHAR2(50 CHAR) NOT NULL,
METHOD VARCHAR2(1000 CHAR) NOT NULL
);
CREATE TABLE RECIPE_INGREDIENT(
RECIPE_INGREDIENT_NO NUMBER(5) CONSTRAINT RECIPE_INGREDIENT_NO_PK PRIMARY KEY,
RECIPE_NO NUMBER(5) NOT NULL,
INGREDIENT_NO NUMBER(5) NOT NULL,
CONSTRAINT RECIPE_NO_FK FOREIGN KEY(RECIPE_NO) REFERENCES RECIPE(RECIPE_NO),
INGERDIENT_NO_FK FOREIGN KEY(INGREDIENT_NO) REFERENCES INGERDIENT(INGREDIENT_NO)
);
CREATE TABLE STATS(
STATS_NO NUMBER(5) CONSTRAINT STATS_NO_PK PRIMARY KEY ,
MEMBER_NO NUMBER(5) NOT NULL,
INGREDIENT_NO NUMBER(5) NOT NULL,
AMOUNT NUMBER NOT NULL,
USEDDATE DATE NOT NULL,
CONSTRAINT MEMBER_NO_FK FOREIGN KEY(MEMBER_NO) REFERENCES MEMBER(M_NO),
INGREDIENT_NO_FK FOREIGN KEY(INGERDIENT_NO) REFERENCES INGREDIENT(INGREDIENT_NO)

);


CREATE TABLE MEMBER(
M_NO NUMBER(5) CONSTRAINT M_NO_PK PRIMARY KEY,
M_ID VARCHAR2(15 CHAR) NOT NULL,
M_PW VARCHAR2(100 CHAR) NOT NULL,
M_NAME VARCHAR2(10 CHAR) NOT NULL,
M_NICKNAME VARCHAR2(24 CHAR) NOT NULL,
M_GENDER VARCHAR2(1 CHAR) NOT NULL,
JOINDATE DATE NOT NULL
);

CREATE TABLE REFRIGERATOR_STATUS (    --잠깐 놔둬봐
REFRIGERATOR_STATUS_NO NUMBER(5) CONSTRAINT REFRIGERATOR_STATUS_NO_PK PRIMARY KEY,
MEMBER_NO NUMBER(5) NOT NULL,
INGERDIENT_NO NUMBER(5) NOT NULL,
AMOUNT NUMBER NOT NULL,
REGISTDATE DATE NOT NULL,
EXPIRATIONDATE DATE NOT NULL
CONSTRAINT  MEMBER_NO_FK FOREIGN KEY(MEMBER_NO) REFERENCES MEMBER(M_NO),
INGREDIENT_NO_FK FOREIGN KEY(INGREDIENT_NO) REFERENCES INGREDIENT(INGREDIENT_NO)
);
CREATE TABLE RECIPE_REVIEW
RECIPE_REVIEW_NO NUMBER(5) CONSTRAINT RECIPE_REVIEW_NO_PK PRIMARY KEY ,
RECIPE_NO NUMBER(5) NOT NULL,
TITLE VARCHAR2(100 CHAR) NOT NULL,
CONTENT VARCHAR2(1000 CHAR) NOT NULL,
POSTDATE DATE NOT NULL,
WATCH NUMBER NOT NULL
);
CREATE MY_RECIPE(
MY_RECIPE_NO NUMBER(5) CONSTRAINT MY_RECIPE_NO_PK PRIMARY KEY ,
MEMBER_NO NUMBER(5) NOT NULL,
TITLE VARCHAR2(1000 CHAR) NOT NULL,
POSTDATE DATE NOT NULL,
WATCH NUMBER NOT NULL,
CONSTRAINT MEMBER_NO_FK FOREIGN KEY(MEMBER_NO) REFERENCES MEMBER(M_NO)
);
CREATE TABLE WISHLIST(
WISHLIST_NO NUMBER(5) CONSTRAINT WISHLIST_NO_PK PRIMARY KEY,
MEMBER_NO NUMBER(5) NOT NULL,
COUNT NUMBVER NOT NULL,
CONSTRAINT MEMBER_NO_FK FOREIGN KEY(MEMBER_NO) REFERENCES MEMBER(M_NO),
INGREDIENT_NO_FK FOREIGN KEY(INGREDIENT_NO) REFERENCES INGREDIENT(INGREDIENT_NO)
);

CREATE TABLE INGERDIENT(
NO NUMBER(5) CONSTRAINT INGREDIENT_NO_PK PRIMARY KEY ,
NAME VARCHAR2(50 CHAR) NOT NULL,
CATEGORY NUMBER(5) NULL
);
CREATE TABLE RECIPE_REVIEW_COMMENT (
RECIPE_REVIEW_COMMENT_NO NUMBER(5) CONSTRAINT RECIPE_REVIEW_COMMENT_PK PRIMARY KEY,
RECIPE_REVIEW_NO NUMBER(5) NOT NULL,
CONTENT VARCHAR2(500 CHAR) NOT NULL,
RATING NUMBER(1) NOT NULL,
MEMBER_NO NUMBER(5) NOT NULL,
CONSTRAINT RECIPE_REVIEW_NO_FK FOREIGN KEY(RECIPE_REVIEW_NO) REFERENCES RECIPE_REVIEW(RECIPE_REVIEW_NO)
);

CREATE TABLE MY_RECIPE_COMMENT (
NO NUMBER(5) NOT NULL,
MY_RECIPE_NO NUMBER(5)  NOT NULL,
CONTENT VARCHAR2(500 CHAR) NOT NULL,
RATING NUMBER(1) NOT NULL,
MEMBER_NO NUMBER(5) NOT NULL
CONSTRAINT MY_RECIPE_NO_FK FOREIGN KEY(MY_RECIPE_NO) REFERENCES MY_RECIPE(MY_RECIPE_NO)
);

