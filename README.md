# GestorLogisticaWeb_ACN3BV_LukasGalarza_FINAL

Aplicación web full-stack desarrollada con **Spring Boot** para la gestión de productos, movimientos de stock y usuarios con roles diferenciados.

Está pensada para comercios, depósitos, ferreterías o centros logísticos que necesitan controlar inventario, registrar ingresos y egresos, y mantener un historial de movimientos con auditoría por usuario.

## Descripción general

El sistema implementa una arquitectura **MVC** con persistencia en **MySQL**, vistas con **Thymeleaf** y seguridad mediante **Spring Security**. Permite administrar el stock de forma centralizada, controlar accesos por rol y mantener trazabilidad de cada operación.

## Tecnologías utilizadas

- Java 17
- Spring Boot 3.x
- Spring MVC
- Thymeleaf
- Hibernate / Spring Data JPA
- Spring Security
- MySQL Server
- Maven
- Bootstrap 4.5.2
- HTML5
- CSS3

## Estructura general del proyecto

- **Controllers**: `ProductoController`, `MovimientoController`, `UsuarioController`, `LoginController`
- **Model / Entities**: `Producto`, `Movimiento`, `Usuario`, `TipoMovimiento`
- **Repositories**: interfaces de acceso a datos con Spring Data JPA
- **SecurityConfig**: configuración de autenticación, autorización y rutas protegidas
- **Views**: plantillas Thymeleaf ubicadas en `src/main/resources/templates`
- **static/**: recursos públicos como CSS, imágenes y JavaScript
- **application.properties**: configuración de conexión a base de datos y JPA

## Funcionalidades principales

- Inicio de sesión seguro con control de sesión
- Alta, edición y baja de productos
- Registro de movimientos de stock: ingresos y egresos
- Historial de movimientos con usuario y fecha
- Gestión de usuarios con roles diferenciados
- Vistas protegidas según permisos
- Filtros de movimientos por tipo o fecha
- Formularios validados en frontend y backend

## Roles del sistema

### ADMIN
- Acceso total al sistema
- Administración de productos
- Administración de usuarios
- Visualización completa de movimientos

### LOGISTICO
- Gestión operativa de stock
- Registro de ingresos y egresos
- Consulta de productos y movimientos permitidos
- Sin acceso a zonas de administración

## Validaciones implementadas

- El stock no puede quedar negativo
- Los campos numéricos se validan correctamente
- No se permiten productos duplicados por nombre y tipo
- Los movimientos actualizan el stock automáticamente
- Cada acción queda registrada con fecha y usuario
- Los usuarios logísticos no acceden a funciones administrativas

## Base de datos

La aplicación trabaja con una base de datos MySQL y genera las tablas automáticamente al iniciar el proyecto.

### Entidades principales
- `Producto`
- `Usuario`
- `Movimiento`
- `TipoMovimiento`

### Relaciones
- Los movimientos quedan asociados al usuario que los realizó
- Se mantiene historial de auditoría para trazabilidad de operaciones

## Requisitos previos

Antes de ejecutar el proyecto, asegurate de tener instalado:

- Java 17
- Maven
- MySQL Server
- Un IDE compatible como IntelliJ IDEA o Eclipse

## Instalación y ejecución

### 1. Clonar o descargar el proyecto

```bash
git clone https://github.com/Lukarda/GestorLogisticaWeb_ACN3BV_LukasGalarza_FINAL.git
cd GestorLogisticaWeb_ACN3BV_LukasGalarza_FINAL
```

### 2. Crear la base de datos

```sql
CREATE DATABASE gestorlogistica_db;
```

### 3. Configurar `application.properties`

Ubicá el archivo en:

`src/main/resources/application.properties`

Ejemplo de configuración:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestorlogistica_db
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

### 4. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

### 5. Acceder desde el navegador

```text
http://localhost:8080/login
```

## Acceso inicial

- **Usuario:** `admin`
- **Contraseña:** `admin123`

Este usuario cuenta con permisos totales sobre el sistema.

## Observaciones técnicas

- Arquitectura modular basada en Spring Boot + MVC + JPA
- Migrado desde una versión de escritorio en JavaFX
- Interfaz web simple y funcional basada en Bootstrap
- Diseñado para escalar con nuevas entidades como categorías, reportes o historial extendido

## Posibles mejoras futuras

- Reportes y métricas de stock
- Exportación de movimientos a Excel o PDF
- Categorías de productos
- Alertas por stock mínimo
- Historial avanzado de auditoría
- Búsqueda y paginación en listados

## Autor

Proyecto realizado por **Lukas Galarza**.
