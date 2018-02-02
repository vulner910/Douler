package structure;

public class TreeInfo{
	int id;//` INT(11) NOT NULL AUTO_INCREMENT COMMENT '树类型id',
	String name;//` VARCHAR(50) NULL DEFAULT NULL COMMENT '树名称',
	int total;//` INT(11) NULL DEFAULT NULL COMMENT '生长周期',
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
