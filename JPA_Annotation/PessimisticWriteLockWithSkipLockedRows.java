package br.com.jardelnovaes.utils;

import br.com.jardelnovaes.utils.QueryHintsUtils;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;


@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
//it puts a lock on selected query (e.g. Select ... For Update)
@Lock(LockModeType.PESSIMISTIC_WRITE)
//it adds a SKIP LOCKED on PostgreSQL 9.5+ or a With(rowlock, updlock, readpast) on SQL Server, etc.
@QueryHints({@QueryHint(name = QueryHintsUtils.TIMEOUT_HINT_NAME, value = QueryHintsUtils.UPGRADE_SKIPLOCKED)})
public @interface PessimisticWriteLockWithSkipLockedRows {
}
