package com.soen390.team11.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PartMaterialIDGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String prefix = "prtmat-";
        Connection connection = session.connection();

        try {
            Statement statement=connection.createStatement();

            ResultSet rs=statement.executeQuery("select count(id) from part_material");

            if(rs.next())
            {
                int id=rs.getInt(1) + 1;
                String generatedId = prefix + Integer.toString(id);
                return generatedId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
