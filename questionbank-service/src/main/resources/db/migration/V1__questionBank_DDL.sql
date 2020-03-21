-- Create Chapter Table

CREATE TABLE parent_question (
  id VARCHAR(50) NOT NULL,
  question_description VARCHAR(800) NOT NULL,
  question_category VARCHAR(45) NULL,
  img_location VARCHAR(250) NULL,
  PRIMARY KEY (id))
COMMENT = 'Parent question table';

CREATE TABLE if NOT EXISTS chapter (
  id int(11) NOT NULL,
  chapter_name varchar(150) NOT NULL,
  description varchar(200) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Chapter name. Keep some information about chapter';


-- Create Class Table

CREATE TABLE if NOT EXISTS class (
  id int(11) NOT NULL,
  class_name varchar(45) NOT NULL,
  description varchar(100) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Information about class. Question and class linking';


CREATE TABLE if NOT EXISTS question (
  id varchar(45) NOT NULL,
  question_description varchar(700) NOT NULL,
  question_options json NOT NULL COMMENT 'Keep options in JSON format. ',
  question_correct_answer json NOT NULL,
  question_complexity_level varchar(55) NOT NULL COMMENT 'Complexity level of the question',
  question_metadata json DEFAULT NULL COMMENT 'Can store extra information about question in JSOn format. example Image location',
  question_owner_details json NOT NULL COMMENT 'Owner details of the question',
  question_hint json DEFAULT NULL COMMENT 'Question hint stored in JSON format',
  class_id int(11) NOT NULL,
  chapter_id int(11) NOT NULL,
  PRIMARY KEY (id),
  KEY question_chapter_id_idx (chapter_id),
  KEY question_class_id (class_id),
  CONSTRAINT question_chapter_id FOREIGN KEY (chapter_id) REFERENCES chapter (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT question_class_id FOREIGN KEY (class_id) REFERENCES class (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Main table keep details about question';


