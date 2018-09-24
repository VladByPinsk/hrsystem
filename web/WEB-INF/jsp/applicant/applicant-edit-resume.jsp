<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="EN" />
<c:if test="${sessionScope.locale!=null}">
	<fmt:setLocale value="${sessionScope.locale}" />
</c:if>
<fmt:setBundle basename="resource.locale" var="locale" />
<fmt:message bundle="${locale}"
	key="locale.applicant.office.header.profile" var="profile" />
<fmt:message bundle="${locale}"
	key="locale.applicant.office.header.resume" var="resume" />
<fmt:message bundle="${locale}" key="locale.resume.add" var="add" />
<fmt:message bundle="${locale}" key="locale.resume.resumeName"
	var="resumeName" />
<fmt:message bundle="${locale}" key="locale.resume.enterResumeName"
	var="enterResumeName" />
<fmt:message bundle="${locale}" key="locale.resume.wrongResumeName"
	var="wrongResumeName" />
<fmt:message bundle="${locale}" key="locale.resume.military"
	var="military" />
<fmt:message bundle="${locale}"
	key="locale.resume.military.notSpecified" var="notSpecified" />
<fmt:message bundle="${locale}" key="locale.resume.military.fit"
	var="fit" />
<fmt:message bundle="${locale}" key="locale.resume.military.notFit"
	var="notFit" />
<fmt:message bundle="${locale}"
	key="locale.resume.military.militaryDepartment"
	var="militaryDepartment" />
<fmt:message bundle="${locale}" key="locale.resume.military.notBound"
	var="notBound" />
<fmt:message bundle="${locale}" key="locale.resume.addResume"
	var="addResume" />


<fmt:message bundle="${locale}" key="locale.remove" var="remove" />
<fmt:message bundle="${locale}" key="locale.edit" var="edit" />
<fmt:message bundle="${locale}" key="locale.resume.language"
	var="language" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ru">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${update}</title>
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
				<li class="active"><a
					href="Controller?command=to-applicant-list-resume">${resume}</a></li>
			</ul>
		</div>
		<div class="panel-group clearfix">
			<div class="panel panel-primary clearfix">
				<div class="panel-heading clearfix">
					<h3 class="panel-title pull-left">${requestScope.resume.name}</h3>
				</div>
				<div class="panel-body">
					<%@include file="/WEB-INF/jspf/education.jspf"%>
					<%@include file="/WEB-INF/jspf/language.jspf"%>
					<%@include file="/WEB-INF/jspf/skill.jspf"%>
					<%@include file="/WEB-INF/jspf/workPlace.jspf"%>
				</div>
			</div>
		</div>

	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/addEducationValidation.js"></script>
	<script src="js/editEducationValidation.js"></script>
	<script src="js/addResumeLanguageValidation.js"></script>
	<script src="js/chooseLanguage.js"></script>
	<script src="js/editResumeLanguageValidation.js"></script>
</body>
</html>