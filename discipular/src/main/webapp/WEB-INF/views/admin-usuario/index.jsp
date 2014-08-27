<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/" var="path"></spring:url>

<c:if test="${mensagem != null}">
	<div>
		<button type="button" class="close" data-dismiss="alert">
			<span aria-hidden="true">&times;</span>
		</button>
		<div class="alert alert-${status} alert-dismissible" role="alert">
			${mensagem}</div>
	</div>
</c:if>
<div>
	<ul class="breadcrumb">
		<li><a href="${path}"><i class="fa fa-home"></i> Discipular</a></li>
		<li class="active"><i class="fa fa-child"></i> Usuários</li>
	</ul>
</div>
<div style="margin-bottom: 15px;">
	<a href="${path}admin/usuario/novo" class="btn btn-s-md btn-primary">
		<i class="fa fa-plus-square-o"></i> Novo Usuário {{1 + 1}}
	</a>
</div>
<div class="input-group pull-right col-lg-3" style="padding-top: 4px; padding-right: 5px;">
	<input type="text" placeholder="Buscar por nome" class="form-control find" /> 
	<span class="input-group-btn">
		<button class="btn btn-default go" type="button">Go!</button>
	</span>
</div>
<div class="panel panel-default">
	<div class="panel-heading">
		<i class="fa fa-list"></i> Lista de Usuários
	</div>
	<table class="table table-hover table-responsive" data-pagina="${pagina}">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Celular</th>
				<th>Opções</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${usuarios}" var="usuario">
				<tr>
					<td># ${usuario.id}</td>
					<td>${usuario.nome}</td>
					<td>${usuario.celular}</td>
					<td>
						<div class="btn-group">
							<button type="button"
								class="btn btn-primary btn-sm dropdown-toggle"
								data-toggle="dropdown">
								Opções <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="${path}admin/usuario/editar/${usuario.id}"><i class="fa fa-pencil-square-o"></i> Editar</a></li>
								<li class="divider"></li>
								<li><a data-toggle="modal" data-target="#excluir-${usuario.id}"><i class="fa fa-trash-o"></i> Excluir</a></li>
							</ul>
						</div>
						<div class="modal fade" id="excluir-${usuario.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header" style="background-color: #1a2d69; color:#FFF">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
										</button>
										<h4 class="modal-title" id="myModalLabel"><i class="fa fa-warning"></i> Atenção!</h4>
									</div>
									<div class="modal-body">
										<p>Você realmente deseja excluir o usuário
											${usuario.nome}?</p>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Cancelar</button>
										<a href="${path}admin/usuario/excluir/${usuario.id}"
											class="btn btn-danger">Excluir</a>
									</div>
								</div>
							</div>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="text-center" data-marker="${marker}">
		<ul class="pagination pagination-sm">
			<li><a href="javascript:void(0);" class="anterior btn disabled"><i class="fa fa-chevron-left"></i></a></li>
			<li><a href="javascript:void(0);" class="proximo btn"><i class="fa fa-chevron-right"></i></a></li>
		</ul>
	</div>
</div>
<script src="/discipular/usuario.js"> </script>