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
					<li class="active"><i class="fa fa-users"></i> Membros</li>
				</ol>
			</div>
		</div>
		<a type="button" href="${path}membro/novo" class="btn btn-primary btn-lg">
			<span class="fa fa-plus-circle"></span> Novo  Membro
		</a>
		<p></p>
		<h1>
			<strong>Fonte de Graça</strong>
		</h1>
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
										<th><span>Tipo</span></th>
										<th><span>Celular</span></th>
										<th><span>Email</span></th>
										<th><span>Data de Nascimento</span></th>
										<th><span>Menu</span></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${registros}" var="registro">
										<tr>
											<td>${registro.nome}</td>
											<td>${registro.tipo}</td>
											<td>${registro.celular}</td>
											<td>${registro.email}</td>
											<td>${registro.nascimento}</td>
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