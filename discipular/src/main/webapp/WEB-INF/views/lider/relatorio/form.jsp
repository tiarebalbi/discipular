<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url value="/" var="path"></spring:url>

<div class="row text-center">
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
	<div class="main-box-body clearfix" style="margin-bottom: 20px;">
		<form:form role="form" action="${path}relatorio/salvar" method="post" modelAttribute="relatorio">
			<form:hidden path="id" value="${relatorio.id}" />
			<div class="form-group">
				<form:label path="tema"><span>Tema*</span></form:label>
				<form:input path="tema" class="form-control" />
				<form:errors path="tema" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="data">Data*</form:label> 
				<form:input path="data" class="form-control" id="maskedDate" placeholder="__/__/____" />
				<form:errors path="data" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="geral"><span>1) De um modo geral como foi a Célula "HOJE"?*</span></form:label>
				<form:input path="geral" type="range" max="5" min="0" oninput="amount1.value=geral.value"/>
				<output name="amount1" for="geral">0</output>
				<form:errors path="geral" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="e5"><span>2) A Célula PASSOU pelos 5 E's? (Encontro, Exaltação, Edificação, Evangelização e Entrega)* </span></form:label>
				<form:input path="e5" type="range" max="5" min="0" oninput="amount2.value=e5.value" />
				<output name="amount2" for="e5">0</output>
				<form:errors path="e5" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="participacao"><span>3) Como foi a participação dos membros?*</span></form:label>
				<form:input path="participacao" type="range" max="5" min="0" oninput="amount3.value=participacao.value" />
				<output name="amount3" for="participacao">0</output>
				<form:errors path="participacao" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="tempo"><span>4) O "Início" e "Término" da Célula deu-se no horário determinado e cumpriu 1h30 de duração?*</span></form:label>
				<form:input path="tempo" type="range" max="5" min="0" oninput="amount4.value=tempo.value" />
				<output name="amount4" for="tempo">0</output>
				<form:errors path="tempo" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="conteudo"><span>5) O que os membros acharam do CONTEÚDO do roteiro HOJE apresentado?*</span></form:label>
				<form:input path="conteudo" type="range" max="5" min="0" oninput="amount5.value=conteudo.value" />
				<output name="amount5" for="conteudo">0</output>
				<form:errors path="conteudo" style="color:#FFF" cssClass="label label-danger" element="span" />
			</div>
			<div class="form-group">
				<form:label path="observacao"><span>Observação</span></form:label>
				<form:textarea style="min-height:100px;" path="observacao" placeholder="Favor informar as ocorrências da células, como por exemplo: explicar a justificativas das faltas, citar as visitas da célula, etc." class="form-control" ></form:textarea>
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
				<c:forEach items="${membros}" var="membro" varStatus="i">
					<c:if test="${membro.id != null}">
						<form:hidden path="chamada[${i.count - 1}].id" value="${membro.id}"/>
					</c:if>
					<div class="col-lg-12 col-xs-12">
						<div class="col-lg-6 col-xs-6">
							<div class="form-group"> 
								<input disabled value="${membro.nome}" class="form-control" />
								<form:hidden path="chamada[${i.count - 1}].nome" value="${membro.nome}" />
							</div>
						</div>
						<div class="col-lg-6 col-xs-6">
							<form:select path="chamada[${i.count - 1}].tipo" class="form-control" items="${chamadas}"> 
							</form:select>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="col-lg-12">
				<button type="submit" class="btn btn-primary btn-lg pull-right" style="margin-left: 20px;">Salvar</button>
				<a type="button" href="${path}relatorio" class="btn btn-danger btn-lg pull-right">Cancelar</a>
			</div>
		</form:form>
	</div>
</div>
<script src="${path}resources/templates/centaurus/js/jquery.js"></script>
<script src="${path}resources/both/js/jquery.mask.min.js"></script>
<script src="${path}resources/both/js/mask.js"></script>