<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url value="/" var="path"></spring:url>

<div class="row">
	<div class="col-lg-12">
		<div class="row">
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="${path}"><i class="fa fa-home"></i> Home</a></li>
					<li class="active"><i class="fa fa-sitemap"></i> Supervisores</li>
				</ol>
			</div>
		</div>
		<a type="button" href="${path}admin/supervisor/novo" class="btn btn-primary btn-lg">
			<span class="fa fa-plus-circle"></span> Novo Supervisor
		</a>
		<p></p>
		<div class="row">
			<div class="col-lg-12">
				<div class="main-box clearfix">
					<header class="main-box-header clearfix">
						<h2 class="pull-left"><i class="fa fa-list"></i> Lista de registros</h2>
						<div class="filter-block pull-right">
							<div class="form-group pull-left">
								<input type="text" class="form-control" id="condicao" placeholder="Buscar Por Nome...">
								<a href="javascript:void(0);" onclick="buscar()"><i class="fa fa-search search-icon"></i></a>
							</div>
						</div>
					</header>
					<div class="main-box-body clearfix">
						<div class="table-responsive">
							<table class="table table-striped table-hover">
								<thead>
									<tr>
										<th>Nome</th>
										<th>Menu</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${registros}" var="registro">
										<tr>
											<td>${registro.nome}</td>
											<td>
												<div class="btn-group">
													<button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown">
														Opções <span class="caret"></span>
													</button>
													<ul class="dropdown-menu" role="menu">
														<li><a href="${path}admin/supervisor/editar/${registro.id}"><i class="fa fa-pencil-square-o"></i> Editar</a></li>
														<li class="divider"></li>
														<li><a data-toggle="modal" data-target="#excluir-${registro.id}"><i class="fa fa-trash-o"></i> Excluir</a></li>
													</ul>
												</div>
												<div class="modal fade" id="excluir-${registro.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
													<div class="modal-dialog">
														<div class="modal-content">
															<div class="modal-header" style="background-color: #1a2d69; color:#FFF">
																<button type="button" class="close" data-dismiss="modal">
																	<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
																</button>
																<h4 class="modal-title" id="myModalLabel"><i class="fa fa-warning"></i> Atenção!</h4>
															</div>
															<div class="modal-body">
																<p>Você realmente deseja excluir o(a) supervisor(a) ${registro.nome}?</p>
															</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
																<a href="${path}admin/supervisor/excluir/${registro.id}" class="btn btn-danger">Excluir</a>
															</div>
														</div>
													</div>
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>