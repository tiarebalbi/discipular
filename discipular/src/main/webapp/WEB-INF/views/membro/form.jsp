<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<spring:url value="/" var="path"></spring:url>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="${path}"><i class="fa fa-home"></i> Home</a></li>
			<li><a href="${path}membro"><i class="fa fa-child"></i>
					Membros</a></li>
			<li class="active"><span><i class="fa fa-plus-circle"></i>
					Novo Membro</span></li>
		</ol>
		<h1>
			Cadastrar <strong>Membro</strong>
		</h1>
	</div>
</div>

<div class="main-box">
	<header class="main-box-header clearfix">
		<h2>Formulário</h2>
	</header>
	<div class="main-box-body clearfix">
		<form:form role="form" action="${path}membro/salvar" method="post" modelAttribute="membro">
			<div class="form-group">
				<form:label path="nome">Nome</form:label>
				<form:input path="nome" class="form-control" />
				<form:errors path="nome" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="email">Email</form:label>
				<form:input type="email" path="email" class="form-control" />
			</div>
			<div class="form-group">
				<form:label path="endereco">Endereco</form:label>
				<form:input path="endereco" class="form-control" />
			</div>
			<div class="form-group">
				<form:label path="celular">Celular</form:label>
				<form:input path="celular" class="form-control" />
			</div>
			<div class="form-group">
				<form:label path="tipo">Tipo do Membro</form:label>
				<form:select class="form-control" path="tipo" items="${tipos}">
				</form:select>
				<form:errors path="tipo" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="col-lg-12">
				<button type="submit" class="btn btn-primary btn-lg pull-right"
					style="margin-left: 20px;">Salvar</button>
				<a type="button" href="${path}membro" class="btn btn-danger btn-lg pull-right">Cancelar</a>
			</div>
		</form:form>
	</div>
</div>