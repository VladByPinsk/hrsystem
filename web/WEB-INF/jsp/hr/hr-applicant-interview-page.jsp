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
<fmt:message bundle="${locale}" key="locale.add" var="add" />
<fmt:message bundle="${locale}" key="locale.open" var="open" />
<fmt:message bundle="${locale}" key="locale.edit" var="edit" />
<fmt:message bundle="${locale}" key="locale.remove" var="remove" />
<fmt:message bundle="${locale}" key="locale.edit" var="edit" />
<fmt:message bundle="${locale}" key="locale.active" var="active" />
<fmt:message bundle="${locale}" key="locale.deactive" var="deactive" />
<fmt:message bundle="${locale}" key="locale.hot" var="hot" />
<fmt:message bundle="${locale}"
	key="locale.applicant.office.header.profile" var="profile" />
<fmt:message bundle="${locale}" key="locale.vacancy" var="vacancy" />
<fmt:message bundle="${locale}" key="locale.applicant" var="applicant" />
<fmt:message bundle="${locale}" key="locale.skype" var="skype" />
<fmt:message bundle="${locale}" key="locale.addInterview"
	var="addInterview" />
<fmt:message bundle="${locale}" key="locale.contactPhone"
	var="contactPhone" />
<fmt:message bundle="${locale}" key="locale.typeOfInterview"
	var="typeOfInterview" />
<fmt:message bundle="${locale}" key="locale.preliminary"
	var="preliminary" />
<fmt:message bundle="${locale}" key="locale.technical" var="technical" />
<fmt:message bundle="${locale}" key="locale.addMark" var="addMark" />
<fmt:message bundle="${locale}" key="locale.dateBegin" var="date" />
<fmt:message bundle="${locale}" key="locale.mark" var="mark" />
<fmt:message bundle="${locale}" key="locale.skill" var="skill" />
<fmt:message bundle="${locale}" key="locale.resume.skill.noviceSkill"
	var="noviceSkill" />
<fmt:message bundle="${locale}"
	key="locale.resume.skill.intermediateSkill" var="intermediateSkill" />
<fmt:message bundle="${locale}" key="locale.resume.skill.advancedSkill"
	var="advancedSkill" />
<fmt:message bundle="${locale}" key="locale.resume.skill.expertSkill"
	var="expertSkill" />
<fmt:message bundle="${locale}" key="locale.deleteInterview"
	var="deleteInterview" />
<fmt:message bundle="${locale}" key="locale.deleteInterviewMark"
	var="deleteInterviewMark" />
<fmt:message bundle="${locale}" key="locale.listTechMarkIsEmapty"
	var="listTechMarkIsEmapty" />
<fmt:message bundle="${locale}" key="locale.listOfPrelimMarkIsEmpty"
	var="listOfPrelimMarkIsEmpty" />
<fmt:message bundle="${locale}" key="locale.interviewDatePattern"
	var="interviewDatePattern" />
<fmt:message bundle="${locale}" key="locale.skillPattern"
	var="skillPattern" />
<fmt:message bundle="${locale}" key="locale.enterSkillName"
	var="enterSkillName" />
<fmt:message bundle="${locale}" key="locale.enterInterviewDate"
	var="enterInterviewDate" />
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
									<h3>${vacancy}:${requestScope.verify.vacancy.name}</h3>
									<h3>${applicant}:
										${requestScope.verify.resume.applicant.surname}
										${requestScope.verify.resume.applicant.name}
										${requestScope.verify.resume.applicant.secondName}</h3>
									<p>
										<strong>${skype}:</strong>
										${requestScope.verify.resume.applicant.skype}
									</p>
									<p>
										<strong>${contactPhone}:</strong>
										${requestScope.verify.resume.applicant.contactPhone}
									</p>
									<div class="panel panel-primary">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a data-toggle="collapse" href="#collapse1">
													${addInterview}</a>
											</h4>
										</div>
										<div id="collapse1" class="panel-collapse collapse">
											<div class="panel-body">
												<form class="form-horizontal" action="Controller"
													method="post">
													<input type="hidden" name="command"
														value="add-interview-to-applicant"> <input
														type="hidden" value="${requestScope.verify.idVerify}"
														name="idVerify">
													<div class="form-group">
														<label class="control-label col-xs-3 col-md-1"
															for="interviewType">${typeOfInterview}:</label>
														<div class="col-xs-6">
															<select class="form-control" name="interviewType">
																<option value="preliminary">${preliminary}</option>
																<option value="techical">${technical}</option>
															</select>
														</div>
													</div>
													<div class="form-group">
														<div class="date">
															<label class="control-label col-xs-3 col-md-1"
																for="interviewDate">${date}:</label>
															<div class="col-xs-6">
																<input type="text" class="form-control"
																	name="interviewDate"
																	placeholder="${enterInterviewDate}" required
																	pattern="^[\d]{4}-[\d]{2}-[\d]{2} [\d]{2}:[\d]{2}$">
																<c:choose>
																	<c:when test="${requestScope.errorDate}">
																		<div class="help-block">
																			<p class="text-danger">${wrongDate}</p>
																		</div>
																	</c:when>
																	<c:otherwise>
																		<div class="help-block">
																			<p>${interviewDatePattern}</p>
																		</div>
																	</c:otherwise>
																</c:choose>
															</div>
														</div>
													</div>
													<div class="left-menu clearfix">
														<input type="submit" class="btn btn-primary"
															value="${add}" id="addInterview">
													</div>
												</form>
											</div>
										</div>
									</div>
									<c:choose>
										<c:when test="${empty requestScope.interviewList}">
										</c:when>
										<c:otherwise>
											<table class="table table-hover">
												<thead>
													<tr>
														<th>${typeOfInterview}</th>
														<th>${date}</th>
														<th>${addMark}</th>
														<th>${deleteInterview}</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${requestScope.interviewList}"
														var="interview">
														<tr>
															<c:choose>
																<c:when test="${interview.interviewType=='TECHICAL'}">
																	<td>${technical}</td>
																</c:when>
																<c:otherwise>
																	<td>${preliminary}</td>
																</c:otherwise>
															</c:choose>
															<td>${interview.dateBegin}</td>
															<td><button class="btn btn-success"
																	data-toggle="modal"
																	data-target="#${interview.idInterview}">${addMark}</button></td>
															<td><form action="Controller" method="post">
																	<input type="hidden" name="command"
																		value="delete-interview"> <input type="hidden"
																		name="idInterview" value="${interview.idInterview}">
																	<input type="submit" class="btn btn-danger"
																		value="${deleteInterview}">
																</form></td>
														</tr>
														<div class="modal fade" id="${interview.idInterview}"
															tabindex="-1" role="dialog"
															aria-labelledby="myModalLabel" aria-hidden="true">
															<div class="modal-dialog">
																<div class="modal-content">
																	<div class="modal-header">
																		<button type="button" class="close"
																			data-dismiss="modal" aria-hidden="true">&times;</button>
																		<h3 class="modal-title" id="myModalLabel">${addMark}</h3>
																	</div>
																	<div class="modal-body clearfix">
																		<form class="form-input" action="Controller"
																			method="post">
																			<input type="hidden" name="command" value="add-mark">
																			<input type="hidden" value="${interview.idInterview}"
																				name="idInterview">
																			<div class="form-group">
																				<div class="skill">
																					<label class="control-label col-xs-2" for="skill">*${skill}:</label>
																					<div class="col-xs-10">
																						<input type="text" class="form-control"
																							name="skill" placeholder="${enterSkillName}"
																							required maxLength="30">
																						<c:choose>
																							<c:when test="${requestScope.errorSkillName}">
																								<div class="help-block">
																									<p class="text-danger">${wrongSkillName}</p>
																								</div>
																							</c:when>
																							<c:otherwise>
																								<div class="help-block">
																									<p>${skillPattern}</p>
																								</div>
																							</c:otherwise>
																						</c:choose>
																					</div>
																				</div>
																			</div>
																			<br> <br>
																			<div class="form-group">
																				<label class="control-label col-xs-2" for="mark">*${mark}:</label>
																				<div class="col-xs-10">
																					<select class="form-control" name="mark">
																						<option value="novice">${noviceSkill}</option>
																						<option value="intermediate">${intermediateSkill}</option>
																						<option value="advanced">${advancedSkill}</option>
																						<option value="expert">${expertSkill}</option>
																					</select>
																				</div>
																			</div>
																			<div class="left-menu clearfix">
																				<input type="submit" class="btn btn-success"
																					value="${addMark}" id="addMark">
																			</div>
																		</form>
																	</div>
																</div>
															</div>
														</div>
													</c:forEach>
												</tbody>
											</table>
											<h1>${mark}:</h1>
											<div class="clearfix col-xs-6">
												<h2>${technical}:</h2>
												<c:choose>
													<c:when test="${empty requestScope.listMarkTechnical}">
														<p>${listTechMarkIsEmapty}</p>
													</c:when>
													<c:otherwise>
														<table class="table table-bordered">
															<thead>
																<tr>
																	<th>${skill}</th>
																	<th>${mark}</th>
																	<th>${deleteInterviewMark}</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${requestScope.listMarkTechnical}"
																	var="mark">
																	<tr>
																		<td>${mark.skill}</td>
																		<td><c:choose>
																				<c:when test="${mark.mark=='NOVICE'}">${noviceSkill}</c:when>
																				<c:when test="${mark.mark=='INTERMEDIATE'}">${intermediateSkill}</c:when>
																				<c:when test="${mark.mark=='ADVANCED'}">${advancedSkill}</c:when>
																				<c:when test="${mark.mark=='EXPERT'}">${expertSkill}</c:when>
																			</c:choose></td>
																		<td><form action="Controller" method="post">
																				<input type="hidden" name="command"
																					value="delete-interview-mark"> <input
																					type="hidden" name="idInterviewMark"
																					value="${mark.idMark}"> <input
																					type="submit" class="btn btn-danger"
																					value="${deleteInterviewMark}">
																			</form></td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</c:otherwise>
												</c:choose>
											</div>
											<div class="clearfix col-xs-6">
												<h2>${preliminary}:</h2>
												<c:choose>
													<c:when test="${empty requestScope.listMarkPreliminary}">
														<p>${listOfPrelimMarkIsEmpty}</p>
													</c:when>
													<c:otherwise>
														<table class="table table-bordered">
															<thead>
																<tr>
																	<th>${skill}</th>
																	<th>${mark}</th>
																	<th>${deleteInterviewMark}</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${requestScope.listMarkPreliminary}"
																	var="mark">
																	<tr>
																		<td>${mark.skill}</td>
																		<td><c:choose>
																				<c:when test="${mark.mark=='NOVICE'}">${noviceSkill}</c:when>
																				<c:when test="${mark.mark=='INTERMEDIATE'}">${intermediateSkill}</c:when>
																				<c:when test="${mark.mark=='ADVANCED'}">${advancedSkill}</c:when>
																				<c:when test="${mark.mark=='EXPERT'}">${expertSkill}</c:when>
																			</c:choose></td>
																		<td><form action="Controller" method="post">
																				<input type="hidden" name="command"
																					value="delete-interview-mark"> <input
																					type="hidden" name="idInterviewMark"
																					value="${mark.idMark}"> <input
																					type="submit" class="btn btn-danger"
																					value="${deleteInterviewMark}">
																			</form></td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</c:otherwise>
												</c:choose>
											</div>
											<p>bla bla</p>
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
	<script src="js/interviewValidator.js"></script>
	<script src="js/interviewMarkValidator.js"></script>
</body>
</html>