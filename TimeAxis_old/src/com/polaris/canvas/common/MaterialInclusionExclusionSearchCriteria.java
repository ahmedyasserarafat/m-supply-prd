package com.polaris.canvas.common;


import java.util.Map;

import com.polaris.canvas.properties.reader.ControlDataSearchCriteria;


/**
 * Search criteria for searching MPNs based on a date range.
 * In the start date the start time is defaulted to 00:00:01 on the same day and
 * to time is defaulted to 23:59:59 on the same day.
 * 
 * @author Pankaj Mondal
 * @Date Nov 05, 2014
 * @Modified February 2017
 */
public class MaterialInclusionExclusionSearchCriteria extends ControlDataSearchCriteria {

	
	private String inclusionExclusion;
	private String materialId; // ZACCOUNT_TYPE
	private String validDateFrom;
	private String fromTime;
	private String validDateTo;
	private String toTime;
	
	public String dds=region;
	
	public MaterialInclusionExclusionSearchCriteria(){;}
	public MaterialInclusionExclusionSearchCriteria(Map httpParams){
	}
	
	public String getInclusionExclusion() {
		return getRegion();
	}
	public void setInclusionExclusion(String inclusionExclusion) {
		this.inclusionExclusion = inclusionExclusion;
	}
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getValidDateFrom() {
		return validDateFrom;
	}
	public void setValidDateFrom(String validDateFrom) {
		this.validDateFrom = validDateFrom;
	}
	public String getValidDateTo() {
		return validDateTo;
	}
	public void setValidDateTo(String validDateTo) {
		this.validDateTo = hqId+validDateTo;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
}
