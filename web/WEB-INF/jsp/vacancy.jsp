<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="EN" />
<c:if test="${sessionScope.locale!=null}">
	<fmt:setLocale value="${sessionScope.locale}" />
</c:if>
<fmt:setBundle basename="resource.locale" var="locale" />
<fmt:message bundle="${locale}" key="locale.mainPage" var="mainPage" />
<fmt:message bundle="${locale}" key="locale.vacancy" var="vacancy" />
<fmt:message bundle="${locale}" key="locale.vacancy.contactPerson"
	var="contactPerson" />
<fmt:message bundle="${locale}" key="locale.vacancy.contactPhone"
	var="contactPhone" />
<fmt:message bundle="${locale}" key="locale.vacancy.skype" var="skype" />
<fmt:message bundle="${locale}" key="locale.vacancy.wage" var="wage" />
<fmt:message bundle="${locale}" key="locale.vacancy.duties" var="duties" />
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
<fmt:message bundle="${locale}" key="locale.vacancy.chooseVacancy"
	var="chooseVacancy" />
<fmt:message bundle="${locale}" key="locale.vacancy.apply" var="apply" />
<fmt:message bundle="${locale}" key="locale.vacancy.noVacancy"
	var="noVacancy" />
<fmt:message bundle="${locale}" key="locale.vacancy.leftVacancy"
	var="leftVacancy" />


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ru">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${vacancy}</title>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
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
	<%@include file="/WEB-INF/jspf/header.jspf"%>
	<div class="col-xs-2 visible-lg" id="sidebar" role="navigation">
		<div class="list-group">
			<a href="#" class="list-group-item active">
				<h4 class="list-group-item-heading">${contactPerson}</h4>
			</a> <a href="#" class="list-group-item">
				<p class="list-group-item-text">${requestScope.hr.surname}
					${requestScope.hr.name} ${requestScope.hr.secondName}</p>
				<p class="list-group-item-text">${contactPhone}</p>
				<p class="list-group-item-text">${requestScope.hr.contactPhone}</p>
				<p class="list-group-item-text">${skype}</p>
				<p class="list-group-item-text">${requestScope.hr.skype}</p>

			</a>
		</div>
	</div>
	<div class="container">
		<ol class="breadcrumb">
			<li><a href="Controller?command=to-index-page">${mainPage}</a></li>
			<li class="active"><a href="#">${requestScope.vacancy.name}</a></li>
		</ol>
		<h1>${requestScope.vacancy.name}:</h1>
		<p>${wage}${requestScope.vacancy.salary}
			<c:choose>
				<c:when test="${requestScope.vacancy.currency=='RUB'}">
		${rub}
		</c:when>
				<c:when test="${requestScope.vacancy.currency=='DOLAR'}">
		${dolar}
		</c:when>
			</c:choose>
		</p>
		<p>${requestScope.vacancy.description}</p>
		<p>
			<strong>${duties}</strong>
		</p>
		<p>${requestScope.vacancy.duty}</p>
		<p>
			<strong>${terms}</strong>
		</p>
		<p>${requestScope.vacancy.condition}</p>
		<p>
			<strong>${typeOfEmployment}</strong>
		</p>
		<c:choose>
			<c:when test="${requestScope.vacancy.employmentType == 'FULL_TIME'}">
				<p>${fullTime}</p>
			</c:when>
			<c:when test="${requestScope.vacancy.employmentType == 'PART_TIME'}">
				<p>${partTime}</p>
			</c:when>
			<c:when
				test="${requestScope.vacancy.employmentType == 'CONTRACTUAL'}">
				<p>${contractual}</p>
			</c:when>
		</c:choose>
		<p class="visible-xs visible-sm visible-md">
			<strong>${contactPerson}:</strong>
		</p>
		<p class="visible-xs visible-sm visible-md">${requestScope.hr.surname}
			${requestScope.hr.name} ${requestScope.hr.secondName}</p>
		<p class="visible-xs visible-sm visible-md">
			<strong>${contactPhone}</strong>
		</p>
		<p class="visible-xs visible-sm visible-md">${requestScope.hr.contactPhone}</p>
		<p class="visible-xs visible-sm visible-md">
			<strong>${skype}</strong>
		</p>
		<p class="visible-xs visible-sm visible-md">${requestScope.hr.contactPhone}</p>


		<c:if test="${user.role=='APPLICANT'}">
			<c:choose>
				<c:when test="${empty requestScope.listLeftResume}">
					<h3>${noVacancy}</h3>
				</c:when>
				<c:otherwise>
					<h3>${leftVacancy}</h3>
					<div class="bg-success">
						<c:forEach var="leftResume" items="${requestScope.listLeftResume}">
							<p>${leftResume.name }</p>
						</c:forEach>
					</div>
				</c:otherwise>
			</c:choose>
			<h3>${chooseVacancy}</h3>
			<form action="Controller" method="post">
				<input type="hidden" name="command" value="add-resume-to-vacancy">
				<input type="hidden" value="${requestScope.vacancy.idVacancy}"
					name="vacancy-id">
				<div class="panel-body col-xs-6">
					<select class="form-control" name="idResume">
						<c:forEach var="resume" items="${requestScope.listResumeByEmail}">
							<option value="${resume.idResume}">${resume.name}</option>
						</c:forEach>
					</select> <br> <input type="submit" class="btn btn-success"
						value="${apply}">
				</div>
			</form>
		</c:if>
	</div>

	<%@include file="/WEB-INF/jspf/footer.jspf"%>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>