package by.training.hrsystem.customtag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class PageTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private static final String TOP = "<div class=\"row text-center\"><div class=\"col-lg-12\"><ul class=\"pagination\">";
	private static final String IF_FIRST_PAGE = "<li class=\"disabled\"><a href=\"#\">&laquo;</a></li>";
	private static final String BOTTOM = "</ul></div></div>";
	private int page;
	private int pageAmount;
	private String href;

	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().write(TOP);
			if (page == 1) {
				pageContext.getOut().write(IF_FIRST_PAGE);
			} else {
				pageContext.getOut().write("<li><a href=\"" + href + "&page=" + (page - 1) + "\">&laquo;</a></li>");
			}
			for (int i = 1; i <= pageAmount; i++) {
				if (i == page) {
					pageContext.getOut()
							.write("<li class=\"active\"><a href=\"" + href + "&page=" + i + "\">" + i + "</a></li>");
				} else {
					pageContext.getOut().write("<li><a href=\"" + href + "&page=" + i + "\">" + i + "</a></li>");
				}
			}
			if (page != pageAmount) {
				pageContext.getOut().write("<li><a href=\"" + href + "&page=" + (page + 1) + "\">&raquo;</a></li>");
			} else {
				pageContext.getOut().write("<li class=\"disabled\"><a href=\"#\">&raquo;</a></li>");
			}

			pageContext.getOut().write(BOTTOM);
		} catch (IOException ex) {
			throw new JspException(ex);
		}
		return SKIP_BODY;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageAmount() {
		return pageAmount;
	}

	public void setPageAmount(int pageAmount) {
		this.pageAmount = pageAmount;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String uri) {
		this.href = uri;
	}
}
