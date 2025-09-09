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
public class UomUtil {

	public void geteratePieChart(String path , List<Object[]> data) {
		DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
		for(Object[] ob : data) {
			dataset.setValue(ob[0].toString(), Double.valueOf((ob[1]).toString()));
		}
		
		JFreeChart chart =ChartFactory.createPieChart("UOM PIE CHART", dataset);
		
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/uompie.jpg"), chart, 500, 300);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void generateBarChart(String path , List<Object[]> data) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(Object ob[]: data) {
			dataset.setValue(Double.valueOf((ob[1]).toString()), ob[0].toString(), "");
			
			JFreeChart chart = ChartFactory.createBarChart("UOM BAR CHART", "UOM TYPE", "COUNT", dataset);
			
			try {
				ChartUtils.saveChartAsJPEG(new File(path+"/uombar.jpg"), chart, 500, 300);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
