<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url value="/" var="path"></spring:url>

<div class="row text-center">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="${path}"><i class="fa fa-home"></i> Home</a></li>
			<li><a href="${path}admin/lider"><i class="fa fa-users"></i> Líderes</a></li>
			<li class="active"><span><i class="fa fa-plus-circle"></i> Novo Líder</span></li>
		</ol>
		<h1>
			Cadastro de <strong>Líder</strong>
		</h1>
	</div>
</div>

<div class="main-box" style=" width: 50%; margin: 0 auto;">
	<header class="main-box-header clearfix">
		<h2>Formulário</h2>
	</header>
	<div class="main-box-body clearfix">
		<form:form role="form" action="${path}admin/lider/salvar" method="post" modelAttribute="usuario">
			<form:hidden path="id" value="${usuario.id}"/>
			<div class="form-group">
				<form:label path="nome">Nome</form:label> 
				<form:input path="nome" class="form-control" />
				<form:errors path="nome" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="login">Login</form:label> 
				<form:input path="login" class="form-control" />
				<form:errors path="login" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<c:choose>
				<c:when test="${usuario.id == null}">
					<div class="form-group">
						<form:label path="senha">Senha</form:label> 
						<form:input type="password" path="senha" class="form-control" />
						<form:errors path="senha" style="color:#FFF" cssClass="label label-danger" element="span" />
					</div>
				</c:when>
				<c:otherwise>
					<form:hidden path="senha" value="${usuario.senha}"/>
				</c:otherwise>
			</c:choose>
			<div class="col-lg-12">
				<button type="submit" class="btn btn-primary btn-lg pull-right" style="margin-left: 20px;">Salvar</button>
				<a type="button" href="${path}admin/lider" class="btn btn-danger btn-lg pull-right">Cancelar</a> 
			</div>
		</form:form>
	</div>
</div>