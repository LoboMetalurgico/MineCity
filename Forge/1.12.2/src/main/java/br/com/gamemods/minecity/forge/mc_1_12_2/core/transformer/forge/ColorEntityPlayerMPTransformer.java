package br.com.gamemods.minecity.forge.mc_1_12_2.core.transformer.forge;

import br.com.gamemods.minecity.forge.base.core.Referenced;
import br.com.gamemods.minecity.forge.base.core.transformer.forge.entity.EntityPlayerMPTransformer;
import br.com.gamemods.minecity.forge.mc_1_12_2.core.MineCityColorCoreMod;

@Referenced
public class ColorEntityPlayerMPTransformer extends EntityPlayerMPTransformer {
    @Referenced(at = MineCityColorCoreMod.class)
    public ColorEntityPlayerMPTransformer() {
        super("br.com.gamemods.minecity.forge.mc_1_12.2.accessors.entity.FrostEntityPlayerMP");
    }
}
