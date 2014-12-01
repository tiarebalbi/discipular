<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url value="/" var="path"></spring:url>

<div class="row text-center">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="${path}"><i class="fa fa-home"></i> Home</a></li>
			<li><a href="${path}supervisor/celula"><i class="fa fa-sitemap"></i> Células</a></li>
			<li class="active"><span><i class="fa fa-plus-circle"></i> Novo Célula</span></li>
		</ol>
		<h1>
			Cadastro de <strong>Célula</strong>
		</h1>
	</div>
</div>

<div class="main-box" style=" width: 50%; margin: 0 auto;">
	<header class="main-box-header clearfix">
		<h2>Formulário</h2>
	</header>
	<div class="main-box-body clearfix" style="margin-bottom: 20px;">
		<form:form role="form" action="${path}supervisor/celula/salvar" method="post" modelAttribute="celula">
			<form:hidden path="id" value="${celula.id}"/>
			<div class="form-group">
				<form:label path="nome">Nome</form:label> 
				<form:input path="nome" class="form-control" />
				<form:errors path="nome" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="endereco">Endereço</form:label> 
				<form:input path="endereco" class="form-control" />
				<form:errors path="endereco" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="dia">Dia</form:label> 
				<form:select class="form-control" path="dia" items="${dias}">
				</form:select>
				<form:errors path="dia" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="horario">Horário</form:label> 
				<form:select class="form-control" path="horario" items="${horarios}" itemLabel="horario">
				</form:select>
				<form:errors path="horario" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="dataNascimento">Data de Nascimento</form:label> 
				<form:input path="dataNascimento" class="form-control" id="maskedDate" placeholder="__/__/____" />
				<form:errors path="dataNascimento" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="usuario">Líder</form:label> 
				<form:select  path="usuario" class="form-control">
					<c:choose>
						<c:when test="${celula.usuario eq null}">
							<form:option value=""></form:option>
							<form:options items="${usuarios}" itemLabel="nome" itemValue="id"></form:options>
						</c:when>
						<c:otherwise>
							<form:options items="${usuarios}" itemLabel="nome" itemValue="id"></form:options>
							<form:option value="">---------- Sem Líder ----------</form:option>
						</c:otherwise>
					</c:choose>
				</form:select>
				<form:errors path="usuario" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="col-lg-12">
				<button type="submit" class="btn btn-primary btn-lg pull-right" style="margin-left: 20px;">Salvar</button>
				<a type="button" href="${path}supervisor/celula" class="btn btn-danger btn-lg pull-right">Cancelar</a> 
			</div>
		</form:form>
	</div>
</div>
<script src="${path}resources/both/js/jquery.mask.min.js"></script>
<script src="${path}resources/both/js/mask.js"></script>