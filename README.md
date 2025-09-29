# 🧾 Validación de Funcionalidades  

> ⚠️ **Nota:** Revisa el documento adjunto para ver la información más detallada.  
> Este README contiene únicamente un resumen de los puntos clave.

Para validar los puntos funcionales del sistema, se ha incluido una colección de Postman en la ruta principal del proyecto. Esta colección permite verificar los siguientes aspectos:

- **F1: CRUD de entidades**  
  Crear, editar, actualizar y eliminar registros de clientes y cuentas.

- **F2 & F3: Registro de movimientos**  
  Registrar operaciones financieras (depósitos y retiros) asociadas a cuentas.

- **F4: Generación de reportes**  
  Consultar movimientos por rango de fechas y por cliente.

---

# 🐳 Despliegue en Contenedores (F7)

Para facilitar las pruebas y evitar problemas de versiones, se ha subido la base del proyecto a Docker. Esto permite levantar toda la solución de forma rápida y reproducible.

### 🔧 Comandos de ejecución

**Subir el proyecto:**
```bash
docker-compose --env-file .env up --build -d
