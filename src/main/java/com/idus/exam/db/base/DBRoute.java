package com.idus.exam.db.base;




import com.idus.exam.db.config.SqlSessionTemplateType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DBRoute {
    /**
     * Value.
     *
     * @return the data source type
     */
    SqlSessionTemplateType value() default SqlSessionTemplateType.EXAM_WRITE;
}
