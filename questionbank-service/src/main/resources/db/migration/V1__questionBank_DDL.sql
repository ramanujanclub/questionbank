-- Chapter table
CREATE TABLE if NOT EXISTS chapter (
  chapterid int(11) NOT NULL AUTO_INCREMENT,
  chaptername varchar(100) NOT NULL,
  description varchar(200) NOT NULL,
  PRIMARY KEY (chapterId)
)COMMENT='Chapter name. Keep some information about chapter';

-- Create Class Table
CREATE TABLE if NOT EXISTS class (
  classid int(11) NOT NULL AUTO_INCREMENT,
  classname varchar(45) NOT NULL,
  description varchar(100) DEFAULT NULL,
  PRIMARY KEY (classId)
) COMMENT='Information about class. Question and class linking';

-- user details table
CREATE TABLE if NOT EXISTS user (
  userid VARCHAR(40) NOT NULL,
  username VARCHAR(55) NOT NULL,
  password VARCHAR(50) NOT NULL,
  emailid VARCHAR(60) NULL,
  mobile VARCHAR(12) NULL,
  PRIMARY KEY (userId)
  );

-- Question Status tab
CREATE TABLE if NOT EXISTS question_status (
  questionstatusid INT4 NOT NULL AUTO_INCREMENT,
  submittedbyuserid varchar(40) NULL,
  submitteddate TIMESTAMP NULL,
  isverified TINYINT NULL,
  verifiedbyuserid varchar(40) NULL,
  verifieddate TIMESTAMP NULL,
  isapproved TINYINT NULL,
  approvedbyuserid varchar(40) NULL,
  approveddate TIMESTAMP NULL,
  PRIMARY KEY (questionstatusid)
)COMMENT = 'Question Status table';

CREATE TABLE if NOT EXISTS parent_question (
  parentquestionid VARCHAR(40) NOT NULL,
  parentquestionheader VARCHAR(50) null,
  questiondescription VARCHAR(800) NULL,
  questionnote VARCHAR(200) NULL,
  classid int(11)  NULL,
  questioncomplexitylevel VARCHAR(30) NOT NULL COMMENT 'Complexity level of the question',
  questionstatusid INT4 NOT NULL,
  PRIMARY KEY (parentQuestionId),
  CONSTRAINT parentquestion_class_id FOREIGN KEY (classId) REFERENCES class (classId) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT parent_question_status_id FOREIGN KEY (questionstatusid) REFERENCES question_status (questionstatusid) ON DELETE NO ACTION ON UPDATE NO ACTION

)
COMMENT = 'Parent question table';

CREATE TABLE if NOT EXISTS question (
  questionid VARCHAR(45) NOT NULL,
  questiondescription VARCHAR(700) ,
  questionheader VARCHAR(50) ,
  questionoptions json NOT NULL COMMENT 'Keep options in JSON format. ',
  questioncorrectanswer json NOT NULL,
  questioncomplexitylevel VARCHAR(30) NOT NULL COMMENT 'Complexity level of the question',
  questionmetadata json DEFAULT NULL COMMENT 'Can store extra information about question in JSOn format. example Image location',
  questionhint json DEFAULT NULL COMMENT 'Question hint stored in JSON format',
  questionnote VARCHAR(200) ,
  classid int(11) NULL,
  chapterid int(11) NULL,
  parentquestionid VARCHAR(40) NULL,
  questionstatusid INT4 NOT NULL,
  PRIMARY KEY (questionId),
  KEY question_chapter_id_idx (chapterId),
  KEY question_class_id (classid),
  CONSTRAINT question_chapter_id FOREIGN KEY (chapterId) REFERENCES chapter (chapterId) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT question_class_id FOREIGN KEY (classId) REFERENCES class (classId) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT question_status_id FOREIGN KEY (questionstatusid) REFERENCES question_status (questionstatusid) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE if NOT EXISTS image (
  imageId varchar(45) NOT NULL,
  questiondescriptionimage mediumblob,
  scannedquestionimage mediumblob,
  parentquestionimage mediumblob,
  questionhintimage mediumblob,
  questionid VARCHAR(40),
  parentquestionid VARCHAR(40),
  PRIMARY KEY (imageId)
) ;

