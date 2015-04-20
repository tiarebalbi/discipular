<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url value="/" var="path"></spring:url>

<div class="row text-center">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="${path}"><i class="fa fa-home"></i> Home</a></li>
			<li><a href="${path}admin/supervisor"><i class="fa fa-trophy"></i> Supervisores</a></li>
			<li class="active"><span><i class="fa fa-plus-circle"></i> Novo Supervisor</span></li>
		</ol>
		<h1>
			Cadastro de <strong>Supervisor</strong>
		</h1>
	</div>
</div>

<div class="main-box">
	<header class="main-box-header clearfix">
		<h2>Formulário</h2>
	</header>
	<div class="main-box-body clearfix"  style="margin-bottom: 20px;">
		<form:form role="form" action="${path}admin/supervisor/salvar" method="post" modelAttribute="usuario">
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
				<c:when test="${usuario.id == nul}">
					<div class="form-group">
						<form:label path="senha">Senha</form:label> 
						<form:input path="senha" type="password" class="form-control" />
						<form:errors path="senha" style="color:#FFF" cssClass="label label-danger" element="span" />
					</div>	
				</c:when>
				<c:otherwise>
					<form:hidden path="senha" value="${senha}"/>
				</c:otherwise>
			</c:choose>
			<div class="form-group">
				<form:label path="area">Área</form:label> 
				<form:input path="area" class="form-control" />
				<form:errors path="area" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="telefone">Telefone</form:label>
				<form:input path="telefone" class="form-control"  id="maskedPhone" placeholder="99999-9999" />
				<form:errors path="telefone" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="email">Email</form:label>
				<form:input path="email" class="form-control" />
				<form:errors path="email" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="col-lg-12">
				<button type="submit" class="btn btn-primary btn-lg pull-right" style="margin-left: 20px;">Salvar</button>
				<a type="button" href="${path}admin/supervisor" class="btn btn-danger btn-lg pull-right">Cancelar</a> 
			</div>
		</form:form>
	</div>
</div>
<script src="${path}resources/both/js/jquery.mask.min.js"></script>
<script src="${path}resources/both/js/mask.js"></script>