# APP-HEALTCHECK
## App para verificar el estado de una base de datos. ##

## Sumario
* [Asignando variables necesarias](#asignando-variables-necesarias)
* [Ejecutar la app](#ejecutar-la-app)
* [Ejecutar la app dockerizada](#ejecutar-la-app-dockerizada)
* [Documentacion de los servicios](#documentacion-de-los-servicios)
* [Swagger - Open Api, documentación del proyecto](#documentación-del-proyecto)

## Asignando variables necesarias
Las variables necesarias son:
  * **url** - url del host de la base de datos
 * **driver** - nombre del driver necesario
 * **username** - username del acceso para la bd
 * **password** - password del acceso para la bd


## Ejecutar la app
comando para ejecutarlo
```bash
java -jar app.jar
```

## Ejecutar la app dockerizada
Las variables necesarias son:
  * **url**
 * **driver**
 * **username**
 * **password**
```bash
docker container run --env username=scott --env password=tigger --env url=jdbc:oracle:thin:@host.docker.internal:1521:ORCL --env driver=oracle.jdbc.driver.OracleDriver -ti -p 8080:8080 dalozz/app_healtcheck
```
## Documentacion de los servicios
El acceso para la documentación de los servicios web es:
- [/app/swagger-ui.html](/app/swagger-ui.html)

Ejemplo:
- [http://localhost:8080/app/](http://localhost:8080/app/)