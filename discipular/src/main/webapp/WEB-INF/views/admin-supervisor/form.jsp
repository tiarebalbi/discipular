<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<spring:url value="/" var="path"></spring:url>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="${path}"><i class="fa fa-home"></i> Home</a></li>
			<li><a href="${path}admin/supervisor"><i class="fa fa-trophy"></i> Supervisores</a></li>
			<li class="active"><span><i class="fa fa-plus-circle"></i> Novo Supervisor</span></li>
		</ol>
		<h1>
			Cadastrar <strong>Supervisor</strong>
		</h1>
	</div>
</div>

<div class="main-box">
	<header class="main-box-header clearfix">
		<h2>Formulário</h2>
	</header>
	<div class="main-box-body clearfix">
		<form:form role="form" action="${path}admin/supervisor/salvar" method="post" modelAttribute="supervisor">
			<form:hidden path="id" value="${supervisor.id}"/>
			<div class="form-group">
				<form:label path="nome">Nome</form:label> 
				<form:input path="nome" class="form-control" />
				<form:errors path="nome" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="usuario.login">Login</form:label> 
				<form:input path="usuario.login" class="form-control" />
				<form:errors path="usuario.login" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="usuario.senha">Senha</form:label> 
				<form:input path="usuario.senha" type="password" class="form-control" />
				<form:errors path="usuario.senha" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			
			<div class="form-group">
				<form:label path="area">Área</form:label> 
				<form:input path="area" class="form-control" />
				<form:errors path="area" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="celulas">Células</form:label> 
				<form:select multiple="multiple" class="form-control" itemValue="id" path="celulas" itemLabel="nome" items="${celulas}">
				</form:select>
				<form:errors path="celulas" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="col-lg-12">
				<button type="submit" class="btn btn-primary btn-lg pull-right" style="margin-left: 20px;">Salvar</button>
				<a type="button" href="${path}admin/celula" class="btn btn-danger btn-lg pull-right">Cancelar</a> 
			</div>
		</form:form>
	</div>
</div>
