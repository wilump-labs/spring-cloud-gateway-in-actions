CREATE TABLE IF NOT EXISTS api_route (
    id bigint auto_increment primary key,
    route_identifier VARCHAR(255) NOT NULL,
    uri VARCHAR(255) NOT NULL,
    method VARCHAR(255) NOT NULL,
    path VARCHAR(255) NOT NULL
);

insert into gateway.api_route (route_identifier, uri, method, path)
values  ('a-service', 'http://localhost:18090', 'GET', '/a/**'),
        ('b-service', 'http://localhost:18091', 'GET', '/b/**');