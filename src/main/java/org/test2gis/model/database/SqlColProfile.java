package org.test2gis.model.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SqlColProfile {
    @EqualsAndHashCode.Include
    private int colId;
    private int colIdSql;
    private String colName;
    private String colDbTypeName;
    private int colSizeDisplay;
    private int colSizeSqlType;
}
