package io.roach.jdbc.sandbox;

import java.sql.Types;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public enum Scenario implements ApplyOption {
    a {
        @Override
        public String description() {
            return "doesnt invoke meta query regardless of 'ignore' setting";
        }

        @Override
        public void apply(MapSqlParameterSource namedParameters) {
            namedParameters.addValue("description", "{\"id\": \"abc\"}", Types.OTHER, "jsonb");
        }
    },
    b {
        @Override
        public String description() {
            return "doesnt invoke meta query regardless of 'ignore' setting";
        }

        @Override
        public void apply(MapSqlParameterSource namedParameters) {
            namedParameters.addValue("description", "{\"id\": \"abc\"}",
                    Types.OTHER);
        }
    },
    c {
        @Override
        public String description() {
            return "always invokes meta query regardless of 'ignore' setting";
        }

        @Override
        public void apply(MapSqlParameterSource namedParameters) {
            namedParameters.addValue("description", null, Types.OTHER,
                    "jsonb");
        }
    },
    d {
        @Override
        public String description() {
            return "doesnt invoke meta query if 'ignore=true'";
        }

        @Override
        public void apply(MapSqlParameterSource namedParameters) {
            namedParameters.addValue("description", null, Types.OTHER);
        }
    },
    e {
        @Override
        public String description() {
            return "doesnt invoke meta query if 'ignore=true'";
        }

        @Override
        public void apply(MapSqlParameterSource namedParameters) {
            namedParameters.addValue("description", null, Types.OTHER,
                    null);
        }
    },
    f {
        @Override
        public String description() {
            return "doesnt invoke meta query if 'ignore=true'";
        }

        @Override
        public void apply(MapSqlParameterSource namedParameters) {
            namedParameters.addValue("description", null);
        }
    };
}
