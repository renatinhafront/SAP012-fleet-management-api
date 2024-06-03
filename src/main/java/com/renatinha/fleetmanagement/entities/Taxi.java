package com.renatinha.fleetmanagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "taxis")
public class Taxi {

    //@is coluna de identificadores da tabela
    @Id
    private int id;
    private String plate;


    //mapeando a lista de trajetoria - lazy faz por baixo um carregamento preguiçoso da lista de trajetoria default
    @OneToMany(mappedBy = "taxi")
    private List<Trajectory> trajectories;

}
