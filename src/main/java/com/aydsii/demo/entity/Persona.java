package com.aydsii.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.Check;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Personas")
@Check(constraints = "puntos >= 0")
@Getter
@Setter
@NoArgsConstructor
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "dni", nullable = false, length = 10, unique = true)
    private String dni;

    @Column(name = "direccion", length = 150)
    private String direccion;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "nombre_usuario", nullable = false, length = 50, unique = true)
    private String nombreUsuario;

    @Column(name = "puntos", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer puntos;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;
}
