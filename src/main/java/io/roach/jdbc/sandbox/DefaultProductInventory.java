package io.roach.jdbc.sandbox;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultProductInventory implements ProductInventory {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createInventory(Scenario scenario) {
        UUID id = UUID.randomUUID();

        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
//                .addValue("description", "{\"id\": \"abc\"}", Types.OTHER, "jsonb") //  doesnt invoke meta query regardless of "ignore=true"
//                .addValue("description", "{\"id\": \"abc\"}", Types.OTHER) //  doesnt invoke meta query regardless of "ignore=true"
//                .addValue("description", null, Types.OTHER, "jsonb") //  invokes meta query regardless of "ignore=true"
//                .addValue("description", null, Types.OTHER) //  doesnt invoke meta query if "ignore=true"
//                .addValue("description", null, Types.OTHER, null) //  doesnt invoke meta query if "ignore=true"
//                .addValue("description", null) //  doesnt invoke meta query if "ignore=true"
                .addValue("id", id)
                .addValue("inventory", 999)
                .addValue("name", "product-x")
                .addValue("price", BigDecimal.TEN)
                .addValue("sku", "X-999");

        scenario.apply(namedParameters);

        namedParameterJdbcTemplate.update("insert into product (id, inventory, name, description, price, sku) " +
                        "values (:id,:inventory,:name,:description,:price,:sku)",
                namedParameters);
    }
}
