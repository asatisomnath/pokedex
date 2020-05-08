package com.maltem.pokedex.acccessor;

import com.maltem.pokedex.datamodels.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    List<Pokemon> findAll();

    List<Pokemon> findByOrderByNameAsc();
    List<Pokemon> findByOrderByType_IdAsc();
    List<Pokemon> findByOrderByStats_BaseAttackAsc();
    List<Pokemon> findByOrderByStats_BaseDefenseAsc();
    List<Pokemon> findByOrderByStats_BaseStaminaAsc();

    List<Pokemon> findByName(String name);
    List<Pokemon> findByType_Id(String type);
    List<Pokemon> findByStats_BaseAttack(Integer baseAttack);
    List<Pokemon> findByStats_BaseDefense(Integer baseDefense);
    List<Pokemon> findByStats_BaseStamina(Integer baseStamina);
}
