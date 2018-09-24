package by.training.hrsystem.domain;

import java.io.Serializable;

public class ApplicationLanguage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String lang;
	private String langName;

	public ApplicationLanguage() {

	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getLangName() {
		return langName;
	}

	public void setLangName(String langName) {
		this.langName = langName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lang == null) ? 0 : lang.hashCode());
		result = prime * result + ((langName == null) ? 0 : langName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApplicationLanguage other = (ApplicationLanguage) obj;
		if (lang == null) {
			if (other.lang != null)
				return false;
		} else if (!lang.equals(other.lang))
			return false;
		if (langName == null) {
			if (other.langName != null)
				return false;
		} else if (!langName.equals(other.langName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ApplicationLanguage [lang=" + lang + ", langName=" + langName + "]";
	}

}
