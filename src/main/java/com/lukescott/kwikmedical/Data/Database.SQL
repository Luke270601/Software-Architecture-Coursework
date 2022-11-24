CREATE TABLE `kwikmedical`.`patient records`
(
    `NHSNumber`    VARCHAR(10) NOT NULL,
    `First Name`   VARCHAR(20) NOT NULL,
    `Last Name`    VARCHAR(20) NOT NULL,
    `Address`      VARCHAR(50) NOT NULL,
    `Postcode`     VARCHAR(8)  NOT NULL,
    `MedCondition` TEXT        NOT NULL,
    PRIMARY KEY (`NHSNumber`)
) ENGINE = MyISAM;
ó

CREATE TABLE `kwikmedical`.`callout reports`
(
    `Callout ID`    int(11) NOT NULL,
    `Hospital ID`    int(4) NOT NULL,
    `NHSNumber`    VARCHAR(10) NOT NULL,
    `Description`  TEXT        NOT NULL,
    `Time`         DATETIME    NOT NULL,
    `Location`     TEXT        NOT NULL,
    `Action Taken` TEXT        NOT NULL,
    `Call Time`    INT         NOT NULL,
    PRIMARY KEY (`Callout ID`)
) ENGINE = MyISAM;

CREATE TABLE `kwikmedical`.`regional hospitals`
(
    `Hospital Id` INT(4) NOT NULL,
    `Name`        VARCHAR(20) NOT NULL,
    `Address`     VARCHAR(50) NOT NULL,
    `Postcode`    VARCHAR(8)  NOT NULL,
    PRIMARY KEY (`Hospital Id`)
) ENGINE = MyISAM;

CREATE TABLE `kwikmedical`.`ambulance request` (
`Request ID` INT(4) NOT NULL AUTO_INCREMENT ,
`Hospital ID` INT(4) NOT NULL ,
`NHS Number` VARCHAR(10) NOT NULL ,
PRIMARY KEY (`Request ID`),
UNIQUE (`NHS Number`)) ENGINE = MyISAM;

INSERT INTO `patient records` (`NHSNumber`, `First Name`, `Last Name`, `Address`, `Postcode`, `MedCondition`)
VALUES ('2518942610', 'David', 'Michael', '34 George Street, Edinburgh', 'BG89 2HG', 'Chronic back pain');

INSERT INTO `patient records` (`NHSNumber`, `First Name`, `Last Name`, `Address`, `Postcode`, `MedCondition`)
VALUES ('1395023697', 'Diana ', 'Carry', '12 Lincoln Way, Livingston', 'EY63 9UJ', 'Waist down paralysis');

INSERT INTO `patient records` (`NHSNumber`, `First Name`, `Last Name`, `Address`, `Postcode`, `MedCondition`)
VALUES ('7276349012', 'Holly', 'Francis', '89 Westway, Paisly ', 'TL73 4PA', 'Deep vein thrombosis ');

INSERT INTO `patient records` (`NHSNumber`, `First Name`, `Last Name`, `Address`, `Postcode`, `MedCondition`)
VALUES ('7623508547', 'Anya', 'Folgers', '12 Curry Road, Bathgate', 'HJ91 2BQ', 'No recorded ailments');


INSERT INTO `regional hospitals` (`Hospital Id`, `Name`, `Address`, `Postcode`)
VALUES ('5171', 'Franks Hospital', 'George Street', 'BG89 2HG');

INSERT INTO `regional hospitals` (`Hospital Id`, `Name`, `Address`, `Postcode`)
VALUES ('8156', 'A Hospital', 'Lincoln Way', 'EY63 9UJ');

INSERT INTO `regional hospitals` (`Hospital Id`, `Name`, `Address`, `Postcode`)
VALUES ('9517', 'St Johns Hospital ', 'Westway', 'TL73 4PA');

INSERT INTO `regional hospitals` (`Hospital Id`, `Name`, `Address`, `Postcode`)
VALUES ('9872', 'Mura Foundation', 'Curry Road', 'HJ91 2BQ');

INSERT INTO `regional hospitals` (`Hospital Id`, `Name`, `Address`, `Postcode`)
VALUES ('6472', 'Dougal Hospital', 'Brentburn', 'EY64 9GH');

INSERT INTO `regional hospitals` (`Hospital ID`, `Name`, `Address`, `Postcode`)
VALUES ('4823', 'Big Hospital', '59 Avenue', 'EY65 8BE');

INSERT INTO `regional hospitals` (`Hospital ID`, `Name`, `Address`, `Postcode`)
VALUES ('7823', 'Borders Hospital', 'Melrose', 'RM29 5UF');

INSERT INTO `regional hospitals` (`Hospital ID`, `Name`, `Address`, `Postcode`)
VALUES ('5217', 'Kelso Hospital', 'Kelso', 'MP31 4QY');

INSERT INTO `regional hospitals` (`Hospital ID`, `Name`, `Address`, `Postcode`)
VALUES ('9723', 'Midpark Hospital', 'Midpark Way', 'DT41 8LB');

INSERT INTO `regional hospitals` (`Hospital ID`, `Name`, `Address`, `Postcode`)
VALUES ('6374', 'Moffat Hospital', 'Moffat Avenue', 'KW82 6HH');

INSERT INTO `regional hospitals` (`Hospital ID`, `Name`, `Address`, `Postcode`)
VALUES ('1089', 'Thornhill Hospital', 'Thornhillside', 'PO10 8BB');

INSERT INTO `regional hospitals` (`Hospital ID`, `Name`, `Address`, `Postcode`)
VALUES ('7263', 'Adamson Hospital', 'Cupar Centre', 'NG23 7PP');

INSERT INTO `regional hospitals` (`Hospital ID`, `Name`, `Address`, `Postcode`)
VALUES ('1920', 'Glenrothes Hospital', 'Glenrothes Park', 'IG80 0EG');

INSERT INTO `regional hospitals` (`Hospital ID`, `Name`, `Address`, `Postcode`)
VALUES ('1080', 'St Andrews Community Hospital', 'St Andrews Place', 'PH19 1AF');

INSERT INTO `regional hospitals` (`Hospital ID`, `Name`, `Address`, `Postcode`)
VALUES ('2560', 'Victoria Hospital', ' Kirkcaldy', 'N815 1AB');

INSERT INTO `regional hospitals` (`Hospital ID`, `Name`, `Address`, `Postcode`)
VALUES ('1440', 'University Hospital', '59 Avenue, Livingston', 'SY82 1GX');

INSERT INTO `regional hospitals` (`Hospital ID`, `Name`, `Address`, `Postcode`)
VALUES ('9001', 'Ayrshire Central Hospital,', 'Ayrshire Centre', 'WD24 7UE');

INSERT INTO `regional hospitals` (`Hospital ID`, `Name`, `Address`, `Postcode`)
VALUES ('2001', 'Brooksby House Hospital', 'Largs', 'BN43 5NX');

INSERT INTO `regional hospitals` (`Hospital ID`, `Name`, `Address`, `Postcode`)
VALUES ('8261', 'Biggart Hospital', 'Prestwick', 'EH55 8PP');

INSERT INTO `regional hospitals` (`Hospital ID`, `Name`, `Address`, `Postcode`)
VALUES ('3719', 'Knoll Hospital', 'Duns', 'NG20 9AB');
