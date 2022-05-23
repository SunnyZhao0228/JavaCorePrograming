package exercise01;

public class Country {

	private int sno;  //序号
	private String continent; //大洲
	private String name;	//国名
	private float landArea;	//陆地面积（平方公里）
	private int population; //人口数（千人）
	private String capital;	//首都


	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Country(int sno, String continent, String name, float landArea, int population, String capital) {
		super();
		this.sno = sno;
		this.continent = continent;
		this.name = name;
		this.landArea = landArea;
		this.population = population;
		this.capital = capital;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getLandArea() {
		return landArea;
	}
	public void setLandArea(float landArea) {
		this.landArea = landArea;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	@Override
	public String toString() {
		return "Country [sno=" + sno + ", continent=" + continent + ", name=" + name + ", landArea=" + landArea
				+ ", population=" + population + ", capital=" + capital + "]";
	}

}
