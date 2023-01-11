package org.acme.form;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class PokemonUpdateForm {
    public String name;
    public Integer level;
    public Boolean evolves;
}
