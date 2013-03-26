Näin asennat Jenkinsin
======================

1. Tomcat usersille
-------------------

* Ota ssh-yhteys laitoksen users-palvelimelle: ssh omatunnari@users.cs.helsinki.fi
* Asenna tomcat komennolla: wanna-tomcat


2. Jenkinsin lataaminen
-----------------------

* Mene tomcatin webapps kansioon: cd tomcat/webapps
* Lataa jenkins: wget http://mirrors.jenkins-ci.org/war/latest/jenkins.war
    
    
3. Tomcat käyntiin
------------------

* Käynnistä tomcat komennolla: start-tomcat
* Kokeile toimiiko Jenkins: t-omatunnari.users.cs.helsinki.fi/jenkins
    

4. Jenkinsin konffaaminen
-------------------------

GitHub toimintakuntoon
* Manage jenkins -> Manage plugins -> Available -välilehti: Etsi GitHub plugin ja asenna.
* Mene usersin juuressa sijaitsevaan .ssh -kansioon ja seuraa [ohjeita](https://help.github.com/articles/generating-ssh-keys)

Jenkins turvalliseksi [täältä](https://wiki.jenkins-ci.org/display/JENKINS/Standard+Security+Setup)

Maven
* Manage jenkins -> Configure System
* Maven -kohdan alta pitäisi defaulttiasetusten riittää, nimeksi riittää Maven.
    
Nyt pitäisi Jenkinsin olla valmis ja ensimmäisen laskarin [ohjeita soveltamalla](https://github.com/mluukkai/ohtu2013/wiki/laskari-1) saat projektisi liitettyä.
