
fun calculDefenseTotal(defense: Int, typeArmure: Int, rarete: Int): Int = defense+typeArmure+rarete

fun lanceDes(nombreDes: Int, nombreFace: Int): Int {
              var res = 0
              for (i in 1..nombreDes) {
                  res += (1..nombreFace).random()
              }
              return res
          }
fun calculDegatArme(nbDes: Int,nbFace: Int,bonus: Int,activeCrit: Int, mutliplicateurCrit: Int): Int{
    var resDes = lanceDes(nbDes,nbFace)
    if (resDes >= activeCrit){
        resDes *= mutliplicateurCrit
        resDes += bonus
    }
    if (resDes <= 0){
        resDes = 0
    }
    return resDes
}
fun attaque(pv: Int,def: Int, degat: Int, nomAttaquant: String, nomCible: String){
    var armeDegat = degat-def
    var ciblePv = pv
    if (armeDegat <= 0){
        armeDegat = 0
    }
    ciblePv = pv - armeDegat
    if (ciblePv <0){
        ciblePv = 0
    }
    println("$nomAttaquant attaque $nomCible pour $armeDegat point de dégats")
}
fun boirePotion(nomPerso: String, pv: Int, pvMax: Int, puissancePotion: Int){
    val exPv = pv
    var newPv = pv + puissancePotion
    if (newPv > pvMax){
        newPv = pvMax
    }
    val recup = newPv - exPv
    println("$nomPerso boit une potion et récupère $recup PV")
}

fun bouleDeFeu(nomCaster: String,nomCible: String,scoreAttaqueCaster: Int,defCible: Int, pvCible: Int){
    val pvBase = pvCible
    var degat = (lanceDes(scoreAttaqueCaster/3,6)) - defCible
    var pvActuelle = pvCible
    if (degat < 0){
        degat = 0
    }
    pvActuelle -= degat
    if (pvActuelle < 0 ){
        pvActuelle = 0
    }
    val pvPerdu = pvCible -pvActuelle
    println("$nomCaster lance une boule de feu et inflige $pvPerdu points de dégâts à $nomCible.")

}
fun missileMagique(nomCaster: String,nomCible: String,scoreAttaqueCaster: Int,def: Int,pvCible: Int){
    var pvActuelle = pvCible
    for (i in 1..scoreAttaqueCaster/2){
        var missile = lanceDes(1,6) - def
        if (missile < 0){
            missile = 0
        }
        pvActuelle -= missile
        if (pvActuelle <= 0){
            pvActuelle = 0
            var pvPerdu = pvCible - pvActuelle
            println("$nomCaster lance une missile magique et inflige $pvPerdu points de dégâts à $nomCible.")
            break
        }
        var pvPerdu = pvCible - pvActuelle
        println("$nomCaster lance une missile magique et inflige $pvPerdu points de dégâts à $nomCible.")
        }
    }
fun invocationArme(nomCaster: String,typeArme: String){
    val lance = lanceDes(1,20)
    val rarete = if (lance<5) "commune" else if (lance < 10) "rare" else if (lance < 15) "épic" else "légendaire"
    println("$nomCaster invoque un(e) $typeArme $rarete")
    }
fun soins(nomCaster: String,scoreAttaqueCaster: Int,pvCible: Int,pvMax: Int,isDead: Boolean){
    val soin = scoreAttaqueCaster/2
    var pvAcutelle = pvCible
    if (isDead){
        pvAcutelle -= soin
        if (pvAcutelle < 0){
            pvAcutelle =0
        }
        val pvPerdu = pvCible -pvAcutelle
        println("$nomCaster a infligé $pvPerdu de dégats")
    }
    else{
        pvAcutelle += soin
        if (pvAcutelle >pvMax){
            pvAcutelle = pvMax
        }
        val pvGagne = pvAcutelle-pvCible
        println("$nomCaster a récupérer $pvGagne PV")
    }
}
fun afficherInventaire(nomPerso: String, inventaires: MutableList<String>){
    println("inventaire de $nomPerso")
    for (inventaire in inventaires.indices){
        println("$inventaire => ${inventaires[inventaire]}")
    }
}
fun choisirItem(nomPerso: String,inventaires: MutableList<String>){
    val inv = afficherInventaire(nomPerso,inventaires)
    println("choisissez l'item")
    var i = 0
    while (i <= 1){
        var res = readln().toInt()
        if (res in inventaires.indices){
            println("$nomPerso utilise ${inventaires[res]}")
            i++
        }
        else{
            println("valeur non valide. veuillez recommencez")
            val inv = afficherInventaire(nomPerso,inventaires)
        }
    }
}
fun tourJoueur(defense: Int, typeArmure: Int, rarete: Int, nombreDes: Int, nombreFace: Int, bonus: Int,activeCrit: Int,
               mutliplicateurCrit: Int,def: Int, degat: Int, nomAttaquant: String, nomCible: String,nomPerso: String,
               pv: Int, pvMax: Int, puissancePotion: Int,nomCaster: String,nomCible: String,scoreAttaqueCaster: Int,defCible: Int, pvCible: Int,
               ){
    println("veuillez choisir une action")
    val actions = listOf("Attaquer","Boule de feu", "Missile Magique", "Soins","Utiliser un item")
    for (choix in actions.indices){
        println("$choix => ${actions[choix]}")
    }
    var res  = readln().toInt()
    when(res){
        0 -> attaque()
        1 -> bouleDeFeu()
        2 -> missileMagique()
        3 -> soins()
        4 -> choisirItem()
    }

}

 fun main(){
//     println(calculDefenseTotal(3,2,1))
//     val unDes6 = lanceDes(1,6)
//     val deuxDes5 = lanceDes(2,5) val quatreDes20 = lanceDes(4,20)
//     println(unDes6)
//     println(deuxDes5)
//     println(quatreDes20)
//     println(calculDegatArme(1,6,2,6,2))
//     println(calculDegatArme(1,8,3,5,3))
//     println(calculDegatArme(2,4,2,10,2))
//     attaque(10,calculDefenseTotal(3,2,1),calculDegatArme(1,6,2,6,2),"François","Clément")
//     boirePotion("François",8,20,6)
//     bouleDeFeu("pierre","caillou",8,6,50)
//     missileMagique("François","Isma",25,3,150)
//     invocationArme("François","épee")
//     soins("François",30,10,100,false)
//     afficherInventaire("Ligma", mutableListOf("crampte","rizz","lookmaxing"))
     choisirItem("Ligma",mutableListOf("crampte","rizz","lookmaxing"))
          }
