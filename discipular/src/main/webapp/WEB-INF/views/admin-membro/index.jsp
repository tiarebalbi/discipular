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
					<li class="active"><i class="fa fa-child"></i> Membros</li>
				</ol>
			</div>
		</div>
		<h1>
			<strong>Membros</strong>
		</h1>
		<div class="row">
			<div class="col-lg-12">
				<div class="main-box clearfix">
					<header class="main-box-header clearfix">
						<h2 class="pull-left"><i class="fa fa-list"></i> Lista de registros</h2>
						<div class="filter-block pull-right">
							<div class="form-group pull-left">
								<input type="text" class="form-control" id="condicao" placeholder="Buscar Por Célula...">
								<a href="javascript:void(0);" onclick="buscar()"><i class="fa fa-search search-icon"></i></a>
							</div>
						</div>
					</header>
					<div class="main-box-body clearfix">
						<div class="table-responsive" data-pagina="${pagina}" data-modulo="${modulo}">
							<table class="table table-striped table-hover">
								<thead>
									<tr>
										<th>Nome</th>
										<th>Célula</th>
										<th>Tipo</th>
										<th>Celular</th>
										<th>Email</th>
										<th>Data de Nascimento</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${registros}" var="registro">
										<tr>
											<td>${registro.nome}</td>
											<td>${registro.celula.nome}</td>
											<td>${registro.tipo}</td>
											<td>${registro.celular}</td>
											<td>${registro.email}</td>
											<td>${registro.data}</td>
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
<script src="${path}resources/admin/js/busca-paginacao.js"></script>
<script src="${path}resources/admin/js/membro-index.js"></script>