<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" 		uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="languages" required="true" type="java.util.Set" %>
<%@ attribute name="showEdit"  required="false" type="java.lang.Boolean" %>

<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">
			<i class="fa fa-language"></i> Foreign languages 
			<c:if test="${showEdit}"><a href="/edit/languages" class="edit-block">Edit</a></c:if>
		</h3>
	</div>
	<div class="panel-body">
		<c:forEach var="language" items="${languages}" varStatus="index">
			<strong>${language.name}:</strong> ${language.level} <c:if test="${language.hasLanguageType}"><i> (${language.type }) </i></c:if><br>
		</c:forEach>
	</div>
</div>
