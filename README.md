# ProyectoWeb_Servlet

## Sistema de Gestión de Ventas - Parte del Administrador

## 📝 Descripción
Esta parte del sistema está diseñada para que los administradores gestionen de manera eficiente los recursos de la tienda, incluyendo la administración de libros, autores, categorías y editoriales, así como la generación de reportes detallados sobre ventas e inventario.

## 🌟 Funcionalidades
- **Inicio de Sesión:**
  - Acceso seguro para administradores mediante credenciales.
- **Catálogo de Productos:**
  - Búsqueda de libros por título mediante un campo de texto dinámico.
- **Mantenimiento:**
  - CRUD (Crear, Leer, Actualizar, Eliminar) para libros, autores, categorías y editoriales.
- **Reportes:**
  - **Ventas Mensuales:**
    - Filtrado por mes y año.
    - Descarga de reporte en formato PDF con detalles de las ventas.
  - **Inventario:**
    - Visualización de todos los libros con detalles como nombre, stock y estado.
    - Las filas se destacan según el stock:
      - Rojo: Fuera de stock.
      - Amarillo: Stock bajo (≤ 20).
      - Normal: Stock adecuado (> 20).
    - Generación de un PDF con el inventario actualizado.

## 🛠️ Tecnologías Utilizadas
- **Backend:** Java con Servlets y JSP.
- **Base de Datos:** MySQL.
- **Frontend:** HTML, CSS, y JavaScript.
- **PDF:** Generación de archivos con la librería iTextPDF.

## 📋 Requisitos
1. Servidor Apache Tomcat.
2. Base de datos MySQL con las tablas necesarias configuradas.
3. Dependencias de iTextPDF configuradas en el proyecto.

## 🚀 Configuración
1. Clona este repositorio en tu máquina local.
2. Configura la conexión a la base de datos en el archivo de propiedades.
3. Despliega el proyecto en Apache Tomcat.
4. Accede al sistema desde el navegador.

## 🔗 Enlaces
- Para obtener más información como las indicaciones y la base de datos ingresar a este enlace de drive: https://drive.google.com/file/d/1cZ9ey9rGYPDEAHRi35STL0KhesT4FRwn/view?usp=sharing
