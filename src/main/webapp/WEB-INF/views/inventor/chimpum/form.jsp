<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
<acme:input-textbox code="inventor.chimpum.form.label.pattern" path="pattern" /> 
<jstl:if test="${acme:anyOf(command, 'show, update, delete')}">
<acme:input-textbox code="inventor.chimpum.form.label.code" path="code" readonly="true"/> 
</jstl:if>

<acme:input-textbox code="inventor.chimpum.form.label.title" path="title"/> 
<acme:input-textbox code="inventor.chimpum.form.label.description" path="description"/>
<acme:input-money code="inventor.chimpum.form.label.budget" path="budget"/> 
<jstl:if test="${isExchange}">
			<acme:input-money code="any.item.list.label.exchange" path="exchange" readonly="true"/>
	</jstl:if>
<acme:input-moment code="inventor.chimpum.form.label.creationMoment" path="creationMoment" readonly="true"/> 
<acme:input-moment code="inventor.chimpum.form.label.startDate" path="startDate"/> 
<acme:input-moment code="inventor.chimpum.form.label.endDate" path="endDate"/> 
<acme:input-textbox code="inventor.chimpum.form.label.moreInfo" path="moreInfo"/> 

<jstl:choose>
	<jstl:when test="${command== 'create'}">
		<acme:submit code="inventor.chimpum.form.button.create" action="/inventor/chimpum/create?itemId=${itemId}"/>
	</jstl:when>
	<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
		<acme:submit code="inventor.chimpum.form.button.update" action="/inventor/chimpum/update"/>
		<acme:submit code="inventor.chimpum.form.button.delete" action="/inventor/chimpum/delete"/>
		<acme:button code="inventor.chimpum.form.button.items" action="/inventor/item/show?id=${itemId}"/>
	</jstl:when>
</jstl:choose>
</acme:form>