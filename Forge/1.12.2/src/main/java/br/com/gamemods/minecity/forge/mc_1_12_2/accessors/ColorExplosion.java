package br.com.gamemods.minecity.forge.mc_1_12_2.accessors;

import br.com.gamemods.minecity.forge.base.accessors.entity.base.IEntity;
import br.com.gamemods.minecity.forge.base.accessors.world.IExplosion;
import br.com.gamemods.minecity.forge.base.core.Referenced;
import br.com.gamemods.minecity.forge.mc_1_12_2.core.transformer.forge.ColorExplosionTransformer;
import br.com.gamemods.minecity.forge.mc_1_12_2.core.transformer.forge.ColorInterfaceTransformer;
import net.minecraft.world.Explosion;

@Referenced(at = ColorInterfaceTransformer.class)
public interface ColorExplosion extends IExplosion
{
    @Override
    default double getExplosionX() {
        return ((Explosion) this).getPosition().x;
    }

    @Override
    default double getExplosionY() {
        return ((Explosion) this).getPosition().y;
    }

    @Override
    default double getExplosionZ() {
        return ((Explosion) this).getPosition().z;
    }

    @Referenced(at = ColorExplosionTransformer.class)
    @Override
    IEntity getExploder();
}
