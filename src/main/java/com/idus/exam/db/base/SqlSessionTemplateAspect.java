package com.idus.exam.db.base;


import com.idus.exam.db.config.SqlSessionTemplateType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SqlSessionTemplateAspect {

    @Around(value = "@within(dBRoute) || @annotation(dBRoute)")
    public Object setSqlMapClient(ProceedingJoinPoint pjp, DBRoute dBRoute) throws Throwable {
        Object returnObj = null;

        try {
            if (dBRoute != null) {
                SqlSessionTemplateTypeContextHolder.setSqlSessionTemplateTypeId(dBRoute.value());
            } else {
                DBRoute classAnnotation = (DBRoute) pjp.getSignature().getDeclaringType().getAnnotation(DBRoute.class);
                SqlSessionTemplateTypeContextHolder.setSqlSessionTemplateTypeId(classAnnotation.value());
            }
            returnObj = pjp.proceed();
        } catch (Throwable t) {
            throw t;
        } finally {
            SqlSessionTemplateTypeContextHolder.setSqlSessionTemplateTypeId(SqlSessionTemplateType.EXAM_WRITE);
        }

        return returnObj;
    }
}
