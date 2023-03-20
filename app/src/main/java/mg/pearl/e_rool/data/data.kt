package mg.pearl.e_rool.data

data class UserId(
    var idUserCurrent:String
)
data class Login(
    var adresseEmail:String,
    var motDePasse:String
    )
data class Apropos(
    var nomComplet:String,
    var dateDeNaissance:String,
    var sexe:String,
    var pays:String,
)
data class InformationComplementaire(
    var nomUtilisateur:String,
    var adresse:String,
    var numeroTelephone:String,
    var bioConducteur:String,
    var profil:Int,
)
data class Utilisateur(
    var adresseEmail:String,
    var motDePasse:String,
    var nomComplet:String,
    var nomUtilisateur:String,
    var dateDeNaissance:String,
    var sexe:String,
    var pays:String,
    var adresse:String,
    var numeroTelephone:String,
    var bioConducteur:String,
    var profil:Int,
)
val login=Login("","")
val apropos=Apropos("","","","")
val infoPlus=InformationComplementaire("","","","",0)
val idUser=UserId("")
val utilisateurCurrent=Utilisateur("","","","","","","","","","",0)
val listeUtilisateur= mutableListOf<Utilisateur>()