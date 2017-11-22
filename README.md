# Projet extraction d'appels en mode ligne

Le but de ce projet est de créer un programme Java permettant d'exporter au format XML les appels d'un service d'urgence.

Il y a un appel par ligne.

Ce fichier est importable dans Microsoft Excel.

## Utilisation:
```
java ExpCalls [-dbserver db] -u unum [[-b début] [-e fin]|[-n nbJour]] [-o fichier.xml] [-p path] [-s suffixe] [-d] [-t] 
```
où :
* ```-dbserver db``` est la référence à la base de données, par défaut désigne la base de données de développement. Voir fichier *ExpCalls.prop* (optionnel).
* ```-u unum``` est la référence du service d'urgence (paramètre obligatoire).
* ```-b début``` : date de début de l'extraction à 0h au format JJ/MM/AAAA. Amorcé à hier par défaut (paramètre optionnel).
* ```-e fin``` : date de fin de l'extraction à 0h au format JJ/MM/AAAA. Amorcé à aujourd'hui par défaut (paramètre optionnel).
* ```-n nbJour``` : précise le nombre de jour(s) à compter de la date courante. Non défini par défaut (paramètre optionnel).
* ```-o fichier.xml``` est le nom du fichier qui recevra les résultats au format XML. Amorcé à *tickets_0000.xml* par défaut (paramètre optionnel).
* ```-p path``` est le répertoire vers lequel exporter le fichier des résultats. Par défaut c'est le répertoire courant du programme (paramètre optionnel).
* ```-s suffixe``` est le suffixe à ajouter au nom du fichier. Par défaut il n'y a pas de suffixe (paramètre optionnel).
* ```-d``` le programme s'exécute en mode débug, il est beaucoup plus verbeux. Désactivé par défaut (paramètre optionnel).
* ```-t``` le programme s'exécute en mode test, les transactions en base de données ne sont pas faites. Désactivé par défaut (paramètre optionnel).

## Pré-requis :
- Java 6 ou supérieur.
- JDBC Informix
- JDBC MySql

## Formats XML reconnus :

Il existe plusieurs types de formats XML reconnus pour décrire les appels. 

Ils dépendent du service d'urgence concerné.

Il y a un format par défaut : *tickets_0000.xsd*.

## Fichier des paramètres : 

Ce fichier permet de spécifier les paramètres d'accès aux différentes bases de données.

A adapter selon les implémentations locales.

Ce fichier est nommé : *ExpCalls.prop*.

Le fichier *ExpCalls_Example.prop* est fourni à titre d'exemple.

## Références:

- Construire une application XML, J.C. Bernadac, F. Knab, Eyrolles.

- [OpenClassroom Java XML](https://openclassrooms.com/courses/structurez-vos-donnees-avec-xml/dom-exemple-d-utilisation-en-java)
- [Syntaxe Markdown](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)
- [Tuto OpenClassroom sur DTD](https://openclassrooms.com/courses/structurez-vos-donnees-avec-xml/introduction-aux-definitions-et-aux-dtd)
- [Tuto W3C sur DTD (en)](https://www.google.fr/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&uact=8&sqi=2&ved=0ahUKEwiDrurll-fMAhWHBsAKHYdzAegQFggfMAA&url=http%3A%2F%2Fwww.w3schools.com%2Fxml%2Fxml_dtd_intro.asp&usg=AFQjCNGCt7X2oRyUSkTES1aXf8GljqhekA&bvm=bv.122448493,d.ZGg)
- [Validation fichier XML](http://www.xmlvalidation.com/)
- [Convertisseur DTD/XSD](http://www.freeformatter.com/xsd-generator.html)
- [Tuto XML/XSD](http://www.codeguru.com/java/article.php/c13529/XSD-Tutorial-XML-Schemas-For-Beginners.htm)
- [Java et Excel avec POI](http://poi.apache.org/)
- [Java et Excel tuto](http://www.jmdoudoux.fr/java/dej/chap-generation-documents.htm)
