/**
 * Copyright 2017 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 package com.delug3.indiecorner.model;

import com.google.firebase.firestore.IgnoreExtraProperties;

/**
 * Juego POJO.
 */
@IgnoreExtraProperties
public class Juego {

    private String nombre;
    private String desarrolladora;
    private String descripcion;
    private String lanzamiento;
    private String foto;
    private String genero;
    private float puntuacion;
    private String trailer;
    private String web;

    public Juego() {}

    public Juego(String nombre, String desarrolladora, String descripcion, String lanzamiento,
                 String foto,String genero, float puntuacion,String trailer,String web) {
        this.nombre = nombre;
        this.desarrolladora = desarrolladora;
        this.descripcion = descripcion;
        this.lanzamiento = lanzamiento;
        this.foto = foto;
        this.genero = genero;
        this.puntuacion = puntuacion; //float, puntuacion sobre 5.Importante: valor number en firebase
        this.trailer = trailer;
        this.web = web;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesarrolladora() {
        return desarrolladora;
    }

    public void setDesarrolladora(String desarrolladora) {
        this.desarrolladora = desarrolladora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLanzamiento() {
        return lanzamiento;
    }

    public void setLanzamiento(String lanzamiento) {
        this.lanzamiento = lanzamiento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public float getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}
