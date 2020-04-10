-- Create Chapter Table

CREATE TABLE if NOT EXISTS parent_question (
  parentQuestionId VARCHAR(40) NOT NULL,
  questionDescription VARCHAR(800) NOT NULL,
  questionCategory VARCHAR(45) NULL,
  PRIMARY KEY (parentQuestionId))
COMMENT = 'Parent question table';

-- Chapter table
CREATE TABLE if NOT EXISTS chapter (
  chapterId int(11) NOT NULL,
  chapterName varchar(100) NOT NULL,
  description varchar(200) NOT NULL,
  PRIMARY KEY (chapterId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Chapter name. Keep some information about chapter';


-- Create Class Table
CREATE TABLE if NOT EXISTS class (
  classId int(11) NOT NULL,
  className varchar(45) NOT NULL,
  description varchar(100) DEFAULT NULL,
  PRIMARY KEY (classId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Information about class. Question and class linking';

-- user details table
CREATE TABLE if NOT EXISTS user (
  userId VARCHAR(40) NOT NULL,
  userName VARCHAR(55) NOT NULL,
  emailId VARCHAR(60) NULL,
  mobile VARCHAR(12) NULL,
  PRIMARY KEY (userId)
  )ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE if NOT EXISTS question (
  questionId VARCHAR(45) NOT NULL,
  questionDescription VARCHAR(700) ,
  questionheader VARCHAR(50) ,
  questionOptions json NOT NULL COMMENT 'Keep options in JSON format. ',
  questionCorrectAnswer json NOT NULL,
  questionComplexityLevel VARCHAR(30) NOT NULL COMMENT 'Complexity level of the question',
  questionMetadata json DEFAULT NULL COMMENT 'Can store extra information about question in JSOn format. example Image location',
  questionHint json DEFAULT NULL COMMENT 'Question hint stored in JSON format',
  classId int(11) NOT NULL,
  chapterId int(11) NOT NULL,
  PRIMARY KEY (questionId),
  KEY question_chapter_id_idx (chapterId),
  KEY question_class_id (classId),
  CONSTRAINT question_chapter_id FOREIGN KEY (chapterId) REFERENCES chapter (chapterId) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT question_class_id FOREIGN KEY (classId) REFERENCES class (classId) ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE if NOT EXISTS image (
  imageId varchar(45) NOT NULL,
  questionDescriptionImage mediumblob,
  scannedQuestionImage mediumblob,
  parentQuestionImage mediumblob,
  questionHintImage mediumblob,
  questionId VARCHAR(40),
  parentQuestionId VARCHAR(40),
  PRIMARY KEY (imageId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Question Status table
CREATE TABLE if NOT EXISTS question_status (
  questionStatusId INT NOT NULL,
  createdBy varchar(45) NULL,
  isVerified TINYINT NULL,
  verifiedBy varchar(45) NULL,
  verifiedDate TIMESTAMP NULL,
  isApproved TINYINT NULL,
  approvedBy varchar(45) NULL,
  approvedDate TIMESTAMP NULL,
  questionId varchar(45) NOT NULL,
  PRIMARY KEY (questionStatusId),
    INDEX question_status_questionId_idx (questionId)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT = 'Question Status table';

















