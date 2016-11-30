package businessLogic.javaClass;

public class Period {
	
	int periodID;
	String periodName;
	int priceIncrease;
	String startDate;
	String endDate;
	
	public Period(){
		this.periodID = 0;
		this.periodName = "";
		this.priceIncrease = 0;
		startDate = "";
		endDate = "";
	}
	
	public String getPeriodName() {
		return periodName;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

	public int getPeriodID() {
		return periodID;
	}

	public void setPeriodID(int periodID) {
		this.periodID = periodID;
	}

	public int getPriceIncrease() {
		return priceIncrease;
	}

	public void setPriceIncrease(int priceIncrease) {
		this.priceIncrease = priceIncrease;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	}
