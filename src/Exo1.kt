fun calculDefenseTotal(defense: Int, typeArmure: Int, rarete: Int): Int = defense + typeArmure + rarete

fun lanceDes(nombreDes: Int, nombreFace: Int): Int {
    var res = 0
    for (i in 1..nombreDes) {
        res += (1..nombreFace).random()
    }
    return res
}

fun calculDegatArme(nbDes: Int, nbFace: Int, bonus: Int, activeCrit: Int, mutliplicateurCrit: Int): Int {
    var resDes = lanceDes(nbDes, nbFace)
    if (resDes >= activeCrit) {
        resDes *= mutliplicateurCrit
        resDes += bonus
    }
    if (resDes <= 0) {
        resDes = 0
    }
    return resDes
}

fun attaque(pv: Int, def: Int, degat: Int, nomAttaquant: String, nomCible: String) {
    var armeDegat = degat - def
    if (armeDegat <= 0) {
        armeDegat = 0
    }
    var pvActuelle = pv - armeDegat
    if (pvActuelle < 0){
        pvActuelle = 0
    }
    println("$nomAttaquant attaque $nomCible pour $armeDegat point de dégats")
}

fun boirePotion(nomPerso: String, pv: Int, pvMax: Int, puissancePotion: Int) {
    val exPv = pv
    var newPv = pv + puissancePotion
    if (newPv > pvMax) {
        newPv = pvMax
    }
    val recup = newPv - exPv
    println("$nomPerso boit une potion et récupère $recup PV")
}

fun bouleDeFeu(nomAttaquant: String, nomCible: String, degat: Int, def: Int, pvCible: Int) {
    val pvBase = pvCible
    var degat = (lanceDes(degat / 3, 6)) - def
    var pvActuelle = pvCible
    if (degat < 0) {
        degat = 0
    }
    pvActuelle -= degat
    if (pvActuelle < 0) {
        pvActuelle = 0
    }
    val pvPerdu = pvCible - pvActuelle
    println("$nomAttaquant lance une boule de feu et inflige $pvPerdu points de dégâts à $nomCible.")

}

fun missileMagique(nomAttaquant: String, nomCible: String, degat: Int, def: Int, pvCible: Int) {
    var pvActuelle = pvCible
    for (i in 1..degat / 2) {
        var missile = lanceDes(1, 6) - def
        if (missile < 0) {
            missile = 0
        }
        pvActuelle -= missile
        if (pvActuelle <= 0) {
            pvActuelle = 0
            var pvPerdu = pvCible - pvActuelle
            println("$nomAttaquant lance une missile magique et inflige $pvPerdu points de dégâts à $nomCible.")
            break
        }
        var pvPerdu = pvCible - pvActuelle
        println("$nomAttaquant lance une missile magique et inflige $pvPerdu points de dégâts à $nomCible.")
    }
}

fun invocationArme(nomAttaquant: String, typeArme: String) {
    val lance = lanceDes(1, 20)
    val rarete = if (lance < 5) "commune" else if (lance < 10) "rare" else if (lance < 15) "épic" else "légendaire"
    println("$nomAttaquant invoque un(e) $typeArme $rarete")
}

fun soins(nomAttaquant: String, degat: Int, pvCible: Int, pvMax: Int, isDead: Boolean) {
    val soin = degat / 2
    var pvAcutelle = pvCible
    if (isDead) {
        pvAcutelle -= soin
        if (pvAcutelle < 0) {
            pvAcutelle = 0
        }
        val pvPerdu = pvCible - pvAcutelle
        println("$nomAttaquant a infligé $pvPerdu de dégats")
    } else {
        pvAcutelle += soin
        if (pvAcutelle > pvMax) {
            pvAcutelle = pvMax
        }
        val pvGagne = pvAcutelle - pvCible
        println("$nomAttaquant a récupérer $pvGagne PV")
    }
}

fun afficherInventaire(nomPerso: String, inventaires: MutableList<String>) {
    println("inventaire de $nomPerso")
    for (inventaire in inventaires.indices) {
        println("$inventaire => ${inventaires[inventaire]}")
    }
}

fun choisirItem(nomPerso: String, inventaires: MutableList<String>) {
    val inv = afficherInventaire(nomPerso, inventaires)
    println("choisissez l'item")
    var i = 0
    while (i <= 1) {
        var res = readln().toInt()
        if (res in inventaires.indices) {
            println("$nomPerso utilise ${inventaires[res]}")
            i++
        } else {
            println("valeur non valide. veuillez recommencez")
            val inv = afficherInventaire(nomPerso, inventaires)
        }
    }
}

fun tourJoueur(
    def: Int, degat: Int, nomAttaquant: String, nomCible: String, nomPerso: String,
    pv: Int, pvMax: Int, pvCible: Int,
    isDead: Boolean, inventaires: MutableList<String>
) {

    println("veuillez choisir une action")
    val actions = listOf("Attaquer", "Boule de feu", "Missile Magique", "Soins", "Utiliser un item")
    for (choix in actions.indices) {
        println("$choix => ${actions[choix]}")
    }
    var i = 0
    while (i < 1) {
        var res = readln().toInt()
        when (res) {
            0 -> {
                attaque(pv, def, degat, nomAttaquant, nomCible)
                i++
            }

            1 -> {
                bouleDeFeu(nomAttaquant, nomCible, degat, def, pvCible)
                i++
            }

            2 -> {
                missileMagique(nomAttaquant, nomCible, degat, def, pvCible)
                i++
            }

            3 -> {
                soins(nomAttaquant, degat, pvCible, pvMax, isDead)
                i++
            }

            4 -> {
                choisirItem(nomPerso, inventaires)
            }

            else -> println("recommencez")
        }
    }

}

fun tourOrdinateur(
    def: Int, degat: Int, nomAttaquant: String, nomCible: String, nomPerso: String,
    pv: Int, pvMax: Int,pvCible: Int,
    isDead: Boolean, inventaires: MutableList<String>
) {
    val lance = lanceDes(1, 30)
    val actions = listOf("Attaquer", "Boule de feu", "Missile Magique", "Soins", "Utiliser un item")
    for (choix in actions.indices) {
        println("$choix => ${actions[choix]}")
    }
    when (lance) {
        in 1..14 -> attaque(pv, def, degat, nomAttaquant, nomCible)
        in 15..19 -> bouleDeFeu(nomAttaquant, nomCible, degat, def, pvCible)
        in 20..24 -> missileMagique(nomAttaquant, nomCible, degat, def, pvCible)
        else -> soins(nomAttaquant, degat, pvCible, pvMax, isDead)

    }
}
fun tourCombat(
    def: Int, degat: Int, nomAttaquantJ: String, nomCibleJ: String, nomPersoJ: String,
    pv: Int, pvMax: Int,pvCible: Int, nomAttaquantO: String, nomCibleO: String, nomPersoO: String,
    isDead: Boolean, inventaires: MutableList<String>){
    println("Début du tour")
    tourJoueur(def,degat,nomAttaquantJ,nomCibleJ,nomPersoJ,pv,pvMax,pvCible,true, mutableListOf("épee","pioche","carotte"))
    tourOrdinateur(def,degat,nomAttaquantO,nomCibleO,nomPersoO,pv,pvMax,pvCible,true, mutableListOf("épee","pioche","carotte"))
    println("Fin du tour")
}


fun main() {
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
//     choisirItem("Ligma",mutableListOf("crampte","rizz","lookmaxing"))
//    tourJoueur(
//        6, 20, "François", "Isma", "François", 30, 120,
//        40, false, mutableListOf("épee", "pioche", "pelle", "or")
//    )
//    tourOrdinateur(
//        6, 20, "François", "Isma", "François", 30, 120,
//        40, false, mutableListOf("épee", "pioche", "pelle", "or")
//    )
    tourCombat(6, 20, "François", "Ordinateur", "François", 30, 120,
        40, "Ordinateur","Joueur","Ordinateur",true,mutableListOf("épee", "pioche", "pelle", "or"))
}
