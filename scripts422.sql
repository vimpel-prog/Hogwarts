CREATE TABLE car
(
    Id    REAL PRIMARY KEY,
    Brand TEXT,
    Model TEXT,
    Price INTEGER
);
CREATE TABLE human
(
    Id                REAL PRIMARY KEY,
    HumanName         TEXT,
    Age               INTEGER,
    HaveDriverLicense BOOLEAN,
    CarId             REAL REFERENCES car (Id)
);
