<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="resume" tagdir="/WEB-INF/tags"%>

<div class="container">
	<div class="row">
		<div class="col-md-4 col-sm-6">
			<resume:profile-main profile="${profile}" showEdit="${true}"/>
			<div class="hidden-xs">
				<c:if test="${fn:length(profile.languages) > 0}">
					<resume:profile-languages languages="${profile.languages}" showEdit="${true }" />
				</c:if>
				<c:if test="${fn:length(profile.hobbies) > 0}">
					<resume:profile-hobbies hobbies="${profile.hobbies}" showEdit="${true }" />
				</c:if>
				<c:if test="${profile.info != null}">
					<resume:profile-info profile="${profile}" showEdit="${true}"/>
				</c:if>
			</div>
		</div>
		<div class="col-md-8 col-sm-6">
			<resume:profile-objective profile="${profile}" showEdit="${true}" />
			<c:if test="${fn:length(profile.skills) > 0}">
				<resume:profile-skills skills="${profile.skills}" showEdit="${true}" />
			</c:if>
			<c:if test="${fn:length(profile.practics) > 0}">
				<resume:profile-practics practics="${profile.practics}" showEdit="${true}"/>
			</c:if>
			<c:if test="${fn:length(profile.certificates) > 0}">
				<resume:profile-certificates certificates="${profile.certificates}" showEdit="${true}"/>
			</c:if>
			<c:if test="${fn:length(profile.courses) > 0}">
				<resume:profile-courses courses="${profile.courses}" showEdit="${true}"/>
			</c:if>
			<c:if test="${fn:length(profile.educations) > 0}">
				<resume:profile-education educations="${profile.educations}" showEdit="${true}"/>
			</c:if>
		</div>
		<div class="col-xs-12 visible-xs block">
			<c:if test="${fn:length(profile.languages) > 0}">
				<resume:profile-languages languages="${profile.languages }" showEdit="${true }" />
			</c:if>
			<c:if test="${fn:length(profile.hobbies) > 0}">
				<resume:profile-hobbies hobbies="${profile.hobbies }" showEdit="${true }" />
			</c:if>
			<c:if test="${profile.info != null}">
				<resume:profile-info profile="${profile}" showEdit="${true }"/>
			</c:if>
		</div>
	</div>
</div>
