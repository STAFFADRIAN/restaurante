package controller

import model.ManagementDb
import view.AppView

class AppCotroller(val view : AppView) {
    val conexion: ManagementDb = ManagementDb.getInstance()
   fun onStart():Int{
        var option = view.menu()
       return option
    }

    fun selectOption (){
        var fin= false
        val appView: AppView = AppView()
        val appController: AppCotroller = AppCotroller(appView)
        conexion.conectDb()
        while(fin==false){

            when (appController.onStart()) {
                1 -> conexion.selectAll()
                2 -> conexion.selectAllbyId()
                3 -> conexion.createReserve()
                4 -> conexion.updateReserve()
                5 -> conexion.deleteReserveById()
                6 -> fin=true
                else -> {
                    println("Ha introdución una opcion no valida")
                }
            }
        }
    }
    fun OnExit(){
        conexion.disconectDb()
    }
}