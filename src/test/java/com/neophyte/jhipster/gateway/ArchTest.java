package com.neophyte.jhipster.gateway;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.neophyte.jhipster.gateway");

        noClasses()
            .that()
            .resideInAnyPackage("com.neophyte.jhipster.gateway.service..")
            .or()
            .resideInAnyPackage("com.neophyte.jhipster.gateway.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.neophyte.jhipster.gateway.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
