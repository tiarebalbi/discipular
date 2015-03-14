<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url value="/" var="path"></spring:url>

<div class="row text-center">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="${path}"><i class="fa fa-home"></i> Home</a></li>
			<li><a href="${path}${modulo}"><i class="fa fa-file-text-o"></i> Relatórios</a></li>
		</ol>
		<h1>
			Visualizar <strong>Relatório</strong>
		</h1>
	</div>
</div>

<div class="main-box">
	<header class="main-box-header clearfix">
		<h2>Visualização</h2>
	</header>
	<div class="main-box-body clearfix" style="margin-bottom: 20px;">
		<form role="form" action="#">
			<div class="form-group">
				<label><span>Tema</span></label>
				<input disabled class="form-control" value="${relatorio.tema}"/>
			</div>
			<div class="form-group">
				<label>Data</label> 
				<input disabled class="form-control" value="${relatorio.data}">
			</div>
			<div class="form-group">
				<label><span>1) De um modo geral como foi a Célula "HOJE"?</span></label>
				<input disabled class="form-control" value="${relatorio.ask5}" />
			</div>
			<div class="form-group">
				<label><span>2) A Célula PASSOU pelos 5 E's? (Encontro, Exaltação, Edificação, Evangelização e Entrega) </span></label>
				<input disabled class="form-control" value="${relatorio.ask2}" />
			</div>
			<div class="form-group">
				<label><span>3) Como foi a participação dos membros?</span></label>
				<input disabled class="form-control" value="${relatorio.ask3}" />
			</div>
			<div class="form-group">
				<label><span>4) O "Início" e "Término" da Célula deu-se no horário determinado e cumpriu 1h30 de duração?</span></label>
				<input disabled class="form-control" value="${relatorio.ask4}" />
			</div>
			<div class="form-group">
				<label><span>5) O que os membros acharam do CONTEÚDO do roteiro HOJE apresentado?</span></label>
				<input disabled class="form-control" value="${relatorio.ask5}" />
			</div>
			<div class="form-group">
				<label for="observacao"><span>Observação</span></label>
				<textarea disabled style="min-height:100px;" placeholder="Favor informar as ocorrências da células, como por exemplo: explicar a justificativas das faltas, citar as visitas da célula, etc." class="form-control" ></textarea>
			</div>
			<div class="main-box clearfix" style="background-color: #afc7c7;"> 
				<div class="col-lg-12 col-xs-12">
					<div class="col-lg-6 col-xs-6">
						<label><span>Nome</span></label>
					</div>
					<div class="col-lg-6 col-xs-6">
						<label><span>Chamada</span></label>
					</div>
				</div>
				<c:forEach items="${chamadas}" var="membro" varStatus="i">
					<div class="col-lg-12 col-xs-12">
						<div class="col-lg-6 col-xs-6">
							<div class="form-group"> 
								<input disabled value="${membro.nome}" class="form-control" />
							</div>
						</div>
						<div class="col-lg-6 col-xs-6">
							<input disabled value="${membro.tipo}" class="form-control" />
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="col-lg-12">
				<a type="button" href="${path}${modulo}" class="btn btn-danger btn-lg pull-right">Voltar</a>
			</div>
		</form>
	</div>
</div>
<script src="${path}resources/templates/centaurus/js/jquery.js"></script>