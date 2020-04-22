package com.epipe.ucloud.dossier.utils.id;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ID生成工具类
 *
 * @author Panda520
 * @version 2016年6月10日 下午5:24:04
 *
 */
public class IdUtil {

	private static IdGenerator idGenerator = new SnowflakeIdGenerator(1);
	private static SnWorker snWorker = new SnWorker(1, 1);

	/**
	 * 获取16位数字ID
	 *
	 * @return
	 * 
	 */
	public static synchronized long getId() {
		return idGenerator.nextId();
	}

	/**
	 * 获取20位数字ID
	 *
	 * @return
	 * 
	 */
	public static synchronized String getLongId() {
		return idGenerator.nextId(4);
	}

	public static synchronized String getSn() {
		Long thisTime = Long.parseLong(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		BigInteger bigInteger = new BigInteger(thisTime.toString());
		bigInteger = bigInteger.multiply(BigInteger.valueOf((long) Math.pow(10, 12)))
				.add(BigInteger.valueOf(snWorker.nextId() % 1000000000000L));
		return bigInteger.toString();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(IdUtil.getId());
			System.out.println(IdUtil.getLongId());
			System.out.println(IdUtil.getSn());

		}
	}
}
