<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<spring:url value="/" var="path"></spring:url>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="${path}"><i class="fa fa-home"></i> Home</a></li>
			<li><a href="${path}admin/usuario"><i class="fa fa-users"></i> Usuários</a></li>
			<li class="active"><span><i class="fa fa-plus-circle"></i> Novo Usuário</span></li>
		</ol>
		<h1>
			Cadastrar <strong>Usuário</strong>
		</h1>
	</div>
</div>

<div class="main-box">
	<header class="main-box-header clearfix">
		<h2>Formulário</h2>
	</header>
	<div class="main-box-body clearfix">
		<form:form role="form" action="${path}admin/usuario/salvar" method="post" modelAttribute="usuario">
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
			<div class="form-group">
				<form:label path="senha">Senha</form:label> 
				<form:input type="password" path="senha" class="form-control" />
				<form:errors path="senha" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="tipo">Tipo do Usuário</form:label>
				<form:select class="form-control" path="tipo" items="${tipos}">
				</form:select>
				<form:errors path="tipo" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="col-lg-12">
				<button type="submit" class="btn btn-primary btn-lg pull-right" style="margin-left: 20px;">Salvar</button>
				<a type="button" href="${path}admin/usuario" class="btn btn-danger btn-lg pull-right">Cancelar</a> 
			</div>
		</form:form>
	</div>
</div>