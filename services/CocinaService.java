package services;

import entidades.*;
import excepciones.StockInsuficienteException;
import interfaces.Despensable;
import excepciones.VidaUtilInsuficienteException;

import java.util.ArrayList;
import java.util.List;

public class CocinaService {

    private Chef chef;
    private Despensa despensa;

    public CocinaService() {
    }

    public CocinaService(Chef chef, Despensa despensa) {
        this.chef = chef;
        this.despensa = despensa;
    }


        public String prepararPlatos(Receta receta) throws StockInsuficienteException, VidaUtilInsuficienteException {
        List<Despensable> ingredientes = receta.getIngredientes();
        List<Despensable> utensilios = receta.getUtensilios();
        boolean sePuedePreparar = true;

        DespensaService despensaService = new DespensaService(despensa);

        if (!despensaService.verificarStock(ingredientes)) {
            sePuedePreparar = false;
        }
        if (!despensaService.verificarVidaUtil(utensilios)) {
            sePuedePreparar = false;
        }

        for (Despensable ingrediente : ingredientes) {
            despensa.sacar(ingrediente.getNombre(), ingrediente.getCantidadDisponible());

        }

        for (Despensable utensilio : utensilios) {
             despensa.sacar(utensilio.getNombre(), utensilio.getCantidadDisponible());
        }

        if (sePuedePreparar) {
            return "El Chef " + chef.getNombre() + " ha preparado exitosamente la receta: " + receta.getClass().getSimpleName();
        } else {
            return "No se pudo preparar la receta: " + receta.getClass().getSimpleName();
        }


    }


//    Aca se puede ver que se está utilizando el método allMatch de la clase Stream, que recibe un predicado y devuelve un booleano.
//    public String prepararPlatos(Receta receta) throws StockInsuficienteException, VidaUtilInsuficienteException {
//        DespensaService despensaService = new DespensaService(despensa);
//
//        boolean sePuedePreparar = despensaService.verificarStock(receta.getIngredientes()) && despensaService.verificarVidaUtil(receta.getUtensilios());
//
//            if (sePuedePreparar) {
//                try {
//                    receta.getIngredientes().forEach(ingrediente -> {
//                        try {
//                            despensa.sacar(ingrediente.getNombre(), ingrediente.getCantidadDisponible());
//                        } catch (StockInsuficienteException e) {
//                            throw new RuntimeException(e);
//                        } catch (VidaUtilInsuficienteException e) {
//                            throw new RuntimeException(e);
//                        }
//                    });
//
//                } catch (Exception e) {
//                    System.out.println(e.getMessage());
//                }
//                try {
//                    receta.getUtensilios().forEach(utensilio -> {
//                        try {
//                            despensa.sacar(utensilio.getNombre(), utensilio.getCantidadDisponible());
//                        } catch (StockInsuficienteException e) {
//                            throw new RuntimeException(e);
//                        } catch (VidaUtilInsuficienteException e) {
//                            throw new RuntimeException(e);
//                        }
//                    });
//                } catch (Exception e) {
//                    System.out.println(e.getMessage());
//                }
//                return "El Chef " + chef.getNombre() + " ha preparado exitosamente la receta: " + receta.getClass().getSimpleName();
//            } else {
//                return "No se pudo preparar la receta: " + receta.getClass().getSimpleName();
//            }
//
//    }
}






















