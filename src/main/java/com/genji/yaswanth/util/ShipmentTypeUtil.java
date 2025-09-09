package com.genji.yaswanth.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

@Component
public class ShipmentTypeUtil {

	public void getShipmentModePieChart(String path , List<Object[]> data) {
		
		DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
		for(Object ob[] :data) {
			dataset.setValue(ob[0].toString(), Double.valueOf(ob[1].toString()));
		}
		
		JFreeChart chart = ChartFactory.createPieChart("SHIPMENT MODE PIE", dataset);
		
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/shipmentmodepie.jpg"), chart, 500, 300);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void getShipmentModeBarChart(String path , List<Object[]> data) {
		DefaultCategoryDataset dataset =new DefaultCategoryDataset();
		
		for(Object ob [] : data) {
			dataset.setValue(Double.valueOf(ob[1].toString()), ob[0].toString() , "");
		}
		
		JFreeChart chart = ChartFactory.createBarChart("SHIPMENT MODE BAR", "SHIPMENT MODE", "COUNT", dataset);
		
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/shipmentmodebar.jpg"), chart, 500, 300);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
