<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url value="/" var="path"></spring:url>

<div class="row">
	<div class="col-lg-12">
		<ol class="breadcrumb">
			<li><a href="${path}"><i class="fa fa-home"></i> Home</a></li>
			<li><a href="${path}relatorio"><i class="fa fa-file-text-o"></i>
					Relatórios</a></li>
			<li class="active"><span><i class="fa fa-plus-circle"></i>
					Novo Relatório</span></li>
		</ol>
		<h1>
			Cadastrar <strong>Relatório</strong>
		</h1>
	</div>
</div>

<div class="main-box">
	<header class="main-box-header clearfix">
		<h2>Formulário</h2>
	</header>
	<div class="main-box-body clearfix">
		<form:form role="form" action="${path}relatorio/salvar" method="post" modelAttribute="relatorio">
			<form:hidden path="id"/>
			<div class="form-group">
				<form:label path="tema"><span>Tema</span></form:label>
				<form:input path="tema" class="form-control" />
				<form:errors path="tema" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="ask1"><span>1) De um modo geral como foi a Célula "HOJE"?</span></form:label>
				<form:input type="number" min="0" max="5" path="ask1" class="form-control" />
				<form:errors path="ask1" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="ask2"><span>2) A Célula PASSOU pelos 5 E's? (Encontro, Exaltação, Edificação, Evangelização e Entrega) </span></form:label>
				<form:input type="number" min="0" max="5" path="ask2" class="form-control" />
				<form:errors path="ask2" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="ask3"><span>3) Como foi a participação dos membros?</span></form:label>
				<form:input type="number" min="0" max="5" path="ask3" class="form-control" />
				<form:errors path="ask3" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="ask4"><span>4) O "Início" e "Término" da Célula deu-se no horário determinado e cumpriu 1h30 de duração?</span></form:label>
				<form:input type="number" min="0" max="5" path="ask4" class="form-control" />
				<form:errors path="ask4" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="ask5"><span>5) O que os membros acharam do CONTEÚDO do roteiro HOJE apresentado?</span></form:label>
				<form:input type="number" min="0" max="5" path="ask5" class="form-control" />
				<form:errors path="ask5" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="observacao"><span>Observação</span></form:label>
				<form:textarea style="min-height:100px;" path="observacao" class="form-control" ></form:textarea>
			</div>
			<div class="col-lg-12">
				<div class="col-lg-4">
					<label><span>Nome</span></label>
				</div>
				<div class="col-lg-4">
					<label><span>Chamada</span></label>
				</div>
			</div>
					
<%-- 			<c:forEach items="${membros}" var="membro" varStatus="i"> --%>
<!-- 				<div class="col-lg-12"> -->
<!-- 					<div class="col-lg-4"> -->
<!-- 						<div class="form-group"> -->
<%-- 							<input disabled value="${membro.nome}" data-nome="${membro.nome}" class="form-control membro-nome${i.count}" /> --%>
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="col-lg-4"> -->
<%-- 						<select class="form-control membro-chamada${i.count}" onchange="chamadak()">  --%>
<!-- 							<option value="presente">Presente</option> -->
<!-- 							<option value="ausente">Ausênte</option> -->
<!-- 							<option value="justificado">Justificado</option> -->
<!-- 						</select> -->
<!-- 					</div> -->
<!-- 				</div> -->
<%-- 				<form:hidden path="chamada" id="chamada"/> --%>
<%-- 			</c:forEach> --%>
			<div class="col-lg-12">
				<button type="submit" class="btn btn-primary btn-lg pull-right" style="margin-left: 20px;">Salvar</button>
				<a type="button" href="${path}relatorio" class="btn btn-danger btn-lg pull-right">Cancelar</a>
			</div>
		</form:form>
	</div>
</div>
<script src="${path}resources/templates/centaurus/js/jquery.js"></script>
<script src="${path}resources/teste.js"></script>