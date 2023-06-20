package com.rmarrugo.enumeration;

import com.rmarrugo.exception.PreconditionFailedException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Stream;

@Slf4j
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public enum IdentificationType {

    IDENTIFICATION("C", "Cédula"),
    PASSPORT("P", "Pasaporte");

    String code;
    String description;

    public static IdentificationType findByCode(String code) throws PreconditionFailedException {
        return Stream.of(IdentificationType.values())
                .filter(identificationType -> identificationType.code.equals(code))
                .findFirst()
                .orElseThrow(() -> {
                    log.warn("Identification type {} is not allowed", code);
                    return new PreconditionFailedException("Identification type is not allowed");
                });
    }
}
