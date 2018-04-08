package com.docker.yyf;

import com.docker.yyf.entity.*;
import com.docker.yyf.util.ExcelUtil;
import com.docker.yyf.util.RandomAlphaNumericGenerator;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DockerApplicationTests {

	@Autowired
	private ICustomerManager customerManager;
	@Autowired
	private IProjectManage projectManage;

	@Test
	public void contextLoads() {
		projectManage.addProject("3","yyf123456");
	}
	@Test
	public void save() throws Exception {
		List<String[]> columNames = new ArrayList<String[]>();
		columNames.add(new String[] { "订单号", "类型" ,"创建时间" });
		columNames.add(new String[] { "总条数", "增加数" ,"减少" });

		List<String[]> fieldNames = new ArrayList<String[]>();
		fieldNames.add(new String[] { "orderId", "type" ,"createTime"});
		fieldNames.add(new String[] { "total", "addNumber" ,"reduceNumber"});

		List<Record> select = new ArrayList<Record>();
		for (int i=0;i<10;i++){
			Record record = new Record();
			record.setOrderId("orderId"+i);
			record.setType(2);
			record.setCreateTime(new Date());
			select.add(record);
		}
		RecordTotal recordTotal = new RecordTotal();
		recordTotal.setAddNumber(10);
		recordTotal.setReduceNumber(10);
		recordTotal.setTotal(10);
		List<RecordTotal> totals = new ArrayList<RecordTotal>();
		totals.add(recordTotal);

		ExcelUtil.ExcelExportData setInfo = new ExcelUtil.ExcelExportData();

		LinkedHashMap<String, List<?>> map = new LinkedHashMap<String, List<?>>();
		map.put("用户操作记录",select);
		map.put("统计",totals);
		setInfo.setDataMap(map);
		setInfo.setFieldNames(fieldNames);
		setInfo.setTitles(new String[] { "用户操作记录","统计"});
		setInfo.setColumnNames(columNames);
		ExcelUtil.export2File(setInfo, "d:/test5.xls","type", (co) -> {
			Integer integer = Integer.valueOf(co.toString());
			return NameType.getType(integer).name();
		});


	}

	public  static void modifyTheValue(){
		throw new RuntimeException();
	}


	private  String te11(int name,GetFunction function){
		return function.invoke(name);
	}


	@Test
	public void test() throws IOException, ClassNotFoundException {
		for (int i=0;i<10;i++){
			System.out.println(RandomAlphaNumericGenerator.nextString(6,11));
		}
	}

	// 放大倍数
	private static final int mulriple = 1000000;

	public static boolean pay(BigDecimal prizes) {
		int lastScope = 0;
		Map<Boolean, int[]> prizeScopes = new HashMap<Boolean, int[]>();
		boolean prizeId = true;
		// 划分区间
		int currentScope = lastScope + prizes.multiply(new BigDecimal(mulriple)).intValue();
		prizeScopes.put(prizeId, new int[] { lastScope + 1, currentScope });
		// 获取1-1000000之间的一个随机数
		int luckyNumber = new Random().nextInt(mulriple);
		boolean luckyPrizeId = false;
		// 查找随机数所在的区间
		if ((null != prizeScopes) && !prizeScopes.isEmpty()) {
			Set<Map.Entry<Boolean, int[]>> entrySets = prizeScopes.entrySet();
			for (Map.Entry<Boolean, int[]> m : entrySets) {
				boolean key = m.getKey();
				if (luckyNumber >= m.getValue()[0] && luckyNumber <= m.getValue()[1]) {
					luckyPrizeId = key;
					break;
				}
			}
		}
		return luckyPrizeId;
	}

	 static class Prize {

		/**
		 * 奖品唯一标示
		 */
		private Integer prizeId;

		/**
		 * 中奖概率
		 */
		private BigDecimal probability;

		public Prize(){}

		public Integer getPrizeId() {
			return prizeId;
		}

		public void setPrizeId(Integer prizeId) {
			this.prizeId = prizeId;
		}

		public BigDecimal getProbability() {
			return probability;
		}

		public void setProbability(BigDecimal probability) {
			this.probability = probability;
		}
	}

	@Test
	public  void sss(){
			List<Prize> prizes = new ArrayList<Prize>();
			Prize prize1 = new Prize();
			prize1.setPrizeId(100);
			prize1.setProbability(new BigDecimal(0.50));

			prizes.add(prize1);
			int a=0;
			for (int i=0;i<100;i++){
				 if (pay(new BigDecimal(0.5))){
				 	a++;
				 }
			}
		System.out.println(a);
	}

}
