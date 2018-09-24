<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="EN" />
<c:if test="${sessionScope.locale!=null}">
	<fmt:setLocale value="${sessionScope.locale}" />
</c:if>
<fmt:setBundle basename="resource.locale" var="locale" />
<fmt:message bundle="${locale}" key="locale.vacancy.add" var="add" />
<fmt:message bundle="${locale}"
	key="locale.applicant.office.header.profile" var="profile" />
<fmt:message bundle="${locale}" key="locale.hr.office.header.vacancy"
	var="vacancy" />
<fmt:message bundle="${locale}"
	key="locale.hr.office.header.vacancyVerify" var="vacancyVerify" />
<fmt:message bundle="${locale}" key="locale.hr.office.header.interview"
	var="interview" />
<fmt:message bundle="${locale}" key="locale.vacancy.contactPerson"
	var="contactPerson" />
<fmt:message bundle="${locale}" key="locale.vacancy.contactPhone"
	var="contactPhone" />
<fmt:message bundle="${locale}" key="locale.vacancy.skype" var="skype" />
<fmt:message bundle="${locale}" key="locale.vacancy.wage" var="wage" />
<fmt:message bundle="${locale}" key="locale.vacancy.terms" var="terms" />
<fmt:message bundle="${locale}" key="locale.vacancy.rub" var="rub" />
<fmt:message bundle="${locale}" key="locale.vacancy.dolar" var="dolar" />
<fmt:message bundle="${locale}" key="locale.vacancy.typeOfEmployment"
	var="typeOfEmployment" />
<fmt:message bundle="${locale}" key="locale.vacancy.fullTime"
	var="fullTime" />
<fmt:message bundle="${locale}" key="locale.vacancy.partTime"
	var="partTime" />
<fmt:message bundle="${locale}" key="locale.vacancy.contractual"
	var="contractual" />
<fmt:message bundle="${locale}" key="locale.vacancy.edit" var="edit" />
<fmt:message bundle="${locale}" key="locale.vacancy.description"
	var="description" />
<fmt:message bundle="${locale}" key="locale.vacancy" var="vacancy" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ru">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${privateOffice}</title>
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/styleForProfile.css" rel="stylesheet">
<link href="css/footerStyle.css" rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<%@include file="/WEB-INF/jspf/navigation.jspf"%>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div class="container">
		<div class="top-nav clearfix">
			<ul class="nav nav-tabs nav-justified">
				<li><a href="Controller?command=to-private-office">${profile}</a></li>
				<li><a href="Controller?command=to-hr-list-vacancy">${vacancy}</a></li>
				<li class="active"><a href="Controller?command=to-verify-list">${vacancyVerify}</a></li>
			</ul>
		</div>
		<%@include file="/WEB-INF/jspf/resume.jspf"%>
	</div>
	<%@include file="/WEB-INF/jspf/footer.jspf"%>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>