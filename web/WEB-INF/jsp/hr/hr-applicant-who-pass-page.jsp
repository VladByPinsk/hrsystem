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
<fmt:message bundle="${locale}"
	key="locale.applicant.office.header.profile" var="profile" />
<fmt:message bundle="${locale}" key="locale.noOnePass" var="noOnePass" />
<fmt:message bundle="${locale}" key="locale.email" var="email" />
<fmt:message bundle="${locale}" key="locale.surname" var="surname" />
<fmt:message bundle="${locale}" key="locale.name" var="name" />
<fmt:message bundle="${locale}" key="locale.secondName" var="secondName" />
<fmt:message bundle="${locale}" key="locale.contactPhone"
	var="contactPhone" />
<fmt:message bundle="${locale}" key="locale.interview" var="interview" />
<fmt:message bundle="${locale}" key="locale.sendToInterview"
	var="sendToInterview" />
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
											<h3 class="text-danger">${noOnePass}</h3>
										</c:when>
										<c:otherwise>
											<table class="table table-hover">
												<thead>
													<tr>
														<th>${email}</th>
														<th>${surname}</th>
														<th>${name}</th>
														<th>${secondName}</th>
														<th>${contactPhone}</th>
														<th>${interview}</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${requestScope.verifyList}" var="verify">
														<tr>
															<td>${verify.resume.applicant.email}</td>
															<td>${verify.resume.applicant.surname}</td>
															<td>${verify.resume.applicant.name}</td>
															<td>${verify.resume.applicant.secondName}</td>
															<td>${verify.resume.applicant.contactPhone}</td>
															<td><a
																href="Controller?command=to-applicant-interview&idVerify=${verify.idVerify}"
																class="btn btn-info" role="button">${sendToInterview}</a></td>
														</tr>
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