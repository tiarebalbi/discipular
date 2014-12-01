package br.com.discipular.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class TemplateGraficoDTO {

	private List<Long> data = new ArrayList<>();
	
	private List<String> label = new ArrayList<>();;

	public List<Long> getData() {
		return data;
	}

	public void setData(List<Long> data) {
		this.data = data;
	}

	public List<String> getLabel() {
		return label;
	}

	public void setLabel(List<String> label) {
		this.label = label;
	}
	
}
