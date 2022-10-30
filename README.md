
# <strong style="color:blue; opacity: 0.80">Mise en oeuvre d'une architecture micro-services avec Docker </strong>:mortar_board::computer: 
# <span style="color:green "> 1.Présentation de l'activité pratique</span>
 * <strong style="color:dark">Mise en oeuvre d'une application distribuée basée sur deux micro-services en utilisant les bonnes pratiques  :
  - Couches DA0, Service, Web, DTO
  - Utilisation de MapStruct pour le mapping entre les objet Entities et DTO
  - Génération des API-DOCS en utilisant SWAGGER3 (Open API)
  - Communication entre micro-services en utilisant OpenFeign
  - Spring Cloud Gateway
  - Eureka Discovery Service. </span>
## <span style="color:#66ff66"> Entités et règles de gestion : :label:</span>
L’application devra gérer 2 entités. 
Les entités utilisées dans l’application sont : 
* * * 
>	Une entité "Customer" qui comporte les propriétés suivantes :
 - Son id
 - email
 - nom
```java=10
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Customer {
    @Id
    private String id;
    private String name;
}

```

```java=10
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    @Id
    private String id;
    private Date date;
    private BigDecimal amount;
    private String customerId;
    @Transient
    private Customer customer;
}

```

## <span style="color:#66ff66">  les fonctionnalités de l'application :label: </span>
L'application offre les fonctionnalités suivantes :


### 1. <span style="color:#001a33">Ajouter/Supprimer/modifier/consulter un customer.</span>
### 2. <span style="color:#001a33">Ajouter/Supprimer/modifier/consulter un bill.</span>

## <span style="color:#66ff66">Le service "Discovery service" Eureka :label: </span>

![](https://i.imgur.com/PAA90rD.png)

![](https://i.imgur.com/wsarJzI.png)



## <span style="color:#66ff66">Le service de gateway "Spring Cloud gateway service" :label: </span>
![](https://i.imgur.com/Gd96MRt.png)


## <span style="color:#66ff66">La communication entre les micro-services :label: </span>


### L'interface de 'Open Feign' :

```java=10

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping(path = "/api/customers/{id}")
    Customer getCustomerById(@PathVariable(name = "id") String id);

    @GetMapping(path = "api/customers")
    Collection<Customer> getAllCustomers();
}
```



# <span style="color:green">3.Les Technologies utilisées</span>
 #### <span style="color:#0036ad"> 1.Java</span>
 * <strong style="color:dark">* <strong style="color:dark">Java est le langage de choix pour créer des applications à l'aide de code managé qui peut s'exécuter sur des appareils mobiles.

*voir également à propos* [JAVA](https://www.java.com/fr/):link: 


 #### <span style="color:#0036ad"> 2.Spring Data JPA</span>
 * <strong style="color:dark">Spring Data JPA, qui fait partie de la grande famille Spring Data, facilite la mise en œuvre de référentiels basés sur JPA. Ce module traite de la prise en charge améliorée des couches d'accès aux données basées sur JPA. Il facilite la création d'applications alimentées par Spring qui utilisent des technologies d'accès aux données.
    

*voir également à propos de [Spring Data JPA](https://spring.io/projects/spring-data-jpa) :link: 

    
 #### <span style="color:#0036ad"> 3.Mapstruct</span>
 * <strong style="color:dark">MapStruct est un générateur de code qui simplifie considérablement la mise en œuvre des mappages entre les types de bean Java sur la base d'une convention sur l'approche de configuration.
    

*voir également à propos de [Mapstruct](https://www.baeldung.com/mapstruct) :link: 

  #### <span style="color:#0036ad"> 4.Spring Cloud Open Feign</span>
 * <strong style="color:dark">Ce projet fournit des intégrations OpenFeign pour les applications Spring Boot via la configuration automatique et la liaison à l'environnement Spring et à d'autres idiomes de modèle de programmation Spring.
    

*voir également à propos de [Spring Cloud Open Feign](https://spring.io/projects/spring-cloud-openfeign) :link: 
    
  #### <span style="color:#0036ad"> 5.Spring Cloud Gateway</span>
 * <strong style="color:dark">Ce projet fournit une bibliothèque pour créer une passerelle API au-dessus de Spring WebFlux. Spring Cloud Gateway vise à fournir un moyen simple mais efficace d'acheminer vers les API et de leur fournir des préoccupations transversales telles que : la sécurité, la surveillance/les métriques et la résilience.
    

*voir également à propos de [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway) :link: 
    

  #### <span style="color:#0036ad"> 6.Swagger Open API</span>
 * <strong style="color:dark">Swagger est une suite d'outils pour les développeurs d'API de SmartBear Software et une ancienne spécification sur laquelle est basée la spécification OpenAPI.
    

*voir également à propos de [Swagger](https://swagger.io/docs/specification/2-0/what-is-swagger/) :link: 
    
  #### <span style="color:#0036ad"> 7.Docker Compose</span>
 * <strong style="color:dark">Compose est un outil permettant de définir et d'exécuter des applications Docker multi-conteneurs. Avec Compose, vous utilisez un fichier YAML pour configurer les services de votre application.
    

*voir également à propos de [Docker Compose](https://docs.docker.com/compose/) :link: 
    
## <span style="color:green ">4.Structure du projet</span>

>discovery-service
![](https://i.imgur.com/OZ07yCs.png)

    
>gateway-service
    
![](https://i.imgur.com/lhyHAmz.png)

    
>customer-service
![](https://i.imgur.com/05RWOKK.png)

    
>billing service
![](https://i.imgur.com/FfHyhS1.png)


 
  ---




 ## <strong style="color: green; opacity: 0.80" >6.comment ça marche avec Docker?</strong>
1. extraire les fichiers "jar"
![](https://i.imgur.com/0GPBPhf.png)
![](https://i.imgur.com/5knzBPF.png)
![](https://i.imgur.com/horgqD2.png)
![](https://i.imgur.com/PUdomjJ.png)

2. créer les images dans docker

![](https://i.imgur.com/oDkKVhy.png)

3. créer le script .yml pour créer le réseau des conainers
```
  version: '3'
services:
  eureka-service:
    build: ./discovery-service/
    hostname: discovery-service
    ports:
      - "8761:8761"
    networks:
      - default-network

  customer-service:
    build: ./customer-service/
    hostname: customer-service
    ports:
      - "8082:8082"
    depends_on:
      - eureka-service
    environment:
      - eureka.client.service-url.defaultZone=http://discovery-service:8761/eureka
    networks:
      - default-network

  billing-service:
    build: ./Billing-serviceVF/
    hostname: billing-service
    ports:
      - "8083:8083"
    restart: on-failure
    depends_on:
      - customer-service
      - eureka-service
    environment:
      - eureka.client.service-url.defaultZone=http://discovery-service:8761/eureka
    networks:
      - default-network

  gateway-service:
    build: ./gateway-service/
    hostname: gateway-service
    ports:
      - "8888:8888"
    depends_on:
      - customer-service
      - billing-service
      - eureka-service
    environment:
      - eureka.client.service-url.defaultZone=http://discovery-service:8761/eureka
    networks:
      - default-network

networks:
  default-network:
    driver: bridge
  
```
    _> docker-compose up
![](https://i.imgur.com/LJlSgk8.png)
![](https://i.imgur.com/xer16Ki.png)
    
![](https://i.imgur.com/Azmxe8S.png)


>les APIs:
    
![](https://i.imgur.com/Qd4sKcw.png)
    
![](https://i.imgur.com/UI9TQA3.png)

les interfaces de documentations Swagger
![](https://i.imgur.com/t8QHuLw.png)
    
![](https://i.imgur.com/AdkXs0X.png)



    
* <strong style="color: dark ; opacity: 0.80">Enfin nous tenons à remercier le seul et unique, notre professeur Mr YOUSFI Mohamed *Docteur & professeur à l'ENSET MEDIA* pour son soutien et son encouragement envers nous, aussi pour nous avoir donné cette opportunité d'améliorer nos compétences et de connaître les nouvelles technologies comme celles qui nous avons travaillé.

*voir également à propos* Mr [YOUSSFI Mohamed](https://www.linkedin.com/in/mohamed-youssfi-3ab0811b/)
</strong>

> Created by :[name=ELMAJNI KHAOULA]
[time=Mon,2022,10,20][color=#EF0101]
>*voir également à propos de moi* [ELMAJNI Khaoula](https://www.linkedin.com/in/khaoula-elmajni/)