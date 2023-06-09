package org.example.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionRecordUtils {

    /**
     * 拼接主键id的方法
     * @param id 主键id
     * @param prefix 前缀
     * @return
     */
    public static String getAltId(Long id, String prefix, LocalDateTime createdAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
        String format = createdAt.format(formatter);
        // 主键id不足四位补0
        StringBuilder idStr = new StringBuilder(id.toString());
        if (idStr.length() < 4) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 4 - idStr.length(); i++) {
                stringBuilder.append(0);
            }
            idStr.append(stringBuilder);
        }
        String altId = prefix + format + idStr.toString();
        return altId;
    }

    /**
     * 还原主键id
     * @param altId
     * @return
     */
    public static Long getIdByAltId(String altId) {
        return Long.parseLong(altId.substring(8));
    }

}
