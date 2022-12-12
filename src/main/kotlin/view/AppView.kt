package view

import org.apache.commons.lang3.math.NumberUtils.isCreatable
import org.apache.commons.lang3.time.DateFormatUtils
import java.time.format.DateTimeFormatter
import java.util.*


class AppView {

    fun menu(): Int {
        println(
            "\n ¿Introduzca la opción a realizar? \n" +
                    "1.Mostrar todas las reservas \n" +
                    "2.Mostrar una reserva en especifico \n" +
                    "3.Crear una reserva nueva \n" +
                    "4.Modificar una reserva \n" +
                    "5.Borrar una reserva \n" +
                    "6.Salir del programa \n"
        )

        var option = readln().toInt()
        return option
    }

    fun askId(): Int {
        println("Introduzca la id de su reserva")
        var id = readln()
        while (!isCreatable(id)) {
            println("Introduzca el id de su reserva correctamente")
            id = readln()
        }
        return id.toInt()
    }

    fun reAskId() {
        println("Error no se ha encontrado la reserva ,Introduzca la id de su correctamente")
        askId()
    }

    fun askNumberPersons(): Int {

        var numberPerson = readln()
        while (!isCreatable(numberPerson)) {
            println("Introduzca el numero de comensales correctamente")
            numberPerson = readln()
        }
        return numberPerson.toInt()
    }

    fun askName(): String {

        var numberPerson = readln()
        while (isCreatable(numberPerson)) {
            println("Introduzca su nombre correctamente")
            numberPerson = readln()
        }
        return numberPerson
    }

    //Todavia no se incluye (Incompleta)
    fun askDate(): String {

        var date = readln()
        while (!DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.equals(date)) {
            println("Introduzca la fecha correctamente")
            date = readln()
        }
        return date
    }
}





