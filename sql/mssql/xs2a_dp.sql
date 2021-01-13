IF OBJECT_ID('dbo.alembic_version', 'U') 			IS NOT NULL  DROP TABLE dbo.alembic_version;
IF OBJECT_ID('dbo.down_times', 'U') 				IS NOT NULL  DROP TABLE dbo.down_times;
IF OBJECT_ID('dbo.http_statistics', 'U') 			IS NOT NULL  DROP TABLE dbo.http_statistics;


CREATE TABLE alembic_version (
    version_num VARCHAR(32) NOT NULL,
    PRIMARY KEY (version_num)
);


CREATE TABLE down_times (
    id INTEGER IDENTITY(1, 1)  NOT NULL,
    type_id INTEGER,
    start_on DATETIME2(6),
    end_on DATETIME2(6),
    es_id_list NTEXT,
    PRIMARY KEY (id)
);

CREATE TABLE http_statistics (
    id INTEGER IDENTITY(1, 1)  NOT NULL,
    date DATE,
    latency_pis_api INTEGER,
    latency_pis_ib INTEGER,
    latency_ais_api INTEGER,
    latency_ais_ib INTEGER,
    latency_piis_api INTEGER,
    latency_piis_ib INTEGER,
    requests_pis_api INTEGER,
    requests_pis_ib INTEGER,
    requests_ais_api INTEGER,
    requests_ais_ib INTEGER,
    requests_piis_api INTEGER,
    requests_piis_ib INTEGER,
    errors_pis_5xx INTEGER,
    errors_pis_4xx INTEGER,
    errors_ais_5xx INTEGER,
    errors_ais_4xx INTEGER,
    errors_piis_5xx INTEGER,
    errors_piis_4xx INTEGER,
    bytes_sent_ais_api INTEGER,
    bytes_sent_ais_ib INTEGER,
    bytes_sent_piis_api INTEGER,
    bytes_sent_piis_ib INTEGER,
    bytes_sent_pis_api INTEGER,
    bytes_sent_pis_ib INTEGER,
    PRIMARY KEY (id),
    UNIQUE (date)
);
