# AREP BONO PARCIL

## LINK docker hub
~~~
https://hub.docker.com/repository/docker/sebastianporras0914/bonoparcial/general
~~~

![test](https://github.com/sebasporras14/AREP-lab04/blob/master/img/dockerhub.png)

~~~
docker run -d -p 34007:6000 --name otrodocker sebastianporras0914/bonoparcial:latest
~~~


## Corriendo el proyecto

Una vez clonado el proyecto y generado el jar con los siguientes comandos:

~~~
git clone https://github.com/sebasporras14/AREP-bonoparcial.git
~~~
~~~
mvn package
~~~

se puede ejecutar con:

~~~
java -cp "./target/classes" edu.escuelaing.arep.app.SparkWebServer
~~~

## Construido con 
* [Maven](https://maven.apache.org/) - Dependency Management
* [java](https://rometools.github.io/rome/) - Used to generate RSS Feeds


## Autor

* **Sebastian Porras**

### Fecha

09/21/2023 

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
