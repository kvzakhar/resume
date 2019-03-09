<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ attribute name="profile" required="true" type="net.simplesoft.resume.entity.Profile" %>
<%@ attribute name="showEdit"  required="false" type="java.lang.Boolean" %>

<div class="panel panel-primary">
<c:choose>
	<c:when test="${showEdit}">
		<a href="/edit"><img alt="photo" src="${profile.largePhoto}" class="img-responsive photo"></a>
		<h1 class="text-center"><a href="/edit" style="color:black;">${profile.fullName }</a></h1>
	</c:when>
	<c:otherwise>
		<img alt="photo" src="${profile.largePhoto}" class="img-responsive photo">
		<h1 class="text-center">${profile.fullName}</h1>
	</c:otherwise>
	</c:choose>
	<h6 class="text-center">
		<strong>${profile.city}, ${profile.country}</strong>
	</h6>
	<h6 class="text-center">
		<strong>Age:</strong> ${profile.age} <strong> Birthday: </strong> ${profile.birthDay}
	</h6>
	<div class="list-group contacts">
		<a class="list-group-item" href="${profile.phone}"><i class="fa fa-phone"></i>${profile.phone}</a> 
		<a class="list-group-item" href="mailto:${profile.email}"><i class="fa fa-envelope"></i>${profile.email}</a> 
		<a class="list-group-item" href="javascript:void(0);"><i class="fa fa-skype"></i>${profile.contacts.skype}</a> 
		<a target="_blank" class="list-group-item" href="${profile.contacts.vkontakte}"><i class="fa fa-vk"></i>${profile.contacts.vkontakte}</a> 
		<a target="_blank" class="list-group-item" href="${profile.contacts.facebook}"><i class="fa fa-facebook"></i>${profile.contacts.facebook}</a> 
		<a target="_blank" class="list-group-item" href="${profile.contacts.linkedin}"><i class="fa fa-linkedin"></i>${profile.contacts.linkedin}</a> 
		<a target="_blank" class="list-group-item" href="${profile.contacts.github}"><i class="fa fa-github"></i>${profile.contacts.github}</a> 
		<a target="_blank" class="list-group-item" href="${profile.contacts.stackoverflow}"><i class="fa fa-stack-overflow"></i>${profile.contacts.stackoverflow}</a>
	</div>
</div>
