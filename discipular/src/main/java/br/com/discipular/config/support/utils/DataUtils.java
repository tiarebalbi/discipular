package br.com.discipular.config.support.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DataUtils {

	public static String formatDataPtBr(LocalDate data) {
		return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
}
