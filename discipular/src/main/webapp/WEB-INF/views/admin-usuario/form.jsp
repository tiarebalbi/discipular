<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<spring:url var="path" value="/"></spring:url>

<!-- style="margin: 35px 25px 20px 25px;" -->
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
			<form:form commandName="usuario" action="${path}admin/usuario/salvar" method="post">
				<form:hidden path="id" value="${usuario.id}"/>
				<div class="form-group">
					<form:label path="nome" class="col-sm-2 control-label">Nome Completo: </form:label>
					<div class="col-sm-10">
						<form:input path="nome" type="text" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="login" class="col-sm-2 control-label">Login: </form:label>
					<div class="col-sm-10">
						<form:input path="login" type="text" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="email" class="col-sm-2 control-label">Email: </form:label>
					<div class="col-sm-10">
						<form:input path="email" type="email" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="password" class="col-sm-2 control-label">Senha: </form:label>
					<div class="col-sm-10">
						<form:input path="password" type="password" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="telefone" class="col-sm-2 control-label">Telefone: </form:label>
					<div class="col-sm-10">
						<form:input path="telefone" type="text" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="celular" class="col-sm-2 control-label">Celular: </form:label>
					<div class="col-sm-10">
						<form:input path="celular" type="text" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<form:label path="endereco" class="col-sm-2 control-label">Endereço: </form:label>
					<div class="col-sm-10">
						<form:input path="endereco" type="text" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-4 col-sm-offset-2">
						<button type="submit" class="btn btn-primary">Salvar</button>
					</div>
				</div>
			</form:form>
		</div>
	</section>
</div>