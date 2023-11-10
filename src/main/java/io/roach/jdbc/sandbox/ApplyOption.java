package io.roach.jdbc.sandbox;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public interface ApplyOption {
    String description();

    void apply(MapSqlParameterSource namedParameters);
}
