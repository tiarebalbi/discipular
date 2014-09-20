<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<spring:url var="path" value="/"></spring:url>

<div>
	<ul class="breadcrumb">
		<li><a href="${path}"><i class="fa fa-home"></i> Discipular</a></li>
		<li><a href="${path}admin/usuario"><i class="fa fa-child"></i> Usuários</a></li>
		<li class="active"><i class="fa fa-floppy-o"></i> Novo Usuário</li>
	</ul>
</div>

<div>
	<section class="panel panel-default">
		<header class="panel-heading font-bold">
			<strong>Cadastro de Usuário</strong>
		</header>
		<div class="panel-body">
			<form:form commandName="usuario" class="form-horizontal" action="${path}admin/usuario/salvar" method="post">
				<form:hidden path="id" value="${usuario.id}"/>
				<div class="form-group">
					<form:label path="login" class="col-sm-2 control-label">Login: </form:label>
					<div class="col-sm-10">
						<form:input path="login" placeholder="Ex. 98252-2233" type="text" class="form-control" />
						<form:errors path="login" style="color:#FFF" cssClass="label label-danger" element="span" />
					</div>
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<form:label path="senha" class="col-sm-2 control-label">Senha: </form:label>
					<div class="col-sm-10">
						<form:input path="senha" placeholder="Ex. Rua Tijuca nº 123" type="text" class="form-control" />
						<form:errors path="senha" style="color:#FFF" cssClass="label label-danger" element="span" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-4 col-sm-offset-2">
						<a class="btn btn-default" href="${path}admin/usuario">Cancelar</a>
						<button class="btn btn-info" type="submit">Salvar</button>
					</div>
				</div>
			</form:form>
		</div>
	</section>
</div>