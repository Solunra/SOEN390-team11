package com.soen390.team11.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.io.Serializable;
import java.util.UUID;

/**
 * UUID generator for Material Inventory
 */
public class MaterialInventoryIDGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String prefix = "matinv-";
        return prefix + UUID.randomUUID().toString();
    }
}
