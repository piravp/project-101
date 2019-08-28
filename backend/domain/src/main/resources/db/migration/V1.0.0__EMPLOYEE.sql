-- TODO Create a table for Employee containing an ID, firstname, lastname and date of birth

CREATE TABLE EMPLOYEE
(
    ID                            NUMBER(20) GENERATED    BY DEFAULT AS IDENTITY,
    FIRST_NAME                    VARCHAR2(30 CHAR)       NOT NULL,
    LAST_NAME                     VARCHAR2(30 CHAR)       NOT NULL,
    DATE_OF_BIRTH                 DATE                    NOT NULL,
    CONSTRAINT EMPLOYEE_PK PRIMARY KEY (ID)
);