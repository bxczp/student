package Util;

import java.util.UUID;

public class UUIDUtil {

	/**
	 *获取UUID
	 * @return
	 */
	public static String getUUID(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(UUIDUtil.getUUID());
	}
}
