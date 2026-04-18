package main.java.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 与相似度计算相关的方法
public class UtilsSimilarity {
    private static final Logger logger = LoggerFactory.getLogger(UtilsSimilarity.class);

    // 计算绝对xpath路径的相似度，将值控制在 0~1 之间
    // 公式：1 - 编辑距离 / 标签数中的较大值
    public static double simOfXpath(String xpath1, String xpath2) {
        // 计算编辑距离
        if (xpath1 != null && xpath2 != null) {
            xpath1 = xpath1.replace("//hierarchy", "");
            xpath2 = xpath2.replace("//hierarchy", "");

            int k;
            for (k=0; k<xpath1.length(); k++) {
                if (xpath1.charAt(k) != '/') break;
            }
            xpath1 = xpath1.substring(k);
            for (k=0; k<xpath2.length(); k++) {
                if (xpath2.charAt(k) != '/') break;
            }
            xpath2 = xpath2.substring(k);

            String str1[] = xpath1.split("/");
            String str2[] = xpath2.split("/");
            int n = str1.length;
            int m = str2.length;
            if (n == 0 || m == 0) {
                return 0;
            } else {
                if (n > m) {
                    String[] tmp = str1;
                    str1 = str2;
                    str2 = tmp;
                    n = m;
                    m = tmp.length;
                }

                int[] p = new int[n + 1];

                int i;
                for (i = 0; i <= n; p[i] = i++);

                for (int j = 1; j <= m; ++j) {
                    int upper_left = p[0];
                    String s_j = str2[j - 1];
                    p[0] = j;

                    for (i = 1; i <= n; ++i) {
                        int upper = p[i];
                        int cost = str1[i - 1].equalsIgnoreCase(s_j) ? 0 : 1;
                        p[i] = Math.min(Math.min(p[i - 1] + 1, p[i] + 1), upper_left + cost);
                        upper_left = upper;
                    }
                }

                return  1.0 - (double) p[n] / m;
            }
        } else {
            logger.error("Strings must not be null");
        }

        return 0.0;
    }
}
