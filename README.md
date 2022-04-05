# LPsecurity 
Réalisaion d'un **plugin de sécurité/sanction** pour **Minecraft** sous version 1.8.8 avec un **Proxy** et une base de données.

# LPsecurity ?
Est **un plugin** (sous java 8) qui a pour bute de **sécurisé** un serveur Minecraft en version 1.8.8 relier à un proxy et de **sanctioner** les utilisateur mal intentionner  en **récupérent** les données des utilisateurs et en les **enregistrant** dans une base de donnée pour les réutiliser. Il bénéficie de commande utile à la sécurité et la sanction.
> Proxy ([BungeeCord](https://www.spigotmc.org/wiki/bungeecord/))
, Serveur Minecraft ([Spigot 1.8.8](https://www.spigotmc.org))

# Infrastructure d'une machine hôte.

![Diagramme de l'infrastructure hôte](doc/DiagInfrastructure.drawio.png)

**La machine hote** execute les différents processus. 
- Nous avons en exemple trois **serveur Minecraft** (ou plus) relier à un serveur **proxy** qui gère la connection entre les différents serveur Minecraft et  un serveur **mysql**.
- Afin de **sécurisé les connections des joueurs** nous avons besoin d'un **plugin**. Ceci évitera les voles de compte permetra une gestion de ses utilisateur grace à une base de données le tout sur la même machine.


## Infrastructure de la Base de donnée.(Les données conserver.)
| Nom              | Type            | Donnée(s) par default| Description |
| :---------------:|:---------------:| :--------------:     | :---------------:  |
| id  |int(Auto increment)|n+1|Clef primaire de relation entre les tables.		| 
| uuid  |varchar(32)|null|Uuid du joueur recupérer automatique via LPsecurity.		|
| pseudo|varchar(255)|null|Pseudo du joueur recupérer automatique via LPsecurity.		| 
| password|varchar(64)|null|Password du joueur recupérer gace à saisie de la commande "register" et hacher tout via LPsecurity.|		 
| ban|int(1)|0|Bannisement vraie ou faux en fonction du nom du joueur. 0 ou 1		| 
| warn|int(1)|0| ? | 
| historique|longtexte|null|Information des sanctions sur un joueur|

# La partie sécurité intègre:
## Commande securiter (Sera amené à changer dans le temps).

| Nomenclature | Nom |Option| Arguments |Permission| Description| 
| :---------------:|:---------------:| :--------------:     | :---------------:  |  :---------------:  | :---------------:| 
|/|banish|null|'nom joueur' 0 ou 1|LP.security.ban| Bannir ou debannir (0/1)| 
|/|login|null|'password'|LP.security.login|Identifier le joueur à la connection.| 
|/|register|null|'password' 'password'|LP.security.register|Enregistre le joueur à sa première connection.| 

> La donnée "password" est soumise à une fonction de hachage: ici la SHA 256 avec un préfixe de salage.
Elle intègre un côte passif, géré dans le code. Lorsque un joueur est déja en ligne celui-ci ne peut être déconnecté via un lanceur non officiel grâce à son pseudonyme.
Un joueur mal intentionné ne peut pas connecter plus de 1 à N (Un nombre N au choix.) comptes avec la même adresse IP (Internet Protocol) ainsi que pour les joueurs enregistrés.

# La partie sanction intègre:
## Commande sanction (Sera amené à changer dans le temps).

| Nomenclature | Nom |Option| Arguments |Permission| Description| 
|:---------------:|:---------------:| :--------------:| :---------------:  | :---------------:  | :---------------:| 
|/|ban|null|'nom joueur' 'raison !=null'|LP.sanction.ban|Bannir un joueur avec raison obligatoire.| 
|/|unban|null|'nom joueur''raison !=null'|LP.sanction.ban|Pardonner un joueur avec raison obligatoire.| 
|/|warn|null|'nom joueur''raison !=null'|LP.sanction.warn|Avertissement sur un joueur avec raison obligatoire.|
|/|kick|null|'nom joueur''raison !=null'|LP.sanction.kick|Exclue le joueur avec une raison obligatoire.| 
|/|mute|null|'nom joueur''raison !=null'|LP.sanction.mute|Met sous silence un joueur dans le chat avec raison  obligatoire.| 
|/|unmute|null|'nom joueur''raison !=null'|LP.sanction.mute|Donner la parole à un joueur préalablement "mute" avec raison obligatoire.|
|/|tempban|null|'nom joueur''raison !=null'|LP.sanction.ban|Bannir un joueur pour un temp donner avec raison obligatoire| 
|/|tempmute|null|'nom joueur''raison !=null'|LP.sanction.mute|Mute un joueur pour un temp donner avec raison obligatoire.| 
|/|historique|null|'nom joueur''raison !=null'|LP.sanction.h|Affiche toutes les sanctions du joueur.|
|/|resethistorique|null|'nom joueur''raison !=null'|LP.sanction.rh|Supprime l'historique des sanctions du joueur.|

Toutes les données sont stockées sur la base de donné en fonction de 'l'UUID' du joueur pour permettre un suivi de leurs infractions.


# Diagramme de cas d'utilisation

![Diagramme de cas d'utilisation](doc/DiagCasUtilisation.drawio.png)


## Cas d'un joueur inconnue au serveur.
1. Lors de la connection au serveur pour la premiere fois le joueur est invité à enregister un mot de passe pour pouvoir avoir acces au serveur il a alors X minute pour renseigner sont mot de passe au bout de ce temps impartie il est exclu.

- Si celui ci ne renseigne pas de mot de passe pour une premiere connection ses données joueur ne sont pas inscrite dans la base de données donc à la prochaine reconnection on demande un enregistrement.

- Si celui ci renseigne un mot de passe les données joueurs peuvent etre enregistrer dans la base de données.
	- Données enregistrer : 
		-  	Jouer-mot-de-passe ("Son mot de passe hash par algorithme sha256 + un préfixe salage").
	- Données ajouter par default :
		- 	Joueur-ban (0) .
		- 	Joueur-warn (0) . 
	- Données enregistrer automatique en fonction du joueur:
		-	Joueur-pseudo ("Son peseudo").
		-   Joueur-uuid ("Son uudi").

## Cas d'un joueur connue au serveur.
2. Lors d'une connection avec connaissance du joueur dans la base de données.

- Le joueur devras renseigner sont mot de passe dans le X minutes suivant la connection sinon celui ci sera exclu:
	- Si celui ci ne coresspond pas le joueur est invité à retaper sont mot de passe il a alors trois tentative si celui ci echou il sera déconnecter du serveur.

## Cas d'un joueur deja en ligne sur le serveur.
3. Lors d'une connection au serveur avec le même pseudo qu'un autre joueur à des fin d'usurpé son identité.

	- Si un joueur essaye de ce connecter à un serveur en prenent l'identité de celui ci il serra bloque avant d'entre sur le serveur.



# Lien utiles à la suivie du projet

> Liens ([Plannig LPsecurity](https://docs.google.com/spreadsheets/d/1M6eF-qHVqqDWeEFvFPF7m3PK6LDU6fDgVOAws3adxWI/edit?usp=sharing))
, ([Dictionnaire des données](https://docs.google.com/spreadsheets/d/100Dfm-IlbA1CwI49hHGxUlPoba_JcX5GFmZyjgF3IZk/edit?usp=sharing))

# Lien utiles au test réaliser sur le projet
> La Base de donnée.

> Le code java.