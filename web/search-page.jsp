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
<fmt:message bundle="${locale}" key="locale.index.hotVacancies"
	var="hotVacancies" />
<fmt:message bundle="${locale}" key="locale.mainPage" var="mainPage" />
<fmt:message bundle="${locale}" key="locale.index.listOfVacancies"
	var="listOfVacancies" />
<fmt:message bundle="${locale}" key="locale.reg.email" var="email" />
<fmt:message bundle="${locale}" key="locale.reg.enterEmail"
	var="enterEmail" />
<fmt:message bundle="${locale}" key="locale.searchVacancy"
	var="searchVacancy" />
<fmt:message bundle="${locale}" key="locale.search" var="search" />
<fmt:message bundle="${locale}" key="locale.vacancy.rub" var="rub" />
<fmt:message bundle="${locale}" key="locale.vacancy.dolar" var="dolar" />
<fmt:message bundle="${locale}" key="locale.vacancy.wage" var="wage" />
<fmt:message bundle="${locale}" key="locale.publishDate"
	var="publishDate" />
<fmt:message bundle="${locale}" key="locale.searchVacancy"
	var="searchVacancy" />
<fmt:message bundle="${locale}" key="locale.search" var="search" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ru">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>IFoundJob</title>
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

	<div class="container">
		<ol class="breadcrumb">
			<li><a href="Controller?command=to-index-page">${mainPage}</a></li>
			<li class="active"><a href="#">${search}</a></li>
		</ol>
		<form class="form-horizontal" action="Controller" method="post">
			<input type="hidden" name="command" value="search-vacancy">
			<div class="search-panel">
				<div class="input-group">
					<input type="text" class="form-control"
						placeholder="${searchVacancy}" name="vacancyName"> <span
						class="input-group-btn"> <input type="submit"
						class="btn btn-secondary" value="${search}">
					</span>
				</div>
			</div>
		</form>
		<h1>${listOfVacancies}</h1>
		<div class="row">

			<c:forEach var="vacancy" items="${requestScope.vacancies}">

				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<div class="caption">
							<h3>
								<a
									href="Controller?command=show-vacancy&vacancy-id=${vacancy.idVacancy}">${vacancy.name}</a>
							</h3>
							<p>${wage}
								${vacancy.salary}
								<c:choose>
									<c:when test="${vacancy.currency=='RUB'}">
								${rub}
							</c:when>
									<c:when test="${vacancy.currency=='DOLAR'}">
								${dolar}
							</c:when>
								</c:choose>
							</p>
							<p>${publishDate}:${vacancy.publishDate}</p>
						</div>
					</div>
				</div>

			</c:forEach>
		</div>
	</div>
	<%@include file="/WEB-INF/jspf/footer.jspf"%>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>