<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/paging.tlg" prefix="pt"%>
<fmt:setLocale value="EN" />
<c:if test="${sessionScope.locale!=null}">
	<fmt:setLocale value="${sessionScope.locale}" />
</c:if>
<fmt:setBundle basename="resource.locale" var="locale" />
<fmt:message bundle="${locale}" key="locale.hr.office.header.vacancy"
	var="vacancy" />
<fmt:message bundle="${locale}"
	key="locale.hr.office.header.vacancyVerify" var="vacancyVerify" />
<fmt:message bundle="${locale}" key="locale.hr.office.header.interview"
	var="interview" />
<fmt:message bundle="${locale}" key="locale.vacancy.add" var="add" />
<fmt:message bundle="${locale}" key="locale.open" var="open" />
<fmt:message bundle="${locale}" key="locale.edit" var="edit" />
<fmt:message bundle="${locale}" key="locale.remove" var="remove" />
<fmt:message bundle="${locale}" key="locale.edit" var="edit" />
<fmt:message bundle="${locale}" key="locale.active" var="active" />
<fmt:message bundle="${locale}" key="locale.deactive" var="deactive" />
<fmt:message bundle="${locale}" key="locale.hot" var="hot" />
<fmt:message bundle="${locale}" key="locale.noVerify" var="noVerify" />
<fmt:message bundle="${locale}"
	key="locale.applicant.office.header.profile" var="profile" />
<fmt:message bundle="${locale}" key="locale.email" var="email" />
<fmt:message bundle="${locale}" key="locale.resume" var="resume" />
<fmt:message bundle="${locale}" key="locale.verify" var="verify" />
<fmt:message bundle="${locale}" key="locale.pass" var="pass" />
<fmt:message bundle="${locale}" key="locale.notPass" var="notPass" />
<fmt:message bundle="${locale}" key="locale.showWhoPass"
	var="showWhoPass" />
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
<link href="css/footerStyleForProfile.css" rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<%@include file="/WEB-INF/jspf/navigation.jspf"%>
	<div class="container">
		<br> <br> <br> <br> <br>
		<div class="container">
			<div class="container">
				<div class="top-nav clearfix">
					<ul class="nav nav-tabs nav-justified">
						<li><a href="Controller?command=to-private-office">${profile}</a></li>
						<li><a href="Controller?command=to-hr-list-vacancy">${vacancy}</a></li>
						<li class="active"><a
							href="Controller?command=to-verify-list">${vacancyVerify}</a></li>
					</ul>
				</div>
				<div class="left-menu clearfix">
					<a
						href="Controller?command=to-pass-verify-applicant&vacancy-id=${requestScope.vacancy.idVacancy}"
						class="btn btn-success btn-lg" role="button">${showWhoPass}</a>
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">${listVacancy}</h3>
					</div>
					<div class="panel-body">
						<div class="panel-body">
							<div class="thumbnail">
								<div class="caption">
									<h3>${requestScope.vacancy.name}</h3>
									<p>${publishDate}${requestScope.vacancy.publishDate}</p>
									<c:choose>
										<c:when test="${empty requestScope.verifyList}">
											<h3 class="text-danger">${noVerify}</h3>
										</c:when>
										<c:otherwise>
											<table class="table table-hover">
												<thead>
													<tr>
														<th>${email}</th>
														<th>${resume}</th>
														<th>${verify}</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${requestScope.verifyList}" var="verify">
														<c:choose>
															<c:when test="${verify.pass=='UNKNOWN'}">
																<tr>
																	<td>${verify.resume.applicant.email}</td>
																	<td><a
																		href="Controller?command=show-resume&idResume=${verify.resume.idResume}">${verify.resume.name}</a></td>
																	<td><a
																		href="Controller?command=verify-pass&idVerify=${verify.idVerify}"
																		class="btn btn-success" role="button">${pass}</a> <a
																		href="Controller?command=verify-not-pass&idVerify=${verify.idVerify}"
																		class="btn btn-danger" role="button">${notPass}</a>
																</tr>
															</c:when>
															<c:when test="${verify.pass=='PASS'}">
																<tr class="success">
																	<td>${verify.resume.applicant.email}</td>
																	<td><a
																		href="Controller?command=show-resume&idResume=${verify.resume.idResume}">${verify.resume.name}</a></td>
																	<td><a
																		href="Controller?command=verify-not-pass&idVerify=${verify.idVerify}"
																		class="btn btn-danger" role="button">${notPass}</a>
																</tr>
															</c:when>
															<c:when test="${verify.pass=='NOT_PASS'}">
																<tr class="danger">
																	<td>${verify.resume.applicant.email}</td>
																	<td><a
																		href="Controller?command=show-resume&idResume=${verify.resume.idResume}">${verify.resume.name}</a></td>
																	<td><a
																		href="Controller?command=verify-pass&idVerify=${verify.idVerify}"
																		class="btn btn-success" role="button">${pass}</a></td>
																</tr>
															</c:when>
														</c:choose>
													</c:forEach>
												</tbody>
											</table>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>