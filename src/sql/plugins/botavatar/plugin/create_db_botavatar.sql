
--
-- Structure for table botavatar_associations
--

DROP TABLE IF EXISTS botavatar_associations;
CREATE TABLE botavatar_associations (
id_avatar_association int AUTO_INCREMENT,
response long varchar NOT NULL,
avatar_url long varchar NOT NULL,
PRIMARY KEY (id_avatar_association)
);
