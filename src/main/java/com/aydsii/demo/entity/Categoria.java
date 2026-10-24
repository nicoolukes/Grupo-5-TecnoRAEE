package com.aydsii.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Entity
@Table(name = "Categorias")
@Getter
@Setter
@NoArgsConstructor
public class Categoria {
 
    @Id
    @Column(name = "id_categoria")
    private Integer id;
 
    private String nombre;
}