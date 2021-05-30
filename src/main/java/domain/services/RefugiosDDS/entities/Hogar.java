package domain.services.RefugiosDDS.entities;

import java.util.List;

public class Hogar {

  String id;
  String nombre;
  Ubicacion ubicacion;
  String telefono;
  Admisiones admisiones;
  int capacidad;
  int lugaresDisponibles;
  boolean patio;
  List<String> caracteristicas;



}
