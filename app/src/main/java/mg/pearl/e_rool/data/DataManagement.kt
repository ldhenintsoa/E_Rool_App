package mg.pearl.e_rool.data
import android.content.Context
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

fun getDataUserCurrentFromFireBase(context:Context,utilisateurCurrent:Utilisateur){
    val db = FirebaseFirestore.getInstance()
    db.collection("Utilisateur").get().addOnSuccessListener { utilisateur ->
        for (document in utilisateur) {
            if (idUser.idUserCurrent==document.id){
                utilisateurCurrent.adresseEmail= "${document.data["adresseEmail"]}"
                utilisateurCurrent.motDePasse="${document.data["motDePasse"]}"
                utilisateurCurrent.nomComplet="${document.data["nomComplet"]}"
                utilisateurCurrent.nomUtilisateur="${document.data["nomUtilisateur"]}"
                utilisateurCurrent.dateDeNaissance="${document.data["dateDeNaissance"]}"
                utilisateurCurrent.sexe="${document.data["sexe"]}"
                utilisateurCurrent.pays="${document.data["pays"]}"
                utilisateurCurrent.adresse="${document.data["adresse"]}"
                utilisateurCurrent.numeroTelephone="${document.data["numeroTelephone"]}"
                utilisateurCurrent.bioConducteur="${document.data["bioConducteur"]}"
                Toast.makeText(context, "Chargement des données avec succès!!!", Toast.LENGTH_SHORT).show()
                break
            }
        }
    }.addOnFailureListener { e ->
        Toast.makeText(context, "Chargement des données échouées:\n$e", Toast.LENGTH_SHORT).show()
    }

}
fun getDataAllUserFromFireBase(listeAllUser:MutableList<Utilisateur>){
    val db = FirebaseFirestore.getInstance()
    db.collection("Utilisateur").get().addOnSuccessListener { utilisateur ->
        for (document in utilisateur) {
            val user=Utilisateur(
                "${document.data["adresseEmail"]}",
                "${document.data["motDePasse"]}",
                "${document.data["nomComplet"]}",
                "${document.data["nomUtilisateur"]}",
                "${document.data["dateDeNaissance"]}",
                "${document.data["sexe"]}",
                "${document.data["pays"]}",
                "${document.data["adresse"]}",
                "${document.data["numeroTelephone"]}",
                "${document.data["bioConducteur"]}",
                0
            )
            listeAllUser.add(user)
        }
    }

}
