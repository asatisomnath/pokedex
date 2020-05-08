package com.maltem.pokedex.acccessor;

import com.maltem.pokedex.datamodels.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DataRepository extends JpaRepository<Data, Long> {
    List<Data> findByEmail(String email);

    List<Data> findByEmailAndPokemon_Dex(String email, Integer dex);
}

