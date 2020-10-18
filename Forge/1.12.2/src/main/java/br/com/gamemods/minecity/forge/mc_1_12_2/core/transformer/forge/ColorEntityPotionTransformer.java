package br.com.gamemods.minecity.forge.mc_1_12_2.core.transformer.forge;

import br.com.gamemods.minecity.forge.base.core.Referenced;
import br.com.gamemods.minecity.forge.base.core.transformer.forge.entity.EntityPotionTransformer;
import br.com.gamemods.minecity.forge.mc_1_12_2.core.MineCityColorCoreMod;

@Referenced
public class ColorEntityPotionTransformer extends EntityPotionTransformer {
    @Referenced(at = MineCityColorCoreMod.class)
    public ColorEntityPotionTransformer() {
        super(1);
    }
}
