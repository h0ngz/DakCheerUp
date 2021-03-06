-- 회원테이블
CREATE TABLE TB_MEMBER (
	MEMBER_ID       VARCHAR(20) NOT NULL, -- 회원_아이디
	MEMBER_PASSWORD VARCHAR(20) NOT NULL, -- 회원_비밀번호
	MEMBER_NAME     VARCHAR(20) NOT NULL, -- 회원_이름
	MEMBER_REG_DATE TIMESTAMP   NOT NULL  -- 회원_가입일
);

-- 회원테이블
ALTER TABLE TB_MEMBER
	ADD CONSTRAINT PK_TB_MEMBER -- 회원테이블 기본키
		PRIMARY KEY (
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_기본인적사항
CREATE TABLE TB_RESUME_PROFILE (
	MEMBER_ID           VARCHAR(20)  NOT NULL, -- 회원_아이디
	PROFILE_NAME        VARCHAR(20)  NULL,     -- 프로필_이름
	PROFILE_ENAME       VARCHAR(20)  NULL,     -- 프로필_영문이름
	PROFILE_CNAME       VARCHAR(20)  NULL,     -- 프로필_한문이름
	PROFILE_JUMIN_FRONT VARCHAR(6)   NULL,     -- 프로필_주민등록번호_앞
	PROFILE_JUMIN_BACK  VARCHAR(7)   NULL,     -- 프로필_주민등록번호_뒤
	PROFILE_PHONE       VARCHAR(14)  NULL,     -- 프로필_휴대폰번호
	PROFILE_EMAIL       VARCHAR(40)  NULL,     -- 프로필_이메일
	PROFILE_ADDR        VARCHAR(100) NULL,     -- 프로필_주소
	PROFILE_HOBBY       VARCHAR(20)  NULL,     -- 프로필_취미
	PROFILE_FORTE       VARCHAR(20)  NULL,     -- 프로필_특기
	PROFILE_RELIGION    VARCHAR(10)  NULL,     -- 프로필_종교
	PROFILE_VETERANS   VARCHAR(10)  NULL,     -- 프로필_보훈여부
	PROFILE_MARRIAGE    VARCAHR(4)   NULL,     -- 프로필_혼인여부
	PROFILE_DISABILITY  VARCHAR(8)   NULL,     -- 프로필_장애여부
	PROFILE_WEBSITE     VARCHAR(80)  NULL,     -- 프로필_개인웹사이트
	PROFILE_TWITTER     VARCHAR(20)  NULL,     -- 프로필_트위터
	PROFILE_NAVER_BLOG  VARCHAR(20)  NULL,     -- 프로필_네이버블로그
	PROFILE_TISTORY     VARCHAR(20)  NULL,     -- 프로필_티스토리
	PROFILE_INSTAGRAM   VARCHAR(20)  NULL,     -- 프로필_인스타그램
	PROFILE_FACEBOOK    VARCHAR(20)  NULL,     -- 프로필_페이스북
	PROFILE_PINTEREST   VARCHAR(20)  NULL,     -- 프로필_핀터레스트
	PROFILE_TUMBLR      VARCHAR(20)  NULL,     -- 프로필_텀블러
	PROFILE_YOUTUBE     VARCHAR(20)  NULL,     -- 프로필_유투브
	PROFILE_REG_DATE    TIMESTAMP    NULL      -- 프로필_등록일
);

-- 이력서_기본인적사항
ALTER TABLE TB_RESUME_PROFILE
	ADD CONSTRAINT PK_TB_RESUME_PROFILE -- 이력서_기본인적사항 기본키
		PRIMARY KEY (
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_교육이수
CREATE TABLE TB_RESUME_EDUCATION (
	EDUCATION_NO       INT         NOT NULL, -- 교육이수_번호
	EDUCATION_PERIOD   VARCHAR(12) NULL,     -- 교육이수_기간
	EDUCATION_NAME     VARCHAR(40) NULL,     -- 교육이수_명
	EDUCATION_ORG      VARCHAR(30) NULL,     -- 교육이수_기관
	EDUCATION_REG_DATE TIMESTAMP   NOT NULL, -- 교육이수_등록일
	MEMBER_ID          VARCHAR(20) NULL      -- 회원_아이디
);

-- 이력서_교육이수
ALTER TABLE TB_RESUME_EDUCATION
	ADD CONSTRAINT PK_TB_RESUME_EDUCATION -- 이력서_교육이수 기본키
		PRIMARY KEY (
			EDUCATION_NO -- 교육이수_번호
		);

ALTER TABLE TB_RESUME_EDUCATION
	MODIFY COLUMN EDUCATION_NO INT NOT NULL AUTO_INCREMENT;

-- 이력서_참여프로젝트
CREATE TABLE TB_RESUME_PROJECT (
	PROJECT_NO       INT         NOT NULL, -- 프로젝트_번호
	PROJECT_PERIOD   VARCHAR(12) NULL,     -- 프로젝트_기간
	PROJECT_NAME     VARCHAR(40) NULL,     -- 프로젝트_명
	PROJECT_CONTEXT  TEXT        NULL,     -- 프로젝트_세부내용
	PROJECT_REG_DATE TIMESTAMP   NOT NULL, -- 프로젝트_등록일
	MEMBER_ID        VARCHAR(20) NULL,     -- 회원_아이디
	PROJECT_SECTION  VARCHAR(4)  NULL      -- 프로젝트_구분
);

-- 이력서_참여프로젝트
ALTER TABLE TB_RESUME_PROJECT
	ADD CONSTRAINT PK_TB_RESUME_PROJECT -- 이력서_참여프로젝트 기본키
		PRIMARY KEY (
			PROJECT_NO -- 프로젝트_번호
		);

ALTER TABLE TB_RESUME_PROJECT
	MODIFY COLUMN PROJECT_NO INT NOT NULL AUTO_INCREMENT;

-- 이력서_OA능력
CREATE TABLE TB_RESUME_OA (
	MEMBER_ID   VARCHAR(20) NOT NULL, -- 회원_아이디
	OA_DOC      VARCHAR(2)  NULL,     -- OA능력_문서작성
	OA_EXCEL    VARCHAR(2)  NULL,     -- OA능력_스프레드시트
	OA_PPT      VARCHAR(2)  NULL,     -- OA능력_프리젠테이션
	OA_INTERNET VARCHAR(2)  NULL,     -- OA능력_인터넷
	OA_GRAPHIC  VARCHAR(2)  NULL,     -- OA능력_그래픽
	OA_REG_DATE TIMESTAMP   NOT NULL  -- OA능력_등록일
);

-- 이력서_OA능력
ALTER TABLE TB_RESUME_OA
	ADD CONSTRAINT PK_TB_RESUME_OA -- 이력서_OA능력 기본키
		PRIMARY KEY (
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_병역사항
CREATE TABLE TB_RESUME_MILITARY (
	MEMBER_ID         VARCHAR(20) NOT NULL, -- 회원_아이디
	MILITARY_SECTION  VARCHAR(4)  NULL,     -- 병역_구분
	MILITARY_GROUP    VARCHAR(6)  NULL,     -- 병역_군별
	MILITARY_RANK     VARCHAR(8)  NULL,     -- 병역_계급
	MILITARY_PERIOD   VARCHAR(12) NULL,     -- 병역_복무기간
	MILITARY_WORK     VARCHAR(8)  NULL,     -- 병역_병과
	MILITARY_REASON   VARCHAR(40) NULL,     -- 병역_면제사유
	MILITARY_REG_DATE TIMESTAMP   NOT NULL  -- 병역_등록일
);

-- 이력서_병역사항
ALTER TABLE TB_RESUME_MILITARY
	ADD CONSTRAINT PK_TB_RESUME_MILITARY -- 이력서_병역사항 기본키
		PRIMARY KEY (
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_저술내역
CREATE TABLE TB_RESUME_WRITE (
	WRITE_NO       INT         NOT NULL, -- 저술내역_번호
	WRITE_DATE     VARCHAR(6)  NULL,     -- 저술내역_발행일
	WRITE_SECTION  VARCHAR(10) NULL,     -- 저술내역_분류
	WRITE_NAME     VARCHAR(40) NULL,     -- 저술내역_저술명
	WRITE_ORG      VARCHAR(40) NULL,     -- 저술내역_발행처
	WRITE_REG_DATE TIMESTAMP   NULL,     -- 저술내역_등록일
	MEMBER_ID      VARCHAR(20) NULL      -- 회원_아이디
);

-- 이력서_저술내역
ALTER TABLE TB_RESUME_WRITE
	ADD CONSTRAINT PK_TB_RESUME_WRITE -- 이력서_저술내역 기본키
		PRIMARY KEY (
			WRITE_NO -- 저술내역_번호
		);

ALTER TABLE TB_RESUME_WRITE
	MODIFY COLUMN WRITE_NO INT NOT NULL AUTO_INCREMENT;

-- 이력서_가족관계
CREATE TABLE TB_RESUME_FAMILY (
	FAMILY_NO       INT         NOT NULL, -- 가족관계_번호
	FAMILY_RELATION VARCHAR(10) NULL,     -- 가족관계_관계
	FAMILY_NAME     VARCHAR(20) NULL,     -- 가족관계_성명
	FAMILY_AGE      INT         NULL,     -- 가족관계_연령
	FAMILY_JOB      VARCHAR(16) NULL,     -- 가족관계_현재직업
	FAMILY_LIVE     VARCHAR(6)  NULL,     -- 가족관계_동거여부
	FAMILY_REG_DATE TIMESTAMP   NOT NULL, -- 가족관계_등록일
	MEMBER_ID       VARCHAR(20) NULL      -- 회원_아이디
);

-- 이력서_가족관계
ALTER TABLE TB_RESUME_FAMILY
	ADD CONSTRAINT PK_TB_RESUME_FAMILY -- 이력서_가족관계 기본키
		PRIMARY KEY (
			FAMILY_NO -- 가족관계_번호
		);

ALTER TABLE TB_RESUME_FAMILY
	MODIFY COLUMN FAMILY_NO INT NOT NULL AUTO_INCREMENT;

-- 이력서_자격증
CREATE TABLE TB_RESUME_LICENSE (
	LICENSE_NO       INT         NOT NULL, -- 자격증_번호
	LICENSE_REG_DATE TIMESTAMP   NOT NULL, -- 자격증_등록일
	LICENSE_DATE     VARCHAR(8)  NULL,     -- 자격증_취득일
	LICENSE_NAME     VARCHAR(30) NULL,     -- 자격증_명
	LICENSE_ORG      VARCHAR(30) NULL,     -- 자격증_기관
	MEMBER_ID        VARCHAR(20) NULL,     -- 회원_아이디
	LICENSE_PASS     VARCHAR(8)  NULL      -- 자격증_합격구분
);

-- 이력서_자격증
ALTER TABLE TB_RESUME_LICENSE
	ADD CONSTRAINT PK_TB_RESUME_LICENSE -- 이력서_자격증 기본키
		PRIMARY KEY (
			LICENSE_NO -- 자격증_번호
		);

ALTER TABLE TB_RESUME_LICENSE
	MODIFY COLUMN LICENSE_NO INT NOT NULL AUTO_INCREMENT;

-- 이력서_가족관계_형제관계
CREATE TABLE TB_RESUME_BROTHER (
	MEMBER_ID        VARCHAR(20) NOT NULL, -- 회원_아이디
	BROTHER_MALE     INT         NULL,     -- 형제관계_남
	BROTHER_FEMALE   INT         NULL,     -- 형제관계_녀
	BROTHER_ME       INT         NULL,     -- 형제관계_째
	BROTHER_REG_DATE TIMESTAMP   NOT NULL  -- 형제관계_등록일
);

-- 이력서_가족관계_형제관계
ALTER TABLE TB_RESUME_BROTHER
	ADD CONSTRAINT PK_TB_RESUME_BROTHER -- 이력서_가족관계_형제관계 기본키
		PRIMARY KEY (
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_수상실적
CREATE TABLE TB_RESUME_AWARD (
	AWARD_NO       INT         NOT NULL, -- 수상실적_번호
	AWARD_DATE     VARCHAR(6)  NULL,     -- 수상실적_수상일
	AWARD_NAME     VARCHAR(40) NULL,     -- 수상실적_명
	AWARD_ORG      VARCHAR(30) NULL,     -- 수상실적_기관
	AWARD_SECTION  VARCHAR(4)  NULL,     -- 수상실적_구분
	AWARD_REG_DATE TIMESTAMP   NOT NULL, -- 수상실적_등록일
	MEMBER_ID      VARCHAR(20) NULL      -- 회원_아이디
);

-- 이력서_수상실적
ALTER TABLE TB_RESUME_AWARD
	ADD CONSTRAINT PK_TB_RESUME_AWARD -- 이력서_수상실적 기본키
		PRIMARY KEY (
			AWARD_NO -- 수상실적_번호
		);

ALTER TABLE TB_RESUME_AWARD
	MODIFY COLUMN AWARD_NO INT NOT NULL AUTO_INCREMENT;

-- 이력서
CREATE TABLE TB_RESUME (
	MEMBER_ID              VARCHAR(20) NOT NULL, -- 회원_아이디
	RESUME_FIRST_REG_DATE  TIMESTAMP   NULL,     -- 이력서_최초_등록일
	RESUME_LAST_REG_DATE   TIMESTAMP   NULL,     -- 이력서_최근_등록일
	RESUME_LAST_PRINT_DATE TIMESTAMP   NULL      -- 이력서_최근_프린트일
);

-- 이력서
ALTER TABLE TB_RESUME
	ADD CONSTRAINT PK_TB_RESUME -- 이력서 기본키
		PRIMARY KEY (
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_SWOT분석
CREATE TABLE TB_RESUME_SWOT (
	MEMBER_ID          VARCHAR(20) NOT NULL, -- 회원_아이디
	SWOT_STRENGTH      TEXT        NULL,     -- SWOT_강점
	SWOT_WEAKNESS      TEXT        NULL,     -- SWOT_약점
	SWOT_OPPORTUNITIES TEXT        NULL,     -- SWOT_기회
	SWOT_THREATS       TEXT        NULL,     -- SWOT_위협
	SWOT_SO            TEXT        NULL,     -- SWOT_SO전략
	SWOT_ST            TEXT        NULL,     -- SWOT_ST전략
	SWOT_WO            TEXT        NULL,     -- SWOT_WO전략
	SWOT_WT            TEXT        NULL,     -- SWOT_WT전략
	SWOT_REG_DATE      TIMESTAMP   NULL      -- SWOT_등록일
);

-- 이력서_SWOT분석
ALTER TABLE TB_RESUME_SWOT
	ADD CONSTRAINT PK_TB_RESUME_SWOT -- 이력서_SWOT분석 기본키
		PRIMARY KEY (
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_어학능력
CREATE TABLE TB_RESUME_LANGUAGE_ABILITY (
	LANG_ABILITY_NO           INT         NOT NULL, -- 어학능력_번호
	LANG_ABILITY_NAME         VARCHAR(20) NULL,     -- 어학능력_언어명
	LANG_ABILITY_CONVERSATION VARCHAR(2)  NULL,     -- 어학능력_회화능력
	LANG_ABILITY_COMPOSITION  VARCHAR(2)  NULL,     -- 어학능력_작문능력
	LANG_ABILITY_READING      VARCHAR(2)  NULL,     -- 어학능력_독해능력
	LANG_ABILITY_REG_DATE     TIMESTAMP   NOT NULL, -- 어학능력_등록일
	MEMBER_ID                 VARCHAR(20) NULL      -- 회원_아이디
);

-- 이력서_어학능력
ALTER TABLE TB_RESUME_LANGUAGE_ABILITY
	ADD CONSTRAINT PK_TB_RESUME_LANGUAGE_ABILITY -- 이력서_어학능력 기본키
		PRIMARY KEY (
			LANG_ABILITY_NO -- 어학능력_번호
		);

ALTER TABLE TB_RESUME_LANGUAGE_ABILITY
	MODIFY COLUMN LANG_ABILITY_NO INT NOT NULL AUTO_INCREMENT;

-- 이력서_어학시험
CREATE TABLE TB_RESUME_LANGUAGE_EXAM (
	LANG_EXAM_NO       INT         NOT NULL, -- 어학시험_번호
	LANG_EXAM_LNAME    VARCHAR(20) NULL,     -- 어학시험_언어명
	LANG_EXAM_ENAME    VARCHAR(45) NULL,     -- 어학시험_시험명
	LANG_EXAM_SCORE    VARCHAR(10) NULL,     -- 어학시험_점수
	LANG_EXAM_TYPE     VARCHAR(4)  NULL,     -- 어학시험_점수구분
	LANG_EXAM_ORG      VARCHAR(30) NULL,     -- 어학시험_증빙기관
	LANG_EXAM_REG_DATE TIMESTAMP   NOT NULL, -- 어학시험_등록일
	MEMBER_ID          VARCHAR(20) NULL      -- 회원_아이디
);

-- 이력서_어학시험
ALTER TABLE TB_RESUME_LANGUAGE_EXAM
	ADD CONSTRAINT PK_TB_RESUME_LANGUAGE_EXAM -- 이력서_어학시험 기본키
		PRIMARY KEY (
			LANG_EXAM_NO -- 어학시험_번호
		);

ALTER TABLE TB_RESUME_LANGUAGE_EXAM
	MODIFY COLUMN LANG_EXAM_NO INT NOT NULL AUTO_INCREMENT;

-- 이력서_경력및실습
CREATE TABLE TB_RESUME_CAREER (
	CAREER_NO       INT         NOT NULL, -- 경력및실습_번호
	CAREER_PERIOD   VARCHAR(12) NULL,     -- 경력및실습_기간
	CAREER_COMPANY  VARCHAR(30) NULL,     -- 경력및실습_회사명
	CAREER_POSITION VARCHAR(30) NULL,     -- 경력및실습_직위
	CAREER_WORK     VARCHAR(30) NULL,     -- 경력및실습_담당업무
	CAREER_POST     VARCHAR(30) NULL,     -- 경력및실습_부서
	CAREER_REG_DATE TIMESTAMP   NOT NULL, -- 경력및실습_등록일
	MEMBER_ID       VARCHAR(20) NULL      -- 회원_아이디
);

-- 이력서_경력및실습
ALTER TABLE TB_RESUME_CAREER
	ADD CONSTRAINT PK_TB_RESUME_CAREER -- 이력서_경력및실습 기본키
		PRIMARY KEY (
			CAREER_NO -- 경력및실습_번호
		);

ALTER TABLE TB_RESUME_CAREER
	MODIFY COLUMN CAREER_NO INT NOT NULL AUTO_INCREMENT;

-- 이력서_봉사활동
CREATE TABLE TB_RESUME_VOLUNTARY (
	VOLUNTARY_NO       INT         NOT NULL, -- 봉사홛동_번호
	VOLUNTARY_PERIOD   VARCHAR(12) NULL,     -- 봉사홛동_기간
	VOLUNTARY_ORG      VARCHAR(30) NULL,     -- 봉사홛동_기관
	VOLUNTARY_CONTENT  VARCHAR(50) NULL,     -- 봉사활동_내용
	VOLUNTARY_REG_DATE TIMESTAMP   NOT NULL, -- 봉사활동_등록일
	MEMBER_ID          VARCHAR(20) NULL      -- 회원_아이디
);

-- 이력서_봉사활동
ALTER TABLE TB_RESUME_VOLUNTARY
	ADD CONSTRAINT PK_TB_RESUME_VOLUNTARY -- 이력서_봉사활동 기본키
		PRIMARY KEY (
			VOLUNTARY_NO -- 봉사홛동_번호
		);

ALTER TABLE TB_RESUME_VOLUNTARY
	MODIFY COLUMN VOLUNTARY_NO INT NOT NULL AUTO_INCREMENT;

-- 이력서_글로벌경험
CREATE TABLE TB_RESUME_GLOBAL (
	GLOBAL_NO       INT         NOT NULL, -- 글로벌경험_번호
	GLOBAL_PERIOD   VARCHAR(12) NULL,     -- 글로벌경험_기간
	GLOBAL_NATION   VARCHAR(20) NULL,     -- 글로벌경험_국가
	GLOBAL_PURPOSE  VARCHAR(8)  NULL,     -- 글로벌경험_목적
	GLOBAL_CONTENT  VARCHAR(50) NULL,     -- 글로벌경험_내용
	GLOBAL_REG_DATE TIMESTAMP   NOT NULL, -- 글로벌경험_등록일
	MEMBER_ID       VARCHAR(20) NULL      -- 회원_아이디
);

-- 이력서_글로벌경험
ALTER TABLE TB_RESUME_GLOBAL
	ADD CONSTRAINT PK_TB_RESUME_GLOBAL -- 이력서_글로벌경험 기본키
		PRIMARY KEY (
			GLOBAL_NO -- 글로벌경험_번호
		);

ALTER TABLE TB_RESUME_GLOBAL
	MODIFY COLUMN GLOBAL_NO INT NOT NULL AUTO_INCREMENT;

-- 이력서_강점
CREATE TABLE TB_RESUME_STRENGTH (
	STRENGTH_NO       INT         NOT NULL, -- 강점_번호
	STRENGTH_ITEM     VARCHAR(20) NULL,     -- 강점_항목
	STRENGTH_REG_DATE TIMESTAMP   NULL,     -- 등록일
	MEMBER_ID         VARCHAR(20) NULL      -- 회원_아이디
);

-- 이력서_강점
ALTER TABLE TB_RESUME_STRENGTH
	ADD CONSTRAINT PK_TB_RESUME_STRENGTH -- 이력서_강점 기본키
		PRIMARY KEY (
			STRENGTH_NO -- 강점_번호
		);

ALTER TABLE TB_RESUME_STRENGTH
	MODIFY COLUMN STRENGTH_NO INT NOT NULL AUTO_INCREMENT;

-- 이력서_성격
CREATE TABLE TB_RESUME_CHARACTER (
	CHARACTER_NO       INT         NOT NULL, -- 성격_번호
	CHARACTER_ITEM     VARCHAR(20) NULL,     -- 성격_항목
	CHARACTER_REG_DATE TIMESTAMP   NULL,     -- 성격_등록일
	MEMBER_ID          VARCHAR(20) NULL      -- 회원_아이디
);

-- 이력서_성격
ALTER TABLE TB_RESUME_CHARACTER
	ADD CONSTRAINT PK_TB_RESUME_CHARACTER -- 이력서_성격 기본키
		PRIMARY KEY (
			CHARACTER_NO -- 성격_번호
		);

ALTER TABLE TB_RESUME_CHARACTER
	MODIFY COLUMN CHARACTER_NO INT NOT NULL AUTO_INCREMENT;

-- 자소서_그룹
CREATE TABLE TB_JASO_GROUP (
	GROUP_NO       INT         NOT NULL, -- 그룹_번호
	GROUP_NAME     VARCHAR(30) NOT NULL, -- 그룹_이름
	GROUP_REG_DATE TIMESTAMP   NULL,     -- 그룹_생성일
	MEMBER_ID      VARCHAR(20) NULL      -- 회원_아이디
);

-- 자소서_그룹
ALTER TABLE TB_JASO_GROUP
	ADD CONSTRAINT PK_TB_JASO_GROUP -- 자소서_그룹 기본키
		PRIMARY KEY (
			GROUP_NO -- 그룹_번호
		);

-- 자소서_파일
CREATE TABLE TB_JASO_FILE (
	FILE_NO            INT         NOT NULL, -- 파일_번호
	GROUP_NO           INT         NULL,     -- 그룹_번호
	MEMBER_ID          VARCHAR(20) NULL,     -- 회원_아이디
	FILE_NAME          VARCHAR(30) NULL,     -- 파일_이름
	FILE_TRASH_YN      VARCHAR(1)  NULL     DEFAULT 'N', -- 파일_휴지통_상태_유무
	FILE_TRASH_DATE    TIMESTAMP   NULL,     -- 파일_휴지통_이동일
	FILE_INTEREST_YN   VARCHAR(1)  NULL     DEFAULT 'N', -- 파일_관심_유무
	FILE_INTEREST_DATE TIMESTAMP   NULL,     -- 파일_관심_저장일
	FILE_DEADLINE_DATE TIMESTAMP   NULL,     -- 파일_마감설정일
	FILE_REG_DATE      TIMESTAMP   NULL,     -- 파일_생성일
	FILE_EDIT_DATE     TIMESTAMP   NULL      -- 파일_수정일
);

-- 자소서_파일
ALTER TABLE TB_JASO_FILE
	ADD CONSTRAINT PK_TB_JASO_FILE -- 자소서_파일 기본키
		PRIMARY KEY (
			FILE_NO -- 파일_번호
		);

ALTER TABLE TB_JASO_FILE
	MODIFY COLUMN FILE_NO INT NOT NULL AUTO_INCREMENT;

-- 자소서_문항
CREATE TABLE TB_JASO_QNA (
	QNA_NO                  INT         NOT NULL, -- 문항_번호
	FILE_NO                 INT         NULL,     -- 파일_번호
	MEMBER_ID               VARCHAR(20) NULL,     -- 회원_아이디
	QNA_QUESTION            VARCHAR(60) NULL,     -- 문항_질문
	QNA_ANSWER              TEXT        NULL,     -- 문항_답변
	QNA_TEXT_LIMIT          INT         NULL,     -- 문항_글자수_제한
	QNA_TEXT_LIMIT_TYPE     VARCHAR(4)  NULL,     -- 문항_글자수_제한_형식
	QNA_TEXT_LIMIT_SPACE_YN VARCHAR(1)  NULL,     -- 문항_글자수_공백_포함여부
	QNA_INTEREST_YN         VARCHAR(1)  NULL,     -- 문항_관심_유무
	QNA_INTEREST_DATE       TIMESTAMP   NULL,     -- 문항_관심_저장일
	QNA_REG_DATE            TIMESTAMP   NULL,     -- 문항_생성일
	QNA_EDIT_DATE           TIMESTAMP   NULL      -- 문항_수정일
);

-- 자소서_문항
ALTER TABLE TB_JASO_QNA
	ADD CONSTRAINT PK_TB_JASO_QNA_SECTION -- 자소서_문항 기본키
		PRIMARY KEY (
			QNA_NO -- 문항_번호
		);

ALTER TABLE TB_JASO_QNA
	MODIFY COLUMN QNA_NO INT NOT NULL AUTO_INCREMENT;

-- 자소_문항버전
CREATE TABLE TB_JASO_QNA_VERSIONS (
	QNA_V_NO        INT         NOT NULL, -- 문항버전_번호
	QNA_V_QUESTION  VARCHAR(60) NULL,     -- 문항버전_질문
	QNA_V_ANSWER    TEXT        NULL,     -- 문항버전_답변
	QNA_V_REG_DATE  TIMESTAMP   NULL,     -- 문항버전_등록일
	QNA_V_EDIT_DATE TIMESTAMP   NULL,     -- 문항버전_수정일
	QNA_NO          INT         NULL      -- 문항_번호
);

-- 자소_문항버전
ALTER TABLE TB_JASO_QNA_VERSIONS
	ADD CONSTRAINT PK_TB_JASO_QNA_VERSIONS -- 자소_문항버전 기본키
		PRIMARY KEY (
			QNA_V_NO -- 문항버전_번호
		);

-- 자소서
CREATE TABLE TB_JASO (
	MEMBER_ID           VARCHAR(20) NOT NULL, -- 회원_아이디
	JASO_FIRST_REG_DATE TIMESTAMP   NULL,     -- 자소서_최초_등록일
	JASO_LAST_REG_DATE  TIMESTAMP   NULL      -- 자소서_최근_변경일
);

-- 자소서
ALTER TABLE TB_JASO
	ADD CONSTRAINT PK_TB_JASO -- 자소서 기본키
		PRIMARY KEY (
			MEMBER_ID -- 회원_아이디
		);

-- 자소서_휴지통
CREATE TABLE TB_JASO_TRASH (
	MEMBER_ID VARCHAR(20) NOT NULL -- 회원_아이디
);

-- 자소서_휴지통
ALTER TABLE TB_JASO_TRASH
	ADD CONSTRAINT PK_TB_JASO_TRASH -- 자소서_휴지통 기본키
		PRIMARY KEY (
			MEMBER_ID -- 회원_아이디
		);

-- 자소서_관심
CREATE TABLE TB_JASO_INTEREST (
	MEMBER_ID VARCHAR(20) NOT NULL -- 회원_아이디
);

-- 자소서_관심
ALTER TABLE TB_JASO_INTEREST
	ADD CONSTRAINT PK_TB_JASO_INTEREST -- 자소서_관심 기본키
		PRIMARY KEY (
			MEMBER_ID -- 회원_아이디
		);

-- 자소서_휴지통_항목
CREATE TABLE TB_JASO_TRASH_ITEM (
	MEMBER_ID VARCHAR(20) NULL -- 회원_아이디
);

-- 자소서_관심_항목
CREATE TABLE TB_JASO_INTEREST_ITEM (
	MEMBER_ID VARCHAR(20) NULL -- 회원_아이디
);

-- 새 테이블
CREATE TABLE TB_JASO_FILE_INTEREST (
	MEMBER_ID         VARCHAR(20)        NULL, -- 회원_아이디
	FILE_NO           INT                NULL, -- 파일_번호
	INTEREST_REG_DATE <데이터 타입 없음> NULL  -- 관심_저장일
);

-- 새 테이블2
CREATE TABLE TB_JASO_QNA_INTEREST (
	MEMBER_ID         VARCHAR(20)        NULL, -- 회원_아이디
	QNA_NO            INT                NULL, -- 문항_번호
	INTEREST_REG_DATE <데이터 타입 없음> NULL  -- 관심_저장일
);

-- 자소서_문항_로그
CREATE TABLE TB_JASO_QNA_LOG (
	QNA_LOG_NO           INT         NOT NULL, -- 문항_로그_번호
	QNA_NO               INT         NULL,     -- 문항_번호
	QNA_LOG_STATE        VARCHAR(6)  NULL,     -- 문항_로그_상태
	QNA_LOG_TMP_QUESTION VARCHAR(60) NULL,     -- 문항_로그_임시저장_질문
	QNA_LOG_TMP_ANSWER   TEXT        NULL,     -- 문항_로그_임시저장_답변
	QNA_LOG_REG_DATE     TIMESTAMP   NULL      -- 문항_로그_저장일
);

-- 자소서_문항_로그
ALTER TABLE TB_JASO_QNA_LOG
	ADD CONSTRAINT PK_TB_JASO_QNA_LOG -- 새 테이블3 기본키
		PRIMARY KEY (
			QNA_LOG_NO -- 문항_로그_번호
		);

ALTER TABLE TB_JASO_QNA_LOG
	MODIFY COLUMN QNA_LOG_NO INT NOT NULL AUTO_INCREMENT;

-- 새 테이블3
CREATE TABLE TB_RESUME_HIGHSCHOOL (
);

-- 이력서_학력
CREATE TABLE TB_RESUME_ACADEMIC (
	MEMBER_ID      VARCHAR(20) NOT NULL, -- 회원_아이디
	ACADEMIC_FINAL VARCHAR(22) NULL      -- 학력_최종학력
);

-- 이력서_학력
ALTER TABLE TB_RESUME_ACADEMIC
	ADD CONSTRAINT PK_TB_RESUME_ACADEMIC -- 이력서_학력 기본키
		PRIMARY KEY (
			MEMBER_ID -- 회원_아이디
		);

-- 학력_고등학교
CREATE TABLE TB_RESUME_ACADEMIC_HIGHSCHOOL (
	MEMBER_ID              VARCHAR(20) NOT NULL, -- 회원_아이디
	ACADEMIC_HIGH_PERIOD   VARCHAR(12) NULL,     -- 학력_고등학교_재학기간
	ACADEMIC_HIGH_AREA     VARCHAR(12) NULL,     -- 학력_고등학교_소재지
	ACADEMIC_HIGH_NAME     VARCHAR(30) NULL,     -- 학력_고등학교_학교명
	ACADEMIC_HIGH_SECTION  VARCHAR(8)  NULL,     -- 학력_고등학교_구분
	ACADEMIC_HIGH_MAJOR    VARCHAR(20) NULL,     -- 학력_고등학교_전공
	ACADEMIC_HIGH_REG_DATE TIMESTAMP   NULL      -- 학력_고등학교_등록일
);

-- 학력_고등학교
ALTER TABLE TB_RESUME_ACADEMIC_HIGHSCHOOL
	ADD CONSTRAINT PK_TB_RESUME_ACADEMIC_HIGHSCHOOL -- 학력_고등학교 기본키
		PRIMARY KEY (
			MEMBER_ID -- 회원_아이디
		);

-- 학력_대학교
CREATE TABLE TB_RESUME_ACADEMIC_UNIVERSITY (
	ACADEMIC_UNIV_NO                  INT         NOT NULL, -- 학력_대학교_번호
	MEMBER_ID                         VARCHAR(20) NULL,     -- 회원_아이디
	ACADEMIC_UNIV_PERIOD_FIRST        VARCHAR(6)  NULL,     -- 학력_대학교_재학기간_첫번째
	ACADEMIC_UNIV_PERIOD_FIRST_OPTION VARCHAR(4)  NULL,     -- 학력_대학교_재학기간_첫번째_구분
	ACADEMIC_UNIV_PERIOD_LAST         VARCHAR(6)  NULL,     -- 학력_대학교_재학기간_마지막
	ACADEMIC_UNIV_PERIOD_LAST_OPTION  VARCHAR(8)  NULL,     -- 학력_대학교_재학기간_마지막_구분
	ACADEMIC_UNIV_NAME                VARCHAR(30) NULL,     -- 학력_대학교_학교명
	ACADEMIC_UNIV_DEGREE              VARCHAR(8)  NULL,     -- 학력_대학교_학위
	ACADEMIC_UNIV_MAJOR               VARCHAR(30) NULL,     -- 학력_대학교_전공
	ACADEMIC_UNIV_SCORE               INT         NULL,     -- 학력_대학교_학점
	ACADEMIC_UNIV_SCORE_OPTION        VARCHAR(7)  NULL,     -- 학력_대학교_학점_구분
	ACADEMIC_UNIV_DOUBLE_OPTION       VARCHAR(8)  NULL,     -- 학력_대학교_부_복수전공_구분
	ACADEMIC_UNIV_DOUBLE_NAME         VARCHAR(30) NULL,     -- 학력_대학교_부_복수전공
	ACADEMIC_UNIV_REG_DATE            TIMESTAMP   NULL      -- 학력_대학교_등록일
);

-- 학력_대학교
ALTER TABLE TB_RESUME_ACADEMIC_UNIVERSITY
	ADD CONSTRAINT PK_TB_RESUME_ACADEMIC_UNIVERSITY -- 학력_대학교 기본키
		PRIMARY KEY (
			ACADEMIC_UNIV_NO -- 학력_대학교_번호
		);

ALTER TABLE TB_RESUME_ACADEMIC_UNIVERSITY
	MODIFY COLUMN ACADEMIC_UNIV_NO INT NOT NULL AUTO_INCREMENT;

-- 이력서_기본인적사항
ALTER TABLE TB_RESUME_PROFILE
	ADD CONSTRAINT FK_TB_RESUME_TO_TB_RESUME_PROFILE -- 이력서 -> 이력서_기본인적사항
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_RESUME ( -- 이력서
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_교육이수
ALTER TABLE TB_RESUME_EDUCATION
	ADD CONSTRAINT FK_TB_RESUME_TO_TB_RESUME_EDUCATION -- 이력서 -> 이력서_교육이수
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_RESUME ( -- 이력서
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_참여프로젝트
ALTER TABLE TB_RESUME_PROJECT
	ADD CONSTRAINT FK_TB_RESUME_TO_TB_RESUME_PROJECT -- 이력서 -> 이력서_참여프로젝트
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_RESUME ( -- 이력서
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_OA능력
ALTER TABLE TB_RESUME_OA
	ADD CONSTRAINT FK_TB_RESUME_TO_TB_RESUME_OA -- 이력서 -> 이력서_OA능력
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_RESUME ( -- 이력서
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_병역사항
ALTER TABLE TB_RESUME_MILITARY
	ADD CONSTRAINT FK_TB_RESUME_TO_TB_RESUME_MILITARY -- 이력서 -> 이력서_병역사항
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_RESUME ( -- 이력서
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_저술내역
ALTER TABLE TB_RESUME_WRITE
	ADD CONSTRAINT FK_TB_RESUME_TO_TB_RESUME_WRITE -- 이력서 -> 이력서_저술내역
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_RESUME ( -- 이력서
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_가족관계
ALTER TABLE TB_RESUME_FAMILY
	ADD CONSTRAINT FK_TB_RESUME_TO_TB_RESUME_FAMILY -- 이력서 -> 이력서_가족관계
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_RESUME ( -- 이력서
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_자격증
ALTER TABLE TB_RESUME_LICENSE
	ADD CONSTRAINT FK_TB_RESUME_TO_TB_RESUME_LICENSE -- 이력서 -> 이력서_자격증
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_RESUME ( -- 이력서
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_가족관계_형제관계
ALTER TABLE TB_RESUME_BROTHER
	ADD CONSTRAINT FK_TB_RESUME_TO_TB_RESUME_BROTHER -- 이력서 -> 이력서_가족관계_형제관계
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_RESUME ( -- 이력서
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_수상실적
ALTER TABLE TB_RESUME_AWARD
	ADD CONSTRAINT FK_TB_RESUME_TO_TB_RESUME_AWARD -- 이력서 -> 이력서_수상실적
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_RESUME ( -- 이력서
			MEMBER_ID -- 회원_아이디
		);

-- 이력서
ALTER TABLE TB_RESUME
	ADD CONSTRAINT FK_TB_MEMBER_TO_TB_RESUME -- 회원테이블 -> 이력서
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_MEMBER ( -- 회원테이블
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_SWOT분석
ALTER TABLE TB_RESUME_SWOT
	ADD CONSTRAINT FK_TB_RESUME_TO_TB_RESUME_SWOT -- 이력서 -> 이력서_SWOT분석
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_RESUME ( -- 이력서
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_어학능력
ALTER TABLE TB_RESUME_LANGUAGE_ABILITY
	ADD CONSTRAINT FK_TB_RESUME_TO_TB_RESUME_LANGUAGE_ABILITY -- 이력서 -> 이력서_어학능력
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_RESUME ( -- 이력서
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_어학시험
ALTER TABLE TB_RESUME_LANGUAGE_EXAM
	ADD CONSTRAINT FK_TB_RESUME_TO_TB_RESUME_LANGUAGE_EXAM -- 이력서 -> 이력서_어학시험
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_RESUME ( -- 이력서
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_경력및실습
ALTER TABLE TB_RESUME_CAREER
	ADD CONSTRAINT FK_TB_RESUME_TO_TB_RESUME_CAREER -- 이력서 -> 이력서_경력및실습
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_RESUME ( -- 이력서
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_봉사활동
ALTER TABLE TB_RESUME_VOLUNTARY
	ADD CONSTRAINT FK_TB_RESUME_TO_TB_RESUME_VOLUNTARY -- 이력서 -> 이력서_봉사활동
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_RESUME ( -- 이력서
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_글로벌경험
ALTER TABLE TB_RESUME_GLOBAL
	ADD CONSTRAINT FK_TB_RESUME_TO_TB_RESUME_GLOBAL -- 이력서 -> 이력서_글로벌경험
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_RESUME ( -- 이력서
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_강점
ALTER TABLE TB_RESUME_STRENGTH
	ADD CONSTRAINT FK_TB_RESUME_TO_TB_RESUME_STRENGTH -- 이력서 -> 이력서_강점
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_RESUME ( -- 이력서
			MEMBER_ID -- 회원_아이디
		);

-- 이력서_성격
ALTER TABLE TB_RESUME_CHARACTER
	ADD CONSTRAINT FK_TB_RESUME_TO_TB_RESUME_CHARACTER -- 이력서 -> 이력서_성격
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_RESUME ( -- 이력서
			MEMBER_ID -- 회원_아이디
		);

-- 자소서_그룹
ALTER TABLE TB_JASO_GROUP
	ADD CONSTRAINT FK_TB_JASO_TO_TB_JASO_GROUP -- 자소서 -> 자소서_그룹
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_JASO ( -- 자소서
			MEMBER_ID -- 회원_아이디
		);

-- 자소서_파일
ALTER TABLE TB_JASO_FILE
	ADD CONSTRAINT FK_TB_JASO_GROUP_TO_TB_JASO_FILE -- 자소서_그룹 -> 자소서_파일
		FOREIGN KEY (
			GROUP_NO -- 그룹_번호
		)
		REFERENCES TB_JASO_GROUP ( -- 자소서_그룹
			GROUP_NO -- 그룹_번호
		);

-- 자소서_파일
ALTER TABLE TB_JASO_FILE
	ADD CONSTRAINT FK_TB_JASO_TO_TB_JASO_FILE -- 자소서 -> 자소서_파일
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_JASO ( -- 자소서
			MEMBER_ID -- 회원_아이디
		);

-- 자소서_문항
ALTER TABLE TB_JASO_QNA
	ADD CONSTRAINT FK_TB_JASO_FILE_TO_TB_JASO_QNA_SECTION -- 자소서_파일 -> 자소서_문항
		FOREIGN KEY (
			FILE_NO -- 파일_번호
		)
		REFERENCES TB_JASO_FILE ( -- 자소서_파일
			FILE_NO -- 파일_번호
		);

-- 자소서_문항
ALTER TABLE TB_JASO_QNA
	ADD CONSTRAINT FK_TB_JASO_TO_TB_JASO_QNA_SECTION -- 자소서 -> 자소서_문항
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_JASO ( -- 자소서
			MEMBER_ID -- 회원_아이디
		);

-- 자소_문항버전
ALTER TABLE TB_JASO_QNA_VERSIONS
	ADD CONSTRAINT FK_TB_JASO_QNA_SECTION_TO_TB_JASO_QNA_VERSIONS -- 자소서_문항 -> 자소_문항버전
		FOREIGN KEY (
			QNA_NO -- 문항_번호
		)
		REFERENCES TB_JASO_QNA ( -- 자소서_문항
			QNA_NO -- 문항_번호
		);

-- 자소서
ALTER TABLE TB_JASO
	ADD CONSTRAINT FK_TB_MEMBER_TO_TB_JASO -- 회원테이블 -> 자소서
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_MEMBER ( -- 회원테이블
			MEMBER_ID -- 회원_아이디
		);

-- 자소서_휴지통
ALTER TABLE TB_JASO_TRASH
	ADD CONSTRAINT FK_TB_JASO_TO_TB_JASO_TRASH -- 자소서 -> 자소서_휴지통
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_JASO ( -- 자소서
			MEMBER_ID -- 회원_아이디
		);

-- 자소서_관심
ALTER TABLE TB_JASO_INTEREST
	ADD CONSTRAINT FK_TB_JASO_TO_TB_JASO_INTEREST -- 자소서 -> 자소서_관심
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_JASO ( -- 자소서
			MEMBER_ID -- 회원_아이디
		);

-- 자소서_휴지통_항목
ALTER TABLE TB_JASO_TRASH_ITEM
	ADD CONSTRAINT FK_TB_JASO_TRASH_TO_TB_JASO_TRASH_ITEM -- 자소서_휴지통 -> 자소서_휴지통_항목
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_JASO_TRASH ( -- 자소서_휴지통
			MEMBER_ID -- 회원_아이디
		);

-- 자소서_관심_항목
ALTER TABLE TB_JASO_INTEREST_ITEM
	ADD CONSTRAINT FK_TB_JASO_INTEREST_TO_TB_JASO_INTEREST_ITEM -- 자소서_관심 -> 자소서_관심_항목
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_JASO_INTEREST ( -- 자소서_관심
			MEMBER_ID -- 회원_아이디
		);

-- 새 테이블
ALTER TABLE TB_JASO_FILE_INTEREST
	ADD CONSTRAINT FK_TB_JASO_TO_TB_JASO_FILE_INTEREST -- 자소서 -> 새 테이블
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_JASO ( -- 자소서
			MEMBER_ID -- 회원_아이디
		);

-- 새 테이블
ALTER TABLE TB_JASO_FILE_INTEREST
	ADD CONSTRAINT FK_TB_JASO_FILE_TO_TB_JASO_FILE_INTEREST -- 자소서_파일 -> 새 테이블
		FOREIGN KEY (
			FILE_NO -- 파일_번호
		)
		REFERENCES TB_JASO_FILE ( -- 자소서_파일
			FILE_NO -- 파일_번호
		);

-- 새 테이블2
ALTER TABLE TB_JASO_QNA_INTEREST
	ADD CONSTRAINT FK_TB_JASO_TO_TB_JASO_QNA_INTEREST -- 자소서 -> 새 테이블2
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_JASO ( -- 자소서
			MEMBER_ID -- 회원_아이디
		);

-- 새 테이블2
ALTER TABLE TB_JASO_QNA_INTEREST
	ADD CONSTRAINT FK_TB_JASO_QNA_SECTION_TO_TB_JASO_QNA_INTEREST -- 자소서_문항 -> 새 테이블2
		FOREIGN KEY (
			QNA_NO -- 문항_번호
		)
		REFERENCES TB_JASO_QNA ( -- 자소서_문항
			QNA_NO -- 문항_번호
		);

-- 자소서_문항_로그
ALTER TABLE TB_JASO_QNA_LOG
	ADD CONSTRAINT FK_TB_JASO_QNA_SECTION_TO_TB_JASO_QNA_LOG -- 자소서_문항 -> 자소서_문항_로그
		FOREIGN KEY (
			QNA_NO -- 문항_번호
		)
		REFERENCES TB_JASO_QNA ( -- 자소서_문항
			QNA_NO -- 문항_번호
		);

-- 이력서_학력
ALTER TABLE TB_RESUME_ACADEMIC
	ADD CONSTRAINT FK_TB_RESUME_TO_TB_RESUME_ACADEMIC -- 이력서 -> 이력서_학력
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_RESUME ( -- 이력서
			MEMBER_ID -- 회원_아이디
		);

-- 학력_고등학교
ALTER TABLE TB_RESUME_ACADEMIC_HIGHSCHOOL
	ADD CONSTRAINT FK_TB_RESUME_ACADEMIC_TO_TB_RESUME_ACADEMIC_HIGHSCHOOL -- 이력서_학력 -> 학력_고등학교
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_RESUME_ACADEMIC ( -- 이력서_학력
			MEMBER_ID -- 회원_아이디
		);

-- 학력_대학교
ALTER TABLE TB_RESUME_ACADEMIC_UNIVERSITY
	ADD CONSTRAINT FK_TB_RESUME_ACADEMIC_TO_TB_RESUME_ACADEMIC_UNIVERSITY -- 이력서_학력 -> 학력_대학교
		FOREIGN KEY (
			MEMBER_ID -- 회원_아이디
		)
		REFERENCES TB_RESUME_ACADEMIC ( -- 이력서_학력
			MEMBER_ID -- 회원_아이디
		);