package com.github.patsalyukas.archivemanager.aspect;

import com.github.patsalyukas.archivemanager.dictionary.Product;
import com.github.patsalyukas.archivemanager.entities.Audit;
import com.github.patsalyukas.archivemanager.entities.Document;
import com.github.patsalyukas.archivemanager.repositories.AuditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class AuditDocumentAspect {

    private final AuditRepository auditRepository;

    @Pointcut("execution(* com.github.patsalyukas.archivemanager.services.imlp.DocumentServiceImpl.findDocumentById(..))")
    public void auditDocumentAccess() {}

    @AfterReturning(value = "auditDocumentAccess()", returning = "retVal")
    public void logAroundAnnotatedClass(JoinPoint joinPoint, Document retVal) {
        auditRepository.save(Audit.builder()
                .product(Product.DOCUMENT)
                .productCode(retVal.getCode())
                .build()
        );
    }
}
