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

CREATE TABLE `kwikmedical`.`incident reports`
(
    `NHSNumber`    VARCHAR(10) NOT NULL,
    `Description`  TEXT        NOT NULL,
    `Time`         DATETIME    NOT NULL,
    `Location`     TEXT        NOT NULL,
    `Action Taken` TEXT        NOT NULL,
    `Call Time`    INT         NOT NULL,
    PRIMARY KEY (`NHSNumber`)
) ENGINE = MyISAM;

CREATE TABLE `kwikmedical`.`regional hospitals`
(
    `Hospital Id` INT(4) NOT NULL,
    `Name`        VARCHAR(20) NOT NULL,
    `Address`     VARCHAR(50) NOT NULL,
    `Postcode`    VARCHAR(8)  NOT NULL,
    PRIMARY KEY (`Hospital Id`)
) ENGINE = MyISAM;

ALTER TABLE `ambulance request` CHANGE `Request ID` `Request ID` INT(11) NOT NULL AUTO_INCREMENT;

INSERT INTO `patient records` (`NHSNumber`, `First Name`, `Last Name`, `Address`, `Postcode`, `MedCondition`)
VALUES ('2518942610', 'David', 'Michael', '34 George Street, Edinburgh', 'BG89 2HG', 'Chronic back pain');

INSERT INTO `patient records` (`NHSNumber`, `First Name`, `Last Name`, `Address`, `Postcode`, `MedCondition`)
VALUES ('1395023697', 'Diana ', 'Carry', '12 Lincoln Way, Livingston', 'EY63 9UJ', 'Waist down paralysis');

INSERT INTO `patient records` (`NHSNumber`, `First Name`, `Last Name`, `Address`, `Postcode`, `MedCondition`)
VALUES ('7276349012', 'Holly', 'Francis', '89 Westway, Paisly ', 'TL73 4PA', 'Deep vein thrombosis ');

INSERT INTO `patient records` (`NHSNumber`, `First Name`, `Last Name`, `Address`, `Postcode`, `MedCondition`)
VALUES ('7623508547', 'Anya', 'Folgers', '12 Curry Road, Bathgate', 'HJ91 2BQ', 'No recorded ailments');


INSERT INTO `regional hospitals` (`Hospital Id`, `Name`, `Address`, `Postcode`)
VALUES ('5171', 'Franks Hospital', 'George Street, Edinburgh', 'BG89 2HG');

INSERT INTO `regional hospitals` (`Hospital Id`, `Name`, `Address`, `Postcode`)
VALUES ('8156', 'A Hospital', 'Lincoln Way, Livingston ', 'EY63 9UJ');

INSERT INTO `regional hospitals` (`Hospital Id`, `Name`, `Address`, `Postcode`)
VALUES ('9517', 'St Johns Hospital ', 'Westway, Paisly', 'TL73 4PA');

INSERT INTO `regional hospitals` (`Hospital Id`, `Name`, `Address`, `Postcode`)
VALUES ('9872', 'Mura Foundation', ' Curry Road, Bathgate', 'HJ91 2BQ');

INSERT INTO `regional hospitals` (`Hospital Id`, `Name`, `Address`, `Postcode`)
VALUES ('6472', 'Dougal Hospital', 'Brentburn, Livingston', 'EY64 9GH');

INSERT INTO `regional hospitals` (`Hospital ID`, `Name`, `Address`, `Postcode`)
VALUES ('4823', 'Big Hospital', '59 Avenue, Livingston', 'EY65 8BE');
