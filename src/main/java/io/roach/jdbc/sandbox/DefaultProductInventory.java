package io.roach.jdbc.sandbox;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
