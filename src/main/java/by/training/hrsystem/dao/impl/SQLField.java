package by.training.hrsystem.dao.impl;

public final class SQLField {
	public static final String DEFAULT_LANGUAGE = "RU";
	// FOR USER DAO
	public static final String USER_EMAIL = "email";
	public static final String USER_PASSWORD = "password";
	public static final String USER_SURNAME = "surname";
	public static final String USER_NAME = "name";
	public static final String USER_SECONDNAME = "secondname";
	public static final String USER_PHOTO = "photo";
	public static final String USER_SKYPE = "skype";
	public static final String USER_CONTACT_PHONE = "contact_phone";
	public static final String USER_BIRTH_DATE = "birth_date";
	public static final String USER_ROLE = "role";
	// FOR EDUCATION DAO
	public static final String EDUCATION_ID = "e.id_education";
	public static final String EDUCATION_INSTITUTION = "institution";
	public static final String EDUCATION_FACULTY = "faculty";
	public static final String EDUCATION_DEPARTMENT = "department";
	public static final String EDUCATION_EDUCATION = "e.education";
	public static final String EDUCATION_COURSE = "e.course";
	public static final String EDUCATION_GRAND_YEAR = "e.year";
	public static final String EDUCATION_POSTGRADUATE = "e.postdraduate";
	public static final String EDUCATION_ID_RESUME = "e.id_resume";
	// FOR INTERVIEW MARK
	public static final String IMARK_ID = "id_mark";
	public static final String IMARK_SKILL = "skill";
	public static final String IMARK_MARK = "mark";
	public static final String IMARK_ID_INERVIEW = "id_interview";
	// FOR LANGUAGE
	public static final String LANGUAGE_ID = "id_language";
	public static final String LANGUAGE_NAME = "name";
	public static final String LANGUAGE_RAITING = "raiting";
	public static final String LANGUAGE_ID_RESUME = "id_resume";
	// FOR RESUME
	public static final String RESUME_ID = "id_resume";
	public static final String RESUME_NAME = "name";
	public static final String RESUME_PUBLISH_DATE = "publish_date";
	public static final String RESUME_MILLATRY_TYPE = "millatry type";
	public static final String RESUME_ACTIVE_TYPE = "active";
	public static final String RESUME_APPLICANT_EMAIL = "email";
	// FOR SKILL
	public static final String SKILL_ID = "id_skill";
	public static final String SKILL_NAME = "name";
	public static final String SKILL_RAITING = "raiting";
	public static final String SKILL_ID_RESUME = "id_resume";
	// FOR VACANCY
	public static final String VACANCY_ID = "id_vacancy";
	public static final String VACANCY_NAME = "name";
	public static final String VACANCY_SALARY = "salary";
	public static final String VACANCY_CURRENCY = "currency";
	public static final String VACANCY_PUBLISH_DATE = "publish_date";
	public static final String VACANCY_DESCRIPTION = "description";
	public static final String VACANCY_CONDITIONS = "conditions";
	public static final String VACANCY_EMPLOYMENT_TYPE = "employment_type";
	public static final String VACANCY_ACTIVE_TYPE = "active";
	public static final String VACANCY_HR_EMAIL = "email";
	// FOR INTERVIEW MARK
	public static final String MARK_ID_MARK = "id_mark";
	public static final String MARK_SKILL = "skill";
	public static final String MARK_MARK = "mark";
	public static final String MARK_INTERVIEW_ID = "id_interview";
	// FOR WORKPLACE
	public static final String WORKPLACE_ID = "id_workplace";
	public static final String WORKPLACE_COMPANY_NAME = "company_name";
	public static final String WORKPLACE_POSITION = "position";
	public static final String WORKPLACE_DATE_BEGIN = "date_begin";
	public static final String WORKPLACE_DATE_END = "date_end";
	public static final String WORKPLACE_ID_RESUME = "id_resume";
	private SQLField() {
	}
}
