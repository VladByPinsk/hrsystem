package by.training.hrsystem.service.parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import by.training.hrsystem.domain.role.Role;
import by.training.hrsystem.domain.type.CurrencyType;
import by.training.hrsystem.domain.type.EducationType;
import by.training.hrsystem.domain.type.EmploymentType;
import by.training.hrsystem.domain.type.InterviewType;
import by.training.hrsystem.domain.type.LanguageLevelType;
import by.training.hrsystem.domain.type.MilitaryType;
import by.training.hrsystem.domain.type.PostgraduateType;
import by.training.hrsystem.domain.type.SkillType;
import by.training.hrsystem.service.parser.exception.ParserException;

public final class Parser {

	private static final String DATE_FULL_PATTERN = "yyyy-MM-dd";
	private static final String DATE_YEAR_PATTERN = "yyyy";
	private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

	private Parser() {
	}

	private static Date parseStringtoDate(String field, String datePattern) throws ParserException {
		Date date = null;
		try {
			DateFormat df = new SimpleDateFormat(datePattern);
			date = df.parse(field);
		} catch (ParseException e) {
			throw new ParserException("Can't parse string to date");
		}
		return date;
	}

	public static Role fromStringToRole(String value) throws ParserException {
		if (value != null) {
			for (Role role : Role.values()) {
				if (value.equalsIgnoreCase(role.getRole())) {
					return role;
				}
			}
		}
		throw new ParserException("Can't find such role");

	}

	public static SkillType fromStringToSkill(String value) throws ParserException {
		if (value != null) {
			for (SkillType st : SkillType.values()) {
				if (value.equalsIgnoreCase(st.getSkillType())) {
					return st;
				}
			}
		}
		throw new ParserException("Can't find such skillType");

	}

	public static LanguageLevelType fromStringToLanguageLevel(String value) throws ParserException {
		if (value != null) {
			for (LanguageLevelType lt : LanguageLevelType.values()) {
				if (value.equalsIgnoreCase(lt.getLanguageLevelType())) {
					return lt;
				}
			}
		}
		throw new ParserException("Can't find such skillType");

	}

	public static EducationType fromStringToEducType(String value) throws ParserException {
		if (value != null) {
			for (EducationType et : EducationType.values()) {
				if (value.equalsIgnoreCase(et.getEducationType())) {
					return et;
				}
			}
		}
		throw new ParserException("Can't find such educationType");
	}

	public static PostgraduateType fromStringToPostGradType(String value) throws ParserException {
		if (value != null) {
			for (PostgraduateType pt : PostgraduateType.values()) {
				if (value.equalsIgnoreCase(pt.getPostgraduateType())) {
					return pt;
				}
			}
		}
		throw new ParserException("Can't find such educationType");
	}

	public static MilitaryType fromStringToMilitaryType(String value) throws ParserException {
		if (value != null) {
			for (MilitaryType mt : MilitaryType.values()) {
				if (value.equalsIgnoreCase(mt.getMillatryType())) {
					return mt;
				}
			}
		}
		throw new ParserException("Can't find such military type");
	}

	public static CurrencyType fromStringToCurrencyType(String value) throws ParserException {
		if (value != null) {
			for (CurrencyType ct : CurrencyType.values()) {
				if (value.equalsIgnoreCase(ct.getCurrencyType())) {
					return ct;
				}
			}
		}
		throw new ParserException("Can't find such military type");
	}

	public static EmploymentType fromStringToEmplType(String value) throws ParserException {
		if (value != null) {
			for (EmploymentType et : EmploymentType.values()) {
				if (value.equalsIgnoreCase(et.getCurrencyType())) {
					return et;
				}
			}
		}
		throw new ParserException("Can't find such military type");
	}

	public static InterviewType fromStringToInterviewType(String value) throws ParserException {
		if (value != null) {
			for (InterviewType it : InterviewType.values()) {
				if (value.equalsIgnoreCase(it.getInterviewType())) {
					return it;
				}
			}
		}
		throw new ParserException("Can't find such interview type");
	}

	public static Date parseToFullDate(String field) throws ParserException {
		return parseStringtoDate(field, DATE_FULL_PATTERN);
	}

	public static Date parseToShorterForm(String field) throws ParserException {
		return parseStringtoDate(field, DATE_YEAR_PATTERN);
	}

	public static Date parseToDateTime(String field) throws ParserException {
		return parseStringtoDate(field, DATE_TIME_PATTERN);
	}

	public static Integer parseStringtoInt(String field) {
		int number;
		number = Integer.parseInt(field);
		return number;

	}
}
