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
					<li class="active"><i class="fa fa-users"></i> Células</li>
				</ol>
			</div>
		</div>
		<a type="button" href="${path}admin/celula/novo" class="btn btn-primary btn-lg">
			<span class="fa fa-plus-circle"></span> Nova Célula
		</a>
		<p></p>
		<div class="row">
			<div class="col-lg-12">
				<div class="main-box clearfix">
					<header class="main-box-header clearfix">
						<h2><i class="fa fa-list"></i> Lista de registros</h2>
					</header>
					<div class="main-box-body clearfix">
						<div class="table-responsive">
							<table class="table table-striped table-hover">
								<thead>
									<tr>
										<th><span>Nome</span></th>
										<th><span>Dia</span></th>
										<th><span>Horário</span></th>
										<th><span>Números de Membros</span></th>
										<th><span>Menu</span></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${registros}" var="registro">
										<tr>
											<td>${registro.nome}</td>
											<td>${registro.dia}</td>
											<td>${registro.horario}</td>
											<td>${registro.membros.size()}</td>
											<td>Opções</td>
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
