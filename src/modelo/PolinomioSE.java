/*
 * Copyright (C) 2020 Oscar Arenas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package modelo;

/**
 *
 * @author Oscar Arenas
 */
public class PolinomioSE {

    private Nodo primerTermino;
    private int n;

    public PolinomioSE() {
        primerTermino = null;
        n = 0;
    }

    public boolean agregar(double coeficiente, int exponente) {
        if (coeficiente != 0 && exponente >= 0) {
            Nodo actual = primerTermino;
            Nodo anterior = null;

            while (actual != null && exponente > actual.exponente) {
                anterior = actual;
                actual = actual.siguiente;
            }
            if (actual != null && exponente == actual.exponente) {
                return false; // No se permiten exponentes repetidos
            }
            Nodo nuevoNodo = new Nodo(coeficiente, exponente, actual);

            if (anterior == null) {
                primerTermino = nuevoNodo;
            } else {
                anterior.siguiente = nuevoNodo;
            }
            n++;
            return true;
        }
        return false;
    }

    public boolean eliminar(int exponente) {
        Nodo actual = primerTermino;
        Nodo anterior = null;

        while (actual != null && exponente < actual.exponente) {
            anterior = actual;
            actual = actual.siguiente;
        }
        if (actual != null && exponente == actual.exponente) {
            if (anterior == null) {
                primerTermino = actual.siguiente;
            } else {
                anterior.siguiente = actual.siguiente;
            }
            n--;
            return true;
        }
        return false;
    }

    /**
     * Genera una representación en cadena (String) del polinomio. La cadena
     * contiene todos los términos del polinomio en orden descendente de los
     * exponentes.
     *
     * @return Cadena que representa el polinomio.
     */
    @Override
    public String toString() {
        String cadena = "";
        Nodo actual = primerTermino;

        while (actual != null) {
            double coeficiente = actual.coeficiente;
            int exponente = actual.exponente;
            String signo = "";

            if (actual.siguiente!= null) {
                if (coeficiente < 0) {
                    signo = " - ";
                } else {
                    signo = " + ";
                }
            } else if (coeficiente < 0) {
                signo = "-";
            }
            cadena = signo + convertirTermino(coeficiente, exponente) + cadena;
            actual = actual.siguiente;
        }
        return cadena;
    }

    /*
     * Convierte los parámetros en una representación en cadena (String) del 
     * término en función de x. Por ejemplo, si el coeficiente es 5 y el 
     * exponente es 3 el método retorna la cadena "5x^3"
     */
    private String convertirTermino(double coeficiente, int exponente) {
        coeficiente = Math.abs(coeficiente);
        int valorEntero = (int) coeficiente;
        String cadena = "";

        if (valorEntero == coeficiente) {
            cadena += valorEntero;
        } else {
            cadena += coeficiente;
        }
        if (exponente > 0) {
            if (coeficiente == 1) {
                cadena = "";
            }
            if (exponente == 1) {
                cadena += "x";
            } else {
                cadena += "x^" + exponente;
            }
        }
        return cadena;
    }

    private class Nodo {

        // Término
        double coeficiente;
        int exponente;
        // Enlace
        Nodo siguiente;

        public Nodo(double coeficiente, int exponente, Nodo siguiente) {
            this.coeficiente = coeficiente;
            this.exponente = exponente;
            this.siguiente = siguiente;
        }

        public Nodo(double coeficiente, int exponente) {
            this.coeficiente = coeficiente;
            this.exponente = exponente;
            siguiente = null;
        }

        public double getCoeficiente() {
            return coeficiente;
        }

        public void setCoeficiente(double coeficiente) {
            this.coeficiente = coeficiente;
        }

        public int getExponente() {
            return exponente;
        }

        public void setExponente(int exponente) {
            this.exponente = exponente;
        }

        public Nodo getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo siguiente) {
            this.siguiente = siguiente;
        }
    }
}
