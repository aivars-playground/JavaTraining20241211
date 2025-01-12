module courseinfo.server {

    requires courseinfo.repository;
    exports org.example.server to jersey.server;

    requires java.logging;
    requires jakarta.ws.rs;
    requires jersey.container.grizzly2.http;
    requires jersey.common;
    requires jersey.server;
    requires jul.to.slf4j;
    requires org.slf4j;
}
