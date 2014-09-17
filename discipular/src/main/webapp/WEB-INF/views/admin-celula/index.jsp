<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/" var="path"></spring:url>
<div class="row">
	<div class="col-lg-12">
		<div class="row">
			<div class="col-lg-12">
				<ol class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li class="active"><span>Células</span></li>
				</ol>
<!-- 								<h1> -->
<!-- 									Lista de  <strong>Clientes</strong> -->
<!-- 								</h1> -->
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<a type="button" class="btn btn-primary">Nova Célula</a>
				<p></p>
			</div>
		</div>
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
										<th><span>Endereço</span></th>
										<th><span>Número de Membros</span></th>
										<th><span>Menu</span></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${registros}" var="celula">
										<tr>
											<td>${celula.nome}</td>
											<td class="text-center">${celula.dia}</td>
											<td class="text-center">${celula.horario}</td>
											<td class="text-center">${celula.endereco}</td>
											<td class="text-center">${celula.membros.size()}</td>
											<td>Menu</td>
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