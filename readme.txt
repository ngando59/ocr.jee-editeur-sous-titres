Librairies Utilisées pour l'application :
	- jstl-1.2.jar (SubtitlesEditor1.0/WebContent/WEB-INF/lib/jstl-1.2.jar)
	- mariadb-java-client-2.2.6.jar.jar (SubtitlesEditor1.0/WebContent/WEB-INF/lib/mariadb-java-client-2.2.6.jar.jar)

Configuration :
	- Base de données : mariadb
	- Serveur : Tomcat 7.0
	- Fichier temporaire de upload : /tmpUpload/ à créer ou modifier la ligne <location>/tmpUpload/</location> dans SubtitlesEditor1.0/WebContent/WEB-INF/web.xml  
	  chez moi dans (C:\Users\NomUtilisateur\Documents\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina\localhost\SubtitlesEditor1.0\)

creation de la base de données :
	- bdd.sql (SubtitlesEditor1.0/bdd.sql)
	- Informations et driver de connexion à la base de données fichier src/com/ocr/dao/DaoFactory.java
	
L'application se lance avec : http://localhost:8080/SubtitlesEditor1.0/
	
L'application permet de :

	- Uploader un fichier de sous-titres .srt
	- Editer les sous-titres du fichier uploader.
	- Sauvegarder les sous-titres du fichier uploader en cours dans la base données.
	- Télécharger les sous-titres du fichier uploader en cours.
	- Ajouter une traduction pour les sous-titres uploader en cours.
	- Charger un fichier sous-titre enregistré dans la base de données.
	- Editer un fichier sous-titre enregistré dans la base de données.
	- Ajouter une traduction d'un fichier sous-titre enregistré dans la base de données.
	- Mettre à jour un fichier sous-titre enregistré dans la base de données
	- Télécharger un fichier sous-titre enregistré dans la base de données
	- Charger une traduction enregistrée dans la base de données.
	- Mettre à jour une traduction enregistrée dans la base de données.
	- Télécharger une traduction enregistrée dans la base de données