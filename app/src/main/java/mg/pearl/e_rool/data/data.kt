package mg.pearl.e_rool.data

import androidx.compose.ui.graphics.Color
import mg.pearl.e_rool.R

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
data class TypeUtilisateur(
    var ifClientClicked:Boolean,
    var ifConducteurClicked:Boolean

)
data class ColorOfButton(
    var colorWhiteButton:Color,
    var colorGreenButton:Color,
    var ColorWhiteText: Color,
    var ColorGreenText: Color

)
val login=Login("","")
val apropos=Apropos("","","","")
val infoPlus=InformationComplementaire("","","","",0)
val idUser=UserId("")
val utilisateurCurrent=Utilisateur("","","","","","","","","","",0)
val typeUtilisateur=TypeUtilisateur(false,false)
val colorButton=ColorOfButton(Color.Transparent,Color.Transparent,Color(23,193,114),Color(23,193,114))
val listeUtilisateur= mutableListOf<Utilisateur>()