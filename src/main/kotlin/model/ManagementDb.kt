package model

import view.AppView
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement


class ManagementDb private constructor() {

    private val appView: AppView = AppView()
    private val statements: Setences = Setences()

    companion object {
        //Un atributo que contenga la instanciación de clase
        private var instance: ManagementDb? = null


        //Una funcion para instanciar la clase en sí
        fun getInstance(): ManagementDb {
            if (instance == null) {
                instance = ManagementDb()
            }
            return instance!!
        }
    }

    private val url: String = "jdbc:mysql://localhost:3308/"
    private val bd: String = "restaurante"
    private val user: String = "root"
    private val pass: String = ""
    private var query: String = ""

    @Volatile
    private var conn: Connection? = null
    private var st: Statement? = null
    private var rs: ResultSet? = null


    //Conexion con la base de datos
    fun conectDb() {
        if (conn == null) {
            println("[Conexion realizada]")
            conn = DriverManager.getConnection(url + bd, user, pass)

        } else {
            println("[Conexion ya existente]")
        }
    }

    //Desconexion de la base de datos
    fun disconectDb() {
        if (conn != null) {
            conn!!.close()
            println("[Desconexión Realizada]")
        } else {
            println("[No existe conexion a Base de Datos]")
        }
    }


    fun selectAll() {

        //Instanciamos la consulta y la ejecutamos
        query = statements.select
        st = conn!!.createStatement()
        rs = st!!.executeQuery(query)
        conn!!.autoCommit = false
        conn!!.commit()
        //Pintamos los resultados
        while (rs!!.next()) {
            println("Id " + rs!!.getString(1))
            println("Nombre " + rs!!.getString(2))
            println("Comensales " + rs!!.getString(3))
            println("Fecha " + rs!!.getString(4) + "\n")

        }

    }

    //Controlar exception id
    fun selectAllbyId() {

        val id = appView.askId()
        //Instanciamos la consulta y la ejecutamos
        query = statements.selectAll + "$id"
        st = conn!!.createStatement()
        rs = st!!.executeQuery(query)

        if (rs!!.next()) {

            //Pintamos los resultados
            while (rs!!.next()) {
                println("Id " + rs!!.getString(1))
                println("Nombre " + rs!!.getString(2))
                println("Comensales " + rs!!.getString(3))
                println("Fecha " + rs!!.getString(4) + "\n")

            }

        } else {
            println("No existe")

        }
    }

    fun createReserve() {
        conn!!.autoCommit = false
        //Instaciamos la consulta
        query = "INSERT INTO clientes (nombre, comensales, fecha) VALUES (?, ?, ?)"

        //Pedimos al usuario que le de valor a las variables
        println("Para realizar la reserva cumplimente los siguientes datos \n Introduce su nombre:")
        val name = appView.askName()
        println("Introduce el numero de comensales: ")
        val nPerson = appView.askNumberPersons()
        println("Introduce la fecha :  (Formato valido yy-mm-dd)")
        val date = readln()

        //Insertamos los valores dados por el usuario en nuestras columnas
        val statement = conn!!.prepareStatement(query)
        statement.setString(1, name)
        statement.setInt(2, nPerson)
        statement.setString(3, date)

        val rowsInserted = statement.executeUpdate()
        if (rowsInserted > 0) {
            conn!!.commit()
            println("Nueva reserva realizada")
        } else {
            conn!!.rollback()
        }
    }

    //Modificar una reserva por id_reserva
    fun updateReserve() {
        conn!!.autoCommit = false
        val id = appView.askId()
        query = "UPDATE clientes SET nombre=?, comensales=?, fecha=? WHERE id_reserva=$id"

        //Pedimos al usuario que le de valor a las variables
        println("Para cambiar tu reserva cumplimente los siguientes datos \n Introduce su nombre:")
        val name = appView.askName()
        println("Introduce el numero de comensales: ")
        val nPerson = appView.askNumberPersons()
        println("Introduce la fecha :  (Formato valido yy-mm-dd)")
        val date = readln()

        //Seteamos los valores a los introducidos por el usuario
        val statement = conn!!.prepareStatement(query)
        statement.setString(1, name)
        statement.setInt(2, nPerson)
        statement.setString(3, date)

        val rowsUpdated = statement.executeUpdate()
        if (rowsUpdated > 0) {
            println("El usuario con id_reserva = $id, se ha actualizado correctamente")
            conn!!.commit()
        } else {
            conn!!.rollback()
            System.out.println("La id:$id de reserva no existe")
            appView.reAskId()
        }
    }


    //Borrar reserva por id_reserva
    fun deleteReserveById() {
        val id = appView.askId()
        //Instanciamos la query y la ejecutamos
        query = statements.delete + "$id"
        val statement = conn!!.prepareStatement(query)

        val rowsDeleted = statement.executeUpdate()
        if (rowsDeleted > 0) {
            println("El usuario con id_reserva $id, se ha borrado correctamente")
        } else {
            appView.reAskId()
        }
    }


}



