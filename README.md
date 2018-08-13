# springboot-rest-pku-visit-permit

A project to check the tickets available to visit Beijing University. The original website is located at https://visitor.pku.edu.cn/visitor/modules/tourist/indexW.html#/touristWeb.

The main program will loop every 5 minutes to check. In each loop, it will call 3 URLs to get the data. First URL will retrieve the cookie. Before 3rd URL, there will be a 11 seconds sleep. 

The project will depend on spring boot RestTemplate. The base URL is https://spring.io/guides/gs/consuming-rest/.
