# Projet extraction d'appels en mode ligne

Le but de ce projet est de créer un programme Java permettant d'exporter les appels d'un service d'urgence au format XML.

Il y a un appel par ligne.

Ce fichier est importable dans Microsoft Excel.

##Utilisation:
```
java ExpCalls [-dbserver db] -u unum [-b début] [-e fin] [-o fichier.xml] [-d] [-t] 
```
où :
* ```-dbserver db``` est la référence à la base de données, par défaut désigne la base de données de développement. Voir fichier *myDatabases.prop* (optionnel).
* ```-u unum``` est la référence du service d'urgence (obligatoire).
* ```-b début``` : date de début de l'extraction à 0h au format JJ/MM/AAAA. Amorcé à hier par défaut (optionnel).
* ```-e fin``` : date de fin de l'extraction à 0h au format JJ/MM/AAAA. Amorcé à aujourd'hui par défaut (optionnel).
* ```-o fichier.xml``` est le nom du fichier qui recevra les agences au format XML. Amorcé à *tickets_0000.xml* par défaut (optionnel).
* ```-d``` le programme s'exécute en mode débug, il est beaucoup plus verbeux. Désactivé par défaut (optionnel).
* ```-t``` le programme s'exécute en mode test, les transcations en base de données ne sont pas faites. Désactivé par défaut (optionnel).

##Pré-requis :
- Java 6 ou supérieur.
- JDBC Informix
- JDBC MySql

##Formats XML reconnus :

Il existe plusieurs types de formats XML reconnus pour décrire les appels. 

Ils dépendent du service d'urgence concerné.

Il y a un format par défaut : *tickets_0000.xsd*.

##Références:

- Construire une application XML, J.C. Bernadac, F. Knab, Eyrolles.

- [OpenClassroom Java XML](https://openclassrooms.com/courses/structurez-vos-donnees-avec-xml/dom-exemple-d-utilisation-en-java)
- [Syntaxe Markdown](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)
- [Tuto OpenClassroom sur DTD](https://openclassrooms.com/courses/structurez-vos-donnees-avec-xml/introduction-aux-definitions-et-aux-dtd)
- [Tuto W3C sur DTD (en)](https://www.google.fr/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&uact=8&sqi=2&ved=0ahUKEwiDrurll-fMAhWHBsAKHYdzAegQFggfMAA&url=http%3A%2F%2Fwww.w3schools.com%2Fxml%2Fxml_dtd_intro.asp&usg=AFQjCNGCt7X2oRyUSkTES1aXf8GljqhekA&bvm=bv.122448493,d.ZGg)
- [Validation fichier XML](http://www.xmlvalidation.com/)
- [Convertisseur DTD/XSD](http://www.freeformatter.com/xsd-generator.html)
- [Tuto XML/XSD](http://www.codeguru.com/java/article.php/c13529/XSD-Tutorial-XML-Schemas-For-Beginners.htm)

##Fichier des paramètres : 

Ce fichier permet de spécifier les paramètres d'accès aux différentes bases de données.

A adapter selon les implémentations locales.

Ce fichier est nommé : *MyDatabases.prop*.

Le fichier *MyDatabases_Example.prop" est fourni à titre d'exemple.
