package com.realm.generator;

/* This class is automatically generated from the .ftl templates */
public class Templates {
    public static final String TABLE = "${java_header}\r\n<#if packageName?has_content>\r\npackage ${packageName};\r\n</#if>\r\n\r\nimport com.realm.*;\r\nimport com.realm.typed.*;\r\n\r\n/**\r\n * This class represents a TightDB table and was automatically generated.\r\n */\r\n<#if isNested>public class ${tableName} extends AbstractSubtable<${cursorName}, ${viewName}, ${queryName}> {\r\n<#else>public class ${tableName} extends AbstractTable<${cursorName}, ${viewName}, ${queryName}> {\r\n</#if>\r\n\tpublic static final EntityTypes<${tableName}, ${viewName}, ${cursorName}, ${queryName}> TYPES = new EntityTypes<${tableName}, ${viewName}, ${cursorName}, ${queryName}>(${tableName}.class, ${viewName}.class, ${cursorName}.class, ${queryName}.class);\r\n\r\n<#foreach f in columns><#if f.isSubtable>\tpublic final ${f.type}TableColumn<${cursorName}, ${viewName}, ${queryName}, ${f.subTableName}> ${f.name} = new ${f.type}TableColumn<${cursorName}, ${viewName}, ${queryName}, ${f.subTableName}>(TYPES, table, ${f.index}, \"${f.name}\", ${f.subTableName}.class);\r\n<#else>\tpublic final ${f.type}TableColumn<${cursorName}, ${viewName}, ${queryName}> ${f.name} = new ${f.type}TableColumn<${cursorName}, ${viewName}, ${queryName}>(TYPES, table, ${f.index}, \"${f.name}\");\r\n</#if></#foreach>\r\n<#if isNested>\tpublic ${tableName}(Table subtable) {\r\n\t\tsuper(TYPES, subtable);\r\n\t}\r\n<#else>\tpublic ${tableName}() {\r\n\t\tsuper(TYPES);\r\n\t}\r\n\r\n\tpublic ${tableName}(Group group) {\r\n\t\tsuper(TYPES, group);\r\n\t}\r\n\t\r\n\tpublic ${tableName}(Group group, String tableName) {\r\n\t\tsuper(TYPES, group, tableName);\r\n\t}\r\n</#if>\r\n\tpublic static void specifyStructure(TableSpec spec) {\r\n<#foreach f in columns><#if f.isSubtable>        add${f.type}Column(spec, \"${f.name}\", new ${f.subTableName}(null));\r\n<#else>        add${f.type}Column(spec, \"${f.name}\");\r\n</#if></#foreach>    }\r\n\r\n${add}\r\n\r\n${insert}\r\n\r\n}\r\n";
    public static final String TABLE_ADD = "    public ${cursorName} add(<#foreach f in columns><#if (f_index > 0)>, </#if>${f.paramType} ${f.name}</#foreach>) {\r\n        return insert(size()<#foreach f in columns>, ${f.name}</#foreach>);\r\n    }";
    public static final String TABLE_INSERT = "    public ${cursorName} insert(long _rowIndex<#foreach f in columns>, ${f.paramType} ${f.name}</#foreach>) {\r\n        try {\r\n        <#foreach f in columns><#if f.isSubtable>    insert${f.type}(${f.index}, _rowIndex, ${f.name});\r\n        <#else>\tinsert${f.type}(${f.index}, _rowIndex, ${f.name});\r\n        </#if></#foreach>    insertDone();\r\n\r\n            return cursor(_rowIndex);\r\n        } catch (Exception e) {\r\n            throw new RuntimeException(\"Error occured while adding/inserting a new row!\", e);\r\n        }\r\n    }";
    public static final String CURSOR = "${java_header}\r\n<#if packageName?has_content>\r\npackage ${packageName};\r\n</#if>\r\n\r\nimport com.realm.*;\r\nimport com.realm.typed.*;\r\n\r\n/**\r\n * This class represents a TightDB cursor and was automatically generated.\r\n */\r\npublic class ${name} extends AbstractCursor<${name}> {\r\n\r\n<#foreach f in columns><#if f.isSubtable>    private final ${f.type}CursorColumn<${name}, ${viewName}, ${queryName}, ${f.subCursorName}, ${f.subTableName}> ${f.name};\r\n<#else>    private final ${f.type}CursorColumn<${cursorName}, ${viewName}, ${queryName}> ${f.name};\r\n</#if></#foreach>\r\n\r\n\tpublic ${cursorName}(TableOrView tableOrView, long _rowIndex) {\r\n\t\tsuper(${tableName}.TYPES, tableOrView, _rowIndex);\r\n\r\n<#foreach f in columns><#if f.isSubtable>        ${f.name} = new ${f.type}CursorColumn<${cursorName}, ${viewName}, ${queryName}, ${f.subCursorName}, ${f.subTableName}>(${tableName}.TYPES, this, ${f.index}, \"${f.name}\", ${f.subTableName}.class);\r\n<#else>        ${f.name} = new ${f.type}CursorColumn<${cursorName}, ${viewName}, ${queryName}>(${tableName}.TYPES, this, ${f.index}, \"${f.name}\");\r\n</#if></#foreach>\r\n\r\n    }\r\n\r\n<#foreach f in columns><#if f.isSubtable>\tpublic ${f.subTableName} get${f.name?cap_first}() {\r\n<#else>\tpublic ${f.fieldType} get${f.name?cap_first}() {\r\n</#if>\t\treturn this.${f.name}.get();\r\n\t}\r\n\r\n<#if f.isSubtable>\tpublic void set${f.name?cap_first}(${f.subTableName} ${f.name}) {\r\n<#else>\tpublic void set${f.name?cap_first}(${f.fieldType} ${f.name}) {\r\n</#if>\t\tthis.${f.name}.set(${f.name});\r\n\t}\r\n\r\n</#foreach>\t@Override\r\n\tpublic AbstractColumn<?, ?, ?, ?>[] columns() {\r\n\t\treturn getColumnsArray(<#foreach f in columns>${f.name}<#if f_has_next>, </#if></#foreach>);\r\n\t}\r\n\r\n    public void set(<#foreach f in columns><#if (f_index > 0)>, </#if><#if f.isSubtable>${f.subTableName}<#else>${f.fieldType}</#if> ${f.name}</#foreach>) {\r\n<#foreach f in columns>\r\n        this.${f.name}.set(${f.name});\r\n</#foreach>\r\n    }\r\n    \r\n}\r\n";
    public static final String VIEW = "${java_header}\r\n<#if packageName?has_content>\r\npackage ${packageName};\r\n</#if>\r\n\r\nimport com.realm.*;\r\nimport com.realm.typed.*;\r\n\r\n/**\r\n * This class represents a TightDB view and was automatically generated.\r\n */\r\npublic class ${viewName} extends AbstractView<${cursorName}, ${viewName}, ${queryName}> {\r\n\r\n<#foreach f in columns><#if f.isSubtable>\tpublic final ${f.type}ViewColumn<${cursorName}, ${viewName}, ${queryName}, ${f.subTableName}> ${f.name} = new ${f.type}ViewColumn<${cursorName}, ${viewName}, ${queryName}, ${f.subTableName}>(${tableName}.TYPES, tableOrView, ${f.index}, \"${f.name}\", ${f.subTableName}.class);\r\n<#else>\tpublic final ${f.type}ViewColumn<${cursorName}, ${viewName}, ${queryName}> ${f.name} = new ${f.type}ViewColumn<${cursorName}, ${viewName}, ${queryName}>(${tableName}.TYPES, tableOrView, ${f.index}, \"${f.name}\");\r\n</#if></#foreach>\r\n\tpublic ${viewName}(TableView view) {\r\n\t\tsuper(${tableName}.TYPES, view);\r\n\t}\r\n\r\n}\r\n";
    public static final String QUERY = "${java_header}\r\n<#if packageName?has_content>\r\npackage ${packageName};\r\n</#if>\r\n\r\nimport com.realm.*;\r\nimport com.realm.typed.*;\r\n\r\n/**\r\n * This class represents a TightDB query and was automatically generated.\r\n */\r\npublic class ${name} extends AbstractQuery<${name}, ${cursorName}, ${viewName}> {\r\n\r\n<#foreach f in columns><#if f.isSubtable>    public final ${f.type}QueryColumn<${cursorName}, ${viewName}, ${name}, ${f.subTableName}> ${f.name};\r\n<#else>    public final ${f.type}QueryColumn<${cursorName}, ${viewName}, ${name}> ${f.name};\r\n</#if></#foreach>\r\n\tpublic ${name}(Table table, TableQuery query) {\r\n\t\tsuper(${tableName}.TYPES, table, query);\r\n<#foreach f in columns><#if f.isSubtable>        ${f.name} = new ${f.type}QueryColumn<${cursorName}, ${viewName}, ${name}, ${f.subTableName}>(${tableName}.TYPES, table, query, ${f.index}, \"${f.name}\", ${f.subTableName}.class);\r\n<#else>        ${f.name} = new ${f.type}QueryColumn<${cursorName}, ${viewName}, ${name}>(${tableName}.TYPES, table, query, ${f.index}, \"${f.name}\");\r\n</#if></#foreach>\t}\r\n\r\n}\r\n";
}
