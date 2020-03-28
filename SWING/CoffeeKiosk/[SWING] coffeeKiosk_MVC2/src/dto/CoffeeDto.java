package dto;

public class CoffeeDto {
	private String coffeeName;
	private int c_short;
	private int c_tall;
	private int c_grande;
	
	public CoffeeDto() {
	}

	public CoffeeDto(String coffeeName, int c_short, int c_tall, int c_grande) {
		super();
		this.coffeeName = coffeeName;
		this.c_short = c_short;
		this.c_tall = c_tall;
		this.c_grande = c_grande;
	}

	public String getCoffeeName() {
		return coffeeName;
	}

	public void setCoffeeName(String coffeeName) {
		this.coffeeName = coffeeName;
	}

	public int getC_short() {
		return c_short;
	}

	public void setC_short(int c_short) {
		this.c_short = c_short;
	}

	public int getC_tall() {
		return c_tall;
	}

	public void setC_tall(int c_tall) {
		this.c_tall = c_tall;
	}

	public int getC_grande() {
		return c_grande;
	}

	public void setC_grande(int c_grande) {
		this.c_grande = c_grande;
	}

	@Override
	public String toString() {
		return "CoffeeDto [coffeeName=" + coffeeName + ", c_short=" + c_short + ", c_tall=" + c_tall + ", c_grande="
				+ c_grande + "]";
	}
	
	
	
}
