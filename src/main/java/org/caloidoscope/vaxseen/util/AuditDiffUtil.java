package org.caloidoscope.vaxseen.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AuditDiffUtil {

    public static List<String> getDifferences(Object oldObj, Object newObj) {
        List<String> diffs = new ArrayList<>();

        if (oldObj == null || newObj == null || !oldObj.getClass().equals(newObj.getClass())) {
            return diffs;
        }

        Field[] fields = oldObj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                Object oldVal = field.get(oldObj);
                Object newVal = field.get(newObj);

                if (oldVal == null && newVal == null) continue;
                if (!Objects.equals(oldVal, newVal)) {
                    diffs.add(String.format("- %s: %s → %s", field.getName(), oldVal, newVal));
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to access field: " + field.getName(), e);
            }
        }

        return diffs;
    }
}
