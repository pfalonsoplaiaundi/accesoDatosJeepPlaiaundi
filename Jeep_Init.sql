DROP DATABASE IF EXISTS jeep;
CREATE DATABASE IF NOT EXISTS jeep;
USE jeep; 

-- Structure
CREATE TABLE Facturas (
    id bigint unsigned auto_increment,
    subtotal decimal NOT NULL,
    idEmpleado bigint unsigned,
    fecha date NOT NULL,
    iva decimal NOT NULL,
    ptoGenerados bigint NOT NULL,
    bPagado boolean NOT NULL,
    CONSTRAINT pk_table_facturas_id PRIMARY KEY (id)
);

CREATE TABLE proveedores (
    id bigint unsigned auto_increment,
    nombre varchar(100),
    CIF char(9) unique,
    direccion text,
    prioridad integer,
    CONSTRAINT pk_table_proveedores_id PRIMARY KEY (id)
);

CREATE TABLE empleados (
	id bigint unsigned auto_increment,
    rol enum('admin', 'jefe', 'empleado') NOT NULL,
    idUsuario bigint unsigned NOT NULL,
    CONSTRAINT pk_table_empleados_id PRIMARY KEY (id)
);

CREATE TABLE productosPedido (
	id bigint unsigned auto_increment,
	idPedido bigint unsigned NOT NULL,
    precioFinal decimal NOT NULL,
    dctoPorcen decimal NOT NULL,
    cantidad integer NOT NULL,
    idProducto bigint unsigned,
    CONSTRAINT pk_table_productosPedido_id PRIMARY KEY(id)
);

CREATE TABLE pedidosProveedor (
	id bigint unsigned auto_increment,
    idProducto bigint unsigned,
    fecPedido date,
    fecRecibo date,
    idEmpleado bigint unsigned,
    idProveedor bigint unsigned,
    cantidad bigint,
    precio decimal,
    CONSTRAINT pk_table_pedidosProveedor_id PRIMARY KEY (id)
);

CREATE TABLE pedidosVenta (
	id bigint unsigned auto_increment,
    fecha date NOT NULL,
    bRecoger boolean NOT NULL,
    fecEntrega date,
    direccion text NOT NULL,
    idFactura bigint unsigned,
    idCliente bigint unsigned NOT NULL,
    CONSTRAINT pk_table_pedidosVenta_id PRIMARY KEY (id)
);

CREATE TABLE productos (
    id bigint unsigned auto_increment,
    alias varchar(200) NOT NULL,
    descripcion text,
    precioVenta decimal(10,2) NOT NULL,
    precioCompra decimal(10,2) NOT NULL,
    stock bigint NOT NULL,
    categoria enum('juguetes', 'alimentos', 'maquinaria', 'herramientas', 'papeleria', 'manualidades', 'tecnologia', 'mascotas', 'cuidado_personal') NOT NULL,
    tipoIva integer unsigned NOT NULL,
    CONSTRAINT pk_table_productos_id PRIMARY KEY (id)
);

CREATE TABLE clientes (
    id bigint unsigned auto_increment,
    ptoFidelidad integer unsigned,
    direccion text NOT NULL,
    deuda decimal,
    idUsuario bigint unsigned NOT NULL,
    CONSTRAINT pk_table_clientes_id PRIMARY KEY (id)
);

CREATE TABLE usuarios (
    id bigint unsigned auto_increment,
    nombre varchar(100) NOT NULL,
    ape varchar(100) NOT NULL,
    pass varchar(256) NOT NULL,
    email varchar(200) NOT NULL,
    dni char(9) NOT NULL,
    CONSTRAINT pk_table_usuarios_id PRIMARY KEY (id)
);

-- Foreign key constraints
ALTER TABLE clientes ADD CONSTRAINT fk_Clientes_idUsuario_Usuarios_id FOREIGN KEY(idUsuario) REFERENCES Usuarios(id);
ALTER TABLE facturas ADD CONSTRAINT fk_Facturas_idEmpleado_Empleados_id FOREIGN KEY(idEmpleado) REFERENCES empleados(id);
ALTER TABLE pedidosProveedor ADD CONSTRAINT fk_PedidosProveedor_idEmpleado_Empleados_id FOREIGN KEY(idEmpleado) REFERENCES empleados(id);
ALTER TABLE pedidosProveedor ADD CONSTRAINT fk_PedidosProveedor_idProducto_Productos_id FOREIGN KEY(idProducto) REFERENCES productos(id);
ALTER TABLE pedidosVenta ADD CONSTRAINT fk_PedidosVenta_idCliente_Clientes_id FOREIGN KEY(idCliente) REFERENCES clientes(id);
ALTER TABLE pedidosVenta ADD CONSTRAINT fk_PedidosVenta_idFactura_Facturas_id FOREIGN KEY(idFactura) REFERENCES facturas(id);
ALTER TABLE productosPedido ADD CONSTRAINT fk_productosPedido_idPedido_PedidoVenta_id FOREIGN KEY(idPedido) REFERENCES pedidosVenta(id);
ALTER TABLE productosPedido ADD CONSTRAINT fk_productosPedido_idProducto_Productos_id FOREIGN KEY(idProducto) REFERENCES productos(id);
ALTER TABLE pedidosProveedor ADD CONSTRAINT fk_PedidosProveedor_idProveedor_Proveedores_id FOREIGN KEY(idProveedor) REFERENCES proveedores(id);
ALTER TABLE empleados ADD CONSTRAINT fk_Empleados_idUsuario_Usuarios_id FOREIGN KEY(idUsuario) REFERENCES usuarios(id);

-- USERS
-- localhost
-- create user "jeep"@"localhost" identified by "jip";
grant all on jeep.* to "jeep"@"localhost";
-- pablo
-- create user "jeep"@10.10.13.120 identified by "jip";
grant all on jeep.* to "jeep"@10.10.13.120;
-- iker
-- create user "jeep"@10.10.13.115 identified by "jip";
grant all on jeep.* to "jeep"@10.10.13.115;