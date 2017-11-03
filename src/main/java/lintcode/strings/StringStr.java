package lintcode.strings;

/**
 * 对于一个给定的 source 字符串和一个 target 字符串，你应该在 source 字符串中找出 target
 * 字符串出现的第一个位置(从0开始)。如果不存在，则返回 -1。
 * 
 * @author stray
 *
 */
public class StringStr {

	public int strStr(String source, String target) {
		if (source != null && target != null && source.contains(target)) {
			return source.indexOf(target);
		} else {
			return -1;
		}
	}

}
