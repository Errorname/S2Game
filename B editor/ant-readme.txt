Ant permet de compiler un projet codé avec java.

Pour l'utiliser, il faut lancer un terminal et se placer dans le dossier dans lequel le fichier build.xml est présent.

Les fichiers source doivent se situer dans un dossier nommé src.

Liste des commandes pour le build.xml fourni :

$ ant
Permet de compiler, créer un .jar, créer une javadoc et exécuter le programme

$ ant test
Permet de compiler, créer un .jar et exécuter le programme. Particulièrement utile pour tester une nouvelle fonctionnalité.

$ ant compile
Permet de compiler le programme.

$ ant jar
Permet de créer un .jar mais sans compiler auparavant.

$ ant javadoc
permet de créer la javadoc.

$ ant run
Permet d'exécuter le projet mais sans compiler auparavant.

$ ant clean
Nettoie le projet. A noter que cette commande est appelée automatiquement avant chaque compilation
