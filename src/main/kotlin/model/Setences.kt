package model

data class Setences (var select :String = "SELECT * FROM clientes",
                     var selectAll :String = "SELECT * FROM clientes where id_reserva =",
                     var delete : String = "DELETE FROM clientes where id_reserva =",
                     var insert :String = "INSERT INTO clientes (nombre, comensales, fecha) VALUES (?, ?, ?) ",
                     var update : String = "UPDATE clientes SET nombre=?, comensales=?, fecha=? WHERE id_reserva=")
