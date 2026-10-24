package com.aydsii.demo.entity;


import org.hibernate.annotations.Audited.Table;
//import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table (name = "personas")
@Data
@NoArgsConstructor 
@AllArgsConstructor 
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private int id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String dni;

    @Column(nullable = false, length = 100)
    private String direccion;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(name="nombre_usuario", nullable = false, length = 100)
    private String nombreUsuario;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;
}
