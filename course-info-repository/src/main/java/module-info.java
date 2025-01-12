module courseinfo.repository {
    requires com.h2database;
    requires java.naming;
    requires java.sql;

    exports org.example.repository;
    exports org.example.domain;
}