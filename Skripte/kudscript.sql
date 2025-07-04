-- Generated by Oracle SQL Developer Data Modeler 24.3.1.351.0831
--   at:        2024-12-30 12:37:45 CET
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE Clan 
    ( 
     IdC      NUMBER  NOT NULL , 
     ImeC     VARCHAR2 (64) , 
     PrezC    VARCHAR2 (64) , 
     JmbgC    VARCHAR2 (13)  NOT NULL , 
     BrTelC   VARCHAR2 (32) , 
     DatRodjC DATE , 
     DatUpC   DATE 
    ) 
;

ALTER TABLE Clan 
    ADD CONSTRAINT Clan_PK PRIMARY KEY ( IdC ) ;

CREATE TABLE Clanarina 
    ( 
     IdCl           NUMBER  NOT NULL , 
     IznCl          NUMBER , 
     DatPlCl        DATE , 
     Clan_IdC       NUMBER  NOT NULL , 
     Clan_JmbgC     VARCHAR2 (13)  NOT NULL , 
     Sekretar_IdZ   NUMBER  NOT NULL , 
     Sekretar_JmbgZ VARCHAR2 (13)  NOT NULL 
    ) 
;

ALTER TABLE Clanarina 
    ADD CONSTRAINT Clanarina_PK PRIMARY KEY ( IdCl, Clan_IdC, Clan_JmbgC ) ;

CREATE TABLE Grad 
    ( 
     PostBrG NUMBER  NOT NULL , 
     NazG    VARCHAR2 (64) 
    ) 
;

ALTER TABLE Grad 
    ADD CONSTRAINT Grad_PK PRIMARY KEY ( PostBrG ) ;

CREATE TABLE Ide 
    ( 
     OdrzavaSe_IdP   NUMBER  NOT NULL , 
     OdrzavaSe_IdS   NUMBER  NOT NULL , 
     OdrzavaSe_IdKud NUMBER  NOT NULL , 
     Je_IdC          NUMBER  NOT NULL , 
     Je_JmbgC        VARCHAR2 (13)  NOT NULL , 
     Je_IdS          NUMBER  NOT NULL , 
     Je_IdKud        NUMBER  NOT NULL 
    ) 
;

ALTER TABLE Ide 
    ADD CONSTRAINT Ide_PK PRIMARY KEY ( OdrzavaSe_IdP, OdrzavaSe_IdS, OdrzavaSe_IdKud, Je_IdC, Je_JmbgC, Je_IdS, Je_IdKud ) ;

CREATE TABLE Je 
    ( 
     Clan_IdC          NUMBER  NOT NULL , 
     Clan_JmbgC        VARCHAR2 (13)  NOT NULL , 
     Sekcija_IdS       NUMBER  NOT NULL , 
     Sekcija_Kud_IdKud NUMBER  NOT NULL 
    ) 
;

ALTER TABLE Je 
    ADD CONSTRAINT Je_PK PRIMARY KEY ( Clan_IdC, Clan_JmbgC, Sekcija_IdS, Sekcija_Kud_IdKud ) ;

CREATE TABLE Koreograf 
    ( 
     IdZ               NUMBER  NOT NULL , 
     Sekcija_IdS       NUMBER , 
     Sekcija_Kud_IdKud NUMBER , 
     Koreograf_IdZ     NUMBER , 
     Koreograf_JmbgZ   VARCHAR2 (13) , 
     IdZ1              NUMBER 
    ) 
;

ALTER TABLE Koreograf 
    ADD CONSTRAINT Koreograf_PK PRIMARY KEY ( IdZ ) ;

CREATE TABLE Koreografija 
    ( 
     IdK  NUMBER  NOT NULL , 
     NazK VARCHAR2 (64) , 
     KreK VARCHAR2 (100) , 
     TrK  NUMBER 
    ) 
;

ALTER TABLE Koreografija 
    ADD CONSTRAINT Koreografija_PK PRIMARY KEY ( IdK ) ;

CREATE TABLE Kud 
    ( 
     IdKud        NUMBER  NOT NULL , 
     NazKud       VARCHAR2 (64) , 
     AdrKud       VARCHAR2 (100) , 
     Grad_PostBrG NUMBER  NOT NULL 
    ) 
;

ALTER TABLE Kud 
    ADD CONSTRAINT Kud_PK PRIMARY KEY ( IdKud ) ;

CREATE TABLE Nosnja 
    ( 
     IdN  NUMBER  NOT NULL , 
     NazN VARCHAR2 (64) , 
     TipN VARCHAR2 (10) 
    ) 
;

ALTER TABLE Nosnja 
    ADD 
    CHECK (TipN IN ('Muska', 'Zenska')) 
;

ALTER TABLE Nosnja 
    ADD CONSTRAINT Nosnja_PK PRIMARY KEY ( IdN ) ;

CREATE TABLE OdrzavaSe 
    ( 
     Proba_IdP         NUMBER  NOT NULL , 
     Sekcija_IdS       NUMBER  NOT NULL , 
     Sekcija_Kud_IdKud NUMBER  NOT NULL 
    ) 
;
CREATE UNIQUE INDEX OdrzavaSe__IDX ON OdrzavaSe 
    ( 
     Proba_IdP ASC 
    ) 
;

ALTER TABLE OdrzavaSe 
    ADD CONSTRAINT OdrzavaSe_PK PRIMARY KEY ( Proba_IdP, Sekcija_IdS, Sekcija_Kud_IdKud ) ;

CREATE TABLE Posjeduje 
    ( 
     Koreografija_IdK NUMBER  NOT NULL , 
     Kud_IdKud        NUMBER  NOT NULL 
    ) 
;

ALTER TABLE Posjeduje 
    ADD CONSTRAINT Posjeduje_PK PRIMARY KEY ( Koreografija_IdK, Kud_IdKud ) ;

CREATE TABLE Pozajmica 
    ( 
     DatP       DATE , 
     Nosnja_IdN NUMBER  NOT NULL , 
     Clan_IdC   NUMBER  NOT NULL , 
     Clan_JmbgC VARCHAR2 (13)  NOT NULL 
    ) 
;

ALTER TABLE Pozajmica 
    ADD CONSTRAINT Pozajmica_PK PRIMARY KEY ( Nosnja_IdN, Clan_IdC, Clan_JmbgC ) ;

CREATE TABLE Predsjednik 
    ( 
     IdZ       NUMBER  NOT NULL , 
     DatIstMan DATE 
    ) 
;

ALTER TABLE Predsjednik 
    ADD CONSTRAINT Predsjednik_PK PRIMARY KEY ( IdZ ) ;

CREATE TABLE Pripada 
    ( 
     Koreografija_IdK NUMBER  NOT NULL , 
     Nosnja_IdN       NUMBER  NOT NULL 
    ) 
;

ALTER TABLE Pripada 
    ADD CONSTRAINT Pripada_PK PRIMARY KEY ( Koreografija_IdK, Nosnja_IdN ) ;

CREATE TABLE Proba 
    ( 
     IdP    NUMBER  NOT NULL , 
     TrP    NUMBER , 
     VrPocP DATE 
    ) 
;

ALTER TABLE Proba 
    ADD CONSTRAINT Proba_PK PRIMARY KEY ( IdP ) ;

CREATE TABLE Sekcija 
    ( 
     IdS       NUMBER  NOT NULL , 
     NazS      VARCHAR2 (64) , 
     TipS      VARCHAR2 (20) , 
     Kud_IdKud NUMBER  NOT NULL 
    ) 
;

ALTER TABLE Sekcija 
    ADD 
    CHECK (TipS IN ('Izvodjacka', 'Pocetna', 'Pripremna', 'Rekreativna', 'Skolica folklora')) 
;

ALTER TABLE Sekcija 
    ADD CONSTRAINT Sekcija_PK PRIMARY KEY ( IdS, Kud_IdKud ) ;

CREATE TABLE Sekretar 
    ( 
     IdZ NUMBER  NOT NULL 
    ) 
;

ALTER TABLE Sekretar 
    ADD CONSTRAINT Sekretar_PK PRIMARY KEY ( IdZ ) ;

CREATE TABLE Zaposleni 
    ( 
     IdZ       NUMBER  NOT NULL , 
     ImeZ      VARCHAR2 (64) , 
     PrezZ     VARCHAR2 (64) , 
     JmbgZ     VARCHAR2 (13)  NOT NULL , 
     TipZ      VARCHAR2 (20)  NOT NULL , 
     DatZapZ   DATE , 
     BrTelZ    VARCHAR2 (64) , 
     Kud_IdKud NUMBER 
    ) 
;

ALTER TABLE Zaposleni 
    ADD CONSTRAINT CH_INH_Zaposleni 
    CHECK (TipZ IN ('Koreograf', 'Predsjednik', 'Sekretar')) 
;

ALTER TABLE Zaposleni 
    ADD CONSTRAINT Zaposleni_PK PRIMARY KEY ( IdZ ) ;

ALTER TABLE Clanarina 
    ADD CONSTRAINT Clanarina_Clan_FK FOREIGN KEY 
    ( 
     Clan_IdC
    ) 
    REFERENCES Clan 
    ( 
     IdC
    ) 
;

ALTER TABLE Clanarina 
    ADD CONSTRAINT Clanarina_Sekretar_FK FOREIGN KEY 
    ( 
     Sekretar_IdZ
    ) 
    REFERENCES Sekretar 
    ( 
     IdZ
    ) 
;

ALTER TABLE Ide 
    ADD CONSTRAINT Ide_Je_FK FOREIGN KEY 
    ( 
     Je_IdC,
     Je_JmbgC,
     Je_IdS,
     Je_IdKud
    ) 
    REFERENCES Je 
    ( 
     Clan_IdC,
     Clan_JmbgC,
     Sekcija_IdS,
     Sekcija_Kud_IdKud
    ) 
;

ALTER TABLE Ide 
    ADD CONSTRAINT Ide_OdrzavaSe_FK FOREIGN KEY 
    ( 
     OdrzavaSe_IdP,
     OdrzavaSe_IdS,
     OdrzavaSe_IdKud
    ) 
    REFERENCES OdrzavaSe 
    ( 
     Proba_IdP,
     Sekcija_IdS,
     Sekcija_Kud_IdKud
    ) 
;

ALTER TABLE Je 
    ADD CONSTRAINT Je_Clan_FK FOREIGN KEY 
    ( 
     Clan_IdC
    ) 
    REFERENCES Clan 
    ( 
     IdC
    ) 
;

ALTER TABLE Je 
    ADD CONSTRAINT Je_Sekcija_FK FOREIGN KEY 
    ( 
     Sekcija_IdS,
     Sekcija_Kud_IdKud
    ) 
    REFERENCES Sekcija 
    ( 
     IdS,
     Kud_IdKud
    ) 
;

ALTER TABLE Koreograf 
    ADD CONSTRAINT Koreograf_Koreograf_FK FOREIGN KEY 
    ( 
     Koreograf_IdZ
    ) 
    REFERENCES Koreograf 
    ( 
     IdZ
    ) 
;

ALTER TABLE Koreograf 
    ADD CONSTRAINT Koreograf_Sekcija_FK FOREIGN KEY 
    ( 
     Sekcija_IdS,
     Sekcija_Kud_IdKud
    ) 
    REFERENCES Sekcija 
    ( 
     IdS,
     Kud_IdKud
    ) 
;

ALTER TABLE Koreograf 
    ADD CONSTRAINT Koreograf_Zaposleni_FK FOREIGN KEY 
    ( 
     IdZ
    ) 
    REFERENCES Zaposleni 
    ( 
     IdZ
    ) 
;

ALTER TABLE Kud 
    ADD CONSTRAINT Kud_Grad_FK FOREIGN KEY 
    ( 
     Grad_PostBrG
    ) 
    REFERENCES Grad 
    ( 
     PostBrG
    ) 
;

ALTER TABLE OdrzavaSe 
    ADD CONSTRAINT OdrzavaSe_Proba_FK FOREIGN KEY 
    ( 
     Proba_IdP
    ) 
    REFERENCES Proba 
    ( 
     IdP
    ) 
;

ALTER TABLE OdrzavaSe 
    ADD CONSTRAINT OdrzavaSe_Sekcija_FK FOREIGN KEY 
    ( 
     Sekcija_IdS,
     Sekcija_Kud_IdKud
    ) 
    REFERENCES Sekcija 
    ( 
     IdS,
     Kud_IdKud
    ) 
;

ALTER TABLE Posjeduje 
    ADD CONSTRAINT Posjeduje_Koreografija_FK FOREIGN KEY 
    ( 
     Koreografija_IdK
    ) 
    REFERENCES Koreografija 
    ( 
     IdK
    ) 
;

ALTER TABLE Posjeduje 
    ADD CONSTRAINT Posjeduje_Kud_FK FOREIGN KEY 
    ( 
     Kud_IdKud
    ) 
    REFERENCES Kud 
    ( 
     IdKud
    ) 
;

ALTER TABLE Pozajmica 
    ADD CONSTRAINT Pozajmica_Clan_FK FOREIGN KEY 
    ( 
     Clan_IdC
    ) 
    REFERENCES Clan 
    ( 
     IdC
    ) 
;

ALTER TABLE Pozajmica 
    ADD CONSTRAINT Pozajmica_Nosnja_FK FOREIGN KEY 
    ( 
     Nosnja_IdN
    ) 
    REFERENCES Nosnja 
    ( 
     IdN
    ) 
;

ALTER TABLE Predsjednik 
    ADD CONSTRAINT Predsjednik_Zaposleni_FK FOREIGN KEY 
    ( 
     IdZ
    ) 
    REFERENCES Zaposleni 
    ( 
     IdZ
    ) 
;

ALTER TABLE Pripada 
    ADD CONSTRAINT Pripada_Koreografija_FK FOREIGN KEY 
    ( 
     Koreografija_IdK
    ) 
    REFERENCES Koreografija 
    ( 
     IdK
    ) 
;

ALTER TABLE Pripada 
    ADD CONSTRAINT Pripada_Nosnja_FK FOREIGN KEY 
    ( 
     Nosnja_IdN
    ) 
    REFERENCES Nosnja 
    ( 
     IdN
    ) 
;

ALTER TABLE Sekcija 
    ADD CONSTRAINT Sekcija_Kud_FK FOREIGN KEY 
    ( 
     Kud_IdKud
    ) 
    REFERENCES Kud 
    ( 
     IdKud
    ) 
;

ALTER TABLE Sekretar 
    ADD CONSTRAINT Sekretar_Zaposleni_FK FOREIGN KEY 
    ( 
     IdZ
    ) 
    REFERENCES Zaposleni 
    ( 
     IdZ
    ) 
;

ALTER TABLE Zaposleni 
    ADD CONSTRAINT Zaposleni_Kud_FK FOREIGN KEY 
    ( 
     Kud_IdKud
    ) 
    REFERENCES Kud 
    ( 
     IdKud
    ) 
;

CREATE OR REPLACE TRIGGER ARC_FKArc_1_Predsjednik 
BEFORE INSERT OR UPDATE OF IdZ 
ON Predsjednik 
FOR EACH ROW 
DECLARE 
    d VARCHAR2 (20); 
BEGIN 
    SELECT A.TipZ INTO d 
    FROM Zaposleni A 
    WHERE A.IdZ = :new.IdZ; 
    IF (d IS NULL OR d <> 'Predsjednik') THEN 
        raise_application_error(-20223,'FK Predsjednik_Zaposleni_FK in Table Predsjednik violates Arc constraint on Table Zaposleni - discriminator column TipZ doesn''t have value ''Predsjednik'''); 
    END IF; 
    EXCEPTION 
    WHEN NO_DATA_FOUND THEN 
        NULL; 
    WHEN OTHERS THEN 
        RAISE; 
END; 
/

CREATE OR REPLACE TRIGGER ARC_FKArc_1_Koreograf 
BEFORE INSERT OR UPDATE OF IdZ 
ON Koreograf 
FOR EACH ROW 
DECLARE 
    d VARCHAR2 (20); 
BEGIN 
    SELECT A.TipZ INTO d 
    FROM Zaposleni A 
    WHERE A.IdZ = :new.IdZ; 
    IF (d IS NULL OR d <> 'Koreograf') THEN 
        raise_application_error(-20223,'FK Koreograf_Zaposleni_FK in Table Koreograf violates Arc constraint on Table Zaposleni - discriminator column TipZ doesn''t have value ''Koreograf'''); 
    END IF; 
    EXCEPTION 
    WHEN NO_DATA_FOUND THEN 
        NULL; 
    WHEN OTHERS THEN 
        RAISE; 
END; 
/

CREATE OR REPLACE TRIGGER ARC_FKArc_1_Sekretar 
BEFORE INSERT OR UPDATE OF IdZ 
ON Sekretar 
FOR EACH ROW 
DECLARE 
    d VARCHAR2 (20); 
BEGIN 
    SELECT A.TipZ INTO d 
    FROM Zaposleni A 
    WHERE A.IdZ = :new.IdZ; 
    IF (d IS NULL OR d <> 'Sekretar') THEN 
        raise_application_error(-20223,'FK Sekretar_Zaposleni_FK in Table Sekretar violates Arc constraint on Table Zaposleni - discriminator column TipZ doesn''t have value ''Sekretar'''); 
    END IF; 
    EXCEPTION 
    WHEN NO_DATA_FOUND THEN 
        NULL; 
    WHEN OTHERS THEN 
        RAISE; 
END; 
/



   