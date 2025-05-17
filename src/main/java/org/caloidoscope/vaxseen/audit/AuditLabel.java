package org.caloidoscope.vaxseen.audit;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AuditLabel {
    String value();
}
