<%--
- form.jsp
-
- Copyright (C) 2012-2021 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox placeholder="Jhon Doe" code="anonymous.shout.form.label.author" path="author"/>
	<acme:form-textarea placeholder="This is a piece of text!" code="anonymous.shout.form.label.text" path="text"/>
	<acme:form-url code="anonymous.shout.form.label.info" path="info"/>
	
	<acme:menu-separator/>
	
	<acme:form-textbox placeholder="SHOUTXX-yyyy/mm/dd" code="anonymous.shout.form.label.zeus" path="grecia.zeus"/>
	<acme:form-moment code="anonymous.shout.form.label.poseidon" path="grecia.poseidon"/>
	<acme:form-money code="anonymous.shout.form.label.hades" path="grecia.hades"/>
	<acme:form-checkbox code="anonymous.shout.form.label.afrodita" path="grecia.afrodita"/>

	<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>
  	<acme:form-return code="anonymous.shout.form.button.return"/>
</acme:form>
