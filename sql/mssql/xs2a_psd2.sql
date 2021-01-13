IF OBJECT_ID('dbo.psd2_authorization', 'U') 			IS NOT NULL  DROP TABLE dbo.psd2_authorization;
IF OBJECT_ID('dbo.psd2_authorization_object_type', 'U') IS NOT NULL  DROP TABLE dbo.psd2_authorization_object_type;
IF OBJECT_ID('dbo.psd2_basket', 'U') 				    IS NOT NULL  DROP TABLE dbo.psd2_basket;
IF OBJECT_ID('dbo.psd2_consent_access_ais', 'U') 	    IS NOT NULL  DROP TABLE dbo.psd2_consent_access_ais;
IF OBJECT_ID('dbo.psd2_consent_access_cof', 'U') 	    IS NOT NULL  DROP TABLE dbo.psd2_consent_access_cof;	
IF OBJECT_ID('dbo.psd2_consent_action_ais', 'U') 	    IS NOT NULL  DROP TABLE dbo.psd2_consent_action_ais;	
IF OBJECT_ID('dbo.psd2_consent_action_cof', 'U') 	    IS NOT NULL  DROP TABLE dbo.psd2_consent_action_cof;	
IF OBJECT_ID('dbo.psd2_consent_ais', 'U')			    IS NOT NULL  DROP TABLE dbo.psd2_consent_ais;		
IF OBJECT_ID('dbo.psd2_consent_cof', 'U')			    IS NOT NULL  DROP TABLE dbo.psd2_consent_cof;		
IF OBJECT_ID('dbo.psd2_datoteka', 'U') 				    IS NOT NULL  DROP TABLE dbo.psd2_datoteka;		
IF OBJECT_ID('dbo.psd2_message_log', 'U')			    IS NOT NULL  DROP TABLE dbo.psd2_message_log;	
IF OBJECT_ID('dbo.psd2_nalog_tpp', 'U') 				IS NOT NULL  DROP TABLE dbo.psd2_nalog_tpp;	
IF OBJECT_ID('dbo.psd2_x_request_id', 'U')			    IS NOT NULL  DROP TABLE dbo.psd2_x_request_id;	

CREATE TABLE psd2_authorization (
	id_aut INT IDENTITY(1, 1) NOT NULL,
	scast_aut VARCHAR(25) NOT NULL,
	scamt_aut VARCHAR(25) NOT NULL,
	cdate_aut DATETIME2 NOT NULL,
	objct_aut varchar(50) NOT NULL,
	otype_aut INTEGER NOT NULL,
	atype_aut INTEGER NOT NULL,
	tppid_aut VARCHAR(40),
	psuid_aut VARCHAR(40),
	reqid_aut VARCHAR(40) NOT NULL,
	link_aut VARCHAR(250) NOT NULL,
	todat_aut DATETIME2,
	m_date DATETIME2,
	ohash_aut VARCHAR(100),
	PRIMARY KEY (id_aut)
	);
	
CREATE TABLE psd2_authorization_object_type (
	id_aty INT IDENTITY(1, 1) NOT NULL,
	name_aty VARCHAR(25) NOT NULL,
	PRIMARY KEY (id_aty)
	);


	
CREATE TABLE psd2_basket (
	sifra_bas INT IDENTITY(1, 1) NOT NULL,
	cdate_bas DATETIME2 NOT NULL,
	reqid_bas VARCHAR(40) NOT NULL,
	psuid_bas VARCHAR(40),
	psuip_bas VARCHAR(140),
	tppid_bas VARCHAR(40) NOT NULL,
	tppre_bas VARCHAR(140),
	batyp_bas INTEGER,
	statu_bas VARCHAR(4),
	PRIMARY KEY (sifra_bas)
	);

	
	
CREATE TABLE psd2_consent_access_ais (
	sifra_acc INT IDENTITY(1, 1) NOT NULL,
	iban_acc VARCHAR(40) NOT NULL,
	curr_acc VARCHAR(3) NOT NULL,
	type_acc VARCHAR(25) NOT NULL,
	sicon_acc INTEGER NOT NULL,
	parti_acc INTEGER NOT NULL,
	PRIMARY KEY (sifra_acc)
	);
	
	
	
	
CREATE TABLE psd2_consent_access_cof (
	sifra_acc INT IDENTITY(1, 1) NOT NULL,
	iban_acc VARCHAR(40) NOT NULL,
	curr_acc VARCHAR(3),
	type_acc VARCHAR(25) NOT NULL,
	sicon_acc INTEGER NOT NULL,
	parti_acc INTEGER NOT NULL,
	PRIMARY KEY (sifra_acc)
	);
	

CREATE TABLE psd2_consent_action_ais (
	sifra_act INT IDENTITY(1, 1) NOT NULL,
	state_act VARCHAR(40) NOT NULL,
	date_act DATETIME2 NOT NULL,
	sicon_act INTEGER NOT NULL,
	tppid_act VARCHAR(40) NOT NULL,
	reqid_act VARCHAR(40) NOT NULL,
	PRIMARY KEY (sifra_act)
	);

	
CREATE TABLE psd2_consent_action_cof (
	sifra_act INT IDENTITY(1, 1) NOT NULL,
	state_act VARCHAR(40) NOT NULL,
	date_act DATETIME2 NOT NULL,
	sicon_act INTEGER NOT NULL,
	tppid_act VARCHAR(40) NOT NULL,
	reqid_act VARCHAR(40),
	PRIMARY KEY (sifra_act)
	);


CREATE TABLE psd2_consent_ais (
	sifra_con INT IDENTITY(1, 1) NOT NULL,
	state_con VARCHAR(25) NOT NULL,
	frequ_con INTEGER NOT NULL,
	cdate_con DATETIME2 NOT NULL,
	todat_con DATE NOT NULL,
	recur_con INTEGER NOT NULL,
	reqid_con VARCHAR(40) NOT NULL,
	psuid_con VARCHAR(40),
	tppid_con VARCHAR(40) NOT NULL,
	tppfr_con INTEGER,
	lacda_con DATE,
	comsr_con INTEGER NOT NULL,
	uctpp_con INTEGER,
	komit_con INTEGER,
	tppre_con VARCHAR(140),
	tppna_con VARCHAR(140),
	smloc_con DATETIME2,
	PRIMARY KEY (sifra_con)
	);

	
CREATE TABLE psd2_consent_cof (
	sifra_con INT IDENTITY(1, 1) NOT NULL,
	state_con VARCHAR(25) NOT NULL,
	frequ_con INTEGER,
	cdate_con DATETIME2,
	todat_con DATE,
	recur_con INTEGER,
	reqid_con VARCHAR(40) NOT NULL,
	psuid_con VARCHAR(40),
	tppid_con VARCHAR(40) NOT NULL,
	tppfr_con INTEGER,
	lacda_con DATE,
	comsr_con INTEGER,
	uctpp_con INTEGER,
	komit_con INTEGER,
	tppre_con VARCHAR(140),
	tppna_con VARCHAR(140),
	smloc_con DATETIME2,
	PRIMARY KEY (sifra_con)
	);
	

CREATE TABLE psd2_datoteka (
	sifra_dat INT IDENTITY(1, 1) NOT NULL,
	check_dat VARCHAR(255),
	filen_dat VARCHAR(255) NOT NULL,
	vrije_dat DATETIME2,
	ibkor_dat INTEGER,
	statu_dat VARCHAR(1),
	iname_dat VARCHAR(255),
	iorid_dat VARCHAR(255),
	PRIMARY KEY (sifra_dat)
	);

	

CREATE TABLE psd2_message_log (
	id_msg INT IDENTITY(1, 1) NOT NULL,
	reqid_msg VARCHAR(36),
	mtype_msg VARCHAR(20) NOT NULL,
	cdate_msg DATETIME2 NOT NULL,
	messg_msg VARCHAR(max) NOT NULL,
	tppid_msg VARCHAR(40),
	tppad_msg VARCHAR(100),
	rhost_msg VARCHAR(100),
	psuad_msg VARCHAR(100),
	PRIMARY KEY (id_msg)
	);
	
	

CREATE TABLE psd2_nalog_tpp (
	sifra_tpp INT IDENTITY(1, 1) NOT NULL,
	vrije_tpp DATETIME2 NOT NULL,
	tppid_tpp VARCHAR(40) NOT NULL,
	reqid_tpp VARCHAR(40) NOT NULL,
	conty_tpp VARCHAR(70) NOT NULL,
	pyprd_tpp VARCHAR(70) NOT NULL,
	pyser_tpp VARCHAR(70) NOT NULL,
	psuip_tpp VARCHAR(140),
	pymid_tpp VARCHAR(50),
	baskt_tpp INTEGER,
	trsts_tpp VARCHAR(10),
	PRIMARY KEY (sifra_tpp)
	);


	
CREATE TABLE psd2_x_request_id (
	request_id VARCHAR(36) NOT NULL,
	PRIMARY KEY (request_id)
	);

ALTER TABLE psd2_authorization	  ADD FOREIGN KEY	(otype_aut) REFERENCES psd2_authorization_object_type (id_aty);

ALTER TABLE psd2_consent_access_ais ADD FOREIGN KEY (sicon_acc) REFERENCES psd2_consent_ais (sifra_con);

ALTER TABLE psd2_consent_access_cof ADD FOREIGN KEY (sicon_acc) REFERENCES psd2_consent_cof (sifra_con);

ALTER TABLE psd2_consent_action_ais ADD FOREIGN KEY (sicon_act) REFERENCES psd2_consent_ais (sifra_con);

ALTER TABLE psd2_consent_action_cof ADD FOREIGN KEY (sicon_act) REFERENCES psd2_consent_cof (sifra_con);


INSERT INTO psd2_authorization_object_type ( name_aty) VALUES ('psd2_tpp_nalog');
INSERT INTO psd2_authorization_object_type ( name_aty) VALUES ('psd2_consent_ais');
INSERT INTO psd2_authorization_object_type ( name_aty) VALUES ('psd2_basket');
INSERT INTO psd2_authorization_object_type ( name_aty) VALUES ('psd2_consent_cof');
