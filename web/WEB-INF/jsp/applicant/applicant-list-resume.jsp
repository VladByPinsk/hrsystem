<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/WEB-INF/tld/paging.tlg" prefix="pt"%>
<c:if test="${sessionScope.locale!=null}">
	<fmt:setLocale value="${sessionScope.locale}" />
</c:if>
<fmt:setBundle basename="resource.locale" var="locale" />
<fmt:message bundle="${locale}" key="locale.add.resume" var="resume" />
<fmt:message bundle="${locale}"
	key="locale.applicant.office.privateOffice" var="privateOffice" />
<fmt:message bundle="${locale}" key="locale.list.resume.add" var="add" />
<fmt:message bundle="${locale}" key="locale.list.resume.listResume"
	var="listResume" />
<fmt:message bundle="${locale}" key="locale.list.resume.publishDate"
	var="publishDate" />
<fmt:message bundle="${locale}" key="locale.list.resume.deleteResume"
	var="deleteResume" />
<fmt:message bundle="${locale}" key="locale.list.resume.areYouSure"
	var="areYouSure" />
<fmt:message bundle="${locale}" key="locale.list.resume.remove"
	var="remove" />
<fmt:message bundle="${locale}" key="locale.list.resume.close"
	var="resClose" />
<fmt:message bundle="${locale}" key="locale.list.resume.open" var="open" />
<fmt:message bundle="${locale}" key="locale.list.resume.edit" var="edit" />
<fmt:message bundle="${locale}"
	key="locale.applicant.office.header.profile" var="profile" />
<fmt:message bundle="${locale}"
	key="locale.applicant.office.header.resume" var="resume" />


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
		<c:if test="${requestScope.countResume < 3 }">
			<form action="Controller" method="get">
				<input type="hidden" name="command" value="to-applicant-add-resume">
				<div class="left-menu clearfix">
					<input type="submit" class="btn btn-success btn-lg" value="${add}">
				</div>
			</form>
		</c:if>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">${listResume}</h3>
			</div>
			<c:forEach items="${requestScope.listResumeByEmail}" var="resume">
				<div class="panel-body">
					<div class="thumbnail">
						<div class="caption">
							<h3>${resume.name}</h3>
							<p>${publishDate}${resume.publishDate}</p>
							<p>
								<a
									href="Controller?command=show-resume&idResume=${resume.idResume}"
									class="btn btn-primary" role="button">${open}</a> <a
									href="Controller?command=to-applicant-edit-resume&idResume=${resume.idResume}"
									class="btn btn-default" role="button">${edit}</a>
								<button class="btn btn-danger" data-toggle="modal"
									data-target="#${resume.idResume}">${remove}</button>
							</p>
						</div>
					</div>
				</div>
				<div class="modal fade" id="${resume.idResume}" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h3 class="modal-title">${deleteResume}</h3>
							</div>
							<div class="modal-body">
								<p>${areYouSure}"${resume.name}"?</p>
							</div>

							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">${resClose}</button>
								<a class="btn btn-primary"
									href="Controller?command=delete-resume&idResume=${resume.idResume}">${remove}</a>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>