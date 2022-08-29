package com.lokiy.learing.note.bytes;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.io.UnsupportedEncodingException;

/**
 * byte的操作
 * 
 * @author hebin
 */
@Slf4j
public final class ByteUtil {
	/**
	 * 将int转为长度为bytelen的byte[],溢出不做处理 如果bytelen<4,则num值必须>=0
	 * 
	 * @param num
	 *            要转化的数字
	 * @param bytelen
	 *            转化后的byte数组长度，必须<=4
	 * @return 转化后的byte[]
	 * @throws IllegalArgumentException
	 *             如果bytelen的长度大于4,则值不可能为int，直接抛出异常
	 */
	public static byte[] int2Byte(int num, int bytelen) {
		Assert.isTrue(bytelen <= 4 && bytelen > 0, "Byte array out of bound, max length is 4.");
		byte[] b = new byte[bytelen];
		for (int i = bytelen - 1; i >= 0; i--) {
			b[i] = (byte) (num & 0xFF);
			num = num >> 8;
		}
		return b;
	}

	public static void main(String[] args) {
		byte[] b = new byte[]{(byte)0X00,(byte)0X00,(byte)0X01,(byte)0X73,(byte)0X13,(byte)0X6e,(byte)0Xa4,(byte)0Xcd};
		Long s = byte2Long(b);
		System.out.println("args = [" + s + "]");
	}

	/**
	 * 将long转为长度为bytelen的byte[],溢出不做处理 如果bytelen<8，则num值必须>=0
	 * 
	 * @param num
	 *            要转化的数字
	 * @param bytelen
	 *            转化后的byte数组长度，必须<=8
	 * @return 转化后的byte[]
	 * @throws IllegalArgumentException
	 *             如果bytelen的长度大于8,则值不可能为long，直接抛出异常
	 */
	public static byte[] long2Byte(long num, int bytelen) {
		Assert.isTrue(bytelen <= 8, "Byte array out of bound, max length is 8.");
		byte[] b = new byte[bytelen];
		for (int i = bytelen - 1; i >= 0; i--) {
			b[i] = (byte) (num & 0xFF);
			num = num >> 8;
		}
		return b;
	}

	/**
	 * 将long转为长度为bytelen的byte[]
	 * 
	 * @param num
	 *            要转化的数字
	 * @return 转化后的byte[]
	 */
	public static byte[] long2Byte(long num) {
		return long2Byte(num, 8);
	}

	/**
	 * 将int转为byte[]
	 * 
	 * @param num
	 *            要转化的数字
	 * @return 转化后的byte[]
	 */
	public static byte[] int2Byte(int num) {
		return int2Byte(num, 4);
	}

	/**
	 * 将float转为长度为2的byte[],溢出不做处理，小数点2位后不做处理,最后对float做*100处理，
	 * 
	 * @param num
	 *            要转化的数字
	 * @param bytelen
	 *            转化后的byte数组长度，必须<=4
	 * @return 转化后的byte[]
	 * @throws IllegalArgumentException
	 *             如果bytelen的长度大于4,则值不可能为int，直接抛出异常
	 */
	public static byte[] float2IntByte(float num, int bytelen) {
		Assert.isTrue(bytelen <= 4 && bytelen > 0, "Byte array out of bound, max length is 4.");
		int i = (int) (num * 100);
		return int2Byte(i, bytelen);
	}

	/**
	 * 将float转为byte[]
	 * 
	 * @param num
	 *            要转化的数字
	 * @return 转化后的byte[]
	 */
	public static byte[] float2Byte(float num) {
		int n = Float.floatToIntBits(num);
		return int2Byte(n, 4);
	}

	/**
	 * 将double转为byte[]
	 * 
	 * @param num
	 *            要转化的数字
	 * @return 转化后的byte[]
	 */
	public static byte[] double2Byte(double num) {
		long n = Double.doubleToRawLongBits(num);
		return long2Byte(n);
	}

	/**
	 * 将字符串转为byte[]
	 * 
	 * @param str
	 *            要转化的字符串
	 * @return 转化后的数组
	 */
	public static byte[] str2Byte(String str) {
		return str.getBytes();
	}

	/**
	 * 将byte转化为字符串
	 * 
	 * @param b
	 *            要转化的byte
	 * @return 转化后的字符串
	 * @throws UnsupportedEncodingException
	 *             转化异常
	 */
	public static String byte2Str(byte[] b) throws UnsupportedEncodingException {
		return new String(b, "UTF-8");
	}

	/**
	 * 将byte转化为字符串
	 * 
	 * @param b
	 *            要转化的byte
	 * @return 转化后的字符串
	 * @throws UnsupportedEncodingException
	 *             转化异常
	 */
	public static String byte2Str(byte[] b, int startIndex, int len) {
		Assert.isTrue(b.length >= startIndex + len, "Out of bound, len is out of byte array lenth.");
		try {
			return new String(b, startIndex, len, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("Cover byte to string error.", e);
		}
		return StrUtil.EMPTY;
	}

	/**
	 * 将数组转为int值,
	 * 
	 * @param b
	 *            要转化的数组
	 * @return 转化后的int值
	 * @throws IllegalArgumentException
	 *             如果byte的长度大于4,则值不可能为int，直接抛出异常
	 */
	public static int byte2Int(byte[] b) throws IllegalArgumentException {
		int len = b.length;
		if (0 == len) {
			return 0;
		}
		Assert.isTrue(len <= 4, "Byte array out of bound, max length is 4.");
		int sum = 0;
		for (int i = len - 1; i >= 0; i--) {
			sum ^= ((b[i] & 0xFF) << 8 * (len - i - 1));
		}
		return sum;
	}

	/**
	 * 将数组转为int值,
	 * 
	 * @param b
	 *            要转化的数组
	 * @param startIndex
	 *            数组搜索的下表
	 * @param len
	 *            int值在数组中得length
	 * @return 转化后的int值
	 * @throws IllegalArgumentException
	 *             如果byte的长度大于4,则值不可能为int，直接抛出异常
	 */
	public static int byte2Int(byte[] b, int startIndex, int len) throws IllegalArgumentException {
		Assert.isTrue(len <= 4 && len >= 0, "Byte array out of bound, max length is 4 and min is 0.");
		Assert.isTrue(b.length >= startIndex + len, "Out of bound, len is out of byte array lenth.");
		if (0 == len) {
			return 0;
		}

		int sum = 0;
		for (int i = len - 1; i >= 0; i--) {
			sum ^= ((b[startIndex + i] & 0xFF) << 8 * (len - i - 1));
		}
		return sum;
	}

	/**
	 * 将数组转为long值,
	 * 
	 * @param b
	 *            要转化的数组
	 * @return 转化后的long值
	 * @throws IllegalArgumentException
	 *             如果byte的长度大于8,则值不可能为long，直接抛出异常
	 */
	public static long byte2Long(byte[] b) throws IllegalArgumentException {
		int len = b.length;
		if (0 == len) {
			return 0;
		}
		Assert.isTrue(len <= 8, "Byte array out of bound, max length is 4.");
		long sum = 0;
		for (int i = len - 1; i >= 0; i--) {
			sum ^= ((long) (b[i] & 0xFF) << 8 * (len - i - 1));
		}
		return sum;
	}

	/**
	 * 将数组转为long值,
	 * 
	 * @param b
	 *            要转化的数组
	 * @param startIndex
	 *            数组搜索的下表
	 * @param len
	 *            int值在数组中得length
	 * @return 转化后的long值
	 * @throws IllegalArgumentException
	 *             如果byte的长度大于8,则值不可能为long，直接抛出异常
	 */
	public static long byte2Long(byte[] b, int startIndex, int len) throws IllegalArgumentException {
		Assert.isTrue(len <= 8 && len >= 0, "Byte array out of bound, max length is 8 and min is 0.");
		Assert.isTrue(b.length >= startIndex + len, "Out of bound, len is out of byte array lenth.");
		if (0 == len) {
			return 0;
		}
		// 判断long值的符号位
		int sign = 0;
		if (len == 8 && 1 == ((b[startIndex] & 0xFF) >> 7)) {
			sign = 1;
		}
		long sum = 0;
		for (int i = len - 1; i >= 0; i--) {
			sum ^= ((long) (b[startIndex + i] & 0xFF) << 8 * (len - i - 1));
		}
		return 0 == sign ? sum : -1 * (sum + sign);
	}

	/**
	 * 将数组转为float值,此方法会把获取到的int值 / 100,取小数点后两位
	 * 
	 * @param b
	 *            要转化的数组
	 * @return 转化后的float值
	 * @throws IllegalArgumentException
	 *             如果byte的长度大于4,则值不可能为int，直接抛出异常
	 */
	public static float byte2IntFloat(byte[] b) throws IllegalArgumentException {
		int len = b.length;
		if (0 == len) {
			return 0;
		}
		Assert.isTrue(len <= 4, "byte array out of bound, max length is 4.");
		int sum = 0;
		for (int i = len - 1; i >= 0; i--) {
			sum ^= ((b[i] & 0xFF) << 8 * (len - i - 1));
		}
		return sum / 100.0f;
	}

	/**
	 * 将数组转为float值,此方法会把获取到的int值 / 100,取小数点后两位
	 * 
	 * @param b
	 *            要转化的数组
	 * @param startIndex
	 *            数组搜索的下表
	 * @param len
	 *            int值在数组中得length
	 * @return 转化后的float值
	 * @throws IllegalArgumentException
	 *             如果byte的长度大于4,则值不可能为int，直接抛出异常
	 */
	public static float byte2IntFloat(byte[] b, int startIndex, int len) throws IllegalArgumentException {
		Assert.isTrue(len <= 4 && len >= 0, "Byte array out of bound, max length is 4 and min is 0.");
		Assert.isTrue(b.length >= startIndex + len, "Out of bound, len is out of byte array lenth.");
		if (0 == len) {
			return 0;
		}
		int sum = 0;
		for (int i = len - 1; i >= 0; i--) {
			sum ^= ((b[startIndex + i] & 0xFF) << 8 * (len - i - 1));
		}
		return sum / 100.0f;
	}

	/**
	 * 将数组转为float值,
	 * 
	 * @param b
	 *            要转化的数组
	 * @return 转化后的float值
	 */
	public static float byte2Float(byte[] b) throws IllegalArgumentException {
		int num = byte2Int(b);
		return Float.intBitsToFloat(num);
	}

	/**
	 * 将数组转为double值,
	 * 
	 * @param b
	 *            要转化的数组
	 * @return 转化后的double值
	 */
	public static double byte2Double(byte[] b) throws IllegalArgumentException {
		long num = byte2Long(b);
		return Double.longBitsToDouble(num);
	}

	/**
	 * 将数组转为double值,
	 * 
	 * @param b
	 *            要转化的数组
	 * @return 转化后的double值
	 */
	public static double byte2Double(byte[] b, int startIndex, int len) throws IllegalArgumentException {
		Assert.isTrue(len <= 8 && len >= 0, "Byte array out of bound, max length is 8 and min is 0.");
		Assert.isTrue(b.length >= startIndex + len, "Out of bound, len is out of byte array lenth.");
		long num = byte2Long(b, startIndex, len);
		return Double.longBitsToDouble(num);
	}

	/**
	 * 将数组转为float值,
	 * 
	 * @param b
	 *            要转化的数组
	 * @param startIndex
	 *            数组搜索的下表
	 * @param len
	 *            int值在数组中得length
	 * @return 转化后的float值
	 */
	public static float byte2Float(byte[] b, int startIndex, int len) throws IllegalArgumentException {
		Assert.isTrue(len <= 4 && len >= 0, "Byte array out of bound, max length is 4 and min is 0.");
		Assert.isTrue(b.length >= startIndex + len, "Out of bound, len is out of byte array lenth.");
		int num = byte2Int(b, startIndex, len);
		return Float.intBitsToFloat(num);
	}

	/**
	 * 将byte转为16进制字符串输出（每个byte中包含空格）
	 * 
	 * @param b
	 *            要转化的的byte数组
	 * @return 转化后的字符串
	 */
	public static String byte2HexStrConsole(byte[] b) {
		if (ArrayUtil.isEmpty(b)) {
			return StrUtil.EMPTY;
		}
		StringBuilder sb = new StringBuilder();
		for (byte c : b) {
			if ((c & 0xFF) < 0x10) {
				sb.append("0").append(Integer.toHexString(c & 0xFF)).append(" ");
			} else {
				sb.append(Integer.toHexString(c & 0xFF)).append(" ");
			}
		}
		return sb.substring(0, sb.length() - 1);
	}

	/**
	 * 将byte转为16进制字符串
	 * 
	 * @param b
	 *            要转化的的byte数组
	 * @return 转化后的字符串
	 */
	public static String byte2HexStr(byte[] b) {
		if (ArrayUtil.isEmpty(b)) {
			return StrUtil.EMPTY;
		}
		StringBuilder sb = new StringBuilder();
		for (byte c : b) {
			if ((c & 0xFF) < 0x10) {
				sb.append("0").append(Integer.toHexString(c & 0xFF));
			} else {
				sb.append(Integer.toHexString(c & 0xFF));
			}
		}
		return sb.substring(0, sb.length() - 1);
	}
	
	/**
	 * 将hex的字符串转为byte数组
	 * 
	 * @param str hex字符串
	 * @return 转化后的数组
	 */
	public static byte[] hexStr2Byte(String str) {
		str = str.replace(" ", "").toUpperCase();
		char[] c = str.toCharArray();

		byte[] b = new byte[c.length / 2];
		for (int i = 0; i < c.length; i = i + 2) {
			if ('A' == c[i]) {
				b[i / 2] = (byte) 0xA0;
			} else if ('B' == c[i]) {
				b[i / 2] = (byte) 0xB0;
			} else if ('C' == c[i]) {
				b[i / 2] = (byte) 0xC0;
			} else if ('D' == c[i]) {
				b[i / 2] = (byte) 0xD0;
			} else if ('E' == c[i]) {
				b[i / 2] = (byte) 0xE0;
			} else if ('F' == c[i]) {
				b[i / 2] = (byte) 0xF0;
			} else {
				b[i / 2] = (byte) (Integer.valueOf(String.valueOf(c[i])) & 0xFF);
				b[i / 2] = (byte) (b[i / 2] << 4);
			}

			if ('A' == c[i + 1]) {
				b[i / 2] |= 0x0A;
			} else if ('B' == c[i + 1]) {
				b[i / 2] |= 0x0B;
			} else if ('C' == c[i + 1]) {
				b[i / 2] |= 0x0C;
			} else if ('D' == c[i + 1]) {
				b[i / 2] |= 0x0D;
			} else if ('E' == c[i + 1]) {
				b[i / 2] |= 0x0E;
			} else if ('F' == c[i + 1]) {
				b[i / 2] |= 0x0F;
			} else {
				b[i / 2] |= (byte) (Integer.valueOf(String.valueOf(c[i + 1])) & 0xFF);
			}
		}
		return b;
	}
	
	public static Object coverByte2Obj(byte[] b, int startIndex, int len, String clzName) {
		if (clzName.equals(String.class.getName())) {
			return byte2Str(b, startIndex, len);
		}
		if (clzName.equals(Integer.class.getName())) {
			return byte2Int(b, startIndex, len);
		}
		if (clzName.equals(Float.class.getName())) {
			return byte2IntFloat(b, startIndex, len);
		}
		if (clzName.equals(Double.class.getName())) {
			return byte2Double(b, startIndex, len);
		}
		if (clzName.equals(Long.class.getName())) {
			return byte2Long(b, startIndex, len);
		}
		// unsuport type
		log.error("Unsuport error. clz is : {}", clzName);
		return null;
	}
}
