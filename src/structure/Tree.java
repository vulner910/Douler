package structure;

public class Tree {
	
		
	
	
	String id;//` VARCHAR(50) NOT NULL COMMENT '树、树枝id = 合同ID',
	int type;//` INT(11) NOT NULL DEFAULT '1' COMMENT '种类 such as ：1祥云，2金',
	int ownerid;//` INT(11) NULL DEFAULT NULL COMMENT '所属用户id',
	float treeYield;// FLOAT NULL DEFAULT NULL COMMENT '树的总产量',
	float treeLevel;//` FLOAT UNSIGNED NULL DEFAULT NULL COMMENT '树的阶段',
	float treeGrown;//`` FLOAT NULL DEFAULT '0' COMMENT '树已成长小时数',
	String treeGrownTime;//`` DATETIME NULL DEFAULT NULL COMMENT '已生长对应时间点',
	float treeTotal;//`` FLOAT NULL DEFAULT NULL COMMENT '树总计需要小时数',
	String treeLastWater;//`` DATETIME NULL DEFAULT NULL COMMENT '上次浇水时间',
	String treeAutoWaterTime;//`` DATETIME NULL DEFAULT NULL COMMENT '预计自动浇水到**日期',
	int fertilize;//`` INT(11) NULL DEFAULT '0' COMMENT '使用化肥次数',
	int status;//` INT(11) NULL DEFAULT '1' COMMENT '树的状态 1正常 2冻结 3 回收',
	String getFrom;//`` VARCHAR(50) NULL DEFAULT NULL COMMENT '来源合同ID（转让时有）',
	String getTime;//`` DATETIME NULL DEFAULT NULL COMMENT '来源时间（转让时才有',
	int getType;//`` INT(11) NULL DEFAULT NULL COMMENT '来源方式 1购买 2转让 3赠与',
	
	
	public float getTreeGrown() {
		return treeGrown;
	}
	public void setTreeGrown(float treeGrown) {
		this.treeGrown = treeGrown;
	}
	public String getTreeGrownTime() {
		return treeGrownTime;
	}
	public void setTreeGrownTime(String treeGrownTime) {
		this.treeGrownTime = treeGrownTime;
	}
	public float getTreeTotal() {
		return treeTotal;
	}
	public void setTreeTotal(float treeTotal) {
		this.treeTotal = treeTotal;
	}
	public String getTreeLastWater() {
		return treeLastWater;
	}
	public void setTreeLastWater(String treeLastWater) {
		this.treeLastWater = treeLastWater;
	}
	public String getTreeAutoWaterTime() {
		return treeAutoWaterTime;
	}
	public void setTreeAutoWaterTime(String treeAutoWaterTime) {
		this.treeAutoWaterTime = treeAutoWaterTime;
	}
	public int getFertilize() {
		return fertilize;
	}
	public void setFertilize(int fertilize) {
		this.fertilize = fertilize;
	}
	public String getGetFrom() {
		return getFrom;
	}
	public void setGetFrom(String getFrom) {
		this.getFrom = getFrom;
	}
	public String getGetTime() {
		return getTime;
	}
	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}
	public int getGetType() {
		return getType;
	}
	public void setGetType(int getType) {
		this.getType = getType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getOwnerid() {
		return ownerid;
	}
	public void setOwnerid(int ownerid) {
		this.ownerid = ownerid;
	}
	public float getTreeYield() {
		return treeYield;
	}
	public void setTreeYield(float treeYield) {
		this.treeYield = treeYield;
	}
	public float getTreeLevel() {
		return treeLevel;
	}
	public void setTreeLevel(float treeLevel) {
		this.treeLevel = treeLevel;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
