<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<spring:url value="/" var="path"></spring:url>

<div class="main-box">
	<header class="main-box-header clearfix">
		<h2>Basic elements</h2>
	</header>
	<div class="main-box-body clearfix">
		<form:form role="form" action="#" method="post" modelAttribute="celula">
			<div class="form-group">
				<form:label path="nome">Nome</form:label> 
				<form:input path="nome" type="email" class="form-control" />
			</div>
			<div class="form-group">
				<form:label path="dia">Dia</form:label> 
				<input path="dia" class="form-control" placeholder="Password">
			</div>
			<div class="form-group">
				<label for="exampleTextarea">Textarea</label>
				<textarea class="form-control" id="exampleTextarea" rows="3"></textarea>
			</div>
			<div class="form-group">
				<label for="exampleTooltip">Input with Tooltip</label> <input
					type="text" class="form-control" id="exampleTooltip"
					data-toggle="tooltip" data-placement="bottom" title=""
					data-original-title="very nice tooltip about this field">
			</div>
			<div class="form-group">
				<label>Default Select</label> <select class="form-control">
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
				</select>
			</div>
			<div class="form-group">
				<label>Multiple select</label> <select multiple=""
					class="form-control">
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
				</select>
			</div>
			<div class="form-group form-group-select2">
				<label>Enhanced Select</label>
				<div class="select2-container" id="s2id_sel2" style="width: 300px">
					<a href="javascript:void(0)" onclick="return false;"
						class="select2-choice" tabindex="-1"> <span
						class="select2-chosen">United States</span><abbr
						class="select2-search-choice-close"></abbr> <span
						class="select2-arrow"><b></b></span></a><input
						class="select2-focusser select2-offscreen" type="text"
						id="s2id_autogen1">
					<div
						class="select2-drop select2-display-none select2-with-searchbox">
						<div class="select2-search">
							<input type="text" autocomplete="off" autocorrect="off"
								autocapitalize="off" spellcheck="false" class="select2-input">
						</div>
						<ul class="select2-results">
						</ul>
					</div>
				</div>
				<select style="width: 300px" id="sel2" tabindex="-1"
					class="select2-offscreen">
					<option value="United States">United States</option>
					<option value="United Kingdom">United Kingdom</option>
					<option value="Afghanistan">Afghanistan</option>
					<option value="Albania">Albania</option>
					<option value="Algeria">Algeria</option>
					<option value="American Samoa">American Samoa</option>
					<option value="Andorra">Andorra</option>
					<option value="Angola">Angola</option>
					<option value="Anguilla">Anguilla</option>
					<option value="Antarctica">Antarctica</option>
					<option value="Antigua and Barbuda">Antigua and Barbuda</option>
					<option value="Argentina">Argentina</option>
					<option value="Armenia">Armenia</option>
					<option value="Aruba">Aruba</option>
					<option value="Australia">Australia</option>
					<option value="Austria">Austria</option>
					<option value="Azerbaijan">Azerbaijan</option>
					<option value="Slovakia">Slovakia</option>
				</select>
			</div>
			<div class="form-group form-group-select2">
				<label>Multi-Value Select Boxes</label>
				<div class="select2-container select2-container-multi"
					id="s2id_sel2Multi" style="width: 300px">
					<ul class="select2-choices">
						<li class="select2-search-field"><input type="text"
							autocomplete="off" autocorrect="off" autocapitalize="off"
							spellcheck="false" class="select2-input select2-default"
							id="s2id_autogen2" style="width: 298px;"></li>
					</ul>
					<div class="select2-drop select2-drop-multi select2-display-none">
						<ul class="select2-results">
							<li class="select2-no-results">No matches found</li>
						</ul>
					</div>
				</div>
				<select style="width: 300px" id="sel2Multi" multiple=""
					tabindex="-1" class="select2-offscreen">
					<option value="United States">United States</option>
					<option value="United Kingdom">United Kingdom</option>
					<option value="Afghanistan">Afghanistan</option>
					<option value="Albania">Albania</option>
					<option value="Algeria">Algeria</option>
					<option value="American Samoa">American Samoa</option>
					<option value="Andorra">Andorra</option>
					<option value="Angola">Angola</option>
					<option value="Anguilla">Anguilla</option>
					<option value="Antarctica">Antarctica</option>
					<option value="Antigua and Barbuda">Antigua and Barbuda</option>
					<option value="Argentina">Argentina</option>
					<option value="Armenia">Armenia</option>
					<option value="Aruba">Aruba</option>
					<option value="Australia">Australia</option>
					<option value="Austria">Austria</option>
					<option value="Azerbaijan">Azerbaijan</option>
					<option value="Slovakia">Slovakia</option>
				</select>
			</div>
			<div class="form-group">
				<label for="exampleAutocompleteSimple">Autocomplete</label> <span
					class="twitter-typeahead"
					style="position: relative; display: inline-block;"><input
					class="tt-hint" type="text" autocomplete="off" spellcheck="off"
					disabled=""
					style="position: absolute; top: 0px; left: 0px; border-color: transparent; box-shadow: none; background: none 0% 0%/auto repeat scroll padding-box border-box rgb(255, 255, 255);"><input
					type="text" class="form-control tt-query"
					id="exampleAutocompleteSimple" placeholder="countries"
					autocomplete="off" spellcheck="false" dir="auto"
					style="position: relative; vertical-align: top; background-color: transparent;"><span
					style="position: absolute; left: -9999px; visibility: hidden; white-space: nowrap; font-family: 'Open Sans', sans-serif; font-size: 13px; font-style: normal; font-variant: normal; font-weight: 400; word-spacing: 0px; letter-spacing: 0px; text-indent: 0px; text-rendering: auto; text-transform: none;"></span><span
					class="tt-dropdown-menu"
					style="position: absolute; top: 100%; left: 0px; z-index: 100; display: none;"></span></span>
			</div>
			<div class="form-group example-twitter-oss">
				<label for="exampleAutocomplete">Autocomplete with
					templating</label> <span class="twitter-typeahead"
					style="position: relative; display: inline-block;"><input
					class="tt-hint" type="text" autocomplete="off" spellcheck="off"
					disabled=""
					style="position: absolute; top: 0px; left: 0px; border-color: transparent; box-shadow: none; background: none 0% 0%/auto repeat scroll padding-box border-box rgb(255, 255, 255);"><input
					type="text" class="form-control tt-query" id="exampleAutocomplete"
					placeholder="open source projects by Twitter" autocomplete="off"
					spellcheck="false" dir="auto"
					style="position: relative; vertical-align: top; background-color: transparent;"><span
					style="position: absolute; left: -9999px; visibility: hidden; white-space: nowrap; font-family: 'Open Sans', sans-serif; font-size: 13px; font-style: normal; font-variant: normal; font-weight: 400; word-spacing: 0px; letter-spacing: 0px; text-indent: 0px; text-rendering: auto; text-transform: none;"></span><span
					class="tt-dropdown-menu"
					style="position: absolute; top: 100%; left: 0px; z-index: 100; display: none;"></span></span>
			</div>
			<div class="form-group">
				<label for="examplePwdMeter">Password strength meter (start
					typing...)</label> <input type="password" class="form-control"
					id="examplePwdMeter" placeholder="Enter password"
					data-indicator="pwindicator">
				<div id="pwindicator" class="pwdindicator">
					<div class="bar"></div>
					<div class="pwdstrength-label"></div>
				</div>
			</div>
		</form:form>
	</div>
</div>